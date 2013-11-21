package application.gui.controlPanel;


import javax.swing.*;
import person.Profile;
import java.awt.*;
import java.awt.event.*;
import application.*;

public class AddPanel extends JPanel implements ActionListener {
	JLabel first = new JLabel("First Name: ");
	JTextField firstName = new JTextField(10);
	JLabel last = new JLabel("Last Name: ");
	JTextField lastName = new JTextField(10);
	JButton addButton = new JButton("Add");
	JLabel type = new JLabel("Type");
	String name = new String();
	Profile pf;
	ControlPanel cp;
	Application app;
	
	public AddPanel(ControlPanel cp, Application app){
		this.cp = cp;
		this.app = app;
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		
		c.gridx = 0;
		c.gridy = 0;
		add(first,c);
		
		c.gridx = 0;
		c.gridy = 1;
		add(firstName);
		
		c.gridx = 1;
		c.gridy = 0;
		add(last,c);
		
		c.gridx = 1;
		c.gridy = 1;
		add(lastName);
		
		c.gridx = 0;
		c.gridy	= 2;
		addButton.addActionListener(this);
		add(addButton);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton){
			String name = firstName.getText() + lastName.getText();
			String type = "Crook";
			app.addPerson(name, 500, type, null, null, 0, 0, 0);
		}
	}
	
	public void setApplication(Application app){
		this.app = app; 
	} 
}
