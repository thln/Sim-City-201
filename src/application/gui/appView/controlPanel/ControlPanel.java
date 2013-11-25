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
	JPanel panel4 = new JPanel();
	JPanel panel5 = new JPanel();
	//Add Panel
	private AddPanel addP;
	private EditPanel editP;
	
	//Trace Panel
	private PrintPanel printPanel;
	private JPanel printPanelTab;
	
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
		editP = new EditPanel(this, app, appPanel);
		
		printPanel = new PrintPanel();
		printPanelTab = printPanel;
		
		
		ControlPane.addTab("Add Person", addP);
		ControlPane.addTab("Trace Panel", printPanelTab);
		ControlPane.addTab("Edit Panel", editP);
		ControlPane.addTab("Select Building", panel4);
		ControlPane.addTab("System Statistics", panel5);
		
		add(ControlPane);
	
	}	
	
	public ApplicationPanel getAppPanel(){
		return appPanel;
	}
	
	public EditPanel getEditPanel(){
		return editP;
	}
}
