package seafoodRestaurant;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;

import restaurant.interfaces.Customer;
import restaurant.interfaces.Waiter;

//import restaurant.CashierAgent.CheckState;

public class SeafoodRestaurantCheck 
{

	public enum CheckState
	{Created, Pending, CustomerHere, Paying, NotPaidOff, PaidOff};

		public String foodItem;
		public double cost;
		public double cash;
		public SeafoodRestaurantCustomer c; //CustomerAgent
		public SeafoodRestaurantWaiter w; //WaiterAgent
		public CheckState s = CheckState.Created;
		
		public SeafoodRestaurantCheck(String choice, SeafoodRestaurantCustomer cust, SeafoodRestaurantWaiter wait) //CustomerAgent, WaiterAgent
		{
			//STUB
			foodItem = choice;
			c = cust;
			w = wait;
		}
		
}
