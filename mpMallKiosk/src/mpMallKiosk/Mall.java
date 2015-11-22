package mpMallKiosk;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class Mall {

	protected ArrayList<Store> stores = new ArrayList<Store>();
	protected Cell[][] layout;
	protected String name;
	protected boolean layoutDeployed = false;
	protected Cell kioskLocation;
	protected Cell[][] cellsBeingProcessed;
	protected int length, width;
	protected boolean isDeployed = false;
	private Stack<Cell[][]> moves = new Stack<Cell[][]>();
    private LinkedList<Cell[][]> moveHold = new LinkedList<Cell[][]>();
    protected ArrayList<Cell> selected = new ArrayList<Cell>();
	
	public ArrayList<Cell> getSelected() {
		return selected;
	}

	public void addSelected(Cell highlighted) {
		selected.add(highlighted);
		cellsBeingProcessed[highlighted.getRowNum()][highlighted.getColNum()].setRepresentation(1);
	}

	public Mall (String name) {
		this.name = name;
	}
	
	public void removeCell (Cell cell, int index) {
		selected.remove(index);
		cellsBeingProcessed[cell.getRowNum()][cell.getColNum()].setRepresentation(4);
	}

	public void addStore (Store store) {
		stores.add(store);
		//change icon or color of the cell representation in cellsBeingProcessed of the cells in store
	}
	
	public void setSize(int length, int width) {
		cellsBeingProcessed = new Cell[length][width];
		for(int i = 0; i < length; i++)
    		for(int j = 0; j < width; j++)
    			cellsBeingProcessed[i][j] = new Cell(i, j);
		if(moveHold.size() == 1) {
        	moves.push(moveHold.poll());
        }
        moveHold.offer(cellsBeingProcessed);
	}
	
	public void setUnpassableOrPassable(ArrayList<Cell> cells) {
		for(int i = 0; i <= cells.size(); i++) {
			int x = cells.get(i).getRowNum();
			int y = cells.get(i).getColNum();
			if(cellsBeingProcessed[x][y].getRepresentation() == 3)
				cellsBeingProcessed[x][y].setRepresentation(0);
			else if(cellsBeingProcessed[x][y].getRepresentation() == 0)
				cellsBeingProcessed[x][y].setRepresentation(3);	
		}
	}
	
	public void removeStore (String name) {
		for(int i = 0; i < stores.size(); i++) 
			if(stores.get(i).getName().equalsIgnoreCase(name)){
				for(int j = 0; j <= stores.size(); j++) {
					int row = stores.get(i).getCellsOfStore().get(j).getRowNum();
					int col = stores.get(i).getCellsOfStore().get(j).getColNum();
					cellsBeingProcessed[row][col].setRepresentation(0);
				}
				stores.remove(i);
			}
	}
	public void undo () {
		
	}
	
	public void saveAndQuit () {
		
	}
	
	private void loadFromFile () {
		
	}
	
	private void deploy () {
		
	}
	
	private boolean isDeployed () {
		return isDeployed;
	}
	
	//getters/setters
	
	public ArrayList<Store> getStores() {
		return stores;
	}

	public void setStores(ArrayList<Store> stores) {
		this.stores = stores;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Cell getKioskLocation() {
		return kioskLocation;
	}

	public void setKioskLocation(Cell kioskLocation) {
		this.kioskLocation = kioskLocation;
	}

	public Cell[][] getCellsBeingProcessed() {
		return cellsBeingProcessed;
	}

	public void setCellsBeingProcessed(Cell[][] cellsBeingProcessed) {
		this.cellsBeingProcessed = cellsBeingProcessed;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	

	public Stack<Cell[][]> getMoves() {
		return moves;
	}
	
	public void addMove (Cell[][] cell) {
		moves.push(cell);
	}


	public void setDeployed(boolean isDeployed) {
		this.isDeployed = isDeployed;
	}

	
}
