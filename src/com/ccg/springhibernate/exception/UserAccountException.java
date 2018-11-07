package com.ccg.springhibernate.exception;

public class UserAccountException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public UserAccountException() {
		super();
	}

	public UserAccountException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserAccountException(String message) {
		super(message);
	}
	
}
