package person;

import java.util.*;
import market.MarketCustomer;
import person.Role;
import person.Role.roleState;
import restaurant.RestaurantCustomer;
import agent.Agent;

public abstract class Person extends Agent {

	//Data
	public List<Role> roles = Collections.synchronizedList(new ArrayList<Role>()); 	//contains all the customer roles
	public double money;
	public HashMap <String, Integer> Inventory = new HashMap<String, Integer>(); 		//market list
	public boolean hasCar;
	public boolean hasFoodInFridge;
	public int accountNum;
	public double accountBalance;
	public int sleepTime = 22;
	String name;
	private int newTime;

	Person() {
		//roles.add(new RestaurantCustomer(this));
		//roles.add(new MarketCustomer(this));
		//roles.add(new BankCustomer(this));
	}
	
	//Messages
	void msgNewTime(int time) {
		newTime = time;
		stateChanged();
	}

	//Scheduler
	protected boolean pickAndExecuteAnAction() {

		synchronized (roles) {
			if (!roles.isEmpty()) {
				for (Role r : roles) {
					
					if (newTime >= 0) {
						updateTime(newTime);	
					}
					
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
	
	public abstract void updateTime(int newTime);
	
	public void setRoleActive(Role role) {
		role.state = roleState.active;
		stateChanged();
	}
	
	public void setRoleInactive(Role role) {
		role.state = roleState.inActive;
		stateChanged();
	}
	
	public void goToSleep() {
		//puts agent to sleep
	}
}
