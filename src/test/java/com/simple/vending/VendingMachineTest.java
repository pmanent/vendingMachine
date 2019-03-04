package com.simple.vending;

import java.math.BigInteger;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.simple.vending.dao.CoinDAO;
import com.simple.vending.dao.ProductDAO;
import com.simple.vending.domain.Coin.CoinType;
import com.simple.vending.domain.Product;
import com.simple.vending.exception.NotEnoughChangeException;
import com.simple.vending.exception.NotEnoughCreditException;
import com.simple.vending.service.CoinService;
import com.simple.vending.service.ProductService;

public class VendingMachineTest {
	/**
	 * DAO exclusive for products.
	 */
	private ProductDAO productDAO;
	/**
	 * DAO exclusive for coins.
	 */
	private CoinDAO coinDAO;
	/**
	 * Access to the coin Services.
	 */
	private CoinService coinService;
	/**
	 * Access to the ProductService Services.
	 */
	private ProductService productService;

	private DexmaVendingMachine dexmaVendingMachine;

	@Before
	public void beforTest() {
		this.productDAO = new ProductDAO();
		this.coinDAO = new CoinDAO();
		this.coinService = new CoinService(this.coinDAO);
		this.productService = new ProductService(this.productDAO, this.coinService);

		this.dexmaVendingMachine = new DexmaVendingMachine(productDAO, coinDAO, coinService, productService);

		this.dexmaVendingMachine.addProduct(BigInteger.valueOf(1), "Coke", "Can of Coke", BigInteger.valueOf(150));
		this.dexmaVendingMachine.addProduct(BigInteger.valueOf(2), "Sprite", "Can of Sprite", BigInteger.valueOf(140));
		this.dexmaVendingMachine.addProduct(BigInteger.valueOf(3), "Water", "Still Water", BigInteger.valueOf(140));
	}

	@Test
	public void addCoinsForChangeOKTest() {
		// Firtst of all, add coins for change
		this.dexmaVendingMachine.addCoinsForChange(CoinType.TWO_EURO);
		this.dexmaVendingMachine.addCoinsForChange(CoinType.EURO);
		this.dexmaVendingMachine.addCoinsForChange(CoinType.FIFTY_CENTS);
		this.dexmaVendingMachine.addCoinsForChange(CoinType.TWENTY_CENTS);
		this.dexmaVendingMachine.addCoinsForChange(CoinType.TEN_CENTS);
		this.dexmaVendingMachine.addCoinsForChange(CoinType.TEN_CENTS);
		this.dexmaVendingMachine.addCoinsForChange(CoinType.FIFTY_CENTS);

		this.dexmaVendingMachine.insertCoin(CoinType.EURO);
		this.dexmaVendingMachine.insertCoin(CoinType.FIFTY_CENTS);
		this.dexmaVendingMachine.insertCoin(CoinType.TEN_CENTS);

		Product product = this.dexmaVendingMachine.selectProduct(BigInteger.valueOf(1));

		List<CoinType> change = this.dexmaVendingMachine.finishPurchase(product);

		Assert.assertTrue("The credit is not empty", this.coinService.getCreditCoinList().isEmpty());
		Assert.assertTrue("The owned List has to own nine Coins", this.coinService.getOwnedCoins().size() == 9);
		Assert.assertTrue("The product List has to own two Products", this.productDAO.getProductList().size() == 2);
		Assert.assertTrue("The change coins is incorrect", change.get(0) == CoinType.TEN_CENTS);

	}

	@Test
	public void selectProductTest() {
		this.dexmaVendingMachine.insertCoin(CoinType.EURO);
		this.dexmaVendingMachine.insertCoin(CoinType.FIFTY_CENTS);

		Product product = this.dexmaVendingMachine.selectProduct(BigInteger.valueOf(1));

		this.dexmaVendingMachine.finishPurchase(product);

		Assert.assertTrue("The credit is not empty", this.coinService.getCreditCoinList().isEmpty());
		Assert.assertTrue("The owned List has to own two Coins", this.coinService.getOwnedCoins().size() == 2);
		Assert.assertTrue("The product List has to own two Products", this.productDAO.getProductList().size() == 2);

	}

	@Test
	public void returnCoinsOKTest() {
		this.dexmaVendingMachine.insertCoin(CoinType.EURO);
		this.dexmaVendingMachine.insertCoin(CoinType.FIFTY_CENTS);

		List<CoinType> returnedCoins = this.dexmaVendingMachine.returnCoins();

		Assert.assertTrue("The credit is not empty", this.coinService.getCreditCoinList().isEmpty());
		Assert.assertTrue("The owned coins is not empty", this.coinService.getOwnedCoins().isEmpty());
		Assert.assertTrue("The number of returned coins is not 2", returnedCoins.size() == 2);

	}

	@Test
	public void notEnoughChangeExceptionKOTest() {
		// Firtst of all, add coins for change
		this.dexmaVendingMachine.addCoinsForChange(CoinType.TWO_EURO);

		this.dexmaVendingMachine.insertCoin(CoinType.EURO);
		this.dexmaVendingMachine.insertCoin(CoinType.EURO);
		NotEnoughChangeException actualException = null;
		try {
			this.dexmaVendingMachine.selectProduct(BigInteger.valueOf(1));
		} catch (NotEnoughChangeException e) {
			actualException = e;
		}

		Assert.assertNotNull("The NotEnoughChangeException has to be thrown", actualException);

	}

	@Test
	public void notEnoughCreditExceptionKOTest() {
		// Firtst of all, add coins for change
		this.dexmaVendingMachine.addCoinsForChange(CoinType.TWO_EURO);

		this.dexmaVendingMachine.insertCoin(CoinType.EURO);

		NotEnoughCreditException actualException = null;
		try {
			this.dexmaVendingMachine.selectProduct(BigInteger.valueOf(1));
		} catch (NotEnoughCreditException e) {
			actualException = e;
		}

		Assert.assertNotNull("The NotEnoughChangeException has to be thrown", actualException);

	}

}
