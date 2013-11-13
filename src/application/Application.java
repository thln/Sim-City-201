package application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class Application extends JFrame implements ActionListener{
	
	CityPanel cityPanel = new CityPanel();
	
	//List containing all of the different building animation panels
	public List<CityPanel> cityPanels = new ArrayList<CityPanel>();
	CityPanel cityPanel1 = new CityPanel();
	CityPanel cityPanel2 = new CityPanel();
	CityPanel cityPanel3 = new CityPanel();
	ControlPanel controlPanel = new ControlPanel();
	
	public Application() {
		int WINDOWX = 1200;
        int WINDOWY = 800;
       	setBounds(50, 50, WINDOWX, WINDOWY);
    	setLayout(null);
    	
    	//here we have the main city view
    	cityPanel.setBounds(WINDOWX/2, 0, WINDOWX/2, WINDOWY/2);
    	
    	//stacking the building animations
    	cityPanel1.setBounds(WINDOWX/2, WINDOWY/2, WINDOWX/2, WINDOWY/2);
    	cityPanel2.setBounds(WINDOWX/2+50, WINDOWY/2, WINDOWX/2, WINDOWY/2);
    	cityPanel3.setBounds(WINDOWX/2+100, WINDOWY/2, WINDOWX/2, WINDOWY/2);
    	
    	add(controlPanel);
    	add(cityPanel);
    	
    	add(cityPanel1);
    	add(cityPanel2);
    	add(cityPanel3);
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public static void main(String[] args) {
        Application app = new Application();
        app.setTitle("SimCity 201 - Team 20");
        app.setVisible(true);
        app.setResizable(true);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
	
}
