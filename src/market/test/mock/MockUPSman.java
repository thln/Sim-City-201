package market.test.mock;

import market.MarketOrder;
import market.interfaces.UPSman;
import testing.Mock;

public class MockUPSman extends Mock implements UPSman {

	public MockUPSman(String name) {
		super(name);
	}

	//Messages
	public void msgDeliverOrder(MarketOrder o) {
	}

	//Actions
	public void deliverOrder(MarketOrder o) {
	}
}
