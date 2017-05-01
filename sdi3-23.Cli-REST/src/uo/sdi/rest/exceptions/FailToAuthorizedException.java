package uo.sdi.rest.exceptions;

public class FailToAuthorizedException extends RuntimeException {

    private static final long serialVersionUID = -26483025234556L;

    public FailToAuthorizedException(String message) {
	super(message);
    }

}