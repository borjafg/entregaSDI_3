package uo.sdi.client.actions;

import uo.sdi.business.AdminService;
import uo.sdi.client.util.JndiServiceLocator;
import uo.sdi.client.util.Printer;
import alb.util.console.Console;
import alb.util.log.Log;
import alb.util.menu.Action;

public class ListarUsuariosAction implements Action {

    @Override
    public void execute() throws Exception {
	try {
	    Console.println("\n====================\n");
	    AdminService service = JndiServiceLocator.getAdminService();
	    Console.println("\n====================\n");

	    Printer.printUsers(service.findAllUsersInfo());
	}

	catch (RuntimeException ex) {
	    Log.error("Ha ocurrido un error a la hora de listar los usuarios");
	    Log.error(ex);

	    Console.println("\n====================\n");
	    
	    throw ex;
	}
    }

}