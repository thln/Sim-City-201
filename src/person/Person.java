package person;

import housing.Housing;

import java.util.*;
import java.util.concurrent.Semaphore;

import bank.Bank;
import bank.BankCustomerRole;
import bank.BankGuardRole;
import bank.BankTellerRole;
import bank.interfaces.BankCustomer;
import market.MarketCustomerRole;
import person.Role;
import person.Role.RoleState;
import restaurant.HostRole;
import restaurant.RestaurantCustomerRole;
import agent.Agent;
import application.Phonebook;
import application.TimeManager;
import application.TimeManager.Time;

public abstract class Person extends Agent {

	//Data
	String name;
	private Semaphore atDestination = new Semaphore(0,true);
	private Housing home;
	private Timer alarmClock = new Timer();

	//Role Related
	public List<Role> roles = Collections.synchronizedList(new ArrayList<Role>()); 	//contains all the customer role

	//Car Related
	public enum CarState {noCar, wantsCar, hasCar};
	public CarState carStatus = CarState.noCar;
	final int carCost = 1000;

	//Hunger Related
	public HashMap <String, Integer> Inventory = new HashMap<String, Integer>(); 		//Food list
	public boolean hasFoodInFridge;
	public boolean hungry;

	//Bank Related
	public double money;
	public int accountNum;
	public double accountBalance;
	public double desiredCash;
	public double depositAmount;
	public double withdrawAmount;
	int moneyMinThreshold = 20;
	int moneyMaxThreshold = 200;

	//Time Related
	public int sleepTime = 22;

	Person(String name) {
		this.name = name;
		roles.add(new BankCustomerRole(this, getName(), "Bank Customer"));
		roles.add(new MarketCustomerRole(this, getName(), "Market Customer"));
		roles.add(new RestaurantCustomerRole(this, getName(), "Restaurant Customer"));
	}

	//Scheduler
	protected abstract boolean pickAndExecuteAnAction();

	//Actions
	protected void eatAtHome() {

	}

	protected void prepareForBank () {
		Do("Becoming Bank Customer");
		//Do Gui method

		/*GUI call to go to business
		try {
			atDestination.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		 */
		//Once semaphore is released from GUI

		//Checking if customer has enough money for a car


		for (Role cust1 : roles) {
			if (cust1 instanceof BankCustomerRole) {
				BankCustomerRole BCR = (BankCustomerRole) cust1;

				if (accountNum != 0) {
					if (money <= moneyMinThreshold){
						withdrawAmount = 100;
						BCR.setDesire("withdraw");
					}
					if (money >= moneyMaxThreshold){
						depositAmount = 100;
						BCR.setDesire("deposit");
					}
				}

				Phonebook.getPhonebook().getBank().bankGuardRole.msgArrivedAtBank(BCR);
				cust1.setRoleActive();
				stateChanged();
				return;
			}
		}
		//(String name, Person p1, BankGuard guard1, int desiredCash, int deposit, int accNum, int cash)
		//if bank customer role hasn't already been instantiated, instatiate it
	}

	protected void prepareForMarket() {
		//		//GUI call to go to business
		//		try {
		//			atDestination.acquire();
		//		} catch (InterruptedException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//
		//		}
		//		//Once semaphore is released from GUI

		//Checking if have enough money for car
		if (accountBalance >= (carCost + 100)) {
			if (carStatus == CarState.noCar) {
				carStatus = CarState.wantsCar;
			}
		}

		if (hasFoodInFridge == false) {
			//choosing random item to buy from market
			String item;
			item = chooseMarketItem();
			for (Role cust1 : roles) {
				if (cust1 instanceof MarketCustomerRole) {
					Phonebook.getPhonebook().getMarket().salesPersonRole.msgIWantProducts((MarketCustomerRole) cust1, item, 3);
					cust1.setRoleActive();
					stateChanged();
					return;
				}
			}
		}
		else if (carStatus == CarState.wantsCar) {
			for (Role cust1 : roles) {
				if (cust1 instanceof MarketCustomerRole) {
					Phonebook.getPhonebook().getMarket().salesPersonRole.msgIWantProducts((MarketCustomerRole) cust1, "Car", 3);
					cust1.setRoleActive();
					stateChanged();
					return;
				}
			}
		}
	}

	private String chooseMarketItem() {
		Random rand = new Random();
		int myRandomChoice;
		String item;
		do {
			myRandomChoice = rand.nextInt(10);
			myRandomChoice %= 7;
		} while (!Phonebook.getPhonebook().getMarket().marketItemsForSale.containsKey(myRandomChoice) || (money < Phonebook.getPhonebook().getMarket().marketItemsForSale.get(myRandomChoice).price));
		item = Phonebook.getPhonebook().getMarket().marketItemsForSale.get(myRandomChoice).itemName;
		return item;
	}

	protected void prepareForRestaurant() {
		//GUI call to go to business
		try {
			atDestination.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		//Once semaphore is released from GUI
		for (Role cust1 : roles) {
			if (cust1 instanceof RestaurantCustomerRole) {
				//Must be changed because doesn't have xHome, yHome
				//Phonebook.getPhonebook().getRestaurant().msgIWantFood(cust1, xHome, yHome);
				cust1.setRoleActive();
				stateChanged();
				return;
			}
		}

	}

	public void goToSleep() {
		alarmClock.schedule(new TimerTask() {
			public void run() {
				stateChanged();
			}
		},
		(((24 - TimeManager.getTimeManager().getTime().dayHour) + 8) * 1000));
	}

	@Override
	public String getName() {
		return name;
	}

	public void setHome(Housing place)
	{
		home = place;
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
