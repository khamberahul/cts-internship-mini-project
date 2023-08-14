package com.cts.user.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import com.cts.user.feign.AuthorisationClient;
import com.cts.user.service.UserServiceImpl;

@SpringBootTest
class UserControllerTest {
	@InjectMocks
	UserController userController;

	@Mock
	UserServiceImpl userServiceImpl;

	@Mock
	AuthorisationClient authorisationClient;

	@Test
	void healthChecktest() {
		ResponseEntity<String> healthStatus = userController.healthCheck();
		assertEquals("Ok", healthStatus.getBody().toString());
	}
	
	@Test
	void getUserTest() {
		when(authorisationClient.validate("user1")).thenReturn(true);
//		ResponseEntity<?> result = userController.getAllUsers("user1");
//		String userType = authorisationClient.getUserType("user1").getBody().toString();
	}

}
