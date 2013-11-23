package bank.mock;

import bank.interfaces.LoanOfficer;
import testing.Mock;


public class LoanOfficerMock extends Mock implements LoanOfficer {

	public LoanOfficerMock(String name) {
		super(name);
	}

}