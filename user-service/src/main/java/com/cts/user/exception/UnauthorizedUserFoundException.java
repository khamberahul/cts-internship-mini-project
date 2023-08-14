package com.cts.user.exception;

/**
 * 
 * @author rahul
 *
 */
public class UnauthorizedUserFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	/**
	 * @author rahul
	 * UserNotFoundException contructor
	 * @param message
	 */
	public UnauthorizedUserFoundException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}
}
