package com.simple.vending.command;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.simple.vending.domain.Coin.CoinType;

public class MergeCreditCoinsWithOwnedCoinsCommandTest {

	private MergeCreditCoinsWithOwnedCoinsCommand command;

	private List<CoinType> ownedCoins;

	private List<CoinType> creditCoinList;

	@Before
	public void beforTest() {
		this.ownedCoins = new ArrayList<CoinType>();
		this.creditCoinList = new ArrayList<CoinType>();
		this.ownedCoins.add(CoinType.EURO);
		this.creditCoinList.add(CoinType.EURO);

	}

	@Test
	public void mergeCreditCoinsWithOwnedCoinsCommandOKTest() {
		this.command = new MergeCreditCoinsWithOwnedCoinsCommand(this.ownedCoins, this.creditCoinList);
		this.command.execute();

		Assert.assertTrue("Owned Coins must have 2 Coins", this.ownedCoins.size() == 2);

	}

	@Test
	public void mergeCreditCoinsWithResetOwnedCoinsCommandOKTest() {
		this.ownedCoins.clear();
		this.command = new MergeCreditCoinsWithOwnedCoinsCommand(this.ownedCoins, this.creditCoinList);
		this.command.execute();

		Assert.assertTrue("Owned Coins must have 1 Coins", this.ownedCoins.size() == 1);

	}

	@Test
	public void mergeCreditCoinsWithOwnedCoinsCommandCreditOKTest() {
		this.command = new MergeCreditCoinsWithOwnedCoinsCommand(this.ownedCoins, this.creditCoinList);
		this.command.execute();

		Assert.assertTrue("Owned Coins must have 0 Coins", this.creditCoinList.size() == 0);
	}

}
