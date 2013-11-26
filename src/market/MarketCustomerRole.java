package market;

import java.util.Random;

import market.interfaces.MarketCustomer;
import application.Phonebook;
import application.gui.animation.agentGui.MarketCustomerGui;
import person.Person;
import person.Role;
import testing.LoggedEvent;
import testing.EventLog;

public class MarketCustomerRole extends Role implements MarketCustomer {
	protected String roleName = "Market Customer";

	public EventLog log = new EventLog();

	MarketCustomerGui marketCustomerGui = (MarketCustomerGui) gui;

	//Data
	public enum MarketCustomerState {atMarket, waitingForOrders, recievedOrders, payed, disputingBill}
	public MarketCustomerState state = MarketCustomerState.atMarket;

	public double bill = 0;
	String item;
	int itemAmount;
	String name;

	public MarketCustomerRole(Person person, String pName, String rName) {
		super(person, pName, rName);
	}

	//Messages
	public void msgComeIn() {
		stateChanged();
	}

	public void msgHereAreYourThings(String item, int itemAmount, double orderCost) {
		state = MarketCustomerState.recievedOrders;
		this.item = item;
		this.itemAmount = itemAmount;
		bill = orderCost;
		System.err.print(bill);
		log.add(new LoggedEvent("Recieved msgHereAreYourThings"));
		stateChanged();
	}

	//Scheduler
	@Override
	public boolean pickAndExecuteAnAction() {
		if (state == MarketCustomerState.atMarket) {
			msgSalesPerson();
			return true;
		}
		if (state == MarketCustomerState.recievedOrders) {
			payBill();
			return true;
		}

		if (state == MarketCustomerState.payed) {
			exitMarket();
			return true;
		}

		return false;
	}

	//Actions
	public void msgSalesPerson() {
		if(!Phonebook.getPhonebook().getMarket().isOpen()) {
			print("Waiting for the market to open");
			return;
		}

		if (item == "Car") {
			Phonebook.getPhonebook().getMarket().salesPersonRole.msgIWantProducts(this, "Car", 1);
			print("Arrived at the market");
			state = MarketCustomerState.waitingForOrders;
			return;
		}
		item = chooseMarketItem();
		Phonebook.getPhonebook().getMarket().salesPersonRole.msgIWantProducts(this, item, 3);
		print("Asking sales person for: " + item);
		state = MarketCustomerState.waitingForOrders;

	}

	private String chooseMarketItem() {
		Random rand = new Random();
		int myRandomChoice;
		String item;
		do {
			myRandomChoice = rand.nextInt(10);
			myRandomChoice %= 7;
		} while (!Phonebook.getPhonebook().getMarket().marketItemsForSale.containsKey(myRandomChoice) || (person.money < Phonebook.getPhonebook().getMarket().marketItemsForSale.get(myRandomChoice).price));
		item = Phonebook.getPhonebook().getMarket().marketItemsForSale.get(myRandomChoice).itemName;
		return item;
	}

	public void payBill(){
		if (bill == Phonebook.getPhonebook().getMarket().inventory.get(item).price * itemAmount) {
			print("Paying my bill");
			Phonebook.getPhonebook().getMarket().getSalesPerson(test).msgPayment(this, bill);
			person.money -= bill;
			state = MarketCustomerState.payed;
		}
		else {
			//message market that bill was wrong
			//for now leaving market
			exitMarket();
		}
	}

	public void setItem(String item) {
		this.item = item;
	}

	public void exitMarket() {
		print("Leaving Market");
		state = MarketCustomerState.atMarket;
		this.setRoleInactive();
	}
}
