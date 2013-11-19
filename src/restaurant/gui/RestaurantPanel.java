package restaurant.gui;

import restaurant.CashierAgent;
import restaurant.CookAgent;
import restaurant.CustomerAgent;
import restaurant.HostAgent;
import restaurant.MarketAgent;
import restaurant.WaiterAgent;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

/**
 * Panel in frame that contains all the restaurant information,
 * including host, cook, waiters, and customers.
 */
public class RestaurantPanel extends JPanel implements ActionListener {

	//Host, cook, waiters and customers
	private HostAgent host = new HostAgent("David");
	private CookAgent cook = new CookAgent("Iron Chef");
	private CookGui cookGui = new CookGui(cook);
	private CashierAgent cashier = new CashierAgent("Ca-Ching Ca-Ching");
	
	private JButton PauseButton = new JButton("Pause"); //Pause button
	private JButton UnPauseButton = new JButton("Restart"); //UnPause button
	private JButton	CheckInventoryButton = new JButton("Check Inventory");
	private JButton	KillInventoryButton = new JButton("Kill Inventory");
	
	private Vector<CustomerAgent> customers = new Vector<CustomerAgent>();
	private Vector<WaiterAgent> waiters = new Vector<WaiterAgent>();
	private Vector<MarketAgent> markets = new Vector<MarketAgent>();

	private JPanel restLabel = new JPanel();
	private ListPanel customerPanel = new ListPanel(this, "Customers");
	private ListPanel waiterPanel = new ListPanel(this, "Waiters");
	private ListPanel marketPanel = new ListPanel(this, "Markets");
	private JPanel group1 = new JPanel();
	private JPanel group2 = new JPanel();
	private JPanel group3 = new JPanel();
	private JPanel agentPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();

	private RestaurantGui gui; //reference to main gui

	public RestaurantPanel(RestaurantGui gui) {
		this.gui = gui;
		
		cook.setGui(cookGui);
		gui.animationPanel.addGui(cookGui);
		host.startThread();
		cook.startThread();
		cashier.startThread();

		setLayout(new BoxLayout((Container) this, BoxLayout.X_AXIS));
		//setLayout(new FlowLayout(2));
		
		//Restaurant Panel
		initRestLabel();
		restLabel.setAlignmentX(Component.TOP_ALIGNMENT);
		add(restLabel);
		
		//Customer Panel
		group1.add(waiterPanel);
		group1.setAlignmentX(Component.TOP_ALIGNMENT);
		agentPanel.add(group1);
		
		//Waiter Panel
		group2.add(customerPanel);
		group2.setAlignmentX(Component.TOP_ALIGNMENT);
		agentPanel.add(group2);
		
		//Market Panel
		group3.add(marketPanel);
		group3.setAlignmentX(Component.TOP_ALIGNMENT);
		agentPanel.add(group3);
		
		//Adding Agent Panel
		agentPanel.setAlignmentX(Component.TOP_ALIGNMENT);
		add(agentPanel);
		
		//Pause Button
		PauseButton.setBackground(Color.white);
		PauseButton.addActionListener(this);
		buttonPanel.add(PauseButton);

		//UnPause Button
		UnPauseButton.setBackground(Color.white);
		UnPauseButton.addActionListener(this);
		buttonPanel.add(UnPauseButton);
		
		//Check Inventory Button
		CheckInventoryButton.setBackground(Color.white);
		CheckInventoryButton.addActionListener(this);
		buttonPanel.add(CheckInventoryButton);
		
		//Kill Inventory Button
		KillInventoryButton.setBackground(Color.white);
		KillInventoryButton.addActionListener(this);
		buttonPanel.add(KillInventoryButton);
		
		//Button Panel
		buttonPanel.setLayout(new BoxLayout((Container) buttonPanel, BoxLayout.Y_AXIS));
		//buttonPanel.setAlignmentX(Component.TOP_ALIGNMENT);
		add(buttonPanel);

	}

	/**
	 * Sets up the restaurant label that includes the menu,
	 * and host and cook information
	 */
	private void initRestLabel() {
		JLabel label = new JLabel();

		restLabel.setLayout(new BoxLayout((Container)restLabel, BoxLayout.Y_AXIS));
		
		label.setText(
"<html><h3><u>Tonight's Staff</u></h3><table><tr><td>host:</td><td>" + host.getName() + "</td></tr></table><h3><u> Menu</u></h3><table><tr><td>Steak</td><td>$15.99</td></tr><tr><td>Chicken</td><td>$10.99</td></tr><tr><td>Salad</td><td>$5.99</td></tr><tr><td>Pizza</td><td>$8.99</td></tr></table><br></html>");
		
		restLabel.setBorder(BorderFactory.createTitledBorder("Welcome!"));
		restLabel.add(label, Component.CENTER_ALIGNMENT);
		restLabel.add(new JLabel("               "), Component.CENTER_ALIGNMENT);
		
		//Dimension restLabelDim = new Dimension(150, 300);
		//restLabel.setPreferredSize(restLabelDim);
		//restLabel.setMinimumSize(restLabelDim);
		//restLabel.setMaximumSize(restLabelDim);
	}

	/**
	 * When a customer or waiter is clicked, this function calls
	 * updatedInfoPanel() from the main gui so that person's information
	 * will be shown
	 *
	 * @param type indicates whether the person is a customer or waiter
	 * @param name name of person
	 */
	public void showInfo(String type, String name) {

		if (type.equals("Customers")) {

			for (int i = 0; i < customers.size(); i++) {
				CustomerAgent temp = customers.get(i);
				if (temp.getName() == name)
					gui.updateInfoPanel(temp);
			}
		}
		
		if (type.equals("Waiters")) {

			for (int i = 0; i < waiters.size(); i++) {
				WaiterAgent temp = waiters.get(i);
				if (temp.getName() == name)
					gui.updateInfoPanel(temp);
			}
		}
		
		if (type.equals("Markets")) {
			for (int i = 0; i < markets.size(); i++) {
				MarketAgent temp = markets.get(i);
				if (temp.getName() == name)
					gui.updateInfoPanel(temp);
			}
		}
		
	}

	/**
	 * Adds a customer or waiter to the appropriate list
	 *
	 * @param type indicates whether the person is a customer or waiter (later)
	 * @param name name of person
	 */

	//copy of addPerson with a bool for hungryChecked
	public void addPerson(String type, String name, boolean boxChecked) {

		if (type.equals("Customers")) {
			CustomerAgent c = new CustomerAgent(name);	

			c.setHost(host);
			c.setCashier(cashier);
			customers.add(c);
			
			if (customers.size() <= 12) {
				CustomerGui g = new CustomerGui(c, gui);
				if (boxChecked == true)
					g.setHungry();
				gui.animationPanel.addGui(g);
				c.setGui(g);
				g.setHomePosition((22 * customers.size()), 10);
			}
			else if (customers.size() <= 24) {
				CustomerGui g = new CustomerGui(c, gui);
				if (boxChecked == true)
					g.setHungry();
				gui.animationPanel.addGui(g);
				c.setGui(g);
				g.setHomePosition((22 * (customers.size() - 12)), 32);
			}
			else {
				System.err.println("You can only add 24 waiters.");
				customers.remove(c);
				return;
			}
			
			c.startThread();
		}
		
		if (type.equals("Waiters")) {
			WaiterAgent w = new WaiterAgent(name);	
			w.setHost(host);
			w.setCook(cook);
			w.setCookGui(cookGui);
			w.setCashier(cashier);
			waiters.add(w);
			
			if (waiters.size() <= 12) {
				WaiterGui g = new WaiterGui(w, gui);
				gui.animationPanel.addGui(g);
				w.setGui(g);
				g.setHomePosition(5, (55 + (22 * waiters.size())));
			}
			else if (waiters.size() <= 24) {
				WaiterGui g = new WaiterGui(w, gui);
				gui.animationPanel.addGui(g);
				w.setGui(g);
				g.setHomePosition(27, (55 + (22 * (waiters.size()-12))));
			}
			else {
				System.err.println("You can only add 24 waiters.");
				waiters.remove(w);
				return;
			}
			
			host.addWaiter(w);
			w.startThread();
		}
	}


	public void addPerson(String type, String name) {

		if (type.equals("Customers")) {
			CustomerAgent c = new CustomerAgent(name);	

			c.setHost(host);
			c.setCashier(cashier);
			customers.add(c);
			
			if (customers.size() <= 12) {
				CustomerGui g = new CustomerGui(c, gui);
				gui.animationPanel.addGui(g);
				c.setGui(g);
				g.setHomePosition((22 * customers.size()), 10);
			}
			else if (customers.size() <= 24) {
				CustomerGui g = new CustomerGui(c, gui);
				gui.animationPanel.addGui(g);
				c.setGui(g);
				g.setHomePosition((22 * (customers.size() - 12)), 32);
			}
			else {
				System.err.println("You can only add 24 waiters.");
				customers.remove(c);
				return;
			}
			
			c.startThread();
		}
		
		if (type.equals("Waiters")) {
			WaiterAgent w = new WaiterAgent(name);	
			w.setHost(host);
			w.setCook(cook);
			w.setCookGui(cookGui);
			w.setCashier(cashier);
			waiters.add(w);
			
			if (waiters.size() <= 12) {
				WaiterGui g = new WaiterGui(w, gui);
				gui.animationPanel.addGui(g);
				w.setGui(g);
				g.setHomePosition(5, (55 + (22 * waiters.size())));
			}
			else if (waiters.size() <= 24) {
				WaiterGui g = new WaiterGui(w, gui);
				gui.animationPanel.addGui(g);
				w.setGui(g);
				g.setHomePosition(27, (55 + (22 * (waiters.size()-12))));
			}
			else {
				System.err.println("You can only add 24 waiters.");
				waiters.remove(w);
				return;
			}
			
			host.addWaiter(w);
			w.startThread();
		}
		
		if (type.equals("Markets")) {
			MarketAgent m = new MarketAgent(name, cook, cashier);
			markets.add(m);
			cook.addMarket(m);
			m.startThread();
		}
	}


	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == PauseButton) {
			//This will pause the animation.
			System.err.println("PAUSED");
			host.pause();
			cook.pause();
			cashier.pause();
			for (WaiterAgent waiter: waiters) {
				waiter.pause();
			}
			
			for (CustomerAgent customer: customers) {
				customer.pause();
			}
			
			for (MarketAgent market: markets) {
				market.pause();
			}
		}

		if (e.getSource() == UnPauseButton) {
			//This will restart the animation.
			System.err.println("RESTART");
			host.restart();
			cook.restart();
			cashier.restart();
			for (WaiterAgent waiter: waiters) {
				waiter.restart();
			}
			
			for (CustomerAgent customer: customers) {
				customer.restart();
			}
			
			for (MarketAgent market: markets) {
				market.restart();
			}
		}
		
		if (e.getSource() == CheckInventoryButton) {
			cook.checkInventory();
		}
		
		if (e.getSource() == KillInventoryButton) {
			cook.deleteInventory();
		}
		
	}

}
