package com.example.kanban.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KanbanContext {

	private KanbanTaskState kanbanTaskState;
	
	Logger log = LogManager.getLogger(KanbanContext.class);
	

	public KanbanTaskState getKanbanTaskState() {
		return kanbanTaskState;
	}

	public void setKanbanTaskState(KanbanTaskState kanbanTaskState) {
		this.kanbanTaskState = kanbanTaskState;
	}
	
	
}
