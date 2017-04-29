package uo.sdi.rest.impl;

import javax.servlet.ServletContext;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.specimpl.ResponseBuilderImpl;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.dto.UserDTO;
import uo.sdi.dto.UserNoPasswordDTO;
import uo.sdi.infrastructure.Services;
import uo.sdi.rest.UserServiceRest;
import uo.sdi.rest.util.ErrorResponse;
import uo.sdi.rest.util.MessagesFileReader;
import uo.sdi.util.DecryptionFailedException;
import uo.sdi.util.Encryptor;
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

	    respuesta = generateResponse(new UserNoPasswordDTO(user),
		    Response.Status.ACCEPTED);

	    return respuesta;
	}

	catch (BusinessException bs) {
	    Log.error("Las credenciales del usuario no son válidas.");
	    Log.error(bs.getMessage());

	    respuesta = generateResponse(
		    MessagesFileReader.getValueOf(bs.getClaveFicheroMensajes()),
		    Response.Status.UNAUTHORIZED);

	    throw new NotAuthorizedException(respuesta);
	}

	catch (DecryptionFailedException dfe) {
	    respuesta = generateResponse(new ErrorResponse("El usuario o la "
		    + "contraseña indicados no son válidos"),
		    Response.Status.UNAUTHORIZED);

	    throw new NotAuthorizedException(respuesta);
	}

	catch (Exception ex) {
	    Log.error("Ha ocurrido un error al validar las "
		    + "credenciales del usuario.");
	    Log.error(ex);

	    respuesta = generateResponse(new ErrorResponse(
		    "Ha ocurrido un error al intentar "
			    + "validar sus credenciales"),
		    Response.Status.INTERNAL_SERVER_ERROR);

	    throw new InternalServerErrorException(respuesta);
	}
    }

    private Response generateResponse(Object message, Response.Status status) {
	ResponseBuilder respBuilder = new ResponseBuilderImpl();

	return respBuilder.entity(message).status(status).encoding("UTF-8")
		.build();
    }

}