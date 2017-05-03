package uo.sdi.business.integration;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import uo.sdi.business.LogMessagesService;
import uo.sdi.business.UserService;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.integration.credentials.DecryptionFailedException;
import uo.sdi.business.integration.credentials.Encryptor;
import uo.sdi.business.local.LocalUserService;
import alb.util.log.Log;

@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/canal_comandos_sdi_23") })
public class MessageListenerBean implements MessageListener {

    private final String LOG_HEADER = "MDB ---> ";

    @EJB(beanInterface = LocalUserService.class)
    private UserService userService;

    @EJB
    private LogMessagesService logger;

    @Override
    public void onMessage(Message message) {
	Log.debug("%sSe ha empezado a procesar un mensaje.", LOG_HEADER);

	if (!messageOfExpectedType(message)) {
	    Log.error("%sSe ha recibido un mensaje que no es del tipo "
		    + "esperado. Redirigiendo a la cola de log.", LOG_HEADER);

	    logger.log(message);

	    return;
	}

	// Mensaje del tipo esperado
	MapMessage msg = (MapMessage) message;

	try {
	    boolean valid = validateCredentials(msg.getString("credentials"));

	    if (valid) {
		processMessage(msg);
	    }

	    else {
		Log.error("%sSe ha procesado un mensaje de un usuario "
			+ "sin crendenciales válidas. Redirigiendo a "
			+ "la cola de log.", LOG_HEADER);

		logger.log(msg);
	    }
	}

	catch (Exception ex) {
	    Log.error("%sHa ocurrido un error mientras se "
		    + "procesaba un mensaje.", LOG_HEADER);
	    Log.error(ex);

	    logger.log(msg);
	}
    }

    private void processMessage(MapMessage msg) throws JMSException {
	String operation = msg.getString("operacion");

	if (operation == null) {
	    Log.debug("%sEl mensaje que se está procesando no tiene indica "
		    + "qué operación se debe realizar. Redirigiendo a la cola "
		    + "de log.", LOG_HEADER);
	}

	switch (operation) {
	case "hoy_atrasadas":
	    // Visualizar tareas
	    break;

	case "finalizar_tarea":
	    // finalizar tarea
	    break;

	case "nueva_tarea":
	    // nueva tarea
	    break;

	default:
	    break;
	}

	Log.debug("%sSe ha procesado con éxito un mensaje (operación: %s)",
		LOG_HEADER, operation);
    }

    // ==============================
    // Métodos auxiliares
    // ==============================

    /**
     * Comprueba si un mensaje es de tipo MapMessage, que es el tipo de mensaje
     * que espera el MDB.
     * 
     * @param msg
     *            mensaje que se quiere validar
     * 
     * @return true si el mensaje es válido, false en caso contrario
     * 
     */
    private boolean messageOfExpectedType(Message msg) {
	return msg instanceof MapMessage;
    }

    /**
     * Comprueba que las credenciales del usuario que envía el mensaje sean
     * válidas.
     * 
     * @param credentials
     *            usuario y contraseña encriptados en el mensaje
     * 
     * @return true si el usuario es válido (existe, la contraseña es correcta y
     *         no está deshabilitado), false en caso contrario.
     * 
     */
    private boolean validateCredentials(String credentials) {
	try {
	    String datos = Encryptor.decrypt(credentials);

	    String[] infoUser = datos.split(" - ");

	    if (infoUser.length == 2) {
		String login = infoUser[0];
		String password = infoUser[1];

		userService.findLoggableUser(login, password);
		return true;
	    }

	    else {
		return false;
	    }
	}

	catch (DecryptionFailedException | BusinessException dfe) {
	    return false;
	}
    }

}