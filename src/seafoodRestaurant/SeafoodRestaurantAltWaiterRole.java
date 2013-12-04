package seafoodRestaurant;

import person.Person;
import seafoodRestaurant.interfaces.SeafoodRestaurantWaiter;
import chineseRestaurant.ChineseRestaurantWaiterRole;
import chineseRestaurant.interfaces.ChineseRestaurantWaiter;

public class SeafoodRestaurantAltWaiterRole extends SeafoodRestaurantWaiterRole implements SeafoodRestaurantWaiter 
{

	public SeafoodRestaurantAltWaiterRole(Person person, String name, String title) 
	{
		super(person, name, title);
	}


//Change
	///ADD TO THIS
}
