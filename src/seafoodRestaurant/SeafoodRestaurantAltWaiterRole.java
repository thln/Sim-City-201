package seafoodRestaurant;

import seafoodRestaurant.interfaces.SeafoodRestaurantWaiter;
import chineseRestaurant.ChineseRestaurantWaiterRole;
import chineseRestaurant.interfaces.ChineseRestaurantWaiter;

public class SeafoodRestaurantAltWaiterRole extends SeafoodRestaurantWaiterRole implements SeafoodRestaurantWaiter 
{

	public SeafoodRestaurantAltWaiterRole(String name,
			SeafoodRestaurantHostRole h, SeafoodRestaurantCookRole c,
			SeafoodRestaurantCashierRole cas, int n) 
	{
		super(name, h, c, cas, n);
	}


//Change
	///ADD TO THIS
}
