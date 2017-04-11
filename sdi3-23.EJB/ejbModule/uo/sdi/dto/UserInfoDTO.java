package uo.sdi.dto;

import uo.sdi.model.User;

public class UserInfoDTO extends UserDTO {

    private static final long serialVersionUID = -1968348581L;

    private long numPlannedTasks;
    private long numNotPlannedTasks;
    private long numFinishedTasks;
    private long numFinishedDelayedTasks;

    public UserInfoDTO(User user) {
	super(user);
    }

    public UserInfoDTO(String login) {
	super(login);
    }

    public long getNumPlannedTasks() {
	return numPlannedTasks;
    }

    public void setNumPlannedTasks(long numPlannedTasks) {
	this.numPlannedTasks = numPlannedTasks;
    }

    public long getNumNotPlannedTasks() {
	return numNotPlannedTasks;
    }

    public void setNumNotPlannedTasks(long numNotPlannedTasks) {
	this.numNotPlannedTasks = numNotPlannedTasks;
    }

    public long getNumFinishedTasks() {
	return numFinishedTasks;
    }

    public void setNumFinishedTasks(long numFinishedTasks) {
	this.numFinishedTasks = numFinishedTasks;
    }

    public long getNumFinishedDelayedTasks() {
	return numFinishedDelayedTasks;
    }

    public void setNumFinishedDelayedTasks(long numFinishedDelayedTasks) {
	this.numFinishedDelayedTasks = numFinishedDelayedTasks;
    }

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