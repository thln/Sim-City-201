package bank;

import bank.interfaces.BankCustomer;
import bank.interfaces.BankTeller;
import application.Phonebook;
import person.Person;
import person.Role;
import person.Worker;

public class BankCustomerRole extends Role implements BankCustomer{

	//DATA

	enum BankCustomerDesire {none, withdraw, deposit, wantLoan, closeLoan, openAccount, closeAccount, robBank, leaveBank}
	enum CustomerState {atBank, none, waiting, ready};
	
	BankTeller myTeller;

	double desiredLoanAmount;
	double loan; 	
	BankCustomerDesire desire;
	CustomerState state;
	protected String RoleName = "Bank Customer";

	public BankCustomerRole (Person p1, String pName, String rName) {
		super(p1, pName, rName);
		desire = BankCustomerDesire.openAccount;
		state = CustomerState.atBank;
	}
	
	//Messages

	public void msgGoToTeller(BankTeller tell1) {
		myTeller = tell1;
		state = CustomerState.ready;
		stateChanged();
	}
	
	public void msgNoTellerAvailable(){
		
	}
	
	public void msgHereIsYourMoney(double amount) {
		person.money += amount;
		desire = BankCustomerDesire.leaveBank;
		state = CustomerState.ready;
		stateChanged();
	}

	public void msgHereIsNewAccount (int accountNum) {
		print("Received new bank account");
		person.accountNum = accountNum;
		desire = BankCustomerDesire.deposit;
		person.depositAmount = 100;
		state = CustomerState.ready;
		stateChanged();
	}

	public void msgBankrupt() {
		state = CustomerState.ready;
		desire = BankCustomerDesire.leaveBank;
		stateChanged();
	}

	public void msgInsufficentFunds(){
		state = CustomerState.ready;
		desiredLoanAmount = person.withdrawAmount*10;
		desire = BankCustomerDesire.wantLoan;
		stateChanged();
	}

	public void msgDepositReceived() {
		desire = BankCustomerDesire.leaveBank;
		state = CustomerState.ready;
		print("ready to leave bank");
		stateChanged();
	}

	public void msgYourLoanWasApproved() {
		desire = BankCustomerDesire.withdraw;
		state = CustomerState.ready;
		stateChanged();
	}

	public void msgYourLoanWasDenied(double amount) {
		//decide whether or not to request another loan
		state = CustomerState.ready;
		desire = BankCustomerDesire.leaveBank;
		stateChanged();
	}

	public void msgLoanClosed() {
		state = CustomerState.ready;
		desire = BankCustomerDesire.leaveBank;
		stateChanged();
	}	
	
	public void msgCaughtYou() {
		state = CustomerState.ready;
	}
	
	public void msgGotAway() {
		state = CustomerState.ready;
	}

	//Scheduler
	
	public boolean pickAndExecuteAnAction () {
		
	if (state == CustomerState.atBank)
		MessageGuard();

	if (desire == BankCustomerDesire.openAccount && state == CustomerState.ready)
		OpenAccount();
	
	if (desire == BankCustomerDesire.withdraw && state == CustomerState.ready)
		WithdrawCash();

	if (desire == BankCustomerDesire.deposit && state == CustomerState.ready)
		DepositCash();

	if (desire == BankCustomerDesire.wantLoan && state == CustomerState.ready)
		RequestLoan();

	if (desire == BankCustomerDesire.closeLoan && state == CustomerState.ready)
		PayOffLoan();

	if (desire == BankCustomerDesire.leaveBank && state == CustomerState.ready)
		LeaveBank();
	
	if (desire == BankCustomerDesire.robBank && state == CustomerState.ready)
		RobBank();

	return false;
	}

	//Actions

	void MessageGuard () {
		print("Arrived at bank");
		Phonebook.getPhonebook().getBank().bankGuardRole.msgArrivedAtBank(this);
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
		//GUI operation
		desire = BankCustomerDesire.none;
		state = CustomerState.waiting;	
		myTeller.msgLeavingBank(person.accountNum);
		Phonebook.getPhonebook().getBank().bankGuardRole.msgCustomerLeavingBank(myTeller);
		this.setRoleInactive();
	}

	void RobBank() {
		//Animation();
		Phonebook.getPhonebook().getBank().bankGuardRole.msgRobbingBank(this);
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
