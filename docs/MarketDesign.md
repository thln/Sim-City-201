![Interaction Diagram](https://scontent-b-lax.xx.fbcdn.net/hphotos-prn2/v/1457038_10202191577767904_1313987147_n.jpg?oh=d6dd432eacacfe9d0c86ccfa769e1a3c&oe=5284F43A)

# Sales Person
### Data

<pre><code>
List <Order> orders;
double money;

class Order {
	Order(Customer customer, HashMap <String, Integer> items) {
		this.customer = customer;
		this.items = items;
		
if (customer is an instance of MarketCustomer)
custType = marketCustomer;
else
	custType = business;
	}

Order(Customer customer, HashMap <String, Integer> items, point location) {
		this.customer = customer;
		this.items = items;
		this.location = location;
		
if (customer is an instance of MarketCustomer)
custType = marketCustomer;
else
	custType = business;
	}

	Customer customer;
	enum CustomerType {marketCustomer, business}
	CustomerType custType;
	HashMap <String, Integer> items;
	double orderCost;
	enum orderState {open, processing, itemsFound, gaveToCustomer}
	orderState state.open;
	point location;
}
</code></pre>

### Messages

<pre><code>
msgIWantProducts(MarketCustomer customer, HashMap <String, Integer> items) {
	orders.add(new Order(customer, items);
}

msgIWantProducts(Business customer, HashMap <String, Integer> items, point location, double payment) {
	orders.add(new Order(customer, items, location);
	money += payment;
}

msgPayment(MarketCustomer customer, double payment) {
	money += payment;
	Order o = orders.findOrderWith(customer);
	orders.remove(o);
}
</code></pre>

### Scheduler

<pre><code>
if there exists an Order o in orders &&
o.state == open;
	then
		findItems(o);

if there exists an Order o in orders &&
o.state == itemsFound;
	then
		giveCustomerItems(o);
</code></pre>

### Actions

<pre><code>
findItems(Order o) {
	o.state = orderState.pending;
	marketRunner.msgHeresAnOrder(o);
}

giveCustomerItems(Order o) {
	o.state = orderState.gaveToCustomer;
	calculateOrderCost(o);
	o.customer.msgHereAreYourThings(o.items, o.orderCost);
}
</code></pre>

# Market Runner
### Data

<pre><code>
List <Order> orders;
HashMap <String, Integer> inventory;
</code></pre>

### Messages

<pre><code>
msgHeresAnOrder(Order o) {
	orders.add(o);
}
</code></pre>

### Scheduler

<pre><code>
if there exists an Order o in order
	then
		processOrder(o);
</code></pre>

### Actions

<pre><code>
processOrder(Order o) {
	if (o.customerType is an instance of customer) {
		decreaseInventoryBy(o.items);
                salesPerson.msgOrderFulfilled(o);
		orders.remove(o);
	}
	else { //o.customerType is an instance of business
		decreaseInventoryBy(o.items);
                UPSman.msgDeliverOrder(o);
		orders.remove(o);
	}
}
</code></pre>

# UPS Man
### Data

<pre><code>
List <Order> orders;
</code></pre>

### Messages

<pre><code>
msgDeliverOrder(Order o) {
	orders.add(o);
}
</code></pre>

### Scheduler

<pre><code>
if there exists an Order o in orders
	then
		deliverOrder(o);
</code></pre>

### Actions

<pre><code>
deliverOrder(Order o) {
	o.customer.HereIsOrder(o);
	order.remove(o);
}
</code></pre>

# Market Customer
### Data

<pre><code>
boolean recivedItems = false;
double money;
double bill;
HashMap <String, Integer> inventory;
</code></pre>

### Messages

<pre><code>
msgHereAreYouThings(HashMap<String, Integer> items, double orderCost) {
	inventory.add(items);
	recievedItems = true;
	bill = orderCost;
}
</code></pre>

### Scheduler

<pre><code>
if recievedItems = true;
	then
		payBill();
</code></pre>

### Actions

<pre><code>
payBill(){
	salesPerson.msgPayment(this, bill);
	money -= bill;
	recievedItems = false;
}
</code></pre>
