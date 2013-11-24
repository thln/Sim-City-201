package application.gui.appView.listPanel;

import javax.swing.*;

import person.Crook;
import person.Deadbeat;
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
	private List<Profile> people = new ArrayList<Profile>();
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
		int i = 0;
		for(JButton temp:buttons){
			if(e.getSource() == temp){
				updateInfoPane(people.get(i),i);				
			}
			i++;
		}
	}

	public void addPerson (String name ,int money, String type,
			String jobTitle, String jobLocation, int startT, int lunchT, int endT){
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
			people.add(new Profile(name, money, type, jobTitle,jobLocation,startT,lunchT,endT));
			validate();
		}
	
	
	}
	public void updateInfoPane(Profile pf, int index){
		if(pf != null){
			JTextArea tempInfo = new JTextArea();
			tempInfo.setText("Name: " + pf.getName() +"\nIndex: "+ index);
		}
	}
	
	public void editProfile(){
		
	}
	
	public class Profile{
		String name;
		String type;
		int money;
		String jobTitle;
		String jobLocation;
		int startT;
		int lunchT;
		int endT;
		int current_location;
		int home_location;
		
		public Profile (String name ,int money, String type,
				String jobTitle, String jobLocation, int startT, int lunchT, int endT){
			this.name = name;
			this.money = money;
			this.type = type;
			this.jobTitle = jobTitle;
			this.jobLocation = jobLocation;
			this.startT = startT;
			this.lunchT = lunchT;
			this.endT = endT;
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public int getMoney() {
			return money;
		}

		public void setMoney(int money) {
			this.money = money;
		}

		public String getJobTitle() {
			return jobTitle;
		}

		public void setJobTitle(String jobTitle) {
			this.jobTitle = jobTitle;
		}

		public String getJobLocation() {
			return jobLocation;
		}

		public void setJobLocation(String jobLocation) {
			this.jobLocation = jobLocation;
		}

		public int getStartT() {
			return startT;
		}

		public void setStartT(int startT) {
			this.startT = startT;
		}

		public int getLunchT() {
			return lunchT;
		}

		public void setLunchT(int lunchT) {
			this.lunchT = lunchT;
		}

		public int getEndT() {
			return endT;
		}

		public void setEndT(int endT) {
			this.endT = endT;
		}

		public int getCurrent_location() {
			return current_location;
		}

		public void setCurrent_location(int current_location) {
			this.current_location = current_location;
		}

		public int getHome_location() {
			return home_location;
		}

		public void setHome_location(int home_location) {
			this.home_location = home_location;
		}
	}
	
}
