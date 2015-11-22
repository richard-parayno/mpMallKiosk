package mpMallKiosk;

import javax.swing.JLabel;

public class MyLabel extends JLabel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int x, y;
	
	public MyLabel (String message, int x, int y) {
		super(message);
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
