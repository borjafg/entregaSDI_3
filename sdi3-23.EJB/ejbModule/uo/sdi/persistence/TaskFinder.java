package uo.sdi.persistence;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import uo.sdi.model.Task;
import uo.sdi.persistence.util.Jpa;
import alb.util.date.DateUtil;

public class TaskFinder {

    public static Task findById(Long id) {
	return Jpa.getManager().find(Task.class, id);
    }

    public static List<Task> findAll() {
	return Jpa.getManager().createNamedQuery("Task.findAll", Task.class)
		.getResultList();
    }

    /**
     * Busca las tareas asociadas a un usuario.
     * 
     * @param userId
     *            identificador del usuario
     * 
     * @return todas las tareas del usuario sin tener en cuenta la categoria, la
     *         fecha de ejecucion planeada o la fecha de finalizacion.
     */
    public static List<Task> findByUserId(Long userId) {
	return Jpa.getManager()
		.createNamedQuery("Task.findByUserId", Task.class)
		.setParameter("userId", userId).getResultList();
    }

    /**
     * Busca una tarea por su título y el usuario al que pertenece.
     * 
     * @param title
     *            título de la tarea
     * @param userId
     *            identificador del usuario
     * 
     * @return la tarea si existe, null en caso contrario.
     */
    public static Task findByTitleAndUserId(String title, Long userId) {
	try {
	    return Jpa.getManager()
		    .createNamedQuery("Task.findByTitleAndUserId", Task.class)
		    .setParameter("title", title)
		    .setParameter("userId", userId).getSingleResult();
	}

	catch (NoResultException nre) {
	    return null;
	}
    }

    /**
     * Devuelve todas las tareas no finalizadas y no planificadas por el usuario
     * (categoria inbox; es decir, categoria = null).
     * 
     * @param userId
     *            identificador del usuario
     * 
     * @return todas las tareas no finalizadas de la categoria <i>inbox</i> del
     *         usuario (es decir, sin categoria).
     * 
     */
    public static List<Task> findInboxTasksByUserId(Long userId) {
	return Jpa.getManager()
		.createNamedQuery("Task.findInboxTasksByUserId", Task.class)
		.setParameter("userId", userId).getResultList();
    }

    /**
     * Devuelve todas las tareas que un usuario tiene asignadas para hoy.
     * 
     * @param userId
     *            identificador del usuario
     * 
     * @return todas las tareas no finalizadas para el dia de hoy sin importar
     *         la categoria (inluida la categoria <i>inbox</i>; es decir,
     *         categoria = null)
     * 
     */
    public static List<Task> findTodayTasksByUserId(Long userId) {
	return Jpa.getManager()
		.createNamedQuery("Task.findTodayTasksByUserId", Task.class)
		.setParameter("userId", userId).getResultList();
    }

    /**
     * Devuelve todas las tareas no finalizadas de esta semana de un usuario, o
     * las retrasadas, para todas las categorias (incluida la categoria inbox;
     * es decir, categoria = null).
     * 
     * @param userId
     *            identificador del usuario
     * 
     * @return todas las tareas no finalizadas de esta semana de un usuario (hoy
     *         + 7 dias), o retrasadas, y para todas las categorias (incluida la
     *         <i>inbox</i>).
     * 
     */
    public static List<Task> findWeekTasksByUserId(Long userId) {
	Date fecha = DateUtil.addDays(DateUtil.today(), 6);

	return Jpa.getManager()
		.createNamedQuery("Task.findWeekTasksByUserId", Task.class)
		.setParameter("userId", userId)
		.setParameter("fechaSemana", fecha).getResultList();
    }

    /**
     * Devuelve todas las tareas no finalizadas que esten clasificadas en una
     * determinada categoria.
     * 
     * @param catId
     *            identificador de la categoria
     * 
     * @return todas las tareas ya terminadas de una categoria de un usuario.
     * 
     */
    public static List<Task> findUnfinishedTasksByCategoryId(final Long id) {
	return Jpa
		.getManager()
		.createNamedQuery("Task.findUnfinishedTasksByCategoryId",
			Task.class).setParameter("categId", id).getResultList();
    }

    /**
     * Devuelve todas las tareas finalizadas que esten clasificadas en una
     * determinada categoria.
     * 
     * @param catId
     *            identificador de la categoria
     * 
     * @return todas las tareas ya terminadas de una categoria de un usuario.
     * 
     */
    public static List<Task> findFinishedTasksByCategoryId(Long catId) {
	return Jpa
		.getManager()
		.createNamedQuery("Task.findFinishedTasksByCategoryId",
			Task.class).setParameter("categId", catId)
		.getResultList();
    }

    /**
     * Devuelve todas las tareas finalizadas y no planificadas por el usuario
     * (es decir, que pertenezcan a la categoria inbox; es decir, categoria =
     * null).
     * 
     * @param userId
     *            identificador del usuario
     * 
     * @return todas las tareas finalizadas del usuario, y que pertenezcan a la
     *         categoria <i>inbox</i>.
     * 
     */
    public static List<Task> findFinishedTasksInboxByUserId(Long userId) {
	return Jpa
		.getManager()
		.createNamedQuery("Task.findFinishedTasksInboxByUserId",
			Task.class).setParameter("userId", userId)
		.getResultList();
    }

    // ====================================
    // Buscar número de tareas del usuario
    // ====================================

    public static Long findNumPlannedTasks(Long userId) {
	try {
	    return Jpa.getManager()
		    .createNamedQuery("Task.findNumPlannedTasks", Long.class)
		    .setParameter("userId", userId).getSingleResult();
	}

	catch (NoResultException nre) {
	    throw new RuntimeException("Ha ocurrido un error al buscar el "
		    + "número de tareas planeadas de un usuario", nre);
	}
    }

    public static Long findNumNotPlannedTasks(Long userId) {
	try {
	    return Jpa
		    .getManager()
		    .createNamedQuery("Task.findNumNotPlannedTasks", Long.class)
		    .setParameter("userId", userId).getSingleResult();
	}

	catch (NoResultException nre) {
	    throw new RuntimeException("Ha ocurrido un error al buscar el "
		    + "número de tareas no planeadas de un usuario", nre);
	}
    }

    public static Long findNumFinishedTasks(Long userId) {
	try {
	    return Jpa.getManager()
		    .createNamedQuery("Task.findNumFinishedTasks", Long.class)
		    .setParameter("userId", userId).getSingleResult();
	}

	catch (NoResultException nre) {
	    throw new RuntimeException("Ha ocurrido un error al buscar el "
		    + "número de tareas finalizadas de un usuario", nre);
	}
    }

    public static Long findNumFinishedDelayedTasks(Long userId) {
	try {
	    return Jpa
		    .getManager()
		    .createNamedQuery("Task.findNumFinishedDelayedTasks",
			    Long.class).setParameter("userId", userId)
		    .getSingleResult();
	}

	catch (NoResultException nre) {
	    throw new RuntimeException(
		    "Ha ocurrido un error al buscar el número de tareas "
			    + "finalizadas y retrasadas de un usuario", nre);
	}
    }

    public static List<Task> findNotFinishedTasksByCategoryIdSortedByPlannedASC(
	    Long category_id) {

	return Jpa
		.getManager()
		.createNamedQuery(
			"Task.findNotFinishedTasksByCategoryIdSortedByPlannedASC",
			Task.class).setParameter("category_id", category_id)
		.getResultList();

    }

}