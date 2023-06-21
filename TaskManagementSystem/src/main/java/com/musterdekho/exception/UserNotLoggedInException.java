package com.musterdekho.exception;

public class UserNotLoggedInException extends Exception{

	public UserNotLoggedInException() {
		super("token expired please log in again");
	}

	public UserNotLoggedInException(String message) {
		super(message);
	}
	
	

}
