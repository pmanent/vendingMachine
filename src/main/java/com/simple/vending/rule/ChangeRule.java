package com.simple.vending.rule;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simple.vending.command.RetrieveChangeCommand;
import com.simple.vending.domain.Coin.CoinType;
import com.simple.vending.exception.ValidationException;

/**
 * This Rule can validate the the Machine can return the remaining change.
 * 
 * The formula to validate the change is: (totalCredit - price) <=
 * totalOwnedCoins
 * 
 * @author Pere Manent.
 *
 */
public class ChangeRule implements Rule {
	/**
	 * Logger for the actual Class.
	 */
	private final Logger logger = LoggerFactory.getLogger(ChangeRule.class);
	/**
	 * Total of coins that the Machine has.
	 */
	private List<CoinType> ownedCoins;
	/**
	 * Total credit that contains the Machine.
	 */
	private BigInteger totalCredit;
	/**
	 * Product price that the user wants to buy.
	 */
	private BigInteger price;

	public ChangeRule(List<CoinType> ownedCoins, BigInteger totalCredit, BigInteger price) {
		super();
		this.ownedCoins = ownedCoins;
		this.totalCredit = totalCredit;
		this.price = price;
	}

	@Override
	public void validate() throws ValidationException {
		logger.debug("Validation the change to the user");
		BigInteger ammount = this.totalCredit.subtract(this.price);
		RetrieveChangeCommand command = new RetrieveChangeCommand(new ArrayList<CoinType>(ownedCoins), ammount);
		command.execute();

	}

}
