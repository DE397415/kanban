package com.example.kanban.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.kanban.exception.TaskCreateException;
import com.example.kanban.exception.TaskNotFoundException;
import com.example.kanban.exception.TaskUpdateException;
import com.example.kanban.model.StaffDTO;
import com.example.kanban.model.TaskDTO;

/**
 * @author Deepak Devaraj
 */
@Service
public interface KanbanTaskService {
	public List<TaskDTO> getKanbanTasksOfStaff(Long staffId) throws TaskNotFoundException , Exception ;
	public List<TaskDTO> getKanbanTasksByStatus(Optional<String> status) throws TaskNotFoundException , Exception;
	public void addKanbanTask(TaskDTO taskDTO) throws TaskCreateException , Exception;
	public void updateKanbanTask(Long taskId , TaskDTO taskDTO) throws TaskNotFoundException , TaskUpdateException , Exception;
	public List<StaffDTO> getStaff() throws Exception;
}


