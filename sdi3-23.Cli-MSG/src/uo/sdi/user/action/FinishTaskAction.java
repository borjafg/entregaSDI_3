package uo.sdi.user.action;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.TextMessage;

import uo.sdi.user.MainUser;
import uo.sdi.user.ui.ErrorProcessor;
import uo.sdi.user.ui.Input;
import uo.sdi.user.ui.MessagesReader;
import alb.util.console.Console;
import alb.util.menu.Action;

public class FinishTaskAction implements Action {

    private final String OPERATION = "finalizar_tarea";

    @Override
    public void execute() throws Exception {
	// ===========================
	// (1) Pedir datos al usuario
	// ===========================

	Long idTarea = Input.pedirValorLong("Id de la tarea");

	// ========================
	// (2) Preparar el mensaje
	// ========================

	MapMessage msg = MainUser.producer.createMapMessage(MainUser.getUser()
		.getLogin() + " - " + MainUser.getPassword());

	msg.setStringProperty("operacion", OPERATION);
	msg.setLong("id_tarea", idTarea);

	// ======================
	// (3) Enviar el mensaje
	// ======================

	Message messag = MainUser.producer.sendMessage(msg);

	// ==========================
	// (4) Procesar la respuesta
	// ==========================

	if (ErrorProcessor.errorOcurred(messag)) {
	    ErrorProcessor.processError(messag);
	}

	else {
	    TextMessage message = (TextMessage) messag;

	    Console.println(MessagesReader.getValue(message.getText()));
	}
    }

}