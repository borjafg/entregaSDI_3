package uo.sdi.user.jms;

import java.io.Serializable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import uo.sdi.user.credentials.Encryptor;
import uo.sdi.util.Jndi;
import alb.util.log.Log;

public class Producer {

    private static final String JMS_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private static final String CANAL_COMANDOS_QUEUE = "jms/queue/canal_comandos_sdi_23";

    private Connection con;
    private Session session;

    private MessageProducer sender;
    private MessageConsumer consumer;

    // ==================================
    // Inicializar y finalizar productor
    // ==================================

    public void initialize() {
	try {
	    ConnectionFactory factory = (ConnectionFactory) Jndi
		    .find(JMS_CONNECTION_FACTORY);

	    Destination queue = (Destination) Jndi.find(CANAL_COMANDOS_QUEUE);

	    con = factory.createConnection("sdi", "password");
	    session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    sender = session.createProducer(queue);

	    con.start();
	}

	catch (JMSException ex) {
	    throw new RuntimeException("Ha ocurrido un error al crear "
		    + "un productor.", ex);
	}
    }

    public void close() {
	try {
	    con.close();
	}

	catch (JMSException je) {
	    Log.error("Ha ocurrido un error al cerrar la "
		    + "conexión de un productor.", je);
	}
    }

    // =============================
    // Creación de mensajes
    // =============================

    private void setCredentials(Message msg, String credentials)
	    throws JMSException {

	Log.debug("Se ha creado un nuevo mensaje. Añadiendo "
		+ "crendenciales del usuario al mensaje");

	if (credentials != null && credentials != "") {
	    String datosCifrados = Encryptor.encrypt(credentials);

	    msg.setStringProperty("credentials", datosCifrados);

	    Log.debug("Se han añadido las credenciales del usuario "
		    + "al mensaje ---> " + datosCifrados);
	}
    }

    public MapMessage createMapMessage(String credentials) {
	try {
	    MapMessage msg = session.createMapMessage();
	    setCredentials(msg, credentials);

	    return msg;
	}

	catch (JMSException je) {
	    throw new RuntimeException("Ha ocurrido un error al "
		    + "crear un nuevo \"MapMessage\".", je);
	}
    }

    public ObjectMessage createObjectMessage(String credentials,
	    Serializable obj) {

	try {
	    ObjectMessage msg = session.createObjectMessage(obj);
	    setCredentials(msg, credentials);

	    return msg;
	}

	catch (JMSException je) {
	    throw new RuntimeException("Ha ocurrido un error al "
		    + "crear un nuevo \"ObjectMessage\".", je);
	}
    }

    // =============================
    // Envío de mensajes
    // =============================

    public Message sendMessage(Message msg) {
	try {
	    Destination respQueue = session.createTemporaryQueue();
	    msg.setJMSReplyTo(respQueue);

	    sender.send(msg);

	    consumer = session.createConsumer(respQueue);
	    Message mesag = consumer.receive();
	    consumer.close();

	    return mesag;
	}

	catch (JMSException je) {
	    throw new RuntimeException("Ha ocurrido un error al intentar "
		    + "enviar un mensaje", je);
	}
    }

}