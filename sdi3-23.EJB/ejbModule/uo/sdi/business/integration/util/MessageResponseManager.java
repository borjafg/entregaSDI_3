package uo.sdi.business.integration.util;

import java.util.List;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import uo.sdi.dto.ListTasksDTO;
import uo.sdi.dto.TaskDTO;
import uo.sdi.dto.UserNoPasswordDTO;
import alb.util.log.Log;

public class MessageResponseManager {

    private final static String LOG_HEADER = "JMS response manager ---> ";

    private final static String ERROR_KEY = "hubo_error";
    private final static String ERROR_SEVERITY_KEY = "gravedad_error";
    private final static String ERROR_CAUSE_KEY = "causa_error";

    public static enum ErrorSeverity {
	FATAL, WARNING
    }

    // ======================================
    // Métodos para responder a los clientes
    // ======================================

    public static void generateResponse(ConnectionFactory factory,
	    Destination destino, String errorCause, ErrorSeverity severity)
	    throws JMSException {

	Connection conexion = null;
	Session sesion = null;
	MessageProducer productor = null;

	try {
	    conexion = factory.createConnection();
	    sesion = conexion.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    productor = sesion.createProducer(destino);

	    MapMessage messag = sesion.createMapMessage();

	    messag.setBooleanProperty(ERROR_KEY, true);
	    messag.setString(ERROR_SEVERITY_KEY, severity.name().toLowerCase());
	    messag.setObject(ERROR_CAUSE_KEY, errorCause);

	    productor.send(messag);

	    Log.debug("%sSe ha enviado al usuario la lista de tareas.",
		    LOG_HEADER);
	}

	finally {
	    close(conexion);
	}
    }

    public static void generateResponse(ConnectionFactory factory,
	    Destination destino, List<TaskDTO> tareas) throws JMSException {

	Connection conexion = null;
	Session sesion = null;
	MessageProducer productor = null;

	try {
	    conexion = factory.createConnection();
	    sesion = conexion.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    productor = sesion.createProducer(destino);

	    ListTasksDTO tasks = new ListTasksDTO();
	    tasks.setTasks(tareas);

	    ObjectMessage messag = sesion.createObjectMessage(tasks);
	    messag.setBooleanProperty(ERROR_KEY, false);

	    productor.send(messag);

	    Log.debug("%sSe ha enviado al usuario la lista de tareas.",
		    LOG_HEADER);
	}

	finally {
	    close(conexion);
	}
    }

    public static void generateResponse(ConnectionFactory factory,
	    Destination destino, UserNoPasswordDTO user) throws JMSException {

	Connection conexion = null;
	Session sesion = null;
	MessageProducer productor = null;

	try {
	    conexion = factory.createConnection();
	    sesion = conexion.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    productor = sesion.createProducer(destino);

	    ObjectMessage messag = sesion.createObjectMessage(user);
	    messag.setBooleanProperty(ERROR_KEY, false);

	    productor.send(messag);

	    Log.debug("%sSe ha enviado al usuario [%s] la información de "
		    + "su cuenta de usuario.", LOG_HEADER, user.getLogin());
	}

	finally {
	    close(conexion);
	}
    }

    public static void generateResponseText(ConnectionFactory factory,
	    Destination destino, String text) throws JMSException {

	Connection conexion = null;
	Session sesion = null;
	MessageProducer productor = null;

	try {
	    conexion = factory.createConnection();
	    sesion = conexion.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    productor = sesion.createProducer(destino);

	    TextMessage messag = sesion.createTextMessage(text);
	    messag.setBooleanProperty(ERROR_KEY, false);

	    productor.send(messag);

	    Log.debug("%sSe ha enviado al usuario el mensaje "
		    + "de texto indicado.", LOG_HEADER);
	}

	finally {
	    close(conexion);
	}
    }

    // ========================
    // Métodos auxiliares
    // ========================

    private static void close(Connection conexion) {
	try {
	    if (conexion != null) {
		conexion.close();
	    }
	}

	catch (JMSException je) {
	    Log.error("%sHa ocurrido un error al cerrar un conexión",
		    LOG_HEADER);
	    Log.error(je);
	}
    }

}