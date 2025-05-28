package com.user.demo.exception;

public class BusinessValidationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1446649836109511119L;

	public BusinessValidationException(String message) {
		super(message);
	}
}