package market.test.mock;

import application.Restaurant;
import chineseRestaurant.ChineseRestaurant;
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
		log.add(new LoggedEvent("Recieved msgOrderFulfilled"));
	}

	public void msgOrderDelivered(MarketOrder o) {
		log.add(new LoggedEvent("Recieved msgOrderDelivered"));
	}

	public void msgPayment(MarketCustomer customer, double payment) {
		log.add(new LoggedEvent("Recieved msgPayment"));
	}

	public void msgPayment(ChineseRestaurant chineseRestaurant, double payment) {
	}

	//Actions
	public void findItems(MarketOrder o) {
	}

	public void giveCustomerItems(MarketOrder o) {

	}

	public void askForPayment(MarketOrder o){

	}

}
