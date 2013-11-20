package application.gui.appView.controlPanel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import application.*;
import application.gui.*;

public class ControlPanel extends JPanel{
	private AddPanel addP;
	private JPanel addPersonTab;
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();
	private JPanel panel4 = new JPanel();
	private ImageIcon picture;
	private JPanel tempPanel = new JPanel();
	private JTabbedPane ControlPane = new JTabbedPane();
	private Application app;
	
	public ControlPanel(Application app){
		setLayout(new GridLayout(1,1));
		this.app = app;
		addP = new AddPanel(this, app);
		addPersonTab =  addP.mainPanel;
		
		
		ControlPane.addTab("Add Person", addPersonTab);
		
		
		ControlPane.addTab("Option 2", panel2);
		
		
		ControlPane.addTab("Option 3", panel3);
		add(ControlPane);
	}	
}
