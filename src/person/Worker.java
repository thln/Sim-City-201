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

	boolean shift = false;
	//Data
	protected Job myJob = null;
	protected Role workerRole = null;
	enum WorkState {atWork, prepareForWork, notAtWork}
	WorkState workState;
	public boolean lateWorker;

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
			if (endT <= 8)
				lateWorker = true;
			else
				lateWorker = false;

			if (lateWorker){
				workState = WorkState.notAtWork;
				upcomingTask = false;
			}

			if (!lateWorker){
				upcomingTask = false;
				workState = WorkState.prepareForWork;
			}
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

	 public synchronized void roleFinishedWork(){                 //from worker role
         print("Shift is over, time to leave work");
         //        print("Job time = " + myJob.getStartTime().hour + " Current time = " + TimeManager.getTimeManager().getTime().dayHour);
         workState = WorkState.notAtWork;       
         workerRole.setPerson(null);
         workerRole = null;
         upcomingTask = false;
         stateChanged();
 }



	//Scheduler
	public boolean pickAndExecuteAnAction() {

		if (!upcomingTask) {
			int currentTime = TimeManager.getTimeManager().getTime().dayHour;
			if (lateWorker){
				//Start timer to wake up for work
				if (workState == WorkState.notAtWork) {
				//	print("YZ");
					scheduleNextTask(myJob.getStartTime().hour, currentTime);
					return true;
				}
				//Start timer to wake up for ending work
				if (workState == WorkState.atWork){
					scheduleNextTask(myJob.getEndTime().hour + 24, currentTime);
					return true;
				}
			}

			if (!lateWorker){
				//Start timer to wake up for work
				if (workState == WorkState.notAtWork) {
					if (currentTime < 8){
						scheduleNextTask(myJob.getStartTime().hour, currentTime);
						return true;
					}
					else {
					//	print("early workers");
						scheduleNextTask(myJob.getStartTime().hour + 24, currentTime);
						return true;
					}
				}
				//Start timer to wake up for ending work
				if (workState == WorkState.atWork){
					scheduleNextTask(myJob.getEndTime().hour, currentTime);
					return true;
				}
			}
		}

		if (workerRole != null){
			int currentTime = TimeManager.getTimeManager().getTime().dayHour;
			if (workerRole.getState() == RoleState.active) {
				if (lateWorker && (currentTime < 8) && (myJob.getEndTime().hour - currentTime <= 1)){
					workerRole.msgLeaveRole();
					return true; 
				}
				if (!lateWorker && (myJob.getEndTime().hour - currentTime <= 1)){
					workerRole.msgLeaveRole();
					return true; 
				}
				else
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

		//Job related
		int currentTime = TimeManager.getTimeManager().getTime().dayHour;
		if (workState == WorkState.prepareForWork) {
			if ((!lateWorker && currentTime <= 5 && (myJob.getStartTime().hour - currentTime) <= 1)
				|| (lateWorker && currentTime >= 5 && (myJob.getStartTime().hour - currentTime) <= 1)
				|| (!lateWorker && currentTime > 5 && (myJob.getStartTime().hour + 24 - currentTime) <= 1)
				|| (lateWorker && currentTime > 5 && (myJob.getStartTime().hour + 24 - currentTime) <= 1))
				{
					workState = workState.atWork;
					upcomingTask = false;
					prepareForWork();
					return true;
				}
		}

		//Bank Related
		if (money <= moneyMinThreshold || money >= moneyMaxThreshold) 
		{
			prepareForBank();
			return true;
		}

		//Rent Related
		if (TimeManager.getTimeManager().getTime().day == Day.Monday) {
			resetRentMailbox();
			return true;
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
					print("Going to market");
					prepareForMarket();
					return true;
				}
			}
		}

		goToSleep();
		return false;
	}


	//Actions
	private void scheduleNextTask(int nextTaskTime, int currentTime) {
		upcomingTask = true;
		int timeConversion = 1200;
		//print("Next task time = " + nextTaskTime + " Current time = " + currentTime);
		nextTask.schedule(new TimerTask() {
			public void run() {        
				//print("Time = " + TimeManager.getTimeManager().getTime().dayHour);
				if (workState == WorkState.notAtWork){
					workState = WorkState.prepareForWork;
					//print("Preparing for work");
				}
				pickAndExecuteAnAction();                
			}
		},
		((nextTaskTime - currentTime)) * timeConversion);
	}

	public void prepareForWork() {

		currentRoleName = myJob.title;
		print("Preparing for work as " + myJob.title);
		if (myJob.jobPlace == "bank") 
		{
			print("going to work at bank");
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