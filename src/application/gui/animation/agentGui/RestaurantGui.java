package application.gui.animation.agentGui;

import java.awt.*;

public class RestaurantGui implements Gui {
	private boolean isPresent = true;
	RestaurantGui() {
	}
	
	public boolean isPresent() {
		return isPresent;
	}
	
	public void updatePosition() {
		
	}
	
	public void draw(Graphics2D g) {
		
	}
	
	public void setPresent(boolean p) {
		isPresent = p;
	}
}