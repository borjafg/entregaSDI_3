package uo.sdi.menu.util;

import javax.ws.rs.core.Response;

import alb.util.console.Console;
import uo.sdi.rest.responses.ErrorResponse;

public class ErrorResponseProcessor {

    public static boolean anErrorOcurred(Response response) {
	if (response.getStatus() != Response.Status.ACCEPTED.getStatusCode()) {
	    return true;
	}

	return false;
    }

    public static void processError(Response response) {
	ErrorResponse error = response.readEntity(ErrorResponse.class);

	Console.println();
	Console.println(error.getCausaError());
    }

}