package uo.sdi.business.integration;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import uo.sdi.business.LogMessagesService;
import uo.sdi.business.TaskService;
import uo.sdi.business.UserService;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.integration.exception.MessageNotOfExpectedTypeException;
import uo.sdi.business.integration.util.MessageResponseManager;
import uo.sdi.business.integration.util.MessageResponseManager.ErrorSeverity;
import uo.sdi.business.local.LocalTaskService;
import uo.sdi.business.local.LocalUserService;
import uo.sdi.dto.TaskDTO;
import uo.sdi.dto.UserDTO;
import uo.sdi.dto.UserNoPasswordDTO;
import uo.sdi.infrastructure.encryption.DecryptionFailedException;
import uo.sdi.infrastructure.encryption.Encryptor;
import alb.util.log.Log;

@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/canal_comandos_sdi_23") })
public class MessageListenerBean implements MessageListener {

    private final String LOG_HEADER = "MDB ---> ";

    @EJB(beanInterface = LocalUserService.class)
    private UserService userService;

    @EJB(beanInterface = LocalTaskService.class)
    private TaskService taskService;

    @EJB
    private LogMessagesService logger;

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory factory;

    // ============================
    // Recepción de los mensajes
    // ============================

    @Override
    public void onMessage(Message message) {
	Log.debug("%sSe ha empezado a procesar un mensaje.", LOG_HEADER);

	try {
	    processMessage(message);
	}

	catch (Exception ex) {
	    Log.error("%sHa ocurrido un error mientras se "
		    + "procesaba un mensaje.", LOG_HEADER);
	    Log.error(ex);

	    logger.log(message);

	    sendInfoError(message, "error_procesar_mensaje",
		    ErrorSeverity.WARNING);
	}
    }

    /**
     * Analiza un mensaje para determinar que operación se debe realizar.
     * 
     * @param msg
     *            mensaje que hay que procesar
     * @param user
     *            usuario que mando el mensaje
     * 
     * @throws JMSException
     *             Ha ocurrido un error al analizar el contenido del mensaje
     * 
     */
    private void processMessage(Message msg) throws JMSException {
	// =====================================
	// (1) Comprobar si la Operación válida
	// =====================================

	String operation = msg.getStringProperty("operacion");

	if (operation == null || operation == "") {
	    Log.debug("%sEl mensaje que se está procesando no indica "
		    + "qué operación se debe realizar. Redirigiendo"
		    + " a la cola de log.", LOG_HEADER);

	    logger.log(msg);

	    sendInfoError(msg, "operacion_no_valida", ErrorSeverity.WARNING);

	    return;
	}

	// ====================================================
	// (2) Realizar la operación si el mensaje es del tipo
	// esperado y tiene las credenciales del usuario
	// ====================================================

	Log.debug("%sSe está empezando a procesar un mensaje para "
		+ "realizar la operacion [%s]", LOG_HEADER, operation);

	try {
	    UserDTO user = validateCredentials(msg
		    .getStringProperty("credentials"));

	    if ("login".equals(operation)) {
		generateErrorIfTypeNotExpected(MapMessage.class, msg,
			"El mensaje de [login] debería ser un MapMessage");

		doLogin((MapMessage) msg, user);
	    }

	    else if ("hoy_atrasadas".equals(operation)) {
		generateErrorIfTypeNotExpected(MapMessage.class, msg,
			"El mensaje de [listar tareas] debería "
				+ "ser un MapMessage");

		doListTasksForTodayAndDelayedTasks((MapMessage) msg, user);
	    }

	    else if ("finalizar_tarea".equals(operation)) {
		generateErrorIfTypeNotExpected(MapMessage.class, msg,
			"El mensaje de [finalizar tarea] debería "
				+ "ser un ObjectMessage");

		doMarkAsFinish((MapMessage) msg, user);
	    }

	    else if ("nueva_tarea".equals(operation)) {
		generateErrorIfTypeNotExpected(ObjectMessage.class, msg,
			"El mensaje de [crear una nueva tarea] "
				+ "debería ser un MapMessage");

		doCreateTask((ObjectMessage) msg, user);
	    }

	    else {
		throw new BusinessException("La operación indicada no "
			+ "es válida.", "operacion_no_valida");
	    }

	    Log.debug("%sSe ha procesado con éxito un mensaje (operación: %s)",
		    LOG_HEADER, operation);
	}

	catch (BusinessException bs) {
	    Log.error("%sNo se ha podido realizar la operación solicitada",
		    LOG_HEADER);
	    Log.error("%s%s", LOG_HEADER, bs.getMessage());

	    logger.log(msg);

	    sendInfoError(msg, bs.getClaveFicheroMensajes(),
		    ErrorSeverity.WARNING);
	}
    }

    /**
     * Comprueba que el mensaje recibido es una instancia del tipo de mensaje
     * que se espera recibir.
     * 
     * @param expected
     *            tipo de mensaje esperado
     * @param msg
     *            mensaje que hay que validar
     * @param cause
     *            descripción del error (en caso de que msg no sea del tipo
     *            esperado)
     * 
     */
    private void generateErrorIfTypeNotExpected(final Class<?> clazz,
	    final Message msg, final String cause) {

	// msg implementa/hereda de la clase clazz
	// msg instanceOf clazz
	if (!clazz.isInstance(msg)) {
	    throw new MessageNotOfExpectedTypeException(cause, msg);
	}
    }

    // =====================================
    // Métodos de procesamiento de mensajes
    // =====================================

    /**
     * Le envía al usuario sus datos. Antes de llamar a este método se habrán
     * validado el login y la contraseña del usuario.
     * 
     * @throws JMSException
     * 
     */
    private void doLogin(MapMessage msg, UserDTO user) throws JMSException {
	Log.debug("%sEl usuario [%s] ha iniciado sesión.", LOG_HEADER,
		user.getLogin());

	MessageResponseManager.generateResponse(factory, msg.getJMSReplyTo(),
		new UserNoPasswordDTO(user));
    }

    /**
     * Lista las tareas que el usuario que mandó el mensaje tiene planeadas para
     * hoy, y le envía un respuesta con su información.
     * 
     * @param msg
     *            mensaje que hay que procesar
     * @param user
     *            usuario que envió el mensaje
     * 
     * @throws BusinessException
     * @throws JMSException
     * 
     */
    private void doListTasksForTodayAndDelayedTasks(MapMessage msg, UserDTO user)
	    throws JMSException, BusinessException {

	Log.debug("%sListando las tareas para hoy y las tareas retrasadas",
		LOG_HEADER);

	try {
	    List<TaskDTO> tareas = taskService.findTodayTasksByUserId(user
		    .getId());

	    MessageResponseManager.generateResponse(factory,
		    msg.getJMSReplyTo(), tareas);
	}

	catch (BusinessException ex) {
	    Log.error("%sEste método no lanza ninguna BusinessException pero "
		    + "la interfaz command lleva un throws BusinessException",
		    LOG_HEADER);

	    throw ex;
	}
    }

    /**
     * Marca como finalizada una tarea cuyo id se pasa en el mensaje.
     * 
     * @param msg
     *            mensaje con el id de la tarea
     * @param user
     *            usuario que solicita finalizar la tarea
     * 
     * @throws JMSException
     * @throws BusinessException
     * 
     */
    private void doMarkAsFinish(MapMessage msg, UserDTO user)
	    throws JMSException, BusinessException {

	Log.debug("%sMarcando como finalizada una tarea del usuario [%s]",
		LOG_HEADER, user.getLogin());

	Long idTarea = msg.getLong("id_tarea");

	taskService.markTaskAsFinished(user.getId(), idTarea);

	MessageResponseManager.generateResponseText(factory,
		msg.getJMSReplyTo(), "exito_finalizacion_tarea");
    }

    /**
     * Crea una nueva tarea con los datos que se le pasan como parámetro en el
     * mensaje.
     * 
     * @param msg
     *            mensaje con el objeto [TareaDTO.class]
     * @param user
     *            usuario que solicita crear la nueva tarea
     * 
     * @throws JMSException
     * @throws BusinessException
     * 
     */
    private void doCreateTask(ObjectMessage msg, UserDTO user)
	    throws JMSException, BusinessException {

	Log.debug("%sCreando una tarea del usuario [%s]", LOG_HEADER,
		user.getLogin());

	TaskDTO task = (TaskDTO) msg.getObject();

	taskService.createTask(task);

	MessageResponseManager.generateResponseText(factory,
		msg.getJMSReplyTo(), "exito_creacion_tarea");
    }

    // ==============================
    // Métodos auxiliares
    // ==============================

    /**
     * Informa la usuario sobre un error que ocurrió al procesar un mensaje.
     * 
     * @param msg
     *            mensaje que produjo el error
     * 
     */
    private void sendInfoError(Message msg, String info, ErrorSeverity severity) {
	try {
	    MessageResponseManager.generateResponse(factory,
		    msg.getJMSReplyTo(), info, severity);
	}

	catch (Exception excep) {
	    Log.error("%sHa ocurrido un error al intentar informar "
		    + "al usuario de un error que se produjo al "
		    + "procesar su petición.", LOG_HEADER);
	    Log.error(excep);
	}
    }

    /**
     * Comprueba que las credenciales del usuario que envía el mensaje sean
     * válidas.
     * 
     * @param credentials
     *            usuario y contraseña encriptados en el mensaje
     * 
     * @return los datos del usuario si es válido (existe, la contraseña es
     *         correcta y no está deshabilitado), null en caso contrario.
     * 
     * @throws BusinessException
     * 
     */
    private UserDTO validateCredentials(String credentials)
	    throws BusinessException {

	try {
	    String datos = Encryptor.decrypt(credentials);

	    String[] infoUser = datos.split(" - ");

	    if (infoUser.length == 2) {
		String login = infoUser[0];
		String password = infoUser[1];

		return userService.findLoggableUser(login, password);
	    }

	    else {
		Log.error("%sFaltan indicar las credenciales del usuario.",
			LOG_HEADER);

		throw new BusinessException("En el mensaje no se ha "
			+ "indicado el usuario y la contraseña.",
			"error_login__credenciales_no_indicadas");
	    }
	}

	catch (DecryptionFailedException dfe) {
	    Log.error("%sHa ocurrido un error al validar las "
		    + "credenciales del usuario", LOG_HEADER);

	    throw new RuntimeException("Las credenciales del "
		    + "usuario no son válidas.", dfe);
	}
    }

}