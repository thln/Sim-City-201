package market.interfaces;

import chineseRestaurant.Restaurant;
import market.MarketCustomerRole;
import market.MarketOrder;
import market.MarketOrder.orderState;

public interface SalesPerson {

	//Messages
	public void msgIWantProducts(MarketCustomer customer, String item,
			int numWanted);

	public void msgIWantProducts(Restaurant restaurant, String item,
			int numWanted);

	public void msgOrderFulfilled(MarketOrder o);

	public void msgOrderDelivered(MarketOrder o);

	public void msgPayment(MarketCustomer customer, double payment);

	public void msgPayment(Restaurant restaurant, double payment);

	//Actions
	public void findItems(MarketOrder o);

	public void giveCustomerItems(MarketOrder o);
	
	public void askForPayment(MarketOrder o);
}