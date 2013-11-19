package application;

import bank.Bank;
import market.Market;
import restaurant.Restaurant;

public class Phonebook {
	public static Bank bank;
	public static Market market;
	public static Restaurant restaurant;
	
	//ChineseRestaurant chineseRestaurant;
	//ItalianRestaurant italianRestaurant;
	//MexicanRestaurant mexicanRestaurant;
	//AmericanRestaurant americanRestaurant;
	//SeafoodRestaurant seafoodRestaurant;
	
	Phonebook(Bank bank, Market market, Restaurant restaurant) {
		Phonebook.bank = bank;
		Phonebook.market = market;
		Phonebook.restaurant = restaurant;
		
		//this.chineseRestaurant = chineseRestaurant;
		//this.italianRestaurant = italianRestaurant;
		//this.mexicanRestaurant = mexicanRestaurant;
		//this.americanRestaurant = americanRestaurant;
		//this.seafoodRestaurant = seafoodRestaurant;
	}
}
