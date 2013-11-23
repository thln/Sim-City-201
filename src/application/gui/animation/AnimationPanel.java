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

public class AnimationPanel extends JPanel implements MouseListener {
	
	CityPanel cityPanel = new CityPanel();
	JPanel buildingView = new JPanel();
	//public List<JPanel> buildingPanels = new ArrayList<JPanel>();
	public List<JLabel> imglabels = new ArrayList<JLabel>();
	public Map<JLabel, JPanel> buildingMap = new HashMap<JLabel, JPanel>();
	final static int WINDOWX = 570;
    final static int WINDOWY = 360;
    CardLayout cardLayout = new CardLayout();
	
	public AnimationPanel(){
		
		setLayout(null);
		setBorder(BorderFactory.createTitledBorder("Animation"));
    	//here we have the main city view
		cityPanel.setBounds(10, 20, WINDOWX, WINDOWY); //x & y positions in animation panel, x & y sizes
		cityPanel.addMouseListener(this);
    	cityPanel.setVisible(true);
    	add(cityPanel);
    	
    	cityPanel.addBuilding("bank", 1);
    	cityPanel.addBuilding("market", 1);
    	cityPanel.addBuilding("market", 2);
    	for(int j=0; j<5; j++) {
    		cityPanel.addBuilding("restaurant", j+1);
    	}
    	
    	buildingView.setBounds(10, 400, WINDOWX, WINDOWY);
    	buildingView.setBackground(Color.CYAN);
    	buildingView.setVisible(true);
    	add(buildingView);
    	
    	//stacking the building animations
    	buildingView.setLayout(cardLayout);
    	BuildingPanel blank = new BuildingPanel("name", this);
    	buildingView.add(blank, "blank");
    	List<Building> buildings = cityPanel.getBuildings();
    	for (int i=0; i<buildings.size(); i++) {
    		Building b = buildings.get(i);
    		BuildingPanel bp = new BuildingPanel(b.getName(), this);
    		b.setBuildingPanel(bp);
    		buildingView.add(bp, b.getName());
    	}
    	
	}
	
	public void displayBuildingPanel( BuildingPanel bp ) {
        cardLayout.show(buildingView, bp.getName() );
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(cityPanel)) {
			cardLayout.show(buildingView, "blank");
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
