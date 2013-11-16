package bank;

public class Bank {

	BankGuardRole bankGuardRole;
	
	public void msgArrivedAtBank(BankCustomer c1) {
		bankGuardRole.msgArrivedAtBank(c1);
	}
	
}
