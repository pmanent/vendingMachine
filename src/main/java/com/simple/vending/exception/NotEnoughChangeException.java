package com.simple.vending.exception;

/**
 * This exception is used when the Machine has not enough money to return to the
 * user.
 * 
 * @author Pere Manent
 *
 */
public class NotEnoughChangeException extends ValidationException {
	/**
	 * Message that the exception will throw.
	 */
	private static final String NOT_ENOUGH_CHANGE = "Not enough change to return to the user";

	/**
	 * Constructor of the exception.
	 * 
	 * @param message
	 */
	public NotEnoughChangeException() {
		super(NOT_ENOUGH_CHANGE);
	}

}
