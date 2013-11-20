package application.gui.controlPanel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import application.*;
import application.gui.*;

public class ControlPanel extends JPanel{
	
	AddPanel addPersonTab;
	private ImageIcon picture;
	private JPanel tempPanel = new JPanel();
	private JTabbedPane ControlPane = new JTabbedPane();
	private Application app;
	
	public ControlPanel(){
		addPersonTab = new AddPanel(this, app);
		addPersonTab.setVisible(true);
		ControlPane.addTab("Add Person", addPersonTab);
		add(ControlPane);
	}	
	public void setApplication(Application app){
		this.app = app;
	}
	
}
