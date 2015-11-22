/* VIEWMAP HAS TO BE UNEDITABLE. shift logic to shopper menu, leave visuals here
 * Good job on the shopper module!! hahahhaa kakakita ko lang
 * kabit logic na, almost done with the admin module.
 * help me find a way kung pano magppaint ng icon sa jlabel pag gagawa ng grid given
 * yung 2d array na anduin yung cells with representations. kelangan sabay sa pag gawa ng jlabel yung icon/color.
 * 
 * inserted a marker on where i think we should put the algo for the store icons 
 * 
 * also think na gagana yung aasa tayo sa text sa jlabel kasi magandang implementation na. it's just the matter of hiding the
 * text para di kita. may setforeground naman eh
 */






package mpMallKiosk;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Created by Richard Parayno on 28/10/2015.
 */

@SuppressWarnings("unused")
public class MainWindow extends JFrame implements MouseListener, MouseMotionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Graphics g;
    private ImageIcon backgroundImage;
    private boolean isHighlighted;
    private boolean mapIsCreated;
    private boolean isAdmin;
    //greetingPanel Components
    private JPanel greetingPanel;
    private JButton enterAdmin;
    private JButton enterShopper;
    private JLabel welcomeLabel;

    //AdminMenu Components
    private JPanel mapPanel,
    	controlPanel,
    	rowColPanel,
    	controlInit;
    private JButton ciExit,
      ciEdit,
      ciDeploy,
      cpMallSize,
      cpCellPass,
      cpStoreAdd,
      cpStoreRemove,
      cpClear,
      cpUndo,
      cpSave,
      cpConfirm,
      cpLoad;
    private JTextField yRow,
    	 xCol;
    private JLabel lRow,
   	 	lCol;

    //ShopperMenu Components
    private JPanel smControls,
            getDirectionControls,
            storeDetailControls,
            displayStoreControls;

    private JButton displayStoreAlphabetical,
        displayStoreClassify,
        displayStoreName,
        dsReturnHome;

    private JButton viewMap,
            displayStore,
            storeDetail,
            getDirection,
            returnHome;

    private JButton getDirectionSpecific,
            getDirectionSeries,
            getDirectionSpecial,
            gdReturnHome;

    //PassableMenu Components
    private JPanel passablePanel;
    private JLabel lPassable;
    private JButton ppPassable,
	      ppNotPassable,
	      ppClose;
    
    //mouseListener components
    private int mouseX;
    private int mouseY;

    //AccessMenu Components
    private JPanel accessPanel;
    private JTextField password;
    private JButton enter;

    //StoreOp-Add Components
    private JPanel storeOpAdd;
    private JTextField fname;

    private JLabel lname;
    private JComboBox storeList;
    private JLabel ltype;
    private JButton b1;
    private JButton soExit;
    private String[] storeType;

    private int storeShown;

    //StoreOp-Remove Components
    private JPanel storeOpRemove;

    
    //Logic Components
    private Layout layout;
    private Cell[][] processCells;
    private ArrayList<Cell> selectedCells = new ArrayList<Cell>();
    private LinkedList<Integer> cellsToProcessX = new LinkedList<Integer>(), 
    			cellsToProcessY = new LinkedList<Integer>();
    
    private int row, col;
    private final int CLEAR_SCREEN = 99999;
    private boolean isReallyHighlighted;
    private Mall m;
    
    //maayos na logic components
    private ArrayList<Cell> selected = new ArrayList<Cell>();

    private JPanel foodPanel;
    private String[] foodType;
    private JComboBox foodList;
    private JLabel specialtyDish;
    private JTextField specialty;

    private JPanel clothPanel;
    private JLabel caterLabel;
    private String[] catersTo;
    private JComboBox caterList;

    private JPanel hardPanel;
    private JLabel sells;
    private JCheckBox officeSupplies,
            schoolSupplies,
            homeSupplies;
    private ArrayList<String> supplies;

    private JPanel gadgetPanel;
    private JLabel worksWith;
    private JCheckBox pc,
            laptop,
            phone,
            tab;
    private String[] service;
    private JComboBox servicesOffered;
    private ArrayList<String> gadgetsOffered;

    private BufferedReader br;
    private boolean isDeployed = false; //initialized to false. redundant lines. change to true when deploy button is pressed

    /**
     * This is responsible for setting up the properties and the JFrame of the MainWindow class, wherein all the elements
     * related to the Mall Kiosk's GUI will be added.
     */
    public MainWindow () {
        super("Mall Kiosk");

        //Initialize Frame Properties
        setLayout(null);
        setSize(1000,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setContentPane(new JLabel(new ImageIcon("//Users//Admin//IdeaProjects//mpMallKiosk//mpMallKiosk//src//mpMallKiosk//gui.png")));

        mapIsCreated = false;
        isAdmin = false;

        
        String mallName = JOptionPane.showInputDialog("What is the name of the mall?");
        m = new Mall(mallName);
        setTitle(mallName + " Shopping Kiosk");
        showGreetingMenu();

        //Make frame visible and non-resizable
        setVisible(true);
        setResizable(false);
    }

    /**
     * This is where the first screen of the Mall Kiosk is instantiated and displayed. It also serves
     * as a gateway to other parts of the program, the Admin Menu and the Shopper's Menu.
     */
    public void showGreetingMenu() {
        isAdmin = false;

        //Initialize Buttons
        enterAdmin = new JButton("Admin Menu");
        enterShopper = new JButton("Shopper Menu");

        enterAdmin.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        enterShopper.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));

        setContentPane(new JLabel(new ImageIcon("//Users//Admin//IdeaProjects//mpMallKiosk//mpMallKiosk//src//mpMallKiosk//gui.png")));

        //Add ActionListeners
        enterAdmin.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pass = JOptionPane.showInputDialog("Password: ");
                System.out.println(pass);
                if(pass.equals(" ")){
                	greetingPanel.setVisible(false);
                	remove(greetingPanel);
                	showAdminMenu();
                	}
                }
        });

        enterShopper.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!m.isDeployed) {
                    greetingPanel.setVisible(false);
                    remove(greetingPanel);
                    showShopperMenu();
                }
                else
                    JOptionPane.showMessageDialog(
                            null, "Map not yet deployed! Please contact an Administrator.", "Error", JOptionPane.WARNING_MESSAGE);

            }
        });

        // Greeting Panel Initialization and adding components
        greetingPanel = new JPanel(new GridLayout(4,4));
        //greetingPanel.add(welcomeLabel);
        greetingPanel.add(enterAdmin);
        greetingPanel.add(enterShopper);
        greetingPanel.setSize(300, 300);
        greetingPanel.setLocation(350, 300);
        greetingPanel.setVisible(true);
        greetingPanel.setOpaque(false);


        //Add greetingPanel to frame
        add(greetingPanel);
    }

    /**
     * Instantiates the Admin Menu. It is where various buttons and
     * their corresponding logic essential to the admin menu is located.
     *
     */
    public void showAdminMenu() {
        //Creates a "dummy" mapPanel
        mapPanel = new JPanel();

        setContentPane(new JLabel(new ImageIcon("//Users//Admin//IdeaProjects//mpMallKiosk//mpMallKiosk//src//mpMallKiosk//admin-gui.png")));
        isAdmin = true;
        //Creates the panel for the row/col control
        rowColPanel = new JPanel(new GridLayout(4,1));
        //Instantiates the labels
        lRow = new JLabel("Row: ");
        lCol = new JLabel("Col: ");
        // Sets label properties
        lRow.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        lCol.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
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

        rowColPanel.setOpaque(false);

        //Creates the panel for the controls
        controlPanel = new JPanel(new FlowLayout());
        //Instantiates the buttons
        cpMallSize = new JButton("Set Mall Size");
        cpCellPass = new JButton("Set Cell Pass/Unpassable");
        cpStoreAdd = new JButton("Add a Store");
        cpStoreRemove = new JButton("Remove Store");
        cpClear = new JButton("Clear All");
        cpUndo = new JButton("Undo");
        cpLoad = new JButton("Load Mall");
        cpSave = new JButton("Save Mall");
        cpConfirm = new JButton("Confirm");
        //Sets button properties
        cpMallSize.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        cpCellPass.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        cpStoreAdd.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        cpStoreRemove.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        cpClear.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        cpUndo.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        cpLoad.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        cpSave.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        cpConfirm.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
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

        controlPanel.setOpaque(false);


        //Creates the panel for the controlInit
        controlInit = new JPanel(new FlowLayout());
        //Instantiates the buttons
        ciEdit = new JButton("Edit Mall Layout");
        ciDeploy = new JButton("Deploy");
        ciExit = new JButton("Exit");
        //Sets button properties
        ciEdit.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        ciDeploy.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        ciExit.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        //Adds the buttons
        controlInit.add(ciEdit);
        controlInit.add(ciDeploy);
        controlInit.add(ciExit);
        //Sets the panel's size and location
        controlInit.setSize(300, 300);
        controlInit.setLocation(800, 500);
        //Sets the layout manager of the control panel
        controlInit.setLayout(new BoxLayout(controlInit, BoxLayout.Y_AXIS));

        controlInit.setOpaque(false);

        //Adds the panels to the frames
        add(controlInit);
        setVisible(true);

        //JButton Listeners
        {
            // Set Mall Size -- DONE
            cpMallSize.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // We remove the "dummy" panel we created,
                    remove(mapPanel);            
                    if (Integer.parseInt(xCol.getText()) >= 3 && Integer.parseInt(yRow.getText()) >= 3 && Integer.parseInt(xCol.getText()) <= 50 && Integer.parseInt(yRow.getText()) <= 30){
                        //Creates the panel for the Map
                    	m.setSize(Integer.parseInt(yRow.getText()), Integer.parseInt(xCol.getText()));
                        createGrid(m.getCellsBeingProcessed());
                    }
                    else JOptionPane.showMessageDialog(null, "Invalid number of cells.");
                }
            });
            // Cell Pass/Unpass -- Not Done
            cpCellPass.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	passableMenu();
                	m.setUnpassableOrPassable(m.selected);	                
                	refreshUI(mapPanel);             
                }
            });
            // Store Add -- Not Done
            cpStoreAdd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	Cell[][] tempo;
                	addStore(m.selected);
                	m.addMove(processCells);
                	updateLayout(processCells);
                	System.out.println("Added x store");
                }
            });
            // Store Remove -- Not Done
            cpStoreRemove.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	
                	//dropdown list, ako na bahala sa elements
                	//remove button, back to controlPanel button
                	removeStore();
                
                	//remove array of cells of a store
                	//moves.push(processCells);
                	//updateLayout(processCells);
                    //refreshUI(mapPanel);
                    System.out.println("Removed x store");
                }
            });
            // Clear Button -- DONE
            cpClear.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to clear?");
                	System.out.println(confirm);
                	if(confirm == JOptionPane.YES_OPTION) {
                		System.out.println("Clearing...");
                		mapPanel.setVisible(false);
                		m.addMove(new Cell[2][2]);
                    	System.out.println("Cleared");
                    	
                	}
                	else System.out.println("Clearing cancelled.");
                	
                }
            });
            // Undo Button -- For testing
            cpUndo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //undo last move
                	if(!m.getMoves().isEmpty()){
	                	Cell[][] lastGrid = m.getMoves().pop();
	                	processCells = lastGrid;
	                	System.out.println(lastGrid.length + ", " + lastGrid[0].length);
	                	remove(mapPanel);
	                	createGrid(lastGrid);
	                	System.out.println("Undo");
                	}
                }
            });
            // Load Button -- Not Done
            cpLoad.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                     loadLayout();
                     System.out.println("Loading layout from file");
                }
            });
            // Save Button -- Not Done
            cpSave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                     System.out.println("Saving layout to file");
                }
            });

            // Confirm Button -- Not Done
            cpConfirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                     
                }
            });

            // Edit Button -- Not Done
            ciEdit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    refreshUI(rowColPanel);
                    refreshUI(controlPanel);
                     
                }
            });

            // Deploy Button -- Not Done
            ciDeploy.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    isDeployed = true;
                    //if not layout is deployed, error message
                    //displayErrorMessage("No Layout is deployed at the moment");
                    System.out.println("Deploying...");
                }
            });

            // Exit Button
            ciExit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    remove(rowColPanel);
                    remove(controlPanel);
                    remove(controlInit);
                    remove(mapPanel);
                    showGreetingMenu();
                    validate();
                    repaint();
                     
                }
            });
        }
    }

    /**
     * Instantiates the Shopper Menu. It is where various buttons and
     * their corresponding logic essential to the shopper menu is located.
     */
    public void showShopperMenu() {
        isAdmin = false;

        // Buttons under smControls
        viewMap = new JButton("View Map");
        displayStore = new JButton("Display Store");
        storeDetail = new JButton("Store Detail");
        getDirection = new JButton("Get Direction");
        returnHome = new JButton("Back");

        viewMap.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        displayStore.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        storeDetail.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        getDirection.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        returnHome.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));

        // Buttons under getDirectionControls
        getDirectionSpecific = new JButton("Specific Store");
        getDirectionSeries = new JButton("Series of Stores");
        getDirectionSpecial = new JButton("Special Queries");
        gdReturnHome = new JButton("Back");

        getDirectionSpecific.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        getDirectionSeries.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        getDirectionSpecial.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        gdReturnHome.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));

        // Buttons under displayStoreControls
        displayStoreAlphabetical = new JButton("Alphabetical");
        displayStoreClassify = new JButton("By Classification");
        displayStoreName = new JButton("By Name");
        dsReturnHome = new JButton("Back");
        displayStoreAlphabetical.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        displayStoreClassify.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        displayStoreName.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        dsReturnHome.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));

        //smControls
        smControls = new JPanel(new GridLayout(5,4));
        smControls.setSize(300, 300);
        smControls.setLocation(350, 300);
        smControls.setVisible(true);
        smControls.setOpaque(false);

        smControls.add(viewMap);
        smControls.add(displayStore);
        smControls.add(storeDetail);
        smControls.add(getDirection);
        smControls.add(returnHome);
        refreshUI(smControls);

        //getDirectionControls
        getDirectionControls = new JPanel(new GridLayout(4,4));
        getDirectionControls.setSize(300,300);
        getDirectionControls.setLocation(350, 300);
        getDirectionControls.setVisible(true);
        getDirectionControls.setOpaque(false);

        getDirectionControls.add(getDirectionSpecific);
        getDirectionControls.add(getDirectionSeries);
        getDirectionControls.add(getDirectionSpecial);
        getDirectionControls.add(gdReturnHome);

        //displayStoreControls
        displayStoreControls = new JPanel(new GridLayout(4,4));
        displayStoreControls.setSize(300,300);
        displayStoreControls.setLocation(350,300);
        displayStoreControls.setVisible(true);
        displayStoreControls.setOpaque(false);

        displayStoreControls.add(displayStoreAlphabetical);
        displayStoreControls.add(displayStoreClassify);
        displayStoreControls.add(displayStoreName);
        displayStoreControls.add(dsReturnHome);




        //smControls Listeners
        {
            viewMap.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    remove(smControls);
                    refreshUI(mapPanel);
                    mapPanel.setLocation(100,20);
                }
            });
            displayStore.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    remove(smControls);
                    refreshUI(displayStoreControls);
                }
            });
            storeDetail.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //THIS NEEDS CLARIFICATION!!!
                }
            });
            getDirection.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    remove(smControls);
                    refreshUI(getDirectionControls);
                }
            });
            returnHome.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    remove(smControls);
                    showGreetingMenu();
                    repaint();
                    validate();
                }
            });

        }

        //getDirectionControls Listeners
        {
            getDirectionSpecific.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            getDirectionSeries.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            getDirectionSpecial.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            gdReturnHome.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    remove(getDirectionControls);
                    refreshUI(smControls);
                    repaint();
                    validate();
                }
            });
        }

        //displayStoreControls Listeners
        {
            displayStoreAlphabetical.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            displayStoreClassify.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            displayStoreName.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            dsReturnHome.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    remove(displayStoreControls);
                    refreshUI(smControls);
                    repaint();
                    validate();
                }
            });
        }
    }

    // This method adds the panel and refreshes the UI.

    /**
     * Adds a panel to the frame while also refreshing the UI.
     * @param panel
     */
    public void refreshUI(JPanel panel) {
        add(panel);
        validate();
        repaint();
    }

    /**
     * Instantiates the storeOpAdd JPanel and relevant components. Includes the ability to add a store
     * to the map layout currently being edited.
     * @param store
     */
	public void addStore (ArrayList<Cell> store) {
        remove(controlPanel);
        remove(controlInit);
        remove(rowColPanel);

        storeOpAdd = new JPanel(new GridLayout(6,1));

        fname = new JTextField();
        fname.setMinimumSize(fname.getPreferredSize());

        lname = new JLabel("Store Name: ");
        ltype = new JLabel("Store Type: ");
        lname.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        ltype.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));

        b1 = new JButton("Enter");
        soExit = new JButton("Close");
        b1.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        soExit.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));

        storeType = new String[]{"Food", "Clothing/Apparel", "Hardware/Supplies", "Gadgets", "Service Stores"};
        storeList = new JComboBox(storeType);
        storeList.setSelectedIndex(0);
        storeList.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));


		storeOpAdd.add(lname);
		storeOpAdd.add(fname);
		storeOpAdd.add(ltype);
        storeOpAdd.add(storeList);
		storeOpAdd.add(b1);
		storeOpAdd.add(soExit);
		storeOpAdd.setSize(150,150);
        storeOpAdd.setLocation(800, 275);
        storeOpAdd.setOpaque(false);
		refreshUI(storeOpAdd);
		
		storeShown = 0;
		
		
		//Store-specific components
		//food
		foodType = new String[]{"Filipino", "Chinese", "Italian", "Japanese"};
		foodList = new JComboBox(foodType);
		specialtyDish = new JLabel("Specialty dish: ");
        specialty = new JTextField();
        foodList.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        specialtyDish.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));

        foodList.setSelectedIndex(0);
        foodPanel = new JPanel(new GridLayout(4,1));
        foodPanel.add(foodList);
        foodPanel.add(specialtyDish);
        foodPanel.add(specialty);
        foodPanel.setSize(150,150);
        foodPanel.setLocation(800, 100);
        foodPanel.setOpaque(false);

		
		//clothing
        caterLabel = new JLabel("Caters to:");
		catersTo = new String[]{"Men", "Women", "Both"};
		caterList = new JComboBox(catersTo);
        caterLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        caterList.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));

        caterList.setSelectedIndex(0);
        clothPanel = new JPanel(new GridLayout(2,0));
        clothPanel.add(caterLabel);
        clothPanel.add(caterList);
        clothPanel.setSize(150, 150);
        clothPanel.setLocation(800, 100);
        clothPanel.setOpaque(false);

        //hardware
		sells = new JLabel("Sells: ");
		officeSupplies = new JCheckBox("Office Supplies");
        schoolSupplies = new JCheckBox("School Supplies");
        homeSupplies = new JCheckBox("Home Supplies");
        sells.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        officeSupplies.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        schoolSupplies.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        homeSupplies.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));

		//hardware logic
		ArrayList<String> supplies = new ArrayList<String>();
		//hardware listeners
		officeSupplies.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED && !supplies.contains("Office Supplies"))
					supplies.add("Office Supplies");				
			}
		});
		
		schoolSupplies.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED && !supplies.contains("School Supplies"))
					supplies.add("School Supplies");				
			}
		});
		
		homeSupplies.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED && !supplies.contains("Home Supplies"))
					supplies.add("Home Supplies");				
			}
		});

        hardPanel = new JPanel(new GridLayout(4,1));
        hardPanel.add(sells);
        hardPanel.add(officeSupplies);
        hardPanel.add(schoolSupplies);
        hardPanel.add(homeSupplies);
        hardPanel.setSize(150, 150);
        hardPanel.setLocation(800, 100);
        hardPanel.setOpaque(false);
		
		
		//gadgets
		worksWith = new JLabel("Works with: ");
		pc = new JCheckBox("Desktops");
        laptop = new JCheckBox("Laptops");
        phone = new JCheckBox("Phones");
        tab = new JCheckBox("Tablets");
		service = new String[]{"Sells", "Repairs", "Both"};
        servicesOffered = new JComboBox(service);
        worksWith.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        pc.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        laptop.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        phone.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        tab.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        servicesOffered.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));

        gadgetsOffered = new ArrayList<String>();

        servicesOffered.setSelectedIndex(0);
        gadgetPanel = new JPanel(new GridLayout(7,1));
        gadgetPanel.add(worksWith);
        gadgetPanel.add(pc);
        gadgetPanel.add(laptop);
        gadgetPanel.add(phone);
        gadgetPanel.add(tab);
        gadgetPanel.add(servicesOffered);
        gadgetPanel.setSize(150, 150);
        gadgetPanel.setLocation(800, 100);
        gadgetPanel.setOpaque(false);


		//gadget listeners
		pc.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED && !gadgetsOffered.contains("Desktops")) {
                    gadgetsOffered.add("Desktops");
                }
				else if(e.getStateChange() == ItemEvent.DESELECTED)
					gadgetsOffered.remove(gadgetsOffered.indexOf("Desktops"));

			}

		});
		laptop.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED && !gadgetsOffered.contains("Laptops")) {
                    gadgetsOffered.add("Laptops");
                }
				
				else if(e.getStateChange() == ItemEvent.DESELECTED)
					gadgetsOffered.remove(gadgetsOffered.indexOf("Laptops"));
			}
		});
		phone.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED && !gadgetsOffered.contains("Phones")) {
                    gadgetsOffered.add("Phones");
                }
				else if(e.getStateChange() == ItemEvent.DESELECTED)
					gadgetsOffered.remove(gadgetsOffered.indexOf("Phones"));
			}
		});
		tab.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED && !gadgetsOffered.contains("Tablets"))
					gadgetsOffered.add("Tablets");
				else if(e.getStateChange() == ItemEvent.DESELECTED)
					gadgetsOffered.remove(gadgetsOffered.indexOf("Tablets"));
			}
		});


        //Listeners
        {
            storeList.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                	switch(storeList.getSelectedIndex()){
                		case 0: //food
                			storeShown = 0;
	                		//foodpanel
	                		//insert food components to panel
                			clothPanel.setVisible(false);
                			hardPanel.setVisible(false);
                			gadgetPanel.setVisible(false);
                			foodPanel.setVisible(true);
                            refreshUI(foodPanel);                      

                        break;
                		
                		case 1: //clothing
                			storeShown = 1;
                			//clothing panel
                			foodPanel.setVisible(false);
                			hardPanel.setVisible(false);
                			gadgetPanel.setVisible(false);
                			clothPanel.setVisible(true);
                			refreshUI(clothPanel);
                			
                		break;
                		
                		case 2: //hardware
                			storeShown = 2;
                			//hardware panel
                			clothPanel.setVisible(false);
                			foodPanel.setVisible(false);
                			gadgetPanel.setVisible(false);
                			hardPanel.setVisible(true);
                            refreshUI(hardPanel);
                			
                        break;
                			
                		case 3: //gadgets
                			storeShown = 3;
                			//gadgetpanel            				
            				//insert gadget components
                			clothPanel.setVisible(false);
                			hardPanel.setVisible(false);
                			foodPanel.setVisible(false);
                			gadgetPanel.setVisible(true);
                            refreshUI(gadgetPanel);
                        
                        break;
                          
                		case 4: 
                			clothPanel.setVisible(false);
	            			hardPanel.setVisible(false);
	            			gadgetPanel.setVisible(false);
	            			foodPanel.setVisible(false);
                	}
                }
            });


            b1.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                	boolean allSet = false;
                	switch(storeShown) {
                		case 0: 
                			if(specialty.getText() != null)
                				allSet = true;
                		break;
                		case 1: allSet = true;
                		break;
                		case 2: 
                			if(supplies.size() > 0) allSet = true;
                		break;
                		case 3: 
                			if(gadgetsOffered.size() > 0) allSet = true;
                		break;
                			
                	}
                    
                	if(lname.getText() == null) allSet = false;
                	
                	if(allSet) {
	                	switch(storeList.getSelectedIndex()){
	            		case 0: //food
	            			m.addStore(new Foodstore(fname.getText(), store, foodList.getSelectedItem(), specialty.getText()));
	            			System.out.println("Added " + fname.getText());
	            			JOptionPane.showMessageDialog(null, "Successfully added " + fname.getText() + "!");
	            			
	            			remove(storeOpAdd);  
	            			clothPanel.setVisible(false);
	            			hardPanel.setVisible(false);
	            			gadgetPanel.setVisible(false);
	            			foodPanel.setVisible(false);
	            			refreshUI(rowColPanel);
	            			refreshUI(controlInit);
	            			refreshUI(controlPanel);
	            			
	            			m.addMove(m.cellsBeingProcessed);
	            			createGrid(m.cellsBeingProcessed);
	            			
	            		break;
	            		
	            		case 1: //clothing
	            			m.addStore(new Clothing(fname.getText(), store, caterList.getSelectedItem()));
	            			JOptionPane.showMessageDialog(null, "Successfully added " + fname.getText() + "!");
	            			
	            			remove(storeOpAdd);  
	            			clothPanel.setVisible(false);
	            			hardPanel.setVisible(false);
	            			gadgetPanel.setVisible(false);
	            			foodPanel.setVisible(false);
	            			refreshUI(rowColPanel);
	            			refreshUI(controlInit);
	            			refreshUI(controlPanel);
	            			
	            			m.addMove(m.cellsBeingProcessed);
	            			createGrid(m.cellsBeingProcessed);
	            			
	            		break;
	            		
	            		case 2: //hardware
	            			m.addStore(new Hardware(fname.getText(), store, supplies));  
	            			System.out.println("Added " + fname.getText());
	            			JOptionPane.showMessageDialog(null, "Successfully added " + fname.getText() + "!");
	            			
	            			remove(storeOpAdd);  
	            			clothPanel.setVisible(false);
	            			hardPanel.setVisible(false);
	            			gadgetPanel.setVisible(false);
	            			foodPanel.setVisible(false);
	            			refreshUI(rowColPanel);
	            			refreshUI(controlInit);
	            			refreshUI(controlPanel);
	            			
	            			m.addMove(m.cellsBeingProcessed);
	            			createGrid(m.cellsBeingProcessed);
	            			
	            		break;
	            			
	            		case 3: //gadgets
	            			m.addStore(new Gadget(fname.getText(), store, gadgetsOffered, servicesOffered));
	            			System.out.println("Added " + fname.getText());
	            			JOptionPane.showMessageDialog(null, "Successfully added " + fname.getText() + "!");
	            			
	            			remove(storeOpAdd);  
	            			clothPanel.setVisible(false);
	            			hardPanel.setVisible(false);
	            			gadgetPanel.setVisible(false);
	            			foodPanel.setVisible(false);
	            			refreshUI(rowColPanel);
	            			refreshUI(controlInit);
	            			refreshUI(controlPanel);
	            			
	            			m.addMove(m.cellsBeingProcessed);
	            			createGrid(m.cellsBeingProcessed);
	                	}
	                	
	                    refreshUI(mapPanel);
	                    
	                    
                	}
                	else JOptionPane.showMessageDialog(null, "Some fields are invalid.");
                }
            });

            soExit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    remove(storeOpAdd);
                    clothPanel.setVisible(false);
                    hardPanel.setVisible(false);
                    gadgetPanel.setVisible(false);
                    foodPanel.setVisible(false);
                    refreshUI(rowColPanel);
                    refreshUI(controlInit);
                    refreshUI(controlPanel);
                }
            });
        }
	}

    /**
     * Instantiates the storeOpRemove JPanel and relevant components. Includes the ability to remove
     * stores from the current map layout.
     */
    public void removeStore () {
        remove(controlPanel);
        remove(controlInit);
        remove(rowColPanel);

        storeOpRemove = new JPanel(new GridLayout(6,1));
        lname = new JLabel("Store Names: ");
        lname.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));


        b1 = new JButton("Enter");
        soExit = new JButton("Close");
        b1.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        soExit.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        
        storeType = new String[m.stores.size()];
        for(int i = 0; i < m.stores.size(); i++){
        	storeType[i] = m.stores.get(i).getName();
        }
        
        storeList = new JComboBox(storeType);
        storeList.setSelectedIndex(0);
        storeList.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));


		storeOpRemove.add(lname);
        storeOpRemove.add(storeList);
		storeOpRemove.add(b1);
		storeOpRemove.add(soExit);
		storeOpRemove.setSize(150,150);
        storeOpRemove.setLocation(800, 275);
		refreshUI(storeOpRemove);

        //Listeners


        b1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                m.removeStore((String) storeList.getSelectedItem());
                refreshUI(mapPanel);
                m.addMove(m.cellsBeingProcessed);
            }
        });

        soExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(storeOpRemove);

                refreshUI(rowColPanel);
                refreshUI(controlInit);
                refreshUI(controlPanel);
            }
        });
    }


    /**
     * Instantiates the mapPanel JPanel. Generates the grid where the map will be based on.
     * @param cells
     */
	public void createGrid (Cell[][] cells) {
		int row = cells.length;
		int col = cells[0].length;
		mapPanel = new JPanel(new GridLayout(row, col, -1, -1));
	    mapPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
	    mapPanel.setSize(800, 670);
        mapPanel.setLocation(0, 30);
        mapPanel.setOpaque(false);
        processCells = cells;
	    System.out.println("Grid with " + col + " columns and " + row + " rows created.");
	
		int currentRow = 0, currentCol = 0;
		for (int i = 0; i < cells[0].length * cells.length; i++) {
        	JLabel box = new JLabel(new ImageIcon("//Users//Admin//IdeaProjects//mpMallKiosk//mpMallKiosk//src//mpMallKiosk//floor-tile.png"));
        	
        	if(cells[currentRow][currentCol].getRepresentation() == 1){
        		box.setIcon(new ImageIcon("//Users//Admin//IdeaProjects//mpMallKiosk//mpMallKiosk//src//mpMallKiosk//door.png"));
        		box.setOpaque(true);
        		processCells[currentRow][currentCol] = new Cell(currentRow, currentCol, 1);
        	}
        	
 //SERIES OF IF-ELSES HERE -----------------------------------------------------------------------------------------------------------------------------------------------------------------
        	
        	else processCells[currentRow][currentCol] = new Cell(currentRow, currentCol);
            box.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            box.setText(currentRow + ","+ currentCol);
            box.setBackground(Color.white);
            box.setForeground(Color.white);
            box.repaint();
            validate();
            mapPanel.add(box);
            currentCol++;
            if(currentCol == col) {
        		currentRow++;
        		currentCol = 0;
        	}


            //Mouse Listeners
            {
                box.addMouseMotionListener(new MouseMotionListener() {
                    @Override
                    public void mouseDragged(MouseEvent e) {

                    }

                    @Override
                    public void mouseMoved(MouseEvent e) {
                        //System.out.println("MOUSE MOVED at: " + e.getLocationOnScreen());
                    }
                });
                box.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    	//y
                    	int y = Integer.parseInt(box.getText().substring(box.getText().lastIndexOf(",") + 1));
                    	//x
                    	int x = Integer.parseInt(box.getText().substring(0, box.getText().lastIndexOf(",")));

                    	/*
                        if (e.getButton() == MouseEvent.BUTTON1 && isAdmin == true) {
                            box.setOpaque(true);
                            box.setIcon(new ImageIcon("wall.png"));
                            box.repaint();
                            repaint();
                            validate();
                        }
                        else if (e.getButton() == MouseEvent.BUTTON3 && isAdmin == true) {
                            box.setOpaque(true);
                            box.setIcon(new ImageIcon("door.png"));
                            box.repaint();
                            repaint();
                            validate();
                        }
                        */
                    	
                    	if(m.cellsBeingProcessed[x][y].getRepresentation() != 1) {  
                    		m.cellsBeingProcessed[x][y].setRepresentation(1);                    	
	                    	box.setBackground(Color.CYAN);
	                    	box.setForeground(Color.CYAN);
	                    	box.setOpaque(true);
                            box.setIcon(new ImageIcon("door.png"));
	                        box.repaint();
	                        repaint();
	                        validate();
	                        //add cell to selected array
	                       	m.addSelected(new Cell(x, y));
	                        System.out.println(x + ", " + y);
	                        m.addMove(m.getCellsBeingProcessed());
                    	}
                    	
                    	else {                    		
                    		System.out.println(processCells[x][y].getRepresentation());
	                    	box.setOpaque(false);
	                        box.setBackground(Color.white);
	                        box.setForeground(Color.white);
	                        box.repaint();
	                        repaint();
	                        validate();
	                        //remove cell from selected array
	                        for(int i = 0; i < m.selected.size(); i++)
	                        	if(m.selected.get(i).getRowNum() == x && m.selected.get(i).getColNum() == y)
	                        		m.removeCell(m.selected.get(i), i);
	                        System.out.println("Removed " + x + ", " + y);
	                        m.addMove(m.getCellsBeingProcessed());
                    	}                    	
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                    	
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                });
            }

        }
        mapIsCreated = true;
        refreshUI(mapPanel);
    }

    /**
     * Loads a map layout from a text file and applies it to the current map.
     */
	public void loadLayout () {
		//load layout from file

        // we can apply code from arrayToGrid here
        try {
            br = new BufferedReader(new FileReader("filename"));



            br.close();
        }
        catch (FileNotFoundException ex) {

        }
        catch (IOException ex) {

        }

		refreshUI(mapPanel);
	}

    /**
     * Instantiates the passablePanel JPanel and relevant components. Allows the user to set a block in the map
     * as passable/unpassable.
     */
	public void passableMenu () {
        remove(controlPanel);
        remove(rowColPanel);
        remove(controlInit);

		passablePanel = new JPanel(new FlowLayout());
        passablePanel.setSize(300,1000);
        passablePanel.setLocation(800, 200);
        ppPassable = new JButton("Set Passable/Unpassable");
        ppClose = new JButton("Back");
        ppPassable.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        ppClose.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        
        passablePanel.setLayout(new BoxLayout(passablePanel, BoxLayout.Y_AXIS));

        passablePanel.add(ppPassable);
        passablePanel.add(ppNotPassable);
        passablePanel.add(ppClose);

        refreshUI(passablePanel);

        //JButton Listeners
        {
            //Set Block as Passable
            ppPassable.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	m.setUnpassableOrPassable(selected);
                	createGrid(m.getCellsBeingProcessed());
                	refreshUI(mapPanel);
                }
            });

            //Close passable menu button
            ppClose.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    remove(passablePanel);

                    refreshUI(rowColPanel);
                    refreshUI(controlInit);
                    refreshUI(controlPanel);
                }
            });
        }

	}


    /**
     *
     * @param store
     */
    public void arrayToGrid(Cell[][] store) {
        int row = store.length;
        int col = store[0].length;

        store[0][0].setRepresentation(2); // Representation set to HIGHLIGHTED
        store[0][0].setColNum(2); // Col 3
        store[0][0].setRowNum(2); // Row 3

        JPanel newPanel = new JPanel(new GridLayout(row, col, -1, -1));
        newPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        newPanel.setSize(800, 670);
        newPanel.setLocation(0, 30);
        newPanel.setOpaque(false);

        //Make a new Grid
        int cRow = 0, cCol = 0;
        for (int i = 0; i < store[0].length * store.length; i++) {
            JLabel box = new JLabel(cRow + "," + cCol);

            if (cRow == store[0][0].getRowNum() && cCol == store[0][0].getColNum())
                if (store[0][0].getRepresentation() == 1) { // 1 = HIGHLIGHT
                    box.setBackground(Color.cyan);
                    box.setOpaque(true);
                    processCells[cRow][cCol] = new Cell(cRow, cCol, 1);
                }
                else if (store[0][0].getRepresentation() == 2) { // 2 = STORE
                    box.setBackground(Color.BLACK);
                    box.setOpaque(true);
                    processCells[cRow][cCol] = new Cell(cRow, cCol, 1);
                }
                else if (store[0][0].getRepresentation() == 3) { // 3 = DOOR
                    box.setBackground(Color.BLUE);
                    box.setOpaque(true);
                    processCells[cRow][cCol] = new Cell(cRow, cCol, 1);
                }
                else
                    processCells[cRow][cCol] = new Cell(cRow, cCol);

            box.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            box.setIcon(new ImageIcon("//Users//Admin//IdeaProjects//mpMallKiosk//mpMallKiosk//src//mpMallKiosk//floor-tile.png"));
            newPanel.add(box);
            cCol++;
            if (cCol == col) {
                cRow++;
                cCol = 0;
            }
        }
        refreshUI(newPanel);
    }

	
	//events
	@Override
	public void mouseDragged(MouseEvent e) {
		//check if mouse in grid
		
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}
		

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		//if inside grid 
		//for loop to update every cell clicked
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

    /**
     * Saves the current map's layout to a text file.
     * @param cells
     */
	public void updateLayout (Cell[][] cells) {
		 //layout to file	
	}
	

	
	
}
