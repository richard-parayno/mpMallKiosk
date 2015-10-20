package mpMallKiosk;


import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

public class AdminMenu extends JFrame {

	private JPanel mapPanel;
	private JPanel controlPanel;
	private JButton controlButt1;
	private JButton controlButt2;
	private JButton controlButt3;
	
	
	public AdminMenu() {
		super("Mall Kiosk - Admin Menu");
		
		//Creates the panel for the Map
		mapPanel = new JPanel(new GridLayout(50,50,-1,-1));
		mapPanel.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		mapPanel.setAlignmentY(LEFT_ALIGNMENT);
		
		
		//Creates the panel for the controls
		controlPanel = new JPanel(new GridLayout(10,10));
		controlPanel.setAlignmentY(RIGHT_ALIGNMENT);
		
		controlButt1 = new JButton("Exit");
		controlButt2 = new JButton("Edit");
		controlButt3 = new JButton("Settings");
	
		controlPanel.add(controlButt1);
		controlPanel.add(controlButt2);
		controlPanel.add(controlButt3);
		
		add(controlPanel);
		
		//Creates the boxes
		for (int i =0; i<(50*50); i++){
			final JLabel box = new JLabel();
		    box.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		    mapPanel.add(box);
		}
		
		
		
		add(mapPanel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000,1000);
		setVisible(true);
		setResizable(false);
	}
}
