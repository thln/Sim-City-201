![Interaction Diagram](https://fbcdn-sphotos-h-a.akamaihd.net/hphotos-ak-prn2/v/1467207_10202515164386296_516116633_n.jpg?oh=dac85d8644d4b4c4065f9c1b1d373526&oe=5284CD29&__gda__=1384530388_2be04475c1d081393cde04e70e78ae33)

![Interaction Diagram 2](https://scontent-b-lax.xx.fbcdn.net/hphotos-prn1/v/711516_10202191577647901_2047465932_n.jpg?oh=7aa02da23f36cb2fd96bea1d05a51a2d&oe=528546E7)

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