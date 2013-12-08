package person;

import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;

import bank.BankTellerRole;
import person.Role.RoleState;
import application.Phonebook;
import application.TimeManager;
import application.TimeManager.Day;
import application.gui.trace.AlertLog;
import application.WatchTime;

public class Worker extends Person {

	boolean shift = false;
	//Data
	protected Job myJob = null;
	protected Role workerRole = null;
	public boolean lateWorker;

	public Worker (String name, double money, String jobTitle, String jobPlace, int startT, int lunchT, int endT) {
		super(name, money);
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
		}

		public WatchTime getStartTime() {
			return startTime;
		}

		public void setStartTime(int t) {
			startTime.setTime(t, 0);
		}

		public WatchTime getLunchTime() {
			return lunchTime;
		}

		public void setLunchTime(int t) {
			lunchTime.setTime(t, 0);
		}

		public WatchTime getEndTime() {
			return endTime;
		}

		void setEndTime(int t) {
			endTime.setTime(t, 0);
		}

		void setTitle(String title) {
			this.title = title;
		}

		public String getTitle(){
			return title;
		}
		public String getJobLoc(){
			return jobPlace;
		}
	}


	//Messages
	void msgHereIsPayCheck (double amount) {
		money += amount;
	}

	public synchronized void roleFinishedWork(){                 //from worker role
		print("Shift is over, time to leave work");
		//workerRole.setPerson(null);
		workerRole = null;
		//	scheduleNextTask(TimeManager.getTimeManager().getTime().dayHour, myJob.startTime.hour);
		stateChanged();
	}



	//Scheduler
	public boolean pickAndExecuteAnAction() {

		//Run your worker role
		if (workerRole != null){
			int currentTime = TimeManager.getTimeManager().getTime().dayHour;
			int shiftLength = (((myJob.endTime.hour - myJob.startTime.hour) % 24) + 24) % 24;
			if (workerRole.getState() == RoleState.active) {
				//If my current time is more than the shift length since start time
				//Check to make sure I've started working (fix modulus logic later)
				if ((((((currentTime - myJob.startTime.hour) % 24) + 24) % 24) >= shiftLength)
						&& (((((currentTime - myJob.startTime.hour) % 24) + 24) % 24) > 0)
						&& ((((currentTime - myJob.startTime.hour) % 24) + 24) % 24) < 18){
					//print("Off work at time = " + currentTime + " and shift length = " + shiftLength + " startTime = " + myJob.startTime.hour);
					workerRole.msgLeaveRole();
					return workerRole.pickAndExecuteAnAction();
				}					
				else
					return workerRole.pickAndExecuteAnAction();                      
			}
		}

		//Run your customer role
		synchronized (roles) {
			if (!roles.isEmpty()) {                                
				for (Role r : roles) {
					if (r.getState() == RoleState.active) {
						return r.pickAndExecuteAnAction();
					}
				}
			}
		}

		int currentTime = TimeManager.getTimeManager().getTime().dayHour;

		//make sure user has not closed your business
		if (workIsOpen()) {	

			//If no role is active, check if it's time for work
			if (((((myJob.startTime.hour - currentTime) % 24) + 24) % 24) <= 1) {
				prepareForWork();
				//	scheduleNextTask(currentTime, myJob.endTime.hour);
				return true;
			}

			//Check if you are late for work	
			int timePassed = ((((currentTime - myJob.startTime.hour) % 24) + 24) % 24);
			int maxHoursLate = 4;	//if you are more than 4 hours late, don't bother going to work
			if ((timePassed >= 0) && (timePassed < maxHoursLate)){
				prepareForWork();
				//	scheduleNextTask(currentTime, myJob.endTime.hour);
				return true;
			} 
		}

		//Bank Related (check if you need to go to the bank)
		//Check if there is a bank open before you go
		if (Phonebook.getPhonebook().getEastBank().isOpen()){ // || Phonebook.getPhonebook().getEastBank().isOpen()){	
			if (money <= moneyMinThreshold || money >= moneyMaxThreshold) 
			{
				prepareForBank();
				return true;
			}
		}

		//Rent Related (check if you need to pay rent)
		if (TimeManager.getTimeManager().getTime().day == Day.Monday) {
			resetRentMailbox();
			return true;
		}
		if (TimeManager.getTimeManager().getTime().day == Day.Sunday && !checkedMailbox) {
			prepareForRent();
			return true;
		}

		//Hunger Related ( check if you are hungry)
		if (getHunger() == HungerLevel.hungry) {
			//If you don't have food in the fridge
			if (!hasFoodInFridge) {
				//Check if any restaurants are open
				if (Phonebook.getPhonebook().getChineseRestaurant().isOpen()) {	
					prepareForRestaurant();
				}
				//
				return true;

			}
			else //if you do have food in the fridge
			{
				eatAtHome(); //empty method for now...
				return true;
			}
		}


		//Market Related (check if you need to go to the market)
		if (!hasFoodInFridge || carStatus == CarState.wantsCar) 
		{ 
			if (Phonebook.getPhonebook().getEastMarket().isOpen()) // || Phonebook.getPhonebook().getEastMarket().isOpen())
			{
				print("Going to market");
				prepareForMarket();
				return true;
			}
		}

		goToSleep();
		return false;
	}


	//Actions
	private void scheduleNextTask(int currentTime, int nextTaskTime) {
		int timeConversion = 60 * TimeManager.getSpeedOfTime();
		//print("Next task time = " + nextTaskTime + " Current time = " + currentTime);
		print("Timer length = ms" + ((((nextTaskTime - currentTime) % 24) + 24) % 24) * timeConversion);
		nextTask.schedule(new TimerTask() {
			public void run() {        
				stateChanged();                
			}
		},
		//((i % 5) + 5) % 5)
		((((nextTaskTime - currentTime) % 24) + 24) % 24) * timeConversion);
	}

	public void prepareForWork() {
		currentRoleName = myJob.title;
		print("Preparing for work as " + myJob.title);
		if (myJob.jobPlace.equals("bank")) 
		{
			getGui().DoGoToBank("east");
			try {	
				atDestination.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			workerRole = Phonebook.getPhonebook().getEastBank().arrivedAtWork(this, myJob.title);
			workerRole.setRoleActive();
			return;
		}

		if (myJob.jobPlace == "market") 
		{
			//print("Going to work at market");
			getGui().DoGoToMarket("east");
			try {
				atDestination.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
			print("Going to work at market, job time = " + myJob.startTime.hour);
			workerRole = Phonebook.getPhonebook().getEastMarket().arrivedAtWork(this, myJob.title);
			workerRole.setRoleActive();
			return;
		}

		if (myJob.jobPlace == "restaurant") 
		{
			getGui().DoGoToRestaurant("italian");
			try {
				atDestination.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
			workerRole = Phonebook.getPhonebook().getChineseRestaurant().arrivedAtWork(this, myJob.title);
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

	public boolean workIsOpen() {
		if (myJob.jobPlace.equals("bank")) {
			if (!Phonebook.getPhonebook().getEastBank().userClosed)
				return true;
			else
				return false;
		}

		if (myJob.jobPlace == "market") {
			if (!Phonebook.getPhonebook().getEastMarket().userClosed)
				return true;
			else
				return false;
		}

		if (myJob.jobPlace == "restaurant") {
			if (!Phonebook.getPhonebook().getChineseRestaurant().userClosed)
				return true;
			else
				return false;
		}
		return true;
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