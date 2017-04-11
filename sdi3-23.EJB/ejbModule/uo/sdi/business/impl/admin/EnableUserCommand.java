package uo.sdi.business.impl.admin;

import uo.sdi.business.exception.BusinessCheck;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.command.Command;
import uo.sdi.model.User;
import uo.sdi.model.types.UserStatus;
import uo.sdi.persistence.UserFinder;

public class EnableUserCommand implements Command<Void> {

    private Long id;

    public EnableUserCommand(Long id) {
	this.id = id;
    }

    @Override
    public Void execute() throws BusinessException {
	User user = UserFinder.findById(id);

	BusinessCheck.isNotNull(user, "El usuario no existe.",
		"error_administrador_cambiar_estado_usuario_no_existe");

	BusinessCheck.isFalse(user.getIsAdmin(),
		"No se puede cambiar el estado de la cuenta de este usuario ["
			+ user.getLogin() + "] porque es un administrador.",
		"error_administrador__cambiar_estado_usuario_administrador");

	BusinessCheck.isTrue(user.getStatus().equals(UserStatus.DISABLED),
		"El usuario ya estaba habilitado.",
		"error_administrador_cambiar_estado_usuario_ya_habilitado");

	user.setStatus(UserStatus.ENABLED); // estado persistent

	return null;
    }

}