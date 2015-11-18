package mpMallKiosk;

import java.util.ArrayList;
import java.util.LinkedList;

public class Path {

	protected LinkedList<Cell> shortestPath = new LinkedList<Cell>();
	protected int startX, startY;
	protected Layout mallLayout;

	public Path (Layout layout, int startX, int startY) {
		layout.setStartingX(startX);
		layout.setStartingY(startY);
		this.startX = startX;
		this.startY = startY;
		mallLayout = layout;
	}
	
	public void getShortestPath (Cell destination) {
		ArrayList<Cell> possiblePaths = new ArrayList<Cell>();
		
		int lastXCell = destination.getX(),
				lastYCell = destination.getY(),
				ctrX = lastXCell,
				ctrY = lastYCell;
		
		possiblePaths.add(new Cell(startX, startY));
		
		while(ctrX != lastXCell && ctrY != lastYCell){
			
		}
	}
	
}
