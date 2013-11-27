package application.gui;

import javax.swing.*;

//import bank.Bank;
//import bank.BankGuardRole;
//import bank.LoanOfficerRole;
import application.*;
import bank.*;
import person.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;

import application.*;
import application.gui.*;
import application.gui.animation.*;
import application.gui.appView.*;
import application.gui.trace.AlertLevel;
import application.gui.trace.AlertTag;
import application.gui.trace.TracePanel;
//import application.gui.trace.DemoLauncher.ControlPanel;

public class ApplicationGui extends JFrame {


	Application app;

	ApplicationPanel appPanel; 
	AnimationPanel animPanel = new AnimationPanel();	

	final static int WINDOWX = 1200;
	final static int WINDOWY = 680;
	final static int APPWIDTH = WINDOWX*(4/10); //Application View Panel Width 400
	final static int ANIMWIDTH = WINDOWX*(6/10); //Animation View Panel Width 600
	//final static int AnimPanelY = WINDOWY; //Animation View Panel Height 600	

	ApplicationGui() {
		
		app = new Application(animPanel);
		appPanel = new ApplicationPanel(this, app);
		setBounds(0,0, WINDOWX, WINDOWY);
		//appPanel.getControlPanel().setApplication(app);
		setLayout(new GridLayout(1,2)); //GridLayout with 2 columns and 1 row
		setBounds(0, 0, WINDOWX, WINDOWY);  

		//here's the main application
		//MainView 
		appPanel.setVisible(true);
		add(appPanel);

		//AnimationView
		animPanel.setVisible(true);
		add(animPanel);
	}


	public static void main(String[] args) {
		/*Properties props = new Properties();
		
		try {
		    FileInputStream in = new FileInputStream("propFile.properties");
		    props.load(in);
		    in.close();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(IllegalArgumentException iae) {
			  iae.printStackTrace();
		}*/
		/*
		Scanner in;
		
		try
		{
			in = new Scanner(new File("docs/input.txt"));
			while(in.hasNext())
			{
				System.out.println(in.next());
			}
		}
		catch (FileNotFoundException e)
		{
			System.err.println("File not found. Retry.");
		}*/
		
		
		ApplicationGui gui = new ApplicationGui();
		gui.setTitle("SimCity 201 - Team 20");
		gui.setVisible(true);
		gui.setResizable(true);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
