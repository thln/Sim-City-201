package restaurant;

public class myCustomer {
	
	RestaurantCustomer restaurantCustomer;
	
	int tableNumber;
	double CheckAmount = 0;
	int xHome, yHome;
	
	enum customerState {Waiting, Seated, ReadyToOrder, RequestedToOrder, Ordered, Reorder, WaitingForFood, FoodReady, GotFood, WantCheck, GaveCheck, Finished};
	customerState state = customerState.Waiting;
	
	String choice;
	
	/**
	 * Constructor for myCustomer class
	 */
	
	myCustomer (RestaurantCustomer restaurantCustomer, int tableNumber, int xHome, int yHome) {
		this.restaurantCustomer = restaurantCustomer;
		this.tableNumber = tableNumber;
		this.xHome = xHome;
		this.yHome = yHome;
	}
	
	
	
	//Utilities
	
	RestaurantCustomer getCustomer() {
		return restaurantCustomer;
	}

	boolean isWaiting() {
		if (state == customerState.Waiting)
			return true;
		return false;
	}
	
	boolean isReadyToOrder() {
		if (state == customerState.ReadyToOrder)
			return true;
		return false;
	}
	
	boolean isRequestedToOrder() {
		if (state == customerState.RequestedToOrder)
			return true;
		return false;
	}
	
	boolean isOrdered() {
		if (state == customerState.Ordered)
			return true;
		return false;
	}
	
	boolean isReorder() {
		if (state == customerState.Reorder)
			return true;
		return false;
	}
	
	boolean isGotFood() {
		if (state == customerState.GotFood)
			return true;
		return false;
	}
	
	boolean isWantCheck() {
		if (state == customerState.WantCheck)
			return true;
		return false;
	}
	
	boolean isFinished() {
		if (state == customerState.Finished)
			return true;
		return false;
	}
	
	public void setSeated() {
		state = customerState.Seated;
	}
	
	public void setReadyToOrder() {
		state = customerState.ReadyToOrder;
	}
	
	public void setRequestedToOrder() {
		state = customerState.RequestedToOrder;
	}
	
	public void setOrdered() {
		state = customerState.Ordered;
	}
	
	public void setReorder() {
		state = customerState.Reorder;
	}
	
	public void setWaitingForFood() {
		state = customerState.WaitingForFood;
	}
	
	public void setChoice(String choice) {
		this.choice = choice;
	}
	
	void setFoodReady() {
		state = customerState.FoodReady;
	}
	
	void setGotFood() {
		state = customerState.GotFood;
	}
	
	void setWantCheck() {
		state = customerState.WantCheck;
	}
	
	void setFinished() {
		state = customerState.Finished;
	}
	
}
