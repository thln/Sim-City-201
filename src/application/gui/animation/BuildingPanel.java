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
 
    private String name;
    private List<Gui> guis = new ArrayList<Gui>();
    AnimationPanel myCity;
    
    public BuildingPanel(String buildName, AnimationPanel ap) {
		setMinimumSize(new Dimension(WINDOWX, WINDOWY));
		setMaximumSize(new Dimension(WINDOWX, WINDOWY));
		setPreferredSize(new Dimension(WINDOWX, WINDOWY));
        setVisible(true);
        setLayout(null);
        name = buildName;
        myCity = ap;
    }

	public void actionPerformed(ActionEvent e) {
		repaint();  //Will have paintComponent called
	}

    public void paintComponent(Graphics g) {
    	
        Graphics2D g2 = (Graphics2D)g;
        
        //Clear the screen by painting a rectangle the size of the frame
        if(name.toLowerCase().contains("restaurant"))
        	g2.setColor(getBackground());
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
        
        if (name.toLowerCase().contains("restaurant")) {
        	int xTableLocation = 200;
        	int yTableLocation = 250;
        	int TableWidth = 50;
        	int TableHeight = 50;
        	
        	int xKitchenLocation = 385;
        	int yKitchenLocation = 110;
        	int KitchenTileWidth = 20;
        	int KitchenTileHeight = 20;
        	
        	int xBreakRoomLocation = 310;
        	int yBreakRoomLocation = 7;
        	int BreakRoomWidth = 75;
        	int BreakRoomHeight = 40;
        	
        	int xCashierLocation = 275;
        	int yCashierLocation = 305;
        	int CashierWidth = 50;
        	int CashierHeight = 40;
        	
        	int xJailLocation = 405;
        	int yJailLocation = 7;
        	int JailWidth = 40;
        	int JailHeight = 60;
        	int JailBarsWidth = 3;
        	
        	int xLobbyLocation = 5;
        	int yLobbyLocation = 10;
        	int LobbyWidth = 300;
        	int LobbyHeight = 50;
        	
            //Table 1
            g2.setColor(Color.PINK);
            g2.fillRect(xTableLocation - 150, yTableLocation, TableWidth, TableHeight);
            
            //Table 2
            g2.setColor(Color.PINK);
            g2.fillRect(xTableLocation, yTableLocation, TableWidth, TableHeight);//200 and 250 need to be table params
            
            //Table 3
            g2.setColor(Color.PINK);
            g2.fillRect(xTableLocation + 150, yTableLocation, TableWidth, TableHeight);
            
            //Table 4
            g2.setColor(Color.PINK);
            g2.fillRect(xTableLocation, yTableLocation-175, TableWidth, TableHeight);//200 and 250 need to be table params
            
            
            //Kitchen
            g2.setColor(Color.WHITE);
            g2.fillRect(xKitchenLocation, yKitchenLocation, KitchenTileWidth, KitchenTileHeight);
            g2.fillRect(xKitchenLocation, yKitchenLocation + 40, KitchenTileWidth, KitchenTileHeight);
            g2.fillRect(xKitchenLocation + 20, yKitchenLocation + 20, KitchenTileWidth, KitchenTileHeight);
            g2.fillRect(xKitchenLocation + 20, yKitchenLocation + 60, KitchenTileWidth, KitchenTileHeight);
            g2.fillRect(xKitchenLocation + 40, yKitchenLocation, KitchenTileWidth, KitchenTileHeight);
            g2.fillRect(xKitchenLocation + 40, yKitchenLocation + 40, KitchenTileWidth, KitchenTileHeight);
            
            g2.setColor(Color.BLACK);
            g2.fillRect(xKitchenLocation, yKitchenLocation + 20, KitchenTileWidth, KitchenTileHeight);
            g2.fillRect(xKitchenLocation, yKitchenLocation + 60, KitchenTileWidth, KitchenTileHeight);
            g2.fillRect(xKitchenLocation + 20, yKitchenLocation, KitchenTileWidth, KitchenTileHeight);
            g2.fillRect(xKitchenLocation + 20, yKitchenLocation + 40, KitchenTileWidth, KitchenTileHeight);
            g2.fillRect(xKitchenLocation + 40, yKitchenLocation + 20, KitchenTileWidth, KitchenTileHeight);
            g2.fillRect(xKitchenLocation + 40, yKitchenLocation + 60, KitchenTileWidth, KitchenTileHeight);
            
            //Refrigerator
            g2.setColor(Color.LIGHT_GRAY);
            g2.fillRect(xKitchenLocation, yKitchenLocation - 20, KitchenTileWidth * 3, KitchenTileHeight);
            g2.setColor(Color.BLACK);
            g2.drawString("Fridge", xKitchenLocation + 5, yKitchenLocation - 5);
            
            //Plating Area
            g2.setColor(Color.ORANGE);
            g2.fillRect(xKitchenLocation - 20, yKitchenLocation, KitchenTileWidth, (KitchenTileHeight * 3) + 5);
            
            //Grill
            g2.setColor(Color.LIGHT_GRAY);
            g2.fillRect(xKitchenLocation, yKitchenLocation + 80, KitchenTileWidth * 3, KitchenTileHeight * 2);
            
            g2.setColor(Color.BLACK);
            g2.fillOval(xKitchenLocation + 13, yKitchenLocation + 85, (int) (KitchenTileWidth * .7), (int) (KitchenTileHeight * .7));
            g2.fillOval(xKitchenLocation + 33, yKitchenLocation + 85, (int) (KitchenTileWidth * .7), (int) (KitchenTileHeight * .7));
            g2.fillOval(xKitchenLocation + 13, yKitchenLocation + 100, (int) (KitchenTileWidth * .7), (int) (KitchenTileHeight * .7));
            g2.fillOval(xKitchenLocation + 33, yKitchenLocation + 100, (int) (KitchenTileWidth * .7), (int) (KitchenTileHeight * .7));
            
            //Break Room
            g2.setColor(Color.GRAY);
            g2.fillRect(xBreakRoomLocation, yBreakRoomLocation, BreakRoomWidth, BreakRoomHeight);
            g2.setColor(Color.BLACK);
            g2.drawString("Break Room", xBreakRoomLocation, yBreakRoomLocation + 35);
            
            //Cashier
            g2.setColor(Color.BLACK);
            g2.fillRect(xCashierLocation, yCashierLocation, CashierWidth, CashierHeight);
            g2.setColor(Color.GREEN);
            g2.drawString("$$$", xCashierLocation + 12, yCashierLocation + 35);
            
            //Jail
            g2.setColor(Color.WHITE);
            g2.fillRect(xJailLocation, yJailLocation, JailWidth, JailHeight);
            g2.setColor(Color.GRAY);
            g2.fillRect(xJailLocation, yJailLocation, JailBarsWidth, JailHeight);
            g2.fillRect(xJailLocation + 10, yJailLocation, JailBarsWidth, JailHeight);
            g2.fillRect(xJailLocation + 20, yJailLocation, JailBarsWidth, JailHeight);
            g2.fillRect(xJailLocation + 30, yJailLocation, JailBarsWidth, JailHeight);
            g2.fillRect(xJailLocation + 40, yJailLocation, JailBarsWidth, JailHeight);
            g2.setColor(Color.RED);
            g2.drawString("Jail", xJailLocation + 12, yJailLocation + 75);
            
            //Lobby
            g2.setColor(Color.BLUE);
            g2.fillRect(xLobbyLocation, yLobbyLocation, LobbyWidth, LobbyHeight);
            g2.setColor(Color.WHITE);
            g2.drawString("Lobby", xLobbyLocation + 2, yLobbyLocation + 45);
            
        }
        else if (name.toLowerCase().contains("market")) {
        	g.drawString("Inventory",WINDOWX/2, 60 );
        	g2.fillRect(0, 80, WINDOWX - 100, 10); //inventory
        	g2.setColor(Color.CYAN);
        	g2.fillRect(100, 125, WINDOWX/2, 20); //table
        }
        else if (name.toLowerCase().contains("house")) {
        	
        }
        else if (name.toLowerCase().contains("bank")) {
        	g2.fillRect(WINDOWX - 100, 0, 20, WINDOWY*3/4);
        	for (int j=0; j<4;j++)
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
    	myCity.displayBuildingPanel(this);
    }
    
    public String getName() {
    	return name;
    }
    
    /*public void addGui(Gui gui) {
        guis.add(gui);
        
    }*/
    
    public void addGui(MarketCustomerGui gui) {
        guis.add(gui);
        
    }
    
    public String toString() {
    	return name;
    }
}
