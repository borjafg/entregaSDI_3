package uo.sdi.business.impl.task.task;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.command.Command;
import uo.sdi.dto.DTOadapter;
import uo.sdi.dto.TaskDTO;
import uo.sdi.persistence.TaskFinder;

public class FindTaskByIdCommand implements Command<TaskDTO> {

    private Long taskId;

    public FindTaskByIdCommand(Long taskId) {
	this.taskId = taskId;
    }

    @Override
    public TaskDTO execute() throws BusinessException {
	return DTOadapter.taskToDTO(TaskFinder.findById(taskId));
    }

}