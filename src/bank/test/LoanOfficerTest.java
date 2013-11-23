package bank.test;

import person.Worker;
import bank.LoanOfficerRole;
import bank.BankTellerRole.Account;
import bank.LoanOfficerRole.LoanState;
import bank.mock.BankCustomerMock;
import bank.mock.BankTellerMock;
import junit.framework.TestCase;

public class LoanOfficerTest extends TestCase{

	BankTellerMock teller;
	BankCustomerMock customer;
	LoanOfficerRole officer;
	Worker person;

	public void setUp() throws Exception {
		super.setUp();
		person = new Worker("Josh", 200, "loanOfficer", "bank", 800, 1200, 1500);
		teller = new BankTellerMock ("bankteller");		
		customer = new BankCustomerMock("bankcustomer");
		officer = new LoanOfficerRole ("loanofficer");
	}

	public void testOneLoanRequestedAndApprove() {
		try {
			setUp();
		}	catch (Exception e) {
			e.printStackTrace();
		}

		teller.account1 = new Account(null);
		
		//check preconditions
		assertEquals("Officer should have 0 loans in loan list. It doesn't.", officer.getLoans().size(), 0);
		assertEquals("Officer should have 0 accounts in account list. It doesn't.", officer.getAccounts().size(), 0);

		//Step 1: teller messages Officer with loan request
		officer.msgIsLoanApproved(teller.account1,  teller);

		//Step 1 post-conditions
		assertEquals("Officer should have 1 loan in loan list. It doesn't.", officer.getLoans().size(), 1);
		assertTrue("First loan in the loan list should have the state 'requesting', but didn't",
				officer.getLoans().get(0).getState() == LoanState.requesting);

		//Step 2: call scheduler and run method ProcessLoan
		assertFalse("Officer's scheduler should have returned false, but didn't.", 
				officer.pickAndExecuteAnAction());

		//Step 2 post-conditions
		assertTrue("MockTeller should have logged an event for receiving loan approval, but his last event logged reads instead: " 
				+ teller.log.getLastLoggedEvent().toString(), teller.log.containsString("My customer's loan was approved"));
		assertEquals("Officer should have 0 loans in loan list. It doesn't.", officer.getLoans().size(), 0);
	}
}