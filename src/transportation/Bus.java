package transportation;

import application.Phonebook;
import application.TimeManager;
import agent.Agent;

public abstract class Bus extends Agent{

	boolean needToDeposit;
	String name;


	public Bus(String name) {
		
	}


	//Scheduler
	protected boolean pickAndExecuteAnAction() {
		
		return false;
	}

	//Actions
}