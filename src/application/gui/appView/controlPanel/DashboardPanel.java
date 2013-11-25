package application.gui.appView.controlPanel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import application.*;
import application.gui.appView.*;
import application.gui.appView.listPanel.ListPanel.Profile;



public class DashboardPanel extends JPanel implements AWTEventListener{
	
	private JTextArea mainArea = new JTextArea();
	private final String title = "Team 20's City";
	private final String description = "City Description Tagline";
	private int population;
	private int numHomes;
	private int occupiedHomes;
	private	int numMarkets;
	private int numBanks;
	private int numHomeless;
	private int numUnemployed;
	private Application app;
	public DashboardPanel(Application app){
		setBackground(Color.WHITE);
		this.app = app;
		updateDashboard(app);
		add(mainArea);
		
	}
	
	@Override
	public void eventDispatched(AWTEvent e) {
		updateDashboard(app);
	}
	
	public void updateDashboard(Application app){
		population = app.getPopulationSize();
		numHomes = app.getNumberHomes();
		occupiedHomes = 0;
		numMarkets = 1;
		numBanks = 1;
		numHomeless = 0;
		numUnemployed = 0;
	
		mainArea.removeAll();
		mainArea.setText(title +"\n"
				 +description +"\n"
				 +"Population: " + population +"\n"
				 +"Number of Homes: "+numHomes +"\n"
				 //+"% Occupancy: " +occupiedHomes +"\n"
				 +"# of Markets: " +numMarkets +"\n"
				 +"# of Banks: " +numBanks +"\n"
				 +"% Homeless: " + numHomeless + "\n"
				 +"% Unemployed: " + numUnemployed + "\n"
				);
		validate();
	}

	
	
}
