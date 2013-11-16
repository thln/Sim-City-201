package restaurant.test.mock;

import restaurant.CashierRole;
import restaurant.interfaces.Market;

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
//
//	public void msgOutofItems(String choice, int orderAmount) {
//		log.add(new LoggedEvent("Received msgOutOfItems from cook. Choice: " + choice + "Order Amount: " + orderAmount));
//	}
//
//	public void msgPayment(double payment, CashierAgent cashier) {
//		log.add(new LoggedEvent("Received msgPayment from cashier. Payment: " + payment));
//	}
}