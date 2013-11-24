package bank;

import bank.interfaces.BankCustomer;
import bank.interfaces.BankTeller;
import application.Phonebook;
import person.Person;
import person.Role;
import person.Worker;

public class BankCustomerRole extends Role implements BankCustomer{

	//DATA

	public enum BankCustomerDesire {none, withdraw, deposit, wantLoan, closeLoan, openAccount, closeAccount, robBank, leaveBank}
	public enum CustomerState {atBank, none, waiting, ready};

	public BankTeller myTeller;

	public double desiredLoanAmount;
	public BankCustomerDesire desire;
	public CustomerState state;
	protected String RoleName = "Bank Customer";

	public BankCustomerRole (Person p1, String pName, String rName) {
		super(p1, pName, rName);
		desire = BankCustomerDesire.openAccount;
		state = CustomerState.atBank;
	}

	//Messages

	public void msgGoToTeller(BankTeller tell1) {
		print ("Assigned to teller" + tell1.getName());
		myTeller = tell1;
		state = CustomerState.ready;
		stateChanged();
	}

	public void msgNoTellerAvailable(){

	}

	public void msgHereIsYourMoney(double amount) {
		print("Got my money! Leaving bank");
		person.money += amount;
		desire = BankCustomerDesire.leaveBank;
		state = CustomerState.ready;
		stateChanged();
	}

	public void msgHereIsNewAccount (int accountNum) {
		print("Received new bank account");
		person.accountNum = accountNum;
		if (person.money <= person.moneyMinThreshold)
			desire = BankCustomerDesire.withdraw;
		else if (person.money >= person.moneyMaxThreshold)
			desire = BankCustomerDesire.deposit;
		else	
			desire = BankCustomerDesire.leaveBank;
		state = CustomerState.ready;
		stateChanged();
	}

	public void msgBankrupt() {
		state = CustomerState.ready;
		desire = BankCustomerDesire.leaveBank;
		stateChanged();
	}

	public void msgInsufficientFunds(){
		print("Not enough money in account: need loan");
		state = CustomerState.ready;
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

		if (state == CustomerState.atBank) {
			messageGuard();
			return false;
		}

		if (desire == BankCustomerDesire.openAccount && state == CustomerState.ready){
			openAccount();
			return false;
		}

		if (desire == BankCustomerDesire.withdraw && state == CustomerState.ready){
			withdrawCash();
			return false;
		}

		if (desire == BankCustomerDesire.deposit && state == CustomerState.ready){
			depositCash();
			return false;
		}

		if (desire == BankCustomerDesire.wantLoan && state == CustomerState.ready){
			requestLoan();
			return false;
		}

		if (desire == BankCustomerDesire.closeLoan && state == CustomerState.ready){
			payOffLoan();
			return false;
		}

		if (desire == BankCustomerDesire.leaveBank && state == CustomerState.ready){
			leaveBank();
			return false;
		}

		if (desire == BankCustomerDesire.robBank && state == CustomerState.ready){
			robBank();
			return false;
		}

		return false;
	}

	//Actions

	void messageGuard () {
		print("Arrived at bank");
		Phonebook.getPhonebook().getBank().getBankGuard(test).msgArrivedAtBank(this);
		state = CustomerState.waiting;
	}

	void withdrawCash() {
		myTeller.msgINeedMoney(person.desiredCash,person.accountNum);
		state = CustomerState.waiting;
	}

	void depositCash () {
		myTeller.msgHereIsMyDeposit(person.depositAmount, person.accountNum);
		state = CustomerState.waiting;
	}

	void requestLoan () {
		desiredLoanAmount = 10*person.desiredCash;
		myTeller.msgINeedALoan(desiredLoanAmount, person.accountNum);
		state = CustomerState.waiting;
	}

	void payOffLoan() {
		person.money -= person.loan;
		myTeller.msgPayingOffLoan(person.loan, person.accountNum);
		state = CustomerState.waiting;
	}

	void openAccount () {
		myTeller.msgWantNewAccount(this);
		state = CustomerState.waiting;
	}

	void leaveBank () {	
		//GUI operation
		desire = BankCustomerDesire.none;
		state = CustomerState.waiting;	
		myTeller.msgLeavingBank(person.accountNum);
		Phonebook.getPhonebook().getBank().getBankGuard(test).msgCustomerLeavingBank(myTeller);
		myTeller = null;
		this.setRoleInactive();
	}

	void robBank() {
		//Animation();
		Phonebook.getPhonebook().getBank().getBankGuard(test).msgRobbingBank(this);
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
