package uo.sdi.business.impl;

import javax.ejb.Stateless;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.user.FindLoggableUSerCommand;
import uo.sdi.business.impl.user.RegisterUserCommand;
import uo.sdi.business.impl.user.UpdateUserDetailsCommand;
import uo.sdi.business.local.LocalUserService;
import uo.sdi.business.remote.RemoteUserService;
import uo.sdi.dto.UserDTO;

@Stateless
public class UserServiceImpl implements LocalUserService, RemoteUserService {

    @Override
    public void registerUser(final UserDTO user) throws BusinessException {
	new RegisterUserCommand(user).execute();
    }

    @Override
    public void updateUserDetails(final UserDTO user) throws BusinessException {
	new UpdateUserDetailsCommand(user).execute();
    }

    @Override
    public UserDTO findLoggableUser(final String login, final String password)
	    throws BusinessException {

	return new FindLoggableUSerCommand(login, password).execute();
    }

}