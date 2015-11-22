package mpMallKiosk;

import java.util.ArrayList;

public class Clothing extends Store {
	
	protected String catersTo;
	
	public Clothing (String name, ArrayList<Cell> cellsOfStore, Object catersTo){
		super(name, cellsOfStore);
		this.catersTo = (String) catersTo;
	}

}
