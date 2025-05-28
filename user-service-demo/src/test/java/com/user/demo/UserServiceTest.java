package com.user.demo;

import com.user.demo.exception.BusinessValidationException;
import com.user.demo.exception.ResourceNotFoundException;
import com.user.demo.model.User;
import com.user.demo.repository.UserRepository;
import com.user.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	private UserRepository repository;

	@InjectMocks
	private UserService userService;

	private User validUser;

	@BeforeEach
	void setUp() {
		validUser = new User();
		validUser.setId("123");
		validUser.setName("John Doe");
		validUser.setCountry("France");
		validUser.setAge(25);
	}

	@Test
	void registerUser_Success() {
		when(repository.save(validUser)).thenReturn(validUser);

		User savedUser = userService.registerUser(validUser);

		assertNotNull(savedUser);
		assertEquals("John Doe", savedUser.getName());
		verify(repository, times(1)).save(validUser);
	}

	@Test
	void registerUser_Failure_UnderageOrWrongCountry() {
		User invalidUser = new User();
		invalidUser.setName("Jane");
		invalidUser.setAge(17);
		invalidUser.setCountry("Germany");

		BusinessValidationException exception = assertThrows(
				BusinessValidationException.class,
				() -> userService.registerUser(invalidUser)
		);

		assertEquals("Only adults in France can register.", exception.getMessage());
		verify(repository, never()).save(any());
	}

	@Test
	void getUserById_Success() {
		when(repository.findById("123")).thenReturn(Optional.of(validUser));

		User user = userService.getUserById("123");

		assertNotNull(user);
		assertEquals("John Doe", user.getName());
		verify(repository, times(1)).findById("123");
	}

	@Test
	void getUserById_NotFound() {
		when(repository.findById("999")).thenReturn(Optional.empty());

		ResourceNotFoundException exception = assertThrows(
				ResourceNotFoundException.class,
				() -> userService.getUserById("999")
		);

		assertEquals("User with ID 999 not found", exception.getMessage());
		verify(repository, times(1)).findById("999");
	}
}
