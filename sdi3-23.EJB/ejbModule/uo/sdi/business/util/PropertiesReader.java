package uo.sdi.business.util;

import java.io.InputStream;
import java.util.Properties;

import alb.util.log.Log;

public class PropertiesReader {

    private PropertiesReader() {

    }

    public static String getValueOf(String file, String key) {
	try {
	    Properties properties = new Properties();

	    InputStream input = PropertiesReader.class.getResourceAsStream("/"
		    + file);

	    properties.load(input);

	    return properties.getProperty(key);
	}

	catch (Exception ex) {
	    Log.error("Ha ocurrido un error al buscar en el fichero [" + file
		    + "] el valor de la clave [" + key + "]");

	    Log.error(ex);

	    throw new RuntimeException("Ha ocurrido un error al leer el valor "
		    + "de un fichero de propiedades.");
	}
    }

}