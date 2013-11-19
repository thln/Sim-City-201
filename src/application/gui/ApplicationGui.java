package application.gui;

import javax.swing.*;

//import bank.Bank;
//import bank.BankGuardRole;
//import bank.LoanOfficerRole;
import person.Person;
import person.Worker;

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
		
		
<<<<<<< HEAD
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
=======
		setBounds(50, 50, WINDOWX, WINDOWY);
    	
    	//here's the main application
		//MainView 
		appPanel.setSize(appDim);
		/*
		appPanel.setPreferredSize(appDim);
		appPanel.setMinimumSize(appDim);
		appPanel.setMaximumSize(appDim);
		*/
>>>>>>> 025d81095eaf8017d14ebbb4e9e66b3b0bd7834f
		appPanel.setVisible(true);
		add(appPanel);
		
		//AnimationView
<<<<<<< HEAD
		//c.gridwidth = GridBagConstraints.REMAINDER;
		//c.ipadx = AppPanelY;

=======
		animPanel.setSize(animDim);
		/*
		animPanel.setPreferredSize(animDim);
		animPanel.setMinimumSize(animDim);
		animPanel.setMaximumSize(animDim);
		*/
>>>>>>> 025d81095eaf8017d14ebbb4e9e66b3b0bd7834f
		animPanel.setVisible(true);
		add(animPanel);
    
	}
	
	
	public static void main(String[] args) {
        ApplicationGui gui = new ApplicationGui();
        gui.setTitle("SimCity 201 - Team 20");
        gui.setVisible(true);
        gui.setResizable(true);        
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
      /*
    	Worker loanOffice = new Worker("Officer", 100, "loanOfficer", 8, 0, 10);  	
		Worker bankGuard = new Worker("Guard", 100, "bankGuard", 8, 0 , 10);
		
		Bank.bankGuardRole = (BankGuardRole) bankGuard.getWorkerRole();
		Bank.loanOfficerRole = (LoanOfficerRole) loanOffice.getWorkerRole();
		
		Worker bankTell = new Worker("Teller", 100, "bankTeller", 8, 0, 10);  //needs to have reference to loanOfficer
		
	
		
		Worker bankCust = new Worker("Cust", 10, "", 5, 0, 0);
		
		
		bankTell.startThread();
		bankGuard.startThread();
		loanOffice.startThread();
		bankCust.startThread();
		
		bankTell.updateTime(8);
		bankGuard.updateTime(8);
		loanOffice.updateTime(8);
		bankCust.updateTime(8);
		*/
       
    }
	
}
