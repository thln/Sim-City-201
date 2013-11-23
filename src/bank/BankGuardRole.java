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
		private TellerState state;
		BankTeller tell1;
		
		MyTeller (BankTeller t1) {
			tell1 = t1;
			setState(TellerState.available);
		}

		public TellerState getState() {
			return state;
		}

		public TellerState setState(TellerState state) {
			this.state = state;
			return state;
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
		stateChanged();
	}
	
	public void msgRobbingBank(BankCustomer cust1) {
		robbers.add(cust1);
		stateChanged();
	}

	public void msgArrivedAtBank(BankCustomer c1) {
		print("New customer arrived");
		customers.add(c1);
		stateChanged();
	}

	public void msgCustomerLeavingBank (BankTeller t1) {
		//print("Teller became available");
	
		MyTeller correct = findTeller(t1);
		correct.setState(TellerState.available);
		stateChanged();
	}


	//SCHEDULER
	 public boolean pickAndExecuteAnAction() {
		 
		for (BankCustomer cust1: robbers) {
			catchRobber(cust1);
			return true;
		}


		for (BankCustomer cust1: customers) {
			 assignToTeller(cust1); 
			 return false;
		}
		
		if (leaveRole){
			((Worker) person).roleFinishedWork();
			leaveRole = false;
			return true;
		}
		
		return false;
	}

	//ACTIONS

	private MyTeller findTeller (BankTeller t1) {
		for (MyTeller teller: tellers) {
			if (teller.tell1.equals(t1)) {
				teller.setState(TellerState.available);
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

	private void assignToTeller(BankCustomer cust1) {
		for (MyTeller teller1: tellers) {
			if (teller1.getState() == TellerState.available) {
				cust1.msgGoToTeller(teller1.tell1);
				teller1.setState(TellerState.busy);
				customers.remove(cust1);
			}
		}	
		cust1.msgNoTellerAvailable();
	}

	public List <BankCustomer> getCustomers() {
		return customers;
	}

	public List<MyTeller> getTellers() {
		return tellers;
	}

}
