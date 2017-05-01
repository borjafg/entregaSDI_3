package uo.sdi.rest.requests;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "info_task")
public class FinishTaskRequest {

    private Long user_id;
    private Long task_id;

    public FinishTaskRequest() {

    }

    public FinishTaskRequest(Long user_id, Long task_id) {
	this.user_id = user_id;
	this.task_id = task_id;
    }

    @XmlElement(name = "user_id")
    public Long getUser_id() {
	return user_id;
    }

    public void setUser_id(Long user_id) {
	this.user_id = user_id;
    }

    @XmlElement(name = "task_id")
    public Long getTask_id() {
	return task_id;
    }

    public void setTask_id(Long task_id) {
	this.task_id = task_id;
    }

    @Override
    public String toString() {
	return "FinishTaskRequest [user_id=" + user_id + ", task_id=" + task_id
		+ "]";
    }

}