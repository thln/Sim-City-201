package transportation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import person.Person;

public class BusStop 
{
	List<Person> waitingPassengers = Collections.synchronizedList(new ArrayList<Person>());
	
	public void waitingForBus(Person waitingPassenger)
	{
		waitingPassengers.add(waitingPassenger);
	}
	
	public List<Person> getAllWaitingPassengers() //WorkerRole work)
	{
		//if(Bus)
		//{
			return waitingPassengers;
			//waitingPassengers.clear();
		//}
	}
}
