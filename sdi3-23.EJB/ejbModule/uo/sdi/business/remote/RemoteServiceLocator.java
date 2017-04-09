package uo.sdi.business.remote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import uo.sdi.business.AdminService;
import uo.sdi.business.TaskService;
import uo.sdi.business.UserService;
import uo.sdi.business.util.PropertiesReader;
import uo.sdi.infrastructure.ServicesFactory;
import alb.util.log.Log;

public class RemoteServiceLocator implements ServicesFactory {

    private final String JNDI_KEYS_FILE = "jndi_services.properties";

    @Override
    public AdminService getAdminService() {
	try {
	    Log.debug("Buscando en el JNDI una instancia de la interfaz "
		    + "RemoteAdminService");

	    Context ctx = new InitialContext();

	    return (AdminService) ctx.lookup(PropertiesReader.getValueOf(
		    JNDI_KEYS_FILE, "remote_admin_service"));
	}

	catch (NamingException e) {
	    Log.error("Ha ocurrido un error al buscar una intancia de la "
		    + "interfaz de servicios RemoteAdminService");

	    throw new RuntimeException("Ha ocurrido un error al buscar la "
		    + "interfaz de servicios en el registro JNDI", e);
	}
    }

    @Override
    public TaskService getTaskService() {
	try {
	    Log.debug("Buscando en el JNDI una instancia de la interfaz "
		    + "RemoteTaskService");

	    Context ctx = new InitialContext();

	    return (TaskService) ctx.lookup(PropertiesReader.getValueOf(
		    JNDI_KEYS_FILE, "remote_task_service"));
	}

	catch (NamingException e) {
	    Log.error("Ha ocurrido un error al buscar una intancia de la "
		    + "interfaz de servicios RemoteTaskService");

	    throw new RuntimeException("Ha ocurrido un error al buscar la "
		    + "interfaz de servicios en el registro JNDI", e);
	}
    }

    @Override
    public UserService getUserService() {
	try {
	    Log.debug("Buscando en el JNDI una instancia de la interfaz "
		    + "RemoteUserService");

	    Context ctx = new InitialContext();
	    return (UserService) ctx.lookup(PropertiesReader.getValueOf(
		    JNDI_KEYS_FILE, "remote_user_service"));
	}

	catch (NamingException e) {
	    Log.error("Ha ocurrido un error al buscar una intancia de la "
		    + "interfaz de servicios RemoteUserService");

	    throw new RuntimeException("Ha ocurrido un error al buscar la "
		    + "interfaz de servicios en el registro JNDI", e);
	}
    }

}