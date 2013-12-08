package seafoodRestaurant;
import seafoodRestaurant.interfaces.SeafoodRestaurantCustomer;
import seafoodRestaurant.interfaces.SeafoodRestaurantWaiter;

//import restaurant.CashierAgent.CheckState;

public class SeafoodRestaurantCheck 
{

	public enum CheckState
	{Created, Pending, CustomerHere, Paying, NotPaidOff, PaidOff};

		public String foodItem;
		public double cost;
		public double cash;
		public SeafoodRestaurantCustomer c; //AmericanRestaurantCustomerRole
		public SeafoodRestaurantWaiter w; //AmericanRestaurantWaiterRole
		public CheckState s = CheckState.Created;
		
		public SeafoodRestaurantCheck(String choice, SeafoodRestaurantCustomer cust, SeafoodRestaurantWaiter wait) //AmericanRestaurantCustomerRole, AmericanRestaurantWaiterRole
		{
			//STUB
			foodItem = choice;
			c = cust;
			w = wait;
		}
		
}
