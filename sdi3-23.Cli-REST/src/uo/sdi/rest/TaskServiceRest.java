package uo.sdi.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import uo.sdi.dto.TaskDTO;
import uo.sdi.rest.requests.FinishTaskRequest;

@Path("/task")
public interface TaskServiceRest {

    @GET
    @Path("/list_categories/{user_id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response findCategoriesByUserId(@PathParam("user_id") Long user_id);

    @GET
    @Path("/list_finished_tasks/{user_id}-{category_id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response findNotFinishedTasksSortedFromOldestToMostRecent(
	    @PathParam("user_id") Long user_id,
	    @PathParam("category_id") Long category_id);

    /*
     * En caso de que ocurra un error, se genera una respuesta con la
     * información del error en uno de estos dos formatos (JSON, XML).
     * 
     * En caso de exito sólo se devuelve 200 Ok.
     * 
     * Es por eso que estos dos métodos tienen la notación @Produces a pesar de
     * no tener tipo de retorno.
     */

    @PUT
    @Path("/finish_task")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void markAsFinished(FinishTaskRequest infoTask);

    @POST
    @Path("/create_task")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void createNewTask(TaskDTO task);

}