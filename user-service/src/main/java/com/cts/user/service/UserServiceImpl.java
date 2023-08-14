package com.cts.user.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.user.exception.UserNotFoundException;
import com.cts.user.feign.AuthorisationClient;
import com.cts.user.model.MyUser;
import com.cts.user.model.User;
import com.cts.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author rahul UserServiceImpl Implementation
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	AuthorisationClient authorisationClient;

	/**
	 * Save User Details Implementation
	 * 
	 * @author Rahul
	 */
	@Override
	public void addUser(User user) {
		log.info("==================>>>>>>>>>>Registering User<<<<<<<<<<<<========================");
		userRepository.save(user);
		MyUser userDetails = new MyUser();
		userDetails.setUserName(user.getUsername());
		userDetails.setPassword(user.getUserPassword());
		userDetails.setUserType(user.getUserType());
		authorisationClient.registerUser(userDetails);
		log.info("==================>>>>>>>>>>User Registered<<<<<<<<<<<<========================");
	}

	/**
	 * Fetch user details with userName
	 * 
	 * @author Rahul
	 * @return boolean
	 */
	@Override
	public boolean getUserByUserUsername(String username) {
		log.info(
				"==================>>>>>>>>>>Checking User Username already exists in Database or Not<<<<<<<<<<<<========================");
		if (userRepository.findUserByUserUsername(username) == null) {
			return false;
		} else {
			log.info("==================>>>>>>>>>>Username Checked<<<<<<<<<<<<==================");
			return true;
		}

	}
	/**
	 * Fetch User details using user email
	 * @author rahul
	 * @return boolean
	 */
	@Override
	public boolean getUserByUserUsermail(String usermail) {
		log.info(
				"==================>>>>>>>>>>Checking User Username already exists in Database or Not<<<<<<<<<<<<========================");
		if (userRepository.findUserByUsermail(usermail) == null) {
			return false;
		} else {
			log.info("==================>>>>>>>>>>Username Checked<<<<<<<<<<<<==================");
			return true;
		}
	}

	/**
	 * Return a list of all users whose userType == 'user'
	 * 
	 * @author AtharvaKamble
	 * @return List<User>
	 */
	@Override
	public List<User> getAllUsers() {
		List<User> registeredUsers = userRepository.fetchAllUsers();

		log.info("==================>>>>>>>>>>Fetching all users details<<<<<<<<<<<<==================");

		return registeredUsers;
	}

	/*
	 * Update the user status
	 * 
	 * @author Shridhar
	 */
	LocalDate date = LocalDate.now();
	LocalDate date2 = date.plusDays(30);
	Date date3 = java.sql.Date.valueOf(date2);

	@Override
	public void updateUserStatus(Integer id, String status) {
		log.info("====================>Updating User Status<====================");
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("No such user"));

//		Optional<User> optionalUser = userServiceImpl.getUserById(userId);
//		if (optionalUser.isPresent() == false) {
//			throw new UserNotFoundException("User not found in Database");
//		} else {
//			User fetchUserDetails = optionalUser.get();
		if (status.equals("Accept")) {
			user.setUserStatus(status);
			user.setVaccinationDate(date3);
		} else if (status.equals("Reject")) {
			user.setUserStatus(status);
		} else {
			user.setUserStatus("pending");
//			throw new RuntimeException("Unknown Status Found");
		}
		userRepository.save(user);
		log.info("====================>User Status Updated<====================");
	}

	/**
	 * author prathamesh
	 */
	@Override
	public String userStatus(Integer userId) {
		log.info("====================>Checking User Status<====================");
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("No such user to view status"));
		return userRepository.viewStatus(userId);
	}

	/**
	 * author prathamesh
	 */
	@Override
	public String vaccinationStatus(Integer userId) {
		log.info("====================>Checking User Vaccination Status<====================");
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("No such user to view  status"));
		return userRepository.vaccineStatus(userId);

	}

	/**
	 * author prathamesh
	 */
	@Override
	public User getUserById(Integer id) {
		// TODO Auto-generated method stub
		log.info("====================>Retrieving User By Id<====================");
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("No such user to view  status"));
		return user;
	}

	/**
	 * author prathamesh
	 */

	@Override
	public User getUserByUserName(String username) {
		// TODO Auto-generated method stub
		log.info("====================>Retrieving User By Username<====================");
		return userRepository.getUserByUsername(username);
	}

	/**
	 * @author rahul
	 * Editing User profile
	 */
	@Override
	public boolean editUserDetails(User editInfo, int userId) {
		boolean flag = false;
		log.info("====================>Updating user with ID with ID: {}<====================",userId);

		try {
			User temp = userRepository.findById(userId)
					.orElseThrow(() -> new UserNotFoundException("No user with this ID was found."));

			temp.setFullName(editInfo.getFullName());
			temp.setAadharNumber(editInfo.getAadharNumber());
			temp.setDob(editInfo.getDob());
			temp.setAge(editInfo.getAge());
			temp.setGender(editInfo.getGender());
			temp.setPhoneNumber(editInfo.getPhoneNumber());
			temp.setAadharNumber(editInfo.getAadharNumber());
			temp.setAddress(editInfo.getAddress());
			temp.setAadharNumber(editInfo.getAadharNumber());
			temp.setUsername(editInfo.getUsername());
			temp.setUserEmail(editInfo.getUserEmail());
			temp.setUserPassword(editInfo.getUserPassword());
			temp.setCenterId(editInfo.getCenterId());
			temp.setVaccineId(editInfo.getVaccineId());

			userRepository.save(temp);
			flag = true;
			log.info("====================>User with ID: {} updated successfully!<====================", userId);

		} catch (Exception e) {
			log.info("====================>There was an error while updating center with ID: {}.<====================", userId);
			log.error(e.toString());
		}

		return flag;
	}

	/**
	 * Update vaccination status for a user
	 * @author Shridhar
	 * @param id {Integer}
	 * @param vaccinestatus {String}
	 */

	@Override
	public void updateVaccinationStatus(Integer id, String vaccinestatus) {
		log.info("====================>Updating vaccination status for user with ID: {}<====================", id);
		
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("No such user"));
//		if(vaccinestatus.equals("dose1")) {
//			user1.setVaccinationStatus("Partially Vaccinated");
//			user1.setVaccinationDate(date5);
//		}
//		else if (vaccinestatus.equals("dose2")) {
//			user1.setVaccinationStatus("Fully Vaccinated");
//		}
		if (vaccinestatus.equals("dose1")) {
			user.setVaccinationStatus("vaccinated");
		} else {
			user.setVaccinationStatus("pending");
		}
		log.info("====================>User Vaccinated<====================");
		userRepository.save(user);
	}
}
