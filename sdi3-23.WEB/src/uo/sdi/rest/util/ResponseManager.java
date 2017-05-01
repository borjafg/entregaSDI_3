package uo.sdi.rest.util;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.specimpl.ResponseBuilderImpl;

import uo.sdi.rest.responses.ErrorResponse;

public class ResponseManager {

    private ResponseManager() {

    }

    public static Response generateResponse(Object message,
	    Response.Status status) {

	ResponseBuilder respBuilder = new ResponseBuilderImpl();

	return respBuilder.entity(message).status(status).encoding("UTF-8")
		.build();
    }

    public static Response generateError(String messageKey,
	    Response.Status errorCode) {

	// (1) Preparamos el mensaje de error
	ErrorResponse error = new ErrorResponse(
		MessagesFileReader.getValueOf(messageKey));

	// (2) Generamos la respuesta y la devolvemos
	return generateResponse(error, errorCode);
    }

}