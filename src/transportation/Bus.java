package transportation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import person.Person;
import application.Phonebook;
import application.TimeManager;
import agent.Agent;

public abstract class Bus extends Agent{

	/*********************
	 ****** DATA *********
	 *********************/
	boolean needToDeposit;
	String name;
	
	class busPassenger
	{
		Person passenger;
		int busStop;
		busPassenger(Person p, int bStop)
		{
			passenger = p;
			busStop = bStop;
		}
	}
	
	List<busPassenger> busPassengers = Collections.synchronizedList(new ArrayList<busPassenger>());
	List<Person> peopleAtBusStop = Collections.synchronizedList(new ArrayList<Person>());
	

	public Bus(String name) 
	{
		
	}

	/*********************
	 ***** MESSAGES ******
	 *********************/
	
	public void msgGettingOnBus(Person p, int bStop)
	{
		busPassengers.add(new busPassenger(p, bStop));
		stateChanged();
	}
	
	/*********************
	 ***** SCHEDULER *****
	 *********************/
	protected boolean pickAndExecuteAnAction() 
	{
		
		return false;
	}

	/*********************
	 ****** ACTIONS ******
	 *********************/
	
	private void tellPeopleWaiting()
	{
		
	}
	
	private void tellPeopleGetOff()
	{
		
	}
}