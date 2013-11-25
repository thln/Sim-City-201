package application.gui.animation;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import application.gui.animation.agentGui.*;

public class AnimationPanel extends JPanel implements MouseListener {

	final static int WINDOWX = 600;
	final static int WINDOWY = 300;

	CityPanel cityPanel;
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
			BuildingPanel bp = new BuildingPanel(b.getName(), this );
			b.setMyBuildingPanel(bp);
			buildingPanels.add(bp, bp.getName());

			add(BorderLayout.NORTH, cityPanel);
			add(BorderLayout.SOUTH, buildingPanels);
		}
		
		
		//UNCOMMENT THE FOLLOWING FOR TESTING ONLY!! re-comment to display final product
		testGuis();
		
		//testing mechanisms
		testbutton.addMouseListener(this);
    	testbutton.setSize(100, 50);
    	cityPanel.add(testbutton);
    	testbutton2.addMouseListener(this);
    	testbutton2.setBounds(100, 0, 100, 50);
    	cityPanel.add(testbutton2);
    	
	}

	public void displayBuildingPanel(BuildingPanel bp) {
		cardLayout.show(buildingPanels, bp.getName());
	}
	
	public void displayBlankBuildingPanel() {
		cardLayout.show(buildingPanels, "");
	}
	
	public void testGuis() {
		for(int i=0; i< buildings.size();i++) {
			if(buildings.get(i).getName().toLowerCase().contains("market")) {
			//	buildings.get(i).myBuildingPanel.addGui(market);
				buildings.get(i).myBuildingPanel.addGui(new MarketCustomerGui());
				buildings.get(i).myBuildingPanel.addGui(new MarketSalesPersonGui());
				buildings.get(i).myBuildingPanel.addGui(new MarketRunnerGui());
				buildings.get(i).myBuildingPanel.addGui(new MarketUPSmanGui());
			}
		}
		for(int i=0; i< buildings.size();i++) {
			if(buildings.get(i).getName().toLowerCase().contains("bank")) {
			//	buildings.get(i).myBuildingPanel.addGui(bank);
				buildings.get(i).myBuildingPanel.addGui(new BankCustomerGui());
				buildings.get(i).myBuildingPanel.addGui(new BankTellerGui());
				buildings.get(i).myBuildingPanel.addGui(new BankLoanerGui());
				buildings.get(i).myBuildingPanel.addGui(new BankGuardGui());
			}
		}
		for(int i=0; i< buildings.size();i++) {
			if(buildings.get(i).getName().toLowerCase().contains("house")) {
			//	buildings.get(i).myBuildingPanel.addGui(house);
				buildings.get(i).myBuildingPanel.addGui(new HouseRenterGui());
				buildings.get(i).myBuildingPanel.addGui(new HouseMaintenanceGui());
				buildings.get(i).myBuildingPanel.addGui(new HouseLandlordGui());
			}
		}
		
		for(int i=0; i< buildings.size();i++) {
			if(buildings.get(i).getName().toLowerCase().contains("restaurant")) {
				buildings.get(i).myBuildingPanel.addGui(restaurant);
				buildings.get(i).myBuildingPanel.addGui(new RestaurantCustomerGui());
			//	buildings.get(i).myBuildingPanel.addGui(new RestaurantCookGui());
				buildings.get(i).myBuildingPanel.addGui(new RestaurantWaiterGui());
			}
		}
		
		cityPanel.addGui(car);
		cityPanel.addGui(bus);
		cityPanel.addGui(person);
	}
	/*  OLD. Delete later?
		here we have the main city view
		cityPanel.setBounds(10, 20, WINDOWX, WINDOWY); //x & y positions in animation panel, x & y sizes
		cityPanel.addMouseListener(this);
		cityPanel.setVisible(true);
		add(cityPanel);
		
		buildingView.setBounds(10, 400, WINDOWX, WINDOWY);
		buildingView.setBackground(Color.CYAN);
		buildingView.setVisible(true);
		add(buildingView);
		
		stacking the building animations
		buildingView.setLayout(cardLayout);
		BuildingPanel blank = new BuildingPanel("name", this);
		buildingView.add(blank, "blank");
		
		buildings = cityPanel.getBuildings();
		for (int i=0; i<buildings.size(); i++) {
			Building b = buildings.get(i);
			BuildingPanel bp = new BuildingPanel(b.getName(), this);
			b.setBuildingPanel(bp);
			buildingView.add(bp, b.getName());
		}
*/
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
}
