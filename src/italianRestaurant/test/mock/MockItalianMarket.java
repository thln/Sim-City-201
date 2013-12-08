package italianRestaurant.test.mock;

import italianRestaurant.interfaces.*;

/**
 * A sample MockMarket built to unit test a AmericanRestaurantCashierRole.
 *
 * @author Carmen Tan
 *
 */
public class MockItalianMarket extends Mock implements ItalianMarket {
	
	/**
	 * Reference to the AmericanRestaurantCashier under test that can be set by the unit test.
	 */
	public ItalianCashier cashier;
	private String name; 

	public EventLog log = new EventLog();
	
	public MockItalianMarket(String name) {
		super(name);
		this.name = name;

	}
	
	public void msgOrderforMarket(ItalianCook c, String choice, int amount) {
		
	}
	
	public void msgHereIsPayment(ItalianCashier cashier, Double payment) {
		
	}
	
	public void msgCashierStillOwes(ItalianCashier cashier, Double leftover) {
		
	}
	
	public String toString() {
		return "market " + name;
	}

}
