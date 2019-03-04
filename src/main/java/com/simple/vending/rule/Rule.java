package com.simple.vending.rule;

import com.simple.vending.exception.ValidationException;

/**
 * Rule Pattern to validate objects.
 * 
 * @author Pere Manent.
 *
 */
public interface Rule {
	/**
	 * Method used to validate the rules.
	 * 
	 * @throws ValidationException when the logic is not validated.
	 */
	public void validate() throws ValidationException;
}