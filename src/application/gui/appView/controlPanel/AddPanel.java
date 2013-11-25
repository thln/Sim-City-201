package application.gui.appView.controlPanel;


import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import application.*;
import application.gui.appView.listPanel.ListPanel.Profile;

public class AddPanel extends JPanel implements ActionListener {
	private DashboardPanel dashboard;
	public static JPanel mainPanel = new JPanel();
	private JLabel first = new JLabel("Name: ");
	private JTextField firstName = new JTextField(10);
	private JButton addButton = new JButton("Add");
	private ControlPanel cp;
	private Application app;
	private JComboBox typeBox;

	private String[] personType = {"Deadbeat", "Worker", "Wealthy"};
	
	public AddPanel(ControlPanel cp, Application app){
		this.cp = cp;
		this.app = app;
		setLayout(new GridLayout(1,0));
		dashboard = new DashboardPanel(app);
		add(dashboard);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		c.anchor = GridBagConstraints.CENTER;
		
		c.gridx = 0;
		c.gridy = 0;
		mainPanel.add(first,c);
		c.gridx = 1;
		c.gridy = 0;
		mainPanel.add(firstName,c);
		
		c.gridx = 0;
		c.gridy = 2;
		JLabel typeLabel = new JLabel("Select Type");
		mainPanel.add(typeLabel,c);
		
		c.gridx = 1;
		c.gridy = 2;
		typeBox = new JComboBox(personType);
		typeBox.setSelectedIndex(0);
		typeBox.addActionListener(this);
		mainPanel.add(typeBox,c);
		
		c.gridx = 0;
		c.gridy	= 3;
		addButton.addActionListener(this);
		mainPanel.add(addButton,c);
		add(mainPanel);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton){
			String name = firstName.getText();
			String type = (String)typeBox.getSelectedItem();
			app.addPerson(name, 500, type, null, null, 0, 0, 0);
			//cp.getAppPanel().getListPanel().addPerson(name, 500, type, null, null, 0, 0, 0);
			cp.getAppPanel().getListPanel().updateList();
			
			app.printLastPop();
			System.out.println(type);
			System.out.println(app.getPopulationSize());
		}
	}
	
	public void setApplication(Application app){
		this.app = app; 
	}
	
	
}
