package com.simple.vending.service;

import java.math.BigInteger;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simple.vending.command.Command;
import com.simple.vending.command.MergeCreditCoinsWithOwnedCoinsCommand;
import com.simple.vending.command.RetrieveChangeCommand;
import com.simple.vending.command.SumAllCoinsCommand;
import com.simple.vending.dao.CoinDAO;
import com.simple.vending.domain.Coin.CoinType;
import com.simple.vending.domain.Product;

/**
 * This class is a used to manage all the logic of the Coins.
 * 
 * @author Pere Manent
 */
public class CoinService {
	/**
	 * Logger for this Class.
	 */
	private final Logger logger = LoggerFactory.getLogger(CoinService.class);

	/**
	 * DAO exclusive for coins.
	 */
	private CoinDAO coinDAO;
	/**
	 * Constructor to inject the current coinDAO.
	 * @param coinDAO to inject.
	 */
	public CoinService(CoinDAO coinDAO) {
		this.coinDAO = coinDAO;
	}

	/**
	 * With this method, the users can insert a Coin to the machine.
	 * 
	 * The acceptable coins are the objects defined to the Coins Domain.
	 */
	public void insertCoin(CoinType coin) {
		logger.debug("Inserting coin with value " + coin.getCents());
		this.coinDAO.insertCoin(coin);
	}

	/**
	 * With this method, the supplier can insert a Coin to the machine and refill it
	 * for change..
	 * 
	 * The acceptable coins are the objects defined to the Coins Domain.
	 */
	public void addCoinsForChange(CoinType coin) {
		logger.debug("addCoinsForChange - Adding the coin with value " + coin.getCents());
		this.coinDAO.addCoinsForChange(coin);
	}

	/**
	 * After insert some coins, the user can recover all the coins with this method.
	 * The system will call to the Service to get all the coins.
	 * 
	 * @return
	 */
	public List<CoinType> returnCoins() {
		return this.coinDAO.returnCoins();
	}

	/**
	 * Returns the actual amount of money of the current Vending Machine via
	 * CoinDAO.
	 * 
	 * @return the ownedCoins
	 */
	public List<CoinType> getOwnedCoins() {
		return this.coinDAO.getOwnedCoins();
	}

	/**
	 * Returns the change to the user
	 * 
	 * @return the change
	 */
	public List<CoinType> retrieveChange(Product product) {

		RetrieveChangeCommand command = new RetrieveChangeCommand(this.getOwnedCoins(),
				this.sumAllCreditCoins().subtract(product.getPrice()));
		return (List<CoinType>) command.execute();
	}
	/**
	 * This method will merge the credit Coin list inside the OwnedCoinList.
	 * 
	 * After this execution, the creditCoinList has to be cleared.
	 * 
	 * @param product
	 */
	public void mergeCreditCoindWithOwnedCoins(Product product) {
		logger.debug("Executing mergeCreditCoindWithOwnedCoins");

		Command command = new MergeCreditCoinsWithOwnedCoinsCommand(this.getOwnedCoins(), this.getCreditCoinList());
		command.execute();
		// Clear the credit Coin List
		this.getCreditCoinList().clear();
	}

	/**
	 * Return the actual credit on the system via CoinDAO.
	 * 
	 * @return the creditCoinList
	 */
	public List<CoinType> getCreditCoinList() {
		return this.coinDAO.getCreditCoinList();
	}
	/**
	 * Sum all the price of the coins in the CreditCoinList.
	 * @return All the credit in Cents.
	 */
	public BigInteger sumAllCreditCoins() {
		return sumAllCoins(this.coinDAO.getCreditCoinList());
	}
	/**
	 * Sum all the price of the coins in the OwnedList.
	 * @return All the owned in Cents.
	 */
	public BigInteger sumAllOwnedCoins() {
		return sumAllCoins(this.coinDAO.getOwnedCoins());
	}
	/**
	 * Given a coin list, this command will Sum all the prices.
	 * @param coinList
	 * @return The Sum of the coins in Cents.
	 */
	private BigInteger sumAllCoins(List<CoinType> coinList) {
		Command sumCoinsCommand = new SumAllCoinsCommand(coinList);
		BigInteger sum = (BigInteger) sumCoinsCommand.execute();
		return sum;
	}

}
