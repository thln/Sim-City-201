package bank.test;

import bank.mock.BankCustomerMock;
import junit.framework.TestCase;

public class TellerTest extends TestCase 
{
	BankCustomerMock customer;
	
	public void setUp() throws Exception {
		super.setUp();
		customer = new BankCustomerMock("mockcustomer");
	}
	/*
	 * package restaurant.test;



	 *
	 * 
	 * This class is a JUnit test class to unit test the CashierAgent's basic interaction
	 * with waiters, customers, and the host.
	 * It is provided as an example to students in CS201 for their unit testing lab.
	 *
	 * @author Monroe Ekilah

public class CashierTest extends TestCase
{
	//these are instantiated for each test separately via the setUp() method.
	CashierAgent cashier;
	MockWaiter waiter;
	MockCustomer customer;
	MockHost host;
	MockMarket market;

	/**
	 * This method is run before each test. You can use it to instantiate the class variables
	 * for your agent and mocks, etc.

	public void setUp() throws Exception{
		super.setUp();    
		host = new MockHost("mockhost");
		cashier = new CashierAgent(host);                
		customer = new MockCustomer("mockcustomer");                
		waiter = new MockWaiter("mockwaiter"); 	
		market = new MockMarket("mockmarket");
	}        
	/**
	 * This tests the cashier under very simple terms: one customer is ready to pay the exact bill.

	public void testOneNormalCustomerScenario()
	{
		//setUp() runs first before this test!
		try {
			setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		customer.cashier = cashier;//You can do almost anything in a unit test.                        

		//check preconditions
		assertEquals("Cashier should have 0 bills in it. It doesn't.",cashier.getChecks().size(), 0);                
		assertEquals("CashierAgent should have an empty event log before the Cashier's HereIsBill is called. Instead, the Cashier's event log reads: "
				+ cashier.log.toString(), 0, cashier.log.size());

		int check = 20;
		int cash = 30;

		//step 1 of the test

		cashier.msgComputeBill(waiter, customer, "steak");//send the message from a waiter


		//check postconditions for step 1 and preconditions for step 2
		assertEquals("MockWaiter should have an empty event log before the Cashier's scheduler is called. Instead, the MockWaiter's event log reads: "
				+ waiter.log.toString(), 0, waiter.log.size());

		assertEquals("Cashier should have 1 bill in it. It doesn't.", cashier.getChecks().size(), 1);

		assertEquals(
				"MockWaiter should have an empty event log before the Cashier's scheduler is called for the first time. Instead, the MockWaiter's event log reads: "
						+ waiter.log.toString(), 0, waiter.log.size());

		assertEquals(
				"MockCustomer should have an empty event log after the Cashier's scheduler is called for the first time. Instead, the MockCustomer's event log reads: "
						+ waiter.log.toString(), 0, waiter.log.size());

		//step 2 of test

		assertTrue("Cashier's scheduler should have returned true (needs to react to new check), but didn't.", 
				cashier.pickAndExecuteAnAction());

		assertTrue("MockWaiter should have logged event: HereIsCheck, after Cashier's scheduler is called" , waiter.log.containsString("Received HereIsCheck from cashier. Check = "+ check));

		//step 3 of test, waiter gives bill to customer

		//preconditions

		assertTrue("CashierBill should contain a bill with state: waitingForCust. Instead, the state is " + cashier.getChecks().get(0).getState(),
				cashier.getChecks().get(0).getState() == CashierAgent.checkState.waitingForCust);

		assertEquals("MockCustomer should have an empty event log before the message is called. Instead, the MockCustomer's event log reads: "
				+ customer.log.toString(), 0, customer.log.size());

		//step 4

		customer.msgPleasePayBill();

		assertTrue("MockCustomer should have logged an event for receiving \"HereIsYourTotal\" with the correct bill, but his last event logged reads instead: " 
				+ customer.log.getLastLoggedEvent().toString(), customer.log.containsString("Received HereIsYourTotal from waiter. Total = " + check));


		assertTrue("CashierBill should contain a bill with state: paying. Instead, the state is " + cashier.getChecks().get(0).getState(),
				cashier.getChecks().get(0).getState() == CashierAgent.checkState.paying);


		assertTrue("Cashier should have logged \"Received ReadyToPay\" but didn't. His log reads instead: " 
				+ cashier.log.getLastLoggedEvent().toString(), cashier.log.containsString("Received ReadyToPay"));

		assertTrue("CashierBill should contain a bill of price = $20. It contains something else instead: $" 
				+ cashier.getChecks().get(0).getCheck(), cashier.getChecks().get(0).getCheck() == 20);

		assertTrue("CashierBill should contain a bill with the right customer in it. It doesn't.", 
				cashier.getChecks().get(0).getCust1() == customer);


		//step 4
		//NOTE: I called the scheduler in the assertTrue statement below (to succintly check the return value at the same time)

		//step 4
		assertTrue("Cashier's scheduler should have returned true (needs to react to customer's ReadyToPay), but didn't.", 
				cashier.pickAndExecuteAnAction());

		//check postconditions for step 4
		assertTrue("MockCustomer should have logged an event for receiving \"HereIsYourChange\" with the correct change, but his last event logged reads instead: " 
				+ customer.log.getLastLoggedEvent().toString(), customer.log.containsString("Received HereIsYourChange from cashier. Change = " + (cash-check)));


		assertEquals("CashierBill should contain 0 bills. It doesn't.", cashier.getChecks().size(), 0);

		assertFalse("Cashier's scheduler should have returned false (no actions left to do), but didn't.", 
				cashier.pickAndExecuteAnAction());


	}//end one normal customer scenario

	public void testTwoDishonestCustomerScenario() {
		//setUp() runs first before this test!

		try {
			setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		customer.setName("thief");
		customer.cashier = cashier;                        

		//check preconditions
		assertEquals("Cashier should have 0 bills in it. It doesn't.",cashier.getChecks().size(), 0);                
		assertEquals("CashierAgent should have an empty event log before the Cashier's HereIsBill is called. Instead, the Cashier's event log reads: "
				+ cashier.log.toString(), 0, cashier.log.size());

		int check = 20;
		int cash = 0;

		cashier.msgComputeBill(waiter, customer, "steak");//send the message from a waiter


		//check postconditions for step 1 and preconditions for step 2
		assertEquals("MockWaiter should have an empty event log before the Cashier's scheduler is called. Instead, the MockWaiter's event log reads: "
				+ waiter.log.toString(), 0, waiter.log.size());

		assertEquals("Cashier should have 1 bill in it. It doesn't.", cashier.getChecks().size(), 1);

		assertEquals(
				"MockWaiter should have an empty event log before the Cashier's scheduler is called for the first time. Instead, the MockWaiter's event log reads: "
						+ waiter.log.toString(), 0, waiter.log.size());

		assertEquals(
				"MockCustomer should have an empty event log after the Cashier's scheduler is called for the first time. Instead, the MockCustomer's event log reads: "
						+ waiter.log.toString(), 0, waiter.log.size());

		//step 2 of test

		assertTrue("Cashier's scheduler should have returned true (needs to react to new check), but didn't.", 
				cashier.pickAndExecuteAnAction());

		assertTrue("MockWaiter should have logged event: HereIsCheck, after Cashier's scheduler is called" , waiter.log.containsString("Received HereIsCheck from cashier. Check = "+ check));

		//step 3 of test, waiter gives bill to customer

		//preconditions

		assertTrue("CashierBill should contain a bill with state: waitingForCust. Instead, the state is " + cashier.getChecks().get(0).getState(),
				cashier.getChecks().get(0).getState() == CashierAgent.checkState.waitingForCust);

		assertEquals("MockCustomer should have an empty event log before the message is called. Instead, the MockCustomer's event log reads: "
				+ customer.log.toString(), 0, customer.log.size());

		//step 4

		customer.msgPleasePayBill();

		assertTrue("MockCustomer should have logged an event for receiving \"HereIsYourTotal\" with the correct bill, but his last event logged reads instead: " 
				+ customer.log.getLastLoggedEvent().toString(), customer.log.containsString("Received HereIsYourTotal from waiter. Total = " + check));


		assertTrue("CashierBill should contain a bill with state: unpaid. Instead, the state is " + cashier.getChecks().get(0).getState(),
				cashier.getChecks().get(0).getState() == CashierAgent.checkState.unpaid);

		assertTrue("Cashier should have logged \"Received NotEnoughMoney\" but didn't. His log reads instead: " 
				+ cashier.log.getLastLoggedEvent().toString(), cashier.log.containsString("Received NotEnoughMoney"));

		assertTrue("Cashier's scheduler should have returned true (needs to react to new check), but didn't.", 
				cashier.pickAndExecuteAnAction());

		//step 5

		assertTrue("MockCustomer should have logged an event for received \"you are in debt\" but his last event log reads: " + 
				customer.log.getLastLoggedEvent().toString(), customer.log.containsString("Received YouOweUs from cashier. Debt = "+ (cash-check)));

		assertTrue("MockHost should have logged an event for received \"watch this cust\" but his last event log reads: " + 
				host.log.getLastLoggedEvent().toString(), host.log.containsString("Watch this customer: "+ customer.getName()));

		assertFalse("Cashier's scheduler should have returned false (no actions left to do), but didn't.", 
				cashier.pickAndExecuteAnAction());


	}

	public void testThreeNormalMarketScenario() {

		try {
			setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		cashier.setCashRegister(300);
		market.cashier = cashier;
		//Step 1 preconditions

		assertEquals("Cashier should have 0 market bills in it. It doesn't.",cashier.getMarketBills().size(), 0);                
		assertEquals("CashierAgent should have an empty event log before the Cashier's HereIsBill is called. Instead, the Cashier's event log reads: "
				+ cashier.log.toString(), 0, cashier.log.size());
		assertEquals("MarketAgent should have an empty event log. Instead, the Market's event log reads: "
				+ market.log.toString(), 0,  market.log.size());

		//Step 1

		int bill = 20;
		cashier.msgPayMarketBill(market, bill);

		assertTrue("Cashier should have logged \"Received PayMarketBill\" but didn't. His log reads instead: " 
				+ cashier.log.getLastLoggedEvent().toString(), cashier.log.containsString("Received PayMarketBill"));

		assertEquals("Cashier should have 1 market bill. It doesnt.", cashier.getMarketBills().size(), 1);


		//Step 2 preconditions

		assertEquals("Cashier market bill should contain the correct market. It doesnt.", cashier.getMarketBills().get(0).getMarket(), market);
		assertEquals("Cashier bill should contain the correct bill amount. It doesnt.", cashier.getMarketBills().get(0).getBill(), bill);

		//Step 2, cashier pays market bill

		assertFalse("Cashier's scheduler should have returned false but didn't.", 
				cashier.pickAndExecuteAnAction());

		//Step 3, market received bills

		assertTrue("Market should have logged \"Received HereIsPayment with $20\" but didn't. His log reads instead: " 
				+ market.log.getLastLoggedEvent().toString(), market.log.containsString("Received HereIsPayment with $" + bill));

		//Step 3 post-condition, cashier removes bill

		assertEquals("Cashier should have 0 market bills in it. It doesn't.",cashier.getMarketBills().size(), 0);      

		assertFalse("Cashier's scheduler should have returned false (no actions left to do), but didn't.", 
				cashier.pickAndExecuteAnAction());
	}

	public void testFourCustomerBillInterruptMarketBillScenario() {

		try {
			setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//data initialization

		cashier.setCashRegister(300);
		customer.cashier = cashier;
		market.cashier = cashier;
		int marketBill = 20;
		int check = 20;
		int cash = 30;

		//Step 1 preconditions

		assertEquals("Cashier should have 0 customer's bills in it. It doesn't.",cashier.getChecks().size(), 0); 
		assertEquals("CashierAgent should have an empty event log before the Cashier's HereIsBill is called. Instead, the Cashier's event log reads: "
				+ cashier.log.toString(), 0, cashier.log.size());

		//Step 1, receives customer's bill from waiter

		cashier.msgComputeBill(waiter, customer, "steak");//send the message from a waiter


		//check postconditions for step 1 and preconditions for step 2	

		assertEquals("Cashier should have 1 customer's check in it. It doesn't.", cashier.getChecks().size(), 1);

		assertTrue("CashierBill should contain a bill with the right customer in it. It doesn't.", 
				cashier.getChecks().get(0).getCust1() == customer);

		assertEquals("MockCustomer should have an empty event log after the Cashier's scheduler is called for the first time. "
				+ "Instead, the MockCustomer's event log reads: " + waiter.log.toString(), 0, waiter.log.size());

		assertEquals("MockWaiter should have an empty event log before the Cashier's scheduler is called. Instead, the MockWaiter's event log reads: "
				+ waiter.log.toString(), 0, waiter.log.size());


		//step 2 of test, message waiter with check

		assertTrue("Cashier's scheduler should have returned true (needs to react to new check), but didn't.", 
				cashier.pickAndExecuteAnAction());

		assertTrue("MockWaiter should have logged event: HereIsCheck, after Cashier's scheduler is called" , waiter.log.containsString("Received HereIsCheck from cashier. Check = "+ check));

		assertTrue("CashierBill should contain a bill with state: waitingForCust. Instead, the state is " + cashier.getChecks().get(0).getState(),
				cashier.getChecks().get(0).getState() == CashierAgent.checkState.waitingForCust);

		assertEquals("MockCustomer should have an empty event log before the message is called. Instead, the MockCustomer's event log reads: "
				+ customer.log.toString(), 0, customer.log.size());

		//step 3 of test, waiter gives bill to customer

		customer.msgPleasePayBill();

		assertTrue("MockCustomer should have logged an event for receiving \"HereIsYourTotal\" with the correct bill, but his last event logged reads instead: " 
				+ customer.log.getLastLoggedEvent().toString(), customer.log.containsString("Received HereIsYourTotal from waiter. Total = " + check));


		//customer automatically messages cashier 

		assertTrue("CashierBill should contain a check of price = $20. It contains something else instead: $" 
				+ cashier.getChecks().get(0).getCheck(), cashier.getChecks().get(0).getCheck() == 20);

		assertTrue("CashierBill should contain a bill with state: paying. Instead, the state is " + cashier.getChecks().get(0).getState(),
				cashier.getChecks().get(0).getState() == CashierAgent.checkState.paying);

		assertTrue("Cashier should have logged \"Received ReadyToPay\" but didn't. His log reads instead: " 
				+ cashier.log.getLastLoggedEvent().toString(), cashier.log.containsString("Received ReadyToPay"));

		//step 3 (market bill interrupts customer) pre-conditions

		assertEquals("Cashier should have 0 market bills in it. It doesn't.",cashier.getMarketBills().size(), 0);                
		assertEquals("MarketAgent should have an empty event log. Instead, the Market's event log reads: "
				+ market.log.toString(), 0,  market.log.size());

		//Step 3, market bill interrupts customer

		cashier.msgPayMarketBill(market, marketBill);

		assertEquals("Cashier should have 1 market bill, it doesn't.", 1, cashier.getMarketBills().size());
		assertEquals("Cashier's market bill should contain the correct market. It doesnt.", cashier.getMarketBills().get(0).getMarket(), market);
		assertEquals("Cashier's customer bill should contain the correct bill amount. It doesnt.", cashier.getMarketBills().get(0).getBill(), marketBill);

		//Step 4, message customer first

		assertTrue("Cashier's scheduler should have returned true (needs to react to customer's ReadyToPay), but didn't.", 
				cashier.pickAndExecuteAnAction());

		//Step 4 post-conditions

		assertTrue("MockCustomer should have logged an event for receiving \"HereIsYourChange\" with the correct change, but his last event logged reads instead: " 
				+ customer.log.getLastLoggedEvent().toString(), customer.log.containsString("Received HereIsYourChange from cashier. Change = " + (cash-check)));

		assertEquals("CashierBill should contain 0 customer's bills. It doesn't.", cashier.getChecks().size(), 0);

		//Step 5, pay market bill

		assertFalse("Cashier's scheduler should have returned false but didn't.", 
				cashier.pickAndExecuteAnAction());

		//step 5 post-conditions

		assertTrue("Market should have logged \"Received HereIsPayment with $20\" but didn't. His log reads instead: " 
				+ market.log.getLastLoggedEvent().toString(), market.log.containsString("Received HereIsPayment with $" + marketBill));

		assertEquals("Cashier should have 0 market bills, it doesn't.", 0 , cashier.getMarketBills().size());

		assertFalse("Cashier's scheduler should have returned false (no actions left to do), but didn't.", 
				cashier.pickAndExecuteAnAction());
	}

	public void testFiveTwoMarketBillScenario() {

		try {
			setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		cashier.setCashRegister(300);
		market.cashier = cashier;
		//Step 1 preconditions

		assertEquals("Cashier should have 0 market bills in it. It doesn't.",cashier.getMarketBills().size(), 0);                
		assertEquals("CashierAgent should have an empty event log before the Cashier's HereIsBill is called. Instead, the Cashier's event log reads: "
				+ cashier.log.toString(), 0, cashier.log.size());
		assertEquals("MarketAgent should have an empty event log. Instead, the Market's event log reads: "
				+ market.log.toString(), 0,  market.log.size());

		//Step 1

		int bill1 = 20;
		int bill2 = 30;

		cashier.msgPayMarketBill(market, bill1);

		assertTrue("Cashier should have logged \"Received PayMarketBill\" but didn't. His log reads instead: " 
				+ cashier.log.getLastLoggedEvent().toString(), cashier.log.containsString("Received PayMarketBill"));

		assertEquals("Cashier should have 1 market bill. It doesnt.", cashier.getMarketBills().size(), 1);

		//Step 2 pre-conditions 

		assertEquals("Cashier market bill should contain the correct market. It doesnt.", cashier.getMarketBills().get(0).getMarket(), market);
		assertEquals("Cashier bill should contain the correct bill amount. It doesnt.", cashier.getMarketBills().get(0).getBill(), bill1);

		//Step 2

		cashier.msgPayMarketBill(market, bill2);

		assertEquals("Cashier should have 2 market bills. It doesnt.", cashier.getMarketBills().size(), 2);
		assertEquals("Cashier market bill 2 should contain the correct market. It doesnt.", cashier.getMarketBills().get(1).getMarket(), market);
		assertEquals("Cashier bill should contain the correct bill amount. It doesnt.", cashier.getMarketBills().get(1).getBill(), bill2);

		//Step 3a, cashier pays bill 1

		assertFalse("Cashier's scheduler should have returned false but didn't.", 
				cashier.pickAndExecuteAnAction());

		//Step 3b, market received bill 1

		assertTrue("Market should have logged \"Received HereIsPayment with $20\" but didn't. His log reads instead: " 
				+ market.log.getLastLoggedEvent().toString(), market.log.containsString("Received HereIsPayment with $" + bill1));

		//Step 3 post-condition, cashier removes bill 1

		assertEquals("Cashier should have 1 market bill in it. It doesn't.",cashier.getMarketBills().size(), 1);      

		//step 4a, cashier pays bill 2

		assertFalse("Cashier's scheduler should have returned false but didn't.", 
				cashier.pickAndExecuteAnAction());

		//step 4b, market received bill 2

		assertTrue("Market should have logged \"Received HereIsPayment with $20\" but didn't. His log reads instead: " 
				+ market.log.getLastLoggedEvent().toString(), market.log.containsString("Received HereIsPayment with $" + bill2));


		//step 4 post-condition, cashier removes bill 2

		assertEquals("Cashier should have 0 market bills in it. It doesn't.",cashier.getMarketBills().size(), 0); 

		assertFalse("Cashier's scheduler should have returned false (no actions left to do), but didn't.", 
				cashier.pickAndExecuteAnAction());
	}

	public void testSixMarketBillInterruptSecondCustomerBillScenario() {

		try {
			setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//data initialization

		cashier.setCashRegister(300);
		customer.cashier = cashier;
		market.cashier = cashier;
		int marketBill = 20;
		int check = 20;
		int cash = 30;

		//Step 1 preconditions

		assertEquals("Cashier should have 0 customer's bills in it. It doesn't.",cashier.getChecks().size(), 0); 
		assertEquals("CashierAgent should have an empty event log before the Cashier's HereIsBill is called. Instead, the Cashier's event log reads: "
				+ cashier.log.toString(), 0, cashier.log.size());

		//Step 1, receives customer's bill from waiter

		cashier.msgComputeBill(waiter, customer, "steak");//send the message from a waiter


		//check postconditions for step 1 and preconditions for step 2	

		assertEquals("Cashier should have 1 customer's check in it. It doesn't.", cashier.getChecks().size(), 1);

		assertTrue("CashierBill should contain a bill with the right customer in it. It doesn't.", 
				cashier.getChecks().get(0).getCust1() == customer);

		assertEquals("MockCustomer should have an empty event log after the Cashier's scheduler is called for the first time. "
				+ "Instead, the MockCustomer's event log reads: " + waiter.log.toString(), 0, waiter.log.size());

		assertEquals("MockWaiter should have an empty event log before the Cashier's scheduler is called. Instead, the MockWaiter's event log reads: "
				+ waiter.log.toString(), 0, waiter.log.size());


		//step 2 of test, message waiter with check

		assertTrue("Cashier's scheduler should have returned true (needs to react to new check), but didn't.", 
				cashier.pickAndExecuteAnAction());

		assertTrue("MockWaiter should have logged event: HereIsCheck, after Cashier's scheduler is called" , waiter.log.containsString("Received HereIsCheck from cashier. Check = "+ check));

		assertTrue("CashierBill should contain a bill with state: waitingForCust. Instead, the state is " + cashier.getChecks().get(0).getState(),
				cashier.getChecks().get(0).getState() == CashierAgent.checkState.waitingForCust);

		assertEquals("MockCustomer should have an empty event log before the message is called. Instead, the MockCustomer's event log reads: "
				+ customer.log.toString(), 0, customer.log.size());

		//step 3 of test, waiter gives bill to customer

		customer.msgPleasePayBill();

		assertTrue("MockCustomer should have logged an event for receiving \"HereIsYourTotal\" with the correct bill, but his last event logged reads instead: " 
				+ customer.log.getLastLoggedEvent().toString(), customer.log.containsString("Received HereIsYourTotal from waiter. Total = " + check));


		//customer automatically messages cashier 

		assertTrue("CashierBill should contain a check of price = $20. It contains something else instead: $" 
				+ cashier.getChecks().get(0).getCheck(), cashier.getChecks().get(0).getCheck() == 20);

		assertTrue("CashierBill should contain a bill with state: paying. Instead, the state is " + cashier.getChecks().get(0).getState(),
				cashier.getChecks().get(0).getState() == CashierAgent.checkState.paying);

		assertTrue("Cashier should have logged \"Received ReadyToPay\" but didn't. His log reads instead: " 
				+ cashier.log.getLastLoggedEvent().toString(), cashier.log.containsString("Received ReadyToPay"));

		//step 3 (market bill interrupts customer) pre-conditions

		assertEquals("Cashier should have 0 market bills in it. It doesn't.",cashier.getMarketBills().size(), 0);                
		assertEquals("MarketAgent should have an empty event log. Instead, the Market's event log reads: "
				+ market.log.toString(), 0,  market.log.size());

		//Step 3, market bill interrupts customer

		cashier.msgPayMarketBill(market, marketBill);

		assertEquals("Cashier should have 1 market bill, it doesn't.", 1, cashier.getMarketBills().size());
		assertEquals("Cashier's market bill should contain the correct market. It doesnt.", cashier.getMarketBills().get(0).getMarket(), market);
		assertEquals("Cashier's customer bill should contain the correct bill amount. It doesnt.", cashier.getMarketBills().get(0).getBill(), marketBill);

		//Step 4, message customer first

		assertTrue("Cashier's scheduler should have returned true (needs to react to customer's ReadyToPay), but didn't.", 
				cashier.pickAndExecuteAnAction());

		//Step 4 post-conditions

		assertTrue("MockCustomer should have logged an event for receiving \"HereIsYourChange\" with the correct change, but his last event logged reads instead: " 
				+ customer.log.getLastLoggedEvent().toString(), customer.log.containsString("Received HereIsYourChange from cashier. Change = " + (cash-check)));

		assertEquals("CashierBill should contain 0 customer's bills. It doesn't.", cashier.getChecks().size(), 0);

		//Step 5, pay market bill

		assertFalse("Cashier's scheduler should have returned false but didn't.", 
				cashier.pickAndExecuteAnAction());

		//step 5 post-conditions

		assertTrue("Market should have logged \"Received HereIsPayment with $20\" but didn't. His log reads instead: " 
				+ market.log.getLastLoggedEvent().toString(), market.log.containsString("Received HereIsPayment with $" + marketBill));

		assertEquals("Cashier should have 0 market bills, it doesn't.", 0 , cashier.getMarketBills().size());

		assertFalse("Cashier's scheduler should have returned false (no actions left to do), but didn't.", 
				cashier.pickAndExecuteAnAction());

	}

}
	 */
}
