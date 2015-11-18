package mpMallKiosk;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Created by Richard Parayno on 28/10/2015.
 */

public class MainWindow extends JFrame implements MouseListener, MouseMotionListener {

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
    
    //mouseListener components
    private int mouseX;
    private int mouseY;

    //AccessMenu Components
    private JPanel accessPanel;
    private JTextField password;
    private JButton enter;
    
    //Logic Components
    private Layout layout;
    private Cell[][] processCells, selectedCells;
    private LinkedList<Integer> cellsToProcessX = new LinkedList<Integer>(), 
    			cellsToProcessY = new LinkedList<Integer>();
    private Stack<Cell[][]> moves = new Stack<Cell[][]>();
    //private Stack<JPanel> guiMoves = new Stack<Jpanel>();
    private int row, col;

    private boolean isDeployed = false; //initialized to false. redundant lines. change to true when deploy button is pressed

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
        
        //showIntro();
    }

    public void showGreetingMenu() {
        //Initialize Buttons
        enterAdmin = new JButton("Admin Menu");
        enterShopper = new JButton("Shopper Menu");

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
        ciEdit = new JButton("Edit Mall Layout");
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
                    if (Integer.parseInt(xCol.getText()) >= 3 && Integer.parseInt(yRow.getText()) >= 3){
                        //Creates the panel for the Map
                    	row = Integer.parseInt(yRow.getText());
                    	col = Integer.parseInt(xCol.getText());
                    	processCells = new Cell[row][col];
                        mapPanel = new JPanel(new GridLayout(col, row, -1, -1));
                        mapPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
                        mapPanel.setSize(800, 670);
                        System.out.println("Grid with " + col + " columns and " + row + " rows created.");
                        //Creates the boxes
                        int currentRow = 0, currentCol = 0;
                        for (int i = 0; i < col*row; i++) {
                        	if(currentCol == col) {
                        		currentRow++;
                        		currentCol = 0;
                        	}
                        	JLabel box = new JLabel(currentRow + ", " + currentCol);
                        	//processCells[currentRow][currentCol].setRowNum(currentRow);
                        	//processCells[currentRow][currentCol].setColNum(currentCol);
	                        box.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	                        mapPanel.add(box);
	                        currentCol++;
                        }                       
                       processCells = new Cell[col][row];
                       refreshUI(mapPanel);
                       moves.push(processCells);
                       updateLayout(processCells);
                    }
                    else System.out.println("Error");
                }
            });
            // Cell Pass/Unpass -- Not Done
            cpCellPass.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	
                	//three buttons, passable, unpassable in a separate jpanel when cpCellPass is clicked,
                	//last is to go back to controlPanel 
                	//passableMenu();
                
                	moves.push(processCells);
                	updateLayout(processCells);
                	refreshUI(mapPanel);
                	
                	System.out.println("Cells unpassable");
                }
            });
            // Store Add -- Not Done
            cpStoreAdd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	Cell[][] tempo; //temp
					//check if highlighted cells meet store reqs + error message if needed <-- ako dito
                	addStore(tempo = new Cell[9][9]);
                	moves.push(processCells);
                	updateLayout(processCells);
                	refreshUI(mapPanel);
                	System.out.println("Added x store");
                }
            });
            // Store Remove -- Not Done
            cpStoreRemove.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	
                	//dropdown list, ako na bahala sa elements
                	//remove button, back to controlPanel button
                	//removeStoreMenu();
                
                	//remove array of cells of a store
                	moves.push(processCells);
                	updateLayout(processCells);
                    refreshUI(mapPanel);
                    System.out.println("Removed x store");
                }
            });
            // Clear Button -- Not Done
            cpClear.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	//int indexX, indexY;
                	//for(int i = 0; i < col; i++) {
                		             		
                	//}
                	//restoreCellsToDefault(processCells, x, y);
                	//pass highlighted cells
                	moves.push(processCells);
                	updateLayout(processCells);
                	refreshUI(mapPanel);
                	System.out.println("Clearing grid...");
                }
            });
            // Undo Button -- Not Done
            cpUndo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //undo last move
                	updateLayout(processCells);
                	refreshUI(mapPanel);
                	System.out.println("Undo");
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
                    remove(mapPanel);
                    remove(rowColPanel);
                    remove(controlPanel);
                    remove(controlInit);
                    showGreetingMenu();
                    validate();
                    repaint();
                     
                }
            });
        }
    }

    // This method adds the panel and refreshes the UI.
    public void refreshUI(JPanel panel) {
        add(panel);
        validate();
        repaint();
    }

	public void restoreCellsToDefault(Cell[][] cells, LinkedList<Integer> x, LinkedList<Integer> y) {
		int size = x.size();
		for(int i = 0; i < size; i++ ) {
			cells[y.poll()][x.poll()].setPassable(true);
		}
	}
	
	public void addStore (Cell[][] store) { //pota richard pakiayos di ko maayos eh
		JPanel storeOp = new JPanel(new FlowLayout());
		JTextField fname = new JTextField("                ");
		JLabel lname = new JLabel("Name of store: ");
		//dropdown list of type of store
		JLabel ltype = new JLabel("Type of store: ");
		JButton b1 = new JButton ("Enter");
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//field checkers and error messages
				//if all correct, initialize store object and add store to array of stores
				//show confirmation message
				//return to main panel 
				refreshUI(mapPanel);
			}
		});
		storeOp.add(lname);
		storeOp.add(fname);
		storeOp.add(ltype);
		storeOp.add(b1);
		storeOp.setSize(300,1000);
        storeOp.setLocation(780, 200);
        storeOp.setLayout(new BoxLayout(storeOp, BoxLayout.Y_AXIS));
		controlPanel.setVisible(false);
		add(storeOp);
		storeOp.setVisible(true);
		refreshUI(storeOp);
	}
	
	public void drawBlock (Graphics g, JPanel panel, int row, int col) {
		
		
		refreshUI(panel);
	}

	public void loadLayout () {
		//load layout from file
		refreshUI(mapPanel);
	}
	
	//skeleton below. ako na bahala sa logic basta need buttons with listener skeletons ty mwa 
	
	public void passableMenu () {
		
	}
	
	public void removeStoreMenu () {
		
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
	
	public void updateLayout (Cell[][] cells) {
		 //layout to file	
	}
	

	
	
}
