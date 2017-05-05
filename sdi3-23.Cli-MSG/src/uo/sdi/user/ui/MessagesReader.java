package uo.sdi.user.ui;

import java.io.InputStream;
import java.util.Properties;

import alb.util.log.Log;

public class MessagesReader {

    private final static String MESSAGES_FILE = "messages.properties";

    public static String getValue(String key) {
	try {
	    InputStream inputStream = MessagesReader.class.getClassLoader()
		    .getResourceAsStream(MESSAGES_FILE);

	    Properties properties = new Properties();
	    properties.load(inputStream);

	    return properties.getProperty(key);
	}

	catch (Exception ex) {
	    Log.error("No se ha encontrado el fichero de mensajes "
		    + "de nombre [%s]", MESSAGES_FILE);

	    throw new RuntimeException("No se ha encontrado el fichero "
		    + "que contiene los mensajes de la aplicacion.");
	}
    }

}