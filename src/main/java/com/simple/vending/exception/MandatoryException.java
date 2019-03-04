/**
 * 
 */
package com.simple.vending.exception;

/**
 * This exception is used when a mandatory field is missing.
 * 
 * @author Pere Manent
 *
 */
public class MandatoryException extends ValidationException {

	/**
	 * @param message that the exception will throw.
	 */
	public MandatoryException(String message) {
		super(message);
	}

}
