package com.example.kanban.entity;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;  
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;  
import javax.persistence.GenerationType;  
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table; 

@Entity
@Table(name="staff")  
public class Staff {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long staffId;
	
	@Column
	public String staffName;
	
	@OneToMany(mappedBy="staff", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Task.class)
    private List<Task> taskList;

	

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	
	
}
