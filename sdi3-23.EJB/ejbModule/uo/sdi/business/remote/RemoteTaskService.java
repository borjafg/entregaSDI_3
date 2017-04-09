package uo.sdi.business.remote;

import javax.ejb.Remote;

import uo.sdi.business.TaskService;

@Remote
public interface RemoteTaskService extends TaskService {

}