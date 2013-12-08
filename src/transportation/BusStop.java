package transportation;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import application.gui.trace.AlertLog;
import application.gui.trace.AlertTag;
import person.Person;

public class BusStop 
{
	private int BusStopNumber;
	List<Person> waitingPassengers = Collections.synchronizedList(new ArrayList<Person>());
	Point location;
	private BusAgent currentBus;
	
	public BusStop(int number, int xCord, int yCord)
	{
		BusStopNumber = number;
		location = new Point(xCord, yCord);
	}
	
	public void waitingForBus(Person waitingPassenger)
	{
		waitingPassengers.add(waitingPassenger);
		 AlertLog.getInstance().logInfo(AlertTag.GENERAL_CITY, "Bus Stop # " + BusStopNumber, "Adding "+ waitingPassenger.getName());
	}
	
	public List<Person> getAllWaitingPassengers(BusAgent bus) //WorkerRole work)
	{	
		
		//There should be a way to check if a bus has called this method 
		//if(bus instanceof Bus)
		//{
			currentBus = bus;
			List<Person> loadingPassengers = waitingPassengers;
			waitingPassengers.clear();
			return loadingPassengers;
			//waitingPassengers.clear();
		//}
//		else
//		{
//			
//		}
	}
	
	public int getBusStopNumber()
	{
		return BusStopNumber;
	}
	
	public Point getBusStopLocation()
	{
		return location;
	}
	
	public int getNumberOfWaitingPassengers()
	{
		return waitingPassengers.size();
	}
}
