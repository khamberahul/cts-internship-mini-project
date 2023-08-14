package com.cts.center.exception;

/**
 * 
 * @author rahul
 * To Handle Exception for VaccineDetailsNotFoundException
 */
public class VaccineDetailsNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	/**
	 * VaccineDetailsNotFoundException contructor
	 * @param message
	 */
	public VaccineDetailsNotFoundException(String message) {
		super(message);
	}
}
