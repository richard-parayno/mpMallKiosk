package mpMallKiosk;

public class Cell {

	protected boolean isPassable,
		isBorder,
		isKiosk = false;
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

	public boolean isKiosk() {
		return isKiosk;
	}

	public void setKiosk(boolean isKiosk) {
		this.isKiosk = isKiosk;
	}

	public Cell (int x, int y) {
		isPassable = true;
	}
	
	public boolean isPassable() {
		return isPassable;
	}
	public boolean isBorder() {
		return isBorder;
	}

	public void setBorder(boolean isBorder) {
		this.isBorder = isBorder;
	}

	public void setPassable(boolean isPassable) {
		this.isPassable = isPassable;
	}
}
