package application.gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class ControlPanel extends JPanel implements ActionListener{
	
	private final int WINDOWX = 450;
    private final int WINDOWY = 400;
    
    private JButton addPerson = new JButton("Add a Person");
	
	public ControlPanel() {
		setSize(WINDOWX, WINDOWY);
		add(addPerson);
	}
	public void actionPerformed(ActionEvent e) {
		
	}

}
