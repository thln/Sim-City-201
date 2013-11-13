package person;

public class Crook extends Person {
	//These people become dishonest customers in the restaurant

	int time2RobDaBank = 8;
	int eatTime = 12;
	int marketTime = 15;

	//Messages
	public void msgNewTime (int time) {
		if (time == time2RobDaBank) {
			//RobberRole.state = waitingToExecute;
		}
		if (time == eatTime) {
			//RestaurantCustomer.state = waitingToExecute;
		}
		if (time == marketTime && !hasFoodInFridge) {
			//MarketCustomer.state = waitingToExecute;
		}
		if (time == sleepTime) {
			//GoToSleep();
		}
	}
}
