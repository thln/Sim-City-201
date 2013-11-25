package person;

import java.awt.Point;

import bank.BankTellerRole;
import person.Role.RoleState;
import application.Phonebook;
import application.TimeManager;
import application.WatchTime;

public class Worker extends Person {

        //Data
        protected Job myJob = null;
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
                workerRole = null;
                stateChanged();
        }

        //Scheduler
        public boolean pickAndExecuteAnAction() {

                if (hunger == HungerLevel.full) {
                        startHungerTimer();
                        return true;
                }

                //Decisions more urgent that role continuity (None for now)
                
                if (workerRole != null){
                        if (workerRole.getState() == RoleState.active) {
                                if (((myJob.getEndTime().hour - TimeManager.getTimeManager().getTime().dayHour) <= 0)) 
                                        workerRole.msgLeaveRole(); 
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
                if ((myJob.getStartTime().hour - TimeManager.getTimeManager().getTime().dayHour) <= 1) {
                        prepareForWork();
                        return true;
                }

                //Hunger Related
                if (hunger == HungerLevel.hungry) {
                        //If you don't have food in the fridge
                        if (!hasFoodInFridge) {
                                if (money <= moneyMinThreshold) { 
                                        //This if says go to the business if it is open and at least 1 hour before closing time
                                       // if ((TimeManager.getTimeManager().getTime().dayHour >= Phonebook.getPhonebook().getBank().openTime.hour) &&
                                                     //   (TimeManager.getTimeManager().getTime().dayHour < Phonebook.getPhonebook().getBank().closeTime.hour)) {
                                                prepareForBank();
                                                return true;
                                       // }
                                }
                                else { //if ((TimeManager.getTimeManager().getTime().dayHour >= Phonebook.getPhonebook().getRestaurant().openTime.hour) &&
                                                //(TimeManager.getTimeManager().getTime().dayHour < Phonebook.getPhonebook().getRestaurant().closeTime.hour)) {
                                        prepareForRestaurant();
                                        //
                                        return true;
                                }
                        }
                        else //if you do have food in the fridge
                        {
                                eatAtHome(); //empty method for now...
                                return true;
                        }
                }

                //Bank Related
                if (money <= moneyMinThreshold || money >= moneyMaxThreshold) 
                {
                        prepareForBank();
                        return true;
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
        //Actions
        public void prepareForWork() {

        	currentRoleName = myJob.title;
        	print("Preparing for work as " + myJob.title);
                if (myJob.jobPlace == "bank") 
                {
                        workerRole = Phonebook.getPhonebook().getBank().arrivedAtWork(this, myJob.title);
                        workerRole.setRoleActive();
                        return;
                }

                if (myJob.jobPlace == "market") 
                {
                        workerRole = Phonebook.getPhonebook().getMarket().arrivedAtWork(this, myJob.title);
                        workerRole.setRoleActive();
                        return;
                }

                if (myJob.jobPlace == "restaurant") 
                {
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