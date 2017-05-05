package uo.sdi.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
public class ErrorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String causaError;

    public ErrorDTO() {

    }

    public ErrorDTO(String causaError) {
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
	return "ErrorDTO [causaError = " + causaError + "]";
    }

}