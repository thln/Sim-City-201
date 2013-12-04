package seafoodRestaurant.interfaces;

import restaurant.Check;
//import restaurant.CustomerAgent;
//import restaurant.WaiterAgent;

public interface SeafoodRestaurantCashier {

	
	public abstract void GiveMeCheck(String choice, SeafoodRestaurantCustomer cust, SeafoodRestaurantWaiter wait);
	public abstract void HereIsPayment(Check ch, double cash);
}
