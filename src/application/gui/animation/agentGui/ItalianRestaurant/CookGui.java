package application.gui.animation.agentGui.ItalianRestaurant;

import italianRestaurant.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.*;

import java.awt.image.BufferedImage;

public class CookGui implements Gui {

    private ItalianCookRole agent = null;
    BufferedImage cookIcon = null;
    BufferedImage fridge = null;
	BufferedImage steak = null;
	BufferedImage chicken = null;
	BufferedImage salad = null;
	BufferedImage pizza = null;
	BufferedImage plate = null;

	private List<CookingFood> cookingFoods = Collections.synchronizedList(new ArrayList<CookingFood>());
	private enum Command {noCommand, atMarket, atFridge};
	private Command command=Command.noCommand;

    private int xPos = 450, yPos = 250;//default cook position
    private int xDestination = 375, yDestination = 250;//default start position
    
    static final int NTABLES = 5;
    int tables;

    public CookGui(ItalianCookRole agent) {
        this.agent = agent;
        try {
            cookIcon = ImageIO.read(new File("res/chef.png"));
        	} catch (IOException e) {
        	}
        try {
            fridge = ImageIO.read(new File("res/fridge.png"));
        	} catch (IOException e) {
        	}
		try {
            chicken = ImageIO.read(new File("res/chicken.png"));
        	} catch (IOException e) {
        	}
		try {
            steak = ImageIO.read(new File("res/steak.png"));
        	} catch (IOException e) {
        	}
		try {
            salad = ImageIO.read(new File("res/salad.png"));
        	} catch (IOException e) {
        	}
		try {
            pizza = ImageIO.read(new File("res/pizza.png"));
        	} catch (IOException e) {
        	}
		try {
            plate = ImageIO.read(new File("res/plate.png"));
        	} catch (IOException e) {
        	}
    }

    public void updatePosition() {
    	//for (int ix = 1; ix <= NTABLES; ix++) {
    		if (xPos < xDestination)
    			xPos++;
    		else if (xPos > xDestination)
    			xPos--;

    		if (yPos < yDestination)
    			yPos++;
    		else if (yPos > yDestination)
    			yPos--;
    		   
    		if (xPos == xDestination && yPos == yDestination
    				/*& (xDestination == 375) & (yDestination == 150)*/) {
    			if(command == Command.atMarket)
    				agent.msgatMarket();
    			else if(command == Command.atFridge)
    				agent.msgatFridge();
    			BacktoPosition();
    			command = Command.noCommand;
    			
    		}
    	for(int i = 0; i<cookingFoods.size();i++) {		
    		if (cookingFoods.get(i).xfPos < cookingFoods.get(i).xfDestination)
    			cookingFoods.get(i).xfPos++;
    		else if (cookingFoods.get(i).xfPos >cookingFoods.get(i).xfDestination)
    			cookingFoods.get(i).xfPos--;

    		if (cookingFoods.get(i).yfPos < cookingFoods.get(i).yfDestination)
    			yPos++;
    		else if (cookingFoods.get(i).yfPos > cookingFoods.get(i).yfDestination)
    			cookingFoods.get(i).yfPos--;
    		if (cookingFoods.get(i).xfPos == cookingFoods.get(i).xfDestination && cookingFoods.get(i).yfPos == cookingFoods.get(i).yfDestination
    				&& (cookingFoods.get(i).xfDestination == 330 - 40*i) && (cookingFoods.get(i).yfDestination == 300)){
    			cookingFoods.remove(cookingFoods.get(i));
    			//cookingFoods.get(i).plateImg = null;
    			/*
    			cookingFoods.get(i).xfDestination = 360;
    			cookingFoods.get(i).yfDestination = 300;
    			*/
    		}
        }
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.BLUE);
        //g.drawImage(currImg2, 320, 295, null);
        //g.drawImage(plate, 300, 295, null);
        g.drawImage(cookIcon, xPos, yPos, null);
        g.drawImage(fridge, 420, 200, null);
        for(int i=0;i<cookingFoods.size();i++) {
        	g.drawImage(cookingFoods.get(i).plateImg, 320 - 50*i, 295, null);
        	g.drawImage(cookingFoods.get(i).foodImg,cookingFoods.get(i).xfPos - 50*i, cookingFoods.get(i).yfPos, null);
        }
        //g.fillRect(xPos, yPos, 20, 20);
    }

    public boolean isPresent() {
        return true;
    }
    
    public void DoCooking(String Ochoice, int tablenum) {
    	
    switch (Ochoice){
    	case "Steak": cookingFoods.add(new CookingFood(steak, tablenum));
		break;
    	case "Chicken": cookingFoods.add(new CookingFood(chicken, tablenum));
		break;
    	case "Salad": cookingFoods.add(new CookingFood(salad, tablenum));
		break;
    	case "Pizza": cookingFoods.add(new CookingFood(pizza, tablenum));
		break;
    	default:
    	break;
    	}
    }
    
    public void GoToFridge(){
    	yDestination = 230;
    	command = Command.atFridge;
    }
    
    
    public void DoPlateIt(String Ochoice, int tablenum) {
    	for(int i=0; i<cookingFoods.size();i++) {
    		if(cookingFoods.get(i).table == tablenum) {
    			cookingFoods.get(i).plateImg = plate;
    			cookingFoods.get(i).xfDestination = 330 - 40*i;
    			cookingFoods.get(i).yfDestination = 300;
    		}
    	}
    }
    
    public void GotoMarket() {
    	xDestination = 375;
    	yDestination = 150;
    	command = Command.atMarket;
    }
    
    public void BacktoPosition() {
    	xDestination = 375;
    	yDestination = 250;
    	//command = Command.noCommand;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }
    
    private class CookingFood {
    	int xfPos = 360, yfPos = 350; //default food position
        int xfDestination = 360, yfDestination = 350; //default start position
        int table;
        BufferedImage foodImg = null;
        BufferedImage plateImg = null;
        
        CookingFood(BufferedImage img, int tablenum) {
        	table = tablenum;
        	foodImg = img;
        }
    }
}
