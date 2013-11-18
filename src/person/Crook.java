package person;

public class Crook extends Person {
	//These people become dishonest customers in the restaurant

	int time2RobDaBank = 8;
	int eatTime = 12;
	int marketTime = 15;


	//Messages
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
}

