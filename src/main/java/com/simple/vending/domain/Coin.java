package com.simple.vending.domain;

import java.math.BigInteger;

/**
 * The objects of this interface will define the methods to manage the actual
 * coins domain.
 * 
 * @author Pere Manent
 */
public interface Coin {
	public enum CoinType {
		TWO_EURO("200"), EURO("100"), FIFTY_CENTS("50"), TWENTY_CENTS("20"), TEN_CENTS("10"), FIVE_CENTS("5");

		/**
		 * Object that holds the actual value of the coin in Cents
		 */
		private BigInteger cents;

		/**
		 * The actual currency of the system is only developed in Euro. In future
		 * development, this value must be variable.
		 */
		private String currency = "€";

		/**
		 * Constructor of the CoinType.
		 * 
		 * @param cents String that represents the actual value of the coin in cents.
		 */
		private CoinType(String cents) {
			this.cents = new BigInteger(cents);
		}

		/**
		 * Get method the return value of the type of the Coin
		 * 
		 * @return the cents
		 */
		public BigInteger getCents() {
			return cents;
		}

		/**
		 * Return the actual currency of the Coin type. Euro by Default.
		 * 
		 * @return the currency
		 */
		public String getCurrency() {
			return currency;
		}
	};
}
