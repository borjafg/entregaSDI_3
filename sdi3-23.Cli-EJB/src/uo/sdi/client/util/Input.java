package uo.sdi.client.util;

import alb.util.console.Console;

public class Input {

	public static Long pedirLong(String mensaje) {
		Long valor = null;
		do {
			try {
				valor = Console.readLong(mensaje);
			} catch (NullPointerException ne) {
				valor = null;
			}

		} while (valor == null || valor < 0);
		return valor;
	}
	
	
	

}
