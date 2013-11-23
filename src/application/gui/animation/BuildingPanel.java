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
        	g2.setColor(Color.WHITE);
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
        g2.setColor(Color.BLACK);
        if(name.toLowerCase().contains("restaurant")) {
        	
        }
        else if(name.toLowerCase().contains("market")) {
        	g.drawString("Inventory",WINDOWX/2, 60 );
        	g2.fillRect(0, 80, WINDOWX - 100, 10); //inventory
        	g2.setColor(Color.CYAN);
        	g2.fillRect(100, 125, WINDOWX/2, 20); //table
        }
        else if(name.toLowerCase().contains("house")) {
        	
        }
        else if(name.toLowerCase().contains("bank")) {
        	g2.fillRect(WINDOWX - 100, 0, 20, WINDOWY*3/4);
        	for(int j=0; j<4;j++)
        	g2.fillRect(WINDOWX - 100, 50*(j+1), 100, 10);
        	g2.fillRect(WINDOWX/4, WINDOWY/2, 10, 50); //back of chair
        	g2.fillRect(WINDOWX/4 -20, WINDOWY/2, 20, 10);
        	g2.fillRect(WINDOWX/4 -20, WINDOWY/2+40, 20, 10);
        	
        	//Vault
        	g2.setColor(Color.CYAN);
        	g2.fillRect(10, 10, 150, 100);
        	g2.setColor(Color.BLACK);
        	g.drawString("Bank Vault", 50, 50);
        }
        
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
