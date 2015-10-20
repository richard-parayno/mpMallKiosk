package mpMallKiosk;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainMenu extends JFrame{
	
	private JButton adminButton;
	private JButton userButton;
	private JLabel label1;
	private JPanel mainPane;
	
	public MainMenu() {
		super("Mall Kiosk");
		
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
		
		add(mainPane);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 200);
		setVisible(true);
		setResizable(false);
		
	}

}
