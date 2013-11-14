package bank;

import java.util.List;

import person.Role;

public class BankGuard extends Role {

	//DATA

	List <BankCustomer> customers;
	List <BankCustomer> robbers;
	List <MyTeller> tellers; 

	enum TellerState {available, busy};

	class MyTeller {
		TellerState state;
		BankTeller tell1;
	}

	//MESSAGES

	void msgRobbingBank(BankCustomer cust1) {
		robbers.add(cust1);
	}

	void msgArrivedAtBank(BankCustomer c1) {
		customers.add(c1);
	}

	void msgLeavingBank (BankTeller t1) {
		MyTeller correct = findTeller(t1);
		correct.state = TellerState.available;
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

	MyTeller findTeller (BankTeller t1) {
			return null;
	}

	void catchRobber(BankCustomer robber1) {
		boolean caught = true;
		//95% chance Robber is caught, 5% he gets away;
		if (caught)
			robber1.msgCaughtYou();
		if (!caught)
			robber1.msgGotAway();  
	}

	void assignToTeller(BankCustomer cust1) {
		for (MyTeller teller1: tellers) {
			if (teller1.state == TellerState.available) {
				cust1.msgGoToTeller(teller1.tell1);
				teller1.state = TellerState.busy;
			}
		}
	}

}
