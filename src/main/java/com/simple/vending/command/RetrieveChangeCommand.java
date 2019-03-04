/**
 * 
 */
package com.simple.vending.command;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simple.vending.domain.Coin.CoinType;
import com.simple.vending.exception.NotEnoughChangeException;
import com.simple.vending.exception.ValidationException;

/**
 * This class responsibility is to calculate the total amount of coins the machine has to return.
 * 
 * 
 * @author Pere Manent
 *
 */
public class RetrieveChangeCommand extends CommandImpl {
	/**
	 * Logger for the actual Class.
	 */
	private final Logger logger = LoggerFactory.getLogger(RetrieveChangeCommand.class);

	/**
	 * The coins that the machine owns inside.
	 */
	private List<CoinType> ownedCoins;
	/**
	 * The total amount of money, that the machine has to return.
	 */
	private BigInteger ammount;

	public RetrieveChangeCommand(List<CoinType> ownedCoins, BigInteger ammount) {
		this.ownedCoins = ownedCoins;
		this.ammount = ammount;
	}

	@Override
	public List<CoinType> execute() throws ValidationException {
		logger.debug("Calculated Change " + ammount.toString());
		return this.generateChangeFromOwnedCoinList(this.ownedCoins, this.ammount);
	}
	/**
	 * Private method to calculate the numbers of coins to return.
	 * 
	 * NOTE: The complexity of this method must be refactored.
	 * 
	 * @param ownedCoins Total of coins that the machine has inside.
	 * @param ammount Total of money (in cents) the machine has to return
	 * @return List of CoinType
	 * @throws NotEnoughChangeException When the machine has not enough change to return, the system will throw this exception.
	 */
	private List<CoinType> generateChangeFromOwnedCoinList(List<CoinType> ownedCoins, BigInteger ammount)
			throws NotEnoughChangeException {
		//Change the the method will return
		List<CoinType> change = new ArrayList<CoinType>();
		//This temporal attribute is used to save the initiat state of the owned coins, in case it has to be reset
		List<CoinType> clonedCoins = new ArrayList<CoinType>(ownedCoins);
		//Integer value of the change returned to the user. This will control that the change is exact.
		BigInteger calculatedChange = BigInteger.ZERO;
		//Iteration of all possible of coins that the machine can return.
		for (CoinType coinType : CoinType.values()) {
			//Load the actual price of the coin
			BigInteger actualCoinCents = coinType.getCents();
			boolean finishAddCoin = false;
			//Try to add a coin while is possible
			while (!finishAddCoin) {
				calculatedChange = calculatedChange.add(actualCoinCents);
				//If we have passed
				if (calculatedChange.compareTo(ammount) > 0) {
					//Redo the calculated change and finish to try to add this coin to the change.
					calculatedChange = calculatedChange.subtract(actualCoinCents);
					finishAddCoin = true;
				} else {
					// if the list contains the coin remove it to the list, and added to the change.
					CoinType coinFromMachine = retrieveCoinFromList(ownedCoins, coinType);
					if (coinFromMachine != null) {
						ownedCoins.remove(coinFromMachine);
						change.add(coinFromMachine);
					} else {
						//If the coin is not found in the machine, redo the calcuated change and finish to try to add this coin.
						calculatedChange = calculatedChange.subtract(actualCoinCents);
						finishAddCoin = true;
					}
				}
			}
			
			//If the calculated change is exact, finish the Coins Iteration
			if (calculatedChange.compareTo(ammount) == 0) {
				break;
			}
		}
		if (calculatedChange.compareTo(ammount) < 0) {
			// Is not possible to return the change to the client.
			// Reset owned coins reference to the initial stage and throw the exception.
			ownedCoins.clear();
			ownedCoins.addAll(clonedCoins);
			throw new NotEnoughChangeException();
		}
		//Returns the exact change.
		return change;
	}
	/**
	 * Private method that look for a coin inside a List.
	 * @param ownedCoins List of Coins
	 * @param coinType Coin to find
	 * @return a CoinType
	 */
	private CoinType retrieveCoinFromList(List<CoinType> ownedCoins, CoinType coinType) {
		return ownedCoins.stream().filter(actualCoinType -> (actualCoinType == coinType)).findFirst().orElse(null);
	}

}
