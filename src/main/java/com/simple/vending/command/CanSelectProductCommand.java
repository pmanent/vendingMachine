/**
 * 
 */
package com.simple.vending.command;

import java.math.BigInteger;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simple.vending.domain.Coin.CoinType;
import com.simple.vending.domain.Product;
import com.simple.vending.exception.ValidationException;
import com.simple.vending.rule.ChangeRule;
import com.simple.vending.rule.PriceRule;
import com.simple.vending.rule.Rule;

/**
 * With this command, the vending machine, can validate if the product can be
 * sold. We will focus on the price of the product. If the credit of the user is
 * equals or greater than the Price of the product, then no exceptions will be
 * thrown. The Process will thrown an exception when the credit is not enough.
 * 
 * @author Pere Manent.
 *
 */
public class CanSelectProductCommand extends CommandImpl {
	/**
	 * Logger for the actual Class.
	 */
	private final Logger logger = LoggerFactory.getLogger(CanSelectProductCommand.class);
	/**
	 * Total of coins that the Machine has.
	 */
	private List<CoinType> ownedCoins;
	/**
	 * Total credit that contains the Machine.
	 */
	private BigInteger totalCredit;
	/**
	 * Product that the user wants to buy.
	 */
	private Product product;

	/**
	 * Constructor of the Command. Total of coins, Total credit and the product are mandatory.
	 */
	public CanSelectProductCommand(List<CoinType> ownedCoins, BigInteger totalCredit, Product product) {
		
		this.ownedCoins = ownedCoins;
		this.totalCredit = totalCredit;
		this.product = product;
		Rule priceRule = new PriceRule(this.totalCredit, this.product.getPrice());
		Rule changeRule = new ChangeRule(this.ownedCoins, this.totalCredit, this.product.getPrice());
		this.addRule(priceRule);
		this.addRule(changeRule);
	}

	/**
	 * This method has to validate that the user inserted the correct amount of
	 * money to buy the product.
	 * It is mandatory for the user, to insert the correct amount of money to buy the product.
	 * It is mandatory for the system, to has enough change after the user pay the product.
	 * 
	 */
	@Override
	public Object execute() throws ValidationException {
		
		super.validate();

		return true;
	}

}
