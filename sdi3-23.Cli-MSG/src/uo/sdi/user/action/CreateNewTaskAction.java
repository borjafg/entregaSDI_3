package uo.sdi.user.action;

import java.util.Date;

import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import uo.sdi.dto.TaskDTO;
import uo.sdi.user.MainUser;
import uo.sdi.user.ui.ErrorProcessor;
import uo.sdi.user.ui.Input;
import uo.sdi.user.ui.MessagesReader;
import alb.util.console.Console;
import alb.util.menu.Action;

public class CreateNewTaskAction implements Action {

    private final String OPERATION = "nueva_tarea";

    @Override
    public void execute() throws Exception {
	// ===========================
	// (1) Pedir datos al usuario
	// ===========================

	String title = Input.pedirString("Título de la tarea");
	String comments = Input.pedirString_CadenaVacia("Comentarios (o "
		+ "ninguno si no se quieren añadir comentarios)");

	Date planned = Input.pedirFechaHoyOPosterior_null("Fecha "
		+ "planeada (o \"ninguna\" si no tiene. Debe ser "
		+ "la de hoy o posterior)");

	Long categoryId = Input.pedirValorLong_null("Id de la categoria "
		+ "(o \"ninguno\" para no asignarla a una categoría)");

	// ===========================================
	// (2) Crear la tarea con los datos indicados
	// ===========================================

	TaskDTO task = new TaskDTO();

	task.setTitle(title);
	task.setComments(comments);
	task.setPlanned(planned);

	if (categoryId != null) {
	    task.setCategoryId(categoryId);
	}

	task.setUserId(MainUser.getUser().getId());

	// ========================
	// (3) Preparar el mensaje
	// ========================

	String credentials = MainUser.getUser().getLogin() + " - "
		+ MainUser.getPassword();

	ObjectMessage msg = MainUser.producer.createObjectMessage(credentials,
		task);

	msg.setStringProperty("operacion", OPERATION);

	// ======================
	// (4) Enviar el mensaje
	// ======================

	Message messag = MainUser.producer.sendMessage(msg);

	// ==========================
	// (5) Procesar la respuesta
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