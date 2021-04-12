package pkgMain;


/**
 * Coordinates class, usable x,y coordinates for saving location
 * 
 * @author Aidan Chao
 * @author Nicholas Cutrona
 * @author Caleb Davis
 * @author Joey Loporto
 * @author Tommy Cheung
 */
class Coordinates{
	double x, y;
	
	
	/**
	 * Constructor for Coordinates object,
	 * Sets x and y from passed in doubles
	 * 
	 * @param x double x position
	 * @param y double y position
	 */
	public Coordinates(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	
	/**
	 * Returns the current x coordinate
	 * 
	 * @return double x position
	 */
	public double getX() {
		return this.x;
	}
	
	
	/**
	 * Returns the current y coordinate
	 * 
	 * @return double y position
	 */
	public double getY() {
		return this.y;
	}
}