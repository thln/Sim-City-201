package application.gui.appView.controlPanel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import application.*;
import application.gui.appView.*;
import application.gui.appView.listPanel.ListPanel.Profile;



public class DashboardPanel extends JPanel implements AWTEventListener{
	
	private JLabel mainTitle = new JLabel("Title");
	private JLabel descipt = new JLabel("Description");
	private JLabel pop = new JLabel ("Population");
	private JLabel numberOfHomes = new JLabel ("Number of Homes");
	private JLabel markets = new JLabel ("Number of Markets");
	private JLabel banks = new JLabel ("Number of Banks");
	private JLabel homeless = new JLabel ("% Homeless");
	private JLabel unemployed = new JLabel ("% Unemployed");

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
		setBackground(getBackground());
		this.app = app;
		updateDashboard(app);
		setLayout(new GridLayout(0,1));
		setBorder(BorderFactory.createLoweredBevelBorder());
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

		mainTitle = new JLabel("\t\t\t" + title);
		descipt = new JLabel("\t\t\t" + description);
		pop = new JLabel ("\t\t\t" + "Population: " + population);
		numberOfHomes = new JLabel ("\t\t\t" + "Number of Homes: " + numHomes);
		markets = new JLabel ("\t\t\t" + "Number of Markets: " + numMarkets);
		banks = new JLabel ("\t\t\t" + "Number of Banks: " + numBanks);
		homeless = new JLabel ("\t\t\t" + "% Homeless: " + numHomeless);
		unemployed = new JLabel ("\t\t\t" + "% Unemployed: " + numUnemployed);
		
		add(mainTitle);
		add(descipt);
		add(pop);
		add(numberOfHomes);
		add(markets);
		add(banks);
		add(homeless);
		add(unemployed);
	}
}
