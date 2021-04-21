package pkgMain;

import java.util.ArrayList;


/**
 * GardenState class.
 * Holds the state of a garden.
 * Used to save/load gardens.
 * 
 * @author Aidan Chao
 * @author Nicholas Cutrona
 * @author Caleb Davis
 * @author Joey Loporto
 * @author Tommy Cheung
 */
public class GardenState {

	public int gardenBudget;
	public String GardenName;
	public String lastSaved;
	public int totalLepsSupported;
	public boolean isFull = false;
	//PentagonShape gardenShape;
	
	
	/**
	 * Constructor for GardenState object. Creates instance of GardenState.
	 * Sets state of the instance based on parameters.
	 * 
	 * @param GardenName String name of the garden
	 * @param lastSaved String when the garden was last saved
	 * @param totalLepsSupported int total number of leps supported
	 * @param isFull boolean if the garden is full
	 * @param gardenBudget int the budget of the garden
	 */
	public GardenState(String GardenName, String lastSaved, int totalLepsSupported, boolean isFull, int gardenBudget /*PentagonShape gardenShape*/) {
		this.GardenName = GardenName;
		this.lastSaved = lastSaved;
		this.totalLepsSupported = totalLepsSupported;
		this.isFull = isFull;
		//this.gardenBudget = gardenBudget;
		setGardenBudget(gardenBudget);

		//this.gardenShape = gardenShape;
	}

	
	/**
	 * Returns the name of the garden
	 * 
	 * @return String the name of the garden
	 */
	public String getGardenName() {
		return GardenName;
	}

	
	/**
	 * Sets the garden name 
	 * 
	 * @param gardenName String new name of the garden
	 */
	public void setGardenName(String gardenName) {
		GardenName = gardenName;
	}

	
	/**
	 * Returns the time last saved as a string
	 * 
	 * @return String time last saved
	 */
	public String getLastSaved() {
		return lastSaved;
	}

	
	/**
	 * Sets the time last saved from passed in String
	 * 
	 * @param lastSaved String time last saved
	 */
	public void setLastSaved(String lastSaved) {
		this.lastSaved = lastSaved;
	}

	/**
	 * Returns the total number of leps supported by the garden
	 * 
	 * @return int total leps supported
	 */
	public int getTotalLepsSupported() {
		return totalLepsSupported;
	}

	
	/**
	 * Sets the total number of leps supported by the garden
	 * 
	 * @param totalLepsSupported int number of leps supported
	 */
	public void setTotalLepsSupported(int totalLepsSupported) {
		this.totalLepsSupported = totalLepsSupported;
	}

	
	/**
	 * Returns true if the garden is full
	 * 
	 * @return boolean if the garden is full
	 */
	public boolean isFull() {
		return isFull;
	}

	
	/**
	 * Marks the garden as full or not full
	 * 
	 * @param isFull boolean if the garden is full
	 */
	public void setFull(boolean isFull) {
		this.isFull = isFull;
	}
	
	
	/**
	 * Returns the garden budget
	 * 
	 * @return int the garden budget
	 */
	public int getGardenBudget() {
		return gardenBudget;
	}

	/**
	 * Sets the garden budget
	 * 
	 * @param gardenBudget int the garden budget
	 */
	public void setGardenBudget(int gardenBudget) {
		this.gardenBudget = gardenBudget;
	}

	/*public PentagonShape getGardenShape() {
		return gardenShape;
	}

	public void setGardenShape(PentagonShape gardenShape) {
		this.gardenShape = gardenShape;
	}*/
	
}
