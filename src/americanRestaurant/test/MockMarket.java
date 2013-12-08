package restaurant.test.mock;

import restaurant.CashierAgent;
import restaurant.CookAgent;
import restaurant.CookAgent.Food;
import interfaces.Market;

public class MockMarket extends Mock implements Market {

	public MockMarket(String name) {
		super(name);
	}

	public CashierAgent cashier;
	
	@Override
	public void msgHereIsAnOrder(Food f1, AmericanRestaurantCookRole c1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgHereIsPayment(int bill) {
		log.add(new LoggedEvent("Received HereIsPayment with $" + bill));
	}

}
