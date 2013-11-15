package application.gui;

import javax.swing.*;
import person.Profile;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class GroupPanel extends JPanel implements ActionListener{
	private JPanel ListView = new JPanel();
	private JButton BackToListView = new JButton("Back");
	private JPanel buttonList = new JPanel();
	private JPanel ProfileView = new JPanel();
	public JScrollPane pane = 
			new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
							JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private List<JButton> list = new ArrayList<JButton>();
    
    
	GroupPanel(String type, Color bg){
		this.setBackground(bg);
		//ListView Panel 
		buttonList.setLayout(new BoxLayout((Container) buttonList, BoxLayout.Y_AXIS));
		pane.setViewportView(buttonList);
		Dimension paneDim = new Dimension(this.getSize());
        pane.setPreferredSize(paneDim);
       
        ListView.add(pane);
        
        //
        
        //ProfileView Panel
        ProfileView.setLayout(new BorderLayout());
        
        //TODO Set BackToListView Dimensions
        BackToListView.addActionListener(this);
        ProfileView.add(BackToListView, BorderLayout.PAGE_END);
        //

        ListView.setVisible(true);
        ProfileView.setVisible(false);
        add(ListView); 
        add(ProfileView);
        
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== BackToListView){
			GoBack();
		}
		else{
			for(JButton temp: list){
				if(e.getSource() == temp){
					//updateProfileView();
					ProfileView.setVisible(true);
				}
			}
			
			
			
		}
	}
	
	//Adds person's profile quick view onto a Jbutton and adds this button to the list pane
		public void addPerson(Profile profile){
			if(profile.getName() !=null){
				Dimension paneSize = pane.getSize();
			    
				//Profile Quick View Button
				Dimension buttonSize = new Dimension(paneSize.width-20, (int) (paneSize.height /7));
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
		ListView.setVisible(true);
        ProfileView.setVisible(false);
	}
	
}
