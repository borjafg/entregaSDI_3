package uo.sdi.business.impl.task.list_tasks;

import java.util.List;

import uo.sdi.business.exception.BusinessCheck;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.command.Command;
import uo.sdi.dto.DTOadapter;
import uo.sdi.dto.TaskDTO;
import uo.sdi.model.Category;
import uo.sdi.model.User;
import uo.sdi.persistence.CategoryFinder;
import uo.sdi.persistence.TaskFinder;
import uo.sdi.persistence.UserFinder;

public class FindNotFinishedTasksSortedByPlannedASC implements
	Command<List<TaskDTO>> {

    private Long user_id;
    private Long category_id;

    public FindNotFinishedTasksSortedByPlannedASC(Long user_id, Long category_id) {
	this.user_id = user_id;
	this.category_id = category_id;
    }

    @Override
    public List<TaskDTO> execute() throws BusinessException {
	Category categ = CategoryFinder.findById(category_id);
	User user = UserFinder.findById(user_id);

	BusinessCheck.isTrue(user != null, "El categoría cuyas "
		+ "tareas intentan listarse no existe",
		"error_listado_tareas_categoria__no_existe_categoria");

	BusinessCheck.isTrue(categ.getUser().equals(user), "La categoría de "
		+ "la que se intentan listar las tareas no finalizadas no "
		+ "pertenece a ese usuario",
		"error_listado_tareas_categoria__no_coincide_usuario");

	return DTOadapter
		.tasksToDTO(TaskFinder
			.findNotFinishedTasksByCategoryIdSortedByPlannedASC(category_id));
    }

}