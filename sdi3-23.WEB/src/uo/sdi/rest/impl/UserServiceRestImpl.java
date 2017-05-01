package uo.sdi.rest.impl;

import javax.servlet.ServletContext;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
<<<<<<< HEAD
=======
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.specimpl.ResponseBuilderImpl;
>>>>>>> 622bef857d0c655af153cebdba985cc0a47b63c4

import uo.sdi.business.exception.BusinessException;
import uo.sdi.dto.UserDTO;
import uo.sdi.dto.UserNoPasswordDTO;
import uo.sdi.infrastructure.Services;
import uo.sdi.rest.UserServiceRest;
<<<<<<< HEAD
import uo.sdi.rest.exceptions.DecryptionFailedException;
import uo.sdi.rest.util.Encryptor;
import uo.sdi.rest.util.ResponseManager;
=======
import uo.sdi.rest.util.ErrorResponse;
import uo.sdi.rest.util.MessagesFileReader;
import uo.sdi.util.DecryptionFailedException;
import uo.sdi.util.Encryptor;
>>>>>>> 622bef857d0c655af153cebdba985cc0a47b63c4
import alb.util.log.Log;

public class UserServiceRestImpl implements UserServiceRest {

    @Context
    private ServletContext context;

    @Override
    public Response doLogin(String credentials) {
	Response respuesta;

	try {
	    // ==================================
	    // (1) Desencriptar las credenciales
	    // ==================================

	    Log.debug("Se está empezando a desencriptar el texto que "
		    + "contiene las credenciales del usuario.");

	    String text = Encryptor.decrypt(credentials);

	    String login = text.split(" - ")[0];
	    String password = text.split(" - ")[1];

	    // =============================
	    // (2) Comprobar si son válidas
	    // =============================

	    Log.info("Se está intentando iniciar sesión con la "
		    + "cuenta del usuario '" + login + "'");

	    UserDTO user = Services.getServicesFactory().getUserService()
		    .findLoggableUser(login, password);

	    // =======================================================
	    // (3) Si lo son, entonces devolver los datos del usuario
	    // =======================================================

<<<<<<< HEAD
	    respuesta = ResponseManager.generateResponse(new UserNoPasswordDTO(
		    user), Response.Status.ACCEPTED);
=======
	    respuesta = generateResponse(new UserNoPasswordDTO(user),
		    Response.Status.ACCEPTED);
>>>>>>> 622bef857d0c655af153cebdba985cc0a47b63c4

	    return respuesta;
	}

	catch (BusinessException bs) {
	    Log.error("Las credenciales del usuario no son válidas.");
<<<<<<< HEAD
	    Log.error("Causa --> " + bs.getMessage());

	    respuesta = ResponseManager.generateError(
		    bs.getClaveFicheroMensajes(), Response.Status.UNAUTHORIZED);
=======
	    Log.error(bs.getMessage());

	    respuesta = generateResponse(
		    MessagesFileReader.getValueOf(bs.getClaveFicheroMensajes()),
		    Response.Status.UNAUTHORIZED);
>>>>>>> 622bef857d0c655af153cebdba985cc0a47b63c4

	    throw new NotAuthorizedException(respuesta);
	}

	catch (DecryptionFailedException dfe) {
<<<<<<< HEAD
	    respuesta = ResponseManager.generateError(
		    "rest_error__validacion_credenciales",
=======
	    respuesta = generateResponse(new ErrorResponse("El usuario o la "
		    + "contraseña indicados no son válidos"),
>>>>>>> 622bef857d0c655af153cebdba985cc0a47b63c4
		    Response.Status.UNAUTHORIZED);

	    throw new NotAuthorizedException(respuesta);
	}

	catch (Exception ex) {
	    Log.error("Ha ocurrido un error al validar las "
		    + "credenciales del usuario.");
	    Log.error(ex);

<<<<<<< HEAD
	    respuesta = ResponseManager.generateError(
		    "rest_error_grave__validacion_credenciales",
=======
	    respuesta = generateResponse(new ErrorResponse(
		    "Ha ocurrido un error al intentar "
			    + "validar sus credenciales"),
>>>>>>> 622bef857d0c655af153cebdba985cc0a47b63c4
		    Response.Status.INTERNAL_SERVER_ERROR);

	    throw new InternalServerErrorException(respuesta);
	}
    }

<<<<<<< HEAD
=======
    private Response generateResponse(Object message, Response.Status status) {
	ResponseBuilder respBuilder = new ResponseBuilderImpl();

	return respBuilder.entity(message).status(status).encoding("UTF-8")
		.build();
    }

>>>>>>> 622bef857d0c655af153cebdba985cc0a47b63c4
}