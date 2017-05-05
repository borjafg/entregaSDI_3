package uo.sdi.client.menu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import alb.util.console.Console;
import alb.util.date.DateUtil;

public class Input {

    /**
     * Pide al usuario que escriba un texto. Mientras no escriba un texto no
     * vacio (distinto de "") no se le permitirá continuar.
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
     * Pide al usuario que escriba un texto. Mientras no escriba un texto no
     * vacio (distinto de "") no se le permitirá continuar.<br/>
     * <br/>
     * También se admite el texto "ninguno", en cuyo caso se devolverá "".
     * 
     * @param mensaje
     *            texto que se mostrara al usuario
     * 
     * @return valor indicado por el usuario, o "" si escribe "ninguno"
     * 
     */
    public static String pedirString_CadenaVacia(String mensaje) {
	String texto = "";

	do {
	    texto = Console.readString(mensaje);

	    if ("ninguno".equals(texto)) {
		return "";
	    }
	}

	while (texto == null || "".equals(texto));

	return texto;
    }

    /**
     * Pide al usuario que escriba un número. Mientras no escriba un texto no
     * vacio (distinto de "") no se le permitirá continuar.
     * 
     * @param mensaje
     *            texto que se mostrara al usuario
     * 
     * @return valor indicado por el usuario,
     * 
     */
    public static Long pedirValorLong(String mensaje) {
	Long numero;

	do {
	    numero = Console.readLong(mensaje);

	    if (numero != null) {
		return numero;
	    }
	}

	while (true);
    }

    /**
     * Pide al usuario que escriba un número. Mientras no escriba un número no
     * se le permitirá continuar.<br/>
     * <br/>
     * También se admite el texto
     * 
     * @param mensaje
     *            texto que se mostrara al usuario
     * 
     * @return valor indicado por el usuario, o null si escribe "ninguno"
     * 
     */
    public static Long pedirValorLong_null(String mensaje) {
	String numero = null;

	do {
	    numero = Console.readString(mensaje);

	    try {
		if ("ninguno".equals(numero)) {
		    return null;
		}

		if (numero != null && !numero.equals("")) {
		    return Long.parseLong(numero);
		}
	    }

	    catch (NumberFormatException npe) {
		numero = null;
	    }
	}

	while (true);
    }

    /**
     * Pide al usuario que escriba un texto. Mientras no escriba 'si' o 'no' el
     * usuario no podrá continuar.
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

    /**
     * Pide al usuario que escriba una fecha, que puede ser la de hoy o una
     * posterior.<br/>
     * <br/>
     * Mientras no escriba una fecha válida no podrá continuar. Se acepta
     * 'ninguna' como valor válido.<br/>
     * <br/>
     * El formato de la fecha debe ser dd/MM/yyyy
     * 
     * @param mensaje
     *            texto que se mostrara al usuario
     * 
     * @return fecha elegida por el usuario, o null si escribe "ninguna"
     * 
     */
    public static Date pedirFechaHoyOPosterior_null(String mensaje) {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	String texto = "";
	Date fecha = null;

	do {
	    texto = Console.readString(mensaje);

	    try {
		if ("ninguna".equals(texto)) {
		    return null;
		}

		if (texto != null && !texto.equals("")) {
		    fecha = sdf.parse(texto);

		    if (DateUtil.isAfter(fecha, DateUtil.today())
			    || fecha.equals(DateUtil.today())) {

			return fecha;
		    }
		}
	    }

	    catch (ParseException pe) {
		// Entrada no válida
	    }
	}

	while (true);
    }
}