package person;

import person.Role.RoleState;

public class Wealthy extends Person 
{
    int eatTime1 = 10;
    int eatTime2 = 16;
    int bankTime = 10;
    int sleepTime = 22;
    boolean needToDeposit;
    String name;

    
    public Wealthy(String name,  int money) {
		super(name);
		this.money = money;
		this.name = name;
    }
    
    //Messages
    void msgRentDue() {
	    //roles.landlord.waitingToExecute;
	}
}