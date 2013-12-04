package chineseRestaurant.interfaces;

import market.interfaces.SalesPerson;

public interface Cashier {
	public String getName();

	/**
	 * Messages
	 */
	public void msgComputeBill(String choice, int tableNumber, Waiter waiter);

	public void msgPayment(String choice, double amount, RestaurantCustomer customer);
	
	public void msgPleasePayForItems(String choice, int amount, double bill, SalesPerson market);
}
