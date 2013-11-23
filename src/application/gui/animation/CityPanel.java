package application.gui.animation;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;

import javax.imageio.*;

import application.gui.animation.agentGui.Gui;

import java.awt.image.BufferedImage;

public class CityPanel extends JPanel implements ActionListener, MouseListener{

	private final int WINDOWX = 570;
	private final int WINDOWY = 360;

	AnimationPanel animationPanel;
	private List<Gui> guis = new ArrayList<Gui>();
	public ArrayList<Building> buildings = new ArrayList<Building>();
	Dimension Msize = new Dimension(75, 75);
	Dimension Bsize = new Dimension(75, 75);

	//list of images representing our different buildings
	BufferedImage carIcon = null;
	BufferedImage bankIcon = null;
	BufferedImage marketIcon = null;
	BufferedImage houseIcon = null;
	BufferedImage restaurantIcon = null;

	BufferedImage background = null;

	ImageIcon bank = new ImageIcon("docs/bank.png", "bank");
	ImageIcon restaurant = new ImageIcon("docs/restaurant.png", "restaurant");
	ImageIcon market = new ImageIcon("docs/market.png", "market");
	ImageIcon house = new ImageIcon("docs/house.png", "house");

	public CityPanel(AnimationPanel animationPanel) {
		
		this.animationPanel = animationPanel;
		
		setPreferredSize(new Dimension(WINDOWX, WINDOWY));
		setMaximumSize(new Dimension(WINDOWX,WINDOWY));
		setMinimumSize(new Dimension(WINDOWX, WINDOWY));
		setVisible(true);
		setLayout(null);
		setBorder(BorderFactory.createTitledBorder("Los Angeles"));

		try {
			background = ImageIO.read(new File("docs/concrete.jpg"));
		} catch (IOException e) {
		}

		addBuilding("Restaurant", 1);
		addBuilding("Market", 2);
		addBuilding("Bank", 3);
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;

		//Clear the screen by painting a rectangle the size of the frame
		g2.setColor(getBackground());
		g2.fillRect(0, 0, WINDOWX, WINDOWY );
		g2.drawImage(background, 0, 0, null);
		//Here is the table
		//g2.setColor(Color.ORANGE);

		for (Gui gui : guis) {
			if (gui.isPresent()) {
				gui.draw(g2);
			}
		}

	}

	public void addGui(Gui gui) {
		guis.add(gui);   
	}

	public void addBuilding(String name, int i) {
		Building building = new Building(name);

		//Bank building
		if (name.toLowerCase().contains("bank")) {
			building.setIcon(bank);
			Bsize = building.getPreferredSize();
			building.setBounds(WINDOWX - Bsize.width, WINDOWY/2 - Bsize.height/2, Bsize.width, Bsize.height);
		}
		//Market building
		else if (name.toLowerCase().contains("market")) {
			building.setIcon(market);
			Msize = building.getPreferredSize();
			int yLoc;
			if (i == 1)
				yLoc = 0;
			else 
				yLoc = WINDOWY - Msize.height;
			building.setBounds(WINDOWX/2-Msize.width/2, yLoc, Msize.width, Msize.height);
		}
		//Restaurant building
		else if (name.toLowerCase().contains("restaurant")) {

			//building.setIcon(restaurant);
			Dimension size = building.getPreferredSize();
			building.setBounds(WINDOWX/2+Msize.width/2+size.width*(i-1), WINDOWY - size.height, size.width, size.height);
		}
		//House building
		else if (name.toLowerCase().contains("house")) {
			building.setIcon(house);
			Dimension size = building.getPreferredSize();
			if (i < 10)
				building.setBounds(size.width*(i), 0, size.width, size.height);
			else
				building.setBounds(size.width*(i-10), WINDOWY - size.height, size.width, size.height);
		}

		building.setName(name);
		building.addMouseListener(this);
		buildings.add(building);
		add(building);
	}

	public String toString() {
		return "City JPanel";
	}

	public ArrayList<Building> getBuildings() {
		return buildings;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//If any of the icons are clicked, it will find and display its corresponding animation panel

		for (int i=0; i<buildings.size();i++) {
			if (e.getSource() == buildings.get(i)) {
				System.err.println("Pressed button: " + buildings.get(i).getName());
				animationPanel.displayBuildingPanel(buildings.get(i).getBuildingPanel());
				return;
			}
		}
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}
}
