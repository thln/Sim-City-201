package application.gui.appView.listPanel;

import javax.swing.*;

import person.Crook;
import person.DeadBeat;
import person.Person;
import person.Wealthy;
import person.Worker;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class ListPanel extends JPanel implements ActionListener{
	private JPanel listPane;
	private JPanel infoPane;
	private List<JButton> buttons = new ArrayList<JButton>(); 
	private List<JTextArea> descriptions = new ArrayList<JTextArea>();
	public JScrollPane pane =
	            new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	                    		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JTextArea personInfoArea = new JTextArea();
	
	public ListPanel(){
		
		setLayout(new GridLayout(1,2));
		
		listPane = new JPanel();
		listPane.setLayout(new BoxLayout((Container)listPane, BoxLayout.Y_AXIS));
		listPane.setPreferredSize(new Dimension((this.getWidth())*(1/4),this.getHeight()));
		pane.setViewportView(listPane);
		add(pane);
		
		infoPane = new JPanel();
		personInfoArea.setText("Select A Person");
		listPane.setPreferredSize(new Dimension((this.getWidth())*(3/4),this.getHeight()));
		add(infoPane);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(JButton temp:buttons){
			if(e.getSource() == temp){
				
				
			}
			
		}
		
		
		
	}

	public void addPerson(String name, int money, String type,
			String jobTitle, int startT, int lunchT, int endT){
		if(name != null){
			JButton button = new JButton(name);
				if(type.equals("Wealthy")){
					button.setBackground(Color.WHITE);
				}
				else if(type.equals("Crook")){
					button.setBackground(Color.BLUE);
				}
				else if(type.equals("Worker")){
					button.setBackground(Color.GRAY);
				}
				else if(type.equals("Deadbeat")){
					button.setBackground(Color.ORANGE);
				}
			Dimension paneSize = pane.getSize();
			Dimension buttonSize = new Dimension(paneSize.width - 20, (int) (paneSize.height)*(1/7));
			button.setPreferredSize(buttonSize);
			button.addActionListener(this);
			buttons.add(button);
			listPane.add(button);
			validate();
		}
	
	
	}
	public void updateInfoPane(Object person){
		if(person instanceof Person){
			
		}
	}
	
	
	
}
