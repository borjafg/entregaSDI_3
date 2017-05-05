package uo.sdi.client.rest.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import alb.util.log.Log;

/**
 * Clase que cifra las claves que se envían al servidor. En un proyecto real
 * habría que acordar con el servidor la clave utilizada en la comunicación,
 * pero en este proyecto se supondra que la clave es siempre la misma.
 * 
 * Se utilizará un cifrado con un algortimo AES porque permite utilizar la misma
 * clave para encriptar y desencriptar, y nosotros podemos elegir esa clave.
 * 
 */
public class Encryptor {

    /*
     * ==> Algoritmo utilizado: AES
     * 
     * ==> Modo de cifrado (tamaño bloque): ECB
     * 
     * 1) Se divide el elemento cifrado en bloques del mismo tamaño.
     * 
     * 2) Se cifra cada uno de ellos por separado
     * 
     * No es seguro porque bloques con los mismos bytes generan la misma salida.
     * La ventaja es que no requiere de un vector de inicialización.
     * 
     * ==> Padding: PKCS5
     */
    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";

    private static final String KEY = "%190_CLAVE_SDI_?";

    public static String encrypt(String text) {
	try {
	    // =======================================================
	    // (1) Preparamos la clave con la que cifraremos el texto
	    // =======================================================

	    byte[] bytesClave = KEY.getBytes("UTF-8");

	    // Cifrado con clave de 128 bits = 16 bytes
	    Key key = new SecretKeySpec(bytesClave, 0, 16, "AES");

	    // ===================================
	    // (2) Obtenemos una instancia de la
	    // clase que realizará el cifrado AES
	    // ===================================

	    Cipher aes = Cipher.getInstance(ALGORITHM);

	    // ======================================================
	    // (3) Se le indica la clave que se usará para encriptar
	    // ======================================================

	    aes.init(Cipher.ENCRYPT_MODE, key);

	    // ===========================================
	    // (4) Se le pasa el texto que hay que cifrar
	    // ===========================================

	    byte[] datosEncriptados = aes.doFinal(text.getBytes("UTF-8"));

	    return DatatypeConverter.printBase64Binary(datosEncriptados);
	}

	catch (Exception ex) {
	    Log.error("Ha ocurrido un error al cifrar el texto indicado");
	    Log.error(ex);

	    throw new RuntimeException(
		    "Ha ocurrido un error al cifrar el texto indicado");
	}
    }

}