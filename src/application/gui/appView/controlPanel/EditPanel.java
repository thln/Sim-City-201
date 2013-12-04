package application.gui.appView.controlPanel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import application.*;
import application.gui.appView.*;
import application.gui.appView.listPanel.ListPanel.Profile;

public class EditPanel extends JPanel implements ActionListener {
	
	JPanel mainPanel;
	JTextField nameField;
	JTextField moneyField;
	JButton saveButton;
	JComboBox selectPerson;
	ApplicationPanel appPanel;
	Application app;
	ControlPanel cp;
	JButton closeBuilding;
	int editIndex = 0;
	
	public EditPanel(ControlPanel cp, Application app, ApplicationPanel appPanel){
		this.cp = cp;
		this.app = app;
		this.appPanel = appPanel;
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(4,2));
		JLabel selectLabel = new JLabel("Select Person");
		mainPanel.add(selectLabel);
		
		selectPerson = new JComboBox();
		//selectPerson.setSelectedIndex(0);
		selectPerson.addActionListener(this);
		//updateNamesList();
		mainPanel.add(selectPerson);
		
		JLabel nameLabel = new JLabel("Name");
		nameField = new JTextField(10);
		mainPanel.add(nameLabel);
		mainPanel.add(nameField);
		
		JLabel moneyLabel = new JLabel("Money");
		moneyField = new JTextField(10);
		mainPanel.add(moneyLabel);
		mainPanel.add(moneyField);
		
		saveButton = new JButton("Save");
		saveButton.addActionListener(this);
		mainPanel.add(saveButton);
		
		closeBuilding = new JButton("Close Building");
		closeBuilding.addActionListener(this);
		mainPanel.add(closeBuilding);
		
		
		add(mainPanel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == saveButton){
			app.editPerson(selectPerson.getSelectedIndex(), nameField.getText(), Integer.parseInt(moneyField.getText()));
			
		}
		
		if(e.getSource() == selectPerson){
			editIndex = selectPerson.getSelectedIndex();
			updateTextFields();
		}
		
		if (e.getSource() == closeBuilding){
			Phonebook.getPhonebook().closeBuildings();
		}
	}
	
	public void updateNamesList(){
		//TODO Fix this Function
		if(app.getPopulationSize()> 0){
			for(int i = 0; i<app.getPopulationSize(); i++){
				String tempName = app.getPerson(i).getName();
				selectPerson.addItem((String) tempName);
			}
			validate();
		}
	}
	
	public void updateTextFields(){
		if(appPanel.getListPanel().getListSize()>0){
			nameField = new JTextField(appPanel.getListPanel().getProfile(editIndex).getName(), 10);
			nameField = new JTextField(Integer.toString(appPanel.getListPanel().getProfile(editIndex).getMoney()), 10);
		}
	}

}
