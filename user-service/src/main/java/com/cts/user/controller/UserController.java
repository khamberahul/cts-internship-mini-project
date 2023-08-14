package com.cts.user.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.cts.user.exception.UnauthorizedUserFoundException;
import com.cts.user.exception.UserNotFoundException;
import com.cts.user.feign.AuthorisationClient;
import com.cts.user.model.User;
import com.cts.user.service.UserService;
import com.cts.user.service.UserServiceImpl;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

/**
 * This Class is marked as Rest Controller UserController Default context path
 * for this microservice is /user Here, You can add your @ResquestMapping
 */

@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class UserController {
	@Autowired
	UserServiceImpl userServiceImpl;

	@Autowired
	AuthorisationClient authorisationClient;

	/**
	 * This method healthCheck used to check RestController Path
	 * 
	 * @return HttpStatus
	 */
	@GetMapping("/healthcheck")
	public ResponseEntity<String> healthCheck() {
		return new ResponseEntity<String>("Ok", HttpStatus.OK);
	}

	/**
	 * 
	 * @author rahul
	 * @param user
	 * @return
	 */
	@PostMapping
	public ResponseEntity<String> addUser(@RequestBody User user) {
		if (userServiceImpl.getUserByUserUsername(user.getUsername())) {
			return new ResponseEntity<String>("User with this Username already exsits", HttpStatus.BAD_REQUEST);
		} else if (userServiceImpl.getUserByUserUsermail(user.getUserEmail())) {
			return new ResponseEntity<String>("User with this E-mail already exsits", HttpStatus.BAD_REQUEST);
		} else {
			userServiceImpl.addUser(user);
			return new ResponseEntity<String>("Account created for " + user.getUsername() + ".", HttpStatus.CREATED);

		}
	}

	/**
	 * Mapping to fetch all registered users
	 * @author Atharva
	 * @param token
	 * @return ResponseEntity<List<User>>
	 */
	@GetMapping
	public ResponseEntity<?> getAllUsers(@RequestHeader(name = "Authorization") String token) {
		ResponseEntity<?> res = null;

		if (authorisationClient.validate(token)) {
			if (authorisationClient.getUserType(token).getBody().equals("admin")) {
				List<User> users = userServiceImpl.getAllUsers();
				res = new ResponseEntity<List<User>>(users, HttpStatus.OK);
//				log.info(users.size() + " users were fetched.");
			} else if (authorisationClient.getUserType(token).getBody().equals("user")) {
//				throw new UnauthorizedUserFoundException("Unauthorized User Detected");
				res = new ResponseEntity<String>("Unauthorized User Detected", HttpStatus.UNAUTHORIZED);
			} else {
				res = new ResponseEntity<String>("Invalid User Detected", HttpStatus.UNAUTHORIZED);
			}
		} else {
//			res = new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
			log.error("There was an error fetching users.");
			res = new ResponseEntity<String>("Invalid Token", HttpStatus.UNAUTHORIZED);

		}

		return res;
	}

	/**
	 * @author Shridhar
	 * @param token
	 * @param id
	 * @param status
	 * @return
	 */
	@PutMapping("/userstatus/{id}/{status}")
	public ResponseEntity<String> updateUserStatus(@RequestHeader(name = "Authorization") String token,
			@PathVariable int id, @PathVariable String status) {
		ResponseEntity<String> entity = null;
		if (authorisationClient.validate(token)) {
			userServiceImpl.updateUserStatus(id, status);

			entity = new ResponseEntity<String>("Succesfully updated user status", HttpStatus.ACCEPTED);

		} else {
			entity = new ResponseEntity<>(HttpStatus.FORBIDDEN);

		}
		return entity;
	}

	/**
	 * @author Prathamesh
	 * @param token
	 * @param User
	 * @return
	 * @throws VaccineCenterNotFoundException
	 */

	@GetMapping("/userstatus/{Id}")
	public ResponseEntity<String> userStatus(@RequestHeader(name = "Authorization") String token,
			@PathVariable("Id") Integer Id) {
		ResponseEntity<String> entity = null;
		if (authorisationClient.validate(token)) {
			entity = new ResponseEntity<String>(userServiceImpl.userStatus(Id), HttpStatus.OK);
		} else {
			entity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return entity;

	}

	/**
	 * @author Prathamesh
	 * @param token
	 * @param User
	 * @return
	 * @throws VaccineCenterNotFoundException
	 */

	@GetMapping("/vaccinestatus/{Id}")
	public ResponseEntity<String> vaccineStatus(@RequestHeader(name = "Authorization") String token,
			@PathVariable("Id") Integer Id) {
		ResponseEntity<String> entity = null;
		if (authorisationClient.validate(token)) {
			entity = new ResponseEntity<String>(userServiceImpl.vaccinationStatus(Id), HttpStatus.OK);
		} else {
			entity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return entity;

	}

	/**
	 * @author Prathamesh
	 * @param token
	 * @param username
	 * @return ResponseEntity
	 */
	@GetMapping("/user/{userName}")
	public ResponseEntity<?> getUserByUserName(@RequestHeader(name = "Authorization") String token,
			@PathVariable("userName") String username) {
		ResponseEntity<?> res = null;

		if (authorisationClient.validate(token)) {
			if (authorisationClient.getUserType(token).getBody().equals("user")) {
				User user = userServiceImpl.getUserByUserName(username);
				res = new ResponseEntity<User>(user, HttpStatus.OK);
//				log.info(users.size() + " users were fetched.");
			} else if (authorisationClient.getUserType(token).getBody().equals("admin")) {
//				throw new UnauthorizedUserFoundException("Unauthorized User Detected");
				res = new ResponseEntity<String>("Unauthorized User Detected", HttpStatus.UNAUTHORIZED);
			} else {
				res = new ResponseEntity<String>("Invalid User Detected", HttpStatus.UNAUTHORIZED);
			}
		} else {
//			res = new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
			log.error("There was an error fetching users.");
			res = new ResponseEntity<String>("Invalid Token", HttpStatus.UNAUTHORIZED);

		}

		return res;
	}

	/**
	 * @author Rahul
	 * @param token
	 * @param editInfo {String}
	 * @param userId {int}
	 * @return ResponseEntity
	 */
	@PutMapping("/edit/{userId}")
	public ResponseEntity<?> editUserDetails(@RequestHeader(name = "Authorization") String token,
			@RequestBody User editInfo, @PathVariable("userId") int userId) {
		ResponseEntity<?> responsePayload = null;

		boolean updated = false;

		if (authorisationClient.validate(token)) {
			updated = userServiceImpl.editUserDetails(editInfo, userId);
			responsePayload = new ResponseEntity<Boolean>(updated, HttpStatus.OK);

		} else {
			responsePayload = new ResponseEntity<Boolean>(updated, HttpStatus.FORBIDDEN);
		}

		return responsePayload;
	}

	/**
	 * @author Shridhar
	 * @param token
	 * @param id
	 * @param vaccinestatus
	 * @return
	 */
	@PutMapping("/vaccinationstatus/{id}/{vaccinestatus}")
	public ResponseEntity<String> updateVaccinationStatus(@RequestHeader(name = "Authorization") String token,
			@PathVariable int id, @PathVariable String vaccinestatus) {
		ResponseEntity<String> entity = null;
		if (authorisationClient.validate(token)) {
			userServiceImpl.updateVaccinationStatus(id, vaccinestatus);

			entity = new ResponseEntity<String>("Succesfully updated user vaccination status", HttpStatus.ACCEPTED);

		} else {
			entity = new ResponseEntity<>(HttpStatus.FORBIDDEN);

		}
		return entity;
	}

}
