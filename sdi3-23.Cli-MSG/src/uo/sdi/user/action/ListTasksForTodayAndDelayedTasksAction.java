package uo.sdi.user.action;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import uo.sdi.dto.ListTasksDTO;
import uo.sdi.user.MainUser;
import uo.sdi.user.ui.ErrorProcessor;
import uo.sdi.user.ui.Printer;
import alb.util.menu.Action;

public class ListTasksForTodayAndDelayedTasksAction implements Action {

    private final String OPERATION = "hoy_atrasadas";

    @Override
    public void execute() throws Exception {
	// ========================
	// (1) Preparar el mensaje
	// ========================

	MapMessage msg = MainUser.producer.createMapMessage(MainUser.getUser()
		.getLogin() + " - " + MainUser.getPassword());

	msg.setStringProperty("operacion", OPERATION);

	// ======================
	// (2) Enviar el mensaje
	// ======================

	Message messag = MainUser.producer.sendMessage(msg);

	// ==========================
	// (3) Procesar la respuesta
	// ==========================

	if (ErrorProcessor.errorOcurred(messag)) {
	    ErrorProcessor.processError(messag);
	}

	else {
	    ObjectMessage objMess = (ObjectMessage) messag;

	    ListTasksDTO tasks = (ListTasksDTO) objMess.getObject();

	    Printer.printTasksList(tasks.getTasks(), "Lista "
		    + "de tareas para hoy y retrasadas");
	}
    }

}