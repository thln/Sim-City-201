package person;

import person.Role.RoleState;
import bank.BankCustomerRole;
import application.Phonebook;
import application.TimeManager;
import application.TimeManager.Day;

public class Crook extends Person {
	//These people become dishonest customers in the restaurant

	public Crook(String name, double money) {
		super(name, money);
	}

	public boolean pickAndExecuteAnAction() {
		if (getHunger() == HungerLevel.full) {
			startHungerTimer();
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

		if (TimeManager.getTimeManager().getTime().day == Day.Friday && TimeManager.getTimeManager().getTime().dayHour == 12 && TimeManager.getTimeManager().getTime().dayMinute == 0) {
			robBank();
			return true;
		}

		//Rent Related
		if(TimeManager.getTimeManager().getTime().day == Day.Monday)
		{
			resetRentMailbox();
		}
		if(TimeManager.getTimeManager().getTime().day == Day.Sunday && !checkedMailbox)
		{
			prepareForRent();
			return true;
		}

		//Hunger Related
		if (getHunger() == HungerLevel.hungry) {
			//If you don't have food in the fridge
			if (!hasFoodInFridge) {
				if (money <= moneyMinThreshold) { 
					//This if says go to the business if it is open and at least 1 hour before closing time
					if ((TimeManager.getTimeManager().getTime().dayHour >= Phonebook.getPhonebook().getEastBank().openTime.hour) &&
							(TimeManager.getTimeManager().getTime().dayHour < Phonebook.getPhonebook().getEastBank().closeTime.hour)) {
						prepareForBank();
						return true;
					}
				}
				else if (Phonebook.getPhonebook().getChineseRestaurant().isOpen()) {	
					prepareForRestaurant();
					return true;
				}
			}
			else //if you do have food in the fridge
			{
				eatAtHome(); //empty method for now...
				return true;
			}
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

	private void robBank() {
		//GUI call to go to business
		//		try {
		//			atDestination.acquire();
		//		} catch (InterruptedException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//
		//		}
		//Once semaphore is released from GUI

		for (Role cust1 : roles) {
			if (cust1 instanceof BankCustomerRole) {
				BankCustomerRole bankRobber = (BankCustomerRole) cust1;

				bankRobber.setDesire("robBank");
				Phonebook.getPhonebook().getEastBank().bankGuardRole.msgRobbingBank(bankRobber);
				cust1.setRoleActive();
				stateChanged();
				return;
			}
		}
	}
}

