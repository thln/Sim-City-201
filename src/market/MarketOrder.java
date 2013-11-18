package market;

import restaurant.CookRole;

public class MarketOrder {

	public enum orderState {open, processing, itemsFound, gaveToCustomer};
	MarketCustomerRole customer = null;
	CookRole cookRole = null;
	String item;
	int totalItems;
	double orderCost;
	orderState state = orderState.open;

	MarketOrder(MarketCustomerRole customer, String item, int itemsWanted) {
		this.customer = customer;
		this.item = item;
		totalItems = itemsWanted;
	}

	MarketOrder(CookRole cookRole, String item, int itemsWanted) {
		this.cookRole = cookRole;
		this.item = item;
		totalItems = itemsWanted;
	}

}
