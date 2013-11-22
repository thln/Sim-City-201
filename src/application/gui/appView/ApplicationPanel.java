package application.gui.appView;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import application.*;
import application.gui.*;
import application.gui.animation.*;
import application.gui.animation.agentGui.*;
import application.gui.appView.controlPanel.*;
import application.gui.appView.groupPanel.*;


public class ApplicationPanel extends JPanel{
	
	private ControlPanel cp;
	private JPanel infoPanel = new JPanel();
	private GroupPanel crookPanel;
	private GroupPanel deadbeatPanel;
	private GroupPanel workerPanel;
	private GroupPanel wealthyPanel;
	
	public ApplicationPanel(Application app){
		cp = new ControlPanel(app);
		crookPanel = new GroupPanel("Crook",Color.WHITE, this, app);
		deadbeatPanel = new GroupPanel("Deadbeat", Color.LIGHT_GRAY, this, app);
		workerPanel = new GroupPanel("Worker", Color.LIGHT_GRAY, this, app);
		wealthyPanel = new GroupPanel("Wealthy",Color.WHITE, this, app);
		
		setLayout(new GridLayout(2,1));
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		//Control Panel
		c.gridx = 0;
		c.gridy = 0;
		
		cp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(cp);		
		
		//InfoPanel
		//TODO set size so that info panel is 75% and controPanel is 25% of total height
		c.gridx = 0;
		c.gridy = 1;
		
		infoPanel.setLayout(new GridLayout(2,2));
		infoPanel.setVisible(true);
		infoPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			infoPanel.add(crookPanel);
			infoPanel.add(deadbeatPanel);
			infoPanel.add(workerPanel);
			infoPanel.add(wealthyPanel);
		
		add(infoPanel);
	}
	
	public ControlPanel getControlPanel(){
		return cp;
	}
	
	public void addPerson(String name, int money, String type,
			String jobTitle, int startT, int lunchT, int endT)
	{
		if(type == "Crook"){
			crookPanel.addPerson(name, money, type, jobTitle, startT, lunchT, endT);
		}
		else if(type == "Deadbeat"){
			deadbeatPanel.addPerson(name, money, type, jobTitle, startT, lunchT, endT);
		}
		else if(type == "Worker"){
			workerPanel.addPerson(name, money, type, jobTitle, startT, lunchT, endT);			
		}
		else if(type == "Wealthy"){
			wealthyPanel.addPerson(name, money, type, jobTitle, startT, lunchT, endT);
		}	
	}
	
	public void updateGroups(){
		crookPanel.updatePersonList();
		deadbeatPanel.updatePersonList();
		workerPanel.updatePersonList();
		wealthyPanel.updatePersonList();
	}
}
