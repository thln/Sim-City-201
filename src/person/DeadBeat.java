package person;

import person.Person.CarState;
import person.Role.RoleState;
import application.Phonebook;
import application.TimeManager;

public class Deadbeat extends Person {

	String name;

	public Deadbeat(String name,  int money) {
		super(name);
		this.money = money;
		this.name = name;
	}


	public void msgWelfareCheck() {
		money += 50;
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
		
		//Park Related
		//Start day in park for a couple hours
		if (TimeManager.getTimeManager().getTime().dayHour == 10) {
			visitPark();
			return true;
		}

		//Hunger Related
		if (hungry) {
			//If you don't have food in the fridge
			if (!hasFoodInFridge) {
				if (money <= moneyMinThreshold) { 
					//This if says go to the business if it is open and at least 1 hour before closing time
					if ((TimeManager.getTimeManager().getTime().dayHour >= Phonebook.getPhonebook().getBank().openTime.hour) &&
							(TimeManager.getTimeManager().getTime().dayHour < Phonebook.getPhonebook().getBank().closeTime.hour)) {
						prepareForBank();
						return true;
					}
				}
				else if ((TimeManager.getTimeManager().getTime().dayHour >= Phonebook.getPhonebook().getRestaurant().openTime.hour) &&
						(TimeManager.getTimeManager().getTime().dayHour < Phonebook.getPhonebook().getRestaurant().closeTime.hour)) {
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
				if ((TimeManager.getTimeManager().getTime().dayHour >= Phonebook.getPhonebook().getBank().openTime.hour) &&
						(TimeManager.getTimeManager().getTime().dayHour < Phonebook.getPhonebook().getBank().closeTime.hour)) {
					prepareForBank();
					return true;
				}
			}
			else {
				if ((TimeManager.getTimeManager().getTime().dayHour >= Phonebook.getPhonebook().getMarket().openTime.hour) &&
						(TimeManager.getTimeManager().getTime().dayHour < Phonebook.getPhonebook().getMarket().closeTime.hour)) {
					prepareForMarket();
					return true;
				}
			}
		}

		return false;
	}


	//Actions
	public void visitPark() {
		//Go visit the park (GUI)
	}
}
