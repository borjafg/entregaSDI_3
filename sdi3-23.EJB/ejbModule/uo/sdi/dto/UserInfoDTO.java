package uo.sdi.dto;

import uo.sdi.model.User;

public class UserInfoDTO extends UserDTO {

    private static final long serialVersionUID = -1968348581L;

    private int numFinishedTasks;
    private int numDelayedTasks;
    private int numNotPlannedTasks;

    public UserInfoDTO(User user) {
	super(user);
    }

    public UserInfoDTO(String login) {
	super(login);
    }

    public int getNumNotPlannedTasks() {
	return numNotPlannedTasks;
    }

    public void setNumNotPlannedTasks(int numNotPlannedTasks) {
	this.numNotPlannedTasks = numNotPlannedTasks;
    }

    public int getNumDelayedTasks() {
	return numDelayedTasks;
    }

    public void setNumDelayedTasks(int numDelayedTasks) {
	this.numDelayedTasks = numDelayedTasks;
    }

    public int getNumFinishedTasks() {
	return numFinishedTasks;
    }

    public void setNumFinishedTasks(int numFinishedTasks) {
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