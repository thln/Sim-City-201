package application;

import housing.Housing;

import java.util.ArrayList;
import java.util.List;

import bank.Bank;
import market.Market;
import restaurant.Restaurant;

public class Phonebook {

	public static List<Housing> publicAllHousing; //= Collections.synchronizedList(new ArrayList<Housing>());

	public static Bank bank;
	public static Market market;
	public static Restaurant restaurant;

	
	//ChineseRestaurant chineseRestaurant;
	//ItalianRestaurant italianRestaurant;
	//MexicanRestaurant mexicanRestaurant;
	//AmericanRestaurant americanRestaurant;
	//SeafoodRestaurant seafoodRestaurant;
	
	Phonebook(Bank bank, Market market, Restaurant restaurant, List<Housing> passHousing) {
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
