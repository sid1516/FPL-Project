package com.example.fpl.exceptions;

public class FPLException extends Exception {

	private static final long serialVersionUID = 8145808597235481815L;
	
	public FPLException() {
		super();
	}
	public FPLException(String message) {
		super(message);
	}
	public FPLException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
