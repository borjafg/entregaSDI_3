package uo.sdi.business.impl;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import uo.sdi.business.LogMessagesService;
import alb.util.log.Log;

@Stateless
public class LogMessagesServiceImpl implements LogMessagesService {

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory factory;

    @Resource(mappedName = "java:/queue/canal_log_sdi_23")
    private Destination destination;

    private final String LOG_HEADER = "Bean de log de mensajes ==> ";

    @Override
    public void log(final Message msg) {
	Connection con = null;
	Session session = null;
	MessageProducer prod = null;

	try {
	    Log.debug("%sSe está redirigiendo un mensaje a la cola de log",
		    LOG_HEADER);

	    con = factory.createConnection();
	    session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    prod = session.createProducer(destination);

	    prod.send(msg);

	    Log.debug("%sSe ha redirigido un mensaje a la cola de log",
		    LOG_HEADER);
	}

	catch (Exception ex) {
	    Log.error("%sHa ocurrido un error al intentar redirigir "
		    + "a la cola de error un mensaje", LOG_HEADER);
	    Log.error(ex);
	}

	finally {
	    close(con);
	}
    }

    public void close(Connection con) {
	if (con != null) {
	    try {
		con.close();
	    }

	    catch (JMSException je) {
		Log.error("%sHa ocurrido un error al cerrar una conexión.",
			LOG_HEADER);
		Log.error(je);
	    }
	}
    }

}