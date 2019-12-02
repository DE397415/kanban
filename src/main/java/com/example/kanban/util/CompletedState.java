package com.example.kanban.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonIgnore;
public class CompletedState implements KanbanTaskState {
	//private static final long serialVersionUID = -1322322139926390329L;
	@JsonIgnore 
	Logger log = LogManager.getLogger(WIPState.class);
	public String state;
	@Override
	public void updateTaskState() {
		log.info("Updated to Completed State");
		state = StateEnum.COMPLETED.toString();
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
