package application.gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;


public class ApplicationPanel extends JPanel implements ActionListener {
	
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
		
		controlPanel.setPreferredSize(controlDim);
		controlPanel.setMinimumSize(controlDim);
		controlPanel.setMaximumSize(controlDim);
		controlPanel.setVisible(true);
		add(controlPanel);
		
		//InfoPanel
		infoPanel.setLayout(new GridLayout(2,2));

		infoPanel.setPreferredSize(infoDim);
		infoPanel.setMinimumSize(infoDim);
		infoPanel.setMaximumSize(infoDim);
		infoPanel.setVisible(true);
		
			infoPanel.add(crookPanel);
			infoPanel.add(deadbeatPanel);
			infoPanel.add(workerPanel);
			infoPanel.add(wealthyPanel);
					
		add(infoPanel);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
		
	}

	
	
	
	/*Classes 
	 *There are several subpanels used in this ApplicationPanel that are included in this file 
	 * infoPanel(that 
	 * 
	 * 
	 * 
	 */
	

	
	
}
