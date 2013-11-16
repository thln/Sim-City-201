package restaurant;

public class AltWaiter extends Waiter 
{
	private RevolvingStand theRevolvingStand;
	protected String RoleName = "Alternative Waiter";
	
	public AltWaiter(String name) 
	{
		super(name);
		//super(name);
		//Waiter(name);

		this.name = name;
	}
	
	protected void placeOrder(myCustomer MC) {
		isInLobby = false;
		//print("Placing " + MC.customer.getCustomerName() + "'s order");

		for (myCustomer myCust : myCustomers) {
			if (myCust.customer == MC.customer) {
				myCust.setWaitingForFood();
			}
		}

		//waiterGui.DoGoToKitchen();
		try {
			atDestination.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
		//waiterGui.DoLeaveCustomer();

		//cook.msgHeresAnOrder(MC.tableNumber, MC.choice, this);
		theRevolvingStand.newOrder(new Order(MC.tableNumber, MC.choice, this));
	}
	
	//NEED TO USE THIS SOMEWHERE
	public void setRevolvingStand(RevolvingStand rs)
	{
		theRevolvingStand = rs;
	}
}
