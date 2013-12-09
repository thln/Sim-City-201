package person;

import java.util.Timer;
import java.util.TimerTask;

import person.Person.HungerLevel;
import person.Role.RoleState;
import bank.BankCustomerRole;
import application.Phonebook;
import application.TimeManager;
import application.TimeManager.Day;

public class Crook extends Person {
	//These people become dishonest customers in the restaurant

	//Robbing data
	enum RobState {robbedToday, waitingToRob, readyToRob};
	RobState robState = RobState.readyToRob;
	Timer robTimer = new Timer();
	
	public Crook(String name, double money) {
		super(name, money);
	}

	public boolean pickAndExecuteAnAction() {
		if (getHunger() == HungerLevel.full) {
			startHungerTimer();
			return true;
		}
		
		if (robState == RobState.robbedToday){
			robState = RobState.waitingToRob;
			startRobTimer();
			return true;
		}

		synchronized (roles) {
			if (!roles.isEmpty()) {
				for (Role r : roles) {
					if (r.getState() == RoleState.active) {
						return r.pickAndExecuteAnAction();
					}
				}
			}
		}

		//If no role is active
		//Checking the time

		//If it's time to rob the bank, go ahead
		if (robState == RobState.readyToRob && Phonebook.getPhonebook().getEastBank().isOpen()){
			robState = RobState.robbedToday;		//reset state for new timer
			prepareForBank();
			return true;
		}

		//Rent Related
		if(TimeManager.getTimeManager().getTime().day == Day.Monday)
		{
			resetRentMailbox();
			return true;
		}
		if(TimeManager.getTimeManager().getTime().day == Day.Sunday && !checkedMailbox)
		{
			prepareForRent();
			return true;
		}

		//Hunger Related
		if (getHunger() == HungerLevel.hungry) {
			if (Phonebook.getPhonebook().getChineseRestaurant().isOpen()) {	
				prepareForRestaurant();
				return true;
			}
		}
		else //if you do have food in the fridge
		{
		//	eatAtHome(); 
			return true;
		}

		//Market Related
		if (!hasFoodInFridge || carStatus == CarState.wantsCar) {
			if (money <= moneyMinThreshold && !hasFoodInFridge) {
				if (Phonebook.getPhonebook().getEastBank().isOpen()){ // || Phonebook.getPhonebook().getEastBank().isOpen()){	
					return true;
				}
			}
			else {
				if (Phonebook.getPhonebook().getEastMarket().isOpen()){
					return true;
				}
			}
		}

		goToSleep();
		return false;
	}
	
	//actions
	
	protected void startRobTimer() {
		robTimer.schedule(new TimerTask() {
			public void run() {
				robState = RobState.readyToRob;
				stateChanged();
			}
		},
		(30000)); 
	}
}

