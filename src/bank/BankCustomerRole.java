package bank;

import java.util.concurrent.Semaphore;

import bank.interfaces.BankCustomer;
import bank.interfaces.BankTeller;
import application.Phonebook;
import application.gui.animation.agentGui.BankCustomerGui;
import application.gui.animation.agentGui.RestaurantCustomerGui;
import person.Person;
import person.Role;
import person.Worker;

public class BankCustomerRole extends Role implements BankCustomer{

	//DATA

	public enum BankCustomerDesire {none, withdraw, deposit, wantLoan, closeLoan, openAccount, closeAccount, robBank, leaveBank}
	public enum CustomerState {atBank, none, waiting, ready};
	
	public BankTeller myTeller;
	//private BankCustomerGui custGui = (BankCustomerGui) gui;
	private BankCustomerGui custGui = null;
	public double desiredLoanAmount;
	public BankCustomerDesire desire;
	public CustomerState state;
	protected String RoleName = "Bank Customer";
	private int waitPlace;
	private Semaphore atDestination = new Semaphore(0, true);
	
	public BankCustomerRole (Person p1, String pName, String rName) {
		super(p1, pName, rName);
		desire = BankCustomerDesire.openAccount;
		state = CustomerState.atBank;
		gui = new BankCustomerGui(this);
	}

	//Messages
	public void msgComeIn() {
		stateChanged();
	}

	public void msgGoToTeller(BankTeller tell1) {
		print ("Assigned to teller " + tell1.getName());
		myTeller = tell1;
		state = CustomerState.ready;
		stateChanged();
	}

	public void msgNoTellerAvailable(){
		print("No teller available, must wait");
		state = CustomerState.waiting;
	}

	public void msgHereIsYourMoney(double amount) {
		print("Got my $" + amount);
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
	
	public void msgAtDestination() {
		this.atDestination.release();
	}

	//Scheduler

	public boolean pickAndExecuteAnAction () {

		if (state == CustomerState.waiting) {
			if(myTeller == null)
				waitInLine();
			return false;
		}

		if (state == CustomerState.atBank) {
			messageGuard();
			return false;
		}

		if (state == CustomerState.ready) {
			if (desire == BankCustomerDesire.openAccount){
				openAccount();
				return false;
			}

			if (desire == BankCustomerDesire.withdraw){
				withdrawCash();
				return false;
			}

			if (desire == BankCustomerDesire.deposit){
				depositCash();
				return false;
			}

			if (desire == BankCustomerDesire.wantLoan){
				requestLoan();
				return false;
			}

			if (desire == BankCustomerDesire.closeLoan){
				payOffLoan();
				return false;
			}

			if (desire == BankCustomerDesire.leaveBank){
				leaveBank();
				return false;
			}

			if (desire == BankCustomerDesire.robBank){
				robBank();
				return false;
			}
		}
		return false;
	}

	//Actions

	void messageGuard () {
		if (!test){
			if (!Phonebook.getPhonebook().getEastBank().isOpen()) {
				print("Bank closed, waiting for bank to open.");
				return;
			}
			else
				print("Arrived at bank");
		}

		Phonebook.getPhonebook().getEastBank().getBankGuard(test).msgArrivedAtBank(this);
		state = CustomerState.waiting;
	}
	
	void waitInLine() {
		custGui.WaitTellerLine(waitPlace); //positions to be changed later by guard
		try {
			this.atDestination.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void withdrawCash() {
		//GUI operation
		DoGoToTeller();
		myTeller.msgINeedMoney(person.desiredCash,person.accountNum);
		state = CustomerState.waiting;
	}

	void depositCash () {
		//GUI operation
		DoGoToTeller();
		person.money -= person.depositAmount;
		myTeller.msgHereIsMyDeposit(person.depositAmount, person.accountNum);
		state = CustomerState.waiting;
	}

	void requestLoan () {
		//GUI operation
		DoGoToTeller();
		desiredLoanAmount = 10*person.desiredCash;
		myTeller.msgINeedALoan(desiredLoanAmount, person.accountNum);
		state = CustomerState.waiting;
	}

	void payOffLoan() {
		//GUI operation
		DoGoToTeller();
		person.money -= person.loan;
		myTeller.msgPayingOffLoan(person.loan, person.accountNum);
		state = CustomerState.waiting;
	}

	void openAccount () {
		//GUI operation
		DoGoToTeller();
		myTeller.msgWantNewAccount(this);
		state = CustomerState.waiting;
	}

	void leaveBank () {	
		print("Leaving bank");
		//GUI operation
		custGui.DoExit();
		desire = BankCustomerDesire.none;
		state = CustomerState.waiting;	
		myTeller.msgLeavingBank(person.accountNum);
		Phonebook.getPhonebook().getEastBank().getBankGuard(test).msgCustomerLeavingBank(myTeller);
		myTeller = null;
		this.setRoleInactive();
		stateChanged();
	}

	void robBank() {
		//GUI operation
		custGui.DoRobBank();
		try {
			this.atDestination.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Phonebook.getPhonebook().getEastBank().getBankGuard(test).msgRobbingBank(this);
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
	
	public void DoGoToTeller() {
		int window = myTeller.getTellerPosition();
		if(custGui.getXPos() != 450 || custGui.getYPos() != 20*window+30*(window-1)) {
			custGui.DoGoToTeller(myTeller.getTellerPosition());
			try {
				this.atDestination.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void setGui(BankCustomerGui gui) {
		this.custGui = gui;
	}
	
	public void setWaitPlace(int place) {
		waitPlace = place;
	}
}
