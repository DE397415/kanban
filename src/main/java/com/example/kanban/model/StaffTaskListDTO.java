package com.example.kanban.model;

import java.util.List;

/**
 * @author Deepak Devaraj
 * This is the Staff Task List DTO
 */
public class StaffTaskListDTO {
	public List<TaskDTO> staffTaskList;

	public List<TaskDTO> getStaffTaskList() {
		return staffTaskList;
	}

	public void setStaffTaskList(List<TaskDTO> staffTaskList) {
		this.staffTaskList = staffTaskList;
	}
	
}
