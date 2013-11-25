package restaurant;

import application.Phonebook;
import person.Person;

public class AltWaiterRole extends WaiterRole {
	//private RevolvingStand theRevolvingStand;
	protected String RoleName = "Alternative Waiter";

	public AltWaiterRole(Person p1, String pName, String rName) {
		super(p1, pName, rName);
	}

	protected void placeOrder(myCustomer MC) {
		isInLobby = false;
		print("Placing " + MC.customer.getCustomerName() + "'s order");

		for (myCustomer myCust : myCustomers) {
			if (myCust.customer == MC.customer) 
			{
				myCust.setWaitingForFood();
			}
		}

		waiterGui.DoGoToKitchen();
		try 
		{
			atDestination.acquire();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();

		}
		waiterGui.DoLeaveCustomer();

		//cook.msgHeresAnOrder(MC.tableNumber, MC.choice, this);
		Phonebook.getPhonebook().getRestaurant().getRevolvingStand().newOrder(new Order(MC.tableNumber, MC.choice, this));
	}

}
