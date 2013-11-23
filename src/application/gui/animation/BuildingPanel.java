package application.gui.animation;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.io.*;

import javax.imageio.*;

import java.awt.image.BufferedImage;

import application.gui.animation.agentGui.*;

public class BuildingPanel extends JPanel implements ActionListener {

    private final int WINDOWX = 570;
    private final int WINDOWY = 360;
    static final int NTABLES = 5;
    private String name;
    private List<Gui> guis = new ArrayList<Gui>();
    AnimationPanel panel;
    
    public BuildingPanel(String buildName, AnimationPanel ap) {
    	setSize(WINDOWX, WINDOWY);
        setVisible(true);
        setLayout(null);
        name = buildName;
        panel = ap;
    	Timer timer = new Timer(20, this );
    	timer.start();
    }

	public void actionPerformed(ActionEvent e) {
		repaint();  //Will have paintComponent called
	}

    public void paintComponent(Graphics g) {
    	
        Graphics2D g2 = (Graphics2D)g;
        //Clear the screen by painting a rectangle the size of the frame
        if(name.toLowerCase().contains("restaurant"))
        	g2.setColor(Color.YELLOW);
        else if(name.toLowerCase().contains("market"))
        	g2.setColor(Color.CYAN);
        else if(name.toLowerCase().contains("house"))
        	g2.setColor(Color.ORANGE);
        else if(name.toLowerCase().contains("bank"))
        	g2.setColor(Color.LIGHT_GRAY);
        else
        	g2.setColor(getBackground());
        
        g2.fillRect(0, 0, WINDOWX, WINDOWY );	
        g2.setColor(Color.RED);
        if(name == "name")
        	g.drawString("", 10, 10);
        else
        	g.drawString(name, 10, 10);
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
    
    public void displayBuildingPanel() {
    	panel.displayBuildingPanel(this);
    }
    
    public String getName() {
    	return name;
    }
    
    public void addGui(Gui gui) {
        guis.add(gui);
        
    }
    
    public String toString() {
    	return name;
    }
}
