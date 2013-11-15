package restaurant.interfaces;

import restaurant.Cashier;

public interface Market {
	
	public String getName();
	
	/**
	 * Messages
	 */
	public void msgOutofItems(String choice, int orderAmount);
	
	public void msgPayment(double payment, Cashier cashier);
}
