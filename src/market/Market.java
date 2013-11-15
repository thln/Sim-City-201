package market;

import java.util.*;
import person.Person;

public class Market {

	double money;
	HashMap<String, Integer> inventory = new HashMap<String, Integer>();
	
	SalesPerson salesPersonRole;
	UPSman	UPSmanRole;
	
	public void iWantProducts(MarketCustomer customer, String item, int numWanted) {
		salesPersonRole.msgIWantProducts(customer, item, numWanted);
	}
	
	public SalesPerson arrivedAtWorkSalesPerson(Person person) {
		return salesPersonRole;
	}
	
	public UPSman arrivedAtWorkUPSman(Person person) {
		return UPSmanRole;
	}
}
