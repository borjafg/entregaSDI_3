package uo.sdi.business.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.admin.DeepDeleteUserCommand;
import uo.sdi.business.impl.admin.DisableUserCommand;
import uo.sdi.business.impl.admin.EnableUserCommand;
import uo.sdi.business.impl.admin.FindAllUsers;
import uo.sdi.business.impl.admin.FindAllUsersInfo;
import uo.sdi.business.impl.admin.FindUserById;
import uo.sdi.business.impl.admin.RestartDatabase;
import uo.sdi.business.local.LocalAdminService;
import uo.sdi.business.remote.RemoteAdminService;
import uo.sdi.dto.UserDTO;
import uo.sdi.dto.UserInfoDTO;

@Stateless
@WebService(name = "AdminService_SOAP")
public class AdminServiceImpl implements LocalAdminService, RemoteAdminService {

    @Override
    public void deepDeleteUser(final Long id) throws BusinessException {
	new DeepDeleteUserCommand(id).execute();
    }

    @Override
    public void restartDatabase() throws BusinessException {
	new RestartDatabase().execute();
    }

    @Override
    public void disableUser(final Long id) throws BusinessException {
	new DisableUserCommand(id).execute();
    }

    @Override
    public void enableUser(final Long id) throws BusinessException {
	new EnableUserCommand(id).execute();
    }

    @Override
    public List<UserDTO> findAllUsers() throws BusinessException {
	return new FindAllUsers().execute();
    }

    @Override
    public List<UserInfoDTO> findAllUsersInfo() throws BusinessException {

	return new FindAllUsersInfo().execute();
    }

    @Override
    public UserDTO findUserById(final Long id) throws BusinessException {
	return new FindUserById(id).execute();
    }

}