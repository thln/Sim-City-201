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

# Maintenance
### Data

<pre><code>
Class WorkOrder {
int HomeNumber;
WorkOrder(int n) {
	HomeNumber = n:
} 
}
List <WorkOrder> WorkOrders;
</code></pre>

### Messages

<pre><code>
msgPleaseFixHome (int HomeNumber) {
	WorkOrder.add(new WorkOrder(HomeNumber));
}
</code></pre>

### Scheduler

<pre><code>
If (∃ WorkOrder ∈ WorkOrders)
	actFixHome(WorkOrder);
</code></pre>

### Actions

<pre><code>
actFixHome(WorkOrder wh) {
	WorkOrders.remove(wh);
	//Fix Grill, Sink, Table, etc Gui stuff
}
</code></pre>