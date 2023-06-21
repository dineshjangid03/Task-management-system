package com.musterdekho.exception;

public class TaskNotFoundException extends Exception{

	public TaskNotFoundException() {
		super("No Task Found");
	}

	public TaskNotFoundException(Long id) {
		super("Task Not Found With Id "+id);
	}
	
	

}
