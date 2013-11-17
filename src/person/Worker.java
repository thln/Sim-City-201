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
import bank.BankCustomer;
import bank.BankGuardRole;
import bank.BankTellerRole;
import bank.LoanOfficerRole;

public class Worker extends Person {
	//Data
	Job myJob = null;
	int marketTime;
	int bankTime = 8;
	int sleepTime = 22;

	LoanOfficerRole l1;

	public Worker (String name, int money, String jobTitle, int startT, int lunchT, int endT)  {
		super(name);
		this.money = money;       
		myJob = new Job(jobTitle, startT, lunchT, endT, this);
		marketTime = myJob.getEndTime();

		if (name == "Bill") {
			Worker worker2 = new Worker("Ted", 10, "loanOfficer", 2, 0, 0);      
			l1 = (LoanOfficerRole) worker2.workerRole;
			worker2.startThread();
			worker2.msgNewTime(2);
		}
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
				workerRole = new BankTellerRole(name, myself, l1);
				roles.add(workerRole);
			}
			if (title == "loanOfficer") {
				workerRole = new LoanOfficerRole(name, myself);
				roles.add(workerRole);
			}
			if (title == "bankGuard") {
				workerRole = new BankGuardRole(name, myself);
				roles.add(workerRole);
			}
			if (title == "marketRunner") {
				//workerRole = new Role(marketRunnerRole));
				roles.add(workerRole);
			}
			if (title == "marketSales") {
				workerRole = new SalesPersonRole(myself);
				roles.add(workerRole);
			}
			if (title == "UPSman") {
				workerRole = new UPSmanRole();
				roles.add(workerRole);
			}
			if (title == "maintenance") {
				//	workerRole = new Role(maintenanceRole));
				roles.add(workerRole);
			}
			if (title == "cashier") {
				workerRole = new CashierRole(title);
				roles.add(workerRole);
			}
			if (title == "host") {
				workerRole = new HostRole(title);
				roles.add(workerRole);
			}
			if (title == "cook") {
				workerRole = new CookRole(title);		//need to input name not title
				roles.add(workerRole);
			}
			if (title == "waiter") {
				workerRole = new WaiterRole(title);
				roles.add(workerRole);
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
			System.out.println("Becoming customer");
			for (Role role1: roles) {
				if (role1 instanceof BankCustomer) {
					role1.setState(roleState.waitingToExecute);
					stateChanged();
					return;
				}
			}
		}
		if (newTime == myJob.startTime) {
			workerRole.setState(roleState.waitingToExecute);
			System.out.println("Starting Job");
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

		newTime = -5;
	}
}
