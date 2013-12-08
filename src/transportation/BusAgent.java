package transportation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import person.Person;
import application.Phonebook;
import application.TimeManager;
import application.gui.animation.agentGui.BusGuiHorizontal;
import application.gui.animation.agentGui.BusGuiVertical;
import agent.Agent;

public abstract class BusAgent extends Agent{

	/*********************
	 ****** DATA *********
	 *********************/
	boolean needToDeposit;
	boolean ifHorizontal;
	int currentBusStop;
	int expectedNumberOfPassengers;
	BusGuiHorizontal guiH;
	BusGuiVertical guiV;
	String name;
	
	public enum busState {Driving, ReachedStop, DroppedOffPeople, PickingUpPeople};
	busState state = busState.Driving;
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
	

	public BusAgent(String name) 
	{

	}
	
	/**** 
	 * Interactions to go -
	 * Person messaging bus stop and going to sleep with metaphor
	 * Bus arrives at bus stops and gets all waiting customers
	 * Messages all sleeping customers to wake up
	 * Customers responds by messaging bus I am getting on
	 * Customer goes to sleep, Gui disappears
	 * Bus arrives at next bus stop, messages all customers with that destination
	 * Customer wakes up, GUI reappears at that spot
	 * ***/

	/*********************
	 ***** MESSAGES ******
	 *********************/
	
	public void msgAtBusStop(int busStopNumber)
	{
		currentBusStop = busStopNumber;
		state = busState.ReachedStop;
		stateChanged();
	}
	
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
		if(state == busState.ReachedStop)
		{
			tellPeopleGetOff();
			return true;
		}
		if(state == busState.DroppedOffPeople)
		{
			tellPeopleWaiting();
			return true;
		}
		if(state == busState.PickingUpPeople)
		{
			checkNumberOfPassengers();
			return true;
		}
		return false;
	}

	/*********************
	 ****** ACTIONS ******
	 *********************/
	
	private void tellPeopleWaiting()
	{
		state = busState.PickingUpPeople;
		peopleAtBusStop = Phonebook.getPhonebook().getAllBusStops().get(currentBusStop).getAllWaitingPassengers(this);
		expectedNumberOfPassengers += peopleAtBusStop.size();
		if(peopleAtBusStop.isEmpty())
		{
			return;
		}
		else
		{
			for(int i = 0; i < peopleAtBusStop.size(); i++)
			{
				peopleAtBusStop.get(i).msgBusIsHere();
				peopleAtBusStop.remove(i);
			}
		}
	}
	
	private void tellPeopleGetOff()
	{
		state = busState.DroppedOffPeople;
		for(int i = 0; i < busPassengers.size(); i++)
		{
			if(busPassengers.get(i).busStop == currentBusStop)
			{
				busPassengers.get(i).passenger.msgAtBusStopDestination();
				busPassengers.remove(i);
			}
		}
		expectedNumberOfPassengers = busPassengers.size();
	}
	
	private void checkNumberOfPassengers()
	{
		if(expectedNumberOfPassengers == busPassengers.size())
		{
			state = busState.Driving;
			currentBusStop = 0;
			expectedNumberOfPassengers = 0;
		}
		
	}
	
	public void setGui(BusGuiHorizontal g)
	{
		ifHorizontal = true;
		guiH = g;
	}
	
	public void setGui(BusGuiVertical g)
	{
		ifHorizontal = false;
		guiV = g;
	}
}