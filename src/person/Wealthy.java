package person;

import person.Role.roleState;

class Wealthy extends Person 
{
    int eatTime1 = 10;
    int eatTime2 = 16;
    int bankTime = 10;
    int sleepTime = 22;
    boolean needToDeposit;

    //Messages
    void msgRentDue () 
    {
	    //roles.landlord.waitingToExecute;
	}

    void msgNewTime (int time) 
    {
		if (time == eatTime1)
		{		
			//Need to turn Restaurant into a "Role"
			//RestaurantCustomer.state = roleState.waitingToExecute;
		}
		if (time == eatTime2)
		{
			//RestaurantCustomer.state = roleState.waitingToExecute;
		}
		if (time == sleepTime)
		{
		    //GoToSleep();
		}
	}
}