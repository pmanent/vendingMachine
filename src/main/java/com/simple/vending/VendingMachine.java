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
	 * 
	 * @param coin the will be added on the creditCoinlist.
	 */
	public void insertCoin(CoinType coin);

	/**
	 * After insert some coins, the user can recover all the coins with this method.
	 * The system will call to the Service to get all the coins.
	 * 
	 * @return the list of coins previously added.
	 */
	public List<CoinType> returnCoins();

	/**
	 * When the user select a product, this method will trigger the select Product
	 * in the Service Layer. This method can Throw exceptions when the product
	 * cannot be delivered.
	 * 
	 * @param productId of the desired product.
	 * @return the selected Poduct for the client.
	 */
	public Product selectProduct(BigInteger productId);

	/**
	 * After the product Selection is finished, the machine calculate the remaining
	 * change and return it to the client.
	 * 
	 * @param product
	 * @return list of coins that represents the change.
	 */
	public List<CoinType> finishPurchase(Product product);

	/**
	 * Method used by the supplier to refill the vending machine with new products.
	 * 
	 * @param id BigInteger, unique id.
	 * @param name String with the name of the Product
	 * @param description String with a short description of the product.
	 * @param price of the product represented in cents.
	 */
	public void addProduct(BigInteger id, String name, String description, BigInteger price);

	/**
	 * Method used by the supplier to refill coins for change.
	 * 
	 * @param coin the will be added on the ownedCoinlist.
	 */
	public void addCoinsForChange(CoinType coin);

}
