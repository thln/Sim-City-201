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

	List<Housing> allHousing = Collections.synchronizedList(new ArrayList<Housing>());
	
	Worker testingMaintenanceWorker = new Worker("Henry", 500, "Maintenance", "Maintenance Company", 800, 1200, 1800);

	//Why isn't Phonebook working
	//Phonebook.getPhonebook().setHousing(testHousing);
}
