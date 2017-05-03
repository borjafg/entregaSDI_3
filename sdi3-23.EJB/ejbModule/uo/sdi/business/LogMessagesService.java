package uo.sdi.business;

import javax.jms.Message;

public interface LogMessagesService {

    public void log(Message msg);
    
}