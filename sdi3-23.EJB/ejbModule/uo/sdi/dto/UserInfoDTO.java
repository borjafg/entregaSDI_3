package uo.sdi.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import uo.sdi.model.User;

@XmlRootElement(name = "user_info")
public class UserInfoDTO extends UserDTO {

    private static final long serialVersionUID = -1968348581L;

    private long numPlannedTasks;
    private long numNotPlannedTasks;
    private long numFinishedTasks;
    private long numFinishedDelayedTasks;

    public UserInfoDTO() {

    }

    public UserInfoDTO(User user) {
	super(user);
    }

    public UserInfoDTO(String login) {
	super(login);
    }

    // ===================================
    // Getters y Setters
    // ===================================

    @XmlElement(name = "num_planned_tasks")
    public long getNumPlannedTasks() {
	return numPlannedTasks;
    }

    public void setNumPlannedTasks(long numPlannedTasks) {
	this.numPlannedTasks = numPlannedTasks;
    }

    @XmlElement(name = "num_not_planned_tasks")
    public long getNumNotPlannedTasks() {
	return numNotPlannedTasks;
    }

    public void setNumNotPlannedTasks(long numNotPlannedTasks) {
	this.numNotPlannedTasks = numNotPlannedTasks;
    }

    @XmlElement(name = "num_finished_tasks")
    public long getNumFinishedTasks() {
	return numFinishedTasks;
    }

    public void setNumFinishedTasks(long numFinishedTasks) {
	this.numFinishedTasks = numFinishedTasks;
    }

    @XmlElement(name = "num_finished_delayed_tasks")
    public long getNumFinishedDelayedTasks() {
	return numFinishedDelayedTasks;
    }

    public void setNumFinishedDelayedTasks(long numFinishedDelayedTasks) {
	this.numFinishedDelayedTasks = numFinishedDelayedTasks;
    }

    // =================================================
    // toString, equals y hashCode heredados de UserDTO
    // =================================================

    @Override
    public String toString() {
	return "UserInfoDTO [numPlannedTasks=" + numPlannedTasks
		+ ", numNotPlannedTasks=" + numNotPlannedTasks
		+ ", numFinishedTasks=" + numFinishedTasks
		+ ", numFinishedDelayedTasks=" + numFinishedDelayedTasks
		+ ", id=" + id + ", login=" + login + ", email=" + email
		+ ", password=" + password + ", isAdmin=" + isAdmin + "]";
    }

}