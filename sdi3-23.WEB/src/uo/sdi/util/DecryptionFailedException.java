package uo.sdi.util;

public class DecryptionFailedException extends RuntimeException {

    private static final long serialVersionUID = -72691457840264319L;

    public DecryptionFailedException(String mensaje) {
	super(mensaje);
    }

}