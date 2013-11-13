package Images;

import java.util.List;

import Images.BankTellerAgent.Account;
import agent.Agent;

public class LoanOfficerAgent {


	//Data

	double vault = 10000;
	double vaultMinimum = 1000;
	enum LoanState {requesting, open, closed}
	class Loan {
		Account account1;
		LoanState state = LoanState.requesting;
		BankTellerAgent teller1;
		
		Loan(Account acc1, BankTellerAgent t1) {
			account1 = acc1;
			teller1 = t1;
		}
	}
	List<Loan> loans;




	//Messages

	void msgIsLoanApproved(Account account1, BankTellerAgent t1) {
		loans.add( new Loan(account1, t1));
	}


	// Scheduler

	protected boolean pickAndExecuteAnAction () {
		
		for (Loan loan1: loans) {
			if (loan1.state == LoanState.requesting)
				ProcessLoan(loan1);
		}
		
		return false;
	}


	//Actions

	void ProcessLoan (Loan loan1) {

		double loanAmount = loan1.account1.processingMoney;
		if (vault <=  vaultMinimum + loanAmount) {
			loan1.teller1.msgThisLoanDenied(loan1.account1, 0);
		}

		if (loanAmount > (loan1.account1.creditScore * 10))		//You are allowed a loan 10 times your credit score
				loan1.teller1.msgThisLoanApproved(loan1.account1);

		else
			loan1.teller1.msgThisLoanDenied(loan1.account1, loan1.account1.creditScore*10); 

	}


}
