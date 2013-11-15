package application.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ApplicationPanel extends JPanel{
	
	JPanel controlPanel = new JPanel();
	JPanel infoPanel = new JPanel();
	private GroupPanel crookPanel = new GroupPanel("Crook",Color.WHITE);
	private GroupPanel deadbeatPanel = new GroupPanel("Deadbeat", Color.LIGHT_GRAY);
	private GroupPanel workerPanel = new GroupPanel("Worker", Color.LIGHT_GRAY);
	private GroupPanel wealthyPanel = new GroupPanel("Wealthy",Color.WHITE);
	
	Dimension controlDim = new Dimension(this.getWidth(), this.getHeight()*(1/4));
	Dimension infoDim = new Dimension(this.getWidth(), this.getHeight()*(3/4));
	
	
	
	ApplicationPanel(){
		
		//Control Panel
		
		controlPanel.setSize(controlDim);
		controlPanel.setVisible(true);
		//commentedoutfortesting add(controlPanel);
		
		
		//InfoPanel
		infoPanel.setLayout(new GridLayout(2,2));

		infoPanel.setSize(infoDim);
		infoPanel.setVisible(true);
		
			infoPanel.add(crookPanel);
			//commentedoutfortesting infoPanel.add(deadbeatPanel);
			//commentedoutfortesting infoPanel.add(workerPanel);
			//commentedoutfortesting infoPanel.add(wealthyPanel);
					
		add(infoPanel);
	}

}
