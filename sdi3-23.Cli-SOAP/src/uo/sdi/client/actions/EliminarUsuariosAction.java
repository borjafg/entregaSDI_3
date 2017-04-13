package uo.sdi.client.actions;

import uo.sdi.client.util.Input;
import uo.sdi.client.util.MessageManager;
import uo.sdi.ws_admin.AdminServiceImplService;
import uo.sdi.ws_admin.BusinessException_Exception;
import alb.util.console.Console;
import alb.util.menu.Action;

public class EliminarUsuariosAction implements Action {

    @Override
    public void execute() throws Exception {
	try {
	    Long id = Input.pedirLong("Escribe el identificador "
		    + "del usuario que hay que eliminar");

	    Console.println("\n====================\n");
	    new AdminServiceImplService().getAdminServiceSOAPPort()
		    .deepDeleteUser(id);
	    Console.println("\n====================\n");

	    Console.println(MessageManager
		    .getMessage("administrador__exito_borrar_usuario"));
	}

	catch (BusinessException_Exception be) {
	    Console.println("\n====================\n");

	    Console.println(MessageManager.getMessage(be.getFaultInfo()
		    .getClaveFicheroMensajes()));
	}
    }

}