package com.example.kanban.entity;
import javax.persistence.CascadeType;
import javax.persistence.Column;  
import javax.persistence.Entity;  
import javax.persistence.GeneratedValue;  
import javax.persistence.GenerationType;  
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table; 

@Entity
@Table(name="task")  
public class Task {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long taskId;
	
	@Column
	public String taskName;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "staff_id")
	public Staff staff;
	
	@Column
	public String status;

	

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
