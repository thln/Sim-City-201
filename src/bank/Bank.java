package bank;

import application.WatchTime;
import person.Person;
import person.Role;
import person.Worker;

public class Bank {

	//Open and closing times
	public WatchTime openTime = new WatchTime(8);
	public WatchTime closeTime = new WatchTime(17);

	//Roles
	public BankGuardRole bankGuardRole = new BankGuardRole("Bank Guard");
	public LoanOfficerRole loanOfficerRole = new LoanOfficerRole("Loan Officer");

	public Role arrivedAtWork(Person person, String title) {
		if (title == "bankGuard") {
			//Setting previous bank guard role to inactive
			if (bankGuardRole.getPerson() != null) {
				Worker worker = (Worker) bankGuardRole.getPerson();
				worker.goOffWork();
			}
			//Setting bank guard role to new role
			bankGuardRole.setPerson(person);
			return bankGuardRole;
		}
		else if (title == "loanOfficer") {
			//Setting previous bank guard role to inactive
			if (loanOfficerRole.getPerson() != null) {
				Worker worker = (Worker) loanOfficerRole.getPerson();
				worker.goOffWork();
			}
			//Setting bank guard role to new role
			loanOfficerRole.setPerson(person);
			return loanOfficerRole;
		}
		else
			return null;
	}

	public void setUPSmanRole(Person person) {
		Worker worker = (Worker) person;
		
		worker.setWorkerRole(loanOfficerRole);
	}

	public void goingOffWork(Person person) {
		Worker worker = (Worker) person;
		
		if (worker.getWorkerRole().equals(bankGuardRole)) {
			bankGuardRole = null;
		}
		else if (worker.getWorkerRole().equals(loanOfficerRole)) {
			loanOfficerRole = null;
		}
	}

}
