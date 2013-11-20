package person;

import housing.MaintenanceWorker;

import java.awt.Point;

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
		simulationTime = timeManager.getTime();

		if((myJob.getStartTime().hour - simulationTime.dayHour) <= 1) {
			prepareForWork();
			return true;
		}






		return false;
		//		return makeDecision(newTime);
	}


	//Actions
	private void prepareForWork() {

		if (myJob.jobPlace == "bank") {
			workerRole = Phonebook.getPhonebook().getBank().arrivedAtWork(this, myJob.title);
			roles.add(workerRole);
			setRoleActive(workerRole);
			return;
		}

		if (myJob.jobPlace == "market") {

			workerRole = Phonebook.getPhonebook().getMarket().arrivedAtWork(this, myJob.title);
			roles.add(workerRole);
			setRoleActive(workerRole);
			return;
		}
		
		if (myJob.jobPlace == "restaurant") {
			workerRole = Phonebook.getPhonebook().getRestaurant().arrivedAtWork(this, myJob.title);
			roles.add(workerRole);
			setRoleActive(workerRole);
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
		
		
	}
	
	public void goOffWork() {
		roles.remove(workerRole);
		workerRole = null;
		stateChanged();
	}

}
