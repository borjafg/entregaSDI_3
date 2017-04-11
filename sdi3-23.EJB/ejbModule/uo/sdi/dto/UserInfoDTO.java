package uo.sdi.dto;

import uo.sdi.model.User;

public class UserInfoDTO extends UserDTO {

    private static final long serialVersionUID = -1968348581L;

    private long numFinishedTasks;
    private long numDelayedTasks;
    private long numNotPlannedTasks;

    public UserInfoDTO(User user) {
	super(user);
    }

    public UserInfoDTO(String login) {
	super(login);
    }

    public long getNumNotPlannedTasks() {
	return numNotPlannedTasks;
    }

    public void setNumNotPlannedTasks(long numNotPlannedTasks) {
	this.numNotPlannedTasks = numNotPlannedTasks;
    }

    public long getNumDelayedTasks() {
	return numDelayedTasks;
    }

    public void setNumDelayedTasks(long numDelayedTasks) {
	this.numDelayedTasks = numDelayedTasks;
    }

    public long getNumFinishedTasks() {
	return numFinishedTasks;
    }

    public void setNumFinishedTasks(long numFinishedTasks) {
	this.numFinishedTasks = numFinishedTasks;
    }

    @Override
    public String toString() {
	return "UserWithDataDTO [numFinishedTasks=" + numFinishedTasks
		+ ", numDelayedTasks=" + numDelayedTasks
		+ ", numNotPlannedTasks=" + numNotPlannedTasks + ", id=" + id
		+ ", login=" + login + ", email=" + email + ", password="
		+ password + ", isAdmin=" + isAdmin + "]";
    }

}