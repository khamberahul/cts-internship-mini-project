package com.cts.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.user.model.User;

import java.util.List;

/**
 * 
 * @author Rahul
 * UserRepository Repository
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	/**
	 * @author Rahul
	 * @param username String
	 * @return String
	 */
	 @Transactional
	@Query(value = "select username from User where username=?1", nativeQuery = true)
	public String findUserByUserUsername(String username);
	 @Transactional
	@Query(value = "select usermail from User where usermail=?1", nativeQuery = true)
	public String findUserByUsermail(String userEmail);

	/**
	 * @author AtharvaKamble
	 * @param null
	 * @return List<User>
	 */
	 @Transactional
	@Query(value = "SELECT * FROM User;", nativeQuery = true)
	public List<User> fetchAllUsers();
	
	
	/**
	 * @author Prathamesh
	 * @param userId Integer
	 * @param status String
	 */
	 @Transactional
	@Query( "select userStatus from User where userId=?1")
	public String viewStatus(Integer id);
	
	/**
	 * @author Prathamesh
	 * @param userId Integer
	 * @param status String
	 * 
	 */
	@Transactional
	@Query("select vaccinationStatus from User where userId=?1")
	public String vaccineStatus(Integer id);

	// /**
	//  * @author Shridhar
	//  * @param userId Integer
	//  * @param status String
	//  */
	// @Transactional
	// @Modifying
	// @Query("update User set userStatus=?2 where userId=?1")
	// public void updateUserStatus(Integer userId,String status);
	
	/**
	 * @author Prathamesh
	 * @param userId Integer
	 * @param status String
	 * 
	 */
	@Transactional
	@Query(value = "select * from User where username=?1", nativeQuery = true)
	public User getUserByUsername(String username);
}
