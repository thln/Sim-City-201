package application.gui.trace;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PrintControlPanel extends JPanel {

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

	public PrintControlPanel(final TracePanel tracePanel) 
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


		messagesButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//============================ TUTORIAL ==========================================
				//This is how you make messages with a certain Level (normal MESSAGE here) show up in the trace panel.
				if(!showMessageBool)
				{
					showMessageBool = true;
					tracePanel.showAlertsWithLevel(AlertLevel.MESSAGE);
					messagesButton.setText("Hide Level: MESSAGE");
				}
				else if (showMessageBool)
				{
					showMessageBool = false;
					tracePanel.hideAlertsWithLevel(AlertLevel.MESSAGE);
					messagesButton.setText("Show Level: MESSAGE");
				}
				//================================================================================
			}
		});
		errorButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//============================ TUTORIAL ==========================================
				//This is how you make messages with a certain Level (normal MESSAGE here) show up in the trace panel.
				if(!showErrorBool)
				{
					showErrorBool = true;
					tracePanel.showAlertsWithLevel(AlertLevel.ERROR);
					errorButton.setText("Hide Level: ERROR");
				}
				else if (showErrorBool)
				{
					showErrorBool = false;
					tracePanel.hideAlertsWithLevel(AlertLevel.ERROR);
					errorButton.setText("Show Level: ERROR");
				}
				//================================================================================
			}
		});
		restaurantMessagesButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//============================ TUTORIAL ==========================================
				//This is how you make messages with a certain Level (normal MESSAGE here) show up in the trace panel.
				if(!showRestaurantMsgBool)
				{
					showRestaurantMsgBool = true;
					tracePanel.showAlertsWithTag(AlertTag.RESTAURANT);
					restaurantMessagesButton.setText("Hide Tag : RESTAURANT");
				}
				else if (showRestaurantMsgBool)
				{
					showRestaurantMsgBool = false;
					tracePanel.hideAlertsWithTag(AlertTag.RESTAURANT);
					restaurantMessagesButton.setText("Show Tag : RESTAURANT");
				}
				//================================================================================
			}
		});
		bankMessagesButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//============================ TUTORIAL ==========================================
				//This is how you make messages with a certain Level (normal MESSAGE here) show up in the trace panel.
				if(!showBankMsgBool)
				{
					showBankMsgBool = true;
					tracePanel.showAlertsWithTag(AlertTag.BANK);
					bankMessagesButton.setText("Hide Tag : BANK");
				}
				else if (showBankMsgBool)
				{
					showBankMsgBool = false;
					tracePanel.hideAlertsWithTag(AlertTag.BANK);
					bankMessagesButton.setText("Show Tag : BANK");
				}
				//================================================================================
			}
		});
		marketMessagesButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//============================ TUTORIAL ==========================================
				//This is how you make messages with a certain Level (normal MESSAGE here) show up in the trace panel.
				if(!showMarketMsgBool)
				{
					showMarketMsgBool = true;
					tracePanel.showAlertsWithTag(AlertTag.MARKET);
					marketMessagesButton.setText("Hide Tag : MARKET");
				}
				else if (showMarketMsgBool)
				{
					showMarketMsgBool = false;
					tracePanel.hideAlertsWithTag(AlertTag.MARKET);
					marketMessagesButton.setText("Show Tag : MARKET");
				}
				//================================================================================
			}
		});
		housingMessagesButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//============================ TUTORIAL ==========================================
				//This is how you make messages with a certain Level (normal MESSAGE here) show up in the trace panel.
				if(!showHousingMsgBool)
				{
					showHousingMsgBool = true;
					tracePanel.showAlertsWithTag(AlertTag.HOUSING);
					housingMessagesButton.setText("Hide Tag : HOUSING");
				}
				else if (showHousingMsgBool)
				{
					showHousingMsgBool = false;
					tracePanel.hideAlertsWithTag(AlertTag.HOUSING);
					housingMessagesButton.setText("Show Tag : HOUSING");
				}
				//================================================================================
			}
		});
		generalCityMessagesButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//============================ TUTORIAL ==========================================
				//This is how you make messages with a certain Level (normal MESSAGE here) show up in the trace panel.
				if(!showGeneralCityMsgBool)
				{
					showGeneralCityMsgBool = true;
					tracePanel.showAlertsWithTag(AlertTag.GENERAL_CITY);
					generalCityMessagesButton.setText("Hide Tag : GENERAL CITY");
				}
				else if (showGeneralCityMsgBool)
				{
					showGeneralCityMsgBool = false;
					tracePanel.hideAlertsWithTag(AlertTag.GENERAL_CITY);
					generalCityMessagesButton.setText("Show Tag : GENERAL CITY");
				}
				//================================================================================
			}
		});

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(messagesButton);
		this.add(errorButton);
		this.add(restaurantMessagesButton);
		this.add(bankMessagesButton);
		this.add(housingMessagesButton);
		this.add(marketMessagesButton);
		this.add(generalCityMessagesButton);
		this.setMinimumSize(new Dimension(50, 600));
	}
}
