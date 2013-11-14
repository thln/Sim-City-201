package application.gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class AnimationPanel extends JPanel implements ActionListener {
	
	
	CityPanel cityPanel = new CityPanel();
	public List<JPanel> buildingPanels = new ArrayList<JPanel>();
	//JPanel buildingPanel = new JFrame("Building View");
	
	final static int CityDimX = 600; //City View Frame Width
	final static int CityDimY = 300; //City View Frame Height
	final static int BuildingDimX = 600; //Building View Frame Width
	final static int BuildingDimY = 300; //Building View Frame Height
	
	
	AnimationPanel(){
		
		//Dimension cityDim = new Dimension(getWidth(), getHeight());
		setLayout(null);
		setBorder(BorderFactory.createTitledBorder("Animation"));
    	//here we have the main city view
    	
		//cityPanel.setSize(700, 400);
		cityPanel.setBounds(3, 15, 700, 400); //x & y positions in animation panel, x & y sizes
		/*
    	cityPanel.setPreferredSize(cityDim);
    	cityPanel.setMinimumSize(cityDim);
    	cityPanel.setMaximumSize(cityDim);
    	*/
    	cityPanel.setVisible(true);
    	add(cityPanel);
    	
    	
    	//stacking the building animations
    	//add(buildingPanel1)
    	//add(buiildingPanel2)
    	
    	
    	
    	
		
		
		
		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		
	}
	
	
	
	
}
