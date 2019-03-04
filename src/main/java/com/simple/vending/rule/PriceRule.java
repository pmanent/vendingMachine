package com.simple.vending.rule;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simple.vending.exception.NotEnoughCreditException;
import com.simple.vending.exception.ValidationException;

/**
 * The Rule will throw an exception when the credit is lower than the price.
 * 
 * @author Pere Manent.
 *
 */
public class PriceRule implements Rule {
	/**
	 * Logger for the actual Class.
	 */
	private final Logger logger = LoggerFactory.getLogger(PriceRule.class);
	/**
	 * Total credit that contains the Machine.
	 */
	private BigInteger totalCredit;
	/**
	 * Product price that the user wants to buy.
	 */
	private BigInteger price;

	public PriceRule(BigInteger totalCredit, BigInteger price) {
		this.totalCredit = totalCredit;
		this.price = price;
	}

	@Override
	public void validate() throws ValidationException {
		logger.debug("Validation the Price and the Credit");
		if (this.totalCredit.compareTo(this.price) < 0) {
			logger.debug("Validation the Price. Not enought credit to buy the product");
			throw new NotEnoughCreditException();
		}
	}

}
