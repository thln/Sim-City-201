package person;

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

public abstract class Person extends Agent {

	//Data
	String name;
	public double money;
	private Semaphore atDestination = new Semaphore(0,true);

	//Role Related
	public List<Role> roles = Collections.synchronizedList(new ArrayList<Role>()); 	//contains all the customer roles
	protected Role workerRole;

	//Car Related
	public enum CarState {noCar, wantsCar, hasCar};
	public CarState carStatus = CarState.noCar;
	final int carCost = 1000;

	//Hunger Related
	public HashMap <String, Integer> Inventory = new HashMap<String, Integer>(); 		//Food list
	public boolean hasFoodInFridge;

	//Bank Related
	public int accountNum;
	public double accountBalance;
	public double desiredCash;
	public double depositAmount;
	public double withdrawAmount;
	int moneyMinThreshold = 20;
	int moneyMaxThreshold = 200;

	//Time Related
	public int sleepTime = 22;
	protected int newTime;




	Person(String name) {
		this.name = name;
		//	roles.add(new RestaurantCustomerRole(getName(), this));
		//	roles.add(new MarketCustomerRole(this));

		roles.add(new BankCustomerRole(this, getName(), "BankCustomerRole"));
				//Phonebook.bank.bankGuardRole, 100, 20, 0, 10));
		newTime = -5;
		//constructors should be changed so they match
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


					if (r.getState() == RoleState.active) {
						return r.pickAndExecuteAnAction();
					}

					if (r.getState() == RoleState.waitingToExecute) {

						if (r.equals(workerRole)) {
							Do("Going to work");
							prepareForWork(r);
						}

						if (r instanceof BankCustomerRole) {
							Do("Going to bank");
							if (this instanceof Crook)
								robBank(r);
							else
								prepareForBank(r);
						}
						if (r instanceof MarketCustomerRole) {
							prepareForMarket(r);
						}
						if (r instanceof RestaurantCustomerRole) {
							prepareForRestaurant(r);
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
	}

	//Actions

	public abstract void updateTime(int newTime);

	private void prepareForBank (Role r){
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

		BankCustomerRole cust1 = (BankCustomerRole) r;
		if (accountNum != 0) {
			if (money <= moneyMinThreshold){
				withdrawAmount = 100;
				cust1.setDesire("withdraw");
			}
			if (money >= moneyMaxThreshold){
				depositAmount = 200;
				cust1.setDesire("deposit");
			}
		}
		//(String name, Person p1, BankGuard guard1, int desiredCash, int deposit, int accNum, int cash)
		//if bank customer role hasn't already been instantiated, instatiate it
		//phonebook.bank.bankGuardRole.msgArrivedAtBank(cust1);
		setRoleActive(r);
		stateChanged();
	}

	private void robBank(Role r) {
		//GUI call to go to business
		try {
			atDestination.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		//Once semaphore is released from GUI

		setRoleActive(r);
		BankCustomerRole cust1 = (BankCustomerRole) r;
		cust1.setDesire("robBank");
		Phonebook.bank.bankGuardRole.msgRobbingBank(cust1);
		stateChanged();
	}

	private void prepareForMarket(Role r) {
		//GUI call to go to business
		try {
			atDestination.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		//Once semaphore is released from GUI

		if(accountBalance >= (carCost + 100)) {
			if (carStatus == CarState.noCar) {
				carStatus = CarState.wantsCar;
			}
		}

		if (hasFoodInFridge == false) {
			MarketCustomerRole cust1 = (MarketCustomerRole) r;

			//choosing random item to buy from market
			String item;
			item = chooseMarketItem();
			phonebook.market.salesPersonRole.msgIWantProducts(cust1, item, 3);
		}
		else if (carStatus == CarState.wantsCar) {
			MarketCustomerRole cust1 = (MarketCustomerRole) r;
			phonebook.market.salesPersonRole.msgIWantProducts(cust1, "Car", 1);
			//must set desire to hasCar once car is bought
		}

		setRoleActive(r);
		stateChanged();
	}

	private String chooseMarketItem() {
		Random rand = new Random();
		int myRandomChoice;
		String item;
		do {
			myRandomChoice = rand.nextInt(10);
			myRandomChoice %= 7;
		} while (!phonebook.market.marketItemsForSale.containsKey(myRandomChoice) || (money < phonebook.market.marketItemsForSale.get(myRandomChoice).price));
		item = phonebook.market.marketItemsForSale.get(myRandomChoice).itemName;
		return item;
	}

	private void prepareForRestaurant(Role r) {
		//GUI call to go to business
		try {
			atDestination.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		//Once semaphore is released from GUI
		RestaurantCustomerRole cust1 = (RestaurantCustomerRole) r;

		//must change message because no x,y coordinates have been generated
		//phonebook.restaurant.hostRole.msgIWantFood(cust1, xHome, yHome);
		setRoleActive(r);
		stateChanged();
	}

	private void prepareForWork(Role r) {
		//GUI call to go to business
		/*
		try {
			atDestination.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		 */
		//Once semaphore is released from GUI

		if (r instanceof BankTellerRole)
			Phonebook.bank.bankGuardRole.msgTellerCameToWork((BankTellerRole) r);
		setRoleActive(r);
		stateChanged();
	}

	public void setRoleActive(Role role) {
		role.setState(RoleState.active);
	}

	public void setRoleInactive(Role role) {
		role.setState(RoleState.inActive);
	}

	public void goToSleep() {
		//puts agent to sleep
	}

	public Role getWorkerRole() {
		return workerRole;
	}

	public void setWorkerRole(Role workerRole) {
		this.workerRole = workerRole;
	}
	
	@Override
	public String getName() {
		return name;
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
