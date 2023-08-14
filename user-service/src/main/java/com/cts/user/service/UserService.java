package com.cts.user.service;

import java.util.List;
import java.util.Optional;

import com.cts.user.model.User;

/**
 * UserService Interface for the User Service functionality
 */
public interface UserService {

	/**
	 * For saving the User
	 * 
	 * @author Rahul
	 * @param user User
	 */
	public void addUser(User user);

	/**
	 * Fetch user details with userName
	 * 
	 * @author Rahul
	 * @param userName String
	 * @return boolean
	 */
	public boolean getUserByUserUsername(String username);

	/**
	 * @author rahul
	 * @param usermail
	 * @return boolean
	 */
	public boolean getUserByUserUsermail(String usermail);

	/**
	 * Return a list of all users whose userType == 'user'
	 * 
	 * @author AtharvaKamble
	 * @return List<User>
	 */
	public List<User> getAllUsers();

	/**
	 * @author Shridhar
	 * @param id
	 * @param status
	 */
	public void updateUserStatus(Integer id, String status);

	/**
	 * @author Prathamesh
	 * @param userId Integer
	 * @return String
	 */
	public String userStatus(Integer userId);

	/**
	 * @author Prathamesh
	 * @param userId Integer
	 * @return String
	 */
	public String vaccinationStatus(Integer userId);

	public User getUserById(Integer id);

	public User getUserByUserName(String username);

	/**
	 * @author Rahul
	 * @param editInfo
	 * @param userId
	 * @return
	 */
	public boolean editUserDetails(User editInfo, int userId);

	/**
	 * @author Shridhar
	 * @param id
	 * @param vaccinestatus
	 */
	public void updateVaccinationStatus(Integer id, String vaccinestatus);
}
