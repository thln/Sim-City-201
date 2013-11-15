package market;

import restaurant.Cook;

public class MarketOrder {

	public enum orderState {open, processing, itemsFound, gaveToCustomer};
	MarketCustomer customer = null;
	Cook cook = null;
	String item;
	int totalItems;
	double orderCost;
	orderState state = orderState.open;

	MarketOrder(MarketCustomer customer, String item, int itemsWanted) {
		this.customer = customer;
		this.item = item;
		totalItems = itemsWanted;
	}

	MarketOrder(Cook cook, String item, int itemsWanted) {
		this.cook = cook;
		this.item = item;
		totalItems = itemsWanted;
	}

}
