package housing;

//import housing.Housing.housingState;
import person.Person;
import person.Role;

public class MaintenanceWorkerRole extends Role 
{
	//List of housing
	//Need to talk more about housing. 
	//Dynamic number of housing? Cant go through X a day
	//Or automatically X number of housing every day?
	
	//public enum housingState {UrgentWorkOrder, CheckUpNeeded, RecentlyChecked};
	//public housingState state;
	//state = housingState.CheckUpNeeded;
	
	String name; 
	
	class WorkOrder 
	{
		int HomeNumber;
		WorkOrder(int n) {
		    HomeNumber = n;
		} 
	};
		//List  WorkOrders;
	
	public MaintenanceWorkerRole(Person person, String name) 
	{
		super(person);
		this.RoleName = name;
		this.name = name;
	}
	
	protected boolean pickAndExecuteAnAction() 
	{
		return false;
	}
}
