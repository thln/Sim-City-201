package application.gui.animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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

import application.Phonebook;
import application.gui.animation.agentGui.BusGuiHorizontal;
import application.gui.animation.agentGui.BusGuiVertical;
import application.gui.animation.agentGui.Gui;

public class CityPanel extends JPanel implements ActionListener, MouseListener {

	private final int WINDOWX = 600;
	private final int WINDOWY = 325;

	AnimationPanel animationPanel;
	private List<Gui> guis = Collections.synchronizedList(new ArrayList<Gui>());
	public ArrayList<Building> buildings = new ArrayList<Building>();
	Dimension Msize = new Dimension(75, 75);
	Dimension Bsize = new Dimension(75, 75);

	public BufferedImage background = null;

	ImageIcon bank = new ImageIcon("res/bank.png", "bank");
	ImageIcon restaurant = new ImageIcon("res/restaurant.png", "restaurant");
	ImageIcon market = new ImageIcon("res/market.png", "market");
	ImageIcon house = new ImageIcon("res/house.png", "house");
	ImageIcon apartment = new ImageIcon("res/apartment.png", "apartment");
	ImageIcon rave = new ImageIcon("res/rave.jpeg");
	ImageIcon park = new ImageIcon("res/grass.jpg", "park");

	public ImageIcon roadHorizontal = new ImageIcon("res/roadsHorizontal.png");
	public ImageIcon roadVertical = new ImageIcon("res/roadsVertical.png");
	public ImageIcon busStop = new ImageIcon("res/bus_stop.png");	
	public BusGuiHorizontal busA = new BusGuiHorizontal();
	public BusGuiVertical busB = new BusGuiVertical();

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
		addBuilding("Mexican Restaurant", WINDOWX/2-75, WINDOWY-75);
		addBuilding("American Restaurant", WINDOWX-100, WINDOWY-75);
		addBuilding("Italian Restaurant", WINDOWX-100, 20);
		addBuilding("Seafood Restaurant", WINDOWX-100, 100+market.getIconHeight()+2);

		addBuilding("East Market", WINDOWX - 100, 100);
		addBuilding("West Market", 75, 100);

		addBuilding("East Bank", WINDOWX / 2, 230);
		addBuilding("West Bank", WINDOWX / 2-75, 0);

		addBuilding("House", 20, 100);
		addBuilding("West Apartment", 0, 0);
		addBuilding("East Apartment", 500, WINDOWY-50);
		addBuilding("Park",(WINDOWX/2)-80,(WINDOWY/2)-47);

		addBuilding("Mexican Restaurant", 20, WINDOWY-75);
		addBuilding("Burger Restaurant", WINDOWX-100, WINDOWY-75);
		addBuilding("Italian Restaurant", WINDOWX-100, 20);
		addBuilding("Fancy Restaurant", WINDOWX-100, 100+market.getIconHeight()+2);

		addGui(busA);
		addGui(busB);

		Timer timer = new Timer(10, this);
		timer.start();
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;

		//Clear the screen by painting a rectangle the size of the frame
		g2.setColor(getBackground());
		g2.fillRect(0, 0, WINDOWX, WINDOWY );
		g2.drawImage(background, 0, 0, null);

		//Horizontal Roads 1
//		g2.drawImage(roadHorizontal.getImage(), 0, 70, null);
//		g2.drawImage(roadHorizontal.getImage(), 73, 70, null);
//		g2.drawImage(roadHorizontal.getImage(), 146, 70, null);
//		g2.drawImage(roadHorizontal.getImage(), 219, 70, null);
//		g2.drawImage(roadHorizontal.getImage(), 292, 70, null);
//		g2.drawImage(roadHorizontal.getImage(), 365, 70, null);
//		g2.drawImage(roadHorizontal.getImage(), 438, 70, null);
//		g2.drawImage(roadHorizontal.getImage(), 511, 70, null);
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 70, 600, 39);

		//Horizontal Roads 2
//		g2.drawImage(roadHorizontal.getImage(), 0, 190, null);
//		g2.drawImage(roadHorizontal.getImage(), 73, 190, null);
//		g2.drawImage(roadHorizontal.getImage(), 146, 190, null);
//		g2.drawImage(roadHorizontal.getImage(), 219, 190, null);
//		g2.drawImage(roadHorizontal.getImage(), 292, 190, null);
//		g2.drawImage(roadHorizontal.getImage(), 365, 190, null);
//		g2.drawImage(roadHorizontal.getImage(), 438, 190, null);
//		g2.drawImage(roadHorizontal.getImage(), 511, 190, null);
		g2.fillRect(0, 190, 600, 39);

		//Vertical Roads 1
//		g2.drawImage(roadVertical.getImage(), 160, 0, null);
//		g2.drawImage(roadVertical.getImage(), 160, 73, null);
//		g2.drawImage(roadVertical.getImage(), 160, 146, null);
//		g2.drawImage(roadVertical.getImage(), 160, 219, null);
//		g2.drawImage(roadVertical.getImage(), 160, 292, null);
		
		g2.fillRect(160, 0, 39, 325);

		//Vertical Roads 2
//		g2.drawImage(roadVertical.getImage(), 380, 0, null);
//		g2.drawImage(roadVertical.getImage(), 380, 73, null);
//		g2.drawImage(roadVertical.getImage(), 380, 146, null);
//		g2.drawImage(roadVertical.getImage(), 380, 219, null);
//		g2.drawImage(roadVertical.getImage(), 380, 292, null);
		g2.fillRect(380, 0, 39, 325);

		//Intersections
		g2.setColor(Color.BLACK);
		g2.fillRect((int) Phonebook.getPhonebook().intersection1.getIntersection().getX(),
				(int) Phonebook.getPhonebook().intersection1.getIntersection().getY(),
				(int) Phonebook.getPhonebook().intersection1.getIntersection().getWidth(),
				(int) Phonebook.getPhonebook().intersection1.getIntersection().getHeight());
		g2.fillRect((int) Phonebook.getPhonebook().intersection2.getIntersection().getX(),
				(int) Phonebook.getPhonebook().intersection2.getIntersection().getY(),
				(int) Phonebook.getPhonebook().intersection2.getIntersection().getWidth(),
				(int) Phonebook.getPhonebook().intersection2.getIntersection().getHeight());
		g2.fillRect((int) Phonebook.getPhonebook().intersection3.getIntersection().getX(),
				(int) Phonebook.getPhonebook().intersection3.getIntersection().getY(),
				(int) Phonebook.getPhonebook().intersection3.getIntersection().getWidth(),
				(int) Phonebook.getPhonebook().intersection3.getIntersection().getHeight());
		g2.fillRect((int) Phonebook.getPhonebook().intersection4.getIntersection().getX(),
				(int) Phonebook.getPhonebook().intersection4.getIntersection().getY(),
				(int) Phonebook.getPhonebook().intersection4.getIntersection().getWidth(),
				(int) Phonebook.getPhonebook().intersection4.getIntersection().getHeight());

		//Crosswalks
		drawCrosswalks(g2);

		//Bus Stops
		g2.drawImage(busStop.getImage(), 127, 28, null);
		g2.drawImage(busStop.getImage(), 127, 230, null);
		g2.drawImage(busStop.getImage(), 420, 28, null);
		g2.drawImage(busStop.getImage(), 420, 230, null);

		//Busses


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

	public void drawCrosswalks(Graphics2D g2) {
		//Road
		g2.setColor(Color.DARK_GRAY);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk1.getCrosswalk().getX(),
				(int) Phonebook.getPhonebook().crosswalk1.getCrosswalk().getY(),
				(int) Phonebook.getPhonebook().crosswalk1.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk1.getCrosswalk().getHeight());
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk2.getCrosswalk().getX(),
				(int) Phonebook.getPhonebook().crosswalk2.getCrosswalk().getY(),
				(int) Phonebook.getPhonebook().crosswalk2.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk2.getCrosswalk().getHeight());
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk3.getCrosswalk().getX(),
				(int) Phonebook.getPhonebook().crosswalk3.getCrosswalk().getY(),
				(int) Phonebook.getPhonebook().crosswalk3.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk3.getCrosswalk().getHeight());
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk4.getCrosswalk().getX(),
				(int) Phonebook.getPhonebook().crosswalk4.getCrosswalk().getY(),
				(int) Phonebook.getPhonebook().crosswalk4.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk4.getCrosswalk().getHeight());
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk5.getCrosswalk().getX(),
				(int) Phonebook.getPhonebook().crosswalk5.getCrosswalk().getY(),
				(int) Phonebook.getPhonebook().crosswalk5.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk5.getCrosswalk().getHeight());
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk6.getCrosswalk().getX(),
				(int) Phonebook.getPhonebook().crosswalk6.getCrosswalk().getY(),
				(int) Phonebook.getPhonebook().crosswalk6.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk6.getCrosswalk().getHeight());
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk7.getCrosswalk().getX(),
				(int) Phonebook.getPhonebook().crosswalk7.getCrosswalk().getY(),
				(int) Phonebook.getPhonebook().crosswalk7.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk7.getCrosswalk().getHeight());
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk8.getCrosswalk().getX(),
				(int) Phonebook.getPhonebook().crosswalk8.getCrosswalk().getY(),
				(int) Phonebook.getPhonebook().crosswalk8.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk8.getCrosswalk().getHeight());
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk9.getCrosswalk().getX(),
				(int) Phonebook.getPhonebook().crosswalk9.getCrosswalk().getY(),
				(int) Phonebook.getPhonebook().crosswalk9.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk9.getCrosswalk().getHeight());
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk10.getCrosswalk().getX(),
				(int) Phonebook.getPhonebook().crosswalk10.getCrosswalk().getY(),
				(int) Phonebook.getPhonebook().crosswalk10.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk10.getCrosswalk().getHeight());
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk11.getCrosswalk().getX(),
				(int) Phonebook.getPhonebook().crosswalk11.getCrosswalk().getY(),
				(int) Phonebook.getPhonebook().crosswalk11.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk11.getCrosswalk().getHeight());
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk12.getCrosswalk().getX(),
				(int) Phonebook.getPhonebook().crosswalk12.getCrosswalk().getY(),
				(int) Phonebook.getPhonebook().crosswalk12.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk12.getCrosswalk().getHeight());




		//Details
		g2.setColor(Color.YELLOW);

		//Crosswalk 1
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk1.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk1.getCrosswalk().getWidth()/20*2)),
				(int) (Phonebook.getPhonebook().crosswalk1.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk1.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk1.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk1.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk1.getCrosswalk().getWidth()/20*4)),
				(int) (Phonebook.getPhonebook().crosswalk1.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk1.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk1.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk1.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk1.getCrosswalk().getWidth()/20*6)),
				(int) (Phonebook.getPhonebook().crosswalk1.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk1.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk1.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk1.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk1.getCrosswalk().getWidth()/20*8)),
				(int) (Phonebook.getPhonebook().crosswalk1.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk1.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk1.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk1.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk1.getCrosswalk().getWidth()/20*10)),
				(int) (Phonebook.getPhonebook().crosswalk1.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk1.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk1.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk1.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk1.getCrosswalk().getWidth()/20*12)),
				(int) (Phonebook.getPhonebook().crosswalk1.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk1.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk1.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk1.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk1.getCrosswalk().getWidth()/20*14)),
				(int) (Phonebook.getPhonebook().crosswalk1.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk1.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk1.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk1.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk1.getCrosswalk().getWidth()/20*16)),
				(int) (Phonebook.getPhonebook().crosswalk1.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk1.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk1.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk1.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk1.getCrosswalk().getWidth()/20*18)),
				(int) (Phonebook.getPhonebook().crosswalk1.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk1.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk1.getCrosswalk().getHeight());

		//Crosswalk 2
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk2.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk2.getCrosswalk().getWidth()/20*2)),
				(int) (Phonebook.getPhonebook().crosswalk2.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk2.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk2.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk2.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk2.getCrosswalk().getWidth()/20*4)),
				(int) (Phonebook.getPhonebook().crosswalk2.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk2.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk2.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk2.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk2.getCrosswalk().getWidth()/20*6)),
				(int) (Phonebook.getPhonebook().crosswalk2.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk2.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk2.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk2.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk2.getCrosswalk().getWidth()/20*8)),
				(int) (Phonebook.getPhonebook().crosswalk2.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk2.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk2.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk2.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk2.getCrosswalk().getWidth()/20*10)),
				(int) (Phonebook.getPhonebook().crosswalk2.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk2.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk2.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk2.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk2.getCrosswalk().getWidth()/20*12)),
				(int) (Phonebook.getPhonebook().crosswalk2.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk2.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk2.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk2.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk2.getCrosswalk().getWidth()/20*14)),
				(int) (Phonebook.getPhonebook().crosswalk2.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk2.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk2.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk2.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk2.getCrosswalk().getWidth()/20*16)),
				(int) (Phonebook.getPhonebook().crosswalk2.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk2.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk2.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk2.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk2.getCrosswalk().getWidth()/20*18)),
				(int) (Phonebook.getPhonebook().crosswalk2.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk2.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk2.getCrosswalk().getHeight());

		//Crosswalk 3
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk3.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk3.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk3.getCrosswalk().getHeight()/20*2)),
				(int) Phonebook.getPhonebook().crosswalk3.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk3.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk3.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk3.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk3.getCrosswalk().getHeight()/20*4)),
				(int) Phonebook.getPhonebook().crosswalk3.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk3.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk3.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk3.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk3.getCrosswalk().getHeight()/20*6)),
				(int) Phonebook.getPhonebook().crosswalk3.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk3.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk3.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk3.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk3.getCrosswalk().getHeight()/20*8)),
				(int) Phonebook.getPhonebook().crosswalk3.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk3.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk3.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk3.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk3.getCrosswalk().getHeight()/20*10)),
				(int) Phonebook.getPhonebook().crosswalk3.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk3.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk3.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk3.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk3.getCrosswalk().getHeight()/20*12)),
				(int) Phonebook.getPhonebook().crosswalk3.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk3.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk3.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk3.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk3.getCrosswalk().getHeight()/20*14)),
				(int) Phonebook.getPhonebook().crosswalk3.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk3.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk3.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk3.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk3.getCrosswalk().getHeight()/20*16)),
				(int) Phonebook.getPhonebook().crosswalk3.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk3.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk3.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk3.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk3.getCrosswalk().getHeight()/20*18)),
				(int) Phonebook.getPhonebook().crosswalk3.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk3.getCrosswalk().getHeight()/20);

		//Crosswalk 4
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk4.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk4.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk4.getCrosswalk().getHeight()/20*2)),
				(int) Phonebook.getPhonebook().crosswalk4.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk4.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk4.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk4.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk4.getCrosswalk().getHeight()/20*4)),
				(int) Phonebook.getPhonebook().crosswalk4.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk4.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk4.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk4.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk4.getCrosswalk().getHeight()/20*6)),
				(int) Phonebook.getPhonebook().crosswalk4.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk4.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk4.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk4.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk4.getCrosswalk().getHeight()/20*8)),
				(int) Phonebook.getPhonebook().crosswalk4.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk4.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk4.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk4.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk4.getCrosswalk().getHeight()/20*10)),
				(int) Phonebook.getPhonebook().crosswalk4.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk4.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk4.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk4.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk4.getCrosswalk().getHeight()/20*12)),
				(int) Phonebook.getPhonebook().crosswalk4.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk4.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk4.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk4.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk4.getCrosswalk().getHeight()/20*14)),
				(int) Phonebook.getPhonebook().crosswalk4.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk4.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk4.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk4.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk4.getCrosswalk().getHeight()/20*16)),
				(int) Phonebook.getPhonebook().crosswalk4.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk4.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk4.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk4.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk4.getCrosswalk().getHeight()/20*18)),
				(int) Phonebook.getPhonebook().crosswalk4.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk4.getCrosswalk().getHeight()/20);

		//Crosswalk 5
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk5.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk5.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk5.getCrosswalk().getHeight()/20*2)),
				(int) Phonebook.getPhonebook().crosswalk5.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk5.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk5.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk5.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk5.getCrosswalk().getHeight()/20*4)),
				(int) Phonebook.getPhonebook().crosswalk5.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk5.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk5.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk5.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk5.getCrosswalk().getHeight()/20*6)),
				(int) Phonebook.getPhonebook().crosswalk5.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk5.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk5.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk5.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk5.getCrosswalk().getHeight()/20*8)),
				(int) Phonebook.getPhonebook().crosswalk5.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk5.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk5.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk5.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk5.getCrosswalk().getHeight()/20*10)),
				(int) Phonebook.getPhonebook().crosswalk5.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk5.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk5.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk5.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk5.getCrosswalk().getHeight()/20*12)),
				(int) Phonebook.getPhonebook().crosswalk5.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk5.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk5.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk5.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk5.getCrosswalk().getHeight()/20*14)),
				(int) Phonebook.getPhonebook().crosswalk5.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk5.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk5.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk5.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk5.getCrosswalk().getHeight()/20*16)),
				(int) Phonebook.getPhonebook().crosswalk5.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk5.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk5.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk5.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk5.getCrosswalk().getHeight()/20*18)),
				(int) Phonebook.getPhonebook().crosswalk5.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk5.getCrosswalk().getHeight()/20);

		//Crosswalk 6
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk6.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk6.getCrosswalk().getWidth()/20*2)),
				(int) (Phonebook.getPhonebook().crosswalk6.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk6.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk6.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk6.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk6.getCrosswalk().getWidth()/20*4)),
				(int) (Phonebook.getPhonebook().crosswalk6.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk6.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk6.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk6.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk6.getCrosswalk().getWidth()/20*6)),
				(int) (Phonebook.getPhonebook().crosswalk6.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk6.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk6.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk6.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk6.getCrosswalk().getWidth()/20*8)),
				(int) (Phonebook.getPhonebook().crosswalk6.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk6.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk6.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk6.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk6.getCrosswalk().getWidth()/20*10)),
				(int) (Phonebook.getPhonebook().crosswalk6.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk6.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk6.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk6.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk6.getCrosswalk().getWidth()/20*12)),
				(int) (Phonebook.getPhonebook().crosswalk6.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk6.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk6.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk6.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk6.getCrosswalk().getWidth()/20*14)),
				(int) (Phonebook.getPhonebook().crosswalk6.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk6.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk6.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk6.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk6.getCrosswalk().getWidth()/20*16)),
				(int) (Phonebook.getPhonebook().crosswalk6.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk6.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk6.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk6.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk6.getCrosswalk().getWidth()/20*18)),
				(int) (Phonebook.getPhonebook().crosswalk6.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk6.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk6.getCrosswalk().getHeight());

		//Crosswalk 7
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk7.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk7.getCrosswalk().getWidth()/20*2)),
				(int) (Phonebook.getPhonebook().crosswalk7.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk7.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk7.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk7.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk7.getCrosswalk().getWidth()/20*4)),
				(int) (Phonebook.getPhonebook().crosswalk7.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk7.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk7.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk7.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk7.getCrosswalk().getWidth()/20*6)),
				(int) (Phonebook.getPhonebook().crosswalk7.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk7.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk7.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk7.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk7.getCrosswalk().getWidth()/20*8)),
				(int) (Phonebook.getPhonebook().crosswalk7.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk7.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk7.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk7.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk7.getCrosswalk().getWidth()/20*10)),
				(int) (Phonebook.getPhonebook().crosswalk7.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk7.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk7.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk7.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk7.getCrosswalk().getWidth()/20*12)),
				(int) (Phonebook.getPhonebook().crosswalk7.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk7.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk7.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk7.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk7.getCrosswalk().getWidth()/20*14)),
				(int) (Phonebook.getPhonebook().crosswalk7.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk7.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk7.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk7.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk7.getCrosswalk().getWidth()/20*16)),
				(int) (Phonebook.getPhonebook().crosswalk7.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk7.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk7.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk7.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk7.getCrosswalk().getWidth()/20*18)),
				(int) (Phonebook.getPhonebook().crosswalk7.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk7.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk7.getCrosswalk().getHeight());

		//Crosswalk 8
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk8.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk8.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk8.getCrosswalk().getHeight()/20*2)),
				(int) Phonebook.getPhonebook().crosswalk8.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk8.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk8.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk8.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk8.getCrosswalk().getHeight()/20*4)),
				(int) Phonebook.getPhonebook().crosswalk8.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk8.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk8.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk8.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk8.getCrosswalk().getHeight()/20*6)),
				(int) Phonebook.getPhonebook().crosswalk8.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk8.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk8.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk8.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk8.getCrosswalk().getHeight()/20*8)),
				(int) Phonebook.getPhonebook().crosswalk8.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk8.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk8.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk8.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk8.getCrosswalk().getHeight()/20*10)),
				(int) Phonebook.getPhonebook().crosswalk8.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk8.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk8.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk8.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk8.getCrosswalk().getHeight()/20*12)),
				(int) Phonebook.getPhonebook().crosswalk8.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk8.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk8.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk8.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk8.getCrosswalk().getHeight()/20*14)),
				(int) Phonebook.getPhonebook().crosswalk8.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk8.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk8.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk8.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk8.getCrosswalk().getHeight()/20*16)),
				(int) Phonebook.getPhonebook().crosswalk8.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk8.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk8.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk8.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk8.getCrosswalk().getHeight()/20*18)),
				(int) Phonebook.getPhonebook().crosswalk8.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk8.getCrosswalk().getHeight()/20);

		//Crosswalk 9
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk9.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk9.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk9.getCrosswalk().getHeight()/20*2)),
				(int) Phonebook.getPhonebook().crosswalk9.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk9.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk9.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk9.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk9.getCrosswalk().getHeight()/20*4)),
				(int) Phonebook.getPhonebook().crosswalk9.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk9.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk9.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk9.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk9.getCrosswalk().getHeight()/20*6)),
				(int) Phonebook.getPhonebook().crosswalk9.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk9.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk9.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk9.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk9.getCrosswalk().getHeight()/20*8)),
				(int) Phonebook.getPhonebook().crosswalk9.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk9.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk9.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk9.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk9.getCrosswalk().getHeight()/20*10)),
				(int) Phonebook.getPhonebook().crosswalk9.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk9.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk9.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk9.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk9.getCrosswalk().getHeight()/20*12)),
				(int) Phonebook.getPhonebook().crosswalk9.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk9.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk9.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk9.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk9.getCrosswalk().getHeight()/20*14)),
				(int) Phonebook.getPhonebook().crosswalk9.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk9.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk9.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk9.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk9.getCrosswalk().getHeight()/20*16)),
				(int) Phonebook.getPhonebook().crosswalk9.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk9.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk9.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk9.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk9.getCrosswalk().getHeight()/20*18)),
				(int) Phonebook.getPhonebook().crosswalk9.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk9.getCrosswalk().getHeight()/20);

		//Crosswalk 10
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk10.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk10.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk10.getCrosswalk().getHeight()/20*2)),
				(int) Phonebook.getPhonebook().crosswalk10.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk10.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk10.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk10.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk10.getCrosswalk().getHeight()/20*4)),
				(int) Phonebook.getPhonebook().crosswalk10.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk10.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk10.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk10.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk10.getCrosswalk().getHeight()/20*6)),
				(int) Phonebook.getPhonebook().crosswalk10.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk10.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk10.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk10.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk10.getCrosswalk().getHeight()/20*8)),
				(int) Phonebook.getPhonebook().crosswalk10.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk10.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk10.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk10.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk10.getCrosswalk().getHeight()/20*10)),
				(int) Phonebook.getPhonebook().crosswalk10.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk10.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk10.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk10.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk10.getCrosswalk().getHeight()/20*12)),
				(int) Phonebook.getPhonebook().crosswalk10.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk10.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk10.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk10.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk10.getCrosswalk().getHeight()/20*14)),
				(int) Phonebook.getPhonebook().crosswalk10.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk10.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk10.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk10.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk10.getCrosswalk().getHeight()/20*16)),
				(int) Phonebook.getPhonebook().crosswalk10.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk10.getCrosswalk().getHeight()/20);
		g2.fillRect((int) Phonebook.getPhonebook().crosswalk10.getCrosswalk().getX(),
				(int) (Phonebook.getPhonebook().crosswalk10.getCrosswalk().getY() + (Phonebook.getPhonebook().crosswalk10.getCrosswalk().getHeight()/20*18)),
				(int) Phonebook.getPhonebook().crosswalk10.getCrosswalk().getWidth(),
				(int) Phonebook.getPhonebook().crosswalk10.getCrosswalk().getHeight()/20);

		//Crosswalk 11
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk11.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk11.getCrosswalk().getWidth()/20*2)),
				(int) (Phonebook.getPhonebook().crosswalk11.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk11.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk11.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk11.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk11.getCrosswalk().getWidth()/20*4)),
				(int) (Phonebook.getPhonebook().crosswalk11.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk11.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk11.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk11.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk11.getCrosswalk().getWidth()/20*6)),
				(int) (Phonebook.getPhonebook().crosswalk11.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk11.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk11.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk11.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk11.getCrosswalk().getWidth()/20*8)),
				(int) (Phonebook.getPhonebook().crosswalk11.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk11.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk11.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk11.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk11.getCrosswalk().getWidth()/20*10)),
				(int) (Phonebook.getPhonebook().crosswalk11.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk11.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk11.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk11.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk11.getCrosswalk().getWidth()/20*12)),
				(int) (Phonebook.getPhonebook().crosswalk11.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk11.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk11.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk11.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk11.getCrosswalk().getWidth()/20*14)),
				(int) (Phonebook.getPhonebook().crosswalk11.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk11.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk11.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk11.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk11.getCrosswalk().getWidth()/20*16)),
				(int) (Phonebook.getPhonebook().crosswalk11.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk11.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk11.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk11.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk11.getCrosswalk().getWidth()/20*18)),
				(int) (Phonebook.getPhonebook().crosswalk11.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk11.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk11.getCrosswalk().getHeight());

		//Crosswalk 12
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk12.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk12.getCrosswalk().getWidth()/20*2)),
				(int) (Phonebook.getPhonebook().crosswalk12.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk12.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk12.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk12.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk12.getCrosswalk().getWidth()/20*4)),
				(int) (Phonebook.getPhonebook().crosswalk12.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk12.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk12.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk12.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk12.getCrosswalk().getWidth()/20*6)),
				(int) (Phonebook.getPhonebook().crosswalk12.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk12.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk12.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk12.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk12.getCrosswalk().getWidth()/20*8)),
				(int) (Phonebook.getPhonebook().crosswalk12.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk12.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk12.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk12.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk12.getCrosswalk().getWidth()/20*10)),
				(int) (Phonebook.getPhonebook().crosswalk12.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk12.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk12.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk12.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk12.getCrosswalk().getWidth()/20*12)),
				(int) (Phonebook.getPhonebook().crosswalk12.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk12.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk12.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk12.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk12.getCrosswalk().getWidth()/20*14)),
				(int) (Phonebook.getPhonebook().crosswalk12.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk12.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk12.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk12.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk12.getCrosswalk().getWidth()/20*16)),
				(int) (Phonebook.getPhonebook().crosswalk12.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk12.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk12.getCrosswalk().getHeight());
		g2.fillRect((int) (Phonebook.getPhonebook().crosswalk12.getCrosswalk().getX() + (Phonebook.getPhonebook().crosswalk12.getCrosswalk().getWidth()/20*18)),
				(int) (Phonebook.getPhonebook().crosswalk12.getCrosswalk().getY()),
				(int) Phonebook.getPhonebook().crosswalk12.getCrosswalk().getWidth()/20,
				(int) Phonebook.getPhonebook().crosswalk12.getCrosswalk().getHeight());
	}
}
