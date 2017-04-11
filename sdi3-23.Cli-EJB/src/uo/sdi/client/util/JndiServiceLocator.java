package uo.sdi.client.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import uo.sdi.business.AdminService;
import alb.util.log.Log;

public class JndiServiceLocator {

	private static final String JNDI_ADMIN_SERVICE_KEY = "sdi3-23/"
			+ "sdi3-23.EJB/AdminServiceImpl!"
			+ "uo.sdi.business.remote.RemoteAdminService";

	public static AdminService getAdminService() {
		try {
			Log.debug("Buscando en el JNDI una instancia de la interfaz "
					+ "RemoteAdminService");

			Context ctx = new InitialContext();

			return (AdminService) ctx.lookup(JNDI_ADMIN_SERVICE_KEY);
		}

		catch (NamingException e) {
			Log.error("Ha ocurrido un error al buscar una intancia de la "
					+ "interfaz de servicios RemoteAdminService");

			throw new RuntimeException("Ha ocurrido un error al buscar la "
					+ "interfaz de servicios en el registro JNDI", e);
		}
	}

}