package person;

import person.Person.CarState;
import person.Role.RoleState;
import bank.BankCustomerRole;
import application.Phonebook;
import application.TimeManager.Day;
import application.TimeManager.Time;

public class Crook extends Person {
	//These people become dishonest customers in the restaurant

	String name;

	public Crook(String name,  int money) {
		super(name);
		this.money = money;
		this.name = name;
	}

	protected boolean pickAndExecuteAnAction() {

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
		simulationTime = timeManager.getTime();

		if (simulationTime.day == Day.Friday && simulationTime.dayHour == 12 && simulationTime.dayMinute == 0) {
			robBank();
			return true;
		}

		//Hunger Related
		if (hungry) {
			//If you don't have food in the fridge
			if (!hasFoodInFridge) {
				if (money <= moneyMinThreshold) { 
					//This if says go to the business if it is open and at least 1 hour before closing time
					if ((simulationTime.dayHour >= Phonebook.getPhonebook().getBank().openTime.hour) &&
							(simulationTime.dayHour < Phonebook.getPhonebook().getBank().closeTime.hour)) {
						prepareForBank();
						return true;
					}
				}
				else if ((simulationTime.dayHour >= Phonebook.getPhonebook().getRestaurant().openTime.hour) &&
						(simulationTime.dayHour < Phonebook.getPhonebook().getRestaurant().closeTime.hour)) {
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
				if ((simulationTime.dayHour >= Phonebook.getPhonebook().getBank().openTime.hour) &&
						(simulationTime.dayHour < Phonebook.getPhonebook().getBank().closeTime.hour)) {
					prepareForBank();
					return true;
				}
			}
			else {
				if ((simulationTime.dayHour >= Phonebook.getPhonebook().getMarket().openTime.hour) &&
						(simulationTime.dayHour < Phonebook.getPhonebook().getMarket().closeTime.hour)) {
					prepareForMarket();
					return true;
				}
			}
		}

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
				Phonebook.getPhonebook().getBank().bankGuardRole.msgRobbingBank(bankRobber);
				cust1.setRoleActive();
				stateChanged();
				return;
			}
		}
	}
}

