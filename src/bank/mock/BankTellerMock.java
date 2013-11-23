package bank.mock;

import bank.interfaces.BankCustomer;
import bank.interfaces.BankTeller;
import testing.Mock;


public class BankTellerMock extends Mock implements BankTeller {

	public BankTellerMock(String name) {
		super(name);
	}

	@Override
	public void msgINeedMoney(double desiredCash, int accountNum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgHereIsMyDeposit(double depositAmount, int accountNum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgINeedALoan(double desiredLoanAmount, int accountNum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgPayingOffLoan(double loan, int accountNum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgWantNewAccount(BankCustomer bankCustomer) {
		// TODO Auto-generated method stub
		
	}

}
