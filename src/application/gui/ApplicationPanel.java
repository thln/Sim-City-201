package application.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import application.*;


public class ApplicationPanel extends JPanel{
	
	private JPanel controlPanel = new JPanel();
	private JPanel infoPanel = new JPanel();
	private GroupPanel crookPanel = new GroupPanel("Crook",Color.WHITE);
	private GroupPanel deadbeatPanel = new GroupPanel("Deadbeat", Color.LIGHT_GRAY);
	private GroupPanel workerPanel = new GroupPanel("Worker", Color.LIGHT_GRAY);
	private GroupPanel wealthyPanel = new GroupPanel("Wealthy",Color.WHITE);
	private Application app;
	
	ApplicationPanel(Application app){
		this.app = app;
		setLayout(new GridLayout(2,1));
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		//Control Panel
		c.gridx = 0;
		c.gridy = 0;
		
		controlPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(controlPanel);
		
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
	
	public void setApplication(Application app){
		this.app = app;
	}

}
