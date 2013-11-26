package bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bank.interfaces.BankCustomer;
import bank.interfaces.BankGuard;
import bank.interfaces.BankTeller;
import application.Phonebook;
import person.Person;
import person.Role;
import person.Worker;

public class BankGuardRole extends Role implements BankGuard {

	//DATA

	String name;
	List <BankCustomer> customers;
	List <BankCustomer> robbers;
	List <MyTeller> tellers; 

	protected String roleName = "Bank Guard";

	public enum TellerState {available, busy};

	public class MyTeller {
		public TellerState state;
		BankTeller tell1;

		MyTeller (BankTeller t1) {
			tell1 = t1;
			state = TellerState.available;
		}
	}

	public BankGuardRole (String name, Person p1, String roleName) {
		super(p1, name, roleName);
		customers = Collections.synchronizedList(new ArrayList<BankCustomer>());
		robbers = Collections.synchronizedList(new ArrayList<BankCustomer>());
		tellers = Collections.synchronizedList(new ArrayList<MyTeller>());
	}

	public BankGuardRole (String name) {
		super(name);
		customers = Collections.synchronizedList(new ArrayList<BankCustomer>());
		robbers = Collections.synchronizedList(new ArrayList<BankCustomer>());
		tellers = Collections.synchronizedList(new ArrayList<MyTeller>());
	}

	//MESSAGES

	public void msgTellerCameToWork (BankTeller t1) {
		tellers.add(new MyTeller(t1));
	}
	
	public void msgTellerLeavingWork(BankTeller t1) {
		tellers.remove(t1);
	}


	public void msgRobbingBank(BankCustomer cust1) {
		robbers.add(cust1);
		stateChanged();
	}

	public void msgArrivedAtBank(BankCustomer c1) {
		try {
			print("New customer " + ((BankCustomerRole) c1).getName() + " arrived");
		}
		catch (Exception e) {
		}
		customers.add(c1);
		stateChanged();
	}

	public void msgCustomerLeavingBank (BankTeller t1) {
		print("Customer leaving, teller became available");
		MyTeller correct = findTeller(t1);
		correct.state = TellerState.available;
		stateChanged();
	}


	//SCHEDULER
	public boolean pickAndExecuteAnAction() {

		for (BankCustomer cust1: robbers) {
			catchRobber(cust1);
			return true;
		}

		if (customers.size() != 0){
			for (BankCustomer cust1: customers) {
				return assignToTeller(cust1); 
			}
		}

		if (leaveRole){
			leaveRole = false;
			try {
			((Worker) person).roleFinishedWork();	
			}
			catch (Exception e){
				
			};
			return true;
		}

		return false;
	}

	//ACTIONS

	private MyTeller findTeller (BankTeller t1) {
		for (MyTeller teller: tellers) {
			if (teller.tell1.equals(t1)) {
				stateChanged();
				return teller;
			}
		}
		return null;
	}

	private void catchRobber(BankCustomer robber1) {
		boolean caught = true;
		//95% chance Robber is caught, 5% he gets away;
		if (caught)
			robber1.msgCaughtYou();
		if (!caught)
			robber1.msgGotAway();  
	}

	private boolean assignToTeller(BankCustomer cust1) {	
		for (MyTeller teller1: tellers) {
			if (teller1.state == TellerState.available && (Phonebook.getPhonebook().getBank().isOpen() || test)) {
				if (!test)
					print("Assigning " + ((Role) cust1).getPerson().getName() + " to teller " + teller1.tell1.getName());
				cust1.msgGoToTeller(teller1.tell1);
				teller1.state = TellerState.busy;
				customers.remove(cust1);
				return false;
			}
		}	
		cust1.msgNoTellerAvailable();
		return false;
	}

	public List <BankCustomer> getCustomers() {
		return customers;
	}

	public List<MyTeller> getTellers() {
		return tellers;
	}

	public void msgBankOpen() {
		if (customers.size() != 0){
			for (BankCustomer c1: customers){
				c1.msgComeIn();
			}
		}
	}
}
