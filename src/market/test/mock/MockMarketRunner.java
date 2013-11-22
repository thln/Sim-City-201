package market.test.mock;

import market.MarketOrder;
import market.interfaces.MarketRunner;
import testing.Mock;

public class MockMarketRunner extends Mock implements MarketRunner {

	MockMarketRunner(String name) {
		super(name);
	}

	//Messages
	public void msgHeresAnOrder(MarketOrder o) {
	}


	//Actions
	public void processOrder(MarketOrder o) {
	}

	public void decreaseInventoryBy(String item, int amount) {
	}
}
