package person;

import housing.MaintenanceWorkerRole;

import java.awt.Point;
import java.util.Random;

import person.Role.RoleState;
import restaurant.AltWaiterRole;
import restaurant.CashierRole;
import restaurant.CookRole;
import restaurant.HostRole;
import restaurant.WaiterRole;
import market.MarketCustomerRole;
import market.MarketRunnerRole;
import market.SalesPersonRole;
import market.UPSmanRole;
import application.Phonebook;
import application.TimeManager;
import application.WatchTime;
import bank.Bank;
import bank.BankCustomerRole;
import bank.BankGuardRole;
import bank.BankTellerRole;
import bank.LoanOfficerRole;

public class Worker extends Person {
	//Data
	protected Job myJob = null;
	private int moneyMinThreshold = 20;
	private int moneyMaxThreshold = 200;
	protected Role workerRole = null;

	public Worker (String name, int money, String jobTitle, String jobPlace, int startT, int lunchT, int endT) {
		super(name);
		this.money = money;
		myJob = new Job(jobTitle, jobPlace ,startT, lunchT, endT, this);
	}

	public class Job {
		public String title;
		public String jobPlace;
		public int lunchBreakLength = 1; 
		public int wage;
		public Point workLocation;     //point has xCoor,yCoor
		public WatchTime startTime, lunchTime, endTime;
		public Worker myself;

		Job(String title, String jobPlace, int startT, int lunchT, int endT, Worker me) {

			myself = me;
			startTime = new WatchTime(startT, 0);
			lunchTime = new WatchTime(lunchT, 0);
			endTime = new WatchTime(endT, 0);
			this.title = title;
			this.jobPlace = jobPlace;

			//			if (title == "bankTeller") {
			//				workerRole = new BankTellerRole(name, myself, title);
			//				roles.add(workerRole);
			//			}
			//			if (title == "loanOfficer") {
			//				workerRole = new LoanOfficerRole(name, myself, title);
			//				roles.add(workerRole);			
			//			}
			//			if (title == "bankGuard") {
			//				workerRole = new BankGuardRole(name, myself, title);
			//				roles.add(workerRole);			
			//			}
			//			if (title == "marketRunner") {
			//				//	workerRole = new MarketRunnerRole(myself,title);
			//				roles.add(workerRole);
			//			}
			//			if (title == "marketSales") {
			//				workerRole = new SalesPersonRole(myself, name, title);
			//				roles.add(workerRole);
			//			}
			//			if (title == "UPSman") {
			//				workerRole = new UPSmanRole(myself, name, title);
			//				roles.add(workerRole);
			//			}
			//			if (title == "maintenance") {
			//				workerRole = new MaintenanceWorker(myself, name, title);
			//				roles.add(workerRole);
			//			}
			//			if (title == "cashier") {
			//				workerRole = new CashierRole(myself, name, title);
			//				roles.add(workerRole);
			//			}
			//			if (title == "host") {
			//				workerRole = new HostRole(myself, name, title);
			//				roles.add(workerRole);
			//			}
			//			if (title == "cook") {
			//				workerRole = new CookRole(myself, name, title);		//need to input name not title
			//				roles.add(workerRole);
			//			}
			//			if (title == "waiter") {
			//				workerRole = new WaiterRole(myself, name, title);
			//				roles.add(workerRole);
			//			}
			//			if (title == "altWaiter") {
			//				workerRole = new AltWaiterRole(myself, name, title);
			//				roles.add(workerRole);
			//			}

		}

		WatchTime getStartTime() {
			return endTime;
		}

		void setStartTime(int t) {
			startTime.setTime(t, 0);
		}

		WatchTime getLunchTime() {
			return lunchTime;
		}

		void setLunchTime(int t) {
			lunchTime.setTime(t, 0);
		}

		WatchTime getEndTime() {
			return endTime;
		}

		void setEndTime(int t) {
			endTime.setTime(t, 0);
		}

		void setTitle(String title) {
			this.title = title;
		}
	}


	//Messages
	void msgHereIsPayCheck (double amount) {
		money += amount;
	}

	//Scheduler
	public boolean pickAndExecuteAnAction() {
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

		//Job Related
		if ((myJob.getStartTime().hour - TimeManager.getTimeManager().getTime().dayHour) <= 1) {
			prepareForWork();
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
	private void prepareForWork() {

		if (myJob.jobPlace == "bank") {
			workerRole = Phonebook.getPhonebook().getBank().arrivedAtWork(this, myJob.title);
			roles.add(workerRole);
			workerRole.setRoleActive();
			return;
		}

		if (myJob.jobPlace == "market") {

			workerRole = Phonebook.getPhonebook().getMarket().arrivedAtWork(this, myJob.title);
			roles.add(workerRole);
			workerRole.setRoleActive();
			return;
		}

		if (myJob.jobPlace == "restaurant") {
			workerRole = Phonebook.getPhonebook().getRestaurant().arrivedAtWork(this, myJob.title);
			roles.add(workerRole);
			workerRole.setRoleActive();
			return;
		}

		return;
	}



	//			else if (title == "loanOfficer") {
	//				workerRole = new LoanOfficerRole(name, myself, title);
	//				roles.add(workerRole);			
	//			}
	//			if (title == "bankGuard") {
	//				workerRole = new BankGuardRole(name, myself, title);
	//				roles.add(workerRole);			
	//			}
	//			if (title == "marketRunner") {
	//				//	workerRole = new MarketRunnerRole(myself,title);
	//				roles.add(workerRole);
	//			}
	//			if (title == "marketSales") {
	//				workerRole = new SalesPersonRole(myself, name, title);
	//				roles.add(workerRole);
	//			}
	//			if (title == "UPSman") {
	//				workerRole = new UPSmanRole(myself, name, title);
	//				roles.add(workerRole);
	//			}
	//			if (title == "maintenance") {
	//				workerRole = new MaintenanceWorker(myself, name, title);
	//				roles.add(workerRole);
	//			}
	//			if (title == "cashier") {
	//				workerRole = new CashierRole(myself, name, title);
	//				roles.add(workerRole);
	//			}
	//			if (title == "host") {
	//				workerRole = new HostRole(myself, name, title);
	//				roles.add(workerRole);
	//			}
	//			if (title == "cook") {
	//				workerRole = new CookRole(myself, name, title);		//need to input name not title
	//				roles.add(workerRole);
	//			}
	//			if (title == "waiter") {
	//				workerRole = new WaiterRole(myself, name, title);
	//				roles.add(workerRole);
	//			}
	//			if (title == "altWaiter") {
	//				workerRole = new AltWaiterRole(myself, name, title);
	//				roles.add(workerRole);
	//			}

	public void goOffWork() {
		roles.remove(workerRole);
		workerRole = null;
		stateChanged();
	}

	public void setWorkerRole(Role workerRole) {
		this.workerRole = workerRole;
	}

	public Role getWorkerRole() {
		return workerRole;
	}
}
