package bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import person.Person;
import person.Role;

public class BankGuardRole extends Role {

	//DATA

	String name;
	List <BankCustomerRole> customers;
	List <BankCustomerRole> robbers;
	List <MyTeller> tellers; 
	protected String RoleName = "Bank Guard";

	enum TellerState {available, busy};

	class MyTeller {
		TellerState state;
		BankTellerRole tell1;
		
		MyTeller (BankTellerRole t1) {
			tell1 = t1;
			state = TellerState.available;
		}
	}
	
	public BankGuardRole (String name, Person p1, String roleName) {
		super(p1, name, roleName);
		this.name = name;
		this.RoleName = roleName;
		customers = Collections.synchronizedList(new ArrayList<BankCustomerRole>());
		robbers = Collections.synchronizedList(new ArrayList<BankCustomerRole>());
		tellers = Collections.synchronizedList(new ArrayList<MyTeller>());
	}

	//MESSAGES

	public void msgTellerCameToWork (BankTellerRole t1) {
		print("Adding teller to list");
		tellers.add(new MyTeller(t1));
	}
	
	public void msgTellerBecameAvailable (BankTellerRole t1){
		print("Teller became available");
		for (MyTeller teller: tellers) {
			if (teller.tell1.equals(t1)) {
				teller.state = TellerState.available;
				stateChanged();
				return;
			}
		}
			
	}
	
	public void msgRobbingBank(BankCustomerRole cust1) {
		robbers.add(cust1);
		stateChanged();
	}

	public void msgArrivedAtBank(BankCustomerRole c1) {
		print("New customer arrived");
		customers.add(c1);
		stateChanged();
	}

	public void msgLeavingBank (BankTellerRole t1) {
		MyTeller correct = findTeller(t1);
		correct.state = TellerState.available;
		stateChanged();
	}


	//SCHEDULER
	 public boolean pickAndExecuteAnAction() {
		for (BankCustomerRole cust1: robbers) {
			catchRobber(cust1);
			return true;
		}


		for (BankCustomerRole cust1: customers) {
			assignToTeller(cust1); 
			return true;
		}
		
		return false;
	}

	//ACTIONS

	private MyTeller findTeller (BankTellerRole t1) {
			return null;
	}

	private void catchRobber(BankCustomerRole robber1) {
		boolean caught = true;
		//95% chance Robber is caught, 5% he gets away;
		if (caught)
			robber1.msgCaughtYou();
		if (!caught)
			robber1.msgGotAway();  
	}

	private void assignToTeller(BankCustomerRole cust1) {
		for (MyTeller teller1: tellers) {
			if (teller1.state == TellerState.available) {
				cust1.msgGoToTeller(teller1.tell1);
				teller1.state = TellerState.busy;
			}
		}
	}

}
