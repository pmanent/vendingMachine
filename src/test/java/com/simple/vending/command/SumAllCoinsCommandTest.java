package com.simple.vending.command;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.simple.vending.command.Command;
import com.simple.vending.command.SumAllCoinsCommand;
import com.simple.vending.domain.Coin.CoinType;
import com.simple.vending.exception.MandatoryException;
import com.simple.vending.exception.ValidationException;

public class SumAllCoinsCommandTest {
	private List<CoinType> coinList;

	@Before
	public void preloadTest() {
		this.coinList = new ArrayList<CoinType>();

		this.coinList.add(CoinType.TWO_EURO);
		this.coinList.add(CoinType.EURO);
		this.coinList.add(CoinType.FIFTY_CENTS);
		this.coinList.add(CoinType.TWENTY_CENTS);
		this.coinList.add(CoinType.TEN_CENTS);
		this.coinList.add(CoinType.TEN_CENTS);
		this.coinList.add(CoinType.FIVE_CENTS);
		this.coinList.add(CoinType.FIVE_CENTS);

	}

	@Test
	public void sumAllCoinsCommandOKTest() {
		Command command = new SumAllCoinsCommand(this.coinList);
		BigInteger sum = (BigInteger) command.execute();
		BigInteger expected = new BigInteger("400");
		Assert.assertEquals("Error on the Sum Value. The sum value is " + sum, expected, sum);
	}

	@Test
	public void sumAllCoinsCommandWith0ValueTest() {
		Command command = new SumAllCoinsCommand(new ArrayList<CoinType>());
		BigInteger sum = (BigInteger) command.execute();
		BigInteger expected = new BigInteger("0");
		Assert.assertEquals("Error on the Sum Value. The sum value is " + sum, expected, sum);
	}

	@Test
	public void sumAllCoinsCommandWithNullValueTest() {
		Command command = new SumAllCoinsCommand(null);
		ValidationException sumException = null;
		try {
			command.execute();
		} catch (MandatoryException e) {
			sumException = e;
		}
		Assert.assertNotNull("The actual exception must be catched", sumException);
	}

}
