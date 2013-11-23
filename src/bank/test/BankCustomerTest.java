package bank.test;

import person.Worker;
import bank.BankCustomerRole;
import bank.BankCustomerRole.BankCustomerDesire;
import bank.BankCustomerRole.CustomerState;
import bank.mock.BankGuardMock;
import bank.mock.BankTellerMock;
import bank.mock.LoanOfficerMock;
import junit.framework.TestCase;

public class BankCustomerTest extends TestCase{
	BankGuardMock guard;
	BankCustomerRole customer;
	LoanOfficerMock officer;
	BankTellerMock teller;
	Worker person;

	public void setUp() throws Exception {
		super.setUp();
		person = new Worker("Josh", 200, "bankTeller", "bank", 800, 1200, 1500);
		customer = new BankCustomerRole (person, person.getName(), "mockcustomer");
		guard = new BankGuardMock ("mockguard");		
		officer = new LoanOfficerMock ("mockofficer");
		teller = new BankTellerMock ("teller");		
	}

	public void testOneCustomerArrivesAtBankAssignedTeller() {
		try {
			setUp();
		}	catch (Exception e) {
			e.printStackTrace();
		}

		//Preconditions
		assertTrue("Customer should have the state 'atBank' ", 
				customer.state == CustomerState.atBank);

		assertTrue("Customer should not have a teller yet ", 
				customer.myTeller == null);

		//Step 1: Call scheduler, should execute method "openAccount"
		assertFalse("Customer's scheduler should have returned false, but didn't.", 
				customer.pickAndExecuteAnAction());

		//Step 1: post-conditions
		assertTrue("Customer should have the state 'waiting' ", 
				customer.state == CustomerState.waiting);	

		//Step 2: Guard assigns customer to teller
		customer.msgGoToTeller(teller);

		//Step 2 post-conditions
		assertTrue("Customer should have the state 'ready' ", 
				customer.state == CustomerState.ready);

		assertTrue("Customer should have been assigned proper teller ", 
				customer.myTeller == teller);
	}

	public void testTwoCustomerArrivesWantsDepositOpensAccount() {
		try {
			setUp();
		}	catch (Exception e) {
			e.printStackTrace();
		}

		//already tested that teller will be properly assigned

		customer.myTeller = teller;
		customer.state = CustomerState.ready;
		int accountNum = 3000;
		double depositAmt = 200;
		customer.person.depositAmount = depositAmt;

		//Preconditions
		assertTrue("Customer should have the state 'ready' ", 
				customer.state == CustomerState.ready);

		assertTrue("Customer should have the proper teller", 
				customer.myTeller == teller);

		assertTrue("Customer should have desire 'openAccount'",
				customer.desire == BankCustomerDesire.openAccount);


		//Step 1: Call scheduler, should execute method "openAccount"
		assertFalse("Customer's scheduler should have returned false, but didn't.", 
				customer.pickAndExecuteAnAction());

		//Step 1: post-conditions
		assertTrue("Customer should have the state 'waiting' ", 
				customer.state == CustomerState.waiting);

		assertTrue("MockTeller should have logged an event for new account, but his last event logged reads instead: " 
				+ teller.log.getLastLoggedEvent().toString(), teller.log.containsString("Customer wants new account."));

		//Step 2: teller messages customer with new account
		customer.msgHereIsNewAccount(accountNum);

		//Step 2 post-conditions
		assertTrue("Customer should have updated account number in Person", person.accountNum == accountNum);

		assertTrue("Customer should have desire 'deposit'",
				customer.desire == BankCustomerDesire.deposit);

		assertTrue("Customer should have the state 'ready' ", 
				customer.state == CustomerState.ready);

		//Step 3: Call scheduler, should execute method "depositCash"
		assertFalse("Customer's scheduler should have returned false, but didn't.", 
				customer.pickAndExecuteAnAction());

		//Step 3 post-conditions
		assertTrue("Customer should have the state 'waiting' ", 
				customer.state == CustomerState.waiting);

		assertTrue("MockTeller should have logged an event for new account, but his last event logged reads instead: " 
				+ teller.log.getLastLoggedEvent().toString(), teller.log.containsString("Received deposit of $" +depositAmt));

		//Step 4: teller messages customer that deposit received
		customer.msgDepositReceived();

		//Step 4 post-condition

		assertTrue("Customer should have desire 'leaveBank'",
				customer.desire == BankCustomerDesire.leaveBank);

		assertTrue("Customer should have the state 'ready' ", 
				customer.state == CustomerState.ready);


		//Step 5: Call scheduler, should execute method "leaveBank"
		assertFalse("Customer's scheduler should have returned false, but didn't.", 
				customer.pickAndExecuteAnAction());

		//step 5 post-conditions

		assertTrue("Customer should have desire 'none'",
				customer.desire == BankCustomerDesire.none);

		assertTrue("Customer should have the state 'waiting' ", 
				customer.state == CustomerState.waiting);

		assertTrue("MockTeller should have logged an event for customer leaving bank, but his last event logged reads instead: " 
				+ teller.log.getLastLoggedEvent().toString(), teller.log.containsString("Customer left bank."));

		assertTrue("Customer should removed reference to teller", 
				customer.myTeller == null);
	}

	public void testThreeCustomerWithdrawalSuccess() {
		try {
			setUp();
		}	catch (Exception e) {
			e.printStackTrace();
		}

		//already tested that teller will be properly assigned

		customer.myTeller = teller;
		customer.state = CustomerState.ready;
		customer.desire = BankCustomerDesire.withdraw;
		double withdrawAmt = 200;
		customer.person.withdrawAmount = withdrawAmt;
		double baseMoney = customer.person.money = 10;

		//Preconditions
		assertTrue("Customer should have the state 'ready' ", 
				customer.state == CustomerState.ready);

		assertTrue("Customer should have the proper teller", 
				customer.myTeller == teller);

		assertTrue("Customer should have desire 'withdraw'",
				customer.desire == BankCustomerDesire.withdraw);


		//Step 1: Call scheduler, should execute method "openAccount"
		assertFalse("Customer's scheduler should have returned false, but didn't.", 
				customer.pickAndExecuteAnAction());

		//Step 1 post-conditions
		assertTrue("Customer should have the state 'waiting' ", 
				customer.state == CustomerState.waiting);

		assertTrue("MockTeller should have logged an event for new account, but his last event logged reads instead: " 
				+ teller.log.getLastLoggedEvent().toString(), teller.log.containsString("Customer wants to withdraw from account."));

		//Step 2: teller messages customer with money
		customer.msgHereIsYourMoney(withdrawAmt);

		//Step 2 post-conditions
		assertEquals("Person's money should have increased by the withdrawal ammount",customer.person.money, baseMoney + withdrawAmt);

		//Step 3 post-condition

		assertTrue("Customer should have desire 'leaveBank'",
				customer.desire == BankCustomerDesire.leaveBank);

		assertTrue("Customer should have the state 'ready' ", 
				customer.state == CustomerState.ready);


		//Step 4: Call scheduler, should execute method "leaveBank"
		assertFalse("Customer's scheduler should have returned false, but didn't.", 
				customer.pickAndExecuteAnAction());

		//step 4 post-conditions

		assertTrue("Customer should have desire 'none'",
				customer.desire == BankCustomerDesire.none);

		assertTrue("Customer should have the state 'waiting' ", 
				customer.state == CustomerState.waiting);

		assertTrue("MockTeller should have logged an event for customer leaving bank, but his last event logged reads instead: " 
				+ teller.log.getLastLoggedEvent().toString(), teller.log.containsString("Customer left bank."));

		assertTrue("Customer should removed reference to teller", 
				customer.myTeller == null);
	}

	public void testFourCustomerWantsWithdrawalButNeedsLoanGetsLoan() {
		try {
			setUp();
		}	catch (Exception e) {
			e.printStackTrace();
		}

		//already tested that teller will be properly assigned

		customer.myTeller = teller;
		customer.state = CustomerState.ready;
		customer.desire = BankCustomerDesire.withdraw;
		double withdrawAmt = 200;
		customer.person.withdrawAmount = withdrawAmt;
		double baseMoney = customer.person.money = 10;

		//Preconditions
		assertTrue("Customer should have the state 'ready' ", 
				customer.state == CustomerState.ready);

		assertTrue("Customer should have the proper teller", 
				customer.myTeller == teller);

		assertTrue("Customer should have desire 'withdraw'",
				customer.desire == BankCustomerDesire.withdraw);


		//Step 1: Call scheduler, should execute method "openAccount"
		assertFalse("Customer's scheduler should have returned false, but didn't.", 
				customer.pickAndExecuteAnAction());

		//Step 1 post-conditions
		assertTrue("Customer should have the state 'waiting' ", 
				customer.state == CustomerState.waiting);

		assertTrue("MockTeller should have logged an event for new account, but his last event logged reads instead: " 
				+ teller.log.getLastLoggedEvent().toString(), teller.log.containsString("Customer wants to withdraw from account."));

		//Step 2: teller messages customer with money
		customer.msgHereIsYourMoney(withdrawAmt);

		//Step 2 post-conditions
		assertEquals("Person's money should have increased by the withdrawal ammount",customer.person.money, baseMoney + withdrawAmt);

		//Step 3 post-condition

		assertTrue("Customer should have desire 'leaveBank'",
				customer.desire == BankCustomerDesire.leaveBank);

		assertTrue("Customer should have the state 'ready' ", 
				customer.state == CustomerState.ready);


		//Step 4: Call scheduler, should execute method "leaveBank"
		assertFalse("Customer's scheduler should have returned false, but didn't.", 
				customer.pickAndExecuteAnAction());

		//step 4 post-conditions

		assertTrue("Customer should have desire 'none'",
				customer.desire == BankCustomerDesire.none);

		assertTrue("Customer should have the state 'waiting' ", 
				customer.state == CustomerState.waiting);

		assertTrue("MockTeller should have logged an event for customer leaving bank, but his last event logged reads instead: " 
				+ teller.log.getLastLoggedEvent().toString(), teller.log.containsString("Customer left bank."));

		assertTrue("Customer should removed reference to teller", 
				customer.myTeller == null);
	}

	public void testFiveCustomerWantsWithdrawalNeedsLoanButDenied() {
		try {
			setUp();
		}	catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testSixCustomerPaysOffLoan() {
		try {
			setUp();
		}	catch (Exception e) {
			e.printStackTrace();
		}	
		//already tested that teller will be properly assigned
		customer.myTeller = teller;
		customer.state = CustomerState.ready;
		customer.desire = BankCustomerDesire.closeLoan;		
		double baseMoney = 1000;
		double loan = 500;
		customer.person.loan = loan;
		customer.person.money = baseMoney;
		
		//Preconditions
		assertTrue("Customer should have the state 'ready' ", 
				customer.state == CustomerState.ready);

		assertTrue("Customer should have the proper teller", 
				customer.myTeller == teller);

		assertTrue("Customer should have desire 'closeLoan",
				customer.desire == BankCustomerDesire.closeLoan);


		//Step 1: Call scheduler, should execute method "payOffLoan"
		assertFalse("Customer's scheduler should have returned false, but didn't.", 
				customer.pickAndExecuteAnAction());

		//Step 1 post-conditions
		assertTrue("Customer should have the state 'waiting' ", 
				customer.state == CustomerState.waiting);
		
		assertEquals("Customer's person should decreased money by the loan amount", customer.person.money, baseMoney-loan);

		assertTrue("MockTeller should have logged an event for new account, but his last event logged reads instead: " 
				+ teller.log.getLastLoggedEvent().toString(), teller.log.containsString("Customer paid off loan."));

		//Step 2: teller messages customer that loan is closed
		customer.msgLoanClosed();
		
		//Step 2 post-conditions
		
		assertTrue("Customer should have desire 'leaveBank'",
				customer.desire == BankCustomerDesire.leaveBank);

		assertTrue("Customer should have the state 'ready' ", 
				customer.state == CustomerState.ready);


		//Step 3: Call scheduler, should execute method "leaveBank"
		assertFalse("Customer's scheduler should have returned false, but didn't.", 
				customer.pickAndExecuteAnAction());

		//step 3 post-conditions

		assertTrue("Customer should have desire 'none'",
				customer.desire == BankCustomerDesire.none);

		assertTrue("Customer should have the state 'waiting' ", 
				customer.state == CustomerState.waiting);

		assertTrue("MockTeller should have logged an event for customer leaving bank, but his last event logged reads instead: " 
				+ teller.log.getLastLoggedEvent().toString(), teller.log.containsString("Customer left bank."));

		assertTrue("Customer should removed reference to teller", 
				customer.myTeller == null);
		
	}

}
