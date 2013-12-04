package chineseRestaurant.test.mock;

import chineseRestaurant.interfaces.Cashier;
import chineseRestaurant.interfaces.RestaurantCustomer;
import chineseRestaurant.interfaces.Waiter;
import market.interfaces.SalesPerson;
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
