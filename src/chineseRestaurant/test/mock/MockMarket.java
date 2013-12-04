package chineseRestaurant.test.mock;

import chineseRestaurant.CashierRole;
import chineseRestaurant.interfaces.Market;
import testing.LoggedEvent;
import testing.Mock;

/**
 * A sample MockCustomer built to unit test a CashierAgent.
 *
 * @author Kristi Hupka
 *
 */

public class MockMarket extends Mock implements Market {

	public CashierRole cashierRole;

	public MockMarket(String name) {
		super(name);
	}

	public void msgOutofItems(String choice, int orderAmount) {
		log.add(new LoggedEvent("Received msgOutOfItems from cook. Choice: " + choice + "Order Amount: " + orderAmount));
	}

	public void msgPayment(double payment, CashierRole cashier) {
		log.add(new LoggedEvent("Received msgPayment from cashier. Payment: " + payment));
	}
}