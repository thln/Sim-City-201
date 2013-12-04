package application.gui.animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import application.gui.animation.agentGui.Gui;

public class CityPanel extends JPanel implements ActionListener, MouseListener {

	private final int WINDOWX = 600;
	private final int WINDOWY = 325;

	AnimationPanel animationPanel;
	private List<Gui> guis = Collections.synchronizedList(new ArrayList<Gui>());
	public ArrayList<Building> buildings = new ArrayList<Building>();
	Dimension Msize = new Dimension(75, 75);
	Dimension Bsize = new Dimension(75, 75);

	//list of images representing our different buildings
	BufferedImage carIcon = null;
	BufferedImage bankIcon = null;
	BufferedImage marketIcon = null;
	BufferedImage houseIcon = null;
	BufferedImage restaurantIcon = null;

	public BufferedImage background = null;

	ImageIcon bank = new ImageIcon("res/bank.png", "bank");
	ImageIcon restaurant = new ImageIcon("res/restaurant.png", "restaurant");
	ImageIcon market = new ImageIcon("res/market.png", "market");
	ImageIcon house = new ImageIcon("res/house.png", "house");
	ImageIcon apartment = new ImageIcon("res/apartment.png", "apartment");
	ImageIcon rave = new ImageIcon("res/rave.jpeg");
	ImageIcon park = new ImageIcon("res/grass.jpeg", "park");

	public ImageIcon roadHorizontal = new ImageIcon("res/roadsHorizontal.png");
	public ImageIcon roadVertical = new ImageIcon("res/roadsVertical.png");
	public ImageIcon busStop = new ImageIcon("res/bus_stop.png");

	public CityPanel(AnimationPanel animationPanel) {

		this.animationPanel = animationPanel;
		addMouseListener(this);

		setPreferredSize(new Dimension(WINDOWX, WINDOWY));
		setMaximumSize(new Dimension(WINDOWX,WINDOWY));
		setMinimumSize(new Dimension(WINDOWX, WINDOWY));
		setBorder(BorderFactory.createLoweredBevelBorder());
		setVisible(true);
		setLayout(null);

		try {
			background = ImageIO.read(new File("res/concrete.jpg"));
		} catch (IOException e) {
		}

		addBuilding("Chinese Restaurant", WINDOWX / 2, 20);
		addBuilding("Market", WINDOWX - 100, 100);
		addBuilding("Bank", WINDOWX / 2, 230);
		addBuilding("House", 20, 100);
		addBuilding("Apartment", 0, 0);
		addBuilding("Park",WINDOWX/2,WINDOWY/2);

		Timer timer = new Timer(10, this );
		timer.start();
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;

		//Clear the screen by painting a rectangle the size of the frame
		g2.setColor(getBackground());
		g2.fillRect(0, 0, WINDOWX, WINDOWY );
		g2.drawImage(background, 0, 0, null);

		//Horizontal Roads 1
		g2.drawImage(roadHorizontal.getImage(), 0, 70, null);
		g2.drawImage(roadHorizontal.getImage(), 73, 70, null);
		g2.drawImage(roadHorizontal.getImage(), 146, 70, null);
		g2.drawImage(roadHorizontal.getImage(), 219, 70, null);
		g2.drawImage(roadHorizontal.getImage(), 292, 70, null);
		g2.drawImage(roadHorizontal.getImage(), 365, 70, null);
		g2.drawImage(roadHorizontal.getImage(), 438, 70, null);
		g2.drawImage(roadHorizontal.getImage(), 511, 70, null);

		//Horizontal Roads 2
		g2.drawImage(roadHorizontal.getImage(), 0, 190, null);
		g2.drawImage(roadHorizontal.getImage(), 73, 190, null);
		g2.drawImage(roadHorizontal.getImage(), 146, 190, null);
		g2.drawImage(roadHorizontal.getImage(), 219, 190, null);
		g2.drawImage(roadHorizontal.getImage(), 292, 190, null);
		g2.drawImage(roadHorizontal.getImage(), 365, 190, null);
		g2.drawImage(roadHorizontal.getImage(), 438, 190, null);
		g2.drawImage(roadHorizontal.getImage(), 511, 190, null);

		//Vertical Roads 1
		g2.drawImage(roadVertical.getImage(), 160, 0, null);
		g2.drawImage(roadVertical.getImage(), 160, 73, null);
		g2.drawImage(roadVertical.getImage(), 160, 146, null);
		g2.drawImage(roadVertical.getImage(), 160, 219, null);
		g2.drawImage(roadVertical.getImage(), 160, 292, null);
		
		//Vertical Roads 2
		g2.drawImage(roadVertical.getImage(), 380, 0, null);
		g2.drawImage(roadVertical.getImage(), 380, 73, null);
		g2.drawImage(roadVertical.getImage(), 380, 146, null);
		g2.drawImage(roadVertical.getImage(), 380, 219, null);
		g2.drawImage(roadVertical.getImage(), 380, 292, null);
		
		//Intersections
		g2.setColor(Color.BLACK);
		g2.fillRect(170, 72, 30, 38);
		g2.fillRect(390, 72, 30, 38);
		g2.fillRect(170, 192, 30, 38);
		g2.fillRect(390, 192, 30, 38);

		//Bus Stops
		g2.drawImage(busStop.getImage(), 127, 28, null);
		g2.drawImage(busStop.getImage(), 127, 230, null);
		g2.drawImage(busStop.getImage(), 420, 28, null);
		g2.drawImage(busStop.getImage(), 420, 230, null);

		//Drawing all buildings
		for (int i=0; i<buildings.size(); i++ ) {
			Building b = buildings.get(i);
			g2.drawImage(b.getMyImage().getImage(), b.getxLocation(), b.getyLocation(), null);
		}

		//Drawing all People guis
		synchronized (guis) {
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

	public void addGui(Gui gui) {
		guis.add(gui);   
	}

	public void addBuilding(String name, int x, int y) {
		Building building = new Building();

		//Bank building
		if (name.toLowerCase().contains("bank")) {
			building.setMyImage(bank);
			building.setLocation(x, y);
		}
		//Market building
		else if (name.toLowerCase().contains("market")) {
			building.setMyImage(market);
			building.setLocation(x, y);
		}
		//Restaurant building
		else if (name.toLowerCase().contains("restaurant")) {
			building.setMyImage(restaurant);
			building.setLocation(x, y);

		}
		//House building
		else if (name.toLowerCase().contains("house")) {
			building.setMyImage(house);
			building.setLocation(x, y);
		}

		//Apartment building
		else if (name.toLowerCase().contains("apartment")) {
			building.setMyImage(apartment);
			building.setLocation(x, y);
		}
		
		//Park in the Middle
		else if (name.toLowerCase().contains("park")){
			building.setMyImage(park);
			building.setLocation(x,y);
		}

		building.setName(name);
		buildings.add(building);
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
		for ( int i = 0; i<buildings.size(); i++ ) {
			Building b = buildings.get(i);
			if (b.getRect().contains(e.getX(), e.getY())) {
				b.displayBuilding();
				return;
			}
		}

		animationPanel.displayBlankBuildingPanel();
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
