package uo.sdi.client.menu.util;

import javax.ws.rs.core.Response;

import alb.util.console.Console;
import uo.sdi.client.dto.ErrorDTO;

public class ErrorResponseProcessor {

    public static boolean anErrorOcurred(Response response) {
	if (response.getStatus() != Response.Status.ACCEPTED.getStatusCode()) {
	    return true;
	}

	return false;
    }

    public static void processError(Response response) {
	ErrorDTO error = response.readEntity(ErrorDTO.class);

	Console.println();
	Console.println(error.getCausaError());
    }

}