package bank;

import bank.BankGuardRole.MyTeller;
import bank.BankGuardRole.TellerState;
import person.Person;
import person.Role;

public class BankCustomerRole extends Role {

	//DATA

	enum BankCustomerDesire {none, withdraw, deposit, wantLoan, closeLoan, openAccount, closeAccount, robBank, leaveBank}
	enum CustomerState {atBank, none, waiting, ready};
	
	BankTellerRole myTeller;
	BankGuardRole myGuard;

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

	public BankCustomerRole (String name, Person p1, BankGuardRole guard1, int desiredCash, int deposit, int accNum, int cash) {
		super(p1);
		desiredCashAmount = desiredCash;
		depositAmount = deposit;
		accountNum = accNum;
		this.cash = cash;
		desire = BankCustomerDesire.openAccount;
		state = CustomerState.atBank;
		myGuard = guard1;
	}
	
	//Messages

	void msgGoToTeller(BankTellerRole tell1) {
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
		print("Received new bank account");
		this.accountNum = accountNum;
		desire = BankCustomerDesire.deposit;
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
		desire = BankCustomerDesire.leaveBank;
		state = CustomerState.ready;
		print("ready to leave bank");
		stateChanged();
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

	if (desire == BankCustomerDesire.leaveBank && state == CustomerState.ready)
		LeaveBank();
	
	if (desire == BankCustomerDesire.robBank && state == CustomerState.ready)
		RobBank();

	return false;
	}

	//Actions

	void MessageGuard () {
		print("Arrived at bank");
		myGuard.msgArrivedAtBank(this);
		state = CustomerState.waiting;
	}
	
	void WithdrawCash() {
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
	
	void LeaveBank () {
		desire = BankCustomerDesire.none;
		myTeller.msgLeavingBank(accountNum);
		state = CustomerState.waiting;
		person.setRoleInactive(this);
	}

	void RobBank() {
		//Animation();
		myGuard.msgRobbingBank(this);
		state = CustomerState.waiting;
	}
	
	public void setDesiredCash(int d1) {
		desiredCashAmount = d1;
	}
	
	public void setDesire(String d1){
		if (d1 == "deposit")
			desire = BankCustomerDesire.deposit;
		if (d1 == "withdraw")
			desire = BankCustomerDesire.withdraw;
		if (d1 == "robBank")
			desire = BankCustomerDesire.robBank;
	}
}
