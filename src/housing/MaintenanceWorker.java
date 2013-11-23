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
	protected String RoleName = "Maintenance Worker";
	
	class WorkOrder 
	{
		int HomeNumber;
		WorkOrder(int n) {
		    HomeNumber = n;
		} 
	};
		//List  WorkOrders;
	
	public MaintenanceWorker(Person person, String pName, String rName) 
	{
		super(person, pName, rName);
	}
	
	public boolean pickAndExecuteAnAction() 
	{
		return false;
	}
}
