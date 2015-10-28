package mpMallKiosk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.BorderLayout.CENTER;

/**
 * Created by Richard Parayno on 28/10/2015.
 */


// NOTE: Box creation logic is sketchy. Recommended to change the logic to something more reusable

public class MainWindow extends JFrame {

    //greetingPanel Components
    private JPanel greetingPanel;
    private JButton enterAdmin;
    private JButton enterShopper;
    private JLabel welcomeLabel;

    //AdminMenu Components
    private JPanel mapPanel;
    private JPanel controlPanel;
    private JPanel rowColPanel;
    private JButton cpMallSize;
    private JButton cpCellPass;
    private JButton cpStoreAdd;
    private JButton cpStoreRemove;
    private JButton cpClear;
    private JButton cpUndo;
    private JButton cpSave;
    private JButton cpConfirm;
    private JTextField yRow;
    private JTextField xCol;
    private JLabel lRow;
    private JLabel lCol;






    public MainWindow () {
        super("Mall Kiosk");

        //Initialize Frame Properties
        setLayout(null);
        setSize(1000,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Initialize Buttons
        enterAdmin = new JButton("Admin Menu");
        enterShopper = new JButton("Shopper Menu");

        //Add ActionListeners
        enterAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hides the greetingPanel and calls the showAdminMenu() method
                greetingPanel.setVisible(false);
                showAdminMenu();
            }
        });

        enterShopper.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                greetingPanel.setVisible(false);

            }
        });

        //Initialize Labels
        welcomeLabel = new JLabel("Welcome to the Mall Kiosk!");

        // Greeting Panel Initialization and adding components
        greetingPanel = new JPanel(new FlowLayout());
        greetingPanel.add(welcomeLabel);
        greetingPanel.add(enterAdmin);
        greetingPanel.add(enterShopper);
        greetingPanel.setSize(300, 300);
        greetingPanel.setLocation(348, 500);
        greetingPanel.setVisible(true);

        //Add greetingPanel to frame
        add(greetingPanel);

        //Make frame visible and non-resizable
        setVisible(true);
        setResizable(false);


    }

    public void showAdminMenu() {
        //Creates the panel for the Map
        mapPanel = new JPanel(new GridLayout(50,50,-1,-1));
        mapPanel.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        mapPanel.setSize(800, 700);


        //Creates the panel for the row/col control
        rowColPanel = new JPanel(new GridLayout(4,1));
        //Instantiates the labels
        lRow = new JLabel("Row: ");
        lCol = new JLabel("Col: ");
        //Instantiates the text fields
        yRow = new JTextField();
        xCol = new JTextField();
        // Since JTextField doesn't respect preferred size, we have to force it by setting the minimum size manually
        yRow.setMinimumSize(yRow.getPreferredSize());
        xCol.setMinimumSize(xCol.getPreferredSize());
        //Instantiates the row/col controls
        rowColPanel.setSize(150,150);
        rowColPanel.add(lRow);
        rowColPanel.add(yRow);
        rowColPanel.add(lCol);
        rowColPanel.add(xCol);
        rowColPanel.setLocation(800,25);



        //Creates the panel for the controls
        controlPanel = new JPanel(new FlowLayout());
        //Instantiates the buttons
        cpMallSize = new JButton("Set Mall Size");
        cpCellPass = new JButton("Set Passable/Unpassable");
        cpStoreAdd = new JButton("Add Store");
        cpStoreRemove = new JButton("Remove Store");
        cpClear = new JButton("Clear All");
        cpUndo = new JButton("Undo");
        cpSave = new JButton("Save Mall");
        cpConfirm = new JButton("OKS");



        //Adds the buttons
        controlPanel.add(cpMallSize);
        controlPanel.add(cpCellPass);
        controlPanel.add(cpStoreAdd);
        controlPanel.add(cpStoreRemove);
        controlPanel.add(cpClear);
        controlPanel.add(cpUndo);
        controlPanel.add(cpSave);
        controlPanel.add(cpConfirm);

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
        add(rowColPanel);
        add(controlPanel);
        setVisible(true);

        //JTextField Listeners
        {
            // Set Mall Size
            cpMallSize.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    remove(mapPanel);
                    if (Integer.parseInt(xCol.getText()) >= 3 && Integer.parseInt(yRow.getText()) >= 3)
                        //Creates the boxes
                        for (int i = 0; i < (Integer.parseInt(xCol.getText()) * Integer.parseInt(yRow.getText())); i++) {
                            //Creates the panel for the Map
                            mapPanel = new JPanel(new GridLayout(Integer.parseInt(xCol.getText()), Integer.parseInt(yRow.getText()), -1, -1));
                            mapPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
                            final JLabel box = new JLabel();
                            box.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                            mapPanel.add(box);
                        }
                    add(mapPanel);
                }
            });
            // Cell Pass/Unpass -- Note Done
            cpCellPass.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            // Store Add -- Not Done
            cpStoreAdd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {


                }
            });
            // Store Remove -- Not Done
            cpStoreRemove.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            // Clear Button -- Not Done
            cpClear.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            // Undo Button -- Not Done
            cpUndo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            // Save Button -- Not Done
            cpSave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });

            // Confirm Button -- Not Done
            cpConfirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
        }
    }


}
