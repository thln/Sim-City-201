package restaurant;

public class Order {

	Waiter waiter;
	RestaurantCustomer customer;

	int tableNumber;

	public String choice;

	enum orderStatus {Open, Cooking, Done}
	orderStatus status = orderStatus.Open;

	Order(int tableNumber, String choice, Waiter waiter) {
		this.tableNumber = tableNumber;
		this.choice = choice;
		this.waiter = waiter;
	}

	Order(int tableNumber, String choice, RestaurantCustomer customer) {
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