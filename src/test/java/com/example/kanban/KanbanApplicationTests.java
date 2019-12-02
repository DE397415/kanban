package com.example.kanban;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.kanban.entity.Staff;
import com.example.kanban.entity.Task;
import com.example.kanban.exception.TaskNotFoundException;
import com.example.kanban.model.TaskDTO;
import com.example.kanban.repository.KanbanTaskRepositoryDummy;
import com.example.kanban.repository.StaffRepository;
import com.example.kanban.repository.TaskRepository;
import com.example.kanban.service.impl.KanbanTaskServiceImpl;
import com.example.kanban.model.Status;

import junit.framework.Assert;

@DataJpaTest
@RunWith(MockitoJUnitRunner.class)
public class KanbanApplicationTests {

	@InjectMocks
	private KanbanTaskServiceImpl kanbanTaskService;
	
	@Mock
	KanbanTaskRepositoryDummy kanbanTaskRepository;
	
	@Mock
	TaskRepository taskRepository;
	
	
	@Mock
	StaffRepository staffRepository;
	
	//@Test
	public void getKanbanTasksOfStaffTest() throws TaskNotFoundException {
		
		List<TaskDTO> kanbanTasksOfStaff = new ArrayList<>();
		kanbanTasksOfStaff.add(new TaskDTO());
		
//		Mockito.when(kanbanTaskRepository.findByStaffId(Mockito.anyInt())).thenReturn(kanbanTasksOfStaff);
		
		Mockito.when(kanbanTaskRepository.getKanbanTaskStatesForStaffId(Mockito.anyInt())).thenReturn(kanbanTasksOfStaff);
		
		List<TaskDTO> kanbanTasksOfStaff2 = kanbanTaskService.getKanbanTasksOfStaff(Long.valueOf(1));
		
		Assert.assertEquals(1, kanbanTasksOfStaff2.size());
		
	}
	
	//@Test
	public void getKanbanTasksOfStatusTest() throws TaskNotFoundException  {
		
		List<TaskDTO> kanbanTasksOfStaff = new ArrayList<>();
		kanbanTasksOfStaff.add(new TaskDTO());
		
//		Mockito.when(kanbanTaskRepository.findByStaffId(Mockito.anyInt())).thenReturn(kanbanTasksOfStaff);
		
		Mockito.when(kanbanTaskRepository.getKanbanTasksByStatus(Mockito.anyString())).thenReturn(kanbanTasksOfStaff);
		
		List<TaskDTO> kanbanTasksOfStaff2 = kanbanTaskService.getKanbanTasksByStatus(Optional.of("WIP"));
		
		Assert.assertEquals(1, kanbanTasksOfStaff2.size());
		
	}
	
	//@Test
    public void findTaskAndStaff() {
        
        List<Task> taskList = taskRepository.findAll();
        assertEquals(1, taskList.size());
        
        List<Staff> staffList = staffRepository.findAll();
        assertEquals(1, staffList.size());
    }
	
	@Test
    public void givenTaskAndStaffWhenSavedThenPersisted() {
        Task task = new Task();
        Staff staff = new Staff();
        
        staff.setStaffName("Henry");
        
        
        task.setTaskName("UML Designing");
        task.setStatus(Status.BACKLOG.toString());
        
        
        Staff staffPersistedEntity = staffRepository.save(staff);
     //   staffRepository.flush();
        List<Staff> staffList = staffRepository.findAll();
        assertEquals(1, staffList.size());
        
    /*    System.out.println("staffPersistedEntity "+staffPersistedEntity);
        System.out.println("staffPersistedEntity.getStaffId() "+staffPersistedEntity.getStaffId());
        task.setStaffId(staffPersistedEntity.getStaffId());  
        taskRepository.save(task);
        
        
        
        List<Task> taskList = taskRepository.findAll();
        assertEquals(1, taskList.size());
        
        List<Staff> staffList = staffRepository.findAll();
        assertEquals(1, staffList.size());*/
    }

}
