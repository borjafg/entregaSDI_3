package uo.sdi.client.actions;

import uo.sdi.client.util.Printer;
import uo.sdi.ws_admin.AdminServiceImplService;
import alb.util.log.Log;
import alb.util.menu.Action;

public class ListarUsuariosAction implements Action {

    @Override
    public void execute() throws Exception {
	try {
	    AdminServiceImplService service = new AdminServiceImplService();

	    Printer.printUsers(service.getAdminServiceSOAPPort()
		    .findAllUsersInfo());
	}

	catch (RuntimeException ex) {
	    Log.error("Ha ocurrido un error a la hora de listar los usuarios");
	    Log.error(ex);

	    throw ex;
	}
    }

}