package uo.sdi.business.impl.task.task;

import uo.sdi.business.exception.BusinessCheck;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.Command;
import uo.sdi.business.impl.util.TaskCheck;
import uo.sdi.dto.TaskDTO;
import uo.sdi.model.Category;
import uo.sdi.model.Task;
import uo.sdi.model.User;
import uo.sdi.persistence.CategoryFinder;
import uo.sdi.persistence.UserFinder;
import uo.sdi.persistence.util.Jpa;

public class CreateTaskCommand implements Command<Task> {

    private TaskDTO taskDTO;

    public CreateTaskCommand(TaskDTO taskDTO) {
	this.taskDTO = taskDTO;
    }

    @Override
    public Task execute() throws BusinessException {
	User user = UserFinder.findById(taskDTO.getUserId());

	TaskCheck.isUserValid(user);

	TaskCheck.titleIsNotNull(taskDTO);
	TaskCheck.titleIsNotEmpty(taskDTO);

	Task task = new Task(taskDTO.getTitle(), user);

	task.setComments(taskDTO.getComments());

	if (taskDTO.getCategory() != null
		&& taskDTO.getCategory().getId() != null) {

	    Category categ = CategoryFinder.findById(taskDTO.getCategory()
		    .getId());

	    TaskCheck.categoryExists(categ);
	    BusinessCheck.isTrue(categ.getUser().getId().equals(user.getId()),
		    "La categoría a la que se intenta asignar la tarea no "
			    + "pertenece a este usuario",
		    "error_creacion_edicion_tarea__categoria_no_existe");

	    task.setCategory(categ);
	}

	task.setPlanned(taskDTO.getPlanned());
	task.setFinished(null);

	Jpa.getManager().persist(task);

	return task;
    }

}