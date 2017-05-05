package uo.sdi.client.menu;

import javax.ws.rs.BadRequestException;

import uo.sdi.client.menu.actions.CreateNewTaskAction;
import uo.sdi.client.menu.actions.ListCategoriesAction;
import uo.sdi.client.menu.actions.ListNotFinishedTasksByCategoryIdAction;
import uo.sdi.client.menu.actions.MarkAsFinishedAction;
import uo.sdi.client.menu.util.ErrorResponseProcessor;
import uo.sdi.client.menu.util.Input;
import uo.sdi.client.rest.RestClient;
import uo.sdi.client.rest.exceptions.FailToAuthorizedException;
import alb.util.console.Console;
import alb.util.log.Log;
import alb.util.menu.BaseMenu;

public class MainMenu extends BaseMenu {

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

    @Override
    public void execute() {
	try {
	    String intentarLoguearse = "si";

	    do {
		Console.println("====================");
		Console.println();
		Console.println("Inicie sesión para continuar\n");

		String login = Input.pedirString("Nombre de usuario");
		String password = Input.pedirString("Contraseña");

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