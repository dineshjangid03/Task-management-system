package com.musterdekho.exception;

public class UserNotFoundException extends Exception{

	public UserNotFoundException() {
		super("No User Found");
	}

	public UserNotFoundException(Long id) {
		super("User Not Found With Id "+id);
	}
	
	

}
