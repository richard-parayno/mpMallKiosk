package mpMallKiosk;


import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

public class AdminMenu extends JFrame {

	// GUI designed by Richard Parayno and Kurt Ebol
	
	private JPanel mapPanel;
	private JPanel controlPanel;
	private JPanel mainPanel;
	private JButton controlButt1;
	private JButton controlButt2;
	private JButton controlButt3;
	
	
	public AdminMenu() {
		super("Mall Kiosk - Admin Menu");

		mainPanel = new JPanel(new GridLayout(1,2));


		//Creates the panel for the Map
		mapPanel = new JPanel(new GridLayout(50,50,-1,-1));
		mapPanel.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));


		
		
		//Creates the panel for the controls
		controlPanel = new JPanel(new GridLayout(10,10));

		
		controlButt1 = new JButton("Exit");
		controlButt2 = new JButton("Edit");
		controlButt3 = new JButton("Settings");
	
		controlPanel.add(controlButt1);
		controlPanel.add(controlButt2);
		controlPanel.add(controlButt3);

		controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		//Creates the boxes
		for (int i =0; i<(50*50); i++){
			final JLabel box = new JLabel();
		    box.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		    mapPanel.add(box);
		}

		mainPanel.add(mapPanel);
		mainPanel.add(controlPanel);
		add(mainPanel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000,1000);
		setVisible(true);
		setResizable(false);
	}
}
