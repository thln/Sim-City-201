package person;

import java.awt.Point;

import restaurant.Cashier;
import restaurant.Cook;
import restaurant.Host;
import restaurant.Waiter;
import market.SalesPerson;
import bank.BankGuard;
import bank.BankTeller;
import bank.LoanOfficer;


public class Worker extends Person {
	//Data
	Role workerRole;
	Job myJob = null;
	int marketTime = myJob.getEndTime();
	int bankTime = 8;
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
				//workerRole = new UPSman;
				roles.add(workerRole);
			}
			if (title == "maintenance") {
				//workerRole = new Role(maintenanceRole));
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
	void msgNewTime(int time) {
		If ((time == myJob.getBankTime()) && 
				(money  maxMoneyThreshold))
				bankCustomerRole.state = waitingToExecute;      
		If (time == myJob.startTime) {
			workerRole.state = waitingToExecute;

			If (time = myJob.lunchTime) {
				workerRole.msgLunchTime();
			}

			If (time = myJob.lunchTime + myJob.lunchBreak) {
				workerRole.msgBackToWork();
			}

			If (time = marketTime && !hasFoodInFridge)
			MarketCustomerRole.state = waitingToExecute;
		}

		If (time = myJob.endTime) {
			workerRole.msgShiftOver;
			then roles.maintenance.msgShiftOver;
		} 
		if (time = sleepTime)
			GoToSleep();
	}   
}