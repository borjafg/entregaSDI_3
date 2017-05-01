package uo.sdi.menu.actions;

import javax.ws.rs.core.Response;

import uo.sdi.menu.util.ErrorResponseProcessor;
import uo.sdi.menu.util.Input;
import uo.sdi.menu.util.Printer;
import uo.sdi.rest.RestClient;
import uo.sdi.rest.responses.ListTasksResponse;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ListNotFinishedTasksByCategoryIdAction implements Action {

    @Override
    public void execute() throws Exception {
	Console.println();

	// ===========================
	// (1) Pedir datos al usuario
	// ===========================

	Long category_id = Input.pedirValorLong("Id de la categoría");

	// ==============================
	// (2) Realizar la petición REST
	// ==============================

	Response respuesta = RestClient.getTaskService()
		.findNotFinishedTasksSortedFromOldestToMostRecent(
			RestClient.getUser().getId(), category_id);

	// ===========================================
	// (3) Procesar la respuesta
	//
	// El objeto devuelto puede contener la lista
	// o la información de un error (si ocurrió)
	// ===========================================

	if (ErrorResponseProcessor.anErrorOcurred(respuesta)) {
	    ErrorResponseProcessor.processError(respuesta);
	    return;
	}

	Printer.printTasksList(respuesta.readEntity(ListTasksResponse.class)
		.getTasks(), "Lista de tareas de la categoría indicada");
    }

}