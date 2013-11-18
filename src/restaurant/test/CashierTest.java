package restaurant.test;

import restaurant.interfaces.Cashier;
import restaurant.test.mock.MockCustomer;
import restaurant.test.mock.MockMarket;
import restaurant.test.mock.MockWaiter;
import junit.framework.*;

/**
 * 
 * This class is a JUnit test class to unit test the CashierAgent's basic interaction
 * with waiters, customers, and the host.
 * It is provided as an example to students in CS201 for their unit testing lab.
 *
 * @author Kristi Hupka
 */
public class CashierTest extends TestCase {
	
	//these are instantiated for each test separately via the setUp() method.
	Cashier cashier;
	MockWaiter waiter;
	MockCustomer customer;
	MockCustomer customer2;
	MockMarket market1;
	MockMarket market2;
}
//
//
//	/**
//	 * This method is run before each test. You can use it to instantiate the class variables
//	 * for your agent and mocks, etc.
//	 */
//	public void setUp() throws Exception{
//		super.setUp();		
//		cashier = new CashierAgent("Cashier");		
//		customer = new MockCustomer("MockCustomer");
//		customer2 = new MockCustomer("MockCustomer2");
//		waiter = new MockWaiter("MockWaiter");
//		market1 = new MockMarket("MockMarket1");
//		market2 = new MockMarket("MockMarket2");
//	}
//
//	/**
//	 * This tests the cashier under terms: cashier must pay one market if full order is fulfilled
//	 */
//	public void testOneNormalMarketPaymentScenario() {
//		//setUp() runs first before this test!
//		market1.cashier = cashier;			
//
//		//check preconditions
//		assertEquals("Cashier should have 0 orders in OrdersToPay in it. It doesn't.", cashier.OrdersToPay.size(), 0);		
//
//		assertEquals("Cashier should have an empty event log before the Cashier's msgOrderFulfilled is called. Instead, the Cashier's event log reads: "
//				+ cashier.log.toString(), 0, cashier.log.size());
//
//		//step 1 of the test
//		cashier.msgOrderFulfilled("Pizza", 1, market1);//send the message from a market
//
//		//check postconditions for step 1 / preconditions for step 2
//		assertEquals("MockMarket should have an empty event log before the Cashier's scheduler is called. Instead, the MockWaiter's event log reads: "
//				+ market1.log.toString(), 0, market1.log.size());
//
//		assertEquals("Cashier should have logged \"Received msgOrderFulfilled from MockMarket\". Instead, the Cashier's event log reads: "
//				+ cashier.log.toString(), true, cashier.log.containsString("Received msgOrderFulfilled from " + market1.getName()));
//
//		assertEquals("Cashier should have 1 order in OrdersToPay. It doesn't.", cashier.OrdersToPay.size(), 1);
//
//		assertTrue("Cashier's scheduler should have returned true (has an action to do on a order from a market), but didn't.", cashier.pickAndExecuteAnAction());
//
//		//After scheduler is called
//		assertEquals("MockMarket should not have an empty event log after the Cashier's scheduler is called for the first time. It doesn't.", 1, market1.log.size());
//
//		assertEquals("Cashier should have 0 orders in OrderToPay. It doesn't.", cashier.OrdersToPay.size(), 0);
//
//		//step 2
//		//check postconditions for step 1
//		assertFalse("Cashier's scheduler should have returned false (no actions left to do), but didn't.", 
//				cashier.pickAndExecuteAnAction());
//	}//end one normal market payment scenario
//
//
//
//	/**
//	 * This tests the cashier under terms: cashier must pay two markets if partial order is fulfilled
//	 */
//	public void testTwoNormalMarketPaymentScenario() {
//		//setUp() runs first before this test!
//		market1.cashier = cashier;		
//		market2.cashier = cashier;
//
//		//check preconditions
//		assertEquals("Cashier should have 0 orders in OrdersToPay in it. It doesn't.", cashier.OrdersToPay.size(), 0);		
//
//		assertEquals("Cashier should have an empty event log before the Cashier's msgOrderFulfilled is called. Instead, the Cashier's event log reads: "
//				+ cashier.log.toString(), 0, cashier.log.size());
//
//		//step 1 of the test
//		cashier.msgOrderFulfilled("Pizza", 1, market1);//send the message from first market
//
//		//check postconditions for step 1 / preconditions for step 2
//		assertEquals("MockMarket should have an empty event log before the Cashier's scheduler is called. Instead, the MockWaiter's event log reads: "
//				+ market1.log.toString(), 0, market1.log.size());
//
//		assertEquals("Cashier should have logged \"Received msgOrderFulfilled from MockMarket\". Instead, the Cashier's event log reads: "
//				+ cashier.log.toString(), true, cashier.log.containsString("Received msgOrderFulfilled from " + market1.getName()));
//
//		assertEquals("Cashier should have 1 order in OrdersToPay. It doesn't.", cashier.OrdersToPay.size(), 1);
//
//		//step 2 of the test
//		cashier.msgOrderFulfilled("Pizza", 1, market2);//send the message from second market
//
//		//check postconditions for step 2 / preconditions for step 3
//		assertEquals("MockMarket1 should have an empty event log before the Cashier's scheduler is called. Instead, the MockWaiter's event log reads: "
//				+ market1.log.toString(), 0, market1.log.size());
//
//		assertEquals("MockMarket2 should have an empty event log before the Cashier's scheduler is called. Instead, the MockWaiter's event log reads: "
//				+ market1.log.toString(), 0, market2.log.size());
//
//		assertEquals("Cashier should have logged \"Received msgOrderFulfilled from MockMarket\". Instead, the Cashier's event log reads: "
//				+ cashier.log.toString(), true, cashier.log.containsString("Received msgOrderFulfilled from " + market2.getName()));
//
//		assertEquals("Cashier should have 2 orders in OrdersToPay. It doesn't.", cashier.OrdersToPay.size(), 2);
//
//		assertTrue("Cashier's scheduler should have returned true (has an action to do on a order from a market), but didn't.", cashier.pickAndExecuteAnAction());
//
//		//After scheduler is called
//		assertEquals("MockMarket1 should not have logged \"Received msgPayment from cashier. Payment: 8.99\" after the Cashier's scheduler is called for the first time. Instead, it reads: "
//				+ market1.log.toString(), true, market1.log.containsString("Received msgPayment from cashier. Payment: 8.99"));
//
//		assertEquals("Cashier should have 1 order in OrderToPay. It doesn't.", cashier.OrdersToPay.size(), 1);
//
//		//step 3
//		//scheduler is called again
//		assertTrue("Cashier's scheduler should have returned true (has an action to do on a order from a market), but didn't.", cashier.pickAndExecuteAnAction());
//
//		//After scheduler is called again
//		assertEquals("MockMarket2 should not have logged \"Received msgPayment from cashier. Payment: 8.99\" after the Cashier's scheduler is called for the first time. Instead, it reads: "
//				+ market2.log.toString(), true, market2.log.containsString("Received msgPayment from cashier. Payment: 8.99"));
//
//		assertEquals("Cashier should have 0 orders in OrderToPay. It doesn't.", cashier.OrdersToPay.size(), 0);
//
//		//step 4
//		//check postconditions for step 3
//		assertFalse("Cashier's scheduler should have returned false (no actions left to do), but didn't.", 
//				cashier.pickAndExecuteAnAction());
//	}//end two normal market payment scenario
//
//
//
//	/**
//	 * This tests the cashier under terms: one customer is ready to pay the exact check.
//	 */
//	public void testOneNormalCustomerPaymentScenario() {
//		//setUp() runs first before this test!
//		customer.cashier = cashier;//You can do almost anything in a unit test.			
//
//		//check preconditions
//		assertEquals("Cashier should have 0 checks in Checks. It doesn't.", cashier.Checks.size(), 0);		
//
//		assertEquals("Cashier should have an empty event log before the Cashier's msgComputeBill is called. Instead, the Cashier's event log reads: "
//				+ cashier.log.toString(), 0, cashier.log.size());
//
//		//step 1 of the test
//		cashier.msgComputeBill("Pizza", 1, waiter);//send the message from a waiter
//
//		//check postconditions for step 1 / preconditions for step 2
//		assertEquals("MockWaiter should have an empty event log before the Cashier's scheduler is called. Instead, the MockWaiter's event log reads: "
//				+ waiter.log.toString(), 0, waiter.log.size());
//
//		assertEquals("Cashier should have logged \"Calculating bill for table\". Instead, the Cashier's event log reads: "
//				+ cashier.log.toString(), true, cashier.log.containsString("Calculating bill for table"));
//
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
//	}//end one normal customer payment scenario
//
//
//
//	/**
//	 * This tests the cashier under terms: one customer does not have enough money to pay check.
//	 */
//	public void testOneCustomerCannotPayScenario() {
//		//setUp() runs first before this test!
//		customer.cashier = cashier;//You can do almost anything in a unit test.			
//
//		//check preconditions
//		assertEquals("Cashier should have 0 checks in Checks. It doesn't.", cashier.Checks.size(), 0);		
//
//		assertEquals("Cashier should have an empty event log before the Cashier's msgComputeBill is called. Instead, the Cashier's event log reads: "
//				+ cashier.log.toString(), 0, cashier.log.size());
//
//		//step 1 of the test
//		cashier.msgComputeBill("Pizza", 1, waiter);//send the message from a waiter
//
//		//check postconditions for step 1 / preconditions for step 2
//		assertEquals("MockWaiter should have an empty event log before the Cashier's scheduler is called. Instead, the MockWaiter's event log reads: "
//				+ waiter.log.toString(), 0, waiter.log.size());
//
//		assertEquals("Cashier should have logged \"Calculating bill for table\". Instead, the Cashier's event log reads: "
//				+ cashier.log.toString(), true, cashier.log.containsString("Calculating bill for table"));
//
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
//		cashier.msgPayment("Pizza", 0, customer);
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
//		assertTrue("Cashier payment should contain an amount for $0. It contains something else instead: $" 
//				+ cashier.Payments.get(0).payment , cashier.Payments.get(0).payment == 0);
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
//		assertEquals("Customer should have logged \"Received msgGoToJail from cashier\".  Instead the MockCustomer's event log reads: "
//				+ customer.log.toString(), true, customer.log.containsString("Received msgGoToJail from cashier."));
//
//		assertEquals("Cashier should have 0 payments in Payments. It doesn't.", 0, cashier.Payments.size());
//
//
//		//step 3
//		//check postconditions for step 2
//		assertFalse("Cashier's scheduler should have returned false (no actions left to do), but didn't.", 
//				cashier.pickAndExecuteAnAction());
//	}//end one customer cannot pay scenario
//
//
//
//	/**
//	 * This tests the cashier under terms: waiter asks to compute check, market fulfills order, customer pays check
//	 */
//	public void testOneCustomerPaymentOneMarketPaymentScenario(){
//		//setUp() runs first before this test!
//		customer.cashier = cashier;		
//		market1.cashier = cashier;
//
//		//check preconditions
//		assertEquals("Cashier should have 0 checks in Checks. It doesn't.", cashier.Checks.size(), 0);	
//
//		assertEquals("Cashier should have 0 orders in OrdersToPay. It doesn't.", cashier.OrdersToPay.size(), 0);
//
//		assertEquals("Cashier should have an empty event log before the Cashier's msgComputeBill is called. Instead, the Cashier's event log reads: "
//				+ cashier.log.toString(), 0, cashier.log.size());
//
//		//step 1 of the test
//		cashier.msgComputeBill("Pizza", 1, waiter);//send the message from a waiter
//
//		//check postconditions for step 1 / preconditions for step 2
//		assertEquals("MockWaiter should have an empty event log before the Cashier's scheduler is called. Instead, the MockWaiter's event log reads: "
//				+ waiter.log.toString(), 0, waiter.log.size());
//
//		assertEquals("Cashier should have logged \"Calculating bill for table\". Instead, the Cashier's event log reads: "
//				+ cashier.log.toString(), true, cashier.log.containsString("Calculating bill for table"));
//
//		assertEquals("Cashier should have 1 check in it. It doesn't.", cashier.Checks.size(), 1);
//
//		//step 2 of the test
//		cashier.msgOrderFulfilled("Pizza", 1, market1);//send the message from a market
//
//		//check postconditions for step 2 / preconditions for step 3
//		assertEquals("MockMarket should have an empty event log before the Cashier's scheduler is called. Instead, the MockWaiter's event log reads: "
//				+ market1.log.toString(), 0, market1.log.size());
//
//		assertEquals("Cashier should have logged \"Received msgOrderFulfilled from MockMarket\". Instead, the Cashier's event log reads: "
//				+ cashier.log.getLastLoggedEvent().toString(), true, cashier.log.containsString("Received msgOrderFulfilled from " + market1.getName()));
//
//		assertEquals("Cashier should have 1 order in OrdersToPay. It doesn't.", cashier.OrdersToPay.size(), 1);
//
//		//step 3
//		//After scheduler is called
//		//Cashier should deal with checks first before payments
//		assertTrue("Cashier's scheduler should have returned true (has an action to do on a bill from a waiter), but didn't.", cashier.pickAndExecuteAnAction());
//
//		assertEquals("MockWaiter should not have an empty event log after the Cashier's scheduler is called for the first time. It doesn't.", 1, waiter.log.size());
//
//		assertEquals("Cashier should have 0 checks in it. It doesn't.", cashier.Checks.size(), 0);
//
//		assertEquals("MockMarket should have an empty event log after Cashier's scheduler is called. Instead, the MockWaiter's event log reads: "
//				+ market1.log.toString(), 0, market1.log.size());
//
//		assertEquals("Cashier should have 1 order in OrdersToPay. It doesn't.", cashier.OrdersToPay.size(), 1);
//
//		//step 4
//		//After scheduler is called again
//		assertTrue("Cashier's scheduler should have returned true (has an action to do on a order from a market), but didn't.", cashier.pickAndExecuteAnAction());
//
//		assertEquals("MockMarket should not have an empty event log after the Cashier's scheduler is called for the first time. It doesn't.", 1, market1.log.size());
//
//		assertEquals("Cashier should have 0 orders in OrderToPay. It doesn't.", cashier.OrdersToPay.size(), 0);
//
//		//step 5
//		cashier.msgPayment("Pizza", 20, customer);
//
//		//check postconditions for step 5 / preconditions for step 6
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
//		//step 6
//		//check postconditions for step 6
//		assertFalse("Cashier's scheduler should have returned false (no actions left to do), but didn't.", 
//				cashier.pickAndExecuteAnAction());
//	}//end of one customer, one market payment scenario
//
//	
//	
//	/**
//	 * This tests the cashier under terms: waiter asks to compute check, market1 fulfills order, waiter asks to compute second check
//	 * customer1 pays, market 2 fulfills order, customer 2 cannot pay
//	 */
//	public void testTwoCustomerPaymentTwoMarketPaymentScenario(){
//		//setUp() runs first before this test!
//		customer.cashier = cashier;	
//		customer2.cashier = cashier;
//		market1.cashier = cashier;
//		market1.cashier = cashier;
//
//		//check preconditions
//		assertEquals("Cashier should have 0 checks in Checks. It doesn't.", cashier.Checks.size(), 0);	
//
//		assertEquals("Cashier should have 0 orders in OrdersToPay. It doesn't.", cashier.OrdersToPay.size(), 0);
//
//		assertEquals("Cashier should have an empty event log before the Cashier's msgComputeBill is called. Instead, the Cashier's event log reads: "
//				+ cashier.log.toString(), 0, cashier.log.size());
//
//		//step 1 of the test
//		cashier.msgComputeBill("Pizza", 1, waiter);//send the message from a waiter
//
//		//check postconditions for step 1 / preconditions for step 2
//		assertEquals("MockWaiter should have an empty event log before the Cashier's scheduler is called. Instead, the MockWaiter's event log reads: "
//				+ waiter.log.toString(), 0, waiter.log.size());
//
//		assertEquals("Cashier should have logged \"Calculating bill for table\". Instead, the Cashier's event log reads: "
//				+ cashier.log.toString(), true, cashier.log.containsString("Calculating bill for table"));
//
//		assertEquals("Cashier should have 1 check in it. It doesn't.", cashier.Checks.size(), 1);
//
//		//step 2 of the test
//		cashier.msgOrderFulfilled("Pizza", 1, market1);//send the message from a market
//
//		//check postconditions for step 2 / preconditions for step 3
//		assertEquals("MockMarket should have an empty event log before the Cashier's scheduler is called. Instead, the MockWaiter's event log reads: "
//				+ market1.log.toString(), 0, market1.log.size());
//
//		assertEquals("Cashier should have logged \"Received msgOrderFulfilled from MockMarket\". Instead, the Cashier's event log reads: "
//				+ cashier.log.getLastLoggedEvent().toString(), true, cashier.log.containsString("Received msgOrderFulfilled from " + market1.getName()));
//
//		assertEquals("Cashier should have 1 order in OrdersToPay. It doesn't.", cashier.OrdersToPay.size(), 1);
//
//		//step 3 of the test
//		cashier.msgComputeBill("Pizza", 1, waiter);//send the message from a waiter
//
//		//check postconditions for step 1 / preconditions for step 2
//		assertEquals("MockWaiter should have an empty event log before the Cashier's scheduler is called. Instead, the MockWaiter's event log reads: "
//				+ waiter.log.toString(), 0, waiter.log.size());
//
//		assertEquals("Cashier should have logged \"Calculating bill for table\". Instead, the Cashier's event log reads: "
//				+ cashier.log.toString(), true, cashier.log.containsString("Calculating bill for table"));
//
//		assertEquals("Cashier should have 2 checks in it. It doesn't.", cashier.Checks.size(), 2);
//
//
//		//step 4
//		//Cashier should deal with checks first before market payments
//		//call scheduler (deals with 1st check)
//		assertTrue("Cashier's scheduler should have returned true (has an action to do on a payment from customer), but didn't.", cashier.pickAndExecuteAnAction());
//
//		//After scheduler is called
//		assertEquals("MockWaiter should have 1 event log after the Cashier's scheduler is called for the first time. It doesn't.", 1, waiter.log.size());
//
//		assertEquals("Cashier should have 1 check in it. It doesn't.", cashier.Checks.size(), 1);
//
//		assertEquals("MockMarket should have an empty event log after Cashier's scheduler is called. Instead, the MockWaiter's event log reads: "
//				+ market1.log.toString(), 0, market1.log.size());
//
//		assertEquals("Cashier should have 1 order in OrdersToPay. It doesn't.", cashier.OrdersToPay.size(), 1);
//
//		//step 4
//		//call scheduler (deals with 2nd check)
//		assertTrue("Cashier's scheduler should have returned true (has an action to do on a payment from customer), but didn't.", cashier.pickAndExecuteAnAction());
//
//		//After scheduler is called again		
//		assertEquals("MockWaiter should have 2 events in log after the Cashier's scheduler is called for the second time. It doesn't.", 2, waiter.log.size());
//
//		assertEquals("MockMarket should have an empty event log after Cashier's scheduler is called. Instead, the MockWaiter's event log reads: "
//				+ market1.log.toString(), 0, market1.log.size());
//
//		assertEquals("Cashier should have 1 order in OrdersToPay. It doesn't.", cashier.OrdersToPay.size(), 1);
//
//		//step 5
//		//call scheduler (deals with market1 payment)
//		assertTrue("Cashier's scheduler should have returned true (has an action to do on a order from a market), but didn't.", cashier.pickAndExecuteAnAction());
//
//		//After scheduler is called again
//		assertEquals("MockMarket should not have an empty event log after the Cashier's scheduler is called again. It doesn't.", 1, market1.log.size());
//
//		assertEquals("Cashier should have 0 orders in OrderToPay. It doesn't.", cashier.OrdersToPay.size(), 0);
//
//		//step 6
//		cashier.msgPayment("Pizza", 20, customer);
//
//		assertEquals("MockWaiter should have logged \"Received HereIsCheck from cashier. Total = 8.99\". Instead, the MockWaiter's event log reads: "
//				+ waiter.log.toString(), true, waiter.log.containsString("Received HereIsCheck from cashier. Total = 8.99"));
//
//		assertEquals("Cashier should have 1 payment in Payments. It doesn't.", cashier.Payments.size(), 1);
//
//		assertTrue("Cashier should have logged \"Received payment from MockCustomer\" but didn't. His log reads instead: " 
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
//		//step 7
//		cashier.msgOrderFulfilled("Pizza", 1, market2);
//
//		assertEquals("MockMarket2 should have an empty event log before the Cashier's scheduler is called. Instead, the MockWaiter's event log reads: "
//				+ market1.log.toString(), 0, market2.log.size());
//
//		assertEquals("Cashier should have logged \"Received msgOrderFulfilled from MockMarket2\". Instead, the Cashier's event log reads: "
//				+ cashier.log.getLastLoggedEvent().toString(), true, cashier.log.containsString("Received msgOrderFulfilled from " + market2.getName()));
//
//		assertEquals("Cashier should have 1 order in OrdersToPay. It doesn't.", cashier.OrdersToPay.size(), 1);
//
//
//		//step 8
//		cashier.msgPayment("Pizza", 0, customer2);
//
//		assertEquals("MockWaiter should have logged \"Received HereIsCheck from cashier. Total = 8.99\". Instead, the MockWaiter's event log reads: "
//				+ waiter.log.getLastLoggedEvent().toString(), true, waiter.log.containsString("Received HereIsCheck from cashier. Total = 8.99"));
//
//		assertEquals("Cashier should have 2 payment in Payments. It doesn't.", cashier.Payments.size(), 2);
//
//		assertTrue("Cashier should have logged \"Recieved payment from MockCustomer2\" but didn't. His log reads instead: " 
//				+ cashier.log.getLastLoggedEvent().toString(), cashier.log.containsString("Received payment from " + customer2.getCustomerName()));
//
//		assertTrue("Cashier payment should contain a bill with the right choice in it. It doesn't.", 
//				cashier.Payments.get(1).choice == "Pizza");
//
//		assertTrue("Cashier payment should contain an amount for $0. It contains something else instead: $" 
//				+ cashier.Payments.get(1).payment , cashier.Payments.get(1).payment == 0);
//
//		assertTrue("Cashier payment should contain the right customer in it. It doesn't.", 
//				cashier.Payments.get(1).customer == customer2);
//
//		assertEquals("MockCustomer2 should have empty event log before Cashier's scheuler is called.  Instead it reads "
//				+ customer2.log.toString(), 0, customer2.log.size());
//
//		//step 9
//		//Cashier should deal with customer payments first before market payments
//		//call scheduler (deals with 1st customer payment)
//		assertTrue("Cashier's scheduler should have returned true (has an action to do on a payment from customer), but didn't.", cashier.pickAndExecuteAnAction());
//
//		//After scheduler is called
//		assertEquals("Customer should have logged \"Received msgHeresYourChange from cashier. Change: 11.01\".  Instead the MockCustomer's event log reads: "
//				+ customer.log.toString(), true, customer.log.containsString("Received msgHeresYourChange from cashier. Change: 11.01"));
//
//		assertEquals("Cashier should have 1 payment in Payments. It doesn't.", 1, cashier.Payments.size());
//
//		//step 10
//		//call scheduler (deals with 2nd customer payment -- cannot pay)
//		assertTrue("Cashier's scheduler should have returned true (has an action to do on a payment from customer), but didn't.", cashier.pickAndExecuteAnAction());
//
//		//After scheduler is called
//		assertEquals("Customer should have logged \"Received msgGoToJail from cashier\".  Instead the MockCustomer's event log reads: "
//				+ customer2.log.toString(), true, customer2.log.containsString("Received msgGoToJail from cashier."));
//
//		assertEquals("Cashier should have 0 payments in Payments. It doesn't.", 0, cashier.Payments.size());
//
//
//		//step 11
//		//call scheduler (deals with market2 payment)
//		assertTrue("Cashier's scheduler should have returned true (has an action to do on a order from a market), but didn't.", cashier.pickAndExecuteAnAction());
//
//		//After scheduler is called again
//		assertEquals("MockMarket should not have an empty event log after the Cashier's scheduler is called again. It doesn't.", 1, market2.log.size());
//
//		assertEquals("Cashier should have 0 orders in OrderToPay. It doesn't.", cashier.OrdersToPay.size(), 0);
//
//		//step 12
//		//check postconditions for step 11
//		assertFalse("Cashier's scheduler should have returned false (no actions left to do), but didn't.", 
//				cashier.pickAndExecuteAnAction());
//	}//end of Two Customer (One customer cannot pay), Two Market Payment Scenario

