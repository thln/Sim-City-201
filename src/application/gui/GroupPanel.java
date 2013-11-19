package application.gui;

import javax.swing.*;
import person.Profile;
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

    
	GroupPanel(String type, Color bg){
		this.setBackground(bg);
		this.type = type;
		
		//ListView Panel 
		buttonList.setLayout(new BoxLayout(buttonList, BoxLayout.Y_AXIS));
		pane.setViewportView(buttonList);
		paneDim = new Dimension(this.getSize());
		buttonSize = new Dimension(paneDim.width, (int) (paneDim.height /7));
        pane.setSize(paneDim);
        
        ListView.add(pane);
        ListView.setVisible(true);
        
        ///Temp Testing
		button.setText("Person1");
		button.setSize(buttonSize);
        button.addActionListener(this);
        list.add(button);
        button1.setText("Person2");
		button1.setSize(buttonSize);
        button1.addActionListener(this);
        list.add(button1);
        //Temp Testing End
        
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
        
        add(flipPanel);
        
		
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
	
	//Adds person's profile quick view onto a Jbutton and adds this button to the list pane
		public void addPerson(Profile profile){
			if(profile.getName() !=null){
				Dimension paneSize = pane.getSize();
			    
				//Profile Quick View Button
				Dimension buttonSize = new Dimension(paneSize.width, (int) (paneSize.height /7));
				JButton button = new JButton();
				button.setText(profile.printQuickView());
				button.setSize(buttonSize);
	            button.addActionListener(this);
	            list.add(button);
			}
		}
		
		
		public void updateProfileView(Profile profile){
			//Profile Full view
			JLabel  fullView = new JLabel();
			fullView.setText(profile.printFullView());
			add(fullView);

		}
		
		
	public void GoBack(){
		CardLayout c1 = (CardLayout) (flipPanel.getLayout());
		c1.show(flipPanel, LISTVIEW);
	}
	
}
