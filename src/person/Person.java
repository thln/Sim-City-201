package person;

import java.util.*;

import person.Role;
import person.Role.roleState;
import agent.Agent;

public abstract class Person extends Agent {

	//Data
	public List<Role> roles = Collections.synchronizedList(new ArrayList<Role>()); 	//contains all the customer roles
	public double money;
	public HashMap <String, Integer> Inventory = new HashMap<String, Integer>(); 		//market list
	public boolean hasCar;
	public int accountNum;
	public double accountBalance;
	public boolean hasFoodInFridge;
	public int sleepTime = 22;
	String name;

	//Messages
	void msgNewTime(int time) {
	}

	//Scheduler
	protected boolean pickAndExecuteAnAction() {

		synchronized (roles) {
			if (!roles.isEmpty()) {
				for (Role r : roles) {
					if (r.state == roleState.active) {
						r.pickAndExecuteAnAction();
						return true;
					}

					if (r.state == roleState.waitingToExecute) {
						setRoleActive(r);
						return true;
					}
				}
				//goHome();
				return false;
			}
			//goHome();
			return false;
		}
	}

	//Actions
	public void setRoleActive(Role role) {
		role.state = roleState.active;
		stateChanged();
	}
	
	public void setRoleInactive(Role role) {
		role.state = roleState.inActive;
		stateChanged();
	}
}
