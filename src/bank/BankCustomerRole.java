package bank;

import application.Phonebook;
import person.Person;
import person.Role;

public class BankCustomerRole extends Role {

	//DATA

	enum BankCustomerDesire {none, withdraw, deposit, wantLoan, closeLoan, openAccount, closeAccount, robBank, leaveBank}
	enum CustomerState {atBank, none, waiting, ready};
	
	BankTellerRole myTeller;
//	BankGuardRole myGuard;

//	double cash;
//	int accountNum;
//	double accountBalance;
	double desiredLoanAmount;
	double loan; 	
	BankCustomerDesire desire;
	CustomerState state;

	public BankCustomerRole (Person p1, String pName, String rName) {
		super(p1, pName, rName);
		desire = BankCustomerDesire.openAccount;
		state = CustomerState.atBank;
	}
	
	//Messages

	void msgGoToTeller(BankTellerRole tell1) {
		myTeller = tell1;
		state = CustomerState.ready;
		stateChanged();
	}
	
	void msgHereIsYourMoney(double amount) {
		person.money += amount;
		desire = BankCustomerDesire.leaveBank;
		state = CustomerState.ready;
		stateChanged();
	}

	void msgHereIsNewAccount (int accountNum) {
		print("Received new bank account");
		person.accountNum = accountNum;
		desire = BankCustomerDesire.deposit;
		state = CustomerState.ready;
		stateChanged();
	}

	void msgBankrupt() {
		state = CustomerState.ready;
	}

	void msgInsufficentFunds(){
		state = CustomerState.ready;
		desiredLoanAmount = person.withdrawAmount*10;
		desire = BankCustomerDesire.wantLoan;
		stateChanged();
	}
//finish setting loan and teller interactions, make sure everything is phonebook global or person data
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
		Phonebook.bank.bankGuardRole.msgArrivedAtBank(this);
		state = CustomerState.waiting;
	}
	
	void WithdrawCash() {
		myTeller.msgINeedMoney(person.desiredCash,person.accountNum);
		state = CustomerState.waiting;
	}

	void DepositCash () {
		myTeller.msgHereIsMyDeposit(person.depositAmount, person.accountNum);
		state = CustomerState.waiting;
	}

	void RequestLoan () {
		desiredLoanAmount = 10*person.withdrawAmount;
		myTeller.msgINeedALoan(desiredLoanAmount, person.accountNum);
		state = CustomerState.waiting;
	}

	void PayOffLoan() {
		person.money -= loan;
		myTeller.msgPayingOffLoan(loan, person.accountNum);
		state = CustomerState.waiting;
	}

	void OpenAccount () {
		myTeller.msgWantNewAccount(this);
		state = CustomerState.waiting;
	}
	
	void LeaveBank () {
		desire = BankCustomerDesire.none;
		myTeller.msgLeavingBank(person.accountNum);
		state = CustomerState.waiting;
		person.setRoleInactive(this);
	}

	void RobBank() {
		//Animation();
		Phonebook.bank.bankGuardRole.msgRobbingBank(this);
		state = CustomerState.waiting;
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
