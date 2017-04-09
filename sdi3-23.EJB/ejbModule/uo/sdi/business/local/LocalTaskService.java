package uo.sdi.business.local;

import javax.ejb.Local;

import uo.sdi.business.TaskService;

@Local
public interface LocalTaskService extends TaskService {

}