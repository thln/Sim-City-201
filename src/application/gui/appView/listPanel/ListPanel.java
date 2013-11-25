package application.gui.appView.listPanel;

import javax.swing.*;

import person.Crook;
import person.Deadbeat;
import person.Person;
import person.Wealthy;
import person.Worker;
import person.Worker.Job;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

import application.*;
import application.gui.appView.*;
import application.gui.appView.controlPanel.*;

public class ListPanel extends JPanel implements ActionListener{
	private JPanel listPane;
	private JPanel infoPane;
	private Application app;
	private List<JButton> buttons = new ArrayList<JButton>(); 
	private List<Profile> people = new ArrayList<Profile>();
	public JScrollPane pane =
	            new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	                    		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JTextArea personInfoArea = new JTextArea();
	
	public ListPanel(ApplicationPanel appPanel, Application app){
		this.app = app;
		setLayout(new GridLayout(1,2));
		
		listPane = new JPanel();
		listPane.setLayout(new BoxLayout((Container)listPane, BoxLayout.Y_AXIS));
		updateList();
		pane.setViewportView(listPane);
		
		listPane.setPreferredSize(new Dimension((1/4)*(this.getWidth()),this.getHeight()));
		add(pane);
		
		infoPane = new JPanel();
		personInfoArea.setText("Select A Person");
		infoPane.add(personInfoArea);
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
					button.setBackground(Color.white);
				}
				else if(type.equals("Crook")){
					button.setBackground(Color.lightGray);
				}
				else if(type.equals("Worker")){
					button.setBackground(Color.cyan);
				}
				else if(type.equals("Deadbeat")){
					button.setBackground(Color.orange);
				}
				else{
					button.setBackground(Color.green);
				}
			Dimension paneSize = listPane.getSize();
			Dimension buttonSize = new Dimension(paneSize.width, (1/7)* (paneSize.height));
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
			personInfoArea.setText("Name: " + pf.getName() +"\nIndex: "+ index);
		}
	}
	
	public void updateList(){
		people.clear();
		buttons.clear();
		listPane.removeAll();
		for(int i = 0; i<app.getPopulationSize();i++){
			Person temp = app.getPerson(i);
			JButton button = new JButton(temp.getName());
			if(temp instanceof Crook){
				people.add(new Profile(temp.getName(), (int) temp.getMoney(), "Crook", temp.getCurrentRoleName(), null, 0,0,0));
				button.setBackground(Color.lightGray);
			}
			else if(temp instanceof Deadbeat){
				people.add(new Profile(temp.getName(), (int) temp.getMoney(), "Deadbeat", temp.getCurrentRoleName(), null, 0,0,0));
				button.setBackground(Color.orange);
			}
			else if(temp instanceof Worker){
				//Job tempJob = ((Worker) temp).getJob();
				people.add(new Profile(temp.getName(), (int) temp.getMoney(), "Worker", temp.getCurrentRoleName(), null, 0,0,0));
				button.setBackground(Color.white);
			}
			else if(temp instanceof Wealthy){
				people.add(new Profile(temp.getName(), (int) temp.getMoney(), "Wealthy", temp.getCurrentRoleName(), null, 0,0,0));
				button.setBackground(Color.green);
			}
			else {
				//if the person is none of the types of the type can't be detected, output a black button to indicate an error
				people.add(new Profile(temp.getName(), (int) temp.getMoney(), "None", temp.getCurrentRoleName(), null, 0,0,0));
				button.setBackground(Color.black);
			}
			//Dimension buttonSize = new Dimension(listPane.getSize().width, (1/7)* (listPane.getSize().height));
			//button.setPreferredSize(buttonSize);
			button.addActionListener(this);
			buttons.add(button);
			listPane.add(button);
			validate();
		}
		
	}
	public void editProfile(Profile pf, int index){
		
	}
	
	public int findIndex(String name, String type){
		int index = 0;
		for (index = 0; index<people.size(); index++){
			if(people.get(index).getName()== name && people.get(index).getType()== type){
				break;
			}
		}
		if(index == people.size()){
			index = -1;
		}
		return index;
	}
	public Profile getProfile(int index){
		return people.get(index);
	}
	public String getName(int index){
		return people.get(index).getName();
	}
	public int getListSize(){
		return people.size();
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
		int index;
		
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
		public Profile(){
			this.name = null;
			this.money = 0;
			this.type = null;
			this.jobTitle = null;
			this.jobLocation = null;
			this.startT = 0;
			this.lunchT = 0;
			this.endT = 0;
			
		}
		
		public int getIndex(){
			return index;
		}
		
		public void setIndex(int index){
			this.index = index;
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
