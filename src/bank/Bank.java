package bank;

public class Bank {

	BankGuard bankGuard;
	
	public void msgArrivedAtBank(BankCustomer c1) {
		bankGuard.msgArrivedAtBank(c1);
	}
	
}
