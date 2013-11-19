package application.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class ApplicationGui extends JFrame {
	
	ApplicationPanel appPanel = new ApplicationPanel();
	AnimationPanel animPanel = new AnimationPanel();
	//List containing all of the different building animation panels
	final static int WINDOWX = 1200;
    final static int WINDOWY = 800;
    final static int AppPanelX = WINDOWX*(4/10); //Application View Panel Width 400
	final static int AppPanelY = WINDOWY; //Application View Panel Height 600
	final static int AnimPanelX = WINDOWX*(6/10); //Animation View Panel Width 600
	final static int AnimPanelY = WINDOWY; //Animation View Panel Height 600
	 
	ApplicationGui() {
		setBounds(50,0, WINDOWX, WINDOWY);
		//Horizontal BoxLayout -- ApplicaitonPanel on the Left, 
				//AnimationPanel on the Right
		
		
		setLayout(new GridLayout(1,2));
		/*GridBagConstraints c = new GridBagConstraints();
		//c.gridwidth = 1;
    	//c.fill = GridBagConstraints.HORIZONTAL;
    	//here's the main application
		//MainView 
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.ipadx = AppPanelX;*/
		appPanel.setVisible(true);
		appPanel.setBackground(Color.WHITE);
		add(appPanel);
		
		//AnimationView
		//c.gridwidth = GridBagConstraints.REMAINDER;
		//c.ipadx = AppPanelY;
		animPanel.setVisible(true);
		add(animPanel);
	}
	
	public static void main(String[] args) {
        ApplicationGui gui = new ApplicationGui();
        gui.setTitle("SimCity 201 - Team 20");
        
        gui.setVisible(true);
        gui.setResizable(true);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
	
}
