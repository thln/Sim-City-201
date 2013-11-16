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
import bank.BankCustomer;
import bank.BankGuard;
import bank.BankTeller;
import bank.LoanOfficer;

public class Worker extends Person {
	//Data
	String name;
	Job myJob = null;
	int marketTime;
	int bankTime = 8;
	int sleepTime = 22;
	
	LoanOfficer l1;

	public Worker (String name, int money, String jobTitle, int startT, int lunchT, int endT) {
		super();
		this.money = money;
		this.name = name;       
		myJob = new Job(jobTitle, startT, lunchT, endT, this);
		marketTime = myJob.getEndTime();
			
			if (name == "Bill") {
				 Worker worker2 = new Worker("Ted", 10, "loanOfficer", 2, 0, 0);      
			        l1 = (LoanOfficer) worker2.workerRole;
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
				workerRole = new BankTeller(name, myself, l1);
				roles.add(workerRole);
			}
			if (title == "loanOfficer") {
				workerRole = new LoanOfficer(name, myself);
				roles.add(workerRole);
			}
			if (title == "bankGuard") {
				workerRole = new BankGuard(name, myself);
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
				if (role1 instanceof MarketCustomer) {
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
