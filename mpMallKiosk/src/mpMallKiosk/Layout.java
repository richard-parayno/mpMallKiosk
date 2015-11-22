package mpMallKiosk;

public class Layout {

	protected Cell[][] cells;
	protected int rows,
		cols;
	protected int startingX,
		startingY;
	
	public Layout (int x, int y) {
		cells = new Cell[x][y];
	}
	
	public Cell[][] getCells() {
		return cells;
	}
	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}
	public int getStartingX() {
		return startingX;
	}
	public void setStartingX(int startingX) {
		this.startingX = startingX;
	}
	public int getStartingY() {
		return startingY;
	}
	public void setStartingY(int startingY) {
		this.startingY = startingY;
	}
	public void setRowAndCol (int x, int y) {
		cells = new Cell[x][y];
	}
	public void setRow (int x) {
		cells = new Cell[x][cols];
	}
	public void setCol (int y) {
		cells = new Cell[rows][y];
	}
	
	
}
