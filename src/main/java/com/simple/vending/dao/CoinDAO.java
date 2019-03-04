package com.simple.vending.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simple.vending.domain.Coin.CoinType;

/**
 * This class will handle all the methods to Read and Delete coins in the coin
 * list.
 * 
 * The actual coins of the Vending Machine will be registered in memory in a Map
 * of coins.
 * 
 * 
 * @author Pere Manent
 *
 */
public class CoinDAO {

	/**
	 * Actual logger of the CoinDAO.
	 */
	private final Logger logger = LoggerFactory.getLogger(CoinDAO.class);

	/**
	 * Object that saves for each type of Coin, the amount that has in the inventory
	 * for each one. After the user select a product, the credit list will be added
	 * to this list.
	 */
	private List<CoinType> ownedCoins;
	/**
	 * The credit list will be user in the insert coin process.
	 */
	private List<CoinType> creditCoinList;

	/**
	 * Default Constructor. All the attributes will be generated.
	 */
	public CoinDAO() {
		logger.debug("Generating Coin DAO...");
		this.ownedCoins = new ArrayList<CoinType>();
		this.creditCoinList = new ArrayList<CoinType>();
	}

	/**
	 * Add the actual coin in the creditCoinList.
	 * 
	 * @param coin
	 */
	public void insertCoin(CoinType coin) {
		logger.debug("Inserting coin with value " + coin.getCents());
		this.creditCoinList.add(coin);
	}

	/**
	 * Add the actual coin in the owned coin list.
	 * 
	 * @param coin
	 */
	public void addCoinsForChange(CoinType coin) {
		logger.debug("Inserting coin with value " + coin.getCents());
		this.ownedCoins.add(coin);

	}

	/**
	 * After insert some coins, the user can recover all the coins with this method.
	 * The system will generate a cloned list and reset the actual creditCoinList.
	 * 
	 * @return clonedCreditCoinList
	 */
	public List<CoinType> returnCoins() {
		logger.debug("Executing Method of returning coins");
		List<CoinType> clonedCreditCoinList = new ArrayList<CoinType>(this.creditCoinList);
		this.creditCoinList = new ArrayList<CoinType>();
		return clonedCreditCoinList;
	}

	/**
	 * For information purposes, the system can return the owned Coins of the
	 * Vending Machine.
	 * 
	 * @return the ownedCoins
	 */
	public List<CoinType> getOwnedCoins() {
		return ownedCoins;
	}

	/**
	 * For information purposes, the system can return the credit Coins of the
	 * Vending Machine.
	 * 
	 * @return the creditCoinList
	 */
	public List<CoinType> getCreditCoinList() {
		return creditCoinList;
	}

}
