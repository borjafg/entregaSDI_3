package uo.sdi.util;

import alb.util.console.Console;

public class Input {

    /**
     * Pide al usuario que escriba un texto. Mientras no escriba un texto no
     * vacio (distinto de "") no se le permitira continuar.
     * 
     * @param mensaje
     *            texto que se mostrara al usuario
     * 
     * @return texto leido
     * 
     */
    public static String pedirString(String mensaje) {
	String texto = "";

	do {
	    texto = Console.readString(mensaje);
	}

	while (texto == null || "".equals(texto));

	return texto;
    }

    /**
     * Pide al usuario que escriba un texto. Mientras no escriba 'si' o 'no' el
     * usuario no podra continuar.
     * 
     * @param mensaje
     *            texto que se mostrara al usuario
     * 
     * @return opcion elegida por el usuario (si / no)
     * 
     */
    public static String pedirConfirmacion(String pregunta) {
	String texto = "";

	do {
	    texto = Console.readString(pregunta);
	}

	while (!"si".equals(texto) && !"no".equals(texto));

	return texto;
    }

}