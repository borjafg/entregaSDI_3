package uo.sdi.rest;

import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import uo.sdi.dto.UserNoPasswordDTO;
import uo.sdi.util.Encryptor;
import alb.util.console.Console;
import alb.util.log.Log;

public class RestClient {

    // ==================
    // Servicio de login
    // ==================

    private static UserNoPasswordDTO user;
    private static String password;

    public static boolean doLogin(String login, String password) {
	UserServiceRest serv = new ResteasyClientBuilder().build()
		.target("http://localhost:8280/sdi3-23.WEB/rest/")
		.proxy(UserServiceRest.class);

	String credenciales = login + " - " + password;

	Response respuesta = serv.doLogin(Encryptor.encrypt(credenciales));

	if (respuesta.getStatusInfo().getStatusCode() == Response.Status.ACCEPTED
		.getStatusCode()) {

	    RestClient.user = respuesta.readEntity(UserNoPasswordDTO.class);
	    RestClient.password = password;

	    Log.info("Usuario logueado con la cuenta '" + user.getLogin() + "'");

	    return true;
	}

	else {
	    ErrorResponse error = respuesta.readEntity(ErrorResponse.class);

	    Log.error("El usuario no se ha podido loguear con exito");
	    Log.error(error.getCausaError());

	    if (respuesta.getStatusInfo().getStatusCode() == Response.Status.INTERNAL_SERVER_ERROR
		    .getStatusCode()) {

		throw new RuntimeException(error.getCausaError());
	    }

	    Console.println(error.getCausaError());

	    return false;
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