package uo.sdi.rest.exceptions;

public class FailedToEncriptCredentialsException extends RuntimeException {

    private static final long serialVersionUID = -2166459303540379398L;

    public FailedToEncriptCredentialsException(String message, Throwable ex) {
	super(message, ex);
    }

}