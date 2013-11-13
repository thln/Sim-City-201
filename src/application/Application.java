package application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Application extends JFrame implements ActionListener{

	public Application() {
		int WINDOWX = 1200;
        int WINDOWY = 800;
       	setBounds(50, 50, WINDOWX, WINDOWY);
    	setLayout(null);
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public static void main(String[] args) {
        Application app = new Application();
        app.setTitle("SimCity 201 - Team 20");
        app.setVisible(true);
        app.setResizable(true);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
	
}
