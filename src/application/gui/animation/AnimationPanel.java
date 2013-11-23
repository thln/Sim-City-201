package application.gui.animation;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.io.*;

import javax.imageio.*;

import java.awt.image.BufferedImage;
import application.gui.animation.agentGui.*;

public class AnimationPanel extends JPanel implements MouseListener {
	
	CityPanel cityPanel = new CityPanel();
	JPanel buildingView = new JPanel();
	final static int WINDOWX = 570;
    final static int WINDOWY = 360;
    CardLayout cardLayout = new CardLayout();
    List<Building> buildings;
    
    //testing mechanisms
    JButton testbutton = new JButton("test");
    JButton testbutton2 = new JButton("test2");
    MarketUPSmanGui market = new MarketUPSmanGui();
    
	public AnimationPanel(){
		
		setLayout(null);
		setBorder(BorderFactory.createTitledBorder("Animation"));
    	//here we have the main city view
		cityPanel.setBounds(10, 20, WINDOWX, WINDOWY); //x & y positions in animation panel, x & y sizes
		cityPanel.addMouseListener(this);
    	cityPanel.setVisible(true);
    	add(cityPanel);
    	
    	sampleLayout();
    	
    	buildingView.setBounds(10, 400, WINDOWX, WINDOWY);
    	buildingView.setBackground(Color.CYAN);
    	buildingView.setVisible(true);
    	add(buildingView);
    	
    	//stacking the building animations
    	buildingView.setLayout(cardLayout);
    	BuildingPanel blank = new BuildingPanel("name", this);
    	//buildingView.add(blank, "blank");
    	
    	buildings = cityPanel.getBuildings();
    	for (int i=0; i<buildings.size(); i++) {
    		Building b = buildings.get(i);
    		BuildingPanel bp = new BuildingPanel(b.getName(), this);
    		b.setBuildingPanel(bp);
    		buildingView.add(bp, b.getName());
    	}
    	//testGuis();
    	testbutton.addMouseListener(this);
    	testbutton.setSize(100, 50);
    	cityPanel.add(testbutton);
    	testbutton2.addMouseListener(this);
    	testbutton2.setBounds(100, 0, 100, 50);
    	cityPanel.add(testbutton2);
	}
	
	public void displayBuildingPanel( BuildingPanel bp ) {
        cardLayout.show(buildingView, bp.getName() );
	}
	
	public void sampleLayout() {
		cityPanel.addBuilding("Bank", 1);
		
    	cityPanel.addBuilding("Market", 1);
    	cityPanel.addBuilding("Market", 2);
    	
    	for(int j=0; j<5; j++) {
    		cityPanel.addBuilding("Restaurant", j+1);
    	}
	}
	
	public void testGuis() {
		
		for(int i=0; i< buildings.size();i++) {
			if(buildings.get(i).getName().contains("Market")) {
				buildings.get(i).myBuildingPanel.addGui(market);
				buildings.get(i).myBuildingPanel.addGui(new MarketCustomerGui());
				buildings.get(i).myBuildingPanel.addGui(new MarketSalesPersonGui());
				buildings.get(i).myBuildingPanel.addGui(new MarketRunnerGui());
			}
		}
		/*
		BuildingPanel bp = new BuildingPanel("Market 1", this);
		buildingView.add(bp, bp.getName());
		bp.addGui(new MarketCustomerGui());
		*/
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(cityPanel)) {
			cardLayout.show(buildingView, "blank");
		}
		if(e.getSource().equals(testbutton)) {
			//market.DoDeliverOrder();
		}
		if(e.getSource().equals(testbutton2)) {
		//	market.DoGoToSalesPerson();
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
