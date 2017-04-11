package uo.sdi.business.impl.admin;

import java.util.ArrayList;
import java.util.List;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.command.Command;
import uo.sdi.dto.UserInfoDTO;
import uo.sdi.model.User;
import uo.sdi.persistence.TaskFinder;
import uo.sdi.persistence.UserFinder;

public class FindAllUsersInfo implements Command<List<UserInfoDTO>> {

    @Override
    public List<UserInfoDTO> execute() throws BusinessException {
	List<UserInfoDTO> usersInfo = new ArrayList<UserInfoDTO>();
	List<User> users = UserFinder.findAll();

	for (User user : users) {
	    UserInfoDTO userInfo = new UserInfoDTO(user);

	    userInfo.setNumNotPlannedTasks(TaskFinder.findNumPlannedTasks(user
		    .getId()));

	    userInfo.setNumDelayedTasks(TaskFinder.findNumDelayedTasks(user
		    .getId()));

	    userInfo.setNumFinishedTasks(TaskFinder.findNumFinishedTasks(user
		    .getId()));

	    usersInfo.add(userInfo);
	}

	return usersInfo;
    }

}