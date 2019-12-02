package com.example.kanban.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.kanban.exception.TaskCreateException;
import com.example.kanban.exception.TaskNotFoundException;
import com.example.kanban.exception.TaskUpdateException;
import com.example.kanban.model.StaffDTO;
import com.example.kanban.model.StaffTaskListDTO;
import com.example.kanban.model.TaskDTO;
import com.example.kanban.service.KanbanTaskService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Deepak Devaraj
 * This is the REST Controller for Kanban Task
 * There are 2 GET methods that returns the List of Tasks based on the Staff Id and the Status respectively
 */
@RestController
@RequestMapping(value="/kanban")
@Api(tags = { "Kanban Task API" })
public class KanbanTaskController {
	
	@Autowired
	KanbanTaskService kanbanTaskService;

	Logger log = LogManager.getLogger(KanbanTaskController.class);
	
	
	/**
     * Get the Kanban Tasks for the given Staff Id
     * @param id of int type.
     * @return new object of StaffTaskListDTO type.
     */
	@RequestMapping(value="/staffId/{staffId:[\\d]+}", method = RequestMethod.GET , produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get Kanban Tasks Of Id", notes = "This API fetches all the Kanban Tasks Of the given Staff Id.")
    public ResponseEntity<StaffTaskListDTO> getKanbanTasksOfStaff(@PathVariable("staffId") Long staffId) throws TaskNotFoundException , Exception {
        
        log.info("KanbanTaskController::getKanbanTasksOfStaff()");
        StaffTaskListDTO staffTaskList =  new StaffTaskListDTO();
        staffTaskList.setStaffTaskList(kanbanTaskService.getKanbanTasksOfStaff(staffId));
   //     return staffTaskList;
        return new ResponseEntity<>(staffTaskList, HttpStatus.OK);
    }
	
	/**
     * Get the Kanban Tasks for the given Status
     * @param status of String type.
     * @return new object of StaffTaskListDTO type.
     */
	@RequestMapping(value="/status/{status}", method = RequestMethod.GET , produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get Kanban Tasks of status", notes = "This API fetches all the Kanban Tasks coming under the given status.")
    public ResponseEntity<StaffTaskListDTO> getKanbanTasksByStatus(@PathVariable("status") Optional<String> status) throws TaskNotFoundException , Exception {
        
        log.info("KanbanTaskController::getKanbanTasksByStatus()");
        StaffTaskListDTO staffTaskList =  new StaffTaskListDTO();
        staffTaskList.setStaffTaskList(kanbanTaskService.getKanbanTasksByStatus(status));
     //   return staffTaskList;
        return new ResponseEntity<>(staffTaskList, HttpStatus.OK);
    }
	
	/**
     * Add a new Task to Kanban list of tasks
     * @param status of String type.
     * @return new object of StaffTaskListDTO type.
     */
	@RequestMapping(method = RequestMethod.POST , consumes = "application/json" )
	@ResponseBody
	@ApiOperation(value = "Add a Kanban Task", notes = "This API adds a new Task to Kanban's list of tasks.")
    public void addKanbanTask(@RequestBody TaskDTO taskDTO) throws TaskCreateException , Exception {
        
        log.info("KanbanTaskController::addKanbanTask()");
        kanbanTaskService.addKanbanTask(taskDTO);
    }
	
	/**
     * Update a Kanban Task based on the status of a Task and/or the staff working on that task
     * @param status of String type.
     * @return new object of StaffTaskListDTO type.
     */
	@RequestMapping(value="/taskId/{taskId:[\\d]+}", method = RequestMethod.POST , consumes = "application/json" )
	@ResponseBody
	@ApiOperation(value = "Update a Kanban Task", notes = "This API updates the status of a Task and/or the staff working on that task.")
    public void updateKanbanTask(@PathVariable("taskId") Long taskId , @RequestBody TaskDTO taskDTO) throws TaskNotFoundException , TaskUpdateException , Exception {
        
        log.info("KanbanTaskController::addKanbanTask()");
        kanbanTaskService.updateKanbanTask(taskId , taskDTO);
    }
	
	/**
     * Get All the Staffs
     * @param id of int type.
     * @return new object of StaffTaskListDTO type.
     */
	@RequestMapping(value="/staffId", method = RequestMethod.GET , produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get All Staff", notes = "This API fetches all the Staff.")
    public ResponseEntity<List<StaffDTO>> getStaff() throws Exception {
        
        log.info("KanbanTaskController::getStaff()");
  //      return kanbanTaskService.getStaff();
        return new ResponseEntity<>(kanbanTaskService.getStaff(), HttpStatus.OK);
    }
	
	
}
