package restaurant.gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class AnimationPanel extends JPanel implements ActionListener {
	//Adding static variable for timer
	static final int timerDelay = 5;
	
	static final int xTableLocation = 200;
	static final int yTableLocation = 250;
	static final int TableWidth = 50;
	static final int TableHeight = 50;
	
	static final int xKitchenLocation = 385;
	static final int yKitchenLocation = 110;
	static final int KitchenTileWidth = 20;
	static final int KitchenTileHeight = 20;
	
	static final int xBreakRoomLocation = 310;
	static final int yBreakRoomLocation = 7;
	static final int BreakRoomWidth = 75;
	static final int BreakRoomHeight = 40;
	
	static final int xCashierLocation = 275;
	static final int yCashierLocation = 305;
	static final int CashierWidth = 50;
	static final int CashierHeight = 40;
	
	static final int xJailLocation = 405;
	static final int yJailLocation = 7;
	static final int JailWidth = 40;
	static final int JailHeight = 60;
	static final int JailBarsWidth = 3;
	
	static final int xLobbyLocation = 5;
	static final int yLobbyLocation = 10;
	static final int LobbyWidth = 300;
	static final int LobbyHeight = 50;
	
    private final int WINDOWX = 450;
    private final int WINDOWY = 350;
    private Image bufferImage;
    private Dimension bufferSize;

    private List<Gui> guis = new ArrayList<Gui>();

    public AnimationPanel() {
    	setSize(WINDOWX, WINDOWY);
        setVisible(true);
        
        bufferSize = this.getSize();
 
    	Timer timer = new Timer(timerDelay, this );
    	timer.start();
    }

	public void actionPerformed(ActionEvent e) {
		repaint();  //Will have paintComponent called
	}

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        //Clear the screen by painting a rectangle the size of the frame
        g2.setColor(getBackground());
        g2.fillRect(0, 0, WINDOWX, WINDOWY );

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

    public void addGui(CustomerGui gui) {
        guis.add(gui);
    }
    
    public void addGui(WaiterGui gui) {
        guis.add(gui);
    }

    public void addGui(HostGui gui) {
        guis.add(gui);
    }
    
    public void addGui(CookGui gui) {
        guis.add(gui);
    }
}
