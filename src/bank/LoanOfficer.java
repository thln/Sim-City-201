package bank;

import person.Person;
import person.Role;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bank.BankTeller.Account;

public class LoanOfficer extends Role {


	//Data

	double vault = 10000;
	double vaultMinimum = 1000;
	String name;
	enum LoanState {requesting, open, closed}
	class Loan {
		Account account1;
		LoanState state = LoanState.requesting;
		BankTeller teller1;
		
		Loan(Account acc1, BankTeller t1) {
			account1 = acc1;
			teller1 = t1;
		}
	}
	
	public List<Loan> loans;
	public List<Account> accounts;

	public LoanOfficer(String name, Person p1) {
		super(p1);
		this.name = name;
		loans = Collections.synchronizedList(new ArrayList<Loan>());
		accounts = Collections.synchronizedList(new ArrayList<Account>());
	}
	
	//Messages

	void msgIsLoanApproved(Account account1, BankTeller t1) {
		loans.add( new Loan(account1, t1));
	}


	// Scheduler

	protected boolean pickAndExecuteAnAction () {
		
		for (Loan loan1: loans) {
			if (loan1.state == LoanState.requesting)
				ProcessLoan(loan1);
		}
		
		return false;
	}


	//Actions

	void ProcessLoan (Loan loan1) {

		double loanAmount = loan1.account1.processingMoney;
		if (vault <=  vaultMinimum + loanAmount) {
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
