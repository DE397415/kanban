package com.example.kanban;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.kanban.exception.TaskCreateException;
import com.example.kanban.exception.TaskNotFoundException;
import com.example.kanban.exception.TaskUpdateException;
import com.example.kanban.model.StaffDTO;
import com.example.kanban.model.StaffTaskListDTO;
import com.example.kanban.model.Status;
import com.example.kanban.model.TaskDTO;

import junit.framework.Assert;

@DataJpaTest
@RunWith(MockitoJUnitRunner.class)
public class KanbanApplicationIntegrationTest {

	@Test
	public void getKanbanTasksOfStaffTest() {
		
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		ResponseEntity<StaffTaskListDTO> response = testRestTemplate.
		  getForEntity("http://localhost:8080/kanban/id" + "/1", StaffTaskListDTO.class);
		  
		Assert.assertEquals(response.getStatusCode() , HttpStatus.OK);
		
	}	
	
	@Test
	public void getAllKanbanTasks() {
		
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		ResponseEntity<StaffTaskListDTO> response = testRestTemplate.
		  getForEntity("http://localhost:8080/kanban/status/" , StaffTaskListDTO.class);
		  
		Assert.assertEquals(response.getStatusCode() , HttpStatus.OK);
		
	}
	
	@Test
	public void addKanbanTask() throws TaskCreateException , Exception {
		
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		TaskDTO taskDTO = new TaskDTO();
		taskDTO.setTaskName("UML Designing Impl");
		taskDTO.setStatus(Status.BACKLOG);
		StaffDTO staffDTO = new StaffDTO();
		staffDTO.setStaffName("Jack");
		taskDTO.setStaff(staffDTO);
		//ResponseEntity<String> response = testRestTemplate.exchange("http://localhost:8080/kanban/", HttpMethod.POST, staffDTO, String.class)
		 // exchange("http://localhost:8080/kanban/id" + "/1", HttpMethod.POST, staffDTO);
		  
		String result = testRestTemplate.postForObject( "http://localhost:8080/kanban", taskDTO, String.class);
		
		System.out.println("result "+result);
		//Assert.assertEquals(result.getStatusCode() , HttpStatus.OK);
		
	}	
	
	@Test
	public void updateKanbanTask() throws TaskNotFoundException , TaskUpdateException , Exception {
		
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		TaskDTO taskDTO = new TaskDTO();
		taskDTO.setStatus(Status.WIP);
		StaffDTO staffDTO = new StaffDTO();
		staffDTO.setStaffName("Jana");
		taskDTO.setStaff(staffDTO);
		//ResponseEntity<String> response = testRestTemplate.exchange("http://localhost:8080/kanban/", HttpMethod.POST, staffDTO, String.class)
		 // exchange("http://localhost:8080/kanban/id" + "/1", HttpMethod.POST, staffDTO);
		  
		String result = testRestTemplate.postForObject( "http://localhost:8080/kanban/taskId/2", taskDTO, String.class);
		
		System.out.println("result "+result);
		//Assert.assertEquals(result.getStatusCode() , HttpStatus.OK);
		
	}	

}
