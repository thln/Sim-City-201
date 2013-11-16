package person;

import java.util.*;

import bank.BankCustomer;
import bank.BankGuard;
import market.MarketCustomer;
import person.Role;
import person.Role.roleState;
import restaurant.Host;
import restaurant.RestaurantCustomer;
import agent.Agent;

public abstract class Person extends Agent {

	//Data
	public List<Role> roles = Collections.synchronizedList(new ArrayList<Role>()); 	//contains all the customer roles
	public double money;
	Role workerRole;
	public HashMap <String, Integer> Inventory = new HashMap<String, Integer>(); 		//market list
	public boolean hasCar;
	public boolean hasFoodInFridge;
	public int accountNum;
	public double accountBalance;
	int moneyMinThreshold = 20;
	int moneyMaxThreshold = 200;
	public int sleepTime = 22;
	private int newTime;
	final int carCost = 1000;
	PhoneBook myPhoneBook;

	class PhoneBook {
		Host host1;
		public BankGuard bankGuard;
	}


	Person() 
	{
		roles.add(new RestaurantCustomer(getName(), this));
		roles.add(new MarketCustomer(this));
		roles.add(new BankCustomer(getName(), this, myPhoneBook.bankGuard, 0, 0, 0, 0));
	}

	//Messages
	public void msgNewTime(int time) {
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

					if (r.getState() == roleState.active) {
						r.pickAndExecuteAnAction();
						return true;
					}

					if (r.getState() == roleState.waitingToExecute) {
						if (r instanceof BankCustomer) {
							if (this instanceof Crook)
								robBank(r);
							else
								prepareForBank(r);
						}
						if (r instanceof MarketCustomer) {
							prepareForMarket(r);
						}
						if (r instanceof RestaurantCustomer) {
							prepareForRestaurant(r);
						}
						if (r.equals(this.workerRole)) {
							prepareForWork(r);
						}
						return true;
					}
				}
				//goHome();
				return false;
			}
			//goHome();
			return false;
		}
		
		//at some point we check to see if people have enough money to buy a car
	}

	//Actions

	public abstract void updateTime(int newTime);

	private void prepareForBank (Role r){
		//Do Gui method
		setRoleActive(r);
		BankCustomer cust1 = (BankCustomer) r;
		if (money <= moneyMinThreshold){
			cust1.setDesiredCash(100);
			cust1.setDesire("withdraw");
		}
		if (money >= moneyMaxThreshold){
			cust1.setDesire("deposit");
		}
		//(String name, Person p1, BankGuard guard1, int desiredCash, int deposit, int accNum, int cash)
		//if bank customer role hasn't already been instantiated, instatiate it
		myPhoneBook.bankGuard.msgArrivedAtBank(cust1);
		stateChanged();
	}

	private void robBank (Role r) {
		//Do Gui method
		setRoleActive(r);
		BankCustomer cust1 = (BankCustomer) r;
		cust1.setDesire("robBank");
		stateChanged();
	}

	private void prepareForMarket (Role r) {
		//Do GUI method

		//make money decisions

		setRoleActive(r);
		stateChanged();
	}

	private void prepareForRestaurant (Role r) {
		//Do GUI method


		setRoleActive(r);
		stateChanged();
	}

	private void prepareForWork(Role r) {
		//Do GUI method

		setRoleActive(r);
		stateChanged();
	}

	public void setRoleActive(Role role) {
		role.setState(roleState.active);

	}

	public void setRoleInactive(Role role) {
		role.setState(roleState.inActive);
	}

	public void goToSleep() {
		//puts agent to sleep
	}
	
	/*
	public void print(String s)
	{
		String roleName = "";
		
		synchronized (roles) 
		{
			if (!roles.isEmpty()) 
			{
				for (Role r : roles) 
				{
					if (r.getState() == roleState.active) 
					{
						roleName = r.
					}
				}
			}
		}
				
		System.out.println(getName() + ": " + s);
	}
	*/
}
