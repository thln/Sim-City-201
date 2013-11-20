package person;

public class DeadBeat extends Person
{
    int wanderTime = 8;
    int parkTime = 20;
    int eatTime = 13;
    
	String name;

    public DeadBeat(String name,  int money)
    {
		super(name);
		this.money = money;
		this.name = name;
    }

	
	public void updateTime(int newTime) 
	{
		if (newTime % wanderTime == 0)
		{
            //Randomly wander between Restaurant, Bank, and Market
		}
        if (newTime == parkTime)
        {
            //GoToPark();
        }   

		if (newTime == sleepTime)
		{
			//GoToSleep();
		}
	}
	
	public void msgWelfareCheck() 
	{
	    money += 50;
	}
}
