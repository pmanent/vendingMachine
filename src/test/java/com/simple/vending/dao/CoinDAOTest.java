package com.simple.vending.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.simple.vending.domain.Coin.CoinType;

/**
 * 
 * @author Pere Manent.
 *
 */
public class CoinDAOTest {

	private CoinDAO coinDAO;

	@Test
	public void insertCoinOKTest() {
		this.coinDAO = new CoinDAO();

		CoinType coin = CoinType.FIVE_CENTS;
		this.coinDAO.insertCoin(coin);
		List<CoinType> creditCoinList = this.coinDAO.getCreditCoinList();

		BigInteger expectedValue = BigInteger.valueOf(5);
		BigInteger actualValue = creditCoinList.get(0).getCents();

		Assert.assertEquals("The actual Value is " + actualValue, expectedValue, actualValue);
	}

	@Test
	public void returnCoinsOKTest() {

		this.coinDAO = new CoinDAO();
		CoinType coin = CoinType.FIVE_CENTS;
		this.coinDAO.insertCoin(coin);
		this.coinDAO.insertCoin(coin);
		List<CoinType> creditCoinList = this.coinDAO.getCreditCoinList();

		List<CoinType> returnedCoinList = this.coinDAO.returnCoins();

		Assert.assertEquals(
				"The size of the returned Coins are not the same of the credit List." + returnedCoinList.size(),
				creditCoinList.size(), returnedCoinList.size());
	}
}
