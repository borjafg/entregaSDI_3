package uo.sdi.client;

import uo.sdi.client.actions.DeshabilitarUsuarioAction;
import uo.sdi.client.actions.EliminarUsuariosAction;
import uo.sdi.client.actions.ListarUsuariosAction;
import alb.util.log.Log;
import alb.util.log.LogLevel;
import alb.util.menu.BaseMenu;

public class MainMenu extends BaseMenu {

    public MainMenu() {
	menuOptions = new Object[][] {

	{ "Menú administrador EJB", null },
		{ "Listar usuarios", ListarUsuariosAction.class },
		{ "Eliminar usuarios", EliminarUsuariosAction.class },
		{ "Deshabilitar usuarios", DeshabilitarUsuarioAction.class } };
    }

    public static void main(String[] args) {
	Log.setLogLevel(LogLevel.DEBUG);

	new MainMenu().execute();
    }

}