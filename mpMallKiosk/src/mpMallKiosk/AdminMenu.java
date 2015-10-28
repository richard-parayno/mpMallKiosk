package mpMallKiosk;


import java.awt.*;

import javax.swing.*;

public class AdminMenu extends JFrame {

	// GUI designed by Richard Parayno
	
	private JPanel mapPanel;
	private JPanel controlPanel;
	private JButton controlButt1;
	private JButton controlButt2;
	private JButton controlButt3;
	
	
	public AdminMenu() {
		super("Mall Kiosk - Admin Menu");

		setLayout(null);


	}

	public void showAdminMenu() {
		//Creates the panel for the Map
		mapPanel = new JPanel(new GridLayout(50,50,-1,-1));
		mapPanel.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		mapPanel.setSize(800, 700);

		//Creates the panel for the controls
		controlPanel = new JPanel(new FlowLayout());
		//Instantiates the buttons
		controlButt1 = new JButton("Exit");
		controlButt2 = new JButton("Edit");
		controlButt3 = new JButton("Settings");
		//Adds the buttons
		controlPanel.add(controlButt1);
		controlPanel.add(controlButt2);
		controlPanel.add(controlButt3);
		//Sets the control panel's size and location on the frame
		controlPanel.setSize(300,1000);
		controlPanel.setLocation(800, 200);
		//Sets the layout of the control panel
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
		//Creates the boxes
		for (int i =0; i<(50*50); i++){
			final JLabel box = new JLabel();
			box.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			mapPanel.add(box);
		}
		//Adds the panels to the frames
		add(mapPanel);
		add(controlPanel);


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000,1000);
		setVisible(true);
		setResizable(false);
	}
}
