package market;

import chineseRestaurant.ChineseRestaurant;
import market.interfaces.MarketCustomer;

public class MarketOrder {

	public enum orderState {open, processing, itemsFound, itemsDelivered, gaveToCustomer};
	MarketCustomer customer = null;
	ChineseRestaurant chineseRestaurant = null;
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

	public MarketOrder(ChineseRestaurant chineseRestaurant, String item, int itemAmountOrdered) {
		this.chineseRestaurant = chineseRestaurant;
		this.item = item;
		this.itemAmountOrdered = itemAmountOrdered;
	}

}
