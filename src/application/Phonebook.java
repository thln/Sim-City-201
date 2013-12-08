package application;

import housing.Housing;
import housing.HousingMaintenanceCompany;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import italianRestaurant.ItalianCustomerRole;
import italianRestaurant.ItalianRestaurant;
import chineseRestaurant.ChineseRestaurant;
import bank.Bank;
import market.Market;
import application.gui.animation.*;
import application.gui.animation.agentGui.Gui;

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

	//Intersections	
	public Intersection intersection1 = new Intersection(170, 72, 30, 38, 1);
	public Intersection intersection2 = new Intersection(390, 72, 30, 38, 2);
	public Intersection intersection3 = new Intersection(170, 192, 30, 38, 3);
	public Intersection intersection4 = new Intersection(390, 192, 30, 38, 4);
	

	private static Phonebook phonebook;

	private Phonebook() 
	{
		eastBank = new Bank("Bank");
		//	westBank = new Bank("Bank");
		eastMarket = new Market("Market");
		//	westMarket = new Market("Market");
		chineseRestaurant = new ChineseRestaurant("Chinese Restaurant");
		italianRestaurant = new ItalianRestaurant("Italian Restaurant");
		housingMaintenanceCompany = new HousingMaintenanceCompany("Housing maintenance company");
		getBusStops().add(new Point(172, 28));
		getBusStops().add(new Point(172, 230));
		getBusStops().add(new Point(420, 28));
		getBusStops().add(new Point(420, 230));
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
	
	public class Intersection {
		public Rectangle intersection;
		public int number;

		public boolean intersectionBusy;
		
		public Intersection(int x, int y, int width, int height, int n) {
			intersection = new Rectangle(x, y, width, height);
			intersectionBusy = false;
			number = n;
		}
		
		public Rectangle getIntersection() {
			return intersection;
		}

		public void setIntersection(Rectangle intersection) {
			this.intersection = intersection;
		}

		public boolean isIntersectionBusy() {
			return intersectionBusy;
		}

		public void setIntersectionBusy(boolean intersectionBusy) {
			this.intersectionBusy = intersectionBusy;
		}
	}
}
