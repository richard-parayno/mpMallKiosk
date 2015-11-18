package mpMallKiosk;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainMenu extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton adminButton;
	private JButton userButton;
	private JLabel label1;
	private JPanel mainPane;
	private JPanel mapPanel;
	private JPanel controlPanel;
	private JButton controlButt1;
	private JButton controlButt2;
	private JButton controlButt3;
	
	public MainMenu() {
		super("Mall Kiosk");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 1000);
		setVisible(true);
		setResizable(false);
		
		mainPane = new JPanel();
		mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.PAGE_AXIS));
		
		adminButton = new JButton("Admin Menu");
		userButton = new JButton("User Menu");
		label1 = new JLabel("Welcome to the Mall Kiosk!");
		
		adminButton.setAlignmentX(CENTER_ALIGNMENT);
		userButton.setAlignmentX(CENTER_ALIGNMENT);
		label1.setAlignmentX(CENTER_ALIGNMENT);
		
		mainPane.add(label1);
		mainPane.add(adminButton);
		mainPane.add(userButton);
		
		adminButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPane.setVisible(false);
				JPanel MyPanel = new JPanel();
				JTextField pw = new JTextField("DLSU2015");
				JButton enter = new JButton("Enter");
				MyPanel.add(pw);
				MyPanel.add(enter);
				add(MyPanel);
					enter.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							if(pw.getText().equals("DLSU2015")) {
								MyPanel.setVisible(false);
								showAdminMenu();
							}
						}
					});
			}
		});
		add(mainPane);
	}

	public void showAdminMenu() {
		
		//Creates the panel for the controls
		controlPanel = new JPanel(new GridLayout(10,10));
		controlPanel.setAlignmentY(RIGHT_ALIGNMENT);
		
		controlButt1 = new JButton("Exit");
		controlButt2 = new JButton("Edit Mode");
		controlButt3 = new JButton("Settings");
		JButton	controlButt4 = new JButton("Deploy");
	
		controlPanel.add(controlButt1);
		controlPanel.add(controlButt2);
		controlPanel.add(controlButt3);
		controlPanel.add(controlButt4);
		
		add(controlPanel);
	
		//edit
		controlButt2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controlPanel.setVisible(false);
				showEditMenu();
				System.out.println("test");
			}
		});
		
	}
	
	public void showEditMenu () {
		
		JPanel ePanel = new JPanel(new GridLayout(10,10)),
				sizePanel = new JPanel();
		JTextField y = new JTextField("Enter Rows"),
					x = new JTextField("Enter Columns"); 
		JButton button1 = new JButton("Set Mall Size"),
				//setPassable
				button2 = new JButton("Set Cells Unpassable/Passable"),
				//addStore
				button3 = new JButton("Add/Remove Stores"),
				//wipe
				button4 = new JButton("Clear All"),
				//undo
				button5 = new JButton("Undo"),
				//save
				button6 = new JButton("Save & Quit"),
				//enter
				button7 = new JButton("OK");
		
		ePanel.add(button1);
		ePanel.add(button2);
		ePanel.add(button3);
		ePanel.add(button4);
		ePanel.add(button5);
		ePanel.add(button6);
		
		add(ePanel);
		ePanel.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		ePanel.setAlignmentX(RIGHT_ALIGNMENT);
			ePanel.setVisible(true);
		sizePanel.setAlignmentX(RIGHT_ALIGNMENT);
		
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				sizePanel.add(y);
				sizePanel.add(x);
				sizePanel.add(button7);
				ePanel.setVisible(false);
				add(sizePanel);
				sizePanel.setVisible(true);
					button7.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							if(Integer.parseInt(x.getText()) >= 3 && Integer.parseInt(y.getText()) >= 3)
							//Creates the boxes
							for (int i =0; i< (Integer.parseInt(x.getText())*Integer.parseInt(y.getText())) ; i++){
								//Creates the panel for the Map
								mapPanel = new JPanel(new GridLayout(50,50,-1,-1));
								mapPanel.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
								mapPanel.setAlignmentY(LEFT_ALIGNMENT);
								
								final JLabel box = new JLabel();
							    box.setBorder(BorderFactory.createLineBorder(Color.BLACK));
							    mapPanel.add(box);
							}
							
						}
					});
				sizePanel.setVisible(false);
				ePanel.setVisible(true);
				}
		});
		
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		button3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		button4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		button5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		button6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});

	}

}

