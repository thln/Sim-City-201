package application.gui.appView.controlPanel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

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

	JPanel mainPanel = new JPanel();
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
	
	private JPanel restaurantInfoPanel;
	public JScrollPane scrollInfoPane =
            new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JLabel jobNameLabel = new JLabel("Job   Name");
	private List<JButton> workerButtons = new ArrayList<JButton>(); 

	
	public SelectBuilding(ControlPanel cp, Application app, ApplicationPanel appPanel)
	{
		this.cp = cp;
		this.app = app;
		this.appPanel = appPanel;
		this.setLayout((new BoxLayout((Container) this, BoxLayout.X_AXIS)));
		//setLayout(new GridLayout(1,0));
		
		restaurantInfoPanel = new JPanel();
		restaurantInfoPanel.setLayout(new BoxLayout((Container)restaurantInfoPanel, BoxLayout.Y_AXIS));
		scrollInfoPane.setViewportView(restaurantInfoPanel);
		scrollInfoPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
		Dimension paneDim = new Dimension(250, 500);
		scrollInfoPane.setPreferredSize(paneDim);
		scrollInfoPane.setMinimumSize(paneDim);
		scrollInfoPane.setMaximumSize(paneDim);
		//updateList();
        
		add(scrollInfoPane);
		
		
		
		GridBagConstraints gbcConstraints = new GridBagConstraints();
		gbcConstraints.fill = GridBagConstraints.VERTICAL;
		gbcConstraints.anchor = GridBagConstraints.CENTER;
		//mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(10,2));
		
		gbcConstraints.gridx = 0;
		gbcConstraints.gridy = 0;
		mainPanel.add(selectBuildingLabel, gbcConstraints);
		gbcConstraints.gridx = 0;
		gbcConstraints.gridy = 1;
		selectBuildingBox = new JComboBox(allBuildings);
		selectBuildingBox.addActionListener(this);
		mainPanel.add(selectBuildingBox, gbcConstraints);
		
		//closeBuildingButton;
		gbcConstraints.gridx = 1;
		gbcConstraints.gridy = 0;
		openCloseBuildingButton.setEnabled(false);
		openCloseBuildingButton.addActionListener(this);
		//openCloseBuildingButton.setSize(paneDim);
		mainPanel.add(openCloseBuildingButton, gbcConstraints);
		
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
				updateWorkerList();
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
	
	public void updateWorkerList()
	{
		workerButtons.clear();
		restaurantInfoPanel.removeAll();
		if(selectBuildingBox.getSelectedItem() == " ")
		{
			return;
		}
		else
		{
			if(selectBuildingBox.getSelectedItem() == "East Bank")
			{
				JButton button = new JButton(Phonebook.getPhonebook().getEastBank().bankGuardRole.getRoleName() + " " + Phonebook.getPhonebook().getEastBank().bankGuardRole.getName());
				button.setMaximumSize(new Dimension(250, 25));
				workerButtons.add(button);
				restaurantInfoPanel.add(button);
			}
			if(selectBuildingBox.getSelectedItem() == "West Bank")
			{
				
			}
			if(selectBuildingBox.getSelectedItem() == "East Market")
			{
				
			}
			if(selectBuildingBox.getSelectedItem() == "West Market")
			{
			 	
			}
			if(selectBuildingBox.getSelectedItem() == "Chinese Restaurant")
			{
				
			}
			if(selectBuildingBox.getSelectedItem() == "Housing Maintenance Company")
			{
				
			}
			
		}
		validate();
	}
}


