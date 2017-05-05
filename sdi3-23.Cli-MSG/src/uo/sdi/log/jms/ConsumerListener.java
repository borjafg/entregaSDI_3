package uo.sdi.log.jms;

import java.util.Date;

import javax.jms.BytesMessage;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;

import uo.sdi.log.util.DateUtil;
import alb.util.console.Console;
import alb.util.log.Log;

public class ConsumerListener implements MessageListener {

    @Override
    public void onMessage(Message msg) {
	try {
	    StringBuilder sb = new StringBuilder();

	    sb.append("\n==> Datos del mensaje:");
	    sb.append("\n\t==> Id: ").append(msg.getJMSMessageID());

	    sb.append("\n\t==> Operation: ").append(
		    msg.getStringProperty("operacion"));
	    sb.append("\n\t==> Tipo de mensaje: ").append(getType(msg));

	    Date fecha = new Date(msg.getJMSTimestamp());
	    sb.append("\n\t==> Date: ").append(DateUtil.format(fecha));

	    Console.println(sb.toString());
	}

	catch (Exception ex) {
	    Log.error("Ha ocurrido un error al procesar un mensaje.");
	    Log.error(ex);
	}
    }

    private String getType(Message msg) {
	if (msg instanceof MapMessage) {
	    return "MapMessage";
	}

	else if (msg instanceof ObjectMessage) {
	    return "ObjectMessage";
	}

	else if (msg instanceof TextMessage) {
	    return "TextMessage";
	}

	else if (msg instanceof BytesMessage) {
	    return "Bytesmessage";
	}

	else if (msg instanceof StreamMessage) {
	    return "StreamMessage";
	}

	else {
	    return null;
	}
    }

}