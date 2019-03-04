/**
 * 
 */
package com.simple.vending.command;

import java.math.BigInteger;
import java.util.List;

import com.simple.vending.domain.Coin.CoinType;
import com.simple.vending.exception.ValidationException;
import com.simple.vending.rule.MandatoryRule;

/**
 * This object will Sum all the coin added in the coinList.
 * 
 * @author Pere Manent
 *
 */
public class SumAllCoinsCommand extends CommandImpl {

	/**
	 * Constant attribute that hols the value of the Error Message.
	 */
	private static final String THE_CREDIT_COINT_LIST_IS_MANDATORY = "The credit coint List is Mandatory";
	/**
	 * The credit list will be user in the insert coin process.
	 */
	private List<CoinType> coinList;

	/**
	 * Constructor of the class, with the coin List and Rules generation.
	 * 
	 * @param coinList
	 */
	public SumAllCoinsCommand(List<CoinType> coinList) {
		this.coinList = coinList;
		this.addRule(new MandatoryRule(this.coinList, THE_CREDIT_COINT_LIST_IS_MANDATORY));
	}

	@Override
	public Object execute() throws ValidationException {
		this.validate();
		Integer totalSum = this.coinList.stream().mapToInt(actualCointType -> actualCointType.getCents().intValue())
				.sum();
		return new BigInteger(totalSum.toString());
	}

}
