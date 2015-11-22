package mpMallKiosk;

import java.util.ArrayList;

public class Hardware extends Store {
	
	protected ArrayList<String> sells;
	
	public Hardware (String name, ArrayList<Cell> cellsOfStore, ArrayList<String> sells) {
		super(name, cellsOfStore);
		this.sells = sells;
	}

}
