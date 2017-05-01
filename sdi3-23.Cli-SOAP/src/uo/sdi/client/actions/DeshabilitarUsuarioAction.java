package uo.sdi.client.actions;

import uo.sdi.client.util.Input;
import uo.sdi.client.util.MessageManager;
import uo.sdi.ws_admin.AdminServiceImplService;
import uo.sdi.ws_admin.BusinessException_Exception;
import alb.util.console.Console;
import alb.util.menu.Action;

public class DeshabilitarUsuarioAction implements Action {

    @Override
    public void execute() throws Exception {
	Long id;

	try {
	    id = Input.pedirLong("Escribe el identificador del"
		    + "usuario que hay que deshabilitar");

	    new AdminServiceImplService().getAdminServiceSOAPPort()
		    .disableUser(id);

	    Console.println(MessageManager
		    .getMessage("administrador__exito_cambiar_estado"));
	}

	catch (BusinessException_Exception be) {
	    Console.println(MessageManager.getMessage(be.getFaultInfo()
		    .getClaveFicheroMensajes()));
	}
    }

}