/**
 * 
 */
package com.simple.vending.exception;

/**
 * This exception is used when the price of the credit of the Machine is not
 * enough to buy a Product.
 * 
 * @author Pere Manent
 *
 */
public class NotEnoughCreditException extends ValidationException {
	/**
	 * Message that the exception will throw.
	 */
	private static final String NOT_ENOUGH_CREDIT = "Not enough credit to buy the product";

	/**
	 * Main constructor of the exception. The message is generic for all the
	 * application.
	 * 
	 * @param message
	 */
	public NotEnoughCreditException() {
		super(NOT_ENOUGH_CREDIT);
	}

}
