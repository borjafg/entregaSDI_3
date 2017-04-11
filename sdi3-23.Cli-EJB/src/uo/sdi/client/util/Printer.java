package uo.sdi.client.util;

import java.util.List;

import uo.sdi.dto.UserInfoDTO;
import alb.util.console.Console;

public class Printer {

	public static void printUsers(List<UserInfoDTO> users) {

		for (UserInfoDTO user : users) {

			String mensaje = "\tid: " + user.getId() + " - login: "
					+ user.getLogin() + " - email: " + user.getEmail()
					+ " - estado: " + user.getStatus().name().toLowerCase()
					+ " - admin: ";
			if (user.getIsAdmin()) {
				mensaje += "si \n";
			} else {
				mensaje += "no \n";
			}
			mensaje += "\t\t--> tareas planeadas:" + user.getNumPlannedTasks()
					+ "\n";
			mensaje += "\t\t--> tareas finalizadas:"
					+ user.getNumFinishedTasks() + "\n";
			mensaje += "\t\t--> tareas retrasadas y finalizadas:"
					+ user.getNumFinishedDelayedTasks() + "\n";
			mensaje += "\t\t--> tareas no planeadas:"
					+ user.getNumNotPlannedTasks() + "\n";

			Console.print(mensaje + "\n");
		}
	}

}
