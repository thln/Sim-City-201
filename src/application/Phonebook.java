package application;

import bank.Bank;
import market.Market;
import restaurant.Restaurant;

public class Phonebook {
	public Bank bank;
	public Market market;
	public Restaurant restaurant;
	
	//ChineseRestaurant chineseRestaurant;
	//ItalianRestaurant italianRestaurant;
	//MexicanRestaurant mexicanRestaurant;
	//AmericanRestaurant americanRestaurant;
	//SeafoodRestaurant seafoodRestaurant;
	
	Phonebook(Bank bank, Market market, Restaurant restaurant) {
		this.bank = bank;
		this.market = market;
		this.restaurant = restaurant;
		
		//this.chineseRestaurant = chineseRestaurant;
		//this.italianRestaurant = italianRestaurant;
		//this.mexicanRestaurant = mexicanRestaurant;
		//this.americanRestaurant = americanRestaurant;
		//this.seafoodRestaurant = seafoodRestaurant;
	}
}
