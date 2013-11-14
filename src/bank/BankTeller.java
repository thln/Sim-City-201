package bank;

import person.Person;
import person.Role;

import java.util.List;

public class BankTeller extends Role {

//TODO
	//Finish find account function,
	
	//DATA

	List<Account> accounts;
	LoanOfficer myLoaner;
	int balanceMinimum = 5;
	String name;
	
	enum AccountState {neutral, newAccount, waiting, depositing, withdrawing, requestingLoan, closingLoan, loanApproved, loanDenied}

	class Account {	
		BankCustomer customer;
		int accountNum; 		//the hash key
		double loan = 0;
		double balance = 0;
		double creditScore = 0;
		double processingMoney = 0;
		AccountState state = AccountState.newAccount;
		
		Account (BankCustomer c1) {
			customer = c1;
		}
	}

	BankTeller (String name, Person p1, LoanOfficer officer1) {
		super(p1);
		this.name = name;
		myLoaner = officer1;
		accounts = myLoaner.getAccounts();
	}
	
	//MESSAGES

	void msgWantNewAccount (BankCustomer cust1) {
		accounts.add(new Account(cust1));
	}

	void msgWantToCloseAccount(int accountNum) {
		Account correct = FindAccount(accountNum);
		accounts.remove(correct);
	}

	void msgINeedMoney(double desiredAmount, int accountNum) {
		Account correct = FindAccount (accountNum);
		correct.processingMoney = desiredAmount;
		correct.state = AccountState.withdrawing;
		stateChanged();
	}

	void msgHereIsMyDeposit(double amount, int accountNum) {
		Account correct = FindAccount (accountNum);
		correct.processingMoney = amount;
		correct.state = AccountState.depositing;
		stateChanged();
	}

	void msgINeedALoan(double desiredLoan, int accountNum) {
		Account correct = FindAccount (accountNum);
		correct.processingMoney = desiredLoan;
		correct.state = AccountState.requestingLoan;
		stateChanged();
	}

	void msgPayingOffLoan(double loan, int accountNum) {
		Account correct = FindAccount (accountNum);
		correct.processingMoney = loan;
		correct.state = AccountState.closingLoan;
		stateChanged();
	}

	void msgThisLoanApproved(Account account1) {
		account1.state = AccountState.loanApproved;
		stateChanged();
	}

	void msgThisLoanDenied (Account account1, double possibleLoan) {
		account1.state = AccountState.loanDenied;
		account1.processingMoney = possibleLoan;
		stateChanged();
	}

	//Scheduler

	protected boolean pickAndExecuteAnAction() {

		for (Account account1: accounts) {
			
			if (account1.state == AccountState.newAccount)	
				OpenAccount(account1);
			
			if (account1.state == AccountState.withdrawing)	
				WithdrawMoney(account1);
			
			if (account1.state == AccountState.depositing)	
				DepositMoney(account1);
			
			if (account1.state == AccountState.requestingLoan)	
				RequestLoan(account1);
			
			if (account1.state == AccountState.closingLoan)	
				CloseLoan(account1);
			
			if (account1.state == AccountState.loanApproved)	
				ApproveLoan(account1);
			
			if (account1.state == AccountState.loanDenied)	
				DenyLoan(account1);
			
			if (account1.state == AccountState.closingLoan)	
				CloseLoan(account1);
		}
		
		return false;

	}



	//Actions

	Account FindAccount (int accountNum) {
		return null;
	}

	void OpenAccount (Account account1) {
		//generate key (accountNum)
		account1.customer.msgHereIsNewAccount(account1.accountNum);
		account1.state = AccountState.neutral;
	}

	void WithdrawMoney(Account account1) {
		if (account1.balance > (account1.processingMoney + balanceMinimum) &&
				(account1.processingMoney < (myLoaner.vault - myLoaner.vaultMinimum))) {
			myLoaner.vault -= account1.processingMoney;
			account1.state = AccountState.neutral;
			account1.customer.msgHereIsYourMoney(account1.processingMoney);
		}
		else if (account1.processingMoney > (myLoaner.vault-myLoaner.vaultMinimum))
			account1.customer.msgBankrupt();
		else
			account1.customer.msgInsufficentFunds();

		account1.processingMoney = 0;
		account1.state = AccountState.neutral;
	}

	void DepositMoney(Account account1) {
		myLoaner.vault += account1.processingMoney;
		account1.balance +=  account1.processingMoney;
		account1.creditScore += account1.processingMoney/10;	//every time you deposit money, your credit goes up the bank can trust that you have money
		account1.customer.msgDepositReceived();
		account1.state = AccountState.neutral;
	}

	void RequestLoan (Account account1) {
		myLoaner.msgIsLoanApproved(account1, this);
		account1.state = AccountState.waiting;
	}

	void CloseLoan (Account account1) {	
		account1.creditScore += account1.loan/10;
		account1.loan = 0;
		account1.state = AccountState.neutral;
		account1.customer.msgLoanClosed();
	}

	void ApproveLoan (Account account1) {
		account1.state = AccountState.neutral;
		account1.loan = account1.processingMoney;
		account1.balance += account1.loan;
		account1.customer.msgYourLoanWasApproved();
	}

	void DenyLoan (Account account1) {
		account1.state = AccountState.neutral;	
		account1.customer.msgYourLoanWasDenied(account1.processingMoney);	//loan denied, but given your credit score you can have a loan of size (processingMoney)
	}

	public double getVault() {
		return myLoaner.vault;
	}

}
