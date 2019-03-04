package com.simple.vending.service;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simple.vending.command.CanSelectProductCommand;
import com.simple.vending.command.Command;
import com.simple.vending.dao.ProductDAO;
import com.simple.vending.domain.Product;
import com.simple.vending.domain.ProductImpl;

/**
 * This class is a used to manage all the logic related with the Products.
 * 
 * @author Pere Manent
 */
public class ProductService {

	/**
	 * Logger for this Class.
	 */
	private final Logger logger = LoggerFactory.getLogger(CoinService.class);

	/**
	 * DAO exclusive for products.
	 */
	private ProductDAO productDAO;

	/**
	 * Access to the coin Services.
	 */
	private CoinService coinService;

	/**
	 * Constructor to inject the current DAO objects.
	 * 
	 * @param productDAO
	 * @param coinDAO
	 */
	public ProductService(ProductDAO productDAO, CoinService coinService) {
		this.productDAO = productDAO;
		this.coinService = coinService;
	}
	/**
	 * Add a new register of a product in the machine.
	 * This method is only used by the supplier.
	 * 
	 * @param id, must be unique. This unique validation is not implemented. 
	 * @param name, it is not unique, it is used only for information. 
	 * @param description, it is not unique, it is used only for information. 
	 * @param price in Cents. Must be greater than Zero.
	 */
	public void add(BigInteger id, String name, String description, BigInteger price) {
		logger.debug("Adding actual productId: " + id);
		this.productDAO.add(new ProductImpl(id, name, description, price));
	}

	/**
	 * This method will load the product, validate that the client can buy it and remove it if it's successful.
	 * 
	 * @param productId of the desired product.
	 */
	public Product selectProduct(BigInteger productId) {
		Product product = this.productDAO.getProductById(productId);

		Command command = new CanSelectProductCommand(this.coinService.getOwnedCoins(),
				this.coinService.sumAllCreditCoins(), product);
		// execute the command
		command.execute();

		this.productDAO.remove(productId);

		return product;
	}

}
