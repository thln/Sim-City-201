package americanRestaurant.test;

import junit.framework.TestCase;

/**
 * 
 * This class is a JUnit test class to unit test the AmericanRestaurantCashierRole's basic interaction
 * with waiters, customers, and the host.
 * It is provided as an example to students in CS201 for their unit testing lab.
 *
 * @author Monroe Ekilah
 */
public class AmericanRestaurantCashierTest extends TestCase
{
	//these are instantiated for each test separately via the setUp() method.
	CashierAgent cashier;
	AmericanRestaurantMockWaiter waiter;
	AmericanRestaurantMockCustomer customer;
	AmericanRestaurantMockHost host;
	MockMarket market;

	/**
	 * This method is run before each test. You can use it to instantiate the class variables
	 * for your agent and mocks, etc.
	 */
	public void setUp() throws Exception{
		super.setUp();    
		host = new AmericanRestaurantMockHost("mockhost");
		cashier = new CashierAgent(host);                
		customer = new AmericanRestaurantMockCustomer("mockcustomer");                
		waiter = new AmericanRestaurantMockWaiter("mockwaiter"); 	
		market = new MockMarket("mockmarket");
	}        
	/**
	 * This tests the americanRestaurantCashier under very simple terms: one customer is ready to pay the exact bill.
	 */
	public void testOneNormalCustomerScenario()
	{
		//setUp() runs first before this test!
		try {
			setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		customer.americanRestaurantCashier = cashier;//You can do almost anything in a unit test.                        

		//check preconditions
		assertEquals("AmericanRestaurantCashier should have 0 bills in it. It doesn't.",cashier.getChecks().size(), 0);                
		assertEquals("AmericanRestaurantCashierRole should have an empty event log before the AmericanRestaurantCashier's HereIsBill is called. Instead, the AmericanRestaurantCashier's event log reads: "
				+ cashier.log.toString(), 0, cashier.log.size());

		int check = 20;
		int cash = 30;

		//step 1 of the test

		cashier.msgComputeBill(waiter, customer, "steak");//send the message from a waiter


		//check postconditions for step 1 and preconditions for step 2
		assertEquals("AmericanRestaurantMockWaiter should have an empty event log before the AmericanRestaurantCashier's scheduler is called. Instead, the AmericanRestaurantMockWaiter's event log reads: "
				+ waiter.log.toString(), 0, waiter.log.size());

		assertEquals("AmericanRestaurantCashier should have 1 bill in it. It doesn't.", cashier.getChecks().size(), 1);

		assertEquals(
				"AmericanRestaurantMockWaiter should have an empty event log before the AmericanRestaurantCashier's scheduler is called for the first time. Instead, the AmericanRestaurantMockWaiter's event log reads: "
						+ waiter.log.toString(), 0, waiter.log.size());

		assertEquals(
				"AmericanRestaurantMockCustomer should have an empty event log after the AmericanRestaurantCashier's scheduler is called for the first time. Instead, the AmericanRestaurantMockCustomer's event log reads: "
						+ waiter.log.toString(), 0, waiter.log.size());

		//step 2 of test

		assertTrue("AmericanRestaurantCashier's scheduler should have returned true (needs to react to new check), but didn't.", 
				cashier.pickAndExecuteAnAction());

		assertTrue("AmericanRestaurantMockWaiter should have logged event: HereIsCheck, after AmericanRestaurantCashier's scheduler is called" , waiter.log.containsString("Received HereIsCheck from americanRestaurantCashier. Check = "+ check));

		//step 3 of test, waiter gives bill to customer

		//preconditions

		assertTrue("CashierBill should contain a bill with state: waitingForCust. Instead, the state is " + cashier.getChecks().get(0).getState(),
				cashier.getChecks().get(0).getState() == CashierAgent.checkState.waitingForCust);

		assertEquals("AmericanRestaurantMockCustomer should have an empty event log before the message is called. Instead, the AmericanRestaurantMockCustomer's event log reads: "
				+ customer.log.toString(), 0, customer.log.size());

		//step 4

		customer.msgPleasePayBill();

		assertTrue("AmericanRestaurantMockCustomer should have logged an event for receiving \"HereIsYourTotal\" with the correct bill, but his last event logged reads instead: " 
				+ customer.log.getLastLoggedEvent().toString(), customer.log.containsString("Received HereIsYourTotal from waiter. Total = " + check));


		assertTrue("CashierBill should contain a bill with state: paying. Instead, the state is " + cashier.getChecks().get(0).getState(),
				cashier.getChecks().get(0).getState() == CashierAgent.checkState.paying);


		assertTrue("AmericanRestaurantCashier should have logged \"Received ReadyToPay\" but didn't. His log reads instead: " 
				+ cashier.log.getLastLoggedEvent().toString(), cashier.log.containsString("Received ReadyToPay"));

		assertTrue("CashierBill should contain a bill of price = $20. It contains something else instead: $" 
				+ cashier.getChecks().get(0).getCheck(), cashier.getChecks().get(0).getCheck() == 20);

		assertTrue("CashierBill should contain a bill with the right customer in it. It doesn't.", 
				cashier.getChecks().get(0).getCust1() == customer);


		//step 4
		//NOTE: I called the scheduler in the assertTrue statement below (to succintly check the return value at the same time)

		//step 4
		assertTrue("AmericanRestaurantCashier's scheduler should have returned true (needs to react to customer's ReadyToPay), but didn't.", 
				cashier.pickAndExecuteAnAction());

		//check postconditions for step 4
		assertTrue("AmericanRestaurantMockCustomer should have logged an event for receiving \"HereIsYourChange\" with the correct change, but his last event logged reads instead: " 
				+ customer.log.getLastLoggedEvent().toString(), customer.log.containsString("Received HereIsYourChange from americanRestaurantCashier. Change = " + (cash-check)));


		assertEquals("CashierBill should contain 0 bills. It doesn't.", cashier.getChecks().size(), 0);

		assertFalse("AmericanRestaurantCashier's scheduler should have returned false (no actions left to do), but didn't.", 
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
		customer.americanRestaurantCashier = cashier;                        

		//check preconditions
		assertEquals("AmericanRestaurantCashier should have 0 bills in it. It doesn't.",cashier.getChecks().size(), 0);                
		assertEquals("AmericanRestaurantCashierRole should have an empty event log before the AmericanRestaurantCashier's HereIsBill is called. Instead, the AmericanRestaurantCashier's event log reads: "
				+ cashier.log.toString(), 0, cashier.log.size());

		int check = 20;
		int cash = 0;

		cashier.msgComputeBill(waiter, customer, "steak");//send the message from a waiter


		//check postconditions for step 1 and preconditions for step 2
		assertEquals("AmericanRestaurantMockWaiter should have an empty event log before the AmericanRestaurantCashier's scheduler is called. Instead, the AmericanRestaurantMockWaiter's event log reads: "
				+ waiter.log.toString(), 0, waiter.log.size());

		assertEquals("AmericanRestaurantCashier should have 1 bill in it. It doesn't.", cashier.getChecks().size(), 1);

		assertEquals(
				"AmericanRestaurantMockWaiter should have an empty event log before the AmericanRestaurantCashier's scheduler is called for the first time. Instead, the AmericanRestaurantMockWaiter's event log reads: "
						+ waiter.log.toString(), 0, waiter.log.size());

		assertEquals(
				"AmericanRestaurantMockCustomer should have an empty event log after the AmericanRestaurantCashier's scheduler is called for the first time. Instead, the AmericanRestaurantMockCustomer's event log reads: "
						+ waiter.log.toString(), 0, waiter.log.size());

		//step 2 of test

		assertTrue("AmericanRestaurantCashier's scheduler should have returned true (needs to react to new check), but didn't.", 
				cashier.pickAndExecuteAnAction());

		assertTrue("AmericanRestaurantMockWaiter should have logged event: HereIsCheck, after AmericanRestaurantCashier's scheduler is called" , waiter.log.containsString("Received HereIsCheck from americanRestaurantCashier. Check = "+ check));

		//step 3 of test, waiter gives bill to customer

		//preconditions

		assertTrue("CashierBill should contain a bill with state: waitingForCust. Instead, the state is " + cashier.getChecks().get(0).getState(),
				cashier.getChecks().get(0).getState() == CashierAgent.checkState.waitingForCust);

		assertEquals("AmericanRestaurantMockCustomer should have an empty event log before the message is called. Instead, the AmericanRestaurantMockCustomer's event log reads: "
				+ customer.log.toString(), 0, customer.log.size());

		//step 4

		customer.msgPleasePayBill();

		assertTrue("AmericanRestaurantMockCustomer should have logged an event for receiving \"HereIsYourTotal\" with the correct bill, but his last event logged reads instead: " 
				+ customer.log.getLastLoggedEvent().toString(), customer.log.containsString("Received HereIsYourTotal from waiter. Total = " + check));


		assertTrue("CashierBill should contain a bill with state: unpaid. Instead, the state is " + cashier.getChecks().get(0).getState(),
				cashier.getChecks().get(0).getState() == CashierAgent.checkState.unpaid);

		assertTrue("AmericanRestaurantCashier should have logged \"Received NotEnoughMoney\" but didn't. His log reads instead: " 
				+ cashier.log.getLastLoggedEvent().toString(), cashier.log.containsString("Received NotEnoughMoney"));

		assertTrue("AmericanRestaurantCashier's scheduler should have returned true (needs to react to new check), but didn't.", 
				cashier.pickAndExecuteAnAction());

		//step 5

		assertTrue("AmericanRestaurantMockCustomer should have logged an event for received \"you are in debt\" but his last event log reads: " + 
				customer.log.getLastLoggedEvent().toString(), customer.log.containsString("Received YouOweUs from americanRestaurantCashier. Debt = "+ (cash-check)));

		assertTrue("AmericanRestaurantMockHost should have logged an event for received \"watch this cust\" but his last event log reads: " + 
				host.log.getLastLoggedEvent().toString(), host.log.containsString("Watch this customer: "+ customer.getName()));

		assertFalse("AmericanRestaurantCashier's scheduler should have returned false (no actions left to do), but didn't.", 
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

		assertEquals("AmericanRestaurantCashier should have 0 market bills in it. It doesn't.",cashier.getMarketBills().size(), 0);                
		assertEquals("AmericanRestaurantCashierRole should have an empty event log before the AmericanRestaurantCashier's HereIsBill is called. Instead, the AmericanRestaurantCashier's event log reads: "
				+ cashier.log.toString(), 0, cashier.log.size());
		assertEquals("MarketAgent should have an empty event log. Instead, the Market's event log reads: "
				+ market.log.toString(), 0,  market.log.size());

		//Step 1

		int bill = 20;
		cashier.msgPayMarketBill(market, bill);

		assertTrue("AmericanRestaurantCashier should have logged \"Received PayMarketBill\" but didn't. His log reads instead: " 
				+ cashier.log.getLastLoggedEvent().toString(), cashier.log.containsString("Received PayMarketBill"));

		assertEquals("AmericanRestaurantCashier should have 1 market bill. It doesnt.", cashier.getMarketBills().size(), 1);


		//Step 2 preconditions

		assertEquals("AmericanRestaurantCashier market bill should contain the correct market. It doesnt.", cashier.getMarketBills().get(0).getMarket(), market);
		assertEquals("AmericanRestaurantCashier bill should contain the correct bill amount. It doesnt.", cashier.getMarketBills().get(0).getBill(), bill);

		//Step 2, americanRestaurantCashier pays market bill

		assertFalse("AmericanRestaurantCashier's scheduler should have returned false but didn't.", 
				cashier.pickAndExecuteAnAction());

		//Step 3, market received bills

		assertTrue("Market should have logged \"Received HereIsPayment with $20\" but didn't. His log reads instead: " 
				+ market.log.getLastLoggedEvent().toString(), market.log.containsString("Received HereIsPayment with $" + bill));

		//Step 3 post-condition, americanRestaurantCashier removes bill

		assertEquals("AmericanRestaurantCashier should have 0 market bills in it. It doesn't.",cashier.getMarketBills().size(), 0);      

		assertFalse("AmericanRestaurantCashier's scheduler should have returned false (no actions left to do), but didn't.", 
				cashier.pickAndExecuteAnAction());
	}

	public void testFourMarketBillInterruptsCustomerBillScenario() {

		try {
			setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//data initialization

		cashier.setCashRegister(300);
		customer.americanRestaurantCashier = cashier;
		market.cashier = cashier;
		int marketBill = 20;
		int check = 20;
		int cash = 30;

		//Step 1 preconditions

		assertEquals("AmericanRestaurantCashier should have 0 customer's bills in it. It doesn't.",cashier.getChecks().size(), 0); 
		assertEquals("AmericanRestaurantCashierRole should have an empty event log before the AmericanRestaurantCashier's HereIsBill is called. Instead, the AmericanRestaurantCashier's event log reads: "
				+ cashier.log.toString(), 0, cashier.log.size());

		//Step 1, receives customer's bill from waiter

		cashier.msgComputeBill(waiter, customer, "steak");//send the message from a waiter


		//check postconditions for step 1 and preconditions for step 2	

		assertEquals("AmericanRestaurantCashier should have 1 customer's check in it. It doesn't.", cashier.getChecks().size(), 1);

		assertTrue("CashierBill should contain a bill with the right customer in it. It doesn't.", 
				cashier.getChecks().get(0).getCust1() == customer);

		assertEquals("AmericanRestaurantMockCustomer should have an empty event log after the AmericanRestaurantCashier's scheduler is called for the first time. "
				+ "Instead, the AmericanRestaurantMockCustomer's event log reads: " + waiter.log.toString(), 0, waiter.log.size());

		assertEquals("AmericanRestaurantMockWaiter should have an empty event log before the AmericanRestaurantCashier's scheduler is called. Instead, the AmericanRestaurantMockWaiter's event log reads: "
				+ waiter.log.toString(), 0, waiter.log.size());


		//step 2 of test, message waiter with check

		assertTrue("AmericanRestaurantCashier's scheduler should have returned true (needs to react to new check), but didn't.", 
				cashier.pickAndExecuteAnAction());

		assertTrue("AmericanRestaurantMockWaiter should have logged event: HereIsCheck, after AmericanRestaurantCashier's scheduler is called" , waiter.log.containsString("Received HereIsCheck from americanRestaurantCashier. Check = "+ check));

		assertTrue("CashierBill should contain a bill with state: waitingForCust. Instead, the state is " + cashier.getChecks().get(0).getState(),
				cashier.getChecks().get(0).getState() == CashierAgent.checkState.waitingForCust);

		assertEquals("AmericanRestaurantMockCustomer should have an empty event log before the message is called. Instead, the AmericanRestaurantMockCustomer's event log reads: "
				+ customer.log.toString(), 0, customer.log.size());

		//step 3 of test, waiter gives bill to customer

		customer.msgPleasePayBill();

		assertTrue("AmericanRestaurantMockCustomer should have logged an event for receiving \"HereIsYourTotal\" with the correct bill, but his last event logged reads instead: " 
				+ customer.log.getLastLoggedEvent().toString(), customer.log.containsString("Received HereIsYourTotal from waiter. Total = " + check));


		//customer automatically messages americanRestaurantCashier 

		assertTrue("CashierBill should contain a check of price = $20. It contains something else instead: $" 
				+ cashier.getChecks().get(0).getCheck(), cashier.getChecks().get(0).getCheck() == 20);

		assertTrue("CashierBill should contain a bill with state: paying. Instead, the state is " + cashier.getChecks().get(0).getState(),
				cashier.getChecks().get(0).getState() == CashierAgent.checkState.paying);

		assertTrue("AmericanRestaurantCashier should have logged \"Received ReadyToPay\" but didn't. His log reads instead: " 
				+ cashier.log.getLastLoggedEvent().toString(), cashier.log.containsString("Received ReadyToPay"));

		//step 4 (market bill interrupts customer) pre-conditions

		assertEquals("AmericanRestaurantCashier should have 0 market bills in it. It doesn't.",cashier.getMarketBills().size(), 0);                
		assertEquals("MarketAgent should have an empty event log. Instead, the Market's event log reads: "
				+ market.log.toString(), 0,  market.log.size());

		//Step 4, market bill interrupts customer

		cashier.msgPayMarketBill(market, marketBill);

		assertEquals("AmericanRestaurantCashier should have 1 market bill, it doesn't.", 1, cashier.getMarketBills().size());
		assertEquals("AmericanRestaurantCashier's market bill should contain the correct market. It doesnt.", cashier.getMarketBills().get(0).getMarket(), market);
		assertEquals("AmericanRestaurantCashier's customer bill should contain the correct bill amount. It doesnt.", cashier.getMarketBills().get(0).getBill(), marketBill);

		//Step 5, message customer first

		assertTrue("AmericanRestaurantCashier's scheduler should have returned true (needs to react to customer's ReadyToPay), but didn't.", 
				cashier.pickAndExecuteAnAction());

		//Step 5 post-conditions

		assertTrue("AmericanRestaurantMockCustomer should have logged an event for receiving \"HereIsYourChange\" with the correct change, but his last event logged reads instead: " 
				+ customer.log.getLastLoggedEvent().toString(), customer.log.containsString("Received HereIsYourChange from americanRestaurantCashier. Change = " + (cash-check)));

		assertEquals("CashierBill should contain 0 customer's bills. It doesn't.", cashier.getChecks().size(), 0);

		//Step 6, pay market bill

		assertFalse("AmericanRestaurantCashier's scheduler should have returned false but didn't.", 
				cashier.pickAndExecuteAnAction());

		//step 6 post-conditions

		assertTrue("Market should have logged \"Received HereIsPayment with $20\" but didn't. His log reads instead: " 
				+ market.log.getLastLoggedEvent().toString(), market.log.containsString("Received HereIsPayment with $" + marketBill));

		assertEquals("AmericanRestaurantCashier should have 0 market bills, it doesn't.", 0 , cashier.getMarketBills().size());

		assertFalse("AmericanRestaurantCashier's scheduler should have returned false (no actions left to do), but didn't.", 
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

		assertEquals("AmericanRestaurantCashier should have 0 market bills in it. It doesn't.",cashier.getMarketBills().size(), 0);                
		assertEquals("AmericanRestaurantCashierRole should have an empty event log before the AmericanRestaurantCashier's HereIsBill is called. Instead, the AmericanRestaurantCashier's event log reads: "
				+ cashier.log.toString(), 0, cashier.log.size());
		assertEquals("MarketAgent should have an empty event log. Instead, the Market's event log reads: "
				+ market.log.toString(), 0,  market.log.size());

		//Step 1, americanRestaurantCashier receives market bill 1

		int bill1 = 20;
		int bill2 = 30;

		cashier.msgPayMarketBill(market, bill1);

		assertTrue("AmericanRestaurantCashier should have logged \"Received PayMarketBill\" but didn't. His log reads instead: " 
				+ cashier.log.getLastLoggedEvent().toString(), cashier.log.containsString("Received PayMarketBill"));

		assertEquals("AmericanRestaurantCashier should have 1 market bill. It doesnt.", cashier.getMarketBills().size(), 1);

		//Step 2 pre-conditions 

		assertEquals("AmericanRestaurantCashier market bill should contain the correct market. It doesnt.", cashier.getMarketBills().get(0).getMarket(), market);
		assertEquals("AmericanRestaurantCashier bill should contain the correct bill amount. It doesnt.", cashier.getMarketBills().get(0).getBill(), bill1);

		//Step 2, americanRestaurantCashier receives market bill 2

		cashier.msgPayMarketBill(market, bill2);

		assertEquals("AmericanRestaurantCashier should have 2 market bills. It doesnt.", cashier.getMarketBills().size(), 2);
		assertEquals("AmericanRestaurantCashier market bill 2 should contain the correct market. It doesnt.", cashier.getMarketBills().get(1).getMarket(), market);
		assertEquals("AmericanRestaurantCashier bill should contain the correct bill amount. It doesnt.", cashier.getMarketBills().get(1).getBill(), bill2);

		//Step 3, americanRestaurantCashier pays bill 1

		assertFalse("AmericanRestaurantCashier's scheduler should have returned false but didn't.", 
				cashier.pickAndExecuteAnAction());

		//Step 3 post-condition (market received bill 1 & americanRestaurantCashier removes bill 1)

		assertTrue("Market should have logged \"Received HereIsPayment with $20\" but didn't. His log reads instead: " 
				+ market.log.getLastLoggedEvent().toString(), market.log.containsString("Received HereIsPayment with $" + bill1));

		assertEquals("AmericanRestaurantCashier should have 1 market bill in it. It doesn't.",cashier.getMarketBills().size(), 1);      

		//step 4 americanRestaurantCashier pays bill 2

		assertFalse("AmericanRestaurantCashier's scheduler should have returned false but didn't.", 
				cashier.pickAndExecuteAnAction());

		//step 4 post-condition (market receives bill 2 & americanRestaurantCashier removes bill 2)

		assertTrue("Market should have logged \"Received HereIsPayment with $20\" but didn't. His log reads instead: " 
				+ market.log.getLastLoggedEvent().toString(), market.log.containsString("Received HereIsPayment with $" + bill2));

		assertEquals("AmericanRestaurantCashier should have 0 market bills in it. It doesn't.",cashier.getMarketBills().size(), 0); 

		assertFalse("AmericanRestaurantCashier's scheduler should have returned false (no actions left to do), but didn't.", 
				cashier.pickAndExecuteAnAction());
	}

	public void testSixWaiterInterruptMarketBillScenario() {

		try {
			setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//data initialization

		cashier.setCashRegister(300);
		customer.americanRestaurantCashier = cashier;
		market.cashier = cashier;
		int marketBill = 20;
		int check = 20;
		int cash = 30;

		//Step 1 preconditions

		assertEquals("AmericanRestaurantCashier should have 0 Market bills in it. It doesn't.",cashier.getMarketBills().size(), 0); 
		assertEquals("AmericanRestaurantCashierRole should have an empty event log before the AmericanRestaurantCashier's PayMarketBill is called. Instead, the AmericanRestaurantCashier's event log reads: "
				+ cashier.log.toString(), 0, cashier.log.size());

		//Step 1, americanRestaurantCashier receives market bill from market agent

		cashier.msgPayMarketBill(market, marketBill);
		assertEquals("AmericanRestaurantCashier should have 1 market bill, it doesn't.", 1, cashier.getMarketBills().size());
		assertEquals("AmericanRestaurantCashier's market bill should contain the correct market. It doesnt.", cashier.getMarketBills().get(0).getMarket(), market);
		assertEquals("AmericanRestaurantCashier's customer bill should contain the correct bill amount. It doesnt.", cashier.getMarketBills().get(0).getBill(), marketBill);

		//check preconditions for step 2	

		assertEquals("AmericanRestaurantCashier should have 0 customer bills in it. It doesn't.",cashier.getChecks().size(), 0);
		
		//step 2, waiter messages americanRestaurantCashier (interrupts market bill)

		cashier.msgComputeBill(waiter, customer, "steak");	

		//check postconditions for step 2 and preconditions for step 3
		
		assertEquals("AmericanRestaurantMockWaiter should have an empty event log before the AmericanRestaurantCashier's scheduler is called. Instead, the AmericanRestaurantMockWaiter's event log reads: "
				+ waiter.log.toString(), 0, waiter.log.size());
		assertEquals("AmericanRestaurantCashier should have 1 customer's bill in it. It doesn't.", cashier.getChecks().size(), 1);

		
		//step 3, run americanRestaurantCashier scheduler 
			//customer checks have priority over market and should be responded to first
		
		assertTrue("AmericanRestaurantCashier's scheduler should have returned true (needs to react to new check), but didn't.", 
				cashier.pickAndExecuteAnAction());
		
		//step 3 post-condition 

		assertTrue("AmericanRestaurantMockWaiter should have logged event: HereIsCheck, after AmericanRestaurantCashier's scheduler is called" ,
				waiter.log.containsString("Received HereIsCheck from americanRestaurantCashier. Check = "+ check));	
		
		assertTrue("CashierBill should contain a bill with state: waitingForCust. Instead, the state is " + cashier.getChecks().get(0).getState(),
				cashier.getChecks().get(0).getState() == CashierAgent.checkState.waitingForCust);
		
		//step 4 pre-condition
		assertEquals("MarketAgent should have an empty event log. Instead, the Market's event log reads: "
				+ market.log.toString(), 0,  market.log.size());
		
		//step 4, call scheduler again
			//should respond to market bill, customer bill is not ready for processing
		
		assertFalse("AmericanRestaurantCashier's scheduler should have returned false for market bill but didn't.", 
				cashier.pickAndExecuteAnAction());
		
		//step 4 post-condition
		
		assertTrue("Market should have logged \"Received HereIsPayment with $20\" but didn't. His log reads instead: " 
				+ market.log.getLastLoggedEvent().toString(), market.log.containsString("Received HereIsPayment with $" + marketBill));
		assertEquals("AmericanRestaurantCashier should have 0 market bills, it doesn't.", 0 , cashier.getMarketBills().size());
		
		//step 5 pre-condition

		assertEquals("AmericanRestaurantMockCustomer should have an empty event log before the message is called. Instead, the AmericanRestaurantMockCustomer's event log reads: "
				+ customer.log.toString(), 0, customer.log.size());

		//step 5, waiter gives check to customer

		customer.msgPleasePayBill();

		assertTrue("AmericanRestaurantMockCustomer should have logged an event for receiving \"HereIsYourTotal\" with the correct bill, but his last event logged reads instead: " 
				+ customer.log.getLastLoggedEvent().toString(), customer.log.containsString("Received HereIsYourTotal from waiter. Total = " + check));

		assertTrue("CashierBill should contain a bill with state: paying. Instead, the state is " + cashier.getChecks().get(0).getState(),
				cashier.getChecks().get(0).getState() == CashierAgent.checkState.paying);

		assertTrue("AmericanRestaurantCashier should have logged \"Received ReadyToPay\" but didn't. His log reads instead: " 
				+ cashier.log.getLastLoggedEvent().toString(), cashier.log.containsString("Received ReadyToPay"));

		assertTrue("CashierBill should contain a bill of price = $20. It contains something else instead: $" 
				+ cashier.getChecks().get(0).getCheck(), cashier.getChecks().get(0).getCheck() == 20);

		assertTrue("CashierBill should contain a bill with the right customer in it. It doesn't.", 
				cashier.getChecks().get(0).getCust1() == customer);

		
		//step 6, call americanRestaurantCashier scheduler

		assertTrue("AmericanRestaurantCashier's scheduler should have returned true (needs to react to customer's ReadyToPay), but didn't.", 
				cashier.pickAndExecuteAnAction());

		//Step 6 post-conditions, customer received change

		assertTrue("AmericanRestaurantMockCustomer should have logged an event for receiving \"HereIsYourChange\" with the correct change, but his last event logged reads instead: " 
				+ customer.log.getLastLoggedEvent().toString(), customer.log.containsString("Received HereIsYourChange from americanRestaurantCashier. Change = " + (cash-check)));

		assertEquals("CashierBill should contain 0 customer's bills. It doesn't.", cashier.getChecks().size(), 0);

		assertFalse("AmericanRestaurantCashier's scheduler should have returned false (no actions left to do), but didn't.", 
				cashier.pickAndExecuteAnAction());
	}

}







