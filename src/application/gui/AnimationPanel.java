package application.gui;

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
	public List<JPanel> buildingPanels = new ArrayList<JPanel>();
	public List<JLabel> imglabels = new ArrayList<JLabel>();
	public Map<JLabel, JPanel> buildingMap = new HashMap<JLabel, JPanel>();
	
	//BufferedImage bankicon = null;
	ImageIcon bank = new ImageIcon("docs/bank.png", "bank icon");
	ImageIcon restaurant = new ImageIcon("docs/restaurant.png", "restaurant icon");
	ImageIcon market = new ImageIcon("docs/market.png", "market icon");
	ImageIcon house = new ImageIcon("docs/house.png", "house icon");
	
	final static int WINDOWX = 570;
    final static int WINDOWY = 360;
	
	AnimationPanel(){
		
		setLayout(null);
		setBorder(BorderFactory.createTitledBorder("Animation"));
    	//here we have the main city view
    	
		cityPanel.setBounds(10, 20, WINDOWX, WINDOWY); //x & y positions in animation panel, x & y sizes
		cityPanel.addMouseListener(this);
    	cityPanel.setVisible(true);
    	add(cityPanel);
    	
    	//stacking the building animations

    	ImageIcon icon = new ImageIcon("Team Project/docs/bank.jpg",
                "a pretty but meaningless splat");

    	ImageIcon icon2 = new ImageIcon("bank.jpg", "bank icon");

    	//instantiating the bank icon and panel
    	BuildingPanel bankPanel = new BuildingPanel("Bank");
    	JLabel bankLabel = new JLabel(bank);
    	buildingPanels.add(bankPanel);
    	imglabels.add(bankLabel);
    	buildingMap.put(bankLabel, bankPanel);
    	Dimension banksize = bankLabel.getPreferredSize();
    	bankLabel.setBounds(WINDOWX - banksize.width, 150, banksize.width, banksize.height);

    	//instantiating the restaurant icons and panels
    	for(int i=0; i < 5; i++) {
    		BuildingPanel restPanel = new BuildingPanel("Restaurant " + (i+1));
    		buildingPanels.add(restPanel);
    		JLabel restLabel = new JLabel(restaurant);
    		imglabels.add(restLabel);
    		buildingMap.put(restLabel, restPanel);
    		
    		Dimension size = restLabel.getPreferredSize();
    		restLabel.setBounds(size.width*i+150, 100, size.width, size.height);
    	}
    	
    	//instantiating the market icons and panels
    	for(int j=0; j < 3; j++) {
    		BuildingPanel marketPanel = new BuildingPanel("Market " + (j+1));
    		buildingPanels.add(marketPanel);
    		JLabel marketLabel = new JLabel(market);
    		imglabels.add(marketLabel);
    		buildingMap.put(marketLabel, marketPanel);
    		
    		Dimension size = marketLabel.getPreferredSize();
    		marketLabel.setBounds(size.width*j + 150, 200, size.width, size.height);
    	}
    	
    	//instantiating the house icons and panels
    	for(int k=0; k < 20; k++) {
    		BuildingPanel housePanel = new BuildingPanel("House " + (k+1));
    		buildingPanels.add(housePanel);
    		JLabel houseLabel = new JLabel(house);
    		imglabels.add(houseLabel);
    		buildingMap.put(houseLabel, housePanel);
    		
    		Dimension size = houseLabel.getPreferredSize();
    		if(k < 10)
    			houseLabel.setBounds(size.width*(k), 0, size.width, size.height);
    		else
    			houseLabel.setBounds(size.width*(k-10), WINDOWY - size.height, size.width, size.height);
    	}
    	
    	//positioning the city building icons
    	for(int n=0; n<imglabels.size();n++) {
    		cityPanel.add(imglabels.get(n));
    		imglabels.get(n).addMouseListener(this);
    	}
    	
    	//positioning the building panels into the same spot
    	for(int m=0; m<buildingPanels.size();m++) {
    		buildingPanels.get(m).setBounds(10, 400, WINDOWX, WINDOWY);
    		add(buildingPanels.get(m));
    	}
    	clearScreen();
	}

	public void clearScreen() {
		for(int n=0; n<buildingPanels.size();n++) {
			buildingPanels.get(n).setVisible(false);
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		//If any of the icons are clicked, it will find and display its corresponding animation panel
		for(int i=0; i<imglabels.size();i++) {
			if(e.getSource() == imglabels.get(i)) {
				//final String name = imglabels.get(i).getText();
				final String name = imglabels.get(i).getIcon().toString();
				System.out.println(name + " clicked " + buildingMap.get(imglabels.get(i)));
			}
			if(e.getSource().equals(imglabels.get(i))) {
				clearScreen();
				buildingMap.get(imglabels.get(i)).setVisible(true);
			}
		}
		if(e.getSource().equals(cityPanel)) {
			//System.out.println("Screen Cleared");
			clearScreen();
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
}
