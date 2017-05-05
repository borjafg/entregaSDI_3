package uo.sdi.log;

import uo.sdi.log.jms.Consumer;
import alb.util.console.Console;
import alb.util.menu.Action;

public class MainLog implements Action {

    @Override
    public void execute() throws Exception {
	Consumer consumer = new Consumer();
	consumer.initialize();

	Console.readString("--- Pulsa <Enter> para cerrar la aplicación ---\n");

	consumer.close();

	Console.println("=== Fin de la ejecución ===");
    }

}