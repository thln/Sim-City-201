package housing;

import person.Person;
import person.Role;

public class MaintenanceWorker extends Role 
{
	//List of housing
	//Need to talk more about housing. 
	//Dynamic number of housing? Cant go through X a day
	//Or automatically X number of housing every day?
	
	String name; 
	
	class WorkOrder 
	{
		int HomeNumber;
		WorkOrder(int n) {
		    HomeNumber = n;
		} 
	};
		//List  WorkOrders;
	
	public MaintenanceWorker(Person person, String name) 
	{
		super(person);
		this.roleName = name;
		this.name = name;
	}
	
	protected boolean pickAndExecuteAnAction() 
	{
		return false;
	}
}
