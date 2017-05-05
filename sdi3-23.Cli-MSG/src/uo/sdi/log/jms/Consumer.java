package uo.sdi.log.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import alb.util.log.Log;
import uo.sdi.util.Jndi;

public class Consumer {

    private static final String JMS_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private static final String CANAL_LOG_QUEUE = "jms/queue/canal_log_sdi_23";

    private Connection con;
    private Session session;
    private MessageConsumer consumer;

    public void initialize() {
	try {
	    ConnectionFactory factory = (ConnectionFactory) Jndi
		    .find(JMS_CONNECTION_FACTORY);

	    Destination queue = (Destination) Jndi.find(CANAL_LOG_QUEUE);

	    con = factory.createConnection("sdi", "password");
	    session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);

	    consumer = session.createConsumer(queue);
	    consumer.setMessageListener(new ConsumerListener());

	    con.start();
	}

	catch (JMSException je) {
	    Log.error("Ha ocurrido un error al inicializar el consumidor.");

	    throw new RuntimeException(je);
	}
    }

    public void close() {
	if (con != null) {
	    try {
		con.close();
	    }

	    catch (JMSException je) {
		Log.error("Ha ocurrido un error al intentar "
			+ "cerrar el consumidor.");
	    }
	}
    }

}