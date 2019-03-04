/**
 * 
 */
package com.simple.vending.command;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.simple.vending.domain.Coin.CoinType;
import com.simple.vending.domain.Product;
import com.simple.vending.domain.ProductImpl;
import com.simple.vending.exception.NotEnoughChangeException;
import com.simple.vending.exception.NotEnoughCreditException;

/**
 * 
 * @author Pere Manent
 *
 */
public class SelectProductCommandTest {

	/**
	 * Total of coins that the Machine has.
	 */
	private List<CoinType> ownedCoins;

	private BigInteger totalCredit;

	private Product product;

	@Before
	public void preloadTest() {
		this.product = new ProductImpl(new BigInteger("1"), "Name", "Description", new BigInteger("100"));
		this.totalCredit = new BigInteger("120");
		this.ownedCoins = new ArrayList<CoinType>();
	}

	@Test
	public void selectProductCommandOKTest() {
		this.ownedCoins.add(CoinType.TWENTY_CENTS);

		Command selectProductCommand = this.commandCreation();
		Boolean result = (Boolean) selectProductCommand.execute();

		Assert.assertTrue("The Product has not selected", result);
	}

	@Test
	public void selectProductCommandNotEnoughCreditTest() {
		this.totalCredit = new BigInteger("50");

		Command selectProductCommand = this.commandCreation();
		NotEnoughCreditException actualException = null;
		try {
			selectProductCommand.execute();
		} catch (NotEnoughCreditException e) {
			actualException = e;
		}

		Assert.assertNotNull("The actualException is not throwed", actualException);
	}

	@Test
	public void selectProductCommandNotEnoughChangeTest() {
		this.ownedCoins.add(CoinType.FIVE_CENTS);

		Command selectProductCommand = this.commandCreation();
		NotEnoughChangeException actualException = null;
		try {
			selectProductCommand.execute();
		} catch (NotEnoughChangeException e) {
			actualException = e;
		}

		Assert.assertNotNull("The actualException is not throwed", actualException);
	}

	private Command commandCreation() {
		Command command = new CanSelectProductCommand(this.ownedCoins, this.totalCredit, this.product);
		return command;
	}
}
