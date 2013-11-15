package housing;

import person.Person;
import person.Role;

public class MaintenanceWorker extends Role 
{
	//List of housing
	//Need to talk more about housing. 
	//Dynamic number of housing? Cant go through X a day
	//Or automatically X number of housing every day?
	class WorkOrder 
	{
		int HomeNumber;
		WorkOrder(int n) {
		    HomeNumber = n;
		} 
	};
		//List  WorkOrders;
	
	protected MaintenanceWorker(Person person) 
	{
		super(person);
	}
	
	protected boolean pickAndExecuteAnAction() 
	{
		return false;
	}
}
