package bank.mock;


import bank.interfaces.BankGuard;
import testing.Mock;


public class BankGuardMock extends Mock implements BankGuard {

	public BankGuardMock(String name) {
		super(name);
	}

}