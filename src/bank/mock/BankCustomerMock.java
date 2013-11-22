package bank.mock;

import bank.interfaces.BankCustomer;
import bank.interfaces.BankTeller;
import testing.LoggedEvent;
import testing.Mock;


public class BankCustomerMock extends Mock implements BankCustomer {

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgYourLoanWasDenied(double processingMoney) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgDepositReceived() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgInsufficentFunds() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgBankrupt() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgHereIsYourMoney(double processingMoney) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgHereIsNewAccount(int accountNum) {
		// TODO Auto-generated method stub
		
	}

}
