package application.gui;

import javax.swing.*;

import application.Application;
//import bank.Bank;
//import bank.BankGuardRole;
//import bank.LoanOfficerRole;
import application.*;
import bank.*;
import person.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import application.*;
import application.gui.*;

public class ApplicationGui extends JFrame {
	
	Application app = new Application();
	ApplicationPanel appPanel; 
	AnimationPanel animPanel = new AnimationPanel();

	final static int WINDOWX = 1200;
	final static int WINDOWY = 800;
    final static int APPWIDTH = WINDOWX*(4/10); //Application View Panel Width 400
	final static int ANIMWIDTH = WINDOWX*(6/10); //Animation View Panel Width 600
	//final static int AnimPanelY = WINDOWY; //Animation View Panel Height 600	
	
	ApplicationGui() {
		appPanel = new ApplicationPanel(app);
		setBounds(50,0, WINDOWX, WINDOWY);
		//appPanel.getControlPanel().setApplication(app);
		setLayout(new GridLayout(1,2)); //GridLayout with 2 columns and 1 row
		setBounds(50, 50, WINDOWX, WINDOWY);    	
    	//here's the main application
		//MainView 
		appPanel.setVisible(true);
		add(appPanel);
		
		//AnimationView
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
      //  simcity201 = new Application();
    }
	
}
