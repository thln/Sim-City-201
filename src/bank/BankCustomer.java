package bank;

import person.Role;

public class BankCustomer extends Role {


	//Can be implemented as a person or as a business liaison. 
	//If business liaison, they have different accountNum, different cash, loan ,etc.
	//people request loans when desired cash is less than account balance

	//DATA

	enum BankCustomerDesire {none, withdraw, deposit, wantLoan, closeLoan, openAccount, closeAccount, robBank}

	BankTeller myTeller;

	double cash;
	int accountNum;
	double accountBalance;
	double desiredLoanAmount;
	double desiredCashAmount;
	double depositAmount;
	double loan; 	
	BankCustomerDesire desire;

	BankCustomer () {
		super();
		desire = BankCustomerDesire.none;
		myTeller = new BankTeller();
	}
	
	//Messages

	void msgHereIsYourMoney(double amount) {
		cash += amount;
	}

	void msgHereIsNewAccount (int accountNum) {
		this.accountNum = accountNum;
	}

	void msgBankrupt() {
		
	}

	void msgInsufficentFunds(){

	}

	void msgDepositReceived() {

	}

	void msgYourLoanWasApproved() {
		desire = BankCustomerDesire.withdraw;
		stateChanged();
	}

	void msgYourLoanWasDenied(double amount) {
		//decide whether or not to request another loan
	}

	void msgLoanClosed() {

	}	

	//Scheduler
	
	protected boolean pickAndExecuteAnAction () {
		
		

	if (desire == BankCustomerDesire.withdraw)
		WithdrawCash();

	if (desire == BankCustomerDesire.deposit)
		DepositCash();

	if (desire == BankCustomerDesire.wantLoan)
		RequestLoan();

	if (desire == BankCustomerDesire.closeLoan)
		PayOffLoan();

	if (desire == BankCustomerDesire.openAccount)
		OpenAccount();

	if (desire == BankCustomerDesire.closeAccount)
		CloseAccount();

	if (desire == BankCustomerDesire.robBank)
		RobBank();

	return false;
	}

	//Actions

	void WithdrawCash() {
		myTeller.msgINeedMoney(desiredCashAmount,accountNum);
	}

	void DepositCash () {
		myTeller.msgHereIsMyDeposit(depositAmount, accountNum);
	}

	void RequestLoan () {
		desiredLoanAmount = 10*desiredCashAmount;
		myTeller.msgINeedALoan(desiredLoanAmount, accountNum);
	}

	void PayOffLoan() {
		cash -= loan;
		myTeller.msgPayingOffLoan(loan, accountNum);
	}

	void OpenAccount () {
		myTeller.msgWantNewAccount(this);
	}

	void CloseAccount() {
		myTeller.msgWantToCloseAccount(accountNum);
		accountNum = 0;
	}

	void RobBank() {
		//Animation();
		cash += myTeller.getVault()/10;
	}

	public void stateChanged() {
//		super.stateChanged();
	}

}
