package uo.sdi.rest.impl;

import java.util.List;

import javax.ws.rs.InternalServerErrorException;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.dto.CategoryDTO;
import uo.sdi.dto.TaskDTO;
import uo.sdi.infrastructure.Services;
import uo.sdi.rest.TaskServiceRest;

public class TaskServiceRestImpl implements TaskServiceRest {

    @Override
    public List<CategoryDTO> findCategoriesByUserId(Long user_id) {
	try {
	    return Services.getServicesFactory().getTaskService()
		    .findCategoriesByUserId(user_id);
	}

	catch (BusinessException bs) {
	    throw new InternalServerErrorException("De momento hacer así");
	}
    }

    @Override
    public List<TaskDTO> findNotFinishedTasksSortedFromOldestToMostRecent(
	    Long user_id, Long category_id) {

	try {
	    return Services
		    .getServicesFactory()
		    .getTaskService()
		    .findNotFinishedTasksSorterByPlannedASC(user_id,
			    category_id);
	}

	catch (BusinessException bs) {
	    throw new InternalServerErrorException("De momento hacer así");
	}
    }

    @Override
    public void markAsFinished(Long task_id, Long user_id) {
	try {
	    Services.getServicesFactory().getTaskService()
		    .markTaskAsFinished(user_id, task_id);
	}

	catch (BusinessException bs) {
	    throw new InternalServerErrorException("De momento hacer así");
	}
    }

    @Override
    public void createNewTask(TaskDTO task) {
	try {
	    Services.getServicesFactory().getTaskService().createTask(task);
	}

	catch (BusinessException bs) {
	    throw new InternalServerErrorException("De momento hacer así");
	}
    }

}