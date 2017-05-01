package uo.sdi.rest;

<<<<<<< HEAD
=======
import java.util.List;

>>>>>>> 622bef857d0c655af153cebdba985cc0a47b63c4
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
<<<<<<< HEAD
import javax.ws.rs.core.Response;

import uo.sdi.dto.TaskDTO;
import uo.sdi.rest.requests.FinishTaskRequest;
=======

import uo.sdi.dto.CategoryDTO;
import uo.sdi.dto.TaskDTO;

// https://dennis-xlc.gitbooks.io/restful-java-with-jax-rs-2-0-2rd-edition/en/part1/chapter7/exception_handling.html
// http://prideparrot.com/blog/archive/2012/3/creating_a_rest_service_using_asp_net_web_api  <-- Rest API C#
>>>>>>> 622bef857d0c655af153cebdba985cc0a47b63c4

@Path("/task")
public interface TaskServiceRest {

    @GET
    @Path("/list_categories/{user_id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
<<<<<<< HEAD
    public Response findCategoriesByUserId(@PathParam("user_id") Long user_id);
=======
    public List<CategoryDTO> findCategoriesByUserId(
	    @PathParam("user_id") Long user_id);
>>>>>>> 622bef857d0c655af153cebdba985cc0a47b63c4

    @GET
    @Path("/list_finished_tasks/{user_id}-{category_id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
<<<<<<< HEAD
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
=======
    public List<TaskDTO> findNotFinishedTasksSortedFromOldestToMostRecent(
	    @PathParam("user_id") Long user_id,
	    @PathParam("category_id") Long category_id);

    @PUT
    @Path("/finish_task")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void markAsFinished(Long task_id, Long user_id);
>>>>>>> 622bef857d0c655af153cebdba985cc0a47b63c4

    @POST
    @Path("/create_task")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
<<<<<<< HEAD
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
=======
>>>>>>> 622bef857d0c655af153cebdba985cc0a47b63c4
    public void createNewTask(TaskDTO task);

}