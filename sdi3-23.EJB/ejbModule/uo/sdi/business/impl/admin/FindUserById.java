package uo.sdi.business.impl.admin;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.Command;
import uo.sdi.dto.DTOadapter;
import uo.sdi.dto.UserDTO;
import uo.sdi.persistence.UserFinder;

public class FindUserById implements Command<UserDTO> {

    private Long id;

    public FindUserById(Long id) {
	this.id = id;
    }

    @Override
    public UserDTO execute() throws BusinessException {
	return DTOadapter.userToDTO(UserFinder.findById(id));
    }

}