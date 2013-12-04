package market;

import chineseRestaurant.Restaurant;
import market.interfaces.MarketCustomer;

public class MarketOrder {

	public enum orderState {open, processing, itemsFound, itemsDelivered, gaveToCustomer};
	MarketCustomer customer = null;
	Restaurant restaurant = null;
	String item;
	int itemAmountOrdered;
	int itemAmountFulfilled;
	double orderCost;
	public orderState state = orderState.open;

	public MarketOrder(MarketCustomer customer1, String item, int itemAmountOrdered) {
		this.customer = customer1;
		this.item = item;
		this.itemAmountOrdered = itemAmountOrdered;
	}

	public MarketOrder(Restaurant restaurant, String item, int itemAmountOrdered) {
		this.restaurant = restaurant;
		this.item = item;
		this.itemAmountOrdered = itemAmountOrdered;
	}

}
