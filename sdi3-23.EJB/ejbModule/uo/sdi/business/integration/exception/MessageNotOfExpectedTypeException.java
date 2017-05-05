package uo.sdi.business.integration.exception;

import javax.jms.Message;

public class MessageNotOfExpectedTypeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Message messageToRedirect;

    public MessageNotOfExpectedTypeException(String detail,
	    Message messageToRedirect) {

	super(detail);
	this.messageToRedirect = messageToRedirect;
    }

    public Message getMessageToRedirect() {
	return messageToRedirect;
    }

}