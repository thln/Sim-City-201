package market;

import java.util.*;
import person.Person;

public class Market {

	double money;
	HashMap<String, Integer> inventory = new HashMap<String, Integer>();
	
	SalesPersonRole salesPersonRole;
	UPSmanRole	UPSmanRole;
	
	public void iWantProducts(MarketCustomerRole customer, String item, int numWanted) {
		salesPersonRole.msgIWantProducts(customer, item, numWanted);
	}
	
	public SalesPersonRole arrivedAtWorkSalesPerson(Person person) {
		return salesPersonRole;
	}
	
	public UPSmanRole arrivedAtWorkUPSman(Person person) {
		return UPSmanRole;
	}
}
