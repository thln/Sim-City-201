package application;

import housing.Housing;
import housing.HousingMaintenanceCompany;

import java.util.List;

import bank.Bank;
import market.Market;
import restaurant.Restaurant;

public class Phonebook{

	static private Bank bank;
	static private Market market;
	//mockmarket
	private Restaurant restaurant;
	private HousingMaintenanceCompany housingMaintenanceCompany;
	//mockrestaurant

	public List<Housing> publicAllHousing; //= Collections.synchronizedList(new ArrayList<Housing>());


	
	//ChineseRestaurant chineseRestaurant;
	//ItalianRestaurant italianRestaurant;
	//MexicanRestaurant mexicanRestaurant;
	//AmericanRestaurant americanRestaurant;
	//SeafoodRestaurant seafoodRestaurant;
	

	private static Phonebook phonebook;

	private Phonebook() 
	{
		bank = new Bank("Bank");
		market = new Market("Market");
		restaurant = new Restaurant("Restaurant");
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

	public Bank getBank() 
	{
			return bank;
	}

	public void setBank(Bank bank) 
	{
		Phonebook.bank = bank;
	}

	public Market getMarket() 
	{
		return market;
	}

	public void setMarket(Market market) 
	{
		Phonebook.market = market;
	}

	public Restaurant getRestaurant() 
	{
		return restaurant;
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
	
	public List<Housing> getAllHousing()
	{
		return publicAllHousing;
	}
	
	public void setHousingList(List<Housing> ApplicationHousing) 
	{
		this.publicAllHousing = ApplicationHousing;
	}
	
	public HousingMaintenanceCompany getHousingMaintenanceCompany()
	{
		return housingMaintenanceCompany;
	}
	
	public void setHousingMaintenanceCompany(HousingMaintenanceCompany HMC)
	{
		this.housingMaintenanceCompany = HMC;
	}
}
