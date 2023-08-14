package com.cognizant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.model.MyUser;
import com.cognizant.service.UsersService;
import com.cognizant.util.JwtUtil;

/**
 * 
 * @author rahul
 *
 */

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UsersController {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UsersService userService;
	/**
	 * Checking if api is working
	 * @return
	 */
	@GetMapping("/health-check")
	public ResponseEntity<String> healthCheck() {
		return new ResponseEntity<>("Ok", HttpStatus.OK);
	}
	/**
	 * @author rahul
	 * Register User in Database
	 * @param userCredentials
	 * @return
	 */
	@PostMapping("/register")
	public String registerUser(@RequestBody MyUser userCredentials) {
		userService.register(userCredentials);
		return "submit";
	}
	/**
	 * @author sakshi
	 * Get logged in user type
	 * return string
	 */
	@GetMapping("/userType")
	public String getUserType(@RequestHeader(name = "Authorization") String token1) {
		String token = token1.substring(7);
		String type = "";
		try {
			MyUser user = userService.loadMyUserDetails(jwtUtil.extractUsername(token));
			if(user.getUserType().equals("admin")) {
				type = user.getUserType();
			}else if(user.getUserType().equals("user")) {
				type = user.getUserType();
			}else {
				type = "Invalid";
			}
			return type;
		} catch (Exception e) {
			return "Invalid Token";
		}
		
	}
}
