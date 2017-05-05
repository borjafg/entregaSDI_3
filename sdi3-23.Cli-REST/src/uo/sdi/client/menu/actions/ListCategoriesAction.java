package uo.sdi.client.menu.actions;

import javax.ws.rs.core.Response;

import uo.sdi.client.dto.ListCategoriesDTO;
import uo.sdi.client.menu.util.ErrorResponseProcessor;
import uo.sdi.client.menu.util.Printer;
import uo.sdi.client.rest.RestClient;
import alb.util.menu.Action;

public class ListCategoriesAction implements Action {

    @Override
    public void execute() throws Exception {
	// ==============================
	// (1) Realizar la petición REST
	// ==============================

	Response respuesta = RestClient.getTaskService()
		.findCategoriesByUserId(RestClient.getUser().getId());

	// ===========================================
	// (2) Procesar la respuesta
	//
	// El objeto devuelto puede contener la lista
	// o la información de un error (si ocurrió)
	// ===========================================

	if (ErrorResponseProcessor.anErrorOcurred(respuesta)) {
	    ErrorResponseProcessor.processError(respuesta);
	    return;
	}

	Printer.printCategoriesList(
		respuesta.readEntity(ListCategoriesDTO.class)
			.getCategories(), "Lista de categorías:");
    }
}