package uo.sdi.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import uo.sdi.dto.types.UserStatusDTO;
import uo.sdi.model.User;

@XmlRootElement(name = "user")
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Long id;

    protected String login;

    protected String email;
    protected String password;
    protected Boolean isAdmin = false;

    private UserStatusDTO status = UserStatusDTO.ENABLED;

    public UserDTO() {

    }

    public UserDTO(User user) {
	this.id = user.getId();

	this.login = user.getLogin();
	this.email = user.getEmail();
	this.password = user.getPassword();
	this.isAdmin = user.getIsAdmin();

	this.status = DTOadapter.parseStatusToDTO(user.getStatus());
    }

    public UserDTO(String login) {
	this.login = login;
    }

    // ===================================
    // Getters y Setters
    // ===================================

    @XmlElement(name = "id")
    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    @XmlElement(name = "login")
    public String getLogin() {
	return login;
    }

    public void setLogin(String login) {
	this.login = login;
    }

    @XmlElement(name = "email")
    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    @XmlElement(name = "password")
    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    @XmlElement(name = "status")
    public UserStatusDTO getStatus() {
	return status;
    }

    public void setStatus(UserStatusDTO status) {
	this.status = status;
    }

    @XmlElement(name = "is_admin")
    public boolean getIsAdmin() {
	return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
	this.isAdmin = isAdmin;
    }

    // ====================================
    // HashCode, equals y toString
    // ====================================

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;

	result = prime * result + ((id == null) ? 0 : id.hashCode());

	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;

	if (obj == null)
	    return false;

	if (getClass() != obj.getClass())
	    return false;

	UserDTO other = (UserDTO) obj;

	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;

	return true;
    }

    @Override
    public String toString() {
	return "UserDTO [id=" + id + ", login=" + login + ", email=" + email
		+ ", password=" + password + ", isAdmin=" + isAdmin
		+ ", status=" + status + "]";
    }

}