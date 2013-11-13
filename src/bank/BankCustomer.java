package bank;

import person.Role;

public class BankCustomer extends Role {
	
	public BankCustomer() {
		super();
	}
	
	protected boolean pickAndExecuteAnAction() {
		return false;
	}
}
