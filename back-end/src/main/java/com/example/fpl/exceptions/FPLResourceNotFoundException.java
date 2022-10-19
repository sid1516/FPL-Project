package com.example.fpl.exceptions;

public class FPLResourceNotFoundException extends Exception {
	
	private static final long serialVersionUID = -3117217241198087181L;
	
	public FPLResourceNotFoundException() {
		super();
	}
	public FPLResourceNotFoundException(String message) {
		super(message);
	}
	public FPLResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
