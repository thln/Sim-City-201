package bank.interfaces;

public interface BankTeller {

	String getName();

	void msgINeedMoney(double desiredCash, int accountNum);

	void msgHereIsMyDeposit(double depositAmount, int accountNum);

	void msgINeedALoan(double desiredLoanAmount, int accountNum);

	void msgPayingOffLoan(double loan, int accountNum);

	void msgWantNewAccount(BankCustomer bankCustomer);

}
