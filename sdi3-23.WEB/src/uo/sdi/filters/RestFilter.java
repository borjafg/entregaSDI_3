package uo.sdi.filters;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.NotAuthorizedException;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.dto.UserDTO;
import uo.sdi.infrastructure.Services;
import uo.sdi.util.Encryptor;
import alb.util.log.Log;

@WebFilter(dispatcherTypes = { DispatcherType.REQUEST }, urlPatterns = { "/rest/task/*" })
public class RestFilter implements Filter {

    @Override
    public void init(FilterConfig arg0) throws ServletException {
	// No hacer nada
    }

    @Override
    public void destroy() {
	// No hacer nada
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
	    FilterChain chain) throws IOException, ServletException {

	if (!(request instanceof HttpServletRequest)) {
	    chain.doFilter(request, response);
	    return;
	}

	HttpServletRequest requestHttp = (HttpServletRequest) request;

	try {
	    // =================================================
	    // (1) Recuperar las credenciales y desencriptarlas
	    // =================================================

	    String textoCifrado = (String) requestHttp
		    .getAttribute("Authorization");

	    String text = Encryptor.decrypt(textoCifrado);

	    String login = text.split(" - ")[0];
	    String password = text.split(" - ")[1];

	    // =========================================
	    // (2) Validar las credenciales del usuario
	    // =========================================

	    UserDTO user = Services.getServicesFactory().getUserService()
		    .findLoggableUser(login, password);

	    if (user == null) {
		throw new RuntimeException("El usuario o contraseña que "
			+ "indico el usuario [" + login + "] no son válidos");
	    }

	    chain.doFilter(request, response);
	}

	catch (RuntimeException | BusinessException ex) {
	    Log.error("Ha ocurrido un error al validar las credenciales de un"
		    + "usuario. A continuación se mostrará la traza del error.");

	    Log.error(ex);

	    throw new NotAuthorizedException("No se ha indicado las "
		    + "credenciales del usuario o las que se han "
		    + "indicado no son válidas.");
	}
    }

}