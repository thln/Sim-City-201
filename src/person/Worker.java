package person;

import java.awt.Point;

import person.Role.roleState;
import restaurant.CashierRole;
import restaurant.CookRole;
import restaurant.HostRole;
import restaurant.WaiterRole;
import market.MarketCustomerRole;
import market.SalesPersonRole;
import market.UPSmanRole;
import bank.BankCustomerRole;
import bank.BankGuardRole;
import bank.BankTellerRole;
import bank.LoanOfficerRole;

public class Worker extends Person {
	//Data
	Job myJob = null;
	int marketTime;
	int bankTime = 8;
	int sleepTime = 22;

	public Worker (String name, int money, String jobTitle, int startT, int lunchT, int endT)  {
		super(name);
		this.money = money;       
		myJob = new Job(jobTitle, startT, lunchT, endT, this);
		marketTime = myJob.getEndTime();
	}

	class Job {
		String title;
		int lunchBreakLength = 1; 
		int wage;
		Point workLocation;     //point has xCoor,yCoor
		private int startTime, lunchTime, endTime;
		Worker myself;

		Job(String title, int startT, int lunchT, int endT, Worker me) {

			myself = me;
			startTime = startT;
			lunchTime = lunchT;
			endTime = endT;
			this.title = title;

			if (title == "bankTeller") {
				setWorkerRole(new BankTellerRole(name, myself));
				roles.add(getWorkerRole());
			}
			if (title == "loanOfficer") {
				setWorkerRole(new LoanOfficerRole(name, myself));
				roles.add(getWorkerRole());
			}
			if (title == "bankGuard") {
				setWorkerRole(new BankGuardRole(name, myself));
				roles.add(getWorkerRole());
			}
			if (title == "marketRunner") {
				//workerRole = new Role(marketRunnerRole));
				roles.add(getWorkerRole());
			}
			if (title == "marketSales") {
				setWorkerRole(new SalesPersonRole(myself));
				roles.add(getWorkerRole());
			}
			if (title == "UPSman") {
				setWorkerRole(new UPSmanRole());
				roles.add(getWorkerRole());
			}
			if (title == "maintenance") {
				//	workerRole = new Role(maintenanceRole));
				roles.add(getWorkerRole());
			}
			if (title == "cashier") {
				setWorkerRole(new CashierRole(title));
				roles.add(getWorkerRole());
			}
			if (title == "host") {
				setWorkerRole(new HostRole(title));
				roles.add(getWorkerRole());
			}
			if (title == "cook") {
				setWorkerRole(new CookRole(title));		//need to input name not title
				roles.add(getWorkerRole());
			}
			if (title == "waiter") {
				setWorkerRole(new WaiterRole(title));
				roles.add(getWorkerRole());
			}
		}

		int getStartTime() {
			return endTime;
		}

		void setStartTime(int t) {
			endTime = t;
		}

		int getLunchTime() {
			return endTime;
		}

		void setLunchTime(int t) {
			endTime = t;
		}

		int getEndTime() {
			return endTime;
		}

		void setEndTime(int t) {
			endTime = t;
		}

		int getBankTime() {
			return bankTime;
		}
	}


	//Messages
	void msgHereIsPayCheck (double amount) {
		money += amount;
	}

	//Actions

	public void updateTime(int newTime) {
		if ((newTime == myJob.getBankTime()) && (money >= moneyMaxThreshold) || (money <= moneyMinThreshold)) {			
			for (Role role1: roles) {
				if (role1 instanceof BankCustomerRole) {
					this.newTime = -5;
					Do("Becoming customer");
					role1.setState(roleState.waitingToExecute);
					stateChanged();
					return;
				}
			}
		}
		if (newTime == myJob.startTime) {
			workerRole.setState(roleState.waitingToExecute);
			Do("Starting Job");
			stateChanged();
		}
		if (newTime == myJob.lunchTime) {
			//	workerRole.msgLunchTime();
			stateChanged();
		}

		if (newTime == myJob.lunchTime + myJob.lunchTime) {
			//	workerRole.msgBackToWork();
			stateChanged();
		}

		if (newTime == marketTime && (!hasFoodInFridge || money > carCost)) {	//everyone with more than 1000 dollars wants to buy a car
			for (Role role1: roles) {
				if (role1 instanceof MarketCustomerRole) {
					role1.setState(roleState.waitingToExecute);
					stateChanged();
					return;
				}
			}

		}

		if (newTime == myJob.endTime) {
			//	workerRole.msgShiftOver();
			//	roles.maintenance.msgShiftOver;
			stateChanged();
		} 

		if (newTime == sleepTime) {
			//GoToSleep();
			stateChanged();
		}  

		this.newTime = -5;
	}
}
