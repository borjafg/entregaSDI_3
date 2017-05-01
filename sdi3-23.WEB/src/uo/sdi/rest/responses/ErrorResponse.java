package uo.sdi.rest.responses;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
public class ErrorResponse {

    private String causaError;

    public ErrorResponse() {

    }

    public ErrorResponse(String causaError) {
	this.causaError = causaError;
    }

    @XmlElement(name = "causa_error")
    public String getCausaError() {
	return causaError;
    }

    public void setCausaError(String causaError) {
	this.causaError = causaError;
    }

    @Override
    public String toString() {
	return "ErrorResponse [causaError = " + causaError + "]";
    }

}