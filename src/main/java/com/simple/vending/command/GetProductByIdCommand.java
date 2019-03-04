/**
 * 
 */
package com.simple.vending.command;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import com.simple.vending.domain.Product;
import com.simple.vending.exception.ValidationException;
import com.simple.vending.rule.MandatoryRule;

/**
 * This class is responsible to load the desired Product with the productId.
 * 
 * The systems needs the product List and the product Id. This two variables are mandatory.
 *
 * @author Pere Manent.
 *
 */
public class GetProductByIdCommand extends CommandImpl {
	/**
	 * Constant values. This values are used to return a message in a Exception.
	 */
	private static final String THE_PRODUCT_LIST_IS_MANDATORY = "The product List is Mandatory";

	private static final String THE_PRODUCT_ID_IS_MANDATORY = "The productId is Mandatory";

	/**
	 * Product list that holds all the products of the system.
	 */
	private List<Product> productList;
	/**
	 * Product Id to search in the productList.
	 */
	private BigInteger productId;

	public GetProductByIdCommand(List<Product> productList, BigInteger productId) {
		this.productList = productList;
		this.productId = productId;
		this.addRule(new MandatoryRule(this.productId, THE_PRODUCT_ID_IS_MANDATORY));
		this.addRule(new MandatoryRule(this.productList, THE_PRODUCT_LIST_IS_MANDATORY));
	}

	@Override
	public Object execute() throws ValidationException {
		Optional<Product> matchingObject = this.productList.stream()
				.filter(product -> product.getId().equals(this.productId)).findFirst();
		return matchingObject.orElse(null);
	}

}
