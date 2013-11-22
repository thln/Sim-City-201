package application;

import housing.Housing;

import java.util.List;

import bank.Bank;
import market.Market;
import restaurant.Restaurant;

public class Phonebook {

	private Bank bank;
	private Market market;
	private Restaurant restaurant;

	public List<Housing> publicAllHousing; //= Collections.synchronizedList(new ArrayList<Housing>());


	
	//ChineseRestaurant chineseRestaurant;
	//ItalianRestaurant italianRestaurant;
	//MexicanRestaurant mexicanRestaurant;
	//AmericanRestaurant americanRestaurant;
	//SeafoodRestaurant seafoodRestaurant;
	

	private static Phonebook phonebook;

	private Phonebook() {
	}
	
	public static Phonebook getPhonebook() {
		if (phonebook == null) {
			phonebook = new Phonebook();
			return phonebook;
		}
		else {
			return phonebook;
		}
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public Market getMarket() {
		return market;
	}

	public void setMarket(Market market) {
		this.market = market;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
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
}
