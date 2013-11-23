package bank;

import person.Person;
import person.Role;
import person.Worker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import application.Phonebook;
import bank.BankTellerRole.Account;
import bank.interfaces.LoanOfficer;

public class LoanOfficerRole extends Role implements LoanOfficer {


	//Data

	String name;
	protected String RoleName = "Loan Officer";
	enum LoanState {requesting, open, closed}
	class Loan {
		Account account1;
		LoanState state = LoanState.requesting;
		BankTellerRole teller1;
		
		Loan(Account acc1, BankTellerRole t1) {
			account1 = acc1;
			teller1 = t1;
		}
	}
	
	public List<Loan> loans;
	public List<Account> accounts;

	public LoanOfficerRole(String name, Person p1, String roleName) {
		super(p1, name, roleName);
		loans = Collections.synchronizedList(new ArrayList<Loan>());
		accounts = Collections.synchronizedList(new ArrayList<Account>());
	}
	
	public LoanOfficerRole(String roleName) {
		super(roleName);
		loans = Collections.synchronizedList(new ArrayList<Loan>());
		accounts = Collections.synchronizedList(new ArrayList<Account>());
	}
	
	//Messages
	void msgIsLoanApproved(Account account1, BankTellerRole t1) {
		loans.add( new Loan(account1, t1));
	}


	// Scheduler

	public boolean pickAndExecuteAnAction () {
		
		for (Loan loan1: loans) {
			if (loan1.state == LoanState.requesting)
				ProcessLoan(loan1);
		}
		
		if (leaveRole){
			((Worker) person).roleFinishedWork();
			leaveRole = false;
			return true;
		}
		
		return false;
	}


	//Actions

	void ProcessLoan (Loan loan1) {

		double loanAmount = loan1.account1.processingMoney;
		if (Phonebook.getPhonebook().getBank().vault <=  Phonebook.getPhonebook().getBank().vaultMinimum + loanAmount) {
			loan1.teller1.msgThisLoanDenied(loan1.account1, 0);
		}

		if (loanAmount > (loan1.account1.creditScore * 10))		//You are allowed a loan 10 times your credit score
				loan1.teller1.msgThisLoanApproved(loan1.account1);

		else
			loan1.teller1.msgThisLoanDenied(loan1.account1, loan1.account1.creditScore*10); 

	}

	public List<Account> getAccounts() {
		return accounts;
	}

}
