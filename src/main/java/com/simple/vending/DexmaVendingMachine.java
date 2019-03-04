package com.simple.vending;

import java.math.BigInteger;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simple.vending.dao.CoinDAO;
import com.simple.vending.dao.ProductDAO;
import com.simple.vending.domain.Coin.CoinType;
import com.simple.vending.domain.Product;
import com.simple.vending.service.CoinService;
import com.simple.vending.service.ProductService;

public class DexmaVendingMachine implements VendingMachine {
	/**
	 * Logger for the actual Class.
	 */
	private final Logger logger = LoggerFactory.getLogger(DexmaVendingMachine.class);

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

	public DexmaVendingMachine(ProductDAO productDAO, CoinDAO coinDAO, CoinService coinService,
			ProductService productService) {
		super();
		this.productDAO = productDAO;
		this.coinDAO = coinDAO;
		this.coinService = coinService;
		this.productService = productService;
	}

	@Override
	public void insertCoin(CoinType coin) {
		this.coinService.insertCoin(coin);

	}

	@Override
	public List<CoinType> returnCoins() {
		return this.coinService.returnCoins();
	}

	@Override
	public List<CoinType> finishPurchase(Product product) {
		List<CoinType> change = this.coinService.retrieveChange(product);
		// After the change is calculated, the vending machine has to own the price of
		// the product
		this.coinService.mergeCreditCoindWithOwnedCoins(product);
		return change;
	}

	@Override
	public Product selectProduct(BigInteger productId) {
		return this.productService.selectProduct(productId);
	}

	@Override
	public void addProduct(BigInteger id, String name, String description, BigInteger price) {
		logger.debug("Adding actual productId: " + id);
		this.productService.add(id, name, description, price);
	}

	@Override
	public void addCoinsForChange(CoinType coin) {
		this.coinService.addCoinsForChange(coin);

	}

}
