package com.example.kanban.exception;

public class TaskCreateException extends Exception {
	public TaskCreateException(Long taskId) {
        super("Task Create Exception : " + taskId);
    }
	public TaskCreateException(String status) {
        super("Task Create Exception : " + status);
    }
}
