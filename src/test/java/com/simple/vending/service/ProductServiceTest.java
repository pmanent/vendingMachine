/**
 * 
 */
package com.simple.vending.service;

import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.simple.vending.dao.CoinDAO;
import com.simple.vending.dao.ProductDAO;
import com.simple.vending.domain.Coin.CoinType;
import com.simple.vending.domain.Product;
import com.simple.vending.exception.NotEnoughChangeException;
import com.simple.vending.exception.NotEnoughCreditException;

/**
 * @author peremanent
 *
 */
public class ProductServiceTest {

	private ProductDAO productDAO;

	private CoinDAO coinDAO;

	private ProductService productService;

	private CoinService coinService;

	@Before
	public void beforeTest() {
		this.productDAO = new ProductDAO();
		this.coinDAO = new CoinDAO();
		this.coinService = new CoinService(this.coinDAO);
		this.productService = new ProductService(this.productDAO, this.coinService);

		this.productService.add(BigInteger.valueOf(1), "Coke", "Can of Coke", BigInteger.valueOf(150));
		this.productService.add(BigInteger.valueOf(2), "Sprite", "Can of Sprite", BigInteger.valueOf(140));
		this.productService.add(BigInteger.valueOf(3), "Water", "Still Water", BigInteger.valueOf(140));
	}

	@Test
	public void selectProductOKTest() {
		this.coinService.insertCoin(CoinType.EURO);
		this.coinService.insertCoin(CoinType.FIFTY_CENTS);

		Product product = this.productService.selectProduct(BigInteger.valueOf(1));

		Assert.assertEquals(BigInteger.valueOf(1), product.getId());

	}

	@Test
	public void selectProductNotEnoughCreditKOTest() {
		this.coinService.insertCoin(CoinType.EURO);
		NotEnoughCreditException actualException = null;
		try {
			this.productService.selectProduct(BigInteger.valueOf(1));
		} catch (NotEnoughCreditException e) {
			actualException = e;
		}
		Assert.assertNotNull("The exception must be throwed", actualException);
	}

	@Test
	public void selectProductNotEnoughChangeKOTest() {
		this.coinService.insertCoin(CoinType.TWO_EURO);
		NotEnoughChangeException actualException = null;
		try {
			this.productService.selectProduct(BigInteger.valueOf(1));
		} catch (NotEnoughChangeException e) {
			actualException = e;
		}
		Assert.assertNotNull("The exception must be throwed", actualException);
	}
}
