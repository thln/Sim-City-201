package application;

import housing.Housing;
import housing.HousingMaintenanceCompany;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import italianRestaurant.ItalianRestaurant;
import chineseRestaurant.ChineseRestaurant;
import bank.Bank;
import market.Market;
import application.gui.animation.*;

public class Phonebook{

	//Banks
	static private Bank eastBank;
	static private Bank westBank;

	//Markets
	static private Market eastMarket;
	static private Market westMarket;

	//Restaruants
	static private ChineseRestaurant chineseRestaurant;
	static private ItalianRestaurant italianRestaurant;
	//MexicanRestaurant mexicanRestaurant;
	//AmericanRestaurant americanRestaurant;
	//SeafoodRestaurant seafoodRestaurant;

	private HousingMaintenanceCompany housingMaintenanceCompany;


	private List<Housing> publicAllHousing; //= Collections.synchronizedList(new ArrayList<Housing>());
	private List<Housing> testHousing;// = Collections.synchronizedList(new ArrayList<Housing>());


	//the following 4 lists are for dynamic building and business making in v2




	//List of bus stops
	private List<Point> busStops = new ArrayList<>();


	private static Phonebook phonebook;

	private Phonebook() 
	{
		//Set bus stops
		busStops.add(new Point(127, 28));		//Top left bus stop = busStop(0)
		busStops.add(new Point(420, 28));		//Top right bus stop = busStop(1)
		busStops.add(new Point(127, 230));		//Bottom left bus stop = busStop(2)
		busStops.add(new Point(420, 230));		//Bottom right bus stop = busStop(3)
		
		eastBank = new Bank("East Bank");
		eastBank.setClosestStop(busStops.get(3));		//setting building's closest bus stop
		
		//	westBank = new Bank("Bank");
		//set bus stop
		
		eastMarket = new Market("Market");
		eastMarket.setClosestStop(busStops.get(1));		//setting building's closest bus stop
		
		//	westMarket = new Market("Market");
		// set bus stop
		
		chineseRestaurant = new ChineseRestaurant("Chinese Restaurant");
		chineseRestaurant.setClosestStop(busStops.get(1));		//setting building's closest bus stop
		
		italianRestaurant = new ItalianRestaurant("Italian Restaurant");
		italianRestaurant.setClosestStop(busStops.get(1));		//setting building's closest bus stop
		
		housingMaintenanceCompany = new HousingMaintenanceCompany("Housing maintenance company");
		
	
	}

	public static Phonebook getPhonebook() 
	{
		if (phonebook == null) 
		{
			phonebook = new Phonebook();
			return phonebook;
		}
		else 
		{
			return phonebook;
		}
	}

	
	public Bank getEastBank() 
	{
			return eastBank; 
	}
	
	public Bank getWestBank() 
	{
			return westBank; 
	}

	public Market getEastMarket() 
	{
			return eastMarket;
	}
	
	public Market getWestMarket() 
	{
			return westMarket;
	}

	public ChineseRestaurant getChineseRestaurant() 
	{
		return chineseRestaurant; //temporary stub. make more dynamic later
	}
	
	public ItalianRestaurant getItalianRestaurant() 
	{
		return italianRestaurant; //temporary stub. make more dynamic later
	}

	public List<Housing> getAllHousing(boolean test)
	{
		if(test)
		{
			return testHousing;
		}
		else
		{
			return publicAllHousing;
		}
	}

	public void setHousingList(List<Housing> ApplicationHousing) 
	{
		this.publicAllHousing = ApplicationHousing;
	}

	public void setTestHousingList(List<Housing> testingHMC)
	{
		this.testHousing = testingHMC;
	}

	public HousingMaintenanceCompany getHousingMaintenanceCompany()
	{
		return housingMaintenanceCompany;
	}

	public void setHousingMaintenanceCompany(HousingMaintenanceCompany HMC)
	{
		this.housingMaintenanceCompany = HMC;
	}

	public List<Point> getBusStops() {
		return busStops;
	}

	public void setBusStops(List<Point> busStops) {
		this.busStops = busStops;
	}

	public void closeBuilding(String type){
		System.out.println("Closing " + type);
		if (type == "eastBank")
			eastBank.closeBuilding();
		if (type == "westBank")
			westBank.closeBuilding();
		if (type == "eastMarket")
			eastMarket.closeBuilding();
		if (type == "westMarket")
			westMarket.closeBuilding();
		if (type == "chineseRestaurant")
			chineseRestaurant.closeBuilding();
	}
	
	public void openBuilding(String type){
		System.out.println("Opening " + type);
		if (type == "eastBank")
			eastBank.userClosed = false;
		if (type == "westBank")
			westBank.userClosed = false;
		if (type == "eastMarket")
			eastMarket.userClosed = false;
		if (type == "westMarket")
			westMarket.userClosed = false;
		if (type == "chineseRestaurant")
			chineseRestaurant.userClosed = false;
	}
}
