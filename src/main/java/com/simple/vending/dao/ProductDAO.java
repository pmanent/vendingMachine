package com.simple.vending.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simple.vending.command.Command;
import com.simple.vending.command.GetProductByIdCommand;
import com.simple.vending.command.RemoveProductCommand;
import com.simple.vending.domain.Product;

/**
 * This is a Data Access Object. This class will handle the methods like add,
 * remove and get of the Products.
 * 
 * @author Pere Manent
 */
public class ProductDAO {

	private final Logger logger = LoggerFactory.getLogger(ProductDAO.class);

	private List<Product> productList;

	/**
	 * 
	 */
	public ProductDAO() {
		this.productList = new ArrayList<Product>();
	}

	public void add(Product product) {
		logger.debug("Adding actual product: " + product);
		this.productList.add(product);
	}

	public Product remove(BigInteger productId) {
		logger.debug("Removing actual product: " + productId);
		Command removeCommand = new RemoveProductCommand(this.productList, productId);
		return (Product) removeCommand.execute();
	}

	public Product getProductById(BigInteger productId) {
		logger.debug("Loading actual product: " + productId);
		Command command = new GetProductByIdCommand(this.getProductList(), productId);
		return (Product) command.execute();
	}

	/**
	 * @return the productList
	 */
	public List<Product> getProductList() {
		return productList;
	}

}
