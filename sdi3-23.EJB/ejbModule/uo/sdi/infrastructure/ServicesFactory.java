package uo.sdi.infrastructure;

import uo.sdi.business.AdminService;
import uo.sdi.business.TaskService;
import uo.sdi.business.UserService;

public interface ServicesFactory {

    public AdminService getAdminService();

    public TaskService getTaskService();

    public UserService getUserService();

}