/**
 * 
 */
package com.simple.vending.command;

import java.math.BigInteger;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simple.vending.domain.Product;
import com.simple.vending.exception.ValidationException;
import com.simple.vending.rule.MandatoryRule;

/**
 * This class will remove a Product of the system. It is mandataory to have the product List and the ProductId.
 * 
 * @author Pere Manent
 *
 */
public class RemoveProductCommand extends CommandImpl {

	private static final String PRODUCT_LIST_IS_MANDATORY = "Product List is Mandatory";
	private static final String PRODUCT_ID_IS_MANDATORY = "ProductID is Mandatory";

	private final Logger logger = LoggerFactory.getLogger(RemoveProductCommand.class);
	/**
	 * Product list that holds all the products of the system.
	 */
	private List<Product> productList;
	/**
	 * Product Id to search in the productList.
	 */
	private BigInteger productId;

	public RemoveProductCommand(List<Product> productList, BigInteger productId) {
		this.productList = productList;
		this.productId = productId;
		this.addRule(new MandatoryRule(productId, PRODUCT_ID_IS_MANDATORY));
		this.addRule(new MandatoryRule(productList, PRODUCT_LIST_IS_MANDATORY));
	}

	@Override
	public Object execute() throws ValidationException {
		logger.debug("Executing command RemoveProductCommand");
		super.validate();
		
		Product productToRemove = this.searchProductInList();
		if (productToRemove != null) {
			this.productList.remove(productToRemove);
		}

		return productToRemove;
	}
	/**
	 * This method is used to find the product via GetProductByIdCommand.java
	 * 
	 * @return the Product searched
	 */
	private Product searchProductInList() {
		Command command = new GetProductByIdCommand(this.productList, this.productId);
		return (Product) command.execute();
	}

}
