package uo.sdi.rest.impl;

import java.util.List;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Response;

import alb.util.log.Log;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.dto.CategoryDTO;
import uo.sdi.dto.TaskDTO;
import uo.sdi.infrastructure.Services;
import uo.sdi.rest.TaskServiceRest;
import uo.sdi.rest.requests.FinishTaskRequest;
import uo.sdi.rest.responses.ListCategoriesResponse;
import uo.sdi.rest.responses.ListTasksResponse;
import uo.sdi.rest.util.ResponseManager;

/**
 * No está muy claro qué código de error está asociado a un error de lógica, así
 * que en este proyecto se usará el código 400 --> BAD REQUEST<br/>
 * <br/>
 * Después de revisar varias páginas web, vimos que se recomienda el uso de ese
 * código si se explica en el body la causa del error.<br/>
 * <br/>
 * <a href="http://www.restapitutorial.com/httpstatuscodes.html">Página con
 * códigos de errror y su significado</a>
 * 
 */
public class TaskServiceRestImpl implements TaskServiceRest {

    @Override
    public Response findCategoriesByUserId(Long user_id) {
	try {
	    // (1) Buscar las categorías
	    List<CategoryDTO> categorias = Services.getServicesFactory()
		    .getTaskService().findCategoriesByUserId(user_id);

	    // (2) Darle formato al resultado de la búsqueda
	    ListCategoriesResponse categs = new ListCategoriesResponse();
	    categs.setCategories(categorias);

	    // (3) Generar la respuesta
	    Response response = ResponseManager.generateResponse(categs,
		    Response.Status.ACCEPTED);

	    // (4) Devolver la respuesta
	    return response;
	}

	catch (BusinessException bs) {
	    throw processsBusinessException(bs, "No se han podido listar las "
		    + "categorías del usuario con id [" + user_id + "]");
	}

	catch (Exception ex) {
	    throw processException(ex, "Ha ocurrido un error al listar las "
		    + "categorías del usuario con user_id = [" + user_id + "]",
		    "rest_error_grave__listado_categorias");
	}
    }

    @Override
    public Response findNotFinishedTasksSortedFromOldestToMostRecent(
	    Long user_id, Long category_id) {

	try {
	    // (1) Buscar las tareas
	    List<TaskDTO> tareas = Services
		    .getServicesFactory()
		    .getTaskService()
		    .findNotFinishedTasksSorterByPlannedASC(user_id,
			    category_id);

	    // (2) Darle formato al resultado de la búsqueda
	    ListTasksResponse tasks = new ListTasksResponse();
	    tasks.setTasks(tareas);

	    // (3) Generar la respuesta
	    Response response = ResponseManager.generateResponse(tasks,
		    Response.Status.ACCEPTED);

	    // (4) Devolver la respuesta
	    return response;
	}

	catch (BusinessException bs) {
	    throw processsBusinessException(bs, "No se han podido listar las "
		    + "tareas de la categoría con id [" + category_id + "]");
	}

	catch (Exception ex) {
	    throw processException(ex, "Ha ocurrido un error al listar las "
		    + "tareas de la categoria con id [" + category_id + "]",
		    "rest_error_grave__listado_tareas");
	}
    }

    @Override
    public void markAsFinished(FinishTaskRequest infoTask) {
	try {
	    Services.getServicesFactory()
		    .getTaskService()
		    .markTaskAsFinished(infoTask.getUser_id(),
			    infoTask.getTask_id());
	}

	catch (BusinessException bs) {
	    throw processsBusinessException(bs, "No se ha podido finalizar "
		    + "la tarea con id [" + infoTask.getTask_id() + "]");
	}

	catch (Exception ex) {
	    throw processException(ex, "Ha ocurrido un error al intentar "
		    + "finalizar la tarea con id [" + infoTask.getTask_id()
		    + "]", "rest_error_grave__finalizar_tarea");
	}
    }

    @Override
    public void createNewTask(TaskDTO task) {
	try {
	    Services.getServicesFactory().getTaskService().createTask(task);
	}

	catch (BusinessException bs) {
	    throw processsBusinessException(bs,
		    "No se ha podido crear la nueva tarea.");
	}

	catch (Exception ex) {
	    throw processException(ex,
		    "Ha ocurrido un error al intentar crear una nueva tarea.",
		    "rest_error_grave__crear_tarea");
	}
    }

    // ==========================
    // Métodos auxiliares
    // ==========================

    /**
     * Transforms a businessException into an BadRequestException that contains
     * the error information in the format that the client expects.
     * 
     * @param bs
     *            exception to process
     * @param logMessage
     *            message that describe the error
     * 
     * @return result of process the error
     * 
     */
    private BadRequestException processsBusinessException(BusinessException bs,
	    String logMessage) {

	// (1) Guardar la información sobre el error
	Log.error(logMessage);
	Log.error(bs.getMessage());

	// (2) Generar la respuesta
	Response.Status codigoError = Response.Status.BAD_REQUEST;

	Response response = ResponseManager.generateError(
		bs.getClaveFicheroMensajes(), codigoError);

	return new BadRequestException(response);
    }

    /**
     * Transforms a Exception into an InternalServerErrorException that contains
     * the error information in the format that the client expects.
     * 
     * @param ex
     *            exception to process
     * @param logMessage
     *            message that describe the error
     * @param messageKey
     *            key of the message to be shown to the client
     * 
     * @return result of process the error
     * 
     */
    private InternalServerErrorException processException(Exception ex,
	    String logMessage, String messageKey) {

	// (1) Guardar la información sobre el error
	Log.error(logMessage);
	Log.error(messageKey);

	// (2) Generar la respuesta
	Response.Status codigoError = Response.Status.INTERNAL_SERVER_ERROR;

	Response response = ResponseManager.generateError(messageKey,
		codigoError);

	return new InternalServerErrorException(response);
    }

}