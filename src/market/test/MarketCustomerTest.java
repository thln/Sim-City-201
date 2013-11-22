package market.test;

import market.MarketCustomerRole;
import market.test.mock.MockSalesPerson;
import junit.framework.*;


public class MarketCustomerTest extends TestCase {

	MarketCustomerRole marketCustomer;
	MockSalesPerson marketSalesPerson;
	
	/**
	 * This method is run before each test. You can use it to instantiate the class variables
	 * for your agent and mocks, etc.
	 */

	public void setUp() throws Exception {
		super.setUp();
		marketCustomer = new MarketCustomerRole(null, "Customer", "Market Customer");
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

		assertEquals("MarketCustomer should have logged \"Recieved msgHereAreYourThings\". Instead, the Cashier's event log reads: "
				+ marketCustomer.log.toString(), true, marketCustomer.log.containsString("Recieved msgHereAreYourThings"));

//		assertEquals("Cashier should have 1 check in it. It doesn't.", cashier.Checks.size(), 1);
//
//		assertTrue("Cashier's scheduler should have returned true (has an action to do on a bill from a waiter), but didn't.", cashier.pickAndExecuteAnAction());
//
//		//After scheduler is called
//		assertEquals("MockWaiter should not have an empty event log after the Cashier's scheduler is called for the first time. It doesn't.", 1, waiter.log.size());
//
//		assertEquals("Cashier should have 0 checks in it. It doesn't.", cashier.Checks.size(), 0);
//
//		//step 2 of the test
//		cashier.msgPayment("Pizza", 20, customer);
//
//		//check postconditions for step 2 / preconditions for step 3
//		assertEquals("MockWaiter should have logged \"Received HereIsCheck from cashier. Total = 8.99\". Instead, the MockWaiter's event log reads: "
//				+ waiter.log.toString(), true, waiter.log.containsString("Received HereIsCheck from cashier. Total = 8.99"));
//
//		assertEquals("Cashier should have 1 payment in Payments. It doesn't.", cashier.Payments.size(), 1);
//
//		assertTrue("Cashier should have logged \"\" but didn't. His log reads instead: " 
//				+ cashier.log.getLastLoggedEvent().toString(), cashier.log.containsString("Received payment from " + customer.getCustomerName()));
//
//		assertTrue("Cashier payment should contain a bill with the right choice in it. It doesn't.", 
//				cashier.Payments.get(0).choice == "Pizza");
//
//		assertTrue("Cashier payment should contain an amount for $20.00. It contains something else instead: $" 
//				+ cashier.Payments.get(0).payment , cashier.Payments.get(0).payment == 20);
//
//		assertTrue("Cashier payment should contain the right customer in it. It doesn't.", 
//				cashier.Payments.get(0).customer == customer);
//
//		assertEquals("MockCustomer should have empty event log before Cashier's scheuler is called.  Instead it reads "
//				+ customer.log.toString(), 0, customer.log.size());
//
//		assertTrue("Cashier's scheduler should have returned true (has an action to do on a payment from customer), but didn't.", cashier.pickAndExecuteAnAction());
//
//		//After scheduler is called
//		assertEquals("Customer should have logged \"Received msgHeresYourChange from cashier. Change: 11.01\".  Instead the MockCustomer's event log reads: "
//				+ customer.log.toString(), true, customer.log.containsString("Received msgHeresYourChange from cashier. Change: 11.01"));
//
//		assertEquals("Cashier should have 0 payments in Payments. It doesn't.", 0, cashier.Payments.size());
//
//
//		//step 3
//		//check postconditions for step 2
//		assertFalse("Cashier's scheduler should have returned false (no actions left to do), but didn't.", 
//				cashier.pickAndExecuteAnAction());
		
	}// end testOneMarketCustomerPaymentNormativeScenerio
}
