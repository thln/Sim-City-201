package italianRestaurant.test.mock;

import italianRestaurant.interfaces.*;

/**
 * A sample MockWaiter built to unit test a CashierAgent.
 *
 * @author Carmen Tan
 *
 */
public class MockItalianWaiter extends Mock implements ItalianWaiter {
	
	/**
	 * Reference to the Cashier under test that can be set by the unit test.
	 */
	public ItalianCashier cashier;
	private String name; 

	public EventLog log = new EventLog();
	
	public MockItalianWaiter(String name) {
		super(name);
		this.name = name;

	}
	
	public void msgHereIsCheck(ItalianCustomer c, Double total) {
		
	}
	public void msgOrderDone(String choice, int table) {
		
	}
	public void msgFoodOut(String choice, int table) {
		
	}
	
	public String toString() {
		return "waiter " + name;
	}

}
