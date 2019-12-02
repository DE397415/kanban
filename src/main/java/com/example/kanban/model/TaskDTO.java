package com.example.kanban.model;

/**
 * @author Deepak Devaraj
 * This is the TaskDTO
 */
public class TaskDTO {

	public int taskId;
	public String taskName;
	public StaffDTO staff;
	public Status status;
	
	
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	public StaffDTO getStaff() {
		return staff;
	}
	public void setStaff(StaffDTO staff) {
		this.staff = staff;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
		
	
	
}
