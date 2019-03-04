/**
 * 
 */
package com.simple.vending.command;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.simple.vending.command.RemoveProductCommand;
import com.simple.vending.domain.Product;
import com.simple.vending.domain.ProductImpl;
import com.simple.vending.exception.ValidationException;

/**
 * @author Pere Manent
 *
 */
public class RemoveProductCommandTest {
	private List<Product> productList;

	@Before
	public void beforTest() {

		Product product = new ProductImpl(BigInteger.valueOf(1), "Prodcut 1", "Prodcut 1 description",
				BigInteger.valueOf(100));
		this.productList = new ArrayList<Product>();
		this.productList.add(product);
	}

	@Test
	public void RemoveProductCommandOKTest() {
		RemoveProductCommand command = new RemoveProductCommand(this.productList, BigInteger.valueOf(1));

		Product product = (Product) command.execute();

		Assert.assertNotNull("The product is not found in the list", product);
	}

	@Test
	public void RemoveProductCommandKOTest() {
		RemoveProductCommand command = new RemoveProductCommand(this.productList, BigInteger.valueOf(2));

		Product product = (Product) command.execute();

		Assert.assertNull("The product is found in the list", product);
	}

	@Test
	public void RemoveProductCommandExceptionTest() {
		RemoveProductCommand command = new RemoveProductCommand(this.productList, null);

		ValidationException exception = null;
		try {
			command.execute();
		} catch (ValidationException e) {
			exception = e;
		}

		Assert.assertNotNull("The exception is not found in the list", exception);
	}

}
