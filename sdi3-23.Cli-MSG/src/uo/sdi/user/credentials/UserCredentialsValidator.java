package uo.sdi.user.credentials;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import uo.sdi.dto.UserNoPasswordDTO;
import uo.sdi.user.MainUser;
import uo.sdi.user.credentials.exceptions.FailToAuthorizedException;
import uo.sdi.user.ui.ErrorProcessor;
import alb.util.log.Log;

public class UserCredentialsValidator {

    /**
     * Comprueba que las credenciales del usuario sean válidas.
     * 
     * @param login
     *            nombre de usuario
     * @param password
     *            contraseña del usuario
     * @return
     * 
     * @throws FailToAuthorizedException
     *             Las credenciales del usuario no son válidas.
     * 
     */
    public UserNoPasswordDTO doLogin(String login, String password)
	    throws FailToAuthorizedException {

	try {
	    // (1) Mandar credenciales
	    MapMessage msg = MainUser.producer.createMapMessage(login + " - "
		    + password);

	    msg.setStringProperty("operacion", "login");

	    // (2) Al recibir la respuesta comprobar si son válidas
	    Message response = MainUser.producer.sendMessage(msg);

	    // (3) Si ocurrió un error (o no son válidas)
	    if (response.getBooleanProperty("hubo_error")) {
		Log.debug("El intento de validar las crendenciales del "
			+ "usuario ha producido un error.");

		ErrorProcessor.processError(msg);

		throw new FailToAuthorizedException();
	    }

	    // (4) Si las credenciales son válidas
	    else {
		ObjectMessage message = (ObjectMessage) response;

		UserNoPasswordDTO info = (UserNoPasswordDTO) message
			.getObject();

		Log.debug("Se han procesado con éxito las credenciales "
			+ "del usuario [%s]", info.getLogin());

		return info;
	    }
	}

	catch (JMSException je) {
	    throw new RuntimeException("Ha ocurrido un error al validar "
		    + "las credenciales del usuario.", je);
	}
    }

}