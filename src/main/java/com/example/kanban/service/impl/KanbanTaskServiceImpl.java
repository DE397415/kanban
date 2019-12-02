package com.example.kanban.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kanban.model.TaskDTO;
import com.example.kanban.entity.Staff;
import com.example.kanban.entity.Task;
import com.example.kanban.exception.TaskCreateException;
import com.example.kanban.exception.TaskNotFoundException;
import com.example.kanban.exception.TaskUpdateException;
import com.example.kanban.service.KanbanTaskService;
import com.example.kanban.util.ObjectMapperUtils;
import com.example.kanban.repository.KanbanTaskRepositoryDummy;
import com.example.kanban.repository.StaffRepository;
import com.example.kanban.repository.TaskRepository;
import com.example.kanban.model.StaffDTO;
import com.example.kanban.model.Status;


/**
 * @author Deepak Devaraj
 * This is the REST Service Implementation for Kanban Tasks.
 * The user would be able to search for the list of tasks assigned to a particular staffId
 * The user would also be able to search for the list of tasks of a particular status
 */
@Service
public class KanbanTaskServiceImpl implements KanbanTaskService {
	
	@Autowired
	KanbanTaskRepositoryDummy kanbanTaskRepository;
	
	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	StaffRepository staffRepository;
	
	Logger log = LogManager.getLogger(KanbanTaskServiceImpl.class);
	
	/**
     * Get the Kanban Tasks for the given Staff Id
     * @param id of int type.
     * @return new object of List<TaskDTO> type.
     */
	public List<TaskDTO> getKanbanTasksOfStaff(Long staffId) throws TaskNotFoundException , Exception {
		log.info("KanbanTaskService::getKanbanTasksOfStaff()");
		//return kanbanTaskRepository.getKanbanTaskStatesForStaffId(id);
		Staff staff = staffRepository.findByStaffId(staffId);
		if(staff==null) 
			throw new TaskNotFoundException(staffId);
		List<Task> taskList = staff.getTaskList();
		List<TaskDTO> taskDTO = ObjectMapperUtils.mapAll(taskList, TaskDTO.class);
		return taskDTO;
	}
	
	/**
     * Get the Kanban Tasks for the given Status
     * @param status of String type.
     * @return new object of List<TaskDTO> type.
     */
	public List<TaskDTO> getKanbanTasksByStatus(Optional<String> status) throws TaskNotFoundException , Exception {
		log.info("KanbanTaskService::getKanbanTasksByStatus()");
		List<Task> taskList = null;
		// kanbanTaskRepository.getKanbanTasksByStatus(status);
		
		if(status.isPresent()) {
			String statusVal = status.get();
			taskList =  taskRepository.findByStatus(statusVal);
			if(taskList.isEmpty()) {
				throw new TaskNotFoundException(statusVal);
			}
		} else {
			taskList =  taskRepository.findAll();
		}
		
		List<TaskDTO> taskDTO = ObjectMapperUtils.mapAll(taskList, TaskDTO.class);
		return taskDTO;
	}
	
	/**
     * Add a new Kanban Task
     * @param status of TaskDTO type.
     */
	public void addKanbanTask(TaskDTO taskDTO) throws TaskCreateException , Exception {
		log.info("KanbanTaskService::addKanbanTask()");
		Task task = new Task();
		task.setTaskName(taskDTO.getTaskName());
		task.setStatus(Status.BACKLOG.toString());
		log.info("KanbanTaskService::addKanbanTask()::Before findByStaffName()::getStaff "+taskDTO.getStaff());
		log.info("KanbanTaskService::addKanbanTask()::Before findByStaffName()::staff::getStaffName "+taskDTO.getStaff().getStaffName());
		Staff staff = staffRepository.findByStaffName(taskDTO.getStaff().getStaffName());
		log.info("KanbanTaskService::addKanbanTask()::After findByStaffName()::staff "+staff);
		if(staff!=null) {
			task.setStaff(staff);
		} else {
			log.info("KanbanTaskService::addKanbanTask()::Before save");
			Staff staffNew = new Staff();
			staffNew.setStaffName(taskDTO.getStaff().getStaffName());
			try {
				Staff staffEntity = staffRepository.save(staffNew);
				log.info("KanbanTaskService::addKanbanTask()::After save");
				task.setStaff(staffEntity);
			} catch(Exception e) {
				throw new TaskCreateException(e.getMessage());
			}
		}
			
		taskRepository.save(task);
	}
	
	/**
     * Update a Kanban Task
     * @param taskId of Long type.
     * @param taskDTO of TaskDTO type.
     */
	public void updateKanbanTask(Long taskId ,TaskDTO taskDTO) throws TaskNotFoundException , TaskUpdateException , Exception {
		log.info("KanbanTaskService::updateKanbanTask()");
		
		Task task = taskRepository.findByTaskId(taskId);
		if(task==null) {
			throw new TaskNotFoundException(taskId);
		}
		if(!task.getStatus().equals(taskDTO.getStatus().toString())) {
			task.setStatus(taskDTO.getStatus().toString());
		}
		try {
			if(!task.getStaff().getStaffName().equals(taskDTO.getStaff().getStaffName())) {			// This should be staffId
				Staff staff = staffRepository.findByStaffName(taskDTO.getStaff().getStaffName());
				if(staff==null) {
					Staff staffNew = new Staff();
					staffNew.setStaffName(taskDTO.getStaff().getStaffName());
					staff = staffNew;
					staffRepository.save(staffNew);
				}
				task.setStaff(staff);
			}
			taskRepository.save(task);
		} catch(Exception e) {
			throw new TaskUpdateException(e.getMessage());
		}
	}
	
	/**
     * Get all Staff
     * @return new object of List<StaffDTO> type.
     */
	public List<StaffDTO> getStaff() throws Exception {
		List<Staff> staffList = staffRepository.findAll();
		List<StaffDTO> staffDTO = ObjectMapperUtils.mapAll(staffList, StaffDTO.class);
		return staffDTO;
	}
}


