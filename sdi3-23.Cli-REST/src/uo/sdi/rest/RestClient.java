package uo.sdi.rest;

import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import uo.sdi.dto.UserNoPasswordDTO;
<<<<<<< HEAD
import uo.sdi.rest.exceptions.FailToAuthorizedException;
import uo.sdi.rest.responses.ErrorResponse;
import uo.sdi.rest.util.Authenticator;
import uo.sdi.rest.util.Encryptor;
=======
import uo.sdi.util.Encryptor;
import alb.util.console.Console;
>>>>>>> 622bef857d0c655af153cebdba985cc0a47b63c4
import alb.util.log.Log;

public class RestClient {

    // ==================
    // Servicio de login
    // ==================

    private static UserNoPasswordDTO user;
    private static String password;

<<<<<<< HEAD
    public static UserNoPasswordDTO getUser() {
	return user;
    }

=======
>>>>>>> 622bef857d0c655af153cebdba985cc0a47b63c4
    public static boolean doLogin(String login, String password) {
	UserServiceRest serv = new ResteasyClientBuilder().build()
		.target("http://localhost:8280/sdi3-23.WEB/rest/")
		.proxy(UserServiceRest.class);

	String credenciales = login + " - " + password;
<<<<<<< HEAD
	String datosEncriptados = Encryptor.encrypt(credenciales);

	Log.debug("Se han encriptado las credenciales del usuario --> "
		+ datosEncriptados);

	Response respuesta = serv.doLogin(datosEncriptados);
=======

	Response respuesta = serv.doLogin(Encryptor.encrypt(credenciales));
>>>>>>> 622bef857d0c655af153cebdba985cc0a47b63c4

	if (respuesta.getStatusInfo().getStatusCode() == Response.Status.ACCEPTED
		.getStatusCode()) {

	    RestClient.user = respuesta.readEntity(UserNoPasswordDTO.class);
	    RestClient.password = password;

	    Log.info("Usuario logueado con la cuenta '" + user.getLogin() + "'");

	    return true;
	}

	else {
	    ErrorResponse error = respuesta.readEntity(ErrorResponse.class);

<<<<<<< HEAD
	    Log.error("El usuario no ha podido hacer login con éxito");
	    Log.error("Causa --> " + error.getCausaError());
=======
	    Log.error("El usuario no se ha podido loguear con exito");
	    Log.error(error.getCausaError());
>>>>>>> 622bef857d0c655af153cebdba985cc0a47b63c4

	    if (respuesta.getStatusInfo().getStatusCode() == Response.Status.INTERNAL_SERVER_ERROR
		    .getStatusCode()) {

		throw new RuntimeException(error.getCausaError());
	    }

<<<<<<< HEAD
	    throw new FailToAuthorizedException(error.getCausaError());
=======
	    Console.println(error.getCausaError());

	    return false;
>>>>>>> 622bef857d0c655af153cebdba985cc0a47b63c4
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