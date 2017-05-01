package uo.sdi.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.infrastructure.Services;
import uo.sdi.rest.exceptions.DecryptionFailedException;
import uo.sdi.rest.util.Encryptor;
import uo.sdi.rest.util.ResponseManager;
import alb.util.log.Log;

/**
 * Esta clase filtra todas las peticiones que se hagan recursos manejados por el
 * sevlet de rest.
 * 
 * A partir de la versión 3 se utiliza la notación @Naming para enlazar el
 * filtro con uno o más recursos en concreto. En esta versión de jax-rs no está
 * disponible esa funcionalidad. Por eso hay que utilizar la notación @PreMatching
 * y comprobar a que recurso se intenta acceder en el método de filtrado.
 * 
 * @PreMatching --> Indica que el filtro se ejecuta antes de cada petición,
 *              incluso antes de saber a qué recurso se quiere acceder.
 * 
 * @Provider --> Se usa para personalizar la ejecución de jax-rs. Indica que la
 *           clase que lleva está notación es posible que altere el
 *           <i>procesamiento normal de las peticiones</i>.
 * 
 */
@Provider
@PreMatching
public class RestFilter implements ContainerRequestFilter {

    private List<String> urlsProtegidas;

    public RestFilter() {
	urlsProtegidas = new ArrayList<String>();

	urlsProtegidas.add("task");
    }

    // =======================
    // Filtrado de peticiones
    // =======================

    @Override
    public void filter(ContainerRequestContext context) throws IOException {
	String recurso = context.getUriInfo().getPath();

	Log.debug("Filtro REST --> Accediendo al recurso " + recurso);

	if (isResourceProtected(recurso)) {
	    validarCredenciales(context);
	}
    }

    public void validarCredenciales(ContainerRequestContext context) {
	try {
	    // =================================================
	    // (1) Recuperar las credenciales y desencriptarlas
	    // =================================================

	    String textoCifrado = context.getHeaderString("Authorization");

	    Log.debug("Filtro REST --> Descifrando credenciales del usuario");
	    Log.debug("Filtro REST --> Credenciales cifradas: " + textoCifrado);

	    String text = Encryptor.decrypt(textoCifrado);

	    String login = text.split(" - ")[0];
	    String password = text.split(" - ")[1];

	    // =========================================
	    // (2) Validar las credenciales del usuario
	    // =========================================

	    Log.info("Filtro REST --> Validando credenciales de \"" + login
		    + "\"");

	    Services.getServicesFactory().getUserService()
		    .findLoggableUser(login, password);

	    Log.info("Filtro REST --> Las credenciales de \"" + login + "\" "
		    + "son válidas.");
	}

	catch (BusinessException be) {
	    logValidationError(be);

	    Response.Status codigoError = Response.Status.UNAUTHORIZED;

	    Response error = ResponseManager.generateError(
		    be.getClaveFicheroMensajes(), codigoError);

	    throw new NotAuthorizedException(error, codigoError);
	}

	catch (DecryptionFailedException re) {
	    Response.Status codigoError = Response.Status.UNAUTHORIZED;

	    Response error = ResponseManager.generateError(
		    "rest_error__validacion_credenciales", codigoError);

	    throw new NotAuthorizedException(error, codigoError);
	}

	catch (Exception ex) {
	    logValidationError(ex);

	    Response.Status codigoError = Response.Status.INTERNAL_SERVER_ERROR;

	    Response error = ResponseManager.generateError(
		    "rest_error_grave__validacion_credenciales", codigoError);

	    throw new InternalServerErrorException(error);
	}
    }

    // ===================
    // Métodos auxiliares
    // ===================

    private boolean isResourceProtected(String recurso) {
	String[] partes = recurso.split("/");

	// ----------------------------------------------------------
	// (1) Validamos que se indique el servicio que se va a usar
	// Por ejemplo, "user" o "task"
	// ----------------------------------------------------------

	if (partes.length < 2) {
	    return false;
	}

	// ---------------------------------------
	// (2) Comprobamos si está protegido o no
	// ---------------------------------------

	Log.debug("Filtro REST --> Analizando acceso a servicio REST \""
		+ partes[1] + "\"");

	if (urlsProtegidas.contains(partes[1])) {
	    Log.info("Filtro REST --> El recurso está protegido. Se procederá "
		    + "a validar las credenciales del usuario");

	    return true;
	}

	Log.debug("Filtro REST --> El recurso no está protegido. No se "
		+ "requieren las credenciales del usuario");

	return false;
    }

    private void logValidationError(Exception ex) {
	Log.error("Ha ocurrido un error al validar las credenciales de un"
		+ "usuario. A continuación se mostrará la traza del error.");

	Log.error(ex);
    }

}