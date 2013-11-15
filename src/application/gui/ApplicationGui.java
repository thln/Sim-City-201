package application.gui;

import javax.swing.*;

import person.Person;
import person.Worker;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class ApplicationGui extends JFrame implements ActionListener{
	
	CityPanel cityPanel = new CityPanel();
	
	//List containing all of the different building animation panels
	public List<CityPanel> cityPanels = new ArrayList<CityPanel>();
	CityPanel buildingPanel1 = new CityPanel();
	CityPanel buildingPanel2 = new CityPanel();
	CityPanel buildingPanel3 = new CityPanel();
	ControlPanel controlPanel = new ControlPanel();
	
	public ApplicationGui() {
		int WINDOWX = 1200;
        int WINDOWY = 800;
       	setBounds(50, 50, WINDOWX, WINDOWY);
    	setLayout(null);
    	
    	//here we have the main city view
    	cityPanel.setBounds(WINDOWX/2, 0, WINDOWX/2, WINDOWY/2);
    	
    	//stacking the building animations
    	buildingPanel1.setBounds(WINDOWX/2, WINDOWY/2, WINDOWX/2, WINDOWY/2);
    	buildingPanel2.setBounds(WINDOWX/2+50, WINDOWY/2, WINDOWX/2, WINDOWY/2);
    	buildingPanel3.setBounds(WINDOWX/2+100, WINDOWY/2, WINDOWX/2, WINDOWY/2);
    	
    	add(controlPanel);
    	add(cityPanel);
    	
    	add(buildingPanel1);
    	add(buildingPanel2);
    	add(buildingPanel3);
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public static void main(String[] args) {
        ApplicationGui gui = new ApplicationGui();
        gui.setTitle("SimCity 201 - Team 20");
        gui.setVisible(true);
        gui.setResizable(true);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        Worker customer1 = new Worker("Bill", 10, "none", 10, 0, 0);
        customer1.startThread();
        customer1.msgNewTime(8);
    }
	
}
