package uo.sdi.business.impl.task.list_tasks;

import java.util.List;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.Command;
import uo.sdi.dto.DTOadapter;
import uo.sdi.dto.TaskDTO;
import uo.sdi.persistence.TaskFinder;

public class FindUnfinishedTasksByCategoryIdCommand implements Command<List<TaskDTO>> {

    private Long categId;

    public FindUnfinishedTasksByCategoryIdCommand(Long categId) {
	this.categId = categId;
    }

    @Override
    public List<TaskDTO> execute() throws BusinessException {
	return DTOadapter.tasksToDTO(TaskFinder
		.findUnfinishedTasksByCategoryId(categId));
    }

}