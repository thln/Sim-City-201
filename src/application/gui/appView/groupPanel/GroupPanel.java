package application.gui.appView.groupPanel;

import javax.swing.*;

import application.*;
import application.gui.appView.*;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class GroupPanel extends JPanel implements ActionListener{
	final static String  LISTVIEW = "ListView";
	final static String PROFILEVIEW = "ProfileView";
	private String type;
	private JPanel flipPanel = new JPanel(new CardLayout());
	private JPanel ListView = new JPanel();
	private JPanel ProfileView = new JPanel();
	private JButton BackToListView = new JButton("Back");
	private JPanel buttonList = new JPanel();
	public JScrollPane pane = 
			new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
							JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private Dimension paneDim;
    private Dimension buttonSize;
    private List<JButton> list = new ArrayList<JButton>();
    JButton button = new JButton();
	JButton button1 = new JButton();
	private ApplicationPanel appPanel;
	private Application app;
    
	public GroupPanel(String type, Color bg, ApplicationPanel appPanel, Application app){
		this.appPanel = appPanel;
		this.app = app;
		setLayout(new BorderLayout());
		setBackground(bg);
		this.type = type;
		JLabel title  = new JLabel(type);
		Font titleFont = new Font("Serif", Font.BOLD,18);
		title.setFont(titleFont);
		add(title,BorderLayout.PAGE_START);
		
		//ListView Panel
		paneDim = new Dimension(this.getSize());
		Dimension buttonSize = new Dimension(paneDim.width, (int) (paneDim.height /7));
		buttonList.setLayout(new BoxLayout(buttonList, BoxLayout.Y_AXIS));
		/*
		for(int i = 0; i<app.getPopulationSize();i++){
			if(app.getPerson(i).getType()== type){
				JButton button = new JButton();
				button.setText(app.getPerson(i).getName());
				button.setPreferredSize(buttonSize);
		        button.addActionListener(this);
		        list.add(button);
	        }
		}
		*/
		pane.setViewportView(buttonList);
		buttonSize = new Dimension(paneDim.width, (int) (paneDim.height /7));
        pane.setSize(paneDim);
        
        ListView.add(pane);
        ListView.setVisible(true);
        
        //
        
        //ProfileView Panel
        ProfileView.setLayout(new BorderLayout());
        ProfileView.setVisible(true);
        //TODO Set BackToListView Dimensions
        BackToListView.addActionListener(this);
        ProfileView.add(BackToListView, BorderLayout.PAGE_END);
        //
        
        flipPanel.add(ListView, LISTVIEW); 
        flipPanel.add(ProfileView, PROFILEVIEW);
        
        add(flipPanel, BorderLayout.CENTER);
        
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== BackToListView){
			GoBack();
		}
		else{
			for(JButton temp: list){
				if(e.getSource() == temp){
					CardLayout c1 = (CardLayout) (flipPanel.getLayout());
					//updateProfileView();
					c1.show(flipPanel, PROFILEVIEW);
				}
			}
		}
	}
	
	public void updatePersonList(){
		list = new ArrayList<JButton>();
		Dimension paneSize = pane.getSize();
		Dimension buttonSize = new Dimension(paneSize.width, (int) (paneSize.height /7));
		for(int i = 0; i<app.getPopulationSize();i++){
			//if(app.getPerson(i).getType()== type){
				JButton button = new JButton();
				button.setText(app.getPerson(i).getName());
				button.setSize(buttonSize);
		        button.addActionListener(this);
		        list.add(button);
	        //}
		}
	}
	
	//Adds person's profile quick view onto a Jbutton and adds this button to the list pane
	public void addPerson(String name, int money, String type,
			String jobTitle, int startT, int lunchT, int endT)
	{
			if(name !=null){
				Dimension paneSize = pane.getSize();
			    
				//Profile Quick View Button
				Dimension buttonSize = new Dimension(paneSize.width, (int) (paneSize.height /7));
				JButton button = new JButton();
				button.setText(name);
				button.setSize(buttonSize);
	            button.addActionListener(this);
	            list.add(button);
			}
		}
		
		
		public void updateProfileView(String name){
			//Profile Full view
			JLabel  fullView = new JLabel();
			fullView.setText(name);
			add(fullView);

		}
		
		
	public void GoBack(){
		CardLayout c1 = (CardLayout) (flipPanel.getLayout());
		c1.show(flipPanel, LISTVIEW);
	}
	
}
