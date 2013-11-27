![House Mailbox Diagram](https://raw.github.com/usc-csci201-fall2013/team20/014aeaa5a00d787532f65047f6429105c3027724/docs/InteractionDiagrams/HouseMailbox.png?token=3290221__eyJzY29wZSI6IlJhd0Jsb2I6dXNjLWNzY2kyMDEtZmFsbDIwMTMvdGVhbTIwLzAxNGFlYWE1YTAwZDc4NzUzMmY2NTA0N2Y2NDI5MTA1YzMwMjc3MjQvZG9jcy9JbnRlcmFjdGlvbkRpYWdyYW1zL0hvdXNlTWFpbGJveC5wbmciLCJleHBpcmVzIjoxMzg2MTAyNzQ0fQ%3D%3D--56571bf78745cc0708b4a4508574ed256a794c30)

![House Maintenance Diagram](https://raw.github.com/usc-csci201-fall2013/team20/014aeaa5a00d787532f65047f6429105c3027724/docs/InteractionDiagrams/HouseMaintenance.png?token=3290221__eyJzY29wZSI6IlJhd0Jsb2I6dXNjLWNzY2kyMDEtZmFsbDIwMTMvdGVhbTIwLzAxNGFlYWE1YTAwZDc4NzUzMmY2NTA0N2Y2NDI5MTA1YzMwMjc3MjQvZG9jcy9JbnRlcmFjdGlvbkRpYWdyYW1zL0hvdXNlTWFpbnRlbmFuY2UucG5nIiwiZXhwaXJlcyI6MTM4NjEwMjc3NH0%3D--949d144debe9aebac24b3e3eaf17492c9fceb843)

# Renter
### Data

<pre><code>
double rentAmount;
boolean rentDue = false;
double money;
</code></pre>

### Messages

<pre><code>
msgRentDue(double moneyOwed) {
	rentAmount = moneyOwed;
	rentDue = true;
}
</code></pre>

### Scheduler

<pre><code>
if rentDue = true
	then
		payRent();
</code></pre>

### Actions

<pre><code>
payRent() {
	landloard.msgRentPayment(this, rentAmount);
	rentDue = false;
	money -= rentAmount;
}
</code></pre>

# Landlord
### Data

<pre><code>
List <Property> properties;
double money;

class Property {
	Person tenant;
	point location;
	double rentAmount;
	enum rentState {rentDue, pending, paid};
	rentState state = rentState.paid;
};
</code></pre>

### Messages

<pre><code>
//From the application class
msgRentDue(){
	for ∀ Property p in properties
		p.state = rentState.rentDue;
}

msgRentPayment(Renter renter, double payment) {
	Property p = properties.findRenterProperty(renter);
	p.state = rentState.paid;
	money += payment;
}
</code></pre>

### Scheduler

<pre><code>
if there exists an Property p in properties &&
if p.state == rentDue
        	then
                    	askForRent(p);
</code></pre>

### Actions

<pre><code>
askForRent(Property p) {
	p.rent.msgRentDue(p.rentAmount);
	p.state = rentState.pending;
}
</code></pre>

#Mailbox

### Data

<pre><code>
	private int paymentCash;
	private int apartmentRentPrice = 50;
</pre></code>

### Actions

<pre><code>
//should I check how people pay rent? to keep them accountable?
//note for v2
dropRentMoney(int payment) {
	paymentCash += payment;
}
	
pickUpRentMoney(Wealthy landlord) {
	if(landlord is an instance of Wealthy {
		int withdraw = paymentCash;
		paymentCash = 0;
		return withdraw;
	}
	else{
		return 0;
	}
}
</code></pre>

# Maintenance
### Data

<pre><code>
String name;
Timer FixingTimer;
enum maintenanceState {Working, CheckingHouse, RefreshList};
maintenanceState state = maintenanceState.Working;
</code></pre>

### Messages

<pre><code>
msgNeedMaintenance(Housing houseNeedMain) {
	for (Housing h : Phonebook.getPhonebook().getAllHousing(test)) {
		if (h == houseNeedMain) 
			h.state = housingState.UrgentWorkOrder;
	}
}

msgRefreshHousingList() {
	if(!Phonebook.getPhonebook().getAllHousing(test).isEmpty()) {
		state = maintenanceState.RefreshList;
	}
}
</code></pre>

### Scheduler

<pre><code>
If(state == maintenanceState.RefreshList)
	resetHousingCheck();
If((state == maintenanceState.Working)
	for (Housing h : Phonebook.getPhonebook().getAllHousing(test)) {
	if (h.state == housingState.UrgentWorkOrder)
		checkHousing(h);
	}
	for (Housing h : Phonebook.getPhonebook().getAllHousing(test)) {
	if (h.state == housingState.CheckUpNeeded)
		checkHousing(h);
	}
	resetHousingCheck();				
}
</code></pre>

### Actions

<pre><code>
checkHousing(final Housing h) {
	state = maintenanceState.CheckingHouse;
	h.state = housingState.Checking;
	FixingTimer.schedule(new TimerTask() {
		run() {
		state = maintenanceState.Working;
		h.state = housingState.RecentlyChecked;stateChanged();
		}
	}, 2000);
}

resetHousingCheck() {
	state = maintenanceState.Working;
	for (Housing h : Phonebook.getPhonebook().getAllHousing(test)) {
		h.state = housingState.CheckUpNeeded;
	}
}
</code></pre>

# HousingMaintenanceCompany
### Data

<pre><code>
WatchTime openTime;
WatchTime closeTime;
Mailbox mailbox;
</code></pre>
### Actions

<pre><code>
arrivedAtWork(Person person, String title) {
	if (title == "maintenance worker") {
		//Setting previous maintenance worker role to inactive
		if (maintenanceWorkerRole.person exists) {
			Worker worker = (Worker) maintenanceWorkerRole.person;
			worker.roleFinishedWork();
		}
		//Setting maintenance Worker role to new role
		maintenanceWorkerRole.setPerson(person);
		return maintenanceWorkerRole;
	}
	else {
		return null;
	}
}
	
goingOffWork(Person person) {
	Worker worker = (Worker) person;
	if (worker.getWorkerRole().equals(maintenanceWorkerRole)) {
			maintenanceWorkerRole = null;
	}
}
</code></pre>
