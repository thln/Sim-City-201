package restaurant.gui;

import restaurant.CustomerAgent;
import restaurant.MarketAgent;
import restaurant.WaiterAgent;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
/**
 * Main GUI class.
 * Contains the main frame and subsequent panels
 */
public class RestaurantGui extends JFrame implements ActionListener {
	/* The GUI has two frames, the control frame (in variable gui) 
	 * and the animation frame, (in variable animationFrame within gui)
	 */
	AnimationPanel animationPanel = new AnimationPanel();

	/* restPanel holds 2 panels
	 * 1) the staff listing, menu, and lists of current customers all constructed
	 *    in RestaurantPanel()
	 * 2) the infoPanel about the clicked Customer (created just below)
	 */    
	private RestaurantPanel restPanel = new RestaurantPanel(this);

	/* infoPanel holds information about the clicked customer, if there is one*/
	private JPanel infoPanel;
	private JLabel infoLabel; //part of infoPanel
	private JCheckBox stateCB;//part of infoLabel

	
	private Object currentPerson;/* Holds the agent that the info is about.
    								Seems like a hack */





	/**
	 * Constructor for RestaurantGui class.
	 * Sets up all the gui components.
	 */
	public RestaurantGui() {
		int WINDOWX = 450;
		int WINDOWY = 350;

		setBounds(50, 5, (int) (WINDOWX * 2), (int) (WINDOWY * 2.05));

		setLayout(new BoxLayout((Container) getContentPane(), 
				BoxLayout.Y_AXIS));

		//adding restaurant panel
		Dimension restDim = new Dimension((int) (WINDOWX * 2), (int) (WINDOWY * .7));
		restPanel.setPreferredSize(restDim);
		restPanel.setMinimumSize(restDim);
		restPanel.setMaximumSize(restDim);
		add(restPanel);

		//adding info panel
		Dimension infoDim = new Dimension((int) (WINDOWX * 1.9), (int) (WINDOWY * .2));
		infoPanel = new JPanel();
		infoPanel.setPreferredSize(infoDim);
		infoPanel.setMinimumSize(infoDim);
		infoPanel.setMaximumSize(infoDim);
		infoPanel.setBorder(BorderFactory.createTitledBorder("Information"));


		stateCB = new JCheckBox();
		stateCB.setVisible(false);
		stateCB.addActionListener(this);
		
		infoPanel.setLayout(new BorderLayout(10, 10));


		infoLabel = new JLabel(); 
		//infoLabel.setText("<html><pre><i>Click Add to make customers</i></pre></html>");


		infoPanel.add(infoLabel, BorderLayout.WEST);
		infoPanel.add(stateCB, BorderLayout.CENTER);

		add(infoPanel);

		//adding animation panel
		Dimension animationDim = new Dimension(WINDOWX, WINDOWY);
		animationPanel.setPreferredSize(animationDim);
		animationPanel.setMinimumSize(animationDim);
		animationPanel.setMaximumSize(animationDim);
		animationPanel.setBorder(BorderFactory.createTitledBorder("Animation"));
		add(animationPanel);
		


	}
	/**
	 * updateInfoPanel() takes the given customer (or, for v3, Host) object and
	 * changes the information panel to hold that person's info.
	 *
	 * @param person customer (or waiter) object
	 */
	public void updateInfoPanel(Object person) {

		stateCB.setVisible(true);
		
		currentPerson = person;

		if (person instanceof CustomerAgent) {
			CustomerAgent customer = (CustomerAgent) person;
			stateCB.setText("Hungry?");
			stateCB.setSelected(customer.getGui().isHungry());
			stateCB.setEnabled(!customer.getGui().isHungry());
			infoLabel.setText(
					"<html><pre>     Name: " + customer.getName() + "      Money: " + customer.getMoney() + " </pre></html>");
		}
		
		if (person instanceof WaiterAgent) {
			WaiterAgent waiter = (WaiterAgent) person;
			stateCB.setText("On Break");
			stateCB.setSelected(waiter.getGui().isOnBreak());
			stateCB.setEnabled(!waiter.getGui().isOnBreak());

			infoLabel.setText(
				"<html><pre>     Name: " + waiter.getName() + " </pre></html>");
		}
		
		if (person instanceof MarketAgent) {
			stateCB.setVisible(false);
			MarketAgent market = (MarketAgent) person;
			infoLabel.setText(
				"<html><pre>     Name: " + market.getName() + "      Steak: " + market.getSteak() + "      Chicken: " + market.getChicken() + "      Salad: " + market.getSalad()+ "      Pizza: " + market.getPizza() + " </pre></html>");
		}
		
		infoPanel.validate();

	}
	
	/**
	 * Action listener method that reacts to the checkbox being clicked;
	 * If it's the customer's checkbox, it will make him hungry
	 * For v3, it will propose a break for the waiter.
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == stateCB) {
			if (currentPerson instanceof CustomerAgent) {
				CustomerAgent c = (CustomerAgent) currentPerson;
				c.getGui().setHungry();
				stateCB.setEnabled(false);
			}
			
			if (currentPerson instanceof WaiterAgent) {
				WaiterAgent w = (WaiterAgent) currentPerson;
				if (stateCB.isSelected()) {
					w.getGui().askForBreak();
				}
				else {
					w.getGui().setOffBreak();
				}
			}
		}
	}
	/**
	 * Message sent from a customer gui to enable that customer's
	 * "I'm hungry" checkbox.
	 *
	 * @param c reference to the customer
	 */
	public void setCustomerEnabled(CustomerAgent c) {
		if (currentPerson instanceof CustomerAgent) {
			CustomerAgent cust = (CustomerAgent) currentPerson;
			if (c.equals(cust)) {
				stateCB.setEnabled(true);
				stateCB.setSelected(false);
			}
		}
	}
	
	public void enableOffBreakBox(WaiterAgent w) {
		if (currentPerson instanceof WaiterAgent) {
			WaiterAgent wait = (WaiterAgent) currentPerson;
			if (w.equals(wait)) {
				stateCB.setEnabled(true);
				stateCB.setSelected(true);
			}
		}
	}
	
	public void enableOnBreakBox(WaiterAgent w) {
		if (currentPerson instanceof WaiterAgent) {
			WaiterAgent wait = (WaiterAgent) currentPerson;
			if (w.equals(wait)) {
				stateCB.setEnabled(true);
				stateCB.setSelected(false);
			}
		}
	}
	
	public void enableOnBreakBoxSelected(WaiterAgent w) {
		if (currentPerson instanceof WaiterAgent) {
			WaiterAgent wait = (WaiterAgent) currentPerson;
			if (w.equals(wait)) {
				stateCB.setEnabled(true);
				stateCB.setSelected(true);
			}
		}
	}
	
	/**
	 * Main routine to get gui started
	 */
	public static void main(String[] args) {
		RestaurantGui gui = new RestaurantGui();
		gui.setTitle("csci201 Restaurant");
		gui.setVisible(true);
		gui.setResizable(false);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
