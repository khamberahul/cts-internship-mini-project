package com.cts.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.user.model.MyUser;



/**
 * Proxy interface for authorization service
 */
@FeignClient(name = "authorization-service", url = "${AUTH_SERVICE:http://localhost:8081}")
public interface AuthorisationClient {

	/**
	 * Method for validating the token
	 * 
	 * @param token
	 * @return This returns true if token is valid else returns false
	 */
	@GetMapping("/auth/validate")
	public boolean validate(@RequestHeader(name = "Authorization") String token);
	@PostMapping("/auth/users/register")
	public String registerUser(@RequestBody MyUser userCredentials);
	@GetMapping("/auth/users/userType")
	public ResponseEntity<String> getUserType(@RequestHeader(name = "Authorization") String token1);
	
}