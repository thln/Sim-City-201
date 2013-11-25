package bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bank.BankTellerRole.Account;
import bank.interfaces.BankGuard;
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
	public BankGuardRole bankGuardRole = new BankGuardRole("Bank Guard");
	public LoanOfficerRole loanOfficerRole = new LoanOfficerRole("Loan Officer");
	public List <BankTellerRole> tellers = new ArrayList<>();

	//Mocks roles for test
	public BankGuardMock bankGuardMock = new BankGuardMock("Bank Guard");
	public LoanOfficerMock loanOfficerMock = new LoanOfficerMock("Loan Officer");
	public List <BankTellerMock> mockTellers = new ArrayList<>();
	private BuildingPanel bankPanel;
	
	//Constructor
	public Bank(String name) {
		this.name = name;
		vault = 10000;
		vaultMinimum = 1000;
		accounts = Collections.synchronizedList(new ArrayList<Account>());
		BankTellerRole t1 = new BankTellerRole ("BankTeller 1");
		tellers.add(t1);
		BankTellerMock t2 = new BankTellerMock ("BankTellerMock");
		mockTellers.add(t2);
		bankGuardRole.msgTellerCameToWork(t1);
	}


	//Methods
	public Role arrivedAtWork(Person person, String title) {
		if (title == "bankGuard") {
			//Setting previous bank guard role to inactive
			if (bankGuardRole.getPerson() != null) {
				Worker worker = (Worker) bankGuardRole.getPerson();
				worker.roleFinishedWork();
			}
			//Setting bank guard role to new role
			bankGuardRole.setPerson(person);
			return bankGuardRole;
		}
		else if (title == "loanOfficer") {
			//Setting previous bank guard role to inactive
			if (loanOfficerRole.getPerson() != null) {
				Worker worker = (Worker) loanOfficerRole.getPerson();
				worker.roleFinishedWork();
			}
			//Setting bank guard role to new role
			loanOfficerRole.setPerson(person);
			return loanOfficerRole;
		}
		else if (title == "bankTeller") {
			//Setting previous bank guard role to inactive
			for (BankTellerRole r1: tellers) {
				if (r1.getPerson() != null) {
					Worker worker = (Worker) loanOfficerRole.getPerson();
					worker.roleFinishedWork();
				}
			}
			//Setting bank guard role to new role
			tellers.get(0).setPerson(person);
			return tellers.get(0);
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
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
