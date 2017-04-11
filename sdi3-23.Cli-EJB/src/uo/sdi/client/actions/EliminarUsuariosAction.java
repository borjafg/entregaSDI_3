package uo.sdi.client.actions;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.client.util.Input;
import uo.sdi.client.util.JndiServiceLocator;
import uo.sdi.client.util.MessageManager;
import alb.util.console.Console;
import alb.util.menu.Action;

public class EliminarUsuariosAction implements Action {

    @Override
    public void execute() throws Exception {
	try {
	    Long id = Input.pedirLong("Escribe el identificador "
		    + "del usuario que hay que eliminar");

	    Console.println("\n====================\n");
	    JndiServiceLocator.getAdminService().deepDeleteUser(id);
	    Console.println("\n====================\n");

	    Console.println(MessageManager
		    .getMessage("administrador__exito_borrar_usuario"));
	}

	catch (BusinessException be) {
	    Console.println("\n====================\n");

	    Console.println(MessageManager.getMessage(be
		    .getClaveFicheroMensajes()));
	}
    }

}