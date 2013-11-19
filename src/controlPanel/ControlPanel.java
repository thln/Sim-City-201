package controlPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ControlPanel extends JPanel{
	
	AddPersonPanel addPersonTab = new AddPersonPanel();
	private ImageIcon picture;
	//private ArrayList<Profile> tempPersonList = new ArrayList<Profile>();
	private JPanel tempPanel = new JPanel();
	private JTabbedPane ControlPane = new JTabbedPane();
	
	public ControlPanel(){
		
		addPersonTab.setVisible(true);
		ControlPane.addTab("Add Person", null, addPersonTab, "add a person");
		add(ControlPane);
	}	
	
	private class AddPersonPanel extends JPanel implements ActionListener {
		JLabel first = new JLabel("First Name: ");
		JTextField firstName = new JTextField(10);
		JLabel last = new JLabel("Last Name: ");
		JTextField lastName = new JTextField(10);
		JButton AddButton = new JButton("Add");
		JLabel type = new JLabel("Type");
		
		public AddPersonPanel(){
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
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	
	}

}
