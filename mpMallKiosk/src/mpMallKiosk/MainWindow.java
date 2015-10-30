package mpMallKiosk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.BorderLayout.CENTER;

/**
 * Created by Richard Parayno on 28/10/2015.
 */

public class MainWindow extends JFrame {

    private JLabel backgroundImage;

    //greetingPanel Components
    private JPanel greetingPanel;
    private JButton enterAdmin;
    private JButton enterShopper;
    private JLabel welcomeLabel;

    //AdminMenu Components
    private JPanel mapPanel;
    private JPanel controlPanel;
    private JPanel rowColPanel;
    private JPanel controlInit;
    private JButton ciExit;
    private JButton ciEdit;
    private JButton ciDeploy;
    private JButton cpMallSize;
    private JButton cpCellPass;
    private JButton cpStoreAdd;
    private JButton cpStoreRemove;
    private JButton cpClear;
    private JButton cpUndo;
    private JButton cpSave;
    private JButton cpConfirm;
    private JButton cpLoad;
    private JTextField yRow;
    private JTextField xCol;
    private JLabel lRow;
    private JLabel lCol;

    //AccessMenu Components
    private JPanel accessPanel;
    private JTextField password;
    private JButton enter;


    private boolean isDeployed;


    public MainWindow () {
        super("Mall Kiosk");

        //Initialize Frame Properties
        setLayout(null);
        setSize(1000,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        backgroundImage = new JLabel(new ImageIcon("background.png"));
        this.add(backgroundImage);
        showGreetingMenu();

        //Make frame visible and non-resizable
        setVisible(true);
        setResizable(false);


    }

    public void showGreetingMenu() {
        //Initialize Buttons
        enterAdmin = new JButton("Admin Menu");
        enterShopper = new JButton("Shopper Menu");

        //Add ActionListeners
        enterAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hides the greetingPanel and calls the showAdminMenu() method
                greetingPanel.setVisible(false);
                requestAccessMenu();
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
        greetingPanel.setLocation(348, 348);
        greetingPanel.setVisible(true);


        //Add greetingPanel to frame
        add(greetingPanel);
    }

    public void showAdminMenu() {
        //Creates a "dummy" mapPanel
        mapPanel = new JPanel();

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
        cpLoad = new JButton("Load Mall");
        cpSave = new JButton("Save Mall");
        cpConfirm = new JButton("OKS");
        //Adds the buttons
        controlPanel.add(cpMallSize);
        controlPanel.add(cpCellPass);
        controlPanel.add(cpStoreAdd);
        controlPanel.add(cpStoreRemove);
        controlPanel.add(cpClear);
        controlPanel.add(cpUndo);
        controlPanel.add(cpLoad);
        controlPanel.add(cpSave);
        controlPanel.add(cpConfirm);
        //Sets the control panel's size and location on the frame
        controlPanel.setSize(300,1000);
        controlPanel.setLocation(800, 200);
        //Sets the layout of the control panel
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));


        //Creates the panel for the controlInit
        controlInit = new JPanel(new FlowLayout());
        //Instantiates the buttons
        ciEdit = new JButton("Edit");
        ciDeploy = new JButton("Deploy");
        ciExit = new JButton("Exit");
        //Adds the buttons
        controlInit.add(ciEdit);
        controlInit.add(ciDeploy);
        controlInit.add(ciExit);
        //Sets the panel's size and location
        controlInit.setSize(300, 300);
        controlInit.setLocation(800, 500);
        //Sets the layout manager of the control panel
        controlInit.setLayout(new BoxLayout(controlInit, BoxLayout.Y_AXIS));

        //Adds the panels to the frames
        add(controlInit);
        setVisible(true);

        //JButton Listeners
        {
            // Set Mall Size -- DONE!!!!!!
            cpMallSize.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // We remove the "dummy" panel we created,
                    remove(mapPanel);
                    if (Integer.parseInt(xCol.getText()) >= 3 && Integer.parseInt(yRow.getText()) >= 3)
                        //Creates the panel for the Map
                        mapPanel = new JPanel(new GridLayout(Integer.parseInt(xCol.getText()), Integer.parseInt(yRow.getText()), -1, -1));
                        mapPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
                        mapPanel.setSize(800, 670);
                        System.out.println("Grid with " + Integer.parseInt(xCol.getText()) + " columns and " + Integer.parseInt(yRow.getText()) + " rows created.");
                        //Creates the boxes
                        for (int i = 0; i < (Integer.parseInt(xCol.getText()) * Integer.parseInt(yRow.getText())); i++) {
                            final JLabel box = new JLabel();
                            box.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                            mapPanel.add(box);
                        }
                    refreshUI(mapPanel);
                    isDeployed = false;
                }
            });
            // Cell Pass/Unpass -- Not Done
            cpCellPass.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    isDeployed = false;
                }
            });
            // Store Add -- Not Done
            cpStoreAdd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    isDeployed = false;
                }
            });
            // Store Remove -- Not Done
            cpStoreRemove.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    isDeployed = false;
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
                    isDeployed = false;
                }
            });
            // Load Button -- Not Done
            cpLoad.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    isDeployed = false;
                }
            });
            // Save Button -- Not Done
            cpSave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    isDeployed = false;
                }
            });

            // Confirm Button -- Not Done
            cpConfirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    isDeployed = false;
                }
            });

            // Edit Button -- Not Done
            ciEdit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    refreshUI(rowColPanel);
                    refreshUI(controlPanel);
                    isDeployed = false;
                }
            });

            // Deploy Button -- Not Done
            ciDeploy.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    isDeployed = true;
                }
            });

            // Exit Button
            ciExit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    remove(mapPanel);
                    remove(rowColPanel);
                    remove(controlPanel);
                    remove(controlInit);
                    showGreetingMenu();
                    validate();
                    repaint();
                    isDeployed = false;
                }
            });
        }
    }

    public void requestAccessMenu() {
        //accessPanel Initialization and adding components
        accessPanel = new JPanel(new FlowLayout());
        password = new JTextField("DLSU2015");
        enter = new JButton("Enter");
        accessPanel.add(password);
        accessPanel.add(enter);
        accessPanel.setSize(300, 300);
        accessPanel.setLocation(348, 348);
        accessPanel.setVisible(true);
        add(accessPanel);

        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(password.getText().equals("DLSU2015")) {
                    accessPanel.setVisible(false);
                    showAdminMenu();
                }
            }
        });
    }

    // This method adds the panel and refreshes the UI.
    public void refreshUI(JPanel panel) {
        add(panel);
        validate();
        repaint();
    }




}
