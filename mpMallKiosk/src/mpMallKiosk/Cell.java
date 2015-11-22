package mpMallKiosk;

public class Cell {
	
	/* 1 highlighted
	 * 2 store
	 * 3 door 
	 * 0 passable
	 * 5 unpassable
	 */
	
	
	protected boolean isPassable;
	protected int representation, hold;
		
	public int getHold() {
		return hold;
	}

	public void setHold(int hold) {
		this.hold = hold;
	}

	protected int rowNum, colNum;
	
	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public int getColNum() {
		return colNum;
	}

	public void setColNum(int colNum) {
		this.colNum = colNum;
	}
	
	public Cell (int x, int y) {
		setRepresentation(4);
		setRowNum(x);
		setColNum(y);
	}

	public Cell (int x, int y, int represent) {
		setRowNum(x);
		setColNum(y);
		representation = represent;
		
	}

	public boolean isPassable() {
		return isPassable;
	}

	public void setPassable(boolean isPassable) {
		this.isPassable = isPassable;
	}
	
	public int getRepresentation () {
		return representation;
	}
	
	public void setRepresentation (int represent) {
		representation = represent;
	}
}
