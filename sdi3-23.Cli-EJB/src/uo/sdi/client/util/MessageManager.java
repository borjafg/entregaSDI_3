package uo.sdi.client.util;

import java.io.InputStream;
import java.util.Properties;

import alb.util.log.Log;

public class MessageManager {
	private final static String messagesFile = "messages.properties";
	
	public static String getMessage(String key){
		try {
		    InputStream inputStream = MessageManager.class.getClassLoader()
			    .getResourceAsStream(messagesFile );
		    
		    Properties properties = new Properties();
		    properties.load(inputStream);
		    
		    return properties.getProperty(key);
		}

		catch (Exception fnfe) {
		    Log.error("No se ha encontrado el fichero de mensajes de "
			    + "nombre [%s]", messagesFile );

		    throw new RuntimeException("No se ha encontrado el fichero que "
			    + "contiene los mensajes de la aplicacion");
		}
	}
	
	


}
