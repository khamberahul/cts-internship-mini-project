package com.cognizant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.model.MyUser;
import com.cognizant.repository.UserRepo;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author rahul
 *
 */
@Service
@Slf4j
public class UsersService {
	@Autowired
	private UserRepo userRepo;
	
	/**
	 * Register user in database
	 * @author rahul
	 * @param user
	 */
	public void register(MyUser user) {
		log.info("====================>Registering User<====================");
		userRepo.save(user);
	}
	public MyUser loadMyUserDetails(String username) {
		log.info("====================>Retrieving Registered User Details using id<====================");
		MyUser user = userRepo.findByUserName(username);
		return user;
	}
}
