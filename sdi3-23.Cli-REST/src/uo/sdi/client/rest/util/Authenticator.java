package uo.sdi.client.rest.util;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.MultivaluedMap;

import uo.sdi.client.rest.exceptions.FailedToEncriptCredentialsException;

public class Authenticator implements ClientRequestFilter {

    private final String user;
    private final String password;

    public Authenticator(String user, String password) {
	this.user = user;
	this.password = password;
    }

    @Override
    public void filter(ClientRequestContext ctx) throws IOException {
	MultivaluedMap<String, Object> headers = ctx.getHeaders();

	final String encryptedCredentials = encryptCredentials();

	headers.add("Authorization", encryptedCredentials);
    }

    private String encryptCredentials() {
	String credentials = this.user + " - " + this.password;

	try {
	    return Encryptor.encrypt(credentials);
	}

	catch (RuntimeException ex) {
	    throw new FailedToEncriptCredentialsException(
		    "No se pudieron encriptar las crdenciales del usuario", ex);
	}
    }

}