package mpMallKiosk;

import java.util.ArrayList;

public class Gadget extends Store {
	
	protected ArrayList<String> worksWith;
	protected String services;
	
	public Gadget (String name, ArrayList<Cell> cellsOfStore, ArrayList<String> worksWith, Object services) {
		super(name, cellsOfStore);
		this.worksWith = worksWith;
		this.services = (String) services;
	}

}
