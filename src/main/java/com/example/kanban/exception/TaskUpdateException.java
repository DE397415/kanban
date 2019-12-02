package com.example.kanban.exception;

public class TaskUpdateException extends Exception {
	public TaskUpdateException(Long taskId) {
        super("Task Update Exception : " + taskId);
    }
	public TaskUpdateException(String status) {
        super("Task Update Exception : " + status);
    }
}
