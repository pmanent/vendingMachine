package com.simple.vending.dao;

import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.simple.vending.domain.Product;
import com.simple.vending.domain.ProductImpl;

public class ProductDAOTest {

	private ProductDAO productDAO;

	private Product product;

	private Product product2;

	@Before
	public void beforeTest() {
		this.productDAO = new ProductDAO();

		this.product = new ProductImpl(BigInteger.valueOf(1), "Product 1", "Product 1 description",
				BigInteger.valueOf(100));
		this.product2 = new ProductImpl(BigInteger.valueOf(2), "Product 2", "Product 2 description",
				BigInteger.valueOf(100));
	}

	@Test
	public void addProductTest() {
		this.productDAO.add(this.product);

		Assert.assertEquals("The Product is not added to the list", 1, this.productDAO.getProductList().size());

	}

	@Test
	public void removeProductOKTest() {

		this.productDAO.add(this.product);
		this.productDAO.remove(BigInteger.valueOf(1));

		Assert.assertEquals("The Product is not removed to the list", 0, this.productDAO.getProductList().size());
	}

	@Test
	public void removeProductKOTest() {
		this.productDAO.add(this.product);
		Product deleted = this.productDAO.remove(BigInteger.valueOf(2));

		Assert.assertNull("The product is deleted", deleted);

	}

	@Test
	public void getProductByIdOKTest() {
		this.productDAO.add(this.product);
		this.productDAO.add(this.product2);
		Product selectedProduct = this.productDAO.getProductById(BigInteger.valueOf(1));

		Assert.assertEquals("The Product is not added to the list", BigInteger.valueOf(1), selectedProduct.getId());

	}

	@Test
	public void getProductByIdKOTest() {
		this.productDAO.add(this.product);
		this.productDAO.add(this.product2);
		Product selectedProduct = this.productDAO.getProductById(BigInteger.valueOf(3));

		Assert.assertNull("The product is not found on the List", selectedProduct);

	}

	@Test
	public void getProductByIdWithEmptyListKOTest() {

		Product selectedProduct = this.productDAO.getProductById(BigInteger.valueOf(3));

		Assert.assertNull("The product is not found on the List", selectedProduct);

	}
}
