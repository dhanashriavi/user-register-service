package com.user.demo.service;

import com.user.demo.exception.BusinessValidationException;
import com.user.demo.exception.ResourceNotFoundException;
import com.user.demo.model.User;
import com.user.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	private UserRepository repository;

	public User registerUser(User user) {
		if (!Constants.ALLOWED_COUNTRY.equalsIgnoreCase(user.getCountry()) || user.getAge() < Constants.MINIMUM_AGE) {
			throw new BusinessValidationException("Only adults in France can register.");
		}
		logger.info("Registering user: {}", user.getName());
		return repository.save(user);
	}

	public User getUserById(String id) {
		logger.info("Fetching user with ID: {}", id);
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found"));
	}

}