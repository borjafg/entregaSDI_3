package uo.sdi.user.ui;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;

import alb.util.console.Console;
import alb.util.log.Log;

public class ErrorProcessor {

    private final static String ERROR_KEY = "hubo_error";
    private final static String ERROR_SEVERITY_KEY = "gravedad_error";
    private final static String ERROR_CAUSE_KEY = "causa_error";

    public enum ErrorSeverity {
	FATAL, WARNING
    }

    public static boolean errorOcurred(Message messag) throws JMSException {
	return messag.getBooleanProperty(ERROR_KEY);
    }

    public static void processError(Message message) throws JMSException {
	MapMessage msg = (MapMessage) message;

	String severity = msg.getString(ERROR_SEVERITY_KEY);
	String errorCause = msg.getString(ERROR_CAUSE_KEY);

	if (ErrorSeverity.FATAL.name().toLowerCase().equals(severity)) {
	    throw new RuntimeException("Ha ocurrido un error no "
		    + "recuperable al procesar un mensaje. Causa "
		    + "del error: " + errorCause);
	}

	else if (ErrorSeverity.WARNING.name().toLowerCase().equals(severity)) {
	    Console.println(MessagesReader.getValue(errorCause));
	}

	else {
	    Log.debug("Gravedad del error --> " + severity);

	    throw new RuntimeException("La gravedad del error que "
		    + "ocurrió al procesar un mensaje no es válida");
	}
    }

}