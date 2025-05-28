package com.user.demo.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<Map<String, String>> handleResponseStatusException(ResponseStatusException ex) {
		Map<String, String> error = new HashMap<>();
		error.put("error", ex.getReason());
		return new ResponseEntity<>(error, ex.getStatusCode());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> handleGeneric(Exception ex) {
		Map<String, String> error = new HashMap<>();
		error.put("error", "Unexpected error occurred");
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(BusinessValidationException.class)
	public ResponseEntity<Map<String, String>> handleBusinessValidation(BusinessValidationException ex) {
		Map<String, String> error = new HashMap<>();
		error.put("error", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleNotFound(ResourceNotFoundException ex) {
		Map<String, String> error = new HashMap<>();
		error.put("error", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

}
