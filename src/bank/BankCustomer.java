package bank;

import bank.BankGuard.MyTeller;
import bank.BankGuard.TellerState;
import person.Person;
import person.Role;

public class BankCustomer extends Role {

	//DATA

	enum BankCustomerDesire {none, withdraw, deposit, wantLoan, closeLoan, openAccount, closeAccount, robBank, leaveBank}
	enum CustomerState {atBank, none, waiting, ready};
	
	BankTeller myTeller;
	BankGuard myGuard;

	double cash;
	int accountNum;
	double accountBalance;
	double desiredLoanAmount;
	double desiredCashAmount;
	double depositAmount;
	double loan; 	
	BankCustomerDesire desire;
	CustomerState state;
	protected String RoleName = "Bank Customer";

	public BankCustomer (String name, Person p1, BankGuard guard1, int desiredCash, int deposit, int accNum, int cash) {
		super(p1);
		desiredCashAmount = desiredCash;
		depositAmount = deposit;
		accountNum = accNum;
		this.cash = cash;
		desire = BankCustomerDesire.withdraw;
		state = CustomerState.atBank;
		myGuard = guard1;
	}
	
	//Messages

	void msgGoToTeller(BankTeller tell1) {
		myTeller = tell1;
		state = CustomerState.ready;
		stateChanged();
	}
	
	void msgHereIsYourMoney(double amount) {
		cash += amount;
		desire = BankCustomerDesire.leaveBank;
		state = CustomerState.ready;
		stateChanged();
	}

	void msgHereIsNewAccount (int accountNum) {
		this.accountNum = accountNum;
		state = CustomerState.ready;
		stateChanged();
	}

	void msgBankrupt() {
		state = CustomerState.ready;
	}

	void msgInsufficentFunds(){
		state = CustomerState.ready;
	}

	void msgDepositReceived() {
		state = CustomerState.ready;
	}

	void msgYourLoanWasApproved() {
		desire = BankCustomerDesire.withdraw;
		state = CustomerState.ready;
		stateChanged();
	}

	void msgYourLoanWasDenied(double amount) {
		//decide whether or not to request another loan
		state = CustomerState.ready;
	}

	void msgLoanClosed() {
		state = CustomerState.ready;
	}	
	
	void msgCaughtYou() {
		state = CustomerState.ready;
	}
	
	void msgGotAway() {
		state = CustomerState.ready;
	}

	//Scheduler
	
	protected boolean pickAndExecuteAnAction () {
		
	if (state == CustomerState.atBank)
		MessageGuard();

	if (desire == BankCustomerDesire.withdraw && state == CustomerState.ready)
		WithdrawCash();

	if (desire == BankCustomerDesire.deposit && state == CustomerState.ready)
		DepositCash();

	if (desire == BankCustomerDesire.wantLoan && state == CustomerState.ready)
		RequestLoan();

	if (desire == BankCustomerDesire.closeLoan && state == CustomerState.ready)
		PayOffLoan();

	if (desire == BankCustomerDesire.openAccount && state == CustomerState.ready)
		OpenAccount();

	if (desire == BankCustomerDesire.closeAccount && state == CustomerState.ready)
		CloseAccount();

	if (desire == BankCustomerDesire.robBank && state == CustomerState.ready)
		RobBank();

	return false;
	}

	//Actions

	void MessageGuard () {
		System.out.println("Arrived at bank");
		myGuard.msgArrivedAtBank(this);
		state = CustomerState.waiting;
	}
	
	void WithdrawCash() {
		System.out.println("Received withdrawal");
		myTeller.msgINeedMoney(desiredCashAmount,accountNum);
		state = CustomerState.waiting;
	}

	void DepositCash () {
		myTeller.msgHereIsMyDeposit(depositAmount, accountNum);
		state = CustomerState.waiting;
	}

	void RequestLoan () {
		desiredLoanAmount = 10*desiredCashAmount;
		myTeller.msgINeedALoan(desiredLoanAmount, accountNum);
		state = CustomerState.waiting;
	}

	void PayOffLoan() {
		cash -= loan;
		myTeller.msgPayingOffLoan(loan, accountNum);
		state = CustomerState.waiting;
	}

	void OpenAccount () {
		myTeller.msgWantNewAccount(this);
		state = CustomerState.waiting;
	}

	void CloseAccount() {
		myTeller.msgWantToCloseAccount(accountNum);
		accountNum = 0;
		state = CustomerState.waiting;
	}

	void RobBank() {
		//Animation();
		myGuard.msgRobbingBank(this);
		state = CustomerState.waiting;
	}
}
