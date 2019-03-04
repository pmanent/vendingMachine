package com.simple.vending.command;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simple.vending.domain.Coin.CoinType;
import com.simple.vending.exception.ValidationException;

/**
 * This class will merge the credit Coin list inside the OwnedCoinList.
 * With this simple execution, we insert all the coins inside the machine.
 * 
 * @author Pere Manent
 *
 */
public class MergeCreditCoinsWithOwnedCoinsCommand extends CommandImpl {
	/**
	 * Actual logger of the CoinDAO.
	 */
	private final Logger logger = LoggerFactory.getLogger(MergeCreditCoinsWithOwnedCoinsCommand.class);

	/**
	 * After the user select a product, the credit list will be added to this list.
	 */
	private List<CoinType> ownedCoins;
	/**
	 * This credit of coins will be used to update the total Owned Coins.
	 */
	private List<CoinType> creditCoinList;

	public MergeCreditCoinsWithOwnedCoinsCommand(List<CoinType> ownedCoins, List<CoinType> creditCoinList) {
		this.ownedCoins = ownedCoins;
		this.creditCoinList = creditCoinList;
	}

	@Override
	public Object execute() throws ValidationException {
		logger.debug("Executing MergeCreditCoindWithOwnedCoinsCommand");
		this.ownedCoins.addAll(this.creditCoinList);
		this.creditCoinList.clear();
		return this.ownedCoins;
	}

}
