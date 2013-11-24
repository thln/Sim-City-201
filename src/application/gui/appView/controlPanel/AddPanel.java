package application.gui.appView.controlPanel;


import javax.swing.*;

import person.Profile;

import java.awt.*;
import java.awt.event.*;

import application.*;

public class AddPanel implements ActionListener {
	public static JPanel mainPanel = new JPanel();
	private JLabel first = new JLabel("First Name: ");
	private JTextField firstName = new JTextField(10);
	private JLabel last = new JLabel("Last Name: ");
	private JTextField lastName = new JTextField(10);
	private JButton addButton = new JButton("Add");
	private ControlPanel cp;
	private Application app;
	private JComboBox typeBox;

	private String[] personType = {"Crook", "Deadbeat", "Worker", "Wealthy"};
	
	public AddPanel(ControlPanel cp, Application app){
		this.cp = cp;
		this.app = app;
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		
		c.gridx = 0;
		c.gridy = 0;
		mainPanel.add(first,c);
		
		c.gridx = 0;
		c.gridy = 1;
		mainPanel.add(firstName);
		
		c.gridx = 1;
		c.gridy = 0;
		mainPanel.add(last,c);
		
		c.gridx = 1;
		c.gridy = 1;
		mainPanel.add(lastName,c);
		
		c.gridx = 0;
		c.gridy	= 2;
		addButton.addActionListener(this);
		mainPanel.add(addButton,c);
	
		c.gridx = 0;
		c.gridy = 3;
		JLabel typeLabel = new JLabel("Select Type");
		mainPanel.add(typeLabel,c);
		
		c.gridx = 1;
		c.gridy = 3;
		typeBox = new JComboBox(personType);
		typeBox.setSelectedIndex(0);
		typeBox.addActionListener(this);
		mainPanel.add(typeBox,c);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton){
			String name = firstName.getText() +" "  + lastName.getText();
			String type = (String)typeBox.getSelectedItem();
			app.addPerson(name, 500, type, null, 0, 0, 0);
			app.printLastPop();
			
			System.out.println(type);
			System.out.println(app.getPopulationSize());
		}
	}
	
	public void setApplication(Application app){
		this.app = app; 
	} 
	
}
