package restaurant.test.mock;

import market.interfaces.SalesPerson;
import restaurant.interfaces.Cashier;
import restaurant.interfaces.RestaurantCustomer;
import restaurant.interfaces.Waiter;
import testing.EventLog;
import testing.LoggedEvent;
import testing.Mock;

public class MockCashier extends Mock implements Cashier {

	/**
	 * This is a Mock Cashier to serve for testing purposes. 
	 * @author Kristi Hupka
	 *
	 */
	
	public EventLog log = new EventLog();

	public MockCashier(String name) {
		super(name);
	}

	/**
	 * Messages
	 */
	public void msgComputeBill(String choice, int tableNumber, Waiter waiter) {

	}

	public void msgPayment(String choice, double amount, RestaurantCustomer customer) {

	}

	public void msgPleasePayForItems(String choice, int amount, double bill, SalesPerson market) {
		log.add(new LoggedEvent("Recieved msgPleasePayForItems"));
	}
}
