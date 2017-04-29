package uo.sdi.business.impl.task.task;

import uo.sdi.business.exception.BusinessCheck;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.command.Command;
import uo.sdi.model.Task;
import uo.sdi.model.User;
import uo.sdi.model.types.UserStatus;
import uo.sdi.persistence.TaskFinder;
import uo.sdi.persistence.UserFinder;
import alb.util.date.DateUtil;

public class MarkTaskAsFinishedCommand implements Command<Void> {

    private Long user_id;
    private Long task_id;

    public MarkTaskAsFinishedCommand(Long user_id, Long task_id) {
	this.user_id = user_id;
	this.task_id = task_id;
    }

    @Override
    public Void execute() throws BusinessException {
	Task t = TaskFinder.findById(task_id);
	User u = UserFinder.findById(user_id);

	BusinessCheck
		.isNotNull(t, "La tarea que se pretende marcar como "
			+ "finalizada no existe",
			"error_finalizacion_tarea__no_existe");

	BusinessCheck.isTrue(u.getStatus().equals(UserStatus.ENABLED),
		"No se puede finalizar la tarea porque el usuario asociado "
			+ "a ella está deshabilitado",
		"error_finalizacion_tarea__usuario_deshabilitado");

	BusinessCheck.isTrue(u.equals(t.getUser()),
		"El usuario " + u.getLogin() + " ha intentado finalizar una "
			+ "tarea que no estaba asociada a él",
		"error_finalizacion_tarea__usuario_no_propietario");

	t.setFinished(DateUtil.today()); // Estado persistent

	return null;
    }

}