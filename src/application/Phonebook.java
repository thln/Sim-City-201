package application;

import housing.Housing;
import housing.HousingMaintenanceCompany;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bank.Bank;
import market.Market;
import restaurant.Restaurant;
import application.gui.animation.*;

public class Phonebook{

	static private Bank bank;
	static private Market market;
	//mockmarket
	private Restaurant restaurant;
	private HousingMaintenanceCompany housingMaintenanceCompany;
	//mockrestaurant

	private List<Housing> publicAllHousing; //= Collections.synchronizedList(new ArrayList<Housing>());
	private List<Housing> testHousing;// = Collections.synchronizedList(new ArrayList<Housing>());
	
	
	//the following 4 lists are for dynamic building and business making in v2
	private List<Restaurant> restaurants = Collections.synchronizedList(new ArrayList<Restaurant>());
	private List<Bank> banks = Collections.synchronizedList(new ArrayList<Bank>());
	private List<Market> markets = Collections.synchronizedList(new ArrayList<Market>());
	private List<Housing> houses = Collections.synchronizedList(new ArrayList<Housing>());
	
	//ChineseRestaurant chineseRestaurant;
	//ItalianRestaurant italianRestaurant;
	//MexicanRestaurant mexicanRestaurant;
	//AmericanRestaurant americanRestaurant;
	//SeafoodRestaurant seafoodRestaurant;
	
	//List of bus stops
	private List<Point> busStops = new ArrayList<>();

	
	private static Phonebook phonebook;

	private Phonebook() 
	{
		bank = new Bank("Bank");
		market = new Market("Market");
		restaurant = new Restaurant("Restaurant");
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

	public Bank getBank() 
	{
			return banks.get(0); //temporary stub. make more dynamic later
	}

	public void setBank(Bank bank) 
	{
		Phonebook.bank = bank;
	}

	public Market getMarket() 
	{
		return markets.get(0); //temporary stub. make more dynamic later
	}

	public void setMarket(Market market) 
	{
		Phonebook.market = market;
	}

	public Restaurant getRestaurant() 
	{
		return restaurants.get(0); //temporary stub. make more dynamic later
	}

	public void setRestaurant(Restaurant restaurant) 
	{
		this.restaurant = restaurant;

	//Phonebook(Bank bank, Market market, Restaurant restaurant, List<Housing> passHousing) {
		//Phonebook.bank = bank;
		//Phonebook.market = market;
		//Phonebook.restaurant = restaurant;
		//Phonebook.publicAllHousing = passHousing;
		
		//this.chineseRestaurant = chineseRestaurant;
		//this.italianRestaurant = italianRestaurant;
		//this.mexicanRestaurant = mexicanRestaurant;
		//this.americanRestaurant = americanRestaurant;
		//this.seafoodRestaurant = seafoodRestaurant;

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
	
	//the following function is for dynamic building and business making in v2
	public void getBusinessFromGui(Building building) {
		if(building.getName().toLowerCase().contains("market")) {
			Market newMarket = new Market(building.getName());
			newMarket.setBuildingPanel(building.myBuildingPanel);
			markets.add(newMarket);
		}
		if(building.getName().toLowerCase().contains("bank")) {
			Bank newBank = new Bank(building.getName());
			newBank.setBuildingPanel(building.myBuildingPanel);
			banks.add(newBank);
		}
		if(building.getName().toLowerCase().contains("house")) {
			//houses.add(new Housing(building.getName()));
		}
		if(building.getName().toLowerCase().contains("restaurant")) {
			Restaurant newRestaurant = new Restaurant(building.getName());
			newRestaurant.setBuildingPanel(building.myBuildingPanel);
			restaurants.add(newRestaurant);
		}
	}

	public List<Point> getBusStops() {
		return busStops;
	}

	public void setBusStops(List<Point> busStops) {
		this.busStops = busStops;
	}
	
	public void closeBuildings(){
		bank.closeBuilding();
		restaurant.closeBuilding();
		market.closeBuilding();
	}
}
