package uo.sdi.client;

import alb.util.log.Log;
import alb.util.log.LogLevel;
import uo.sdi.client.menu.MainMenu;

public class Main {

    public static void main(String[] args) {
	Log.setLogLevel(LogLevel.DEBUG);

	new MainMenu().execute();
    }

}