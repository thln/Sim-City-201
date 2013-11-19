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
	ImageIcon bank = new ImageIcon("docs/bank.jpg", "bank icon");
	ImageIcon restaurant = new ImageIcon("docs/restaurant.jpg", "restaurant icon");
	ImageIcon market = new ImageIcon("docs/bank.jpg", "market icon");
	ImageIcon house = new ImageIcon("docs/house.jpg", "house icon");
	
	AnimationPanel(){
		
		//Dimension cityDim = new Dimension(getWidth(), getHeight());
		setLayout(null);
		setBorder(BorderFactory.createTitledBorder("Animation"));
    	//here we have the main city view
    	
		cityPanel.setBounds(10, 20, 570, 360); //x & y positions in animation panel, x & y sizes
		/*
    	cityPanel.setPreferredSize(cityDim);
    	cityPanel.setMinimumSize(cityDim);
    	cityPanel.setMaximumSize(cityDim);
    	*/
		cityPanel.addMouseListener(this);
    	cityPanel.setVisible(true);
    	add(cityPanel);
    	
    	
    	//stacking the building animations
    	BuildingPanel bankPanel = new BuildingPanel("Bank");
    	JLabel bankLabel = new JLabel(bank);
    	buildingPanels.add(bankPanel);
    	imglabels.add(bankLabel);
    	buildingMap.put(bankLabel, bankPanel);

    	//initializing some stubs to test the stacking
    	for(int i=0; i < 5; i++) {
    		BuildingPanel restPanel = new BuildingPanel("Restaurant " + (i+1));
    		buildingPanels.add(restPanel);
    		JLabel restLabel = new JLabel(restaurant);
    		imglabels.add(restLabel);
    		buildingMap.put(restLabel, restPanel);
    	}
    	
    	//positioning the city building icons (temp)
    	for(int n=0; n<imglabels.size();n++) {
    		Dimension size = imglabels.get(n).getPreferredSize();
    		imglabels.get(n).setBounds(size.width*n, 50, size.width, size.height);
    		cityPanel.add(imglabels.get(n));
    		
    		imglabels.get(n).addMouseListener(this);
    	}
    	
    	//positioning the building panels into the same spot
    	for(int m=0; m<buildingPanels.size();m++) {
    		buildingPanels.get(m).setBounds(10, 400, 570, 360);
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
