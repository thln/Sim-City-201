package application.gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;


public class ApplicationPanel extends JPanel implements ActionListener {
	
	JPanel controlPanel = new JPanel();
	JPanel infoPanel = new JPanel();
	JPanel crookPanel = new GroupPanel();
	
	private GroupPanel deadbeatPanel = new GroupPanel();
	private GroupPanel workerPanel = new GroupPanel();
	private GroupPanel wealthyPanel = new GroupPanel();
	
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
		
		infoPanel.setPreferredSize(infoDim);
		infoPanel.setMinimumSize(infoDim);
		infoPanel.setMaximumSize(infoDim);
		infoPanel.setVisible(true);
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
