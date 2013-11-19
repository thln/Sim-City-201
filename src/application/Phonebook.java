package application;

import housing.Housing;

import java.util.ArrayList;

import bank.Bank;
import market.Market;
import restaurant.Restaurant;

public class Phonebook {

	public static ArrayList<Housing> publicAllHousing = new ArrayList<Housing>();

	public static Bank bank;
	public static Market market;
	public static Restaurant restaurant;

	
	//ChineseRestaurant chineseRestaurant;
	//ItalianRestaurant italianRestaurant;
	//MexicanRestaurant mexicanRestaurant;
	//AmericanRestaurant americanRestaurant;
	//SeafoodRestaurant seafoodRestaurant;
	
	Phonebook(Bank bank, Market market, Restaurant restaurant, ArrayList<Housing> passHousing) {
		Phonebook.bank = bank;
		Phonebook.market = market;
		Phonebook.restaurant = restaurant;
		Phonebook.publicAllHousing = passHousing;
		
		//this.chineseRestaurant = chineseRestaurant;
		//this.italianRestaurant = italianRestaurant;
		//this.mexicanRestaurant = mexicanRestaurant;
		//this.americanRestaurant = americanRestaurant;
		//this.seafoodRestaurant = seafoodRestaurant;
	}
}
