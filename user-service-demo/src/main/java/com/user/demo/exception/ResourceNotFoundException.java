package com.user.demo.exception;

public class ResourceNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7282131869246252102L;

	public ResourceNotFoundException(String message) {
        super(message);
    }
}
