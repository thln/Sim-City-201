package application;

import housing.Housing;
import housing.HousingMaintenanceCompany;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seafoodRestaurant.SeafoodRestaurant;
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


	//Restaurants
	static private ChineseRestaurant chineseRestaurant;
	static private SeafoodRestaurant seafoodRestaurant;
	//ItalianRestaurant italianRestaurant;
	static private ItalianRestaurant italianRestaurant;
	//MexicanRestaurant mexicanRestaurant;
	//AmericanRestaurant americanRestaurant;
	

	private HousingMaintenanceCompany housingMaintenanceCompany;


	private List<Housing> publicAllHousing; //= Collections.synchronizedList(new ArrayList<Housing>());
	private List<Housing> testHousing;// = Collections.synchronizedList(new ArrayList<Housing>());

	private RadioStation cityDJ;
	
	//the following 4 lists are for dynamic building and business making in v2




	//List of bus stops
	private List<Point> busStops = new ArrayList<>();


	private static Phonebook phonebook;

	private Phonebook() 
	{
		eastBank = new Bank("Bank");
		westBank = new Bank("Bank");
		eastMarket = new Market("Market");
		westMarket = new Market("Market");
		seafoodRestaurant = new SeafoodRestaurant("Seafood Restaurant");
		chineseRestaurant = new ChineseRestaurant("Chinese Restaurant");
		italianRestaurant = new ItalianRestaurant("Italian Restaurant");
		
		cityDJ = new RadioStation();
		//cityDJ.
		
		/* For future use after all the restaurants have been integrated
		 * 
		mexicanRestaurant = new MexicanRestaurant("Mexican Restaurant");
		seafoodRestaurant = new SeafoodRestaurant("Seafood Restaurant");
		americanRestaurant = new AmericanRestaurant("American Restaurant");
		*/
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

	public SeafoodRestaurant getSeafoodRestaurant() {
		return seafoodRestaurant;
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
	
	public RadioStation getRadioStation()
	{
		return cityDJ;
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
