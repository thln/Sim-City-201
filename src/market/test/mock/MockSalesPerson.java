package market.test.mock;

import restaurant.Restaurant;
import testing.Mock;
import market.MarketCustomerRole;
import market.MarketOrder;
import market.interfaces.SalesPerson;

public class MockSalesPerson extends Mock implements SalesPerson {


	public MockSalesPerson(String name) {
		super(name);
	}

	//Messages
	public void msgIWantProducts(MarketCustomerRole customer, String item, int numWanted) {
	}

	public void msgIWantProducts(Restaurant restaurant, String item, int numWanted) {
	}

	public void msgOrderFulfilled(MarketOrder o) {
	}

	public void msgOrderDelivered(MarketOrder o) {
	}

	public void msgPayment(MarketCustomerRole customer, double payment) {

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
