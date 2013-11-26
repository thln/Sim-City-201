package bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bank.BankTellerRole.Account;
import bank.interfaces.BankCustomer;
import bank.interfaces.BankGuard;
import bank.interfaces.BankTeller;
import bank.interfaces.LoanOfficer;
import bank.mock.BankGuardMock;
import bank.mock.BankTellerMock;
import bank.mock.LoanOfficerMock;
import application.Phonebook;
import application.WatchTime;
import person.Person;
import person.Role;
import person.Worker;
import application.gui.animation.*;

public class Bank
{

	//Data
	String name;

	//Open and closing times
	public WatchTime openTime = new WatchTime(8);
	public WatchTime closeTime = new WatchTime(17);

	//	Phonebook.getPhonebook().getBank().bankGuardRole.msgCustomerLeavingBank(myTeller);
	//Data
	public double vault;
	public double vaultMinimum;
	public List<Account> accounts;
	public int accountNumKeyList = 3000;

	//Roles
	public BankGuardRole bankGuardRole;
	public LoanOfficerRole loanOfficerRole;
	
	//Mocks roles for test
	public BankGuardMock bankGuardMock = new BankGuardMock("Bank Guard");
	public LoanOfficerMock loanOfficerMock = new LoanOfficerMock("Loan Officer");
	public List <BankTellerMock> mockTellers = new ArrayList<>();
	private BuildingPanel bankPanel;
	
	//Constructor
	public Bank(String name) {
		bankGuardRole  = new BankGuardRole("Bank Guard");
		loanOfficerRole =  new LoanOfficerRole("Loan Officer");
		//tellers  = new ArrayList<>();
		
		
		this.name = name;
		vault = 10000;
		vaultMinimum = 1000;
		accounts = Collections.synchronizedList(new ArrayList<Account>());
		//BankTellerRole t1 = new BankTellerRole ("BankTeller 1");
		//tellers.add(t1);
		//bankGuardRole.msgTellerCameToWork(t1);
			
		BankTellerMock t2 = new BankTellerMock ("BankTellerMock");
		mockTellers.add(t2);	
	}


	//Methods
	public Role arrivedAtWork(Person person, String title) {
		
		if (title == "bankGuard") {
			//Setting previous bank guard role to inactive
			if (bankGuardRole.getPerson() != null) {
				((Worker) bankGuardRole.getPerson()).roleFinishedWork();
			}
			//Setting bank guard role to new role
			bankGuardRole.setPerson(person);
			if (isOpen())
				bankGuardRole.msgBankOpen();
			return bankGuardRole;
		}
		else if (title == "loanOfficer") {
			//Setting previous bank guard role to inactive
			if (loanOfficerRole.getPerson() != null) {
			((Worker) loanOfficerRole.getPerson()).roleFinishedWork();
			}
			//Setting bank guard role to new role
			loanOfficerRole.setPerson(person);
			if (isOpen())
				bankGuardRole.msgBankOpen();
			return loanOfficerRole;
		}
		else if (title.equals("bankTeller")) {
			BankTellerRole t1 = new BankTellerRole(person.getName(), person, title);
			bankGuardRole.msgTellerCameToWork(t1);
			//Setting bank guard role to new role
			if (isOpen())
				bankGuardRole.msgBankOpen();
			return t1;
		}
		else
			return null;
	}

	public void goingOffWork(Person person) {
		Worker worker = (Worker) person;

		if (worker.getWorkerRole().equals(bankGuardRole)) {
			bankGuardRole = null;
		}
		else if (worker.getWorkerRole().equals(loanOfficerRole)) {
			loanOfficerRole = null;
		}
		else if (worker.getWorkerRole() instanceof BankTellerRole){
			bankGuardRole.msgTellerLeavingWork((BankTeller) worker.getWorkerRole());
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isOpen(){
		if (loanOfficerRole.getPerson() != null && bankGuardRole.getPerson() != null && bankGuardRole.getTellers().size() > 0)
			return true;
		else 
			return false;
	}


	public BankGuard getBankGuard(boolean test) {
		if (test)
			return bankGuardMock;
		else
			return bankGuardRole;
	}
	
	public LoanOfficer getLoanOfficer(boolean test) {
		if (test)
			return loanOfficerMock;
		else 
			return loanOfficerRole;
	}
	
	public void setPanel(BuildingPanel panel) {
		bankPanel = panel;
	}
}
