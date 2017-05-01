package uo.sdi.dto;

import java.util.ArrayList;
import java.util.List;

import uo.sdi.dto.types.UserStatusDTO;
import uo.sdi.model.Category;
import uo.sdi.model.Task;
import uo.sdi.model.User;
import uo.sdi.model.types.UserStatus;

/**
 * Transforma objetos del modelo de dominio en objetos DTO, que son con los que
 * trabaja la capa de presentación.
 * 
 */
public class DTOadapter {

    // ===================================
    // Conversor de tareas
    // ===================================

    public static TaskDTO taskToDTO(Task tarea) {
	TaskDTO task = new TaskDTO(tarea.getId(), tarea.getCreated(), tarea
		.getUser().getId());

	if (tarea.getCategory() != null) {
	    task.setCategory(categoryToDTO(tarea.getCategory()));
	}

	else {
	    task.setCategory(null);
	}

	task.setTitle(tarea.getTitle());
	task.setComments(tarea.getComments());
	task.setPlanned(tarea.getPlanned());
	task.setFinished(tarea.getFinished());

	return task;
    }

    public static List<TaskDTO> tasksToDTO(Iterable<Task> tasks) {
	List<TaskDTO> lista = new ArrayList<TaskDTO>();

	for (Task tarea : tasks) {
	    lista.add(taskToDTO(tarea));
	}

	return lista;
    }

    // ===================================
    // Conversor de categorías
    // ===================================

    public static CategoryDTO categoryToDTO(Category category) {
	CategoryDTO categ = new CategoryDTO(category.getId(),
		category.getCreated(), category.getUser().getId());

	categ.setName(category.getName());

	return categ;
    }

    public static List<CategoryDTO> categoriesToDTO(
	    Iterable<Category> categories) {

	List<CategoryDTO> lista = new ArrayList<CategoryDTO>();

	for (Category category : categories) {
	    lista.add(categoryToDTO(category));
	}

	return lista;
    }

    // ===================================
    // Conversor de usuarios
    // ===================================

    public static UserDTO userToDTO(User user) {
	return new UserDTO(user);
    }

    public static List<UserDTO> usersToDTO(Iterable<User> users) {
	List<UserDTO> lista = new ArrayList<UserDTO>();

	for (User usuario : users) {
	    lista.add(new UserDTO(usuario));
	}

	return lista;
    }

    public static UserStatusDTO parseStatusToDTO(UserStatus status) {
	if (status == UserStatus.ENABLED) {
	    return UserStatusDTO.ENABLED;
	}

	else if (status == UserStatus.DISABLED) {
	    return UserStatusDTO.DISABLED;
	}

	else {
	    throw new IllegalArgumentException("Ese estado "
		    + "de una cuenta de usuario no es válido");
	}
    }

}