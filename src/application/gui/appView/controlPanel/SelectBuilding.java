package application.gui.appView.controlPanel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import application.*;
import application.gui.appView.*;
import application.gui.appView.listPanel.ListPanel.Profile;
import application.gui.animation.*;
import application.gui.trace.AlertLog;
import application.gui.trace.AlertTag;
import application.gui.trace.TracePanel;

public class SelectBuilding extends JPanel implements ActionListener
{
	CityPanel animP;
	ApplicationPanel appPanel;
	Application app;
	ControlPanel cp;
	int editIndex = 0;

	JPanel mainPanel;
	JComboBox selectBuildingBox;
	JButton openCloseBuildingButton = new JButton("Close Building");
	JLabel selectBuildingLabel = new JLabel("Select a Building");
	private String[] allBuildings = {" ", "East Bank", "West Bank", "East Market", "West Market", 
			"Chinese Restaurant", "Housing Maintenance Company"};
	private boolean eastBankIsOpen = true;
	private boolean westBankIsOpen = true;
	private boolean eastMarketIsOpen = true;
	private boolean westMarketIsOpen = true;
	private boolean chineseRestaurantIsOpen = true;
	private boolean housingMaintenanceCompanyIsOpen = true;

	public SelectBuilding(ControlPanel cp, Application app, ApplicationPanel appPanel)
	{
		this.cp = cp;
		this.app = app;
		this.appPanel = appPanel;
		
		GridBagConstraints gbcConstraints = new GridBagConstraints();
		gbcConstraints.fill = GridBagConstraints.VERTICAL;
		gbcConstraints.anchor = GridBagConstraints.CENTER;
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(4,2));
		
		gbcConstraints.gridx = 0;
		gbcConstraints.gridy = 0;
		//selectBuildingLabel = new JLabel("Select a Building");
		mainPanel.add(selectBuildingLabel, gbcConstraints);
		gbcConstraints.gridx = 0;
		gbcConstraints.gridy = 1;
		selectBuildingBox = new JComboBox(allBuildings);
		selectBuildingBox.addActionListener(this);
		mainPanel.add(selectBuildingBox, gbcConstraints);
		
		//closeBuildingButton;
		openCloseBuildingButton.setEnabled(false);
		openCloseBuildingButton.addActionListener(this);
		mainPanel.add(openCloseBuildingButton);
		
		add(mainPanel);
	}

	///@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == selectBuildingBox)
		{
			System.out.print("Test");
			if(selectBuildingBox.getSelectedItem() == " ")
			{
				openCloseBuildingButton.setEnabled(false);
			}
			else
			{
				if(selectBuildingBox.getSelectedItem() == "East Bank")
				{
					if(eastBankIsOpen)
					{
						openCloseBuildingButton.setText("Close Building");
					}
					else
					{
						openCloseBuildingButton.setText("Open Building");
					}
				}
				if(selectBuildingBox.getSelectedItem() == "West Bank")
				{
					if(westBankIsOpen)
					{
						openCloseBuildingButton.setText("Close Building");
					}
					else
					{
						openCloseBuildingButton.setText("Open Building");
					}
				}
				if(selectBuildingBox.getSelectedItem() == "East Market")
				{
					if(eastMarketIsOpen)
					{
						openCloseBuildingButton.setText("Close Building");
					}
					else
					{
						openCloseBuildingButton.setText("Open Building");
					}
				}
				if(selectBuildingBox.getSelectedItem() == "West Market")
				{
					if(westMarketIsOpen)
					{
						openCloseBuildingButton.setText("Close Building");
					}
					else
					{
						openCloseBuildingButton.setText("Open Building");
					}
				}
				if(selectBuildingBox.getSelectedItem() == "Chinese Restaurant")
				{
					if(chineseRestaurantIsOpen)
					{
						openCloseBuildingButton.setText("Close Building");
					}
					else
					{
						openCloseBuildingButton.setText("Open Building");
					}
				}
				if(selectBuildingBox.getSelectedItem() == "Housing Maintenance Company")
				{
					if(housingMaintenanceCompanyIsOpen)
					{
						openCloseBuildingButton.setText("Close Building");
					}
					else
					{
						openCloseBuildingButton.setText("Open Building");
					}
				}
				openCloseBuildingButton.setEnabled(true);
			}
		}
		if (e.getSource() == openCloseBuildingButton)
		{
			if(selectBuildingBox.getSelectedItem() == "East Bank")
			{
				if(eastBankIsOpen)
				{
					eastBankIsOpen = false;
					Phonebook.getPhonebook().closeBuilding("eastBank");
					openCloseBuildingButton.setText("Open Building");
					AlertLog.getInstance().logInfo(AlertTag.GENERAL_CITY,selectBuildingBox.getSelectedItem().toString(), "is now closed.");

				}
				else
				{
					eastBankIsOpen = true;
					Phonebook.getPhonebook().openBuilding("eastBank");
					openCloseBuildingButton.setText("Close Building");
					AlertLog.getInstance().logInfo(AlertTag.GENERAL_CITY,selectBuildingBox.getSelectedItem().toString(), "is now open.");

				}
			}
			if(selectBuildingBox.getSelectedItem() == "West Bank")
			{
				if(westBankIsOpen)
				{
					westBankIsOpen = false;
					//Phonebook.getPhonebook().closeBuilding("westBank");
					openCloseBuildingButton.setText("Open Building");
					AlertLog.getInstance().logInfo(AlertTag.GENERAL_CITY,selectBuildingBox.getSelectedItem().toString(), "is now closed.");

				}
				else
				{
					westBankIsOpen = true;
					//Phonebook.getPhonebook().openBuilding("westBank");
					openCloseBuildingButton.setText("Close Building");
					AlertLog.getInstance().logInfo(AlertTag.GENERAL_CITY,selectBuildingBox.getSelectedItem().toString(), "is now open.");

				}
			}
			if(selectBuildingBox.getSelectedItem() == "East Market")
			{
				if(eastMarketIsOpen)
				{
					eastMarketIsOpen = false;
					Phonebook.getPhonebook().closeBuilding("eastMarket");
					openCloseBuildingButton.setText("Open Building");
					AlertLog.getInstance().logInfo(AlertTag.GENERAL_CITY,selectBuildingBox.getSelectedItem().toString(), "is now closed.");
				}
				else
				{
					eastMarketIsOpen = true;
					Phonebook.getPhonebook().openBuilding("eastMarket");
					openCloseBuildingButton.setText("Close Building");
					AlertLog.getInstance().logInfo(AlertTag.GENERAL_CITY,selectBuildingBox.getSelectedItem().toString(), "is now open.");
				}
			}
			if(selectBuildingBox.getSelectedItem() == "West Market")
			{
				if(westMarketIsOpen)
				{
					westMarketIsOpen = false;
					//Phonebook.getPhonebook().closeBuilding("westMarket");
					openCloseBuildingButton.setText("Open Building");
					AlertLog.getInstance().logInfo(AlertTag.GENERAL_CITY,selectBuildingBox.getSelectedItem().toString(), "is now closed.");
				}
				else
				{
					westMarketIsOpen = true;
					//Phonebook.getPhonebook().openBuilding("westMarket");
					openCloseBuildingButton.setText("Close Building");
					AlertLog.getInstance().logInfo(AlertTag.GENERAL_CITY,selectBuildingBox.getSelectedItem().toString(), "is now open.");

				}
			}
			if(selectBuildingBox.getSelectedItem() == "Chinese Restaurant")
			{
				if(chineseRestaurantIsOpen)
				{
					chineseRestaurantIsOpen = false;
					//Phonebook.getPhonebook().closeBuilding("chineseRestaurant");
					openCloseBuildingButton.setText("Open Building");
					AlertLog.getInstance().logInfo(AlertTag.GENERAL_CITY,selectBuildingBox.getSelectedItem().toString(), "is now closed.");
				}
				else
				{
					chineseRestaurantIsOpen = true;
					//Phonebook.getPhonebook().openBuilding("chineseRestaurant");
					openCloseBuildingButton.setText("Close Building");
					AlertLog.getInstance().logInfo(AlertTag.GENERAL_CITY,selectBuildingBox.getSelectedItem().toString(), "is now open.");
				}
			}
			if(selectBuildingBox.getSelectedItem() == "Housing Maintenance Company")
			{
				if(housingMaintenanceCompanyIsOpen)
				{
					housingMaintenanceCompanyIsOpen = false;
					Phonebook.getPhonebook().closeBuilding("housingMaintenanceCompany");
					openCloseBuildingButton.setText("Open Building");
					AlertLog.getInstance().logInfo(AlertTag.GENERAL_CITY,selectBuildingBox.getSelectedItem().toString(), "is now closed.");
				}
				else
				{
					housingMaintenanceCompanyIsOpen = true;
					Phonebook.getPhonebook().openBuilding("housingMaintenanceCompany");
					openCloseBuildingButton.setText("Close Building");
					AlertLog.getInstance().logInfo(AlertTag.GENERAL_CITY,selectBuildingBox.getSelectedItem().toString(), "is now open.");
				}
			}
		}

	}
}


