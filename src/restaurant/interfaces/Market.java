package restaurant.interfaces;

import restaurant.CashierRole;

public interface Market {
	
	public String getName();
	
	/**
	 * Messages
	 */
	public void msgOutofItems(String choice, int orderAmount);
	
	public void msgPayment(double payment, CashierRole cashierRole);
}
