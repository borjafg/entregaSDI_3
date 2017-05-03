package uo.sdi.business.impl;

import javax.ejb.Stateless;
import javax.jms.Message;

import alb.util.log.Log;
import uo.sdi.business.LogMessagesService;

@Stateless
public class LogMessagesServiceImpl implements LogMessagesService {

    @Override
    public void log(Message msg) {
	try {

	}

	catch (Exception ex) {
	    Log.error("Ha ocurrido un error al intentar redirigir a "
		    + "la cola de error un mensaje");
	    Log.error(ex);
	}
    }

}