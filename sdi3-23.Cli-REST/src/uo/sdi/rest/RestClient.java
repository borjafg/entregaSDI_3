package uo.sdi.rest;

import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import uo.sdi.dto.UserNoPasswordDTO;
import uo.sdi.rest.exceptions.FailToAuthorizedException;
import uo.sdi.rest.responses.ErrorResponse;
import uo.sdi.rest.util.Authenticator;
import uo.sdi.rest.util.Encryptor;
import alb.util.log.Log;

public class RestClient {

    // ==================
    // Servicio de login
    // ==================

    private static UserNoPasswordDTO user;
    private static String password;

    public static UserNoPasswordDTO getUser() {
	return user;
    }

    public static boolean doLogin(String login, String password) {
	UserServiceRest serv = new ResteasyClientBuilder().build()
		.target("http://localhost:8280/sdi3-23.WEB/rest/")
		.proxy(UserServiceRest.class);

	String credenciales = login + " - " + password;
	String datosEncriptados = Encryptor.encrypt(credenciales);

	Log.debug("Se han encriptado las credenciales del usuario --> "
		+ datosEncriptados);

	Response respuesta = serv.doLogin(datosEncriptados);

	if (respuesta.getStatusInfo().getStatusCode() == Response.Status.ACCEPTED
		.getStatusCode()) {

	    RestClient.user = respuesta.readEntity(UserNoPasswordDTO.class);
	    RestClient.password = password;

	    Log.info("Usuario logueado con la cuenta '" + user.getLogin() + "'");

	    return true;
	}

	else {
	    ErrorResponse error = respuesta.readEntity(ErrorResponse.class);

	    Log.error("El usuario no ha podido hacer login con éxito");
	    Log.error("Causa --> " + error.getCausaError());

	    if (respuesta.getStatusInfo().getStatusCode() == Response.Status.INTERNAL_SERVER_ERROR
		    .getStatusCode()) {

		throw new RuntimeException(error.getCausaError());
	    }

	    throw new FailToAuthorizedException(error.getCausaError());
	}
    }

    // ================================
    // Servicio de tareas y categorías
    // ================================

    private static TaskServiceRest client;

    public static TaskServiceRest getTaskService() {
	return client;
    }

    public static void inicializeTaskService() {
	client = new ResteasyClientBuilder().build()
		.register(new Authenticator(user.getLogin(), password))
		.target("http://localhost:8280/sdi3-23.WEB/rest/")
		.proxy(TaskServiceRest.class);
    }

}