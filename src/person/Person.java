package person;

import java.util.*;

import agent.Agent;

public class Person extends Agent {
	
	//Data
	List<Role> roles; 	//contains all the customer roles
	double money;
	HashMap <String, Integer> Inventory; 		//market list
	boolean hasCar;
	int accountNum;
	double accountBalance;
	boolean hasFoodInFridge;
	int sleepTime = 22;

	//Messages
	void msgNewTime(int time) {
	}

	//Scheduler
	protected boolean pickAndExecuteAnAction() {
//		if (exists a role in role such that r. is active)
//			then r.pickAndExecuteAnAction();
//		if (exists a role such that r. is watingToExecute) {
//			then SetRoleActive(role);	
//			return true;
//		}
//		if (no role is active) {
//			GoHome();
//			return false;
//		}
	}

	//Actions
	SetRoleActive(role) {
		role.state = active;
		stateChanged();	
	}
}
