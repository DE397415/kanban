package com.example.kanban.model;

/**
 * @author Deepak Devaraj
 * This is the Enum for Staus that takes mainly 3 types :
 * BackLog , Work In Progress and Completed
 */
public enum Status {
	BACKLOG("BACKLOG"),
	WIP("WIP"),
	COMPLETED("COMPLETED");
	
	private String state;

	Status(String state) {
        this.state = state;
    }

    public String state() {
        return state;
    }
}
