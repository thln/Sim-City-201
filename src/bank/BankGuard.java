package bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import person.Person;
import person.Role;

public class BankGuard extends Role {

	//DATA

	String name;
	List <BankCustomer> customers;
	List <BankCustomer> robbers;
	List <MyTeller> tellers; 

	enum TellerState {available, busy};

	class MyTeller {
		TellerState state;
		BankTeller tell1;
		
		MyTeller (BankTeller t1) {
			tell1 = t1;
		}
	}
	
	public BankGuard (String name, Person p1) {
		super(p1);
		this.name = name;
		customers = Collections.synchronizedList(new ArrayList<BankCustomer>());
		robbers = Collections.synchronizedList(new ArrayList<BankCustomer>());
		tellers = Collections.synchronizedList(new ArrayList<MyTeller>());
	}

	//MESSAGES

	public void msgTellerCameToWork (BankTeller t1) {
		tellers.add(new MyTeller(t1));
	}
	
	public void msgRobbingBank(BankCustomer cust1) {
		robbers.add(cust1);
		stateChanged();
	}

	public void msgArrivedAtBank(BankCustomer c1) {
		System.out.println("New customer arrived");
		customers.add(c1);
		stateChanged();
	}

	public void msgLeavingBank (BankTeller t1) {
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


		for (BankCustomer cust1: customers) {
			assignToTeller(cust1); 
			return true;
		}
		
		return false;
	}

	//ACTIONS

	private MyTeller findTeller (BankTeller t1) {
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
			if (teller1.state == TellerState.available) {
				cust1.msgGoToTeller(teller1.tell1);
				teller1.state = TellerState.busy;
			}
		}
	}

}
