package uo.sdi.user;

import uo.sdi.dto.UserNoPasswordDTO;
import uo.sdi.user.action.CreateNewTaskAction;
import uo.sdi.user.action.FinishTaskAction;
import uo.sdi.user.action.ListTasksForTodayAndDelayedTasksAction;
import uo.sdi.user.credentials.UserCredentialsValidator;
import uo.sdi.user.credentials.exceptions.FailToAuthorizedException;
import uo.sdi.user.jms.Producer;
import uo.sdi.user.ui.Input;
import alb.util.console.Console;
import alb.util.log.Log;
import alb.util.menu.BaseMenu;

public class MainUser extends BaseMenu {

    public static Producer producer;

    private static UserNoPasswordDTO user;
    private static String password;

    public MainUser() {
	menuOptions = new Object[][] {
		{ "Menú de usuario -- MSG", null },

		{ "Listar tareas para hoy y tareas retrasadas",
			ListTasksForTodayAndDelayedTasksAction.class },
		{ "Finalizar una tarea", FinishTaskAction.class },
		{ "Crear una nueva tarea", CreateNewTaskAction.class } };
    }

    // =================================
    // Ejecución del menú
    // =================================

    @Override
    public void execute() {
	doLogin();

	// Al terminar liberar recursos
	producer.close();

	producer = null;
	user = null;
	password = null;

	// Mostrar mensaje
	Log.debug("=== Fin de la ejecución ===");
    }

    private void doLogin() {
	String intentarLoguearse = "si";

	do {
	    Console.println("====================");
	    Console.println();
	    Console.println("Inicie sesión para continuar\n");

	    String login = Input.pedirString("Nombre de usuario");
	    String password = Input.pedirString("Contraseña");

	    try {
		producer = new Producer();
		producer.initialize();

		MainUser.user = new UserCredentialsValidator().doLogin(login,
			password);
		MainUser.password = password;

		run();

		return;
	    }

	    // Si las credenciales no son válidas
	    catch (FailToAuthorizedException ex) {
		intentarLoguearse = Input.pedirConfirmacion("¿Desea "
			+ "volver a intentar loguearse? (si/no)");

		Console.println();
	    }

	    // Si ocurre un error
	    catch (Exception ex) {
		processException(ex);
		return;
	    }
	} while (intentarLoguearse.equals("si"));
    }

    private void run() {
	int opt;

	do {
	    showMenu();
	    opt = getMenuOption();

	    try {
		processOption(opt);
	    }

	    catch (Exception ex) {
		processException(ex);
		return;
	    }
	} while (opt != 0);
    }

    private void processException(Exception ex) {
	Log.debug("Ha ocurrido un error durante la "
		+ "ejecución del menú principal");
	Log.error(ex);

	Console.println("Ha ocurrido un error no recuperable al procesar "
		+ "su petición. La aplicación no puede continuar.");
    }

    // =================================
    // Getters y Setters
    // =================================

    public static UserNoPasswordDTO getUser() {
	return user;
    }

    public static String getPassword() {
	return password;
    }

}