package application.gui.animation;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.io.*;

import javax.imageio.*;

import java.awt.image.BufferedImage;

import housing.*;
import application.gui.animation.agentGui.*;

public class BuildingPanel extends JPanel implements ActionListener {

	public final int WINDOWX = 590;
	public final int WINDOWY = 300;

	public String name;
	public List<Gui> guis = Collections.synchronizedList(new ArrayList<Gui>());
	private List<Housing> housing = Collections.synchronizedList(new ArrayList<Housing>());
	public AnimationPanel myCity;
	
	public BuildingPanel() {
		
	}
	
	public BuildingPanel(String buildName, AnimationPanel ap) {
		setMinimumSize(new Dimension(WINDOWX, WINDOWY));
		setMaximumSize(new Dimension(WINDOWX, WINDOWY));
		setPreferredSize(new Dimension(WINDOWX, WINDOWY));
		setVisible(true);
		setLayout(null);
		name = buildName;
		myCity = ap;
		Timer timer = new Timer(10, this );
		timer.start();
	}

	public void actionPerformed(ActionEvent e) {
		repaint();  //Will have paintComponent called
	}

	public void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(getBackground());

		g2.fillRect(0, 0, WINDOWX, WINDOWY );	
		g2.setColor(Color.RED);

		if(name == "name")
			g.drawString("", WINDOWX/2, 10);
		else
			g.drawString(name, WINDOWX/2, 10);

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
	
	public String getName() {
		return name;
	}

	public void addGui(Gui gui) {
		guis.add(gui); 
	}

	public void removeGui(Gui gui) {
		guis.remove(gui); 
	}

	public String toString() {
		return name;
	}
}
