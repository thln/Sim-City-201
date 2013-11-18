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
	
	BufferedImage bankicon = null;
	
	AnimationPanel(){
		
		//Dimension cityDim = new Dimension(getWidth(), getHeight());
		setLayout(null);
		setBorder(BorderFactory.createTitledBorder("Animation"));
    	//here we have the main city view
    	
		cityPanel.setBounds(3, 15, 700, 400); //x & y positions in animation panel, x & y sizes
		/*
    	cityPanel.setPreferredSize(cityDim);
    	cityPanel.setMinimumSize(cityDim);
    	cityPanel.setMaximumSize(cityDim);
    	*/
		cityPanel.addMouseListener(this);
    	cityPanel.setVisible(true);
    	add(cityPanel);
    	
    	
    	//stacking the building animations
    	ImageIcon icon = new ImageIcon("bank.jpg", "bank icon");

    	//initializing some stubs to test the stacking
    	for(int i=0; i < 5; i++) {
    		CityPanel building = new CityPanel();
    		buildingPanels.add(building);
    		JLabel label = new JLabel("Label " + (i+1), icon, JLabel.TRAILING);
    		//imglabels.add(label);
    		imglabels.add(label);
    		buildingMap.put(label, building);
    	}
    	
    	for(int n=0; n<buildingPanels.size();n++) {
    		Dimension size = imglabels.get(n).getPreferredSize();
    		imglabels.get(n).setBounds(size.width*n, 0, size.width, size.height);
    		imglabels.get(n).addMouseListener(this);
    		cityPanel.add(imglabels.get(n));
    		buildingPanels.get(n).setBounds(3 + 50*n, 415, 700, 400);
    		
    		buildingPanels.get(n).setVisible(false);
    		
    		add(buildingPanels.get(n));
    	}
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
				clearScreen();
				buildingMap.get(imglabels.get(i)).setVisible(true);
			}
		}
		if(e.getSource() == cityPanel) {
			System.out.println("Panel Clicked!!");
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
