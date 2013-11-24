package housing.test;
import housing.Housing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import person.Worker;
import application.Phonebook;
import junit.framework.TestCase;

public class MaintenanceWorkerTest extends TestCase 
{

	List<Housing> testHousing;// = Collections.synchronizedList(new ArrayList<Housing>());
	Worker testingMaintenanceWorker;
	
	//Why isn't Phonebook working
	public void SetUp() throws Exception
	{
		super.setUp();
		testHousing = Collections.synchronizedList(new ArrayList<Housing>());
		Phonebook.getPhonebook().setHousingList(testHousing);
		testingMaintenanceWorker = new Worker("Henry", 500, "Maintenance", "Maintenance Company", 800, 1200, 1800);
	}
	
	//Test that maintenance worker can handle one house
	
	public void testNormativeScenarioOne()
	{
		//assertTrue();
	}
}
