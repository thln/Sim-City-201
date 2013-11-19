package bank;

import person.Person;

public class Bank {
	
	//Roles
	public BankGuardRole bankGuardRole;
	public LoanOfficerRole loanOfficerRole;
	
	public void setBankGuardRole(Person person) {
		person.setWorkerRole(bankGuardRole);
	}
	
	public void setUPSmanRole(Person person) {
		person.setWorkerRole(loanOfficerRole);
	}
	
	public void goingOffWork(Person person) {
		if (person.getWorkerRole().equals(bankGuardRole)) {
			bankGuardRole = null;
		}
		else if (person.getWorkerRole().equals(loanOfficerRole)) {
			loanOfficerRole = null;
		}
	}
	
}
