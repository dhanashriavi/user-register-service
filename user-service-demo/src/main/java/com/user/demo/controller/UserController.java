package com.user.demo.controller;

import com.user.demo.dto.ApiResponse;
import com.user.demo.model.User;
import com.user.demo.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "User Registration", description = "REST APIs for managing users")

public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private final UserService service;



	@PostMapping
	@Operation(summary = "Register a new user", description = "Registers a user if they are from France and at least 18 years old")
	public ResponseEntity<ApiResponse> registerUser(@RequestBody @Valid User user,
			@RequestParam(defaultValue = "false") boolean notify) {
		long startTime = System.currentTimeMillis();

		logger.info("POST / - Input: {}, Notify: {}", user, notify);

		User saved = service.registerUser(user);

		long duration = System.currentTimeMillis() - startTime;

		logger.info("POST / - User saved: {}, Time Taken: {} ms", saved, duration);

		return new ResponseEntity<>(new ApiResponse("User is registered Successfully !!"), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get user by ID", description = "Returns a user based on ID")
	public ResponseEntity<User> getUser(@PathVariable String id) {
		long startTime = System.currentTimeMillis();

		logger.info("GET /{} - Request received", id);

		User user = service.getUserById(id);

		long duration = System.currentTimeMillis() - startTime;

		logger.info("GET /{} - Output: {}, Time Taken: {} ms", id, user, duration);

		return ResponseEntity.ok(user);
	}
}