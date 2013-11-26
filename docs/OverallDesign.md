# List of Roles
 + All of the following roles extend from the base class Role

## Bank
* BankCustomer
* Teller
* LoanOfficer
* Guard

## Restaurant
* RestaurantCustomer
* Host
* Waiter
* Cook
* Cashier

## Market
* SalesPerson
* MarketRunner
* UPSMan
* MarketCustomer

## Home Management
* Renter
* Landlord
* Maintenance

## Transportation
* Passenger
* LeadPassenger


Notes:

--Have a control panel with a list of all people and what they are doing and make it possible to control their actions
	-show money, hungry, position, role, etc.
	-can give money, take it away, want a car

--When you add person, you can specify work start and end time

--Need a file with the information (number of people with each role) for a basic scenario

-Will have to to implement a bus rider agent, or going to car GUI step

-If we have multi-step actions and don't want them, we will either create new roles or separate actions and use state checking 

-Right now, we don't account for priority of actions that are waiting to execute. If we wanted to, we would simply add the most important action to the front of the list in Roles. Because the scheduler just iterates through the list of roles, the first action with waiting to execute state would fire first.

-People will have a "phone book" type class which has information of the agents which begin interactions
(i.e. reference to hostAgents, bankGuards) 

Sim City Design (Application, Person, Person Types)
 
Base Classes:
 + Agent
 + Person (Inherits from Agent)

Inheriting Classes from Person:
 + Worker
 + Crook
 + Deadbeat
 + Wealthy

Buildings
 + 1 apartment complex
 + 1 mansion
 + 1 park
 + 5 restaurants
 + 1 bank
 + 1 market
 + Streets

Times
 + Waking Up (Depends on Person)
 + Going to Sleep (22)
 + Bank (8) except for bank workers
 + Market (After work for workers)

Job Time: (Start, Lunch, End)
 + Bank Teller (8, 12, 16)
 + Loan Officer (8, 12, 16)
 + Bank Guard (8, 12, 16)
 + Market Runner (11, 13, 19)
 + Market Sales (11, 13, 19)
 + UPS Man (11, 13, 19)
 + Cook (10, ??, 17)
 + Waiter (10, ??, 17)
 + Host (10, ??, 17)
 + Cashier(10, ??, 17)
 + Maintenance (8,14, 17)





###Application Class

<pre><code>
class Application {
int time;
HashMap <Int, String> weekday 
{(0, Sunday), (1,Monday), (2,Tuesday), (3,Wednesday), (4, Thursday), (5, Friday), (6, Saturday)}

int dayNumber;
string dayOfWeek;

	scheduler {
		everyHour msg all agents msgNewTime(time);

if (time == 24 && dayNumber less than 6) {
	time = 0;
	dayNumber ++;
	dayOfWeek = weekday.get(dayNumber);
}

if (time == 24 && dayNumber == 6) {
	time = 0;
	dayNumber = 0;
	dayOfWeek = weekday.get(dayNumber);
}

if (time == 0 && dayNumber == 0) 
for all deadbeats msgWelfareCheck();
if (time == 17 && dayNumber == 4)
	for all wealthy msgCollectRentTime();
if (time == 8 && dayNumber == 2) 	
	for all workers msgHereIsPayCheck(200);
	}
}

</code></pre>



###Person Class

<pre><code>
Person extends Agent{
	//Data
	List<Role> roles; 	//contains all the customer roles
	double money;
        int newTime;
	HashMap<String, int> Inventory; 		//market list
	boolean hasCar;
	int accountNum;
	double accountBalance;
	boolean hasFoodInFridge;
	int sleepTime = 22;

	//Messages
	msgNewTime(int time) {
            newTime = time;        //application sends message to person and gives them the time
	}

	//Scheduler
pickAndExecuteAnAction {
if (newTime >= 0)         //this is our check that time has been updated. At the end of updateTime, newTime is 
decisionTime(newTime);   //set to a negative value. If application messages a new time, the value is positive.     
if (exists a role in role such that r. is active)
then r.pickAndExecuteAnAction();
if (exists a role such that r. is watingToExecute) {
then SetRoleActive(role);	
return true;
}
if (no role is active) {
	GoHome();
	return false;
}
}
	
	//Actions
	SetRoleActive(role) {
		role.state = active;
stateChanged();	
	}	
}

//Actions

abstract decisionTime(int time);    //implemented in all of the person subclasses
</code></pre>




###Worker Class

<pre><code>
class Worker extends Person {
	//Data
Role workerRole;
	Job myJob = null;
	int marketTime = Job.endTime;
int bankTime = 8;
int moneyMinThreshold = 20;
int moneyMaxThreshold = 200;
	
Worker (String name, String jobTitle) {
super();
this.name = name;		
myJob = new Job(jobTitle);
}
	
class Job {
		string title;
int lunchBreak = 1;	
		int wage;
		point workLocation;		//point has xCoor,yCoor
		int startTime, lunchTime, endTime;

		Job(String title, int startT, int lunchT, int endT) {
			this.title = title;
                        startTime = startT;
                        lunchTime = lunchT;
                        endTime = endT;

			if (title == “bankTeller”) {
				workerRole = new Role(bankTellerRole));
				roles.add(workerRole);
			}
			if (title == “loanOfficer”) {
				workerRole = new Role(loanOfficerRole));
				roles.add(workerRole);
			}
			if (title == “guard”) {
				workerRole = new Role(guardRole));
				roles.add(workerRole);
			}
			if (title == “marketRunner”) {
				workerRole = new Role(marketRunnerRole));
				roles.add(workerRole);
			}
			if (title == “marketSales”) {
				workerRole = new Role(marketSalesRole));
				roles.add(workerRole);
			}
			if (title == “UPSman”) {
				workerRole = new Role(UPSmanRole));
				roles.add(workerRole);
			}
                        if (title == “maintenance”) {
				workerRole = new Role(maintenanceRole));
				roles.add(workerRole);
			}
                        if (title == “cashier”) {
				workerRole = new Role(cashierRole));
				roles.add(workerRole);
			}
                        if (title == “host”) {
				workerRole = new Role(hostRole));
				roles.add(workerRole);
			}
                        if (title == “cook”) {
				workerRole = new Role(cookRole));
				roles.add(workerRole);
			}
                        if (title == “waiter”) {
				workerRole = new Role(waiterRole));
				roles.add(workerRole);
			}
                }
	}


//Messages
msgHereIsPayCheck (double amount) {
	money += amount;
}

	
//Actions

decisionTime() {
   If (time == bankTime && (money < minMoneyThreshold || money > maxMoneyThreshold)) 
		bankCustomerRole.state = waitingToExecute; 	
   If (time == myJob.startTime) 
		workerRole.state = waitingToExecute; 
   If (time = myJob.lunchTime) 
                workerRole.msgLunchTime(); 
   If (time = myJob.lunchTime + myJob.lunchBreak) 
		workerRole.msgBackToWork(); 
   If (time = marketTime && !hasFoodInFridge)
		MarketCustomerRole.state = waitingToExecute;
   If (time = myJob.endTime) {
		workerRole.msgShiftOver;
		roles.maintenance.msgShiftOver;
   }
   If (time == sleepTime)
	GoToSleep();
  } 
}	
</code></pre>





###Crook Class

<pre><code>
class Crook extends Person {
	//note: these people become dishonest customers in the restaurant
	
	int time2RobDaBank = 8;
	int eatTime = 12;
	int marketTime = 15;
	
//Messages
	msgNewTime (int time) {
if (time = time2RobDaBank) 
	RobberRole.state = waitingToExecute;
		if (time = eatTime)
			RestaurantCustomer.state = waitingToExecute;
		if (time = marketTime && !hasFoodInFridge)
			MarketCustomer.state = waitingToExecute;
		}

		if (time = sleepTime)
GoToSleep();
	}
}
</code></pre>



###Deadbeat Class
<pre><code>
class Deadbeat extends Person {
	int wanderTime = 8;
	int parkTime = 20;

//Messages
	msgNewTime (int time) {
		if (time % wanderTime == 0)
			Randomly wander between Restaurant, Bank, and Market
		if (time = parkTime)
			GoToPark();
		}	

if (time == sleepTime)
	GoToSleep();
	
	}

msgWelfareCheck {
	money += 50;
}
}
</code></pre>



###Wealthy Class

<pre><code>
class Wealthy extends Person {
	int eatTime1 = 10;
	int eatTime2 = 16;
	int bankTime = 10;
	boolean needToDeposit;

//Messages
msgRentDue () {
	roles.landlord.waitingToExecute;
}

	msgNewTime (int time) {
if (time == eatTime1)
	then activate roles.restaurantCustomer;
if (time == eatTime2)
	then activate roles.restaurantCustomer;
if (time == sleepTime)
	GoToSleep();
	}
}
</code></pre>

###Role Class

<pre><code>
public abstract class Role {

	Person person;
	
	enum roleState {active, inActive, waitingToExecute}
	roleState state = roleState.inActive;
	
	Role(Person person) {
		this.person = person;
	}
	
	protected void stateChanged() {
        //calls Person scheduler
		person.pickAndExecuteAnAction();
    }

    protected abstract boolean pickAndExecuteAnAction();
    
    protected void inactivateRole() {
    	state = roleState.inActive;
    	stateChanged();
    }
}
</code></pre>