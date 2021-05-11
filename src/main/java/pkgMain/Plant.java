	package pkgMain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
	private int price, lepsSupported;
	private String scientificName, soil, sun, moisture;
	private int height, width, bloomTime, xCor, yCor;

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
	 * Static method that returns subcollection of plants that fit conditions.
	 * 
	 * @param masterList ArrayList of all plants
	 * @param sun String sun condition
	 * @param soil String soil condition
	 * @param moisture String moisture condition
	 * @return ArrayList of plants that meet desired conditions
	 */
	public static ArrayList<Plant> getConditionCheckedPlants(ArrayList<Plant> masterList, String sun, String soil, String moisture) {


		ArrayList<Plant> conditionedPlants = new ArrayList<Plant>();


		for(Plant plant : masterList) {

			if(plant.sun.toLowerCase().contains(sun.toLowerCase()) && plant.soil.toLowerCase().contains(soil.toLowerCase()) 
					&& plant.moisture.toLowerCase().contains(moisture.toLowerCase())) {
				conditionedPlants.add(plant);
			}
		}

		Collections.sort(conditionedPlants, new PlantCompare());

		return conditionedPlants;
	}
	
	/**
	 * Sorts the masterList in-place via PlantCompare and returns it.
	 * PlantCompare sorts via scientific name.
	 * 
	 * @param masterList ArrayList-Plant- of plants
	 * @return sorted ArrayList-Plant-
	 */
	public static ArrayList<Plant> getLepSupportedPlants(ArrayList<Plant> masterList) {

		Collections.sort(masterList, new PlantCompare());

		return masterList;
	}
	
	/**
	 * returns new LinkedHashMap of plants filtered by soil condition
	 * 
	 * @param masterList ArrayList-Plant- of plants
	 * @param condition soil condition
	 * @return LinkedHashMap-String, Plant- of plants filtered via soil condition
	 */
	public static LinkedHashMap<String, Plant> getSoilSortedPlants(ArrayList<Plant> masterList, String condition) {
		LinkedHashMap<String, Plant> soilSortedPlants = new LinkedHashMap<String, Plant>();
		for (Plant plant : masterList) {
			if (plant.getSoil().toLowerCase().contains(condition)) {
				soilSortedPlants.put(plant.getScientificName(), plant);
			}
		}
		return soilSortedPlants;
	}
	
	/**
	 * returns new LinkedHashMap of plants filtered by sun condition
	 * 
	 * @param masterList ArrayList-Plant- of plants
	 * @param condition sun condition
	 * @return LinkedHashMap-String, Plant- of plants filtered via sun condition
	 */
	public static LinkedHashMap<String, Plant> getSunSortedPlants(ArrayList<Plant> masterList, String condition) {
		LinkedHashMap<String, Plant> sunSortedPlants = new LinkedHashMap<String, Plant>();
		for (Plant plant : masterList) {
			if (plant.getSun().toLowerCase().contains(condition)) {
				sunSortedPlants.put(plant.getScientificName(), plant);
			}
		}
		return sunSortedPlants;
	}
	
	/**
	 * returns new LinkedHashMap of plants filtered by moisture condition
	 * 
	 * @param masterList ArrayList-Plant- of plants
	 * @param condition moisture condition
	 * @return LinkedHashMap-String, Plant- of plants filtered via moisture condition
	 */
	public static LinkedHashMap<String, Plant> getMoistureSortedPlants(ArrayList<Plant> masterList, String condition) {
		LinkedHashMap<String, Plant> moistureSortedPlants = new LinkedHashMap<String, Plant>();
		for (Plant plant : masterList) {
			if (plant.getMoisture().toLowerCase().contains(condition)) {
				moistureSortedPlants.put(plant.getScientificName(), plant);
			}
		}
		return moistureSortedPlants;
	}
	
	/**
	 * Filters and sorts plants for inventory from masterList based on conditions
	 * 
	 * @param masterList ArrayList-Plant- of plants
	 * @param soilCondition soil condition of garden
	 * @param sunCondition sun condition of garden
	 * @param moistureCondition moisture condition of garden
	 * @return LinkedHashMap-String, Plant- of garden-compatible plants filtered by garden conditions and sorted
	 */
	public static LinkedHashMap<String, Plant> sortInvByConditions(ArrayList<Plant> masterList, String soilCondition, String sunCondition, String moistureCondition) {
		LinkedHashMap<String, Plant> conditionSortedPlants = new LinkedHashMap<String, Plant>();
		ArrayList<Plant> soilSortedArray = new ArrayList<Plant>();
		ArrayList<Plant> sunSortedArray = new ArrayList<Plant>();
		conditionSortedPlants = getSoilSortedPlants(masterList, soilCondition);
		for (Map.Entry mapElement : conditionSortedPlants.entrySet()) {
			Plant plant = (Plant)mapElement.getValue();
			soilSortedArray.add(plant);
		}
		conditionSortedPlants = getSunSortedPlants(soilSortedArray, sunCondition);
		for (Map.Entry mapElement : conditionSortedPlants.entrySet()) {
			Plant plant = (Plant)mapElement.getValue();
			sunSortedArray.add(plant);
		}
		conditionSortedPlants = getMoistureSortedPlants(sunSortedArray, moistureCondition);
		return conditionSortedPlants;
	}
 
}

/**
 * PlantCompare class that implements the Comparator interface.
 * Used to sort plants by scientific name.
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

		int value = a1.getLepsSupported() - a2.getLepsSupported();
		if(value==0){
			value = a1.getScientificName().compareToIgnoreCase(a2.getScientificName());
			return value;
		}

		return value *= -1;

	}
}
