package italianRestaurant.test;

import italianRestaurant.*;
import italianRestaurant.test.mock.*;
import market.test.mock.*;
import junit.framework.TestCase;

/**
 * 
 * This class is a JUnit test class to unit test the ItalianRestaurantCashierRole's
 *  interactions with the new Market in SimCity201.
 *
 */

public class CityMarketTest extends TestCase{
	//these are instantiated for each test separately via the setUp() method.
	ItalianCashierRole cashier;
	MockItalianWaiter waiter;
	MockItalianCustomer customer;
	ItalianRestaurant restaurant;
	MockSalesPerson mockSalesPerson;
	
	public void setUp() throws Exception{
		super.setUp();
		cashier = new ItalianCashierRole("italianRestaurantCashier", restaurant);
		customer = new MockItalianCustomer("mockcustomer");		
		waiter = new MockItalianWaiter("mockwaiter");
		mockSalesPerson = new MockSalesPerson("mocksalesperson");
	}
	
	public void testOneNormalMarketScenario() {
		
	}
	
}
