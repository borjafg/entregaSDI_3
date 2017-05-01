package uo.sdi.rest.impl;

import javax.servlet.ServletContext;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.dto.UserDTO;
import uo.sdi.dto.UserNoPasswordDTO;
import uo.sdi.infrastructure.Services;
import uo.sdi.rest.UserServiceRest;
import uo.sdi.rest.exceptions.DecryptionFailedException;
import uo.sdi.rest.util.Encryptor;
import uo.sdi.rest.util.ResponseManager;
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

	    respuesta = ResponseManager.generateResponse(new UserNoPasswordDTO(
		    user), Response.Status.ACCEPTED);

	    return respuesta;
	}

	catch (BusinessException bs) {
	    Log.error("Las credenciales del usuario no son válidas.");
	    Log.error("Causa --> " + bs.getMessage());

	    respuesta = ResponseManager.generateError(
		    bs.getClaveFicheroMensajes(), Response.Status.UNAUTHORIZED);

	    throw new NotAuthorizedException(respuesta);
	}

	catch (DecryptionFailedException dfe) {
	    respuesta = ResponseManager.generateError(
		    "rest_error__validacion_credenciales",
		    Response.Status.UNAUTHORIZED);

	    throw new NotAuthorizedException(respuesta);
	}

	catch (Exception ex) {
	    Log.error("Ha ocurrido un error al validar las "
		    + "credenciales del usuario.");
	    Log.error(ex);

	    respuesta = ResponseManager.generateError(
		    "rest_error_grave__validacion_credenciales",
		    Response.Status.INTERNAL_SERVER_ERROR);

	    throw new InternalServerErrorException(respuesta);
	}
    }

}