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
import bank.Bank;
import bank.BankCustomerRole;
import bank.BankGuardRole;
import bank.BankTellerRole;
import bank.LoanOfficerRole;

public class Worker extends Person {
	//Data
	Job myJob = null;
	int marketTime;
	int bankTime = 900;
	int sleepTime = 2200;
	int moneyMinThreshold = 20;
	int moneyMaxThreshold = 200;

	public Worker (String name, int money, String jobTitle, int startT, int lunchT, int endT) {
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
				workerRole = new BankTellerRole(name, myself, title);
				roles.add(workerRole);
			}
			if (title == "loanOfficer") {
				workerRole = new LoanOfficerRole(name, myself, title);
				Phonebook.bank.loanOfficerRole = (LoanOfficerRole) workerRole;
				roles.add(workerRole);			
			}
			if (title == "bankGuard") {
				workerRole = new BankGuardRole(name, myself, title);
				Phonebook.bank.bankGuardRole = (BankGuardRole) workerRole;
				roles.add(workerRole);			
			}
			if (title == "marketRunner") {
				//	workerRole = new MarketRunnerRole(myself,title);
				roles.add(workerRole);
			}
			if (title == "marketSales") {
				workerRole = new SalesPersonRole(myself, name, title);
				roles.add(workerRole);
			}
			if (title == "UPSman") {
				workerRole = new UPSmanRole(myself, name, title);
				roles.add(workerRole);
			}
			if (title == "maintenance") {
				workerRole = new MaintenanceWorker(myself, name, title);
				roles.add(workerRole);
			}
			if (title == "cashier") {
				workerRole = new CashierRole(myself, name, title);
				roles.add(workerRole);
			}
			if (title == "host") {
				workerRole = new HostRole(myself, name, title);
				roles.add(workerRole);
			}
			if (title == "cook") {
				workerRole = new CookRole(myself, name, title);		//need to input name not title
				roles.add(workerRole);
			}
			if (title == "waiter") {
				workerRole = new WaiterRole(myself, name, title);
				roles.add(workerRole);
			}
			if (title == "altWaiter") {
				workerRole = new AltWaiterRole(myself, name, title);
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

			for (Role r: roles) {
				if (r instanceof BankCustomerRole) {
					r.setState(RoleState.waitingToExecute);

				}
			}
			stateChanged();
		}
		if (newTime == myJob.startTime) {
			workerRole.setState(RoleState.waitingToExecute);
			System.out.println("Starting Job " + workerRole.getRoleName());
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

		if (newTime == marketTime && !hasFoodInFridge) {
			//	for (MarketCustomer role1: roles)
			//role1.setState(roleState.waitingToExecute);
			stateChanged();
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
