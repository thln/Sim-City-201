package person;

import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;

import bank.BankTellerRole;
import person.Role.RoleState;
import application.Phonebook;
import application.TimeManager;
import application.TimeManager.Day;
import application.WatchTime;

public class Worker extends Person {

	//Data
	protected Job myJob = null;
	protected Role workerRole = null;

	public Worker (String name, int money, String jobTitle, String jobPlace, int startT, int lunchT, int endT) {
		super(name);
		this.money = money;
		myJob = new Job(jobTitle, jobPlace ,startT, lunchT, endT, this);
		nextTask = new Timer();
	}

	private Timer nextTask;
	boolean upcomingTask = false;

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
		}

		WatchTime getStartTime() {
			return startTime;
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

	public void roleFinishedWork(){                 //from worker role
		print("Finished working");
		workerRole = null;
		stateChanged();
	}

	//Scheduler
	public boolean pickAndExecuteAnAction() {


		//Problem: if you are in a worker role, and that is not receiving any messages, it will never run again to check if it's time to leave work
		//                if (hunger == HungerLevel.full) {
		//                        startHungerTimer();
		//                        return true;
		//                }

		//Decisions more urgent that role continuity (None for now)
		if (!upcomingTask) {
			if (((myJob.getEndTime().hour - TimeManager.getTimeManager().getTime().dayHour) > 0)) 
				scheduleNextTask(myJob.getEndTime().hour);
		}

		if (workerRole != null){
			if (workerRole.getState() == RoleState.active) {
				if (((myJob.getEndTime().hour - TimeManager.getTimeManager().getTime().dayHour) <= 0) && !workerRole.leaveRole) {
					print("Shift is over, time to leave work");
					workerRole.msgLeaveRole(); 
					return true;
				}
				return workerRole.pickAndExecuteAnAction();                      
			}
		}

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
		if ((myJob.getStartTime().hour - TimeManager.getTimeManager().getTime().dayHour) <= 1 && 
				(myJob.getStartTime().hour - TimeManager.getTimeManager().getTime().dayHour) >= 0) {


			//print("Job time = " + myJob.getStartTime().hour + " Current time = " + TimeManager.getTimeManager().getTime().dayHour);
			prepareForWork();
			return true;
		}

		//Bank Related
		if (money <= moneyMinThreshold || money >= moneyMaxThreshold) {
			prepareForBank();
			return true;
		}

		//Rent Related
		if (TimeManager.getTimeManager().getTime().day == Day.Monday) {
			resetRentMailbox();
		}
		if (TimeManager.getTimeManager().getTime().day == Day.Sunday && !checkedMailbox) {
			prepareForRent();
			return true;
		}

		//Hunger Related
		if (hunger == HungerLevel.hungry) {
			//If you don't have food in the fridge
			if (!hasFoodInFridge) {
				//if ((TimeManager.getTimeManager().getTime().dayHour >= Phonebook.getPhonebook().getRestaurant().openTime.hour) &&
				//(TimeManager.getTimeManager().getTime().dayHour < Phonebook.getPhonebook().getRestaurant().closeTime.hour)) {
				prepareForRestaurant();
				//
				return true;

			}
			else //if you do have food in the fridge
			{
				eatAtHome(); //empty method for now...
				return true;
			}
		}


		//Market Related
		if (!hasFoodInFridge || carStatus == CarState.wantsCar) 
		{ 
			{
				if ((TimeManager.getTimeManager().getTime().dayHour >= Phonebook.getPhonebook().getMarket().openTime.hour) &&
						(TimeManager.getTimeManager().getTime().dayHour < Phonebook.getPhonebook().getMarket().closeTime.hour)) 
				{
					prepareForMarket();
					return true;
				}
			}
		}

		goToSleep();
		return false;
	}


	//Actions
	private void scheduleNextTask(int nextTaskTime) {
		upcomingTask = true;
		nextTask.schedule(new TimerTask() {
			public void run() {
				upcomingTask = false;
				stateChanged();
			}
		},
		(nextTaskTime - TimeManager.getTimeManager().getTime().dayHour));
	}

	public void prepareForWork() {

		currentRoleName = myJob.title;
		print("Preparing for work as " + myJob.title);
		if (myJob.jobPlace == "bank") 
		{
			gui.DoGoToBank();
			try {
				atDestination.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
			workerRole = Phonebook.getPhonebook().getBank().arrivedAtWork(this, myJob.title);
			workerRole.setRoleActive();
			return;
		}

		if (myJob.jobPlace == "market") 
		{
			gui.DoGoToMarket();
			try {
				atDestination.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
			workerRole = Phonebook.getPhonebook().getMarket().arrivedAtWork(this, myJob.title);
			workerRole.setRoleActive();
			return;
		}

		if (myJob.jobPlace == "restaurant") 
		{
			gui.DoGoToRestaurant();
			try {
				atDestination.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
			workerRole = Phonebook.getPhonebook().getRestaurant().arrivedAtWork(this, myJob.title);
			workerRole.setRoleActive();
			return;
		}
		if (myJob.jobPlace == "housing maintenance company")
		{
			workerRole = Phonebook.getPhonebook().getHousingMaintenanceCompany().arrivedAtWork(this, myJob.title);
			workerRole.setRoleActive();
			return;
		}
		//need to put in maintenance role

		return;
	}

	public void setWorkerRole(Role workerRole) 
	{
		this.workerRole = workerRole;
	}

	public Role getWorkerRole() 
	{
		return workerRole;
	}

	public Job getJob() 
	{
		return myJob;
	}
}