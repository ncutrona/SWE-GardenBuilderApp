	package pkgMain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import pkgMain.Conditions.moistureCondition;
import pkgMain.Conditions.soilCondition;
import pkgMain.Conditions.sunCondition;


/**
 * Plant class.
 * Models a plant
 * 
 * @author Aidan Chao
 * @author Nicholas Cutrona
 * @author Caleb Davis
 * @author Joey Loporto
 * @author Tommy Cheung
 */
public class Plant {

	//plant class
	public int price;
	public int lepsSupported;
	public String scientificName;
	public String soil;
	public String sun;
	public String moisture;
	public int height;
	public int width;
	public int bloomTime;
	public int xCor;
	public int yCor;

	// Remove and add this to Garden class
	static Conditions.sunCondition sunCond = sunCondition.FULL;
	static Conditions.soilCondition soilCond = soilCondition.CLAY;
	static Conditions.moistureCondition moistureCond = moistureCondition.DRY;


	/**
	 * Returns the Scientific name of the plant as a string
	 * 
	 * @return scientific name of plant as a string
	 */
	@Override
	public String toString() {
		return scientificName;
	}
	
	
	/**
	 * Constructor for a new plant object
	 * 
	 * @param price int price of plant
	 * @param lepsSupported int number of leps supported
	 * @param scientificName String scientific name of the plant
	 * @param soil String soil condition
	 * @param sun String sun condition
	 * @param moisture String moisture condition
	 * @param height int height
	 * @param width int width
	 * @param bloomTime int bloom time
	 * @param xCor int X coordinate
	 * @param yCor int Y coordinate
	 */
	public Plant(int price, int lepsSupported, String scientificName, String soil, String sun, String moisture, int height, int width, int bloomTime, int xCor, int yCor) {
		this.price = price;
		this.lepsSupported = lepsSupported;
		this.scientificName = scientificName;
		this.soil = soil;
		this.sun = sun;
		this.moisture = moisture;
		this.height = height;
		this.width = width;
		this.bloomTime = bloomTime;
		this.xCor = xCor;
		this.yCor = yCor;

	}

	/**
	 * Returns the price of the plant
	 * 
	 * @return int price
	 */
	public int getPrice() {
		return price;
	}

	
	/**
	 * Sets the price of the plant
	 * 
	 * @param price int price
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * Returns the number of leps supported
	 * 
	 * @return int # of leps supported
	 */
	public int getLepsSupported() {
		return lepsSupported;
	}

	
	/**
	 * Sets the number of leps supported
	 * 
	 * @param lepsSupported int number of leps supported
	 */
	public void setLepsSupported(int lepsSupported) {
		this.lepsSupported = lepsSupported;
	}

	
	/**
	 * Returns the scientific name of the plant
	 * 
	 * @return String scientific name of the plant
	 */
	public String getScientificName() {
		return scientificName;
	}

	
	/**
	 * Sets the scientific name of the plant
	 * 
	 * @param scientificName String scientific name
	 */
	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}

	
	/**
	 * Returns the soil condition
	 * 
	 * @return String soil condition
	 */
	public String getSoil() {
		return soil;
	}

	
	/**
	 * Sets the soil condition
	 * 
	 * @param soil String soil condition
	 */
	public void setSoil(String soil) {
		this.soil = soil;
	}

	
	/**
	 * Returns the sun condition
	 * 
	 * @return String sun condition
	 */
	public String getSun() {
		return sun;
	}

	
	/**
	 * Sets the sun condition
	 * 
	 * @param sun String sun condition
	 */
	public void setSun(String sun) {
		this.sun = sun;
	}

	
	/**
	 * Returns the moisture condition
	 * 
	 * @return String moisture condition
	 */
	public String getMoisture() {
		return moisture;
	}

	
	/**
	 * Sets the moisture condition
	 * 
	 * @param moisture String moisture condition
	 */
	public void setMoisture(String moisture) {
		this.moisture = moisture;
	}

	
	/**
	 * returns the height of the plant
	 * 
	 * @return int height of plant
	 */
	public int getHeight() {
		return height;
	}

	
	/**
	 * Sets the height of the plant
	 * 
	 * @param height int height of plant
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	
	/**
	 * Returns the width of the plant
	 * 
	 * @return int width of plant
	 */
	public int getWidth() {
		return width;
	}

	
	/**
	 * Sets the width of the plant
	 * 
	 * @param width int width of plant
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	
	/**
	 * Returns the bloom time of the plant
	 * 
	 * @return int bloom time
	 */
	public int getBloomTime() {
		return bloomTime;
	}

	
	/**
	 * Sets the bloom time of the plant
	 * @param bloomTime int bloom time
	 */
	public void setBloomTime(int bloomTime) {
		this.bloomTime = bloomTime;
	}
	
	
	/**
	 * Returns the x coordinate of the plant
	 * 
	 * @return int x coordinate of plant
	 */
	public int getxCor() {
		return xCor;
	}

	
	/**
	 * Sets the x coordinate of the plant
	 * 
	 * @param xCor int x coordinate of plant
	 */
	public void setxCor(int xCor) {
		this.xCor = xCor;
	}
	
	
	/**
	 * Returns the y coordinate of the plant
	 * 
	 * @return int y coordinate of plant
	 */
	public int getyCor() {
		return yCor;
	}

	
	/**
	 * Sets the y coordinate of the plant
	 * 
	 * @param yCor int y coordinate of plant
	 */
	public void setyCor(int yCor) {
		this.yCor = yCor;
	}
	
	
	
	/**
	 * Static mathod that sorts an arraylist of plays and returns the sorted collection.
	 * Uses PlantCompare() to sort.
	 * 
	 * @param plantCollection arraylist of plants to be sorted
	 * @return sorted collection of plants
	 */
	public static Collection sortPlants(ArrayList<Plant> plantCollection) {

		Collections.sort(plantCollection, new PlantCompare());


		System.out.println(plantCollection);
		return plantCollection;

	}

	
	/**
	 * Static method that returns subcollection of plants that fit conditions.
	 * 
	 * @param masterList ArrayList of all plants
	 * @param cond GardenConditions desired conditions
	 * @return ArrayList of plants that meet desired conditions
	 */
	public static ArrayList<Plant> conditionCheckedPlants(ArrayList<Plant> masterList, String sun, String soil, String moisture) {


		ArrayList<Plant> conditionedPlants = new ArrayList<Plant>();


		for(Plant plant : masterList) {

			if(plant.sun.toLowerCase().contains(sun.toLowerCase()) && plant.soil.toLowerCase().contains(soil.toLowerCase()) 
					&& plant.moisture.toLowerCase().contains(moisture.toLowerCase())) {
				conditionedPlants.add(plant);
			}
		}

		//checking enum conditions....

		return conditionedPlants;
	}

}

/**
 * PlantCompare class that implements the Comparator interface.
 * Used to compare plants.
 * 
 * @author Aidan Chao
 * @author Nicholas Cutrona
 * @author Caleb Davis
 * @author Joey Loporto
 * @author Tommy Cheung
 */
class PlantCompare implements Comparator<Plant>{

	
	/**
	 * Compares two plants by scientific name, a1 to a2.
	 * Returns 1 if greater, 0 if equal, and -1 if less then.
	 * 
	 * @return int ordering
	 */
	public int compare(Plant a1, Plant a2){

		int value = a1.lepsSupported - a2.lepsSupported;
		if(value==0){
			value = a1.scientificName.compareToIgnoreCase(a2.scientificName);
			return value;
		}

		return value *= -1;

	}
}
