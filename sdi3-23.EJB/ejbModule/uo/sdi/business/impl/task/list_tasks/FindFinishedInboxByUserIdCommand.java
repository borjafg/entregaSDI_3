package uo.sdi.business.impl.task.list_tasks;

import java.util.List;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.Command;
import uo.sdi.dto.DTOadapter;
import uo.sdi.dto.TaskDTO;
import uo.sdi.persistence.TaskFinder;

public class FindFinishedInboxByUserIdCommand implements Command<List<TaskDTO>> {

    private Long userId;

    public FindFinishedInboxByUserIdCommand(Long userId) {
	this.userId = userId;
    }

    @Override
    public List<TaskDTO> execute() throws BusinessException {
	return DTOadapter.tasksToDTO(TaskFinder
		.findFinishedTasksInboxByUserId(userId));
    }

}