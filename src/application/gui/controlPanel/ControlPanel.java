package application.gui.controlPanel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import application.*;
import application.gui.*;

public class ControlPanel extends JPanel{
	private AddPanel addP;
	private JPanel addPersonTab;
	private ImageIcon picture;
	private JPanel tempPanel = new JPanel();
	private JTabbedPane ControlPane = new JTabbedPane();
	private Application app;
	
	public ControlPanel(Application app){
		this.app = app;
		addP = new AddPanel(this, app);
		addPersonTab =  addP.mainPanel;
		ControlPane.addTab("Add Person", addPersonTab);
		add(ControlPane);
		
		add(ControlPane);
	}	
	
	public void setApplication(Application app){
		this.app = app;
	}
	
}
