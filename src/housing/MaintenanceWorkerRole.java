package housing;

//import housing.Housing.housingState;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

import housing.Housing.housingState;
import application.Phonebook;
import person.Person;
import person.Role;

public class MaintenanceWorkerRole extends Role {
	//List of housing
	//Need to talk more about housing. 
	//Dynamic number of housing? Cant go through X a day
	//Or automatically X number of housing every day?

	//public enum housingState {UrgentWorkOrder, CheckUpNeeded, RecentlyChecked};
	//public housingState state;
	//state = housingState.CheckUpNeeded;

	//Data

	String name; 
	private Timer FixingTimer  = new Timer();
	public enum maintenanceState {Working, CheckingHouse};
	public maintenanceState state = maintenanceState.Working;
	//private Semaphore workingOnResidence = new Semaphore(0, true);


	class WorkOrder {
		int HomeNumber;
		WorkOrder(int n) {
			HomeNumber = n;
		} 
	};
	//List  WorkOrders;

	public MaintenanceWorkerRole (Person person, String pName, String rTitle)  {
		super(person, pName, rTitle);
		//this.RoleName = rTitle;
		//this.name = pName;
	}
	
	public MaintenanceWorkerRole (String name)
	{
		super(name);
	}

	//Messages
	public void msgNeedMaintenance(Housing houseNeedMain) {
		synchronized(Phonebook.getPhonebook().publicAllHousing) {
			for (Housing h : Phonebook.getPhonebook().publicAllHousing) {
				if (h == houseNeedMain) {
					h.state = housingState.UrgentWorkOrder;
				}
			}
		}
	}

	//Scheduler
	public boolean pickAndExecuteAnAction() 
	{
		if (state == maintenanceState.Working) 
		{
			if(!Phonebook.getPhonebook().publicAllHousing.isEmpty())
			{
				synchronized(Phonebook.getPhonebook().publicAllHousing) 
				{
					for (Housing h : Phonebook.getPhonebook().publicAllHousing) 
					{
						if (h.state == housingState.UrgentWorkOrder) 
						{
							checkHousing(h);
							return true;
						}
					}
	
					for(Housing h : Phonebook.getPhonebook().publicAllHousing) 
					{
						if (h.state == housingState.CheckUpNeeded) 
						{
							checkHousing(h);
							return true;
						}
	
					}
					resetHousingCheck();
					return true;
				}
			}
		}
		return false;
	}

	//Actions
	public void checkHousing(final Housing h) {
		//For some reason this only runs once
		state = maintenanceState.CheckingHouse;
		h.state = housingState.Checking;
		print("Checking housing " + h.housingNumber + " in state " + h.state);
		//print("The number of semaphores is: " + workingOnResidence.availablePermits());
		FixingTimer.schedule(new TimerTask() {
			public void run() {
				//o.state = FoodState.Ready;
				state = maintenanceState.Working;
				h.state = housingState.RecentlyChecked;
				print("Done checking " + h.housingNumber + " in state " + h.state);
				//print("The number of semaphores is: " + workingOnResidence.availablePermits());
				//workingOnResidence.release();
				//print("The number of semaphores is: " + workingOnResidence.availablePermits());
				stateChanged();
			}
		},
		2000);
	}

	public void resetHousingCheck() {
		synchronized(Phonebook.getPhonebook().publicAllHousing) {
			for (Housing h : Phonebook.getPhonebook().publicAllHousing) {
				print("All houses are finished! Rechecking houses");
				h.state = housingState.CheckUpNeeded;
			}
		}
	}
}
