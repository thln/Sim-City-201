package person;

import java.awt.Point;

import person.Role.roleState;
import restaurant.Cashier;
import restaurant.Cook;
import restaurant.Host;
import restaurant.Waiter;
import market.MarketCustomer;
import market.SalesPerson;
import market.UPSman;
import bank.BankGuard;
import bank.BankTeller;
import bank.LoanOfficer;

public class Worker extends Person {
	//Data
	Role workerRole;
	Job myJob = null;
	int marketTime = myJob.getEndTime();
	int bankTime = 8;
	int sleepTime = 22;
	int moneyMinThreshold = 20;
	int moneyMaxThreshold = 200;

	Worker (String name, String jobTitle, int startT, int lunchT, int endT) {
		super();
		this.name = name;       
		myJob = new Job(jobTitle, startT, lunchT, endT, this);
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
				workerRole = new BankTeller();
				roles.add(workerRole);
			}
			if (title == "loanOfficer") {
				workerRole = new LoanOfficer();
				roles.add(workerRole);
			}
			if (title == "guard") {
				workerRole = new BankGuard();
				roles.add(workerRole);
			}
			if (title == "marketRunner") {
				//workerRole = new Role(marketRunnerRole));
				roles.add(workerRole);
			}
			if (title == "marketSales") {
				workerRole = new SalesPerson(myself);
				roles.add(workerRole);
			}
			if (title == "UPSman") {
				workerRole = new UPSman();
				roles.add(workerRole);
			}
			if (title == "maintenance") {
			//	workerRole = new Role(maintenanceRole));
				roles.add(workerRole);
			}
			if (title == "cashier") {
				workerRole = new Cashier(title);
				roles.add(workerRole);
			}
			if (title == "host") {
				workerRole = new Host(title);
				roles.add(workerRole);
			}
			if (title == "cook") {
				workerRole = new Cook(title);		//need to input name not title
				roles.add(workerRole);
			}
			if (title == "waiter") {
				workerRole = new Waiter(title);
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
		if ((newTime == myJob.getBankTime()) && (money >= moneyMaxThreshold)) {
			//	BankCustomer.state = roleState.waitingToExecute;      
		}
		if (newTime == myJob.startTime) {
			workerRole.setState(roleState.waitingToExecute);
		}
		if (newTime == myJob.lunchTime) {
			//	workerRole.msgLunchTime();
		}

		if (newTime == myJob.lunchTime + myJob.lunchTime) {
			//	workerRole.msgBackToWork();
		}

		if (newTime == marketTime && !hasFoodInFridge) {
		//	for (MarketCustomer role1: roles)
				//role1.setState(roleState.waitingToExecute);
		}

		if (newTime == myJob.endTime) {
			//	workerRole.msgShiftOver();
			//	roles.maintenance.msgShiftOver;
		} 

		if (newTime == sleepTime) {
			//GoToSleep();
		}  
		
		newTime = -30;
	}
}
