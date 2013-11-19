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
	public ControlPanel(Application app){
		this.app = app;
		addPersonTab = new AddPanel(this, app);
		addPersonTab.setVisible(true);
		ControlPane.addTab("Add Person", addPersonTab);
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
