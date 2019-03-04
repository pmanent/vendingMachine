package com.simple.vending.domain;

import java.math.BigInteger;

/**
 * Custom Implementation for the Product of the Simple Vending Machine. This
 * class responsibility is to hold the current properties of the product like
 * id, name, description and Price.
 * 
 * In a future implementation, this class can be an entity object and be saved
 * to a DB.
 * 
 * @author Pere Manent
 */
public class ProductImpl implements Product {

	/**
	 * Id of the product.
	 */
	private BigInteger id;
	/**
	 * Name of the product.
	 */
	private String name;
	/**
	 * Description of the product.
	 */
	private String description;
	/**
	 * Price of the product. The actual currency of this value is Euro and it's type
	 * is in cents of Euro
	 */
	private BigInteger price;
	/**
	 * Stock of the product. With this attribute, the system will control the number
	 * of items of this product are in the vending machine.
	 */
	private BigInteger stock;

	public ProductImpl(BigInteger id, String name, String description, BigInteger price) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	@Override
	public BigInteger getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public BigInteger getPrice() {
		return price;
	}

}
