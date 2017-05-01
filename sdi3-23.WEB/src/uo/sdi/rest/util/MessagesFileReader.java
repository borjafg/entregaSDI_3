package uo.sdi.rest.util;

import java.io.InputStream;
import java.util.Properties;

import alb.util.log.Log;

public class MessagesFileReader {

    private static final String MESSAGES_FILE = "/messages_es.properties";

    /**
     * Returns the value associated with the key key. It only returns messages
     * in Spanish.
     * 
     */
    public static String getValueOf(String key) {
	try {
	    InputStream inputStream = MessagesFileReader.class.getClassLoader()
		    .getResourceAsStream(MESSAGES_FILE);

	    Properties properties = new Properties();
	    properties.load(inputStream);

	    return properties.getProperty(key);
	}

	catch (Exception e) {
	    Log.error("Ha ocurrido un error al leer del fichero de mensajes");
	    Log.error(e);

	    throw new RuntimeException("Ha ocurrido un error al procesar la "
		    + "solicitud del usuario");
	}
    }

}