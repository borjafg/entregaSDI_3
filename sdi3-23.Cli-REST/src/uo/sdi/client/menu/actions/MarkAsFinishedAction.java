package uo.sdi.client.menu.actions;

import uo.sdi.client.menu.util.Input;
import uo.sdi.client.rest.RestClient;
import uo.sdi.client.rest.requests.FinishTaskRequest;
import alb.util.console.Console;
import alb.util.menu.Action;

public class MarkAsFinishedAction implements Action {

    @Override
    public void execute() throws Exception {
	Console.println();

	// =================================
	// (1) Pedir información al usuario
	// =================================

	Long task_id = Input.pedirValorLong("Id de la tarea");

	// ==============================
	// (2) Realizar la petición REST
	// ==============================

	RestClient.getTaskService().markAsFinished(
		new FinishTaskRequest(RestClient.getUser().getId(), task_id));

	Console.println();
	Console.println("Se ha finalizado la tarea");
    }

}