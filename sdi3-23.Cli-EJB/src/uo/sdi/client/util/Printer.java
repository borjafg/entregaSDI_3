package uo.sdi.client.util;

import java.util.List;

import uo.sdi.dto.UserInfoDTO;
import alb.util.console.Console;

public class Printer {

    private static final String TAB = "    ";

    public static void printUsers(List<UserInfoDTO> users) {
	Console.println("Listado de usuarios:\n");

	for (UserInfoDTO user : users) {
	    String mensaje = TAB + "id: %d - login: %s - email: %s - "
		    + "estado: %s - admin: %s\n";

	    String isAdmin;

	    if (user.getIsAdmin()) {
		isAdmin = "si";
	    }

	    else {
		isAdmin = "no";
	    }

	    mensaje += TAB + TAB;
	    mensaje += "--> Número de tareas planeadas (sin finalizar): %d\n";

	    mensaje += TAB + TAB;
	    mensaje += "--> Número de tareas no planeadas (sin finalizar): %d\n";

	    mensaje += TAB + TAB;
	    mensaje += "--> Número de tareas retrasadas y finalizadas: %d\n";

	    mensaje += TAB + TAB;
	    mensaje += "--> Número de tareas finalizadas: %d\n\n";

	    Console.printf(mensaje, user.getId(), user.getLogin(),
		    user.getEmail(), user.getStatus().name().toLowerCase(),
		    isAdmin, user.getNumPlannedTasks(),
		    user.getNumNotPlannedTasks(),
		    user.getNumFinishedDelayedTasks(),
		    user.getNumFinishedTasks());
	}

	Console.println("--- Fin del listado ---");
    }

}