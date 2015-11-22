package mpMallKiosk;

import java.util.ArrayList;

public class Store {
	
	protected String name;
	protected boolean isServiceStore = false;
	protected ArrayList<Cell> cellsOfStore = new ArrayList<Cell>();
	
	public Store (String name, ArrayList<Cell> cellsOfStore) {
		this.name = name;
	}
	
	public String getName () {
		return name;
	}

	public ArrayList<Cell> getCellsOfStore () {
		return cellsOfStore;
	}
}
