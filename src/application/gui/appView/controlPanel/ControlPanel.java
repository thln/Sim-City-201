package application.gui.appView.controlPanel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import application.*;
import application.gui.*;
import application.gui.appView.*;
import application.gui.trace.PrintControlPanel;

public class ControlPanel extends JPanel {
	//Add Panel
	private AddPanel addP;
	private JPanel addPersonTab;
	
	//Trace Panel
	private PrintPanel printPanel;
	private JPanel printPanelTab;
	
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();
	private JPanel panel4 = new JPanel();
	private ImageIcon picture;
	private JPanel tempPanel = new JPanel();
	private JTabbedPane ControlPane = new JTabbedPane();
	private Application app;
	private ApplicationPanel appPanel;
	
	public ControlPanel(ApplicationPanel appPanel, Application app){
		setLayout(new GridLayout(1,1));
		this.app = app;
		this.appPanel = appPanel;
		addP = new AddPanel(this, app);
		addPersonTab =  addP.mainPanel;
		printPanel = new PrintPanel();
		printPanelTab = printPanel;
		
		
		ControlPane.addTab("Add Person", addPersonTab);
		ControlPane.addTab("Trace Panel", printPanelTab);
		ControlPane.addTab("Option 3", panel3);
		
		add(ControlPane);
	}	
	
	public ApplicationPanel getAppPanel(){
		return appPanel;
	}
}
