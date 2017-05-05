package uo.sdi;

import uo.sdi.log.MainLog;
import uo.sdi.user.MainUser;
import alb.util.log.Log;
import alb.util.log.LogLevel;
import alb.util.menu.BaseMenu;

public class MainMenu extends BaseMenu {

    public static void main(String[] args) {
	Log.setLogLevel(LogLevel.DEBUG);

	new MainMenu().execute();
    }

    public MainMenu() {
	menuOptions = new Object[][] { { "Menú de usuario -- MSG", null },
		{ "Revisar los logs", MainLog.class },
		{ "Hacer login como usuario", MainUser.class } };
    }

    @Override
    public void execute() {
	int opt;

	do {
	    showMenu();
	    opt = getMenuOption();

	    try {
		processOption(opt);

		if (opt > 0 && opt < menuOptions.length) {
		    return;
		}
	    }

	    catch (Exception ex) {
		throw new RuntimeException(ex);
	    }
	} while (opt != 0);
    }

}