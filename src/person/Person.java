package person;

import housing.Housing;

import java.util.*;
import java.util.concurrent.Semaphore;

import chineseRestaurant.ChineseRestaurantCustomerRole;
import italianRestaurant.*;
import bank.BankCustomerRole;
import bank.BankCustomerRole.CustomerState;
import market.MarketCustomerRole;
import person.Role;
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
	protected Semaphore atDestination;
	public Housing home;
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
	public enum HungerLevel {full, moderate, hungry};
	protected HungerLevel hunger;
	int eatTime = 4;
	protected Semaphore eating = new Semaphore(0, true);
	//IMPORTANT ADD TO MESSAGES
	protected Semaphore waitingAtBus = new Semaphore(0, true);
	protected Semaphore beingTransported = new Semaphore(0, true);

	//Bank Related
	public double money;
	public String myBank;
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

	Person(String name, double moneyz) {
		this.name = name;
		this.money = moneyz;
		roles.add(new BankCustomerRole(this, getName(), "Bank Customer"));
		roles.add(new MarketCustomerRole(this, getName(), "Market Customer"));
		roles.add(new ChineseRestaurantCustomerRole(this, getName(), "Restaurant Customer"));
		nextTask = new Timer();
		atDestination = new Semaphore(0,true);
		setHunger(HungerLevel.full);
		hasFoodInFridge = false;
	}

	public void msgAtDestination() 
	{
		if(atDestination.availablePermits() < 1)
		{
			getAtDestination().release();
		}
	}
	
	public void msgBusIsHere()
	{
		if(waitingAtBus.availablePermits() < 1)
		{
			getWaitingAtBus().release();
		}
	}
	
	public void msgAtBusStopDestination()
	{
		if(beingTransported.availablePermits() < 1)
		{
			beingTransported.release();
		}
	}

	//Scheduler
	protected abstract boolean pickAndExecuteAnAction();

	//Actions
	protected void eatAtHome() {
		currentRoleName = "";
		int timeConversion = 60 * TimeManager.getSpeedOfTime();
		print("Going to eat at home");
		nextTask.schedule(new TimerTask() {
			public void run() {  
				eating.release();
				hasFoodInFridge = false;  
				print("Finished eating at home");
				hunger = HungerLevel.full;
			}
		}, eatTime * timeConversion);
		try {
			eating.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
	}

	protected void prepareForBank () {

		gui.popToMiddle();
		if (!(this instanceof Crook))
			Do("Becoming Bank Customer");

		if (home.type.equals("East Apartment"))
			gui.walk = gui.decideForBus("East Bank");
		else
			gui.walk = gui.decideForBus("West Bank");

		gui.walk = true;
		if (gui.walk)
			gui.popToMiddle();

		if (!gui.walk){

			goToBusStop(Phonebook.getPhonebook().getEastBank().getClosestBusStop().getBusStopNumber());
			//goToBusStop(gu)
			//What is this supposed to mean?
			//			if (home.type.equals("East Apartment")){
//				gui.doGoToBus(Phonebook.getPhonebook().getEastBank().getClosestStop().getX(),
//						Phonebook.getPhonebook().getEastBank().getClosestStop().getY());
//			}
//			else {
//				gui.doGoToBus(Phonebook.getPhonebook().getWestBank().getClosestStop().getX(),
//						Phonebook.getPhonebook().getWestBank().getClosestStop().getY());
//			}
//
//			try {
//				atDestination.acquire();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}

		if (home.type.equals("East Apartment"))
			getGui().DoGoToBank("East");
		else
			getGui().DoGoToBank("West");

		try {
			atDestination.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();

		}

		for (Role cust1 : roles) {
			if (cust1 instanceof BankCustomerRole) 
			{
				BankCustomerRole BCR = (BankCustomerRole) cust1;
				BankCustomerGui bg = new BankCustomerGui(BCR);
				BCR.setGui(bg);

				if (this instanceof Crook){
					print("Time to rob da bank fools!");
					currentRoleName = "Bank Robber";
					BCR.setDesire("robBank");
					BCR.state = CustomerState.ready;
				}
				else {
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
				}
				cust1.setRoleActive();
				if (home.type.equals("East Apartment"))
					Phonebook.getPhonebook().getEastBank().msgCustomerArrived(BCR);
				else
					Phonebook.getPhonebook().getWestBank().msgCustomerArrived(BCR);
				stateChanged();
				return;
			}
		}
	}

	protected void resetRentMailbox()
	{
		checkedMailbox = false;
	}

	protected void prepareForRent() {
		AlertLog.getInstance().logInfo(AlertTag.HOUSING, name, "Depositing rent for week. Rent is " + Phonebook.getPhonebook().getHousingMaintenanceCompany().mailbox.getApartmentRentCost());
		//print("Depositing rent for week. Rent is " + Phonebook.getPhonebook().getHousingMaintenanceCompany().mailbox.getApartmentRentCost());
		if( money >= Phonebook.getPhonebook().getHousingMaintenanceCompany().mailbox.getApartmentRentCost())
		{
			money -= Phonebook.getPhonebook().getHousingMaintenanceCompany().mailbox.getApartmentRentCost();
			Phonebook.getPhonebook().getHousingMaintenanceCompany().mailbox.dropRentMoney(Phonebook.getPhonebook().getHousingMaintenanceCompany().mailbox.getApartmentRentCost());
			checkedMailbox = true;
			return;
		}
		else if (accountBalance >= Phonebook.getPhonebook().getHousingMaintenanceCompany().mailbox.getApartmentRentCost())
		{
			accountBalance -= Phonebook.getPhonebook().getHousingMaintenanceCompany().mailbox.getApartmentRentCost();
			Phonebook.getPhonebook().getHousingMaintenanceCompany().mailbox.dropRentMoney(Phonebook.getPhonebook().getHousingMaintenanceCompany().mailbox.getApartmentRentCost());
			checkedMailbox = true;
		}
		else
		{
			print("Not enough money to pay rent");
			checkedMailbox = true;
			//Non Norm, making a loan
		}
	}

	protected void goToBusStop(int destinationBusStopNumber)
	{
//		if (home.type.equals("East Apartment")){
//			gui.doGoToBus(Phonebook.getPhonebook().getEastBank().getClosestStop().getX(),
//					Phonebook.getPhonebook().getEastBank().getClosestStop().getY());
//		}
//		else {
//			gui.doGoToBus(Phonebook.getPhonebook().getWestBank().getClosestStop().getX(),
//					Phonebook.getPhonebook().getWestBank().getClosestStop().getY());
//		}
		
		print("Going to bus Stop "+ gui.getClosestBusStopNumber());
		gui.doGoToBusStop();
		//Finish the GUI version of it
		try 
		{
			atDestination.acquire();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		print("At bus Stop "+ gui.getClosestBusStopNumber() + ". Now waiting");
		Phonebook.getPhonebook().getAllBusStops().get(gui.getClosestBusStopNumber()).waitingForBus(this);
		try
		{
			waitingAtBus.acquire();
			//waitingAtBus.acquire();
			//maybe have to do double acquires?
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		print(" Blah");
		print("Telling " + Phonebook.getPhonebook().getAllBusStops().get(gui.getClosestBusStopNumber()).getCurrentBus().getName() + " that I'm getting on");
		Phonebook.getPhonebook().getAllBusStops().get(gui.getClosestBusStopNumber()).getCurrentBus().msgGettingOnBus(this, destinationBusStopNumber);
		gui.setInvisible();
		try
		{
			beingTransported.acquire();
			//beingTransported.acquire();
			//maybe have to do double acquires?
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		print("Arriving at destination Bus Stop: " + destinationBusStopNumber);
		gui.getOffBus(destinationBusStopNumber);
	}
	
	protected void prepareForMarket() {
		print("Going to market as a customer");
		gui.walk = true;
		if (gui.walk)
			gui.popToMiddle();
		if (home.type.equals("East Apartment")){
			getGui().DoGoToMarket("East");
		}
		else{
			getGui().DoGoToMarket("West");
		}
			
		try {
			atDestination.acquire();
		} catch (InterruptedException e) {
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
			for (Role cust1 : roles) {
				if (cust1 instanceof MarketCustomerRole) {
					MarketCustomerRole MCR = (MarketCustomerRole) cust1;
					MCR.setItem("");
					cust1.setRoleActive();
					Phonebook.getPhonebook().getEastMarket().arrived(MCR);
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
					MCR.setItem("Car");
					cust1.setRoleActive();
					Phonebook.getPhonebook().getEastMarket().arrived(MCR);
					currentRoleName = "Market Customer";
					stateChanged();
					return;
				}
			}
		}
	}

	protected void prepareForRestaurant() {
		gui.walk = true;
		if (gui.walk)
			gui.popToMiddle();
		getGui().DoGoToRestaurant("chinese");
		try {
			atDestination.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();

		}

		for (Role cust1 : roles) {
			if (cust1 instanceof ChineseRestaurantCustomerRole) {
				ChineseRestaurantCustomerRole RCR = (ChineseRestaurantCustomerRole) cust1;
				if (Phonebook.getPhonebook().getChineseRestaurant().arrived(RCR)) {
					currentRoleName = "Chinese Restaurant Customer";
					cust1.setRoleActive();
					stateChanged();
				}
				return;
			}
			if (cust1 instanceof ItalianCustomerRole) {
				ItalianCustomerRole RCR = (ItalianCustomerRole) cust1;
				if (Phonebook.getPhonebook().getItalianRestaurant().arrived(RCR)) {
					currentRoleName = "Italian Restaurant Customer";
					cust1.setRoleActive();
					stateChanged();
				}
				return;
			}
		}

	}

	protected void goToSleep() {
//	if (gui.getxPos() != gui.getxHome() && gui.getyPos() != gui.getyHome()){
		getGui().DoGoHome();
		try {
			atDestination.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
			//
		}
//			}

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
		setHunger(HungerLevel.moderate);

		//After arrives home
		hungerTimer.schedule(new TimerTask() {
			public void run() {
				setHunger(HungerLevel.hungry);
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

	public void setGui(PersonGui g) {
		this.gui = g;
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

	public Semaphore getAtDestination() {
		return atDestination;
	}
	
	public Semaphore getWaitingAtBus()
	{
		return waitingAtBus;
	}

	public Semaphore getBeingTransported()
	{
		return beingTransported;
	}
	
	public void setAtDestination(Semaphore atDestination) {
		this.atDestination = atDestination;
	}

	public PersonGui getGui() {
		return gui;
	}

	public HungerLevel getHunger() {
		return hunger;
	}

	public void setHunger(HungerLevel hunger) {
		this.hunger = hunger;
	}
}