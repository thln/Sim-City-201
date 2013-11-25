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

public abstract class Person extends Agent{

	//Data
	String name;
	private Semaphore atDestination = new Semaphore(0,true);
	private Housing home;
	private Timer alarmClock = new Timer();
	private Timer hungerTimer = new Timer();

	//Role Related
	public List<Role> roles = Collections.synchronizedList(new ArrayList<Role>());         //contains all the customer role
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
		currentRoleName = "";
		print("Going to eat at home");
	}

	protected void prepareForBank() {
		print("Becoming Bank Customer");
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
		for (Role cust1 : roles) {
			if (cust1 instanceof BankCustomerRole) 
			{
				BankCustomerRole BCR = (BankCustomerRole) cust1;
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
				Phonebook.getPhonebook().getBank().getBankGuard(cust1.test).msgArrivedAtBank(BCR);		
				return;
			}
		}
	}

	protected void prepareForMarket() {
		//                //GUI call to go to business
		//                try {
		//                        atDestination.acquire();
		//                } catch (InterruptedException e) {
		//                        // TODO Auto-generated catch block
		//                        e.printStackTrace();
		//
		//                }
		//                //Once semaphore is released from GUI

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
					currentRoleName = "Market Customer";
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
		/*
                try {
                        atDestination.acquire();
                } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();

                }
		 */
		//Once semaphore is released from GUI
		for (Role cust1 : roles) {
			if (cust1 instanceof RestaurantCustomerRole) {
				//Must be changed because doesn't have xHome, yHome
				//Phonebook.getPhonebook().getRestaurant().msgIWantFood(cust1, xHome, yHome);
				currentRoleName = "Restaurant Customer";
				cust1.setRoleActive();
				stateChanged();
				return;
			}
		}

	}

	protected void goToSleep() {
		//                gui.goHome();
		//                try {
		//                        atDestination.acquire();
		//                } catch (InterruptedException e) {
		//                        // TODO Auto-generated catch block
		//                        e.printStackTrace();
		//
		//                }
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
		//                gui.goHome();
		//                try {
		//                        atDestination.acquire();
		//                } catch (InterruptedException e) {
		//                        // TODO Auto-generated catch block
		//                        e.printStackTrace();
		//
		//                }

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
}