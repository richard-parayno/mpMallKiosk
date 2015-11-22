package mpMallKiosk;

import java.util.ArrayList;

public class Foodstore extends Store {
	
	protected String type,
		specialty;

	public Foodstore (String name, ArrayList<Cell> cellsOfStore, Object type, String specialty) {
		super(name, cellsOfStore);
		this.type = (String) type;
		this.specialty = specialty;
	}
	
}
