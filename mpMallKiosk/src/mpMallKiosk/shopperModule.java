package mpMallKiosk;

import java.util.LinkedList;
import java.util.Stack;

public class shopperModule {
	
	protected Cell[][] deployedMap;
	protected Mall m;
	
	public shopperModule (Mall m) {
		this.m = m;
	}
	/*
	public void viewMap () {
		
	}
	
	public String[] alphabeticalStores () {
		
		return 
	}
	
	public String[] classifiedStores () {
		
		return
	}
	
	public String[] searchStores () {
		
		return
	}
	*/
	
	public LinkedList<Cell> findPath (Cell start, Cell destination) {
		LinkedList<Cell> path = new LinkedList<Cell>();
		boolean pathFound = false;
		
		
		if(!pathFound) path.offer(new Cell(-1, -1));
		return path;
	}
	
	public LinkedList<Cell> findMultiplePaths (Cell start, Stack<Cell> destinations) {
		LinkedList<Cell> paths = new LinkedList<Cell>();
		
		
		return paths;
	}
	
	//speacial queries

	/*
	public LinkedList<Cell> nearestGadgetStore (Cell start) {
		//get nearest gadget opening cell
		return findPath(start, );	
	}
	
	public LinkedList<Cell> nearestFoodStore (Cell start) {
		//get nearest food opening cell
		return findPath(start, );	
	}
	
	public LinkedList<Cell> nearestClothingStore (Cell start) {
		//get nearest clothing opening cell
		return findPath(start, );	
	}
	*/
}
