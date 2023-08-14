package com.cts.center.exception;

/**
 * 
 * @author rahul
 * To Handle Exception for VaccineCenterNotFoundException
 */
public class VaccineCenterNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	/**
	 * VaccineCenterNotFoundException contructor
	 * @param message
	 */
	public VaccineCenterNotFoundException(String message) {
		super(message);
	}
}
