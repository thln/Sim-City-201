package restaurant;

public class Order {

	WaiterAgent waiter;
	CustomerAgent customer;

	int tableNumber;

	public String choice;

	enum orderStatus {Open, Cooking, Done}
	orderStatus status = orderStatus.Open;

	Order(int tableNumber, String choice, WaiterAgent waiter) {
		this.tableNumber = tableNumber;
		this.choice = choice;
		this.waiter = waiter;
	}

	Order(int tableNumber, String choice, CustomerAgent customer) {
		this.tableNumber = tableNumber;
		this.choice = choice;
		this.customer = customer;
	}

	//utilities
	public void setCooking() {
		status = orderStatus.Cooking;
	}

	public void setDone() {
		status = orderStatus.Done;
	}

	public boolean isOpen() {
		if (status == orderStatus.Open)
			return true;
		else
			return false;
	}


	public boolean isDone() {
		if (status == orderStatus.Done)
			return true;
		else
			return false;
	}
}