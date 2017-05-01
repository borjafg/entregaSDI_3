package uo.sdi.rest.responses;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import uo.sdi.dto.TaskDTO;

@XmlRootElement(name = "tasks_list")
public class ListTasksResponse {

    private List<TaskDTO> tasks;

    public ListTasksResponse() {

    }

    @XmlElement(name = "tasks")
    public List<TaskDTO> getTasks() {
	return tasks;
    }

    public void setTasks(List<TaskDTO> tasks) {
	this.tasks = tasks;
    }

    // ===========================
    // hashCode, equals, toString
    // ===========================

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;

	result = prime * result + ((tasks == null) ? 0 : tasks.hashCode());

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

	ListTasksResponse other = (ListTasksResponse) obj;

	if (tasks == null) {
	    if (other.tasks != null)
		return false;
	} else if (!tasks.equals(other.tasks))
	    return false;

	return true;
    }

    @Override
    public String toString() {
	return "ListTasksResponse [tasks=" + tasks + "]";
    }

}