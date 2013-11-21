package person;

public class Crook extends Person {
	//These people become dishonest customers in the restaurant

	int time2RobDaBank = 8;
	int eatTime = 12;
	int marketTime = 15;
	String name;
	String type = "Crook";

    public Crook(String name,  int money)
    {
		super(name);
		this.money = money;
		this.name = name;
    }
    
    public Crook(String name, String type, int money)
    {
		super(name, type);
		this.money = money;
		this.name = name;
    }

	
	public void updateTime(int newTime) {
		if (this.newTime == time2RobDaBank) {
			//RobberRole.state = waitingToExecute;
		}
		if (this.newTime == eatTime) {
			//RestaurantCustomer.state = waitingToExecute;
		}
		if (this.newTime == marketTime && !hasFoodInFridge) {
			//MarketCustomer.state = waitingToExecute;
		}
		if (this.newTime == sleepTime) {
			//GoToSleep();
		}
	}
	public String getType(){
		return type;
	}
}

