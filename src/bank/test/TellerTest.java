package bank.test;

import person.Worker;
import application.Phonebook;
import bank.BankTellerRole;
import bank.BankTellerRole.Account;
import bank.BankTellerRole.AccountState;
import bank.mock.BankCustomerMock;
import bank.mock.BankGuardMock;
import bank.mock.LoanOfficerMock;
import junit.framework.TestCase;

public class TellerTest extends TestCase 
{

	BankGuardMock guard;
	BankCustomerMock customer;
	LoanOfficerMock officer;
	BankTellerRole teller;
	Worker person;

	public void setUp() throws Exception {
		super.setUp();
		person = new Worker("Josh", 200, "bankTeller", "bank", 800, 1200, 1500);
		guard = new BankGuardMock ("mockguard");
		customer = new BankCustomerMock ("mockcustomer");
		officer = new LoanOfficerMock ("mockofficer");
		teller = new BankTellerRole (person.getName(), person, "bankteller");		
	}

	public void testOneCustomerArrivesWantsDepositOpensAccount() {
		try {
			setUp();
		}	catch (Exception e) {
			e.printStackTrace();
		}
		double depositAmt = 200;
		double vault = Phonebook.getPhonebook().getBank().vault;
	
		//check preconditions in teller's personal list of accounts
		assertEquals("Teller should have 0 accounts in list. It doesn't.", teller.getAccounts().size(), 0);
		
		assertEquals("Account hash key should be at base number 3000. It isn't.", 
				Phonebook.getPhonebook().getBank().accountNumKeyList, 3000);
	
		//Step 1: Customer arrives and wants to open new account
		teller.msgWantNewAccount(customer);
		
		//Step 1 post-conditions
		assertEquals("Teller should have 1 account in list. It doesn't.", teller.getAccounts().size(), 1);
		
		assertTrue("New account should have the state 'newAccount' ", 
				teller.getAccounts().get(0).getState() == AccountState.newAccount);
				
		
		//Step 2: Call scheduler, should execute method "openAccount"
		assertFalse("Guard's scheduler should have returned false, but didn't.", 
								teller.pickAndExecuteAnAction());
		
		//Step 2 post-conditions
		assertEquals("The list of account hash key should have increased ", 
				Phonebook.getPhonebook().getBank().accountNumKeyList, 3001);
		
		assertEquals("New Account's 'accountNum' should be set to this new hashKey",
				teller.getAccounts().get(0).getAccountNum(), Phonebook.getPhonebook().getBank().accountNumKeyList);	
		
		assertTrue("MockCustomer should have logged an event for new account, but his last event logged reads instead: " 
					+ customer.log.getLastLoggedEvent().toString(), customer.log.containsString("New Account created"));
		
		assertTrue("New account should have the state 'neutral' ", 
				teller.getAccounts().get(0).getState() == AccountState.neutral);
		
		//Step 3: Customer wants to deposit money into new account
		teller.msgHereIsMyDeposit(depositAmt, teller.getAccounts().get(0).accountNum);
		
		//Step 3 post-conditions
		assertTrue("Account should have the state 'depositing' ", 
				teller.getAccounts().get(0).getState() == AccountState.depositing);
		
		//Step 4: Call scheduler, should execute method "depositMoney"
		assertFalse("Guard's scheduler should have returned false, but didn't.", 
				teller.pickAndExecuteAnAction());
			
		
		//Step 4 post-conditions
	
		assertEquals("Bank vault should be equal to the original vault amount + deposit amount",
				Phonebook.getPhonebook().getBank().vault, vault + depositAmt);	
		
		assertEquals("Account balance should have increased by the deposit amount",
				teller.getAccounts().get(0).balance, depositAmt);
		
		assertEquals("Account credit score should have increased by 1/10 of the deposit amount",
				teller.getAccounts().get(0).creditScore, depositAmt/10);
		
		assertTrue("MockCustomer should have logged an event for deposit received, but his last event logged reads instead: " 
				+ customer.log.getLastLoggedEvent().toString(), 
				customer.log.containsString("Your deposit of $" + depositAmt + " was received."));
		
		assertTrue("New account should have the state 'neutral' ", 
				teller.getAccounts().get(0).getState() == AccountState.neutral);
		
		//Step 5: customer leaves bank
		teller.msgLeavingBank(teller.getAccounts().get(0).getAccountNum());
		
		//Step 5 post-conditions
		
		assertEquals("Teller should have 0 accounts in list. It doesn't.", teller.getAccounts().size(), 0);
	}
	
	public void testTwoCustomerWantsWithdrawal() {
		try {
			setUp();
		}	catch (Exception e) {
			e.printStackTrace();
		}
		double depositAmt = 200;
		double vault = Phonebook.getPhonebook().getBank().vault;
	
		//check preconditions in teller's personal list of accounts
		assertEquals("Teller should have 0 accounts in list. It doesn't.", teller.getAccounts().size(), 0);
		
		assertEquals("Account hash key should be at base number 3000. It isn't.", 
				Phonebook.getPhonebook().getBank().accountNumKeyList, 3000);
	
		//Step 1: Customer arrives and wants to open new account
		teller.msgWantNewAccount(customer);
		
		//Step 1 post-conditions
		assertEquals("Teller should have 1 account in list. It doesn't.", teller.getAccounts().size(), 1);
		
		assertTrue("New account should have the state 'newAccount' ", 
				teller.getAccounts().get(0).getState() == AccountState.newAccount);
				
		
		//Step 2: Call scheduler, should execute method "openAccount"
		assertFalse("Guard's scheduler should have returned false, but didn't.", 
								teller.pickAndExecuteAnAction());
		
		//Step 2 post-conditions
		assertEquals("The list of account hash key should have increased ", 
				Phonebook.getPhonebook().getBank().accountNumKeyList, 3001);
		
		assertEquals("New Account's 'accountNum' should be set to this new hashKey",
				teller.getAccounts().get(0).getAccountNum(), Phonebook.getPhonebook().getBank().accountNumKeyList);	
		
		assertTrue("MockCustomer should have logged an event for new account, but his last event logged reads instead: " 
					+ customer.log.getLastLoggedEvent().toString(), customer.log.containsString("New Account created"));
		
		assertTrue("New account should have the state 'neutral' ", 
				teller.getAccounts().get(0).getState() == AccountState.neutral);
		
		//Step 3: Customer wants to deposit money into new account
		teller.msgHereIsMyDeposit(depositAmt, teller.getAccounts().get(0).accountNum);
		
		//Step 3 post-conditions
		assertTrue("Account should have the state 'depositing' ", 
				teller.getAccounts().get(0).getState() == AccountState.depositing);
		
		//Step 4: Call scheduler, should execute method "depositMoney"
		assertFalse("Guard's scheduler should have returned false, but didn't.", 
				teller.pickAndExecuteAnAction());
			
		
		//Step 4 post-conditions
	
		assertEquals("Bank vault should be equal to the original vault amount + deposit amount",
				Phonebook.getPhonebook().getBank().vault, vault + depositAmt);	
		
		assertEquals("Account balance should have increased by the deposit amount",
				teller.getAccounts().get(0).balance, depositAmt);
		
		assertEquals("Account credit score should have increased by 1/10 of the deposit amount",
				teller.getAccounts().get(0).creditScore, depositAmt/10);
		
		assertTrue("MockCustomer should have logged an event for deposit received, but his last event logged reads instead: " 
				+ customer.log.getLastLoggedEvent().toString(), 
				customer.log.containsString("Your deposit of $" + depositAmt + " was received."));
		
		assertTrue("New account should have the state 'neutral' ", 
				teller.getAccounts().get(0).getState() == AccountState.neutral);
		
		//Step 5: customer leaves bank
		teller.msgLeavingBank(teller.getAccounts().get(0).getAccountNum());
		
		//Step 5 post-conditions
		
		assertEquals("Teller should have 0 accounts in list. It doesn't.", teller.getAccounts().size(), 0);
	}
	
	public void testThreeCustomerWantsWithdrawalButNeedsLoan() {
		try {
			setUp();
		}	catch (Exception e) {
			e.printStackTrace();
		}
	}
}

