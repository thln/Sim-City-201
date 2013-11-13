package person;

import java.util.*;

import person.Role;
import person.Role.roleState;
import agent.Agent;

public class Person extends Agent {

	//Data
	public List<Role> roles = Collections.synchronizedList(new ArrayList<Role>()); 	//contains all the customer roles
	public double money;
	public HashMap <String, Integer> Inventory = new HashMap<String, Integer>(); 		//market list
	public boolean hasCar;
	public int accountNum;
	public double accountBalance;
	public boolean hasFoodInFridge;
	public int sleepTime = 22;

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
		return false;
		//		}
	}

	//Actions
	public void setRoleActive(Role role) {
		role.state = roleState.active;
		stateChanged();
	}
}
