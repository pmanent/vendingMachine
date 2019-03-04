package com.simple.vending;

import java.math.BigInteger;
import java.util.List;

import com.simple.vending.domain.Coin.CoinType;
import com.simple.vending.domain.Product;

/**
 * Interface that will expose the methods to interact with the Vending Machine.
 *
 * @author Pere Manent
 */
public interface VendingMachine {
	/**
	 * With this method, the users can insert a Coin to the machine.
	 * 
	 * The acceptable coins are the objects defined to the Coins Domain.
	 */
	public void insertCoin(CoinType coin);

	/**
	 * After insert some coins, the user can recover all the coins with this method.
	 * The system will call to the Service to get all the coins.
	 * 
	 * @return
	 */
	public List<CoinType> returnCoins();

	/**
	 * When the user select a product, this method will trigger the select Product
	 * in the Service Layer. This method can Throw exceptions when the product
	 * cannot be delivered.
	 * 
	 * @param productId of the desired product.
	 */
	public Product selectProduct(BigInteger productId);

	/**
	 * After the product Selection is finished, the machine calculate the remaining
	 * change and return it to the client.
	 * 
	 * @param product
	 * @return
	 */
	public List<CoinType> finishPurchase(Product product);

	/**
	 * Method used by the supplier to refill the vending machine with new products.
	 * 
	 * @param id
	 * @param name
	 * @param description
	 * @param price
	 */
	public void addProduct(BigInteger id, String name, String description, BigInteger price);

	/**
	 * Method used by the supplier to refill coins for change.
	 * 
	 * @param coin
	 */
	public void addCoinsForChange(CoinType coin);

}
