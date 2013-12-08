package chineseRestaurant;

import chineseRestaurant.interfaces.ChineseRestaurantWaiter;
import application.Phonebook;
import person.Person;

public class ChineseRestaurantAltWaiterRole extends ChineseRestaurantWaiterRole implements ChineseRestaurantWaiter {
	//private RevolvingStand theRevolvingStand;
	protected String RoleName = "Alternative AmericanRestaurantWaiter";

	public ChineseRestaurantAltWaiterRole(Person p1, String pName, String rName) {
		super(p1, pName, rName);
	}

	protected void placeOrder(ChineseRestaurantMyCustomer MC) {
		isInLobby = false;
		if(test)
		{
			print("Test AmericanRestaurantCustomer");
		}
		else
		{
			print("Placing " + MC.customer.getCustomerName() + "'s order");
		}
		
		for (ChineseRestaurantMyCustomer myCust : ChineseRestaurantMyCustomers) {
			if (myCust.customer == MC.customer || myCust.testCustomer == MC.customer ) 
			{
				myCust.setWaitingForFood();
			}
		}

//		waiterGui.DoGoToKitchen();
//		try 
//		{
//			atDestination.acquire();
//		} 
//		catch (InterruptedException e) 
//		{
//			e.printStackTrace();
//
//		}
//		waiterGui.DoLeaveCustomer();

		//cook.msgHeresAnOrder(MC.tableNumber, MC.choice, this);
		print("Placing order on Revolving Stand.");
		Phonebook.getPhonebook().getChineseRestaurant().getRevolvingStand().newOrder(new ChineseRestaurantOrder(MC.tableNumber, MC.choice, this));
	}

}
