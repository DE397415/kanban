package com.example.kanban.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.example.kanban.model.StaffDTO;
import com.example.kanban.model.Status;
import com.example.kanban.model.TaskDTO;

@Repository
public class KanbanTaskRepositoryDummy {

	List<TaskDTO> staffTaskList = null;
	Map<Integer,StaffDTO> staffMap = null;
	
	Logger log = LogManager.getLogger(KanbanTaskRepositoryDummy.class);
	
	KanbanTaskRepositoryDummy() {
		staffTaskList = new ArrayList<TaskDTO>();
		staffMap = new HashMap<Integer,StaffDTO>();
		addStaff();
		addKanbanTasks();
	}
	
	public void getKanbanTasksOfStaff(int id) {
		log.info("KanbanTaskRepository::getKanbanTasksOfStaff()");
		
		
	}
	
	public List<TaskDTO> getKanbanTaskStatesForStaffId(int id) {
		List<TaskDTO> taskList = staffTaskList.stream().filter(a->a.getStaff().getStaffId()==id).collect(Collectors.toList());
		return taskList;
	}
	
	public List<TaskDTO> getKanbanTasksByStatus(String status) {
		List<TaskDTO> taskList = staffTaskList.stream().filter(a->a.getStatus().toString().equals(status)).collect(Collectors.toList());
		return taskList;
	}
	
	public StaffDTO getStaffById(int id) {
		StaffDTO staff = staffMap.get(id);
		return staff;
	}
	
	public void addKanbanTasks() {
		TaskDTO staffTaskOne = new TaskDTO();
		staffTaskOne.setTaskId(1);
		staffTaskOne.setStaff(staffMap.get(1));
		staffTaskOne.setStatus(Status.WIP);
		staffTaskList.add(staffTaskOne);
		
		TaskDTO staffTaskTwo = new TaskDTO();
		staffTaskTwo.setTaskId(2);
		staffTaskTwo.setStaff(staffMap.get(2));
		staffTaskTwo.setStatus(Status.BACKLOG);
		staffTaskList.add(staffTaskTwo);
		
		TaskDTO staffTaskThree = new TaskDTO();
		staffTaskThree.setTaskId(3);
		staffTaskThree.setStaff(staffMap.get(1));
		staffTaskThree.setStatus(Status.COMPLETED);
		staffTaskList.add(staffTaskThree);
	}
	
	public void addStaff() {
		StaffDTO staff1 = new StaffDTO();
		staff1.setStaffId(1);
		staff1.setStaffName("John");
		staffMap.put(staff1.getStaffId(),staff1);
		
		StaffDTO staff2 = new StaffDTO();
		staff2.setStaffId(2);
		staff2.setStaffName("Tom");
		staffMap.put(staff2.getStaffId(),staff2);
	}
}
