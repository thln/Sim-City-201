package market.test.mock;

import restaurant.Restaurant;
import testing.EventLog;
import testing.LoggedEvent;
import testing.Mock;
import market.MarketCustomerRole;
import market.MarketOrder;
import market.interfaces.MarketCustomer;
import market.interfaces.SalesPerson;

public class MockSalesPerson extends Mock implements SalesPerson {

	public EventLog log = new EventLog();
	
	public MockSalesPerson(String name) {
		super(name);
	}

	//Messages
	public void msgIWantProducts(MarketCustomer customer, String item, int numWanted) {
	}

	public void msgIWantProducts(Restaurant restaurant, String item, int numWanted) {
	}

	public void msgOrderFulfilled(MarketOrder o) {
	}

	public void msgOrderDelivered(MarketOrder o) {
	}

	public void msgPayment(MarketCustomer customer, double payment) {
		log.add(new LoggedEvent("Recieved msgPayment"));
	}

	public void msgPayment(Restaurant restaurant, double payment) {
	}

	//Actions
	public void findItems(MarketOrder o) {
	}

	public void giveCustomerItems(MarketOrder o) {

	}

	public void askForPayment(MarketOrder o){

	}

}
