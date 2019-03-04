package com.simple.vending.rule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simple.vending.exception.MandatoryException;
import com.simple.vending.exception.ValidationException;

/**
 * The Rule will throw an exception when a param is Mandatory.
 * 
 * @author Pere Manent.
 *
 */
public class MandatoryRule implements Rule {
	/**
	 * Logger for the actual Class.
	 */
	private final Logger logger = LoggerFactory.getLogger(MandatoryRule.class);

	/**
	 * Object that is mandatory.
	 */
	private Object paramObject;
	/**
	 * Error message that the Exception will throw.
	 */
	private String errorMessage;

	public MandatoryRule(Object paramObject, String errorMessage) {
		this.paramObject = paramObject;
		this.errorMessage = errorMessage;
	}

	@Override
	public void validate() throws ValidationException {
		logger.debug("Validation of the param Object is not null os empty");
		if (this.paramObject == null) {
			throw new MandatoryException(this.errorMessage);
		}
	}

}