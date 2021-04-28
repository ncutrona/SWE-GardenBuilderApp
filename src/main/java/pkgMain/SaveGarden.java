package pkgMain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

/**
 * SaveGarden class, implements Serializable to save a garden to file.
 * 
 * @author Aidan Chao
 * @author Nicholas Cutrona
 * @author Caleb Davis
 * @author Joey Loporto
 * @author Tommy Cheung
 */
public class SaveGarden implements Serializable{
	private int budget, numLepSupported, length, weight;
	private String Name;

	private String sunCondition, moistCondition, soilCondition;
	private HashMap<String, ArrayList<Coordinates>> plants;
	private ArrayList<Double> hexPoints;
	
	
	/**
	 * Constructor for SaveGarden object.
	 * 
	 * @param length length of garden
	 * @param weight weight of garden
	 */
	public SaveGarden(int length, int weight) {
		this.length = length;
		this.weight = weight;
	}

	/**
	 * returns garden budget
	 * 
	 * @return int garden budget
	 */
	public int getBudget() {
		return budget;
	}

	/**
	 * Sets the garden budget
	 * 
	 * @param budget int garden budget
	 */
	public void setBudget(int budget) {
		this.budget = budget;
	}

	/**
	 * Returns the garden name
	 * 
	 * @return String garden name
	 */
	public String getName() {
		return Name;
	}
	
	/**
	 * Sets the hexPoints for the shape of the garden
	 * 
	 * @param hexPoints ObservableList-Double- points for the garden's polygon
	 */
	public void setHexPoints(ObservableList<Double> hexPoints) {
		this.hexPoints = new ArrayList<Double>();
		this.hexPoints.addAll(hexPoints);
	}
	
	/**
	 * Returns the hexPoints of the garden's shape
	 * 
	 * @return ArrayList-Double- points on gardens polygon
	 */
	public ArrayList<Double> getHexPoints() {
		return this.hexPoints;
	}

	/**
	 * Sets the name of the garden
	 * 
	 * @param name String garden name
	 */
	public void setName(String name) {
		Name = name;
	}
	
	/**
	 * Sets the plants in the garden
	 * 
	 * @param plants HashMap-String, ArrayList-Coordinates-- plants to set to garden plants list
	 */
	public void setPlants(HashMap<String, ArrayList<Coordinates>> plants) {
		this.plants = plants;
	}
	
	/**
	 * Returns the plants list from the garden
	 * 
	 * @return HashMap-String, ArrayList-Coordinates-- garden's plants
	 */
	public HashMap<String, ArrayList<Coordinates>> getPlants() {
		return plants;
	}

	/**
	 * Returns the total number of leps supported by the garden.
	 * 
	 * @return int num of leps supported
	 */
	public int getNumLepSupported() {
		return numLepSupported;
	}

	/**
	 * Sets the number of leps supported 
	 * 
	 * @param numLepSupported int total number of leps supported
	 */
	public void setNumLepSupported(int numLepSupported) {
		this.numLepSupported = numLepSupported;
	}
	
	/**
	 * Return the sun condition 
	 * 
	 * @return String sunCondition
	 */
	public String getSunCondition() {
		return sunCondition;
	}
	
	/**
	 * Return the moisture condition 
	 * 
	 * @return String moistureCondition
	 */
	public String getMoistCondition() {
		return moistCondition;
	}
	
	/**
	 * Return the soil condition 
	 * 
	 * @return String soilCondition
	 */
	public String getSoilCondition() {
		return soilCondition;
	}
	
	
	/**
	 * Sets the conditions of the garden
	 * 
	 * @param soil String soilCondition
	 * @param sun String sunCondition
	 * @param moist String moistureCondition
	 */
	public void setConditions(String soil, String sun, String moist) {
		this.soilCondition = soil;
		this.sunCondition = sun;
		this.moistCondition = moist;
	}
	
	/**
	 * returns the length of the garden
	 * 
	 * @return int garden length
	 */
	public int getLength() {
		return this.length;
	}
	
	/**
	 * Returns the width of the garden
	 * 
	 * @return int garden width
	 */
	public int getWidth() {
		return this.weight;
	}
	
}