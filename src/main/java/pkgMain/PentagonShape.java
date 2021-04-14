package pkgMain;


/**
 * PentagonShape class.
 * Models the shape of a pentagon
 * 
 * @author Aidan Chao
 * @author Nicholas Cutrona
 * @author Caleb Davis
 * @author Joey Loporto
 * @author Tommy Cheung
 */
public class PentagonShape {
	
	Coordinates top;
	Coordinates left;
	Coordinates leftdown;
	Coordinates right;
	Coordinates rightdown;
	
	
	/**
	 * Constructor for new PentagonShape object, takes height and width.
	 * 
	 * @param height int height of pentagon
	 * @param width int width of pentagon
	 */
	public PentagonShape(int height, int width) {
		top = new Coordinates(0,0);
		left = new Coordinates(0,0);
		leftdown = new Coordinates(0,0);
		right = new Coordinates(0,0);
		rightdown = new Coordinates(0,0);
	}
	
	
	/**
	 * Returns the coordinates of the top point
	 * 
	 * @return Coordinates of top point
	 */
	public Coordinates getTop() {
		return this.top;
	}
	
	
	/**
	 * Returns the coordinates of the left point
	 * 
	 * @return Coordinates of left point
	 */
	public Coordinates getLeft() {
		return this.left;
	}
	
	
	/**
	 * Returns the coordinates of the left-down point
	 * 
	 * @return Coordinates of left-down point
	 */
	public Coordinates getLeftdown() {
		return this.leftdown;
	}
	
	
	/**
	 * Returns the coordinates of the right point
	 * 
	 * @return Coordinates of right point
	 */
	public Coordinates getRight() {
		return this.right;
	}
	
	
	/**
	 * Returns the coordinates of the right-down point
	 * 
	 * @return Coordinates of right-down point
	 */
	public Coordinates getRightdown() {
		return this.rightdown;
	}

	
	/**
	 * Sets the coordinates of the top point
	 * 
	 * @param top Coordinates of top point
	 */
	public void setTop(Coordinates top) {
		this.top = top;
	}

	
	/**
	 * Sets the coordinates of the left point
	 * 
	 * @param left Coordinates of left point
	 */
	public void setLeft(Coordinates left) {
		this.left = left;
	}

	
	/**
	 * Sets the coordinates of the left-down point
	 * 
	 * @param leftdown Coordinates of left-down point
	 */
	public void setLeftdown(Coordinates leftdown) {
		this.leftdown = leftdown;
	}

	
	/**
	 * Sets the coordinates of the right point
	 * 
	 * @param right Coordinates of right point
	 */
	public void setRight(Coordinates right) {
		this.right = right;
	}

	
	/**
	 * Sets the coordinates of the right-down point
	 * 
	 * @param rightdown Coordinates of right-down point
	 */
	public void setRightdown(Coordinates rightdown) {
		this.rightdown = rightdown;
	}

}
