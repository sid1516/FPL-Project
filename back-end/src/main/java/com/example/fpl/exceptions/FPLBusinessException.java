package com.example.fpl.exceptions;

public class FPLBusinessException extends Exception {

	private static final long serialVersionUID = -6574399060892650271L;
	
	public FPLBusinessException() {
		super();
	}
	public FPLBusinessException(String message) {
		super(message);
	}
	public FPLBusinessException(String message, Throwable cause) {
		super(message, cause);
	}
}
