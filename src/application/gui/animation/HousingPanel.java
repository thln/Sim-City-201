package application.gui.animation;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import housing.*;
import application.gui.animation.agentGui.*;

public class HousingPanel extends BuildingPanel implements MouseListener{

	private List<Housing> housing = Collections.synchronizedList(new ArrayList<Housing>());
	private List<Rectangle> aptRects = Collections.synchronizedList(new ArrayList<Rectangle>());
	private String type = "";
	BufferedImage arrow = null;
	Rectangle arrowRect;
	Building apartment;
	
	public HousingPanel(String buildName, AnimationPanel ap) {
		super(buildName, ap);
		
		addMouseListener(this);
		try {
            arrow = ImageIO.read(new File("res/arrow.gif"));
        } catch (IOException e) {
        }
		arrowRect = new Rectangle(0, WINDOWY/2-50, arrow.getWidth(), arrow.getHeight());
	}
	
	public void actionPerformed(ActionEvent e) {
		repaint();  //Will have paintComponent called
	}

	public void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D)g;
		
		//Clear the screen by painting a rectangle the size of the frame
		g2.setColor(new Color(255, 229, 204));	
		
		g2.fillRect(0, 0, WINDOWX, WINDOWY );	
		g2.setColor(Color.RED);

		if(name == "name")
			g.drawString("", WINDOWX/2, 10);
		else
			g.drawString(getName(), WINDOWX/2, 10);

		//different layouts based on their type       
		g2.setColor(Color.BLACK);
		if (name.toLowerCase().contains("house")) {
			//drawing the different rooms
			g2.setColor(Color.LIGHT_GRAY);
			g2.fillRect(0, 0, 150, 100); //bathroom
			g2.fillRect(0, WINDOWY-120, 200, 120); //living room

			//bed
			g2.fillRect(WINDOWX - 100, WINDOWY - 120, 60, 100);
			g2.setColor(Color.PINK);
			g2.fillRect(WINDOWX - 100, WINDOWY - 80, 60, 70);
			g2.setColor(Color.WHITE);
			g2.fillRect(WINDOWX - 90, WINDOWY - 110, 40, 20);

			//KITCHEN/////
			//fridge
			g2.fillRect(WINDOWX*3/4, 20, 40, 20);
			//dining room table
			g2.setColor(Color.ORANGE);
			g2.fillOval(WINDOWX/2, 20, 60, 50);

			//labelings
			g2.setColor(Color.BLACK);
			g2.drawString("Bathroom", 20, 20);
			g2.drawString("Living Room", 20, WINDOWY - 100);
			g2.drawString("Kitchen", WINDOWX - 100, 130);
			g2.drawString("table", WINDOWX/2+20, 50);

			//stove
			g2.fillRect(WINDOWX - 100, 20, 60, 55);
			g2.setColor(Color.RED);
			g2.fillOval(WINDOWX-90, 30, 15, 15);
			g2.fillOval(WINDOWX-65, 30, 15, 15);
			g2.fillOval(WINDOWX-90, 50, 15, 15);
			g2.fillOval(WINDOWX-65, 50, 15, 15);

			if(type.equals("Apartment")) {
				g2.drawImage(arrow, 0, WINDOWY/2-50, null);
			}
		}
		else if(name.toLowerCase().contains("apartment")) {
			//for(int h=0; h<apartments;h++) {
			//	g2.setColor(Color.BLACK);
	        	int rows = housing.size()/10;
	        	int cols = 10;
	        	int xremainder = housing.size() - cols*rows;
	        	int counter = 0;
	        	Font myFont = new Font("Times", Font.PLAIN, 12);
	        	//fills up a grid w/ specified rows and columns
    			//xloc is x location, yloc is y location of apt
				for (int iy = 1; iy <= rows; iy++) {
	        		for(int ix=1; ix <= cols; ix++) {
	        			g2.setColor(Color.ORANGE);
	        			g2.fill3DRect(50*ix, 80*iy-50, 45, 45, true);
	        			if(aptRects.get(counter).getX() == 0 && aptRects.get(counter).getY() == 0)
	        				aptRects.get(counter).setLocation(50*ix, 80*iy-50);
	        			g2.setColor(Color.BLACK);
	        			g2.drawString(housing.get(counter).getHousingNumber()+"", 50*ix+10, 80*iy-35);
	        			g2.setFont(myFont);
	        			g2.drawString(housing.get(counter).getOccupantName(), 50*ix, 80*iy-15);
	        			counter++;
	        		}
	        	}
				//creates the remainder apartments
	        	for(int ix=1; ix<= xremainder; ix++) {
	        		g2.setColor(Color.ORANGE);
	        		//g2.fillRect(50*ix, 80*(rows+1)-50, 45, 45);
        			g2.fill3DRect(50*ix, 80*(rows+1)-50, 45, 45, true);
        			if(aptRects.get(counter).getX() == 0 && aptRects.get(counter).getY() == 0)
        				aptRects.get(counter).setLocation(50*ix, 80*(rows+1)-50);
	        		g2.setColor(Color.BLACK);
	        		g2.drawString(housing.get(counter).getHousingNumber()+"", 50*ix+10, 80*(rows+1)-35);
	        		g2.setFont(myFont);
	        		g2.drawString(housing.get(counter).getOccupantName(), 50*ix, 80*(rows+1)-15);
	        		counter++;
	        	}
		//	}
		}

		synchronized(guis){
			for(Gui gui : guis) {
				if (gui.isPresent()) {
					gui.updatePosition();
				}
			}

			for(Gui gui : guis) {
				if (gui.isPresent()) {
					gui.draw(g2);
				}
			}
			
		}
	}

	public void displayBuildingPanel() {
		myCity.displayBuildingPanel(this);
	}
	
	public void addAptUnit(Housing house) {
		housing.add(house);
		
		aptRects.add(new Rectangle(0, 0, 45, 45));
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public void setAptBuilding(Building b) {
		apartment = b;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		//If any of the houseIcons are clicked, it will display the appropriate panel
		for(int i=0; i<aptRects.size();i++) {
			if(aptRects.get(i).contains(e.getX(), e.getY()) ) {
				housing.get(i).getPanel().displayBuildingPanel();
			}
		}
		if(arrowRect.contains(e.getX(), e.getY())) {
			apartment.myBuildingPanel.displayBuildingPanel();
		}
	}

	public void mousePressed(MouseEvent e) { }

	public void mouseReleased(MouseEvent e) { }

	public void mouseEntered(MouseEvent e) { }

	public void mouseExited(MouseEvent e) { }
	
}
