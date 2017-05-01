package uo.sdi.menu;

<<<<<<< HEAD
import javax.ws.rs.BadRequestException;

import uo.sdi.menu.actions.CreateNewTaskAction;
import uo.sdi.menu.actions.ListNotFinishedTasksByCategoryIdAction;
import uo.sdi.menu.actions.ListCategoriesAction;
import uo.sdi.menu.actions.MarkAsFinishedAction;
import uo.sdi.menu.util.ErrorResponseProcessor;
import uo.sdi.menu.util.Input;
import uo.sdi.rest.RestClient;
import uo.sdi.rest.exceptions.FailToAuthorizedException;
=======
import uo.sdi.rest.RestClient;
import uo.sdi.util.Input;
>>>>>>> 622bef857d0c655af153cebdba985cc0a47b63c4
import alb.util.console.Console;
import alb.util.log.Log;
import alb.util.menu.BaseMenu;

public class MainMenu extends BaseMenu {

<<<<<<< HEAD
    public MainMenu() {
	menuOptions = new Object[][] {
		{ "Menú de usuario -- REST", null },

		{ "Listar categorías", ListCategoriesAction.class },
		{ "Listar tareas pendientes de una categoría",
			ListNotFinishedTasksByCategoryIdAction.class },

		{ "Crear una nueva tarea", CreateNewTaskAction.class },
		{ "Finalizar una tarea", MarkAsFinishedAction.class } };
    }

    private void procesarOpcionesMenu() {
	int opt;

	do {
	    showMenu();
	    opt = getMenuOption();

	    try {
		processOption(opt);
	    }

	    catch (BadRequestException bre) {
		ErrorResponseProcessor.processError(bre.getResponse());
	    }

	    catch (Exception ex) {
		throw new RuntimeException(ex);
	    }
	} while (opt != 0);
    }

=======
>>>>>>> 622bef857d0c655af153cebdba985cc0a47b63c4
    @Override
    public void execute() {
	try {
	    String intentarLoguearse = "si";

	    do {
<<<<<<< HEAD
		Console.println("====================");
		Console.println();
=======
>>>>>>> 622bef857d0c655af153cebdba985cc0a47b63c4
		Console.println("Inicie sesión para continuar\n");

		String login = Input.pedirString("Nombre de usuario");
		String password = Input.pedirString("Contraseña");

<<<<<<< HEAD
		try {
		    RestClient.doLogin(login, password);
		    RestClient.inicializeTaskService();

		    procesarOpcionesMenu();

		    break;
		}

		catch (FailToAuthorizedException ex) {
		    Console.println();
		    Console.println(ex.getMessage());
		    Console.println();

		    intentarLoguearse = Input.pedirConfirmacion("¿Desea "
			    + "volver a intentar loguearse? (si/no)");

		    Console.println();
=======
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
>>>>>>> 622bef857d0c655af153cebdba985cc0a47b63c4
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