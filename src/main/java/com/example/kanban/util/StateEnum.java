package com.example.kanban.util;

public enum StateEnum {
	BACKLOG("BACKLOG"),
	WIP("WIP"),
	COMPLETED("COMPLETED");
	
	private String state;

	StateEnum(String state) {
        this.state = state;
    }

    public String state() {
        return state;
    }
}
