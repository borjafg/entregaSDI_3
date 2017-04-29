package uo.sdi.menu;

import uo.sdi.rest.RestClient;
import uo.sdi.util.Input;
import alb.util.console.Console;
import alb.util.log.Log;
import alb.util.menu.BaseMenu;

public class MainMenu extends BaseMenu {

    @Override
    public void execute() {
	try {
	    String intentarLoguearse = "si";

	    do {
		Console.println("Inicie sesión para continuar\n");

		String login = Input.pedirString("Nombre de usuario");
		String password = Input.pedirString("Contraseña");

		boolean exito = RestClient.doLogin(login, password);

		if (exito) {
		    // super.execute();
		    break;
		}

		else {
		    Console.println("El usuario o contraseña "
			    + "indicados no son correctos");

		    intentarLoguearse = Input.pedirConfirmacion("¿Desea "
			    + "volver a intentar loguearse? (si/no)");
		}
	    } while (intentarLoguearse.equals("si"));
	}

	catch (Exception ex) {
	    Log.debug("Ha ocurrido un error durante la "
		    + "ejecución del menú principal");
	    Log.error(ex);

	    Console.println("Ha ocurrido un error no recuperable al procesar "
		    + "su petición. La aplicación no puede continuar.");
	}
    }

}