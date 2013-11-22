package bank.test;

import bank.BankGuardRole;
import bank.mock.BankCustomerMock;
import bank.mock.BankTellerMock;
import junit.framework.TestCase;

public class GuardTest extends TestCase
{

	BankGuardRole guard;
	BankCustomerMock customer;
	BankTellerMock teller;

	public void setUp() throws Exception {
		super.setUp();
		guard = new BankGuardRole ("bankguard");
		customer = new BankCustomerMock ("mockcustomer");
		teller = new BankTellerMock ("bankteller");
	}

	public void testOneNormalTellerArrivesCustomerArrives() {
		try {
			setUp();
		}	catch (Exception e) {
			e.printStackTrace();
		}


		//check preconditions
		assertEquals("Guard should have 0 customers in list. It doesn't.", guard.getCustomers().size(), 0);
		assertEquals("Guard should have 0 tellers in list. It doesn't.", guard.getTellers().size(), 0); 

		//Step 1: Teller comes to work, add teller to the bank guard's list
		guard.msgTellerCameToWork (teller); 

		 
		//Step 1 post-conditions
		assertEquals("Guard should have 1 teller in list. It doesn't.", guard.getTellers().size(), 1); 	
		assertTrue("1st MockTeller should have the state 'available' ", 
				guard.getTellers().get(0).getState() == BankGuardRole.TellerState.available);
		
		//Step 2: Customer arrives at bank
		guard.msgArrivedAtBank(customer);
		
		//Step 2 post-conditions
		assertEquals("Guard should have 1 customer in list. It doesn't.", guard.getCustomers().size(), 1);
		
		//Step 3: Scheduler runs...should execute method "assignToTeller"
		guard.pickAndExecuteAnAction();
		
		//Step 3 post-conditions
		assertTrue("MockCustomer should have logged an event for being assigned to teller, but his last event logged reads instead: " 
				+ customer.log.getLastLoggedEvent().toString(), 
			customer.log.containsString("Assigned to bank teller " + teller.getName()));

		assertTrue("MockTeller shoudl have the state 'busy'",
				guard.getTellers().get(0).getState() == BankGuardRole.TellerState.busy);
		
		assertEquals("Guard should have 0 customers in list. It doesn't.", guard.getCustomers().size(), 0);
		
		assertFalse("Guard's scheduler should have returned false, but didn't.", 
				guard.pickAndExecuteAnAction());
		
		//Step 4: customer leaves bank
		
		guard.msgCustomerLeavingBank(teller) ;
		
		//Step 4 post-conditions
		
		assertTrue("1st MockTeller should have the state 'available' ", 
				guard.getTellers().get(0).getState() == BankGuardRole.TellerState.available);
		
		assertTrue("Guard's scheduler should have returned true (must check everytime a teller becomes available)  but didn't.", 
				guard.pickAndExecuteAnAction());
	}
}
