package uo.sdi.client.actions;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.client.util.Input;
import uo.sdi.client.util.JndiServiceLocator;
import uo.sdi.client.util.MessageManager;
import alb.util.console.Console;
import alb.util.menu.Action;

public class DeshabilitarUsuarioAction implements Action {

	@Override
	public void execute() throws Exception {
		Long id;
		try {
			id = Input.pedirLong("Inserte identificador del usuario"
					+ " que desea deshabilitar");
			Console.println("\n====================\n");
			JndiServiceLocator.getAdminService().disableUser(id);
			Console.println("\n====================\n");
			Console.println(MessageManager
					.getMessage("administrador__exito_cambiar_estado"));

		} catch (BusinessException be) {
			Console.println(MessageManager.getMessage(be
					.getClaveFicheroMensajes()));
		}

	}
}
