package uo.sdi.business.impl.task.list_tasks;

import java.util.List;

import uo.sdi.business.exception.BusinessCheck;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.command.Command;
import uo.sdi.dto.DTOadapter;
import uo.sdi.dto.TaskDTO;
import uo.sdi.model.Category;
import uo.sdi.persistence.CategoryFinder;

public class FindTasksByCategoryIdCommand implements Command<List<TaskDTO>> {

    private Long categId;

    public FindTasksByCategoryIdCommand(Long categId) {
	this.categId = categId;
    }

    @Override
    public List<TaskDTO> execute() throws BusinessException {
	Category categ = CategoryFinder.findById(categId);

	BusinessCheck.isNotNull(categ, "No se pueden listar las tareas de esta"
		+ " categoría porque no existe.",
		"error_listado_tareas_categoria__no_existe_categoria");

	return DTOadapter.tasksToDTO(categ.getTasks());
    }

}