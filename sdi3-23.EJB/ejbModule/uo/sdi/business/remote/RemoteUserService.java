package uo.sdi.business.remote;

import javax.ejb.Remote;

import uo.sdi.business.UserService;

@Remote
public interface RemoteUserService extends UserService {

}