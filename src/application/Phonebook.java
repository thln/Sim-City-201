package application;

import bank.Bank;
import market.Market;
import restaurant.Restaurant;

public class Phonebook {
	public static Bank bank;
	private Market market;
	private Restaurant restaurant;
	
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
	}
}
