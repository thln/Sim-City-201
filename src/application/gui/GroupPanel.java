package application.gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class GroupPanel extends JPanel {
	private JPanel ListView = new JPanel();
	private JPanel ProfileView = new JPanel();
	private JPanel ProfilePanel = new JPanel();
	private JLabel ButtonLabel = new JLabel();
	public JScrollPane pane = 
			new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
							JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private List<JButton> list = new ArrayList<JButton>();
    	
    
	GroupPanel(){
		
		Dimension paneSize = pane.getSize();
	    Dimension buttonSize = new Dimension(paneSize.width-20, (int) (paneSize.height /7));
		
		
		
		
		
		
	}
	
	public void addPerson(String name, String occupation, String currLoc, String money){
		if(name !=null){
			
			
			
			
			
		}
		
	}
	
	
	
}
