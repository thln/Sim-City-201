package bank.mock;

import bank.interfaces.BankCustomer;
import bank.interfaces.BankTeller;
import testing.LoggedEvent;
import testing.Mock;


public class BankCustomerMock extends Mock implements BankCustomer {

	double deposit = 200;
	
	public BankCustomerMock(String name) {
		super(name);
	}

	@Override
	public void msgGoToTeller(BankTeller tell1) {
		log.add(new LoggedEvent("Assigned to bank teller " + tell1.getName()));
	}

	@Override
	public void msgCaughtYou() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgGotAway() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgLoanClosed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgYourLoanWasApproved() {
		log.add(new LoggedEvent("Loan approved. Try withdrawal again."));
	}

	@Override
	public void msgYourLoanWasDenied(double processingMoney) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgDepositReceived() {
		log.add(new LoggedEvent("Your deposit of $" + deposit + " was received."));
	}

	@Override
	public void msgInsufficentFunds() {
		log.add(new LoggedEvent("Account balance is too low for a withdrawal. Must open loan."));
	}

	@Override
	public void msgBankrupt() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgHereIsYourMoney(double processingMoney) {
		log.add(new LoggedEvent("Withdrawal succeeded"));
	}

	@Override
	public void msgHereIsNewAccount(int accountNum) {
		log.add(new LoggedEvent("New Account created"));
	}

	@Override
	public void msgNoTellerAvailable() {
		log.add(new LoggedEvent("No teller available, must wait"));
	}

}
