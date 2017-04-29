package uo.sdi.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uo.sdi.dto.CategoryDTO;
import uo.sdi.dto.TaskDTO;

// https://dennis-xlc.gitbooks.io/restful-java-with-jax-rs-2-0-2rd-edition/en/part1/chapter7/exception_handling.html
// http://prideparrot.com/blog/archive/2012/3/creating_a_rest_service_using_asp_net_web_api  <-- Rest API C#

@Path("/task")
public interface TaskServiceRest {

    @GET
    @Path("/list_categories/{user_id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<CategoryDTO> findCategoriesByUserId(
	    @PathParam("user_id") Long user_id);

    @GET
    @Path("/list_finished_tasks/{user_id}-{category_id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<TaskDTO> findNotFinishedTasksSortedFromOldestToMostRecent(
	    @PathParam("user_id") Long user_id,
	    @PathParam("category_id") Long category_id);

    @PUT
    @Path("/finish_task")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void markAsFinished(Long task_id, Long user_id);

    @POST
    @Path("/create_task")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void createNewTask(TaskDTO task);

}