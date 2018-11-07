package com.ccg.springhibernate.exception;

public class BookStockException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public BookStockException() {
		super();
	}

	public BookStockException(String message, Throwable cause) {
		super(message, cause);
	}

	public BookStockException(String message) {
		super(message);
	}
	
	

}
