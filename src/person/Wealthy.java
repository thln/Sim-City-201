package person;

class Wealthy implements Person 
{
    int eatTime1 = 10;
    int eatTime2 = 16;
    int bankTime = 10;
    int sleepTime = 22;
    boolean needToDeposit;

    //Messages
    void msgRentDue () 
    {
	    roles.landlord.waitingToExecute;
	}

    void msgNewTime (int time) 
    {
		if (time == eatTime1)
		{
		    //then activate roles.restaurantCustomer;
		}
		if (time == eatTime2)
		{
		    //then activate roles.restaurantCustomer;
		}
		if (time == sleepTime)
		{
		    GoToSleep();
		}
	}