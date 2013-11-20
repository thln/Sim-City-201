package bank;

import person.Person;
import person.Role;
import application.Phonebook;
import bank.interfaces.LoanOfficer;

import java.util.List;

public class BankTellerRole extends Role {

//TODO
	//Finish find account function,
	
	//DATA
	int accountNumKeyList = 3000;
	List<Account> accounts;
	protected String RoleName = "Bank Teller";
	int balanceMinimum = 5;
	String name;
	
	enum AccountState {neutral, newAccount, waiting, depositing, withdrawing, requestingLoan, 
		closingLoan, loanApproved, loanDenied, leavingBank}

	class Account {	
		BankCustomerRole customer;
		private int accountNum; 		//the hash key
		double loan = 0;
		double balance = 0;
		double creditScore = 0;
		double processingMoney = 0;
		AccountState state = AccountState.newAccount;
		
		Account (BankCustomerRole c1) {
			customer = c1;
		}
		
		public void setAccountNum (int n){
			accountNum = n;
		}
	}

	public BankTellerRole (String name, Person p1, String roleName) {
		super(p1, name, roleName);
		
		accounts = Phonebook.getPhonebook().getBank().loanOfficerRole.getAccounts();
	}
	
	public BankTellerRole(String roleName) {
		super(roleName);
		accounts = Phonebook.getPhonebook().getBank().loanOfficerRole.getAccounts();
	}
	
	//MESSAGES

	

	void msgWantNewAccount (BankCustomerRole cust1) {	
		print("Customer wants new account");
		accounts.add(new Account(cust1));
		stateChanged();
	}

	void msgINeedMoney(double desiredAmount, int accountNum) {
		print("Customer approached Teller");
		Account correct = FindAccount (accountNum);
		correct.processingMoney = desiredAmount;
		correct.state = AccountState.withdrawing;
		stateChanged();
	}

	void msgHereIsMyDeposit(double amount, int accountNum) {
		print("Customer wants to deposit");
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
	
	void msgLeavingBank (int accountNum) {
		print("Customer Leaving");
		Account correct = FindAccount(accountNum);
		correct.state = AccountState.leavingBank;
		stateChanged();
	}

	//Scheduler

	protected boolean pickAndExecuteAnAction() {

		for (Account account1: accounts) {
			
			if (account1.state == AccountState.newAccount)	{
				OpenAccount(account1);
			}
			
			if (account1.state == AccountState.withdrawing)	{
				WithdrawMoney(account1);
			}
			
			if (account1.state == AccountState.depositing)	{
				DepositMoney(account1);
			}
			
			if (account1.state == AccountState.requestingLoan)	{
				RequestLoan(account1);
			}
			
			if (account1.state == AccountState.closingLoan)	{
				CloseLoan(account1);
			}
			
			if (account1.state == AccountState.loanApproved) {	
				ApproveLoan(account1);
			}
			
			if (account1.state == AccountState.loanDenied)	{
				DenyLoan(account1);
			}
			
			if (account1.state == AccountState.closingLoan)	{
				CloseLoan(account1);
			}
			
			if (account1.state == AccountState.leavingBank) {
				BecomeAvailable(account1);
				return false;
			}
		}
		
		return false;

	}



	//Actions

	Account FindAccount (int accNum) {
		for (Account a: accounts) {
			if (a.accountNum == accNum) {
				return a;		
			}
		}
		return null;
	}

	void OpenAccount (Account account1) {
		accountNumKeyList++;
		account1.setAccountNum(accountNumKeyList);
		account1.customer.msgHereIsNewAccount(account1.accountNum);
		account1.state = AccountState.neutral;
	}

	void WithdrawMoney(Account account1) {
		if (account1.balance > (account1.processingMoney + balanceMinimum) &&
				(account1.processingMoney < (Phonebook.getPhonebook().getBank().vault - Phonebook.getPhonebook().getBank().vaultMinimum))) {
			Phonebook.getPhonebook().getBank().vault -= account1.processingMoney;
			account1.state = AccountState.neutral;
			account1.customer.msgHereIsYourMoney(account1.processingMoney);
		}
		else if (account1.processingMoney > (Phonebook.getPhonebook().getBank().vault-Phonebook.getPhonebook().getBank().vaultMinimum))
			account1.customer.msgBankrupt();
		else
			account1.customer.msgInsufficentFunds();

		account1.processingMoney = 0;
		account1.state = AccountState.neutral;
	}

	void DepositMoney(Account account1) {
		Phonebook.getPhonebook().getBank().vault += account1.processingMoney;
		print("$" + account1.processingMoney + " deposited into bank vault");
		account1.balance +=  account1.processingMoney;
		account1.creditScore += account1.processingMoney/10;	//every time you deposit money, your credit goes up the bank can trust that you have money
		account1.customer.msgDepositReceived();
		account1.state = AccountState.neutral;	
	}

	void RequestLoan (Account account1) {
		Phonebook.getPhonebook().getBank().loanOfficerRole.msgIsLoanApproved(account1, this);
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
	
	void BecomeAvailable (Account account1) {
		account1.state = AccountState.neutral;
		Phonebook.getPhonebook().getBank().bankGuardRole.msgTellerBecameAvailable(this);
	}

	public double getVault() {
		return Phonebook.getPhonebook().getBank().vault;
	}

}
