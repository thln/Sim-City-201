package application.gui;

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

public class BuildingPanel extends JPanel implements ActionListener {

    private final int WINDOWX = 450;
    private final int WINDOWY = 400;
    
    static final int NTABLES = 5;
    
    private Image bufferImage;
    private Dimension bufferSize;
    
    String buildingName;
    private List<JLabel> labels = new ArrayList<JLabel>();
    private List<Gui> guis = new ArrayList<Gui>();

    public BuildingPanel(String name) {
    	setSize(WINDOWX, WINDOWY);
        setVisible(true);
        setLayout(null);
        bufferSize = this.getSize();
        buildingName = name;
    	Timer timer = new Timer(20, this );
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

        //Here is the table
        g2.setColor(Color.RED);
        // ALT: g2.fillRect(WINDOWX*4/9, WINDOWY*5/7, 50, 50);//200 and 250 need to be table params
        
        if(NTABLES < 10) {
        	for(int ix=1; ix<= NTABLES; ix++) {
        		g2.fillRect(50*ix, 50, 40, 40);
        	}
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

    public void addGui(Gui gui) {
        guis.add(gui);
        
    }
    
    public String toString() {
    	return buildingName;
    }
}
