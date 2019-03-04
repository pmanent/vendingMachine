package com.simple.vending.command;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.simple.vending.domain.Coin.CoinType;
import com.simple.vending.exception.NotEnoughChangeException;

public class RetrieveChangeCommandTest {

	private List<CoinType> ownedCoins;

	@Before
	public void beforTest() {
		this.ownedCoins = new ArrayList<CoinType>();
		this.ownedCoins.add(CoinType.EURO);
		this.ownedCoins.add(CoinType.FIFTY_CENTS);
		this.ownedCoins.add(CoinType.FIVE_CENTS);
		this.ownedCoins.add(CoinType.FIVE_CENTS);
		this.ownedCoins.add(CoinType.FIVE_CENTS);
		this.ownedCoins.add(CoinType.FIVE_CENTS);
		this.ownedCoins.add(CoinType.FIVE_CENTS);
		this.ownedCoins.add(CoinType.FIVE_CENTS);
		this.ownedCoins.add(CoinType.FIVE_CENTS);
		this.ownedCoins.add(CoinType.FIVE_CENTS);
		this.ownedCoins.add(CoinType.FIVE_CENTS);
		this.ownedCoins.add(CoinType.FIVE_CENTS);

	}

	@Test
	public void RetriveChangeCommand50CentsOKTest() {
		RetrieveChangeCommand command = new RetrieveChangeCommand(this.ownedCoins, CoinType.FIFTY_CENTS.getCents());
		List<CoinType> coinList = (List<CoinType>) command.execute();

		Assert.assertTrue("The actual coin is Wrong", coinList.get(0) == CoinType.FIFTY_CENTS);
	}

	@Test
	public void RetriveChangeCommand1EurookTest() {
		RetrieveChangeCommand command = new RetrieveChangeCommand(this.ownedCoins, CoinType.EURO.getCents());
		List<CoinType> coinList = (List<CoinType>) command.execute();

		Assert.assertTrue("The actual coin is Wrong", coinList.get(0) == CoinType.EURO);
	}

	@Test
	public void RetriveChangeCommand110CentsokTest() {
		RetrieveChangeCommand command = new RetrieveChangeCommand(this.ownedCoins, BigInteger.valueOf(110));
		List<CoinType> coinList = (List<CoinType>) command.execute();

		Assert.assertTrue("The actual coin is Wrong", coinList.get(0) == CoinType.EURO);
		Assert.assertTrue("The actual coin is Wrong", coinList.get(1) == CoinType.FIVE_CENTS);
		Assert.assertTrue("The actual coin is Wrong", coinList.get(2) == CoinType.FIVE_CENTS);

	}

	@Test
	public void RetriveChangeCommand300CentsKOTest() {
		RetrieveChangeCommand command = new RetrieveChangeCommand(this.ownedCoins, BigInteger.valueOf(300));
		NotEnoughChangeException actualException = null;
		try {
			command.execute();
		} catch (NotEnoughChangeException e) {
			actualException = e;
		}

		Assert.assertNotNull("NotEnoughChangeException has to be catched", actualException);

	}

}
