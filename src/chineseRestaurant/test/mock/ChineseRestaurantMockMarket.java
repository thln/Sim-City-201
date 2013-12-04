package chineseRestaurant.test.mock;

import chineseRestaurant.ChineseRestaurantCashierRole;
import chineseRestaurant.interfaces.ChineseRestaurantMarket;
import testing.LoggedEvent;
import testing.Mock;

/**
 * A sample MockCustomer built to unit test a CashierAgent.
 *
 * @author Kristi Hupka
 *
 */

public class ChineseRestaurantMockMarket extends Mock implements ChineseRestaurantMarket {

	public ChineseRestaurantCashierRole chineseRestaurantCashierRole;

	public ChineseRestaurantMockMarket(String name) {
		super(name);
	}

	public void msgOutofItems(String choice, int orderAmount) {
		log.add(new LoggedEvent("Received msgOutOfItems from cook. Choice: " + choice + "Order Amount: " + orderAmount));
	}

	public void msgPayment(double payment, ChineseRestaurantCashierRole cashier) {
		log.add(new LoggedEvent("Received msgPayment from cashier. Payment: " + payment));
	}
}