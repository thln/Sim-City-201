package market.test.mock;

import market.interfaces.MarketCustomer;
import testing.Mock;

public class MockMarketCustomer extends Mock implements MarketCustomer {


	public MockMarketCustomer(String name) {
		super(name);
	}

	//Messages
	public void msgHereAreYourThings(String item, int itemAmount, double orderCost) {
	}

	//Actions
	public void payBill(){
	}
	
	public void exitMarket() {
	}
}
