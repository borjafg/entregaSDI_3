package uo.sdi.business.local;

import javax.ejb.Local;

import uo.sdi.business.UserService;

@Local
public interface LocalUserService extends UserService {

}