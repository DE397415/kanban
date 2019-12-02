package com.example.kanban.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BacklogState implements KanbanTaskState {
	//private static final long serialVersionUID = -1322322139926390329L;
	@JsonIgnore
	Logger log = LogManager.getLogger(BacklogState.class);
	
	public String state;
	
	@Override
	public void updateTaskState() {
		log.info("Updated to Backlog State");
		state = StateEnum.WIP.toString();
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
}
