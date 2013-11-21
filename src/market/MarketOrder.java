package market;

import restaurant.CookRole;

public class MarketOrder {

	public enum orderState {open, processing, itemsFound, gaveToCustomer};
	MarketCustomerRole customer = null;
	CookRole cookRole = null;
	String item;
	int itemAmountOrdered;
	int itemAmountFulfilled;
	double orderCost;
	orderState state = orderState.open;

	MarketOrder(MarketCustomerRole customer, String item, int itemAmountOrdered) {
		this.customer = customer;
		this.item = item;
		this.itemAmountOrdered = itemAmountOrdered;
	}

	MarketOrder(CookRole cookRole, String item, int itemAmountOrdered) {
		this.cookRole = cookRole;
		this.item = item;
		this.itemAmountOrdered = itemAmountOrdered;
	}

}
