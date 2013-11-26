package person;

import housing.Housing;

import java.util.*;
import java.util.concurrent.Semaphore;

import bank.BankCustomerRole;
import market.MarketCustomerRole;
import person.Role;
import restaurant.RestaurantCustomerRole;
import agent.Agent;
import application.Phonebook;
import application.TimeManager;
import application.gui.trace.AlertLog;
import application.gui.trace.AlertTag;
import application.gui.animation.*;
import application.gui.animation.agentGui.*;

public abstract class Person extends Agent{

	//Data
	String name;
	protected Semaphore atDestination = new Semaphore(0,true);
	private Housing home;
	private Timer alarmClock = new Timer();
	private Timer hungerTimer = new Timer();
	protected PersonGui gui;
	BuildingPanel marketPanel = null;
	BuildingPanel bankPanel = null;
	BuildingPanel housePanel = null;
	BuildingPanel restPanel = null;

	//Role Related
	public List<Role> roles = Collections.synchronizedList(new ArrayList<Role>());         //contains all the customer role
	public List<Gui> guis = Collections.synchronizedList(new ArrayList<Gui>());
	protected String currentRoleName;

	//Car Related
	public enum CarState {noCar, wantsCar, hasCar};
	public CarState carStatus = CarState.noCar;
	final int carCost = 1000;

	//Hunger Related
	public HashMap <String, Integer> Inventory = new HashMap<String, Integer>();                 //Food list
	public boolean hasFoodInFridge = false;
	public enum HungerLevel {full, moderate, hungry, starving};
	HungerLevel hunger = HungerLevel.full;

	//Bank Related
	public double money;

	public int accountNum;
	public double accountBalance;
	public double desiredCash;
	public double depositAmount;
	public double loan;
	public int moneyMinThreshold = 20;
	public int moneyMaxThreshold = 200;

	//Rent Related
	public boolean checkedMailbox = false;

	//Time Related
	public int sleepTime = 22;
	protected Timer nextTask;
	boolean upcomingTask;

	Person(String name) {
		this.name = name;
		roles.add(new BankCustomerRole(this, getName(), "Bank Customer"));
		roles.add(new MarketCustomerRole(this, getName(), "Market Customer"));
		roles.add(new RestaurantCustomerRole(this, getName(), "Restaurant Customer"));
		nextTask = new Timer();
		upcomingTask = false;
	}

	public void msgAtDestination() {
		atDestination.release();
	}

	//Scheduler
	protected abstract boolean pickAndExecuteAnAction();

	//Actions
	protected void eatAtHome() {
		currentRoleName = "";
		print("Going to eat at home");
	}

	protected void prepareForBank () {
		Do("Becoming Bank Customer");
		gui.DoGoToBank();
		try {
			atDestination.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		for (Role cust1 : roles) {
			if (cust1 instanceof BankCustomerRole) 
			{
				BankCustomerRole BCR = (BankCustomerRole) cust1;
				BankCustomerGui bg = new BankCustomerGui(BCR);
				BCR.setGui(bg);
				currentRoleName = "Bank Customer";

				if (money <= moneyMinThreshold)
					desiredCash = 100;
				else if (money >= moneyMaxThreshold)
					depositAmount = money-moneyMaxThreshold+100;

				if (accountNum != 0) {
					if (money <= moneyMinThreshold){
						BCR.setDesire("withdraw");
					}
					if (money >= moneyMaxThreshold){
						BCR.setDesire("deposit");
					}
				}
				cust1.setRoleActive();
				//bankPanel.addGui(bg);
				stateChanged();
				return;
			}
		}
	}

	protected void resetRentMailbox()
	{
		checkedMailbox = false;
	}

    protected void prepareForRent()
    {
    	AlertLog.getInstance().logInfo(AlertTag.HOUSING, name, "Depositing rent for week. Rent is " + Phonebook.getPhonebook().getHousingMaintenanceCompany().mailbox.getApartmentRentCost());
		//print("Depositing rent for week. Rent is " + Phonebook.getPhonebook().getHousingMaintenanceCompany().mailbox.getApartmentRentCost());
    	if( money >= Phonebook.getPhonebook().getHousingMaintenanceCompany().mailbox.getApartmentRentCost())
    	{
    		money -= Phonebook.getPhonebook().getHousingMaintenanceCompany().mailbox.getApartmentRentCost();
    		Phonebook.getPhonebook().getHousingMaintenanceCompany().mailbox.dropRentMoney(Phonebook.getPhonebook().getHousingMaintenanceCompany().mailbox.getApartmentRentCost());
    		checkedMailbox = true;
    	}
    	else if (accountBalance >= Phonebook.getPhonebook().getHousingMaintenanceCompany().mailbox.getApartmentRentCost())
    	{
    		accountBalance -= Phonebook.getPhonebook().getHousingMaintenanceCompany().mailbox.getApartmentRentCost();
    		Phonebook.getPhonebook().getHousingMaintenanceCompany().mailbox.dropRentMoney(Phonebook.getPhonebook().getHousingMaintenanceCompany().mailbox.getApartmentRentCost());
    		checkedMailbox = true;
    	}
    	else
    	{
    		//Non Norm, making a loan
    	}
    }
	
	protected void prepareForMarket() {
		print("testMonkeys");
		gui.DoGoToMarket();
		try {
			atDestination.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

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
					MarketCustomerRole MCR = (MarketCustomerRole) cust1;
					MarketCustomerGui mg = new MarketCustomerGui(MCR);
					MCR.setGui(mg);
					Phonebook.getPhonebook().getMarket().salesPersonRole.msgIWantProducts((MarketCustomerRole) cust1, item, 3);
					cust1.setRoleActive();
					marketPanel.addGui(mg);
					currentRoleName = "Market Customer";
					stateChanged();
					return;
				}
			}
		}
		else if (carStatus == CarState.wantsCar) {
			for (Role cust1 : roles) {
				if (cust1 instanceof MarketCustomerRole) {
					MarketCustomerRole MCR = (MarketCustomerRole) cust1;
					MarketCustomerGui mg = new MarketCustomerGui(MCR);
					MCR.setGui(mg);
					Phonebook.getPhonebook().getMarket().salesPersonRole.msgIWantProducts((MarketCustomerRole) cust1, "Car", 3);
					cust1.setRoleActive();
					marketPanel.addGui(mg);
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
		gui.DoGoToRestaurant();
		try {
			atDestination.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		for (Role cust1 : roles) {
			if (cust1 instanceof RestaurantCustomerRole) {
				RestaurantCustomerRole RCR = (RestaurantCustomerRole) cust1;
				RestaurantCustomerGui rg = new RestaurantCustomerGui(RCR);
				RCR.setGui(rg);
				//Must be changed because doesn't have xHome, yHome
				//Phonebook.getPhonebook().getRestaurant().msgIWantFood(cust1, xHome, yHome);
				currentRoleName = "Restaurant Customer";
				cust1.setRoleActive();
				restPanel.addGui(rg);
				stateChanged();
				return;
			}
		}

	}

	protected void goToSleep() {
		gui.DoGoHome();
		try {
			atDestination.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//
		}

		currentRoleName = " ";
		//After arrives home
		alarmClock.schedule(new TimerTask() {
			public void run() {
				stateChanged();
			}
		},
		(((24 - TimeManager.getTimeManager().getTime().dayHour) + 8) * 500)); //Check this math please?
	}

	protected void startHungerTimer() {
		hunger = HungerLevel.moderate;

		//After arrives home
		hungerTimer.schedule(new TimerTask() {
			public void run() {
				hunger = HungerLevel.hungry;
				stateChanged();
			}
		},
		(3000)); //Check this math please?
	}

	@Override
	public String getName() {
		return name;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public void setHome(Housing place) {
		home = place;
	}

	public String getCurrentRoleName()
	{
		return currentRoleName;
	}

	public void print(String msg)
	{
		AlertLog.getInstance().logInfo(AlertTag.GENERAL_CITY, name, msg);
	}

	public Housing getHousing()
	{
		return home;
	}

	public void setGui(PersonGui gui) {
		this.gui = gui;
	}

	public void setPanel(AnimationPanel ap) {
		ArrayList<Building> buildings = ap.getBuildings();
		for(Building building : buildings) {
			if(building.getName().toLowerCase().contains("market")) {
				marketPanel = building.myBuildingPanel;
			}
			if(building.getName().toLowerCase().contains("bank")) {
				bankPanel = building.myBuildingPanel;
			}
			if(building.getName().toLowerCase().contains("house")) {
				housePanel = building.myBuildingPanel;
			}
			if(building.getName().toLowerCase().contains("restaurant")) {
				restPanel = building.myBuildingPanel;
			}
		}
	}
}