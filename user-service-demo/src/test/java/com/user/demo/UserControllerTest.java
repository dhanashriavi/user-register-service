package com.user.demo;



import com.user.demo.controller.UserController;
import com.user.demo.dto.ApiResponse;
import com.user.demo.model.User;
import com.user.demo.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User validUser;

    @BeforeEach
    void setup() {
        validUser = new User();
        validUser.setId("123");
        validUser.setName("John Doe");
        validUser.setCountry("France");
        validUser.setAge(25);
    }

    @Test
    void testRegisterUser_Success() {
        when(userService.registerUser(validUser)).thenReturn(validUser);

        ResponseEntity<ApiResponse> response = userController.registerUser(validUser, false);

        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals("User is registered Successfully !!", response.getBody().getMessage());
        verify(userService, times(1)).registerUser(validUser);
    }

    @Test
    void testGetUser_Success() {
        when(userService.getUserById("123")).thenReturn(validUser);

        ResponseEntity<User> response = userController.getUser("123");

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("John Doe", response.getBody().getName());
        verify(userService, times(1)).getUserById("123");
    }
}
