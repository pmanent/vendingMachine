package com.simple.vending.domain;

import java.math.BigInteger;

/**
 * The objects of this interface will define the methods to manage the actual
 * Product domain.
 * 
 * We will use only the GET methods, because we don't need to modify the values
 * after creation.
 * 
 * @author Pere Manent
 */
public interface Product {

	/**
	 * @return the actual id of the product.
	 */
	BigInteger getId();

	/**
	 * @return the name of the product.
	 */
	String getName();

	/**
	 * @return the description of the actual Product.
	 */
	String getDescription();

	/**
	 * @return the price of the product in Cents (1 Euro = 100 cents)
	 */
	BigInteger getPrice();

}