package restaurant.gui;

import restaurant.CustomerAgent;
import restaurant.HostAgent;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Subpanel of restaurantPanel.
 * This holds the scroll panes for the customers and, later, for waiters
 */
public class ListPanel extends JPanel implements ActionListener, DocumentListener {

    public JScrollPane pane =
            new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private JPanel view = new JPanel();
    private List<JButton> list = new ArrayList<JButton>();
    private JButton addPersonB = new JButton("Add");    
    
    private JLabel TitleLabel;
    
    private JLabel enterName = new JLabel("Enter name: ");
    private JTextField addPersonName = new JTextField();
    private JCheckBox hungryCB = new JCheckBox();//part of infoLabel
    private JCheckBox onBreakCB = new JCheckBox();//part of infoLabel
    
    private RestaurantPanel restPanel;
    private String type;

    /**
     * Constructor for ListPanel.  Sets up all the gui
     *
     * @param rp   reference to the restaurant panel
     * @param type indicates if this is for customers or waiters
     */
    public ListPanel(RestaurantPanel rp, String type) {
        
    	restPanel = rp;
        this.type = type;

        //do this
        setLayout(new BoxLayout((Container) this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder(type));
  
        enterName.setAlignmentX(Component.RIGHT_ALIGNMENT);
        add(enterName);
       
        addPersonName.addActionListener(this);
        addPersonName.getDocument().addDocumentListener(this);
        add(addPersonName);
        
        if (type == "Waiters") {
        	
        	JPanel addFeatures = new JPanel();
        	addPersonB.addActionListener(this);
        	addFeatures.add(addPersonB);
        	
        	//on break check box
        	onBreakCB.setVisible(true);
        	onBreakCB.addActionListener(this);
        	onBreakCB.setText("On Break");
        	onBreakCB.setEnabled(false);
        	onBreakCB.setSelected(false);
        	addFeatures.add(onBreakCB);
        	
        	add(addFeatures);
        }

        if (type == "Customers") {
        	
        	JPanel addFeatures = new JPanel();
        	
        	addPersonB.addActionListener(this);
            addFeatures.add(addPersonB);
        	
            //hungry check box
        	hungryCB.setVisible(true);
        	hungryCB.addActionListener(this);
        	hungryCB.setText("Hungry?");
        	hungryCB.setEnabled(false);
        	hungryCB.setSelected(true);
        	addFeatures.add(hungryCB);
        	
        	add(addFeatures);
        }
        
        if (type == "Markets") {

        	JPanel addFeatures = new JPanel();

        	addPersonB.addActionListener(this);
        	addFeatures.add(addPersonB);
        	add(addFeatures);
        }
      
        view.setLayout(new BoxLayout((Container) view, BoxLayout.Y_AXIS));
        pane.setViewportView(view);
        pane.setAlignmentX(Component.RIGHT_ALIGNMENT);
        Dimension paneDim = new Dimension(100, 120);
		pane.setPreferredSize(paneDim);
		pane.setMinimumSize(paneDim);
		pane.setMaximumSize(paneDim);
        add(pane);
    }

    /**
     * Method from the ActionListener interface.
     * Handles the event of the add button being pressed
     */
    public void actionPerformed(ActionEvent e) {
        
    	if (e.getSource() == addPersonB) {
        	// Chapter 2.19 describes showInputDialog()
    		if (type == "Customers") {
    			addPerson(addPersonName.getText(), hungryCB.isSelected());
    			addPersonName.setText(null);
    			hungryCB.setEnabled(false);
    			hungryCB.setSelected(true);
    		}
    		
    		if (type == "Waiters") {
    			addPerson(addPersonName.getText(), onBreakCB.isSelected());
    			addPersonName.setText(null);
    			onBreakCB.setEnabled(false);
    			onBreakCB.setSelected(false);
    		}
    		
    		if (type == "Markets") {
    			addPerson(addPersonName.getText());
    			addPersonName.setText(null);
    		}
        }
        
        else {
        	// Isn't the second for loop more beautiful?
            /*for (int i = 0; i < list.size(); i++) {
                JButton temp = list.get(i);*/
        	for (JButton temp:list){
                if (e.getSource() == temp)
                    restPanel.showInfo(type, temp.getText());
            }
        }
    }

    /**
     * If the add button is pressed, this function creates
     * a spot for it in the scroll pane, and tells the restaurant panel
     * to add a new person.
     *
     * @param name name of new person
     */
    public void addPerson(String name) {
    	String TrimmedName = name.trim();
    	if (TrimmedName.length() != 0) {
            JButton button = new JButton(TrimmedName);
            button.setBackground(Color.white);

            Dimension paneSize = pane.getSize();
            Dimension buttonSize = new Dimension(paneSize.width - 20,
                    (int) (paneSize.height / 4));
            button.setPreferredSize(buttonSize);
            button.setMinimumSize(buttonSize);
            button.setMaximumSize(buttonSize);
            button.addActionListener(this);
            list.add(button);
            view.add(button);
            
            restPanel.addPerson(type, TrimmedName);//puts person on a list
            restPanel.showInfo(type, TrimmedName);//puts hungry button on panel
            validate();
        }
    }
    
    
    public void addPerson(String name, boolean boxedChecked) {
      	String TrimmedName = name.trim();
    	
    	if (TrimmedName.length() != 0) {
            JButton button = new JButton(TrimmedName);
            button.setBackground(Color.white);

            Dimension paneSize = pane.getSize();
            Dimension buttonSize = new Dimension(paneSize.width - 20,
                    (int) (paneSize.height / 4));
            button.setPreferredSize(buttonSize);
            button.setMinimumSize(buttonSize);
            button.setMaximumSize(buttonSize);
            button.addActionListener(this);
            list.add(button);
            view.add(button);
            
            if (boxedChecked == true)
            	restPanel.addPerson(type, TrimmedName, true);//puts customer on list as hungry
            else
            	restPanel.addPerson(type, TrimmedName, false);//puts customer on list as not hungry
            	
            	
            restPanel.showInfo(type, TrimmedName);//puts hungry button on panel
            validate();
        }
    }

    
    
	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		String TrimmedName = addPersonName.getText().trim();
		
		if (type == "Customers") {
			if (TrimmedName.length() > 0)
				hungryCB.setEnabled(true);
			else
				hungryCB.setEnabled(false);
		}
		
		if (type == "Waiters") {
			if (TrimmedName.length() > 0)
				onBreakCB.setEnabled(true);
			else
				onBreakCB.setEnabled(false);
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		String TrimmedName = addPersonName.getText().trim();
		
		if (type == "Customers") {
			if (TrimmedName.length() > 0)
				hungryCB.setEnabled(true);
			else
				hungryCB.setEnabled(false);
		}
		
		if (type == "Waiters") {
			if (TrimmedName.length() > 0)
				onBreakCB.setEnabled(true);
			else
				onBreakCB.setEnabled(false);
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		String TrimmedName = addPersonName.getText().trim();
		if (type == "Customers") {
			if (TrimmedName.length() > 0)
				hungryCB.setEnabled(true);
			else
				hungryCB.setEnabled(false);
		}
		
		if (type == "Waiters") {
			if (TrimmedName.length() > 0)
				onBreakCB.setEnabled(true);
			else
				onBreakCB.setEnabled(false);
		}
	}
}
