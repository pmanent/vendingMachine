/**
 * 
 */
package com.simple.vending.service;

import java.math.BigInteger;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.simple.vending.dao.CoinDAO;
import com.simple.vending.domain.Coin.CoinType;

/**
 * @author Pere Manent.
 *
 */
public class CoinServiceTest {

	private CoinService coinService;

	@Before
	public void beforeTest() {
		this.coinService = new CoinService(new CoinDAO());
	}

	@Test
	public void insertCoinOKTest() {

		CoinType coin = CoinType.FIFTY_CENTS;
		this.coinService.insertCoin(coin);
		List<CoinType> creditCoinList = this.coinService.getCreditCoinList();

		BigInteger expectedValue = BigInteger.valueOf(50);
		BigInteger actualValue = creditCoinList.get(0).getCents();

		Assert.assertEquals("The actual Value is " + actualValue, expectedValue, actualValue);
	}

	@Test
	public void returnCoinsOKTest() {

		this.coinService.insertCoin(CoinType.FIFTY_CENTS);
		this.coinService.insertCoin(CoinType.EURO);

		List<CoinType> creditCoinList = this.coinService.getCreditCoinList();

		List<CoinType> returnedCoinList = this.coinService.returnCoins();

		Assert.assertEquals(
				"The size of the returned Coins are not the same of the credit List." + returnedCoinList.size(),
				creditCoinList.size(), returnedCoinList.size());
	}

	@Test
	public void sumAllCoinsOKTest() {

		this.coinService.insertCoin(CoinType.FIFTY_CENTS);
		this.coinService.insertCoin(CoinType.EURO);

		BigInteger sum = this.coinService.sumAllCreditCoins();

		BigInteger expected = new BigInteger("150");
		Assert.assertEquals(expected, sum);
	}

	@Test
	public void sumAllCoinsAfterRemoveCoinOKTest() {

		this.coinService.insertCoin(CoinType.FIFTY_CENTS);
		this.coinService.insertCoin(CoinType.EURO);

		this.coinService.returnCoins();

		BigInteger sum = this.coinService.sumAllCreditCoins();

		BigInteger expected = new BigInteger("0");
		Assert.assertEquals(expected, sum);
	}
}
