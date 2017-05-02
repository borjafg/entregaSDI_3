package uo.sdi.business.integration;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

import uo.sdi.business.integration.credentials.DecryptionFailedException;
import alb.util.log.Log;

@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/canal_comandos_sdi_23") })
public class MessageListenerBean implements MessageListener {

    private final String header = "MDB ---> ";

    @Override
    public void onMessage(Message msg) {
	Log.debug(header + "Se ha empezado a procesar un mensaje");

    }

//    private boolean validateCredentials(String credentials) {
//	try {
//
//	}
//
//	catch (DecryptionFailedException dfe) {
//	    return false;
//	}
//    }

}