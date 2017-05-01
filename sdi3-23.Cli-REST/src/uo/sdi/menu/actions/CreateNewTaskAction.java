package uo.sdi.menu.actions;

<<<<<<< HEAD
import java.util.Date;

import uo.sdi.dto.TaskDTO;
import uo.sdi.menu.util.Input;
import uo.sdi.rest.RestClient;
import alb.util.console.Console;
=======
>>>>>>> 622bef857d0c655af153cebdba985cc0a47b63c4
import alb.util.menu.Action;

public class CreateNewTaskAction implements Action {

    @Override
    public void execute() throws Exception {
<<<<<<< HEAD
	Console.println();

	// ==================================
	// (1) Pedir información de la tarea
	// ==================================

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

	task.setUserId(RestClient.getUser().getId());

	// ==============================
	// (3) Realizar la petición REST
	// ==============================

	RestClient.getTaskService().createNewTask(task);

	Console.println("\nSe ha creado con éxito la tarea");
=======
	// TODO Auto-generated method stub

>>>>>>> 622bef857d0c655af153cebdba985cc0a47b63c4
    }

}