package market.test;

import person.Wealthy;
import market.MarketCustomerRole;
import market.test.mock.MockSalesPerson;
import junit.framework.*;


public class MarketCustomerTest extends TestCase {

	Wealthy wealthy;
	MarketCustomerRole marketCustomer;
	MockSalesPerson marketSalesPerson;

	/**
	 * This method is run before each test. You can use it to instantiate the class variables
	 * for your agent and mocks, etc.
	 */

	public void setUp() throws Exception {
		super.setUp();
		wealthy = new Wealthy("Wealthy Person", 10);
		marketCustomer = new MarketCustomerRole(wealthy, "Customer", "Market Customer");
		marketSalesPerson = new MockSalesPerson("MockSalesPerson");
	}


	public void testOneMarketCustomerPaymentNormativeScenerio() {
		try {
			setUp();
		} catch (Exception e) {
			e.printStackTrace();
		}

		//check preconditions
		assertEquals("MarketCustomer bill should be 0.00", marketCustomer.bill, 0.0);

		//step 1 of the test
		marketCustomer.msgHereAreYourThings("Ice Cream", 1, 4.99);//send the message from a SalesPerson

		//check postconditions for step 1 / preconditions for step 2
		assertEquals("MockSalesPerson should have an empty event log before the MarketCustomer's scheduler is called. Instead, the MockWaiter's event log reads: "
				+ marketSalesPerson.log.toString(), 0, marketSalesPerson.log.size());

		assertEquals("MarketCustomer should have logged \"Recieved msgHereAreYourThings\". Instead, the MarketCustomer's event log reads: "
				+ marketCustomer.log.toString(), true, marketCustomer.log.containsString("Recieved msgHereAreYourThings"));

		assertTrue("MarketCustomer's scheduler should have returned true (has an action to do on a bill from a SalesPerson), but didn't.", marketCustomer.pickAndExecuteAnAction());

		//After scheduler is called
		assertEquals("MockSalesPerson should not have an empty event log after the MarketCustomer's scheduler is called for the first time. It doesn't.", 1, marketSalesPerson.log.size());


		//check postconditions for step 1	
		
		assertEquals("MarketCustomer's person's money should have decreased", marketCustomer.person.money, (10-4.99));

		assertFalse("MarketCustomer's scheduler should have returned false (no actions left to do), but didn't.", 
				marketCustomer.pickAndExecuteAnAction());

	}// end testOneMarketCustomerPaymentNormativeScenerio
}