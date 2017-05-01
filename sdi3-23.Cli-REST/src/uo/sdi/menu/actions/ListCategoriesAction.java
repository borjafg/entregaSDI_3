package uo.sdi.menu.actions;

<<<<<<< HEAD
import javax.ws.rs.core.Response;

import uo.sdi.menu.util.ErrorResponseProcessor;
import uo.sdi.menu.util.Printer;
import uo.sdi.rest.RestClient;
import uo.sdi.rest.responses.ListCategoriesResponse;
=======
>>>>>>> 622bef857d0c655af153cebdba985cc0a47b63c4
import alb.util.menu.Action;

public class ListCategoriesAction implements Action {

    @Override
    public void execute() throws Exception {
<<<<<<< HEAD
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
		respuesta.readEntity(ListCategoriesResponse.class)
			.getCategories(), "Lista de categorías:");
    }
=======
	// TODO Auto-generated method stub

    }

>>>>>>> 622bef857d0c655af153cebdba985cc0a47b63c4
}