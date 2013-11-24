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
<<<<<<< HEAD
	
	static Application app = new Application();
=======

	Application app = new Application();
>>>>>>> master
	ApplicationPanel appPanel; 
	AnimationPanel animPanel = new AnimationPanel();

	//  ControlPanel controlPanel;
	//TracePanel tracePanel;

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
<<<<<<< HEAD
		*/
        app = new Application();
    }
	
	
	//CONTROL PANEL CLASS (for the trace panel)
    private class ControlPanel extends JPanel 
    {
            TracePanel tp;
            
            //Look into JToggleButton
            JButton messagesButton;
            JButton errorButton;
            JButton restaurantMessagesButton;
            JButton bankMessagesButton;
            JButton housingMessagesButton;
            JButton marketMessagesButton;
            JButton generalCityMessagesButton;
            boolean showMessageBool = true;
            boolean showErrorBool = true;
            boolean showRestaurantMsgBool = true;
            boolean showBankMsgBool = true;
            boolean showHousingMsgBool = true;
            boolean showMarketMsgBool = true;
            boolean showGeneralCityMsgBool = true;
            
            public ControlPanel(final TracePanel tracePanel) 
            {
                    this.tp = tracePanel;
                    messagesButton = new JButton("Hide Level: MESSAGE")
                    {
                        {
                            setSize(200, 25);
                            setMaximumSize(getSize());
                        }
                    };
                    errorButton = new JButton("Hide Level : ERROR")
                    {
                        {
                            setSize(200, 25);
                            setMaximumSize(getSize());
                        }
                    };
                    restaurantMessagesButton = new JButton("Hide Tag: RESTAURANT")
                    {
                        {
                            setSize(200, 25);
                            setMaximumSize(getSize());
                        }
                    };
                    bankMessagesButton = new JButton("Hide Tag: BANK")
                    {
                        {
                            setSize(200, 25);
                            setMaximumSize(getSize());
                        }
                    };
                    housingMessagesButton = new JButton("Hide Tag: HOUSING")
                    {
                        {
                            setSize(200, 25);
                            setMaximumSize(getSize());
                        }
                    };
                    marketMessagesButton = new JButton("Hide Tag: MARKET")
                    {
                        {
                            setSize(200, 25);
                            setMaximumSize(getSize());
                        }
                    };
                    generalCityMessagesButton = new JButton("Hide Tag: GENERAL CITY")
                    {
                        {
                            setSize(200, 25);
                            setMaximumSize(getSize());
                        }
                    };
=======
		 */
		//simcity201 = new Application();
	}
>>>>>>> master


	//CONTROL PANEL CLASS
	//<<<<<<< HEAD
//	private class ControlPanel extends JPanel 
//	{
//		TracePanel tp;        //Hack so I can easily call showAlertsWithLevel for this demo.
//
//		JButton messagesButton;
//		JButton errorButton;
//		JButton restaurantMessagesButton;
//		JButton bankMessagesButton;
//		JButton housingMessagesButton;
//		JButton marketMessagesButton;
//		JButton generalCityMessagesButton;
//		boolean showMessageBool = true;
//		boolean showErrorBool = true;
//		boolean showRestaurantMsgBool = true;
//		boolean showBankMsgBool = true;
//		boolean showHousingMsgBool = true;
//		boolean showMarketMsgBool = true;
//		boolean showGeneralCityMsgBool = true;
//		//=======
//	}
//		private class ControlPanel extends JPanel 
//		{
//			TracePanel tp;        //Hack so I can easily call showAlertsWithLevel for this demo.
//
//			JButton messagesButton;
//			JButton errorButton;
//			JButton restaurantMessagesButton;
//			JButton bankMessagesButton;
//			JButton housingMessagesButton;
//			JButton marketMessagesButton;
//			JButton generalCityMessagesButton;
//			boolean showMessageBool = true;
//			boolean showErrorBool = true;
//			boolean showRestaurantMsgBool = true;
//			boolean showBankMsgBool = true;
//			boolean showHousingMsgBool = true;
//			boolean showMarketMsgBool = true;
//			boolean showGeneralCityMsgBool = true;
//
//			public ControlPanel(final TracePanel tracePanel) 
//			{
//				this.tp = tracePanel;
//				messagesButton = new JButton("Hide Level: MESSAGE")
//				{
//					{
//						setSize(200, 25);
//						setMaximumSize(getSize());
//					}
//				};
//				errorButton = new JButton("Hide Level : ERROR")
//				{
//					{
//						setSize(200, 25);
//						setMaximumSize(getSize());
//					}
//				};
//				restaurantMessagesButton = new JButton("Hide Tag: RESTAURANT")
//				{
//					{
//						setSize(200, 25);
//						setMaximumSize(getSize());
//					}
//				};
//				bankMessagesButton = new JButton("Hide Tag: BANK")
//				{
//					{
//						setSize(200, 25);
//						setMaximumSize(getSize());
//					}
//				};
//				housingMessagesButton = new JButton("Hide Tag: HOUSING")
//				{
//					{
//						setSize(200, 25);
//						setMaximumSize(getSize());
//					}
//				};
//				marketMessagesButton = new JButton("Hide Tag: MARKET")
//				{
//					{
//						setSize(200, 25);
//						setMaximumSize(getSize());
//					}
//				};
//				generalCityMessagesButton = new JButton("Hide Tag: GENERAL CITY")
//				{
//					{
//						setSize(200, 25);
//						setMaximumSize(getSize());
//					}
//				};
//			}
//			//>>>>>>> 9d632c73feaf9569d02fa8e28f464fcd5ba0e4dc
//
//			public ControlPanel(final TracePanel tracePanel) 
//			{
//				this.tp = tracePanel;
//				messagesButton = new JButton("Hide Level: MESSAGE")
//				{
//					{
//						setSize(200, 25);
//						setMaximumSize(getSize());
//					}
//				};
//				errorButton = new JButton("Hide Level : ERROR")
//				{
//					{
//						setSize(200, 25);
//						setMaximumSize(getSize());
//					}
//				};
//				restaurantMessagesButton = new JButton("Hide Tag: RESTAURANT")
//				{
//					{
//						setSize(200, 25);
//						setMaximumSize(getSize());
//					}
//				};
//				bankMessagesButton = new JButton("Hide Tag: BANK")
//				{
//					{
//						setSize(200, 25);
//						setMaximumSize(getSize());
//					}
//				};
//				//<<<<<<< HEAD
//				housingMessagesButton = new JButton("Hide Tag: HOUSING");
//				//			=======
//				housingMessagesButton = new JButton("Hide Tag: HOUSING")
//				//			>>>>>>> master
//				{
//					{
//						setSize(200, 25);
//						setMaximumSize(getSize());
//					}
//				};
//				marketMessagesButton = new JButton("Hide Tag: MARKET")
//				{
//					{
//						setSize(200, 25);
//						setMaximumSize(getSize());
//					}
//				};
//				generalCityMessagesButton = new JButton("Hide Tag: GENERAL CITY")
//				{
//					{
//						setSize(200, 25);
//						setMaximumSize(getSize());
//					}
//				};
//
//				messagesButton.addActionListener(new ActionListener() 
//				{
//					@Override
//					public void actionPerformed(ActionEvent e) 
//					{
//						//============================ TUTORIAL ==========================================
//						//This is how you make messages with a certain Level (normal MESSAGE here) show up in the trace panel.
//						if(!showMessageBool)
//						{
//							showMessageBool = true;
//							tracePanel.showAlertsWithLevel(AlertLevel.MESSAGE);
//							messagesButton.setText("Hide Level: MESSAGE");
//						}
//						else if (showMessageBool)
//						{
//							showMessageBool = false;
//							tracePanel.hideAlertsWithLevel(AlertLevel.MESSAGE);
//							messagesButton.setText("Show Level: MESSAGE");
//						}
//						//================================================================================
//					}
//				});
//				errorButton.addActionListener(new ActionListener() 
//				{
//					@Override
//					public void actionPerformed(ActionEvent e) 
//					{
//						//============================ TUTORIAL ==========================================
//						//This is how you make messages with a certain Level (normal MESSAGE here) show up in the trace panel.
//						if(!showErrorBool)
//						{
//							showErrorBool = true;
//							tracePanel.showAlertsWithLevel(AlertLevel.ERROR);
//							errorButton.setText("Hide Level: ERROR");
//						}
//						else if (showErrorBool)
//						{
//							showErrorBool = false;
//							tracePanel.hideAlertsWithLevel(AlertLevel.ERROR);
//							errorButton.setText("Show Level: ERROR");
//						}
//						//================================================================================
//					}
//				});
//				restaurantMessagesButton.addActionListener(new ActionListener() 
//				{
//					@Override
//					public void actionPerformed(ActionEvent e) 
//					{
//						//============================ TUTORIAL ==========================================
//						//This is how you make messages with a certain Level (normal MESSAGE here) show up in the trace panel.
//						if(!showRestaurantMsgBool)
//						{
//							showRestaurantMsgBool = true;
//							tracePanel.showAlertsWithTag(AlertTag.RESTAURANT);
//							restaurantMessagesButton.setText("Hide Tag : RESTAURANT");
//						}
//						else if (showRestaurantMsgBool)
//						{
//							showRestaurantMsgBool = false;
//							tracePanel.hideAlertsWithTag(AlertTag.RESTAURANT);
//							restaurantMessagesButton.setText("Show Tag : RESTAURANT");
//						}
//						//================================================================================
//					}
//				});
//				bankMessagesButton.addActionListener(new ActionListener() 
//				{
//					@Override
//					public void actionPerformed(ActionEvent e) 
//					{
//						//============================ TUTORIAL ==========================================
//						//This is how you make messages with a certain Level (normal MESSAGE here) show up in the trace panel.
//						if(!showBankMsgBool)
//						{
//							showBankMsgBool = true;
//							tracePanel.showAlertsWithTag(AlertTag.BANK);
//							bankMessagesButton.setText("Hide Tag : BANK");
//						}
//						else if (showBankMsgBool)
//						{
//							showBankMsgBool = false;
//							tracePanel.hideAlertsWithTag(AlertTag.BANK);
//							bankMessagesButton.setText("Show Tag : BANK");
//						}
//						//================================================================================
//					}
//				});
//				marketMessagesButton.addActionListener(new ActionListener() 
//				{
//					@Override
//					public void actionPerformed(ActionEvent e) 
//					{
//						//============================ TUTORIAL ==========================================
//						//This is how you make messages with a certain Level (normal MESSAGE here) show up in the trace panel.
//						if(!showMarketMsgBool)
//						{
//							showMarketMsgBool = true;
//							tracePanel.showAlertsWithTag(AlertTag.MARKET);
//							marketMessagesButton.setText("Hide Tag : MARKET");
//						}
//						else if (showMarketMsgBool)
//						{
//							showMarketMsgBool = false;
//							tracePanel.hideAlertsWithTag(AlertTag.MARKET);
//							marketMessagesButton.setText("Show Tag : MARKET");
//						}
//						//================================================================================
//					}
//				});
//				housingMessagesButton.addActionListener(new ActionListener() 
//				{
//					@Override
//					public void actionPerformed(ActionEvent e) 
//					{
//						//============================ TUTORIAL ==========================================
//						//This is how you make messages with a certain Level (normal MESSAGE here) show up in the trace panel.
//						if(!showHousingMsgBool)
//						{
//							showHousingMsgBool = true;
//							tracePanel.showAlertsWithTag(AlertTag.HOUSING);
//							housingMessagesButton.setText("Hide Tag : HOUSING");
//						}
//						else if (showHousingMsgBool)
//						{
//							showHousingMsgBool = false;
//							tracePanel.hideAlertsWithTag(AlertTag.HOUSING);
//							housingMessagesButton.setText("Show Tag : HOUSING");
//						}
//						//================================================================================
//					}
//				});
//				generalCityMessagesButton.addActionListener(new ActionListener() 
//				{
//					@Override
//					public void actionPerformed(ActionEvent e) 
//					{
//						//============================ TUTORIAL ==========================================
//						//This is how you make messages with a certain Level (normal MESSAGE here) show up in the trace panel.
//						if(!showGeneralCityMsgBool)
//						{
//							showGeneralCityMsgBool = true;
//							tracePanel.showAlertsWithTag(AlertTag.GENERAL_CITY);
//							generalCityMessagesButton.setText("Hide Tag : GENERAL CITY");
//						}
//						else if (showGeneralCityMsgBool)
//						{
//							showGeneralCityMsgBool = false;
//							tracePanel.hideAlertsWithTag(AlertTag.GENERAL_CITY);
//							generalCityMessagesButton.setText("Show Tag : GENERAL CITY");
//						}
//						//================================================================================
//					}
//				});
//
//				this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//				this.add(messagesButton);
//				this.add(errorButton);
//				this.add(restaurantMessagesButton);
//				this.add(bankMessagesButton);
//				this.add(housingMessagesButton);
//				this.add(marketMessagesButton);
//				this.add(generalCityMessagesButton);
//				this.setMinimumSize(new Dimension(50, 600));
//			}
//		}
	}
