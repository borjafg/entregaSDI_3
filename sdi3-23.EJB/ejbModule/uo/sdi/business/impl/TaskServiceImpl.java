package uo.sdi.business.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.task.category.CreateCategoryCommand;
import uo.sdi.business.impl.task.category.DeleteCategoryCommand;
import uo.sdi.business.impl.task.category.DuplicateCategoryCommand;
import uo.sdi.business.impl.task.category.FindCategoriesByUserIdCommand;
import uo.sdi.business.impl.task.category.FindCategoryByIdCommand;
import uo.sdi.business.impl.task.category.UpdateCategoryCommand;
import uo.sdi.business.impl.task.list_tasks.FindFinishedInboxByUserIdCommand;
import uo.sdi.business.impl.task.list_tasks.FindFinishedTasksByCategoryIdCommand;
import uo.sdi.business.impl.task.list_tasks.FindInboxTaskByUserIdCommand;
import uo.sdi.business.impl.task.list_tasks.FindNotFinishedTasksSortedByPlannedASC;
import uo.sdi.business.impl.task.list_tasks.FindTasksByCategoryIdCommand;
import uo.sdi.business.impl.task.list_tasks.FindTodayTasksByUserIdCommand;
import uo.sdi.business.impl.task.list_tasks.FindUnfinishedTasksByCategoryIdCommand;
import uo.sdi.business.impl.task.list_tasks.FindWeekTasksByUserIdCommand;
import uo.sdi.business.impl.task.task.CreateTaskCommand;
import uo.sdi.business.impl.task.task.DeleteTaskCommand;
import uo.sdi.business.impl.task.task.FindTaskByIdCommand;
import uo.sdi.business.impl.task.task.MarkTaskAsFinishedCommand;
import uo.sdi.business.impl.task.task.UpdateTaskCommand;
import uo.sdi.business.local.LocalTaskService;
import uo.sdi.business.remote.RemoteTaskService;
import uo.sdi.dto.CategoryDTO;
import uo.sdi.dto.TaskDTO;

@Stateless
@WebService(name = "TaskService_SOAP")
public class TaskServiceImpl implements LocalTaskService, RemoteTaskService {

    // ======================================
    // Métodos relacionados con categorías
    // ======================================

    @Override
    public void createCategory(final CategoryDTO categ)
	    throws BusinessException {

	new CreateCategoryCommand(categ).execute();
    }

    @Override
    public void duplicateCategory(final Long categId) throws BusinessException {
	new DuplicateCategoryCommand(categId).execute();
    }

    @Override
    public void updateCategory(final CategoryDTO categ)
	    throws BusinessException {

	new UpdateCategoryCommand(categ).execute();
    }

    @Override
    public void deleteCategory(final Long catId, final Long idUser)
	    throws BusinessException {

	new DeleteCategoryCommand(catId, idUser).execute();
    }

    @Override
    public CategoryDTO findCategoryById(final Long id) throws BusinessException {
	return new FindCategoryByIdCommand(id).execute();
    }

    @Override
    public List<CategoryDTO> findCategoriesByUserId(final Long userId)
	    throws BusinessException {

	return new FindCategoriesByUserIdCommand(userId).execute();
    }

    // =====================================
    // Métodos relacionados con tareas
    // =====================================

    @Override
    public void createTask(final TaskDTO task) throws BusinessException {
	new CreateTaskCommand(task).execute();
    }

    @Override
    public void deleteTask(final Long taskId) throws BusinessException {
	new DeleteTaskCommand(taskId).execute();
    }

    @Override
    public void markTaskAsFinished(final Long user_id, final Long task_id)
	    throws BusinessException {

	new MarkTaskAsFinishedCommand(user_id, task_id).execute();
    }

    @Override
    public void updateTask(final TaskDTO task) throws BusinessException {
	new UpdateTaskCommand(task).execute();
    }

    @Override
    public TaskDTO findTaskById(final Long taskId) throws BusinessException {
	return new FindTaskByIdCommand(taskId).execute();
    }

    // =====================================
    // Métodos para listar tareas
    // =====================================

    @Override
    public List<TaskDTO> findInboxTasksByUserId(final Long userId)
	    throws BusinessException {

	return new FindInboxTaskByUserIdCommand(userId).execute();
    }

    @Override
    public List<TaskDTO> findWeekTasksByUserId(final Long userId)
	    throws BusinessException {

	return new FindWeekTasksByUserIdCommand(userId).execute();
    }

    @Override
    public List<TaskDTO> findTodayTasksByUserId(final Long userId)
	    throws BusinessException {

	return new FindTodayTasksByUserIdCommand(userId).execute();
    }

    @Override
    public List<TaskDTO> findTasksByCategoryId(final Long categId)
	    throws BusinessException {

	return new FindTasksByCategoryIdCommand(categId).execute();
    }

    @Override
    public List<TaskDTO> findUnfinishedTasksByCategoryId(final Long categId)
	    throws BusinessException {

	return new FindUnfinishedTasksByCategoryIdCommand(categId).execute();
    }

    @Override
    public List<TaskDTO> findFinishedTasksByCategoryId(final Long categId)
	    throws BusinessException {

	return new FindFinishedTasksByCategoryIdCommand(categId).execute();
    }

    @Override
    public List<TaskDTO> findFinishedInboxTasksByUserId(final Long userId)
	    throws BusinessException {

	return new FindFinishedInboxByUserIdCommand(userId).execute();
    }

    // ==============================
    // Métodos de la tercera entrega
    // ==============================

    @Override
    public List<TaskDTO> findNotFinishedTasksSorterByPlannedASC(Long user_id,
	    Long category_id) throws BusinessException {

	return new FindNotFinishedTasksSortedByPlannedASC(user_id, category_id)
		.execute();
    }

}