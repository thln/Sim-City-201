package application.gui.animation;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import application.Phonebook;
import housing.*;
import application.gui.animation.agentGui.*;

public class AnimationPanel extends JPanel implements MouseListener {

	final static int WINDOWX = 600;
	final static int WINDOWY = 325;

	public CityPanel cityPanel;
	JPanel buildingPanels;
	CardLayout cardLayout;
	ArrayList<Building> buildings;

	//begin list of mechanisms for testing agent guis
	JButton testbutton = new JButton("test");
	JButton testbutton2 = new JButton("test2");
	MarketRunnerGui market = new MarketRunnerGui();
	BankLoanerGui bank = new BankLoanerGui();
	HouseMaintenanceGui house = new HouseMaintenanceGui();
	RestaurantCookGui restaurant = new RestaurantCookGui();
	BusGui bus = new BusGui();
	CarGui car = new CarGui();
	PersonGui person = new PersonGui();
	//end list of testing mechanisms

	public AnimationPanel() {
		setVisible(true);
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder("Animation"));

		//here we have the main city view
		cityPanel = new CityPanel(this);
		cityPanel.setPreferredSize(new Dimension(WINDOWX, WINDOWY));
		cityPanel.setMaximumSize(new Dimension(WINDOWX,WINDOWY));
		cityPanel.setMinimumSize(new Dimension(WINDOWX, WINDOWY));
		cityPanel.setVisible(true);

		cardLayout = new CardLayout();

		buildingPanels = new JPanel();
		buildingPanels.setLayout(cardLayout);
		buildingPanels.setMinimumSize(new Dimension(WINDOWX, WINDOWY));
		buildingPanels.setMaximumSize(new Dimension(WINDOWX, WINDOWY));
		buildingPanels.setPreferredSize(new Dimension(WINDOWX, WINDOWY));

		//Adding a blank building panel
		BuildingPanel blank = new BuildingPanel("", this);
		buildingPanels.add(blank, blank.getName());

		//Create the BuildingPanel for each Building object
		buildings = cityPanel.getBuildings();
		for ( int i=0; i<buildings.size(); i++ ) {
			Building b = buildings.get(i);
			String name = b.getName();
			BuildingPanel panel = null;
			if(name.toLowerCase().contains("restaurant")) {
				panel = new RestaurantPanel(name, this );
			}
			else if(name.toLowerCase().contains("market")) {
				panel = new MarketPanel(name, this );
			}
			else if(name.toLowerCase().contains("house") || name.toLowerCase().contains("apartment")) {
				panel = new HousingPanel(name, this );
			}
			else if(name.toLowerCase().contains("bank")) {
				panel = new BankPanel(name, this );
			}
			else if(name.toLowerCase().contains("park")){
				panel = new ParkPanel(name, this);
			}
			
			b.setMyBuildingPanel(panel);
			setBuildingInPhonebook(b);
			buildingPanels.add(panel, name);
			add(BorderLayout.NORTH, cityPanel);
			add(BorderLayout.SOUTH, buildingPanels);
		}

		/*
		//UNCOMMENT THE FOLLOWING FOR TESTING ONLY!! re-comment to display final product
		testGuis();

		//testing mechanisms
		testbutton.addMouseListener(this);
    	testbutton.setSize(100, 50);
    	cityPanel.add(testbutton);
    	testbutton2.addMouseListener(this);
    	testbutton2.setBounds(100, 0, 100, 50);
    	cityPanel.add(testbutton2);
		 */
		//testGuisTwo();
	}

	public void displayBuildingPanel(BuildingPanel bp) {
		cardLayout.show(buildingPanels, bp.getName());
	}

	public void displayBlankBuildingPanel() {
		cardLayout.show(buildingPanels, "");
	}

	public void setBuildingInPhonebook(Building building) {
		//Phonebook.getPhonebook().getBusinessFromGui(building);
		
		/* This only allows for ONE of each type of building. In v2,
		 * it is required to have multiple instances of each, so we
		 * must code for that eventuality.
		 */
		//setting the panels to the already-initialized businesses
		if (building.getName().toLowerCase().contains("chinese")) {
			Phonebook.getPhonebook().getChineseRestaurant().setBuildingPanel(building.myBuildingPanel);
		}
		if (building.getName().toLowerCase().contains("market")) {
			Phonebook.getPhonebook().getEastMarket().setBuildingPanel(building.myBuildingPanel);
		}
		if (building.getName().toLowerCase().contains("bank")) {
			Phonebook.getPhonebook().getEastBank().setBuildingPanel(building.myBuildingPanel);
		}
	}

	public void testGuis() {
		for(Building building : buildings) {
			if(building.getName().toLowerCase().contains("market")) {
				//	building.myBuildingPanel.addGui(market);
				building.myBuildingPanel.addGui(new MarketCustomerGui());
				building.myBuildingPanel.addGui(new MarketSalesPersonGui());
				building.myBuildingPanel.addGui(new MarketRunnerGui());
				building.myBuildingPanel.addGui(new MarketUPSmanGui());
			}
			if(building.getName().toLowerCase().contains("bank")) {
				//	building.myBuildingPanel.addGui(bank);
				building.myBuildingPanel.addGui(new BankCustomerGui());
				building.myBuildingPanel.addGui(new BankTellerGui());
				building.myBuildingPanel.addGui(new BankLoanerGui());
				building.myBuildingPanel.addGui(new BankGuardGui());
			}
			if(building.getName().toLowerCase().contains("house")) {
				//	building.myBuildingPanel.addGui(house);
				building.myBuildingPanel.addGui(new HouseRenterGui());
				building.myBuildingPanel.addGui(new HouseMaintenanceGui());
				building.myBuildingPanel.addGui(new HouseLandlordGui());
			}
			if(building.getName().toLowerCase().contains("restaurant")) {
				//	building.myBuildingPanel.addGui(restaurant);
				building.myBuildingPanel.addGui(new RestaurantCustomerGui());
				building.myBuildingPanel.addGui(new RestaurantCookGui());
				building.myBuildingPanel.addGui(new RestaurantWaiterGui());
			}
		}

		cityPanel.addGui(car);
		cityPanel.addGui(bus);
		cityPanel.addGui(person);

	}

	public void testGuisTwo() {
		ArrayList<Gui> guis = new ArrayList<Gui>();
		guis.add(new MarketCustomerGui());
		guis.add(new MarketSalesPersonGui());
		guis.add(new MarketRunnerGui());
		guis.add(new MarketUPSmanGui());
		guis.add(new BankCustomerGui());
		guis.add(new BankTellerGui());
		guis.add(new BankLoanerGui());
		guis.add(new BankGuardGui());
		guis.add(new HouseRenterGui());
		guis.add(new HouseMaintenanceGui());
		guis.add(new HouseLandlordGui());
		guis.add(new RestaurantCustomerGui());
		guis.add(new RestaurantCookGui());
		guis.add(new RestaurantWaiterGui());
		guis.add(new CarGui());
		guis.add(new BusGui());
		guis.add(new PersonGui());

		BuildingPanel marketPanel = null;
		BuildingPanel bankPanel = null;
		BuildingPanel housePanel = null;
		BuildingPanel restaurantPanel = null;

		for(Building building : buildings) {
			if(building.getName().toLowerCase().contains("market")) {
				marketPanel = building.myBuildingPanel;
			}
			if(building.getName().toLowerCase().contains("bank")) {
				bankPanel = building.myBuildingPanel;
			}
			if(building.getName().toLowerCase().contains("house")) {
				housePanel = building.myBuildingPanel;
			}
			if(building.getName().toLowerCase().contains("restaurant")) {
				restaurantPanel = building.myBuildingPanel;
			}
		}
		for(Gui gui : guis) {
			if(gui instanceof CityGui) {
				cityPanel.addGui(gui);
			}
			if(gui instanceof BankGui) {
				bankPanel.addGui(gui);
			}
			if(gui instanceof MarketGui) {
				marketPanel.addGui(gui);
			}
			if(gui instanceof HouseGui) {
				housePanel.addGui(gui);
			}
			if(gui instanceof RestaurantGui) {
				restaurantPanel.addGui(gui);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(testbutton)) {
			restaurant.DoGoToGrill();
		}
		if(e.getSource().equals(testbutton2)) {
			restaurant.DoPickUpFood();
		}
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public String toString() {
		return "Animation Panel";
	}

	public ArrayList<Building> getBuildings() {
		return buildings;
	}

	//to be used for the adding the people and transportation guis to the city Panel
	public void addGui(Gui gui) {
		if (gui instanceof CityGui) {
			cityPanel.addGui(gui);
		}
		
	}
	
	public int getWindowX(){
		return WINDOWX;
	}
	public int getWindowY(){
		return WINDOWY;
	}
	
	public void addAptUnit(HousingPanel house, Housing housing) {
		buildingPanels.add(house, house.name);
		housing.setBuildingPanel(house);
		//getting the apartment panel from the list of buildings (and thier respective panels)
		for(Building building : buildings) {
			String name = building.getName();
			if(name.toLowerCase().contains("apartment")) {
			//	if(name.toLowerCase().contains("north")) { //east?
					HousingPanel hp = (HousingPanel) building.myBuildingPanel;
					hp.addAptUnit(housing);
					house.setAptBuilding(building);
			/*	}
				else if(name.toLowerCase().contains("south")) { //west?
					HousingPanel hp = (HousingPanel) building.myBuildingPanel;
					hp.addAptUnit(housing);
				}
			*/
			}
		}
	}
}
