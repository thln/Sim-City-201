package application.gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.imageio.*;

import java.awt.image.BufferedImage;

public class CityPanel extends JPanel implements ActionListener{
	
    private final int WINDOWX = 570;
    private final int WINDOWY = 360;
    
    //list of images representing our different buildings
    BufferedImage carIcon = null;
    BufferedImage bankIcon = null;
    BufferedImage marketIcon = null;
    BufferedImage houseIcon = null;
    BufferedImage restaurantIcon = null;
    BufferedImage background = null;
	
	public CityPanel() {
	   	setSize(WINDOWX, WINDOWY);
        setVisible(true);
        setLayout(null);
        try {
            background = ImageIO.read(new File("docs/grass.jpg"));
        	} catch (IOException e) {
        	}
	}
	
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        //Clear the screen by painting a rectangle the size of the frame
        g2.setColor(getBackground());
        g2.fillRect(0, 0, WINDOWX, WINDOWY );
        g2.drawImage(background, 0, 0, null);
        //Here is the table
        //g2.setColor(Color.ORANGE);
        
    }
    public String toString() {
    	return "City JPanel";
    }
}
