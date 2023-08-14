package com.cts.user.exception;

/**
 * 
 * @author rahul
 * To Handle Exception for UserNotFoundException
 */
public class UserNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	/**
	 * UserNotFoundException contructor
	 * @param message
	 */
	public UserNotFoundException(String message) {
		super(message);
	}
}
