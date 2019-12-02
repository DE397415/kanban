package com.example.kanban.exception;

public class TaskNotFoundException extends Exception {
	public TaskNotFoundException(Long taskId) {
        super("Task id not found : " + taskId);
    }
	public TaskNotFoundException(String status) {
        super("Task status not found : " + status);
    }
}
