package com.simple.vending.exception;

/**
 * Base exception of all validations.
 *
 * @author Pere Manent
 */
public abstract class ValidationException extends RuntimeException {

	/**
	 * Constructor of the exception with the message that the exception will hold.
	 * 
	 * @param message that the exception will throw.
	 */
	public ValidationException(String message) {
		super(message);
	}

}
