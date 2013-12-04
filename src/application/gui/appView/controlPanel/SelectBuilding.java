package application.gui.appView.controlPanel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import application.*;
import application.gui.appView.*;
import application.gui.appView.listPanel.ListPanel.Profile;
import application.gui.animation.*;

public class SelectBuilding extends JPanel implements ActionListener{

	JComboBox selectBuilding;
	JPanel mainPanel;
	CityPanel animP;
	JButton closeBuilding;
	ApplicationPanel appPanel;
	Application app;
	ControlPanel cp;
	int editIndex = 0;

	public SelectBuilding(ControlPanel cp, Application app, ApplicationPanel appPanel){
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(4,2));
		JLabel selectLabel = new JLabel("Select Person");
		mainPanel.add(selectLabel);

		this.cp = cp;
		this.app = app;
		this.appPanel = appPanel;

		closeBuilding = new JButton("Close Building");
		closeBuilding.addActionListener(this);
		mainPanel.add(closeBuilding);
		
		add(mainPanel);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == closeBuilding){
			System.out.print("Test");
		}

	}
}


