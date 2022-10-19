package com.example.fpl.exceptions;

public class FPLRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -1967180493936590058L;
	
	public FPLRuntimeException() {
		super();
	}
	public FPLRuntimeException(String message) {
		super(message);
	}
	public FPLRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
}
