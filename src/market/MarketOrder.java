package market;

import restaurant.Restaurant;

public class MarketOrder {

	public enum orderState {open, processing, itemsFound, itemsDelivered, gaveToCustomer};
	MarketCustomerRole customer = null;
	Restaurant restaurant = null;
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

	MarketOrder(Restaurant restaurant, String item, int itemAmountOrdered) {
		this.restaurant = restaurant;
		this.item = item;
		this.itemAmountOrdered = itemAmountOrdered;
	}

}
