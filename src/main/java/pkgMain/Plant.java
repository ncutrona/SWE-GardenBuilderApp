package pkgMain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import pkgMain.Conditions.moistureCondition;
import pkgMain.Conditions.soilCondition;
import pkgMain.Conditions.sunCondition;

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

	// Remove and add this to Garden class
	static Conditions.sunCondition sunCond = sunCondition.FULL;
	static Conditions.soilCondition soilCond = soilCondition.CLAY;
	static Conditions.moistureCondition moistureCond = moistureCondition.DRY;


	public String toString() {
		return scientificName;
	}
	
	public Plant(int price, int lepsSupported, String scientificName, String soil, String sun, String moisture, int height, int width, int bloomTime) {
		this.price = price;
		this.lepsSupported = lepsSupported;
		this.scientificName = scientificName;
		this.soil = soil;
		this.sun = sun;
		this.moisture = moisture;
		this.height = height;
		this.width = width;
		this.bloomTime = bloomTime;

	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getLepsSupported() {
		return lepsSupported;
	}

	public void setLepsSupported(int lepsSupported) {
		this.lepsSupported = lepsSupported;
	}

	public String getScientificName() {
		return scientificName;
	}

	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}

	public String getSoil() {
		return soil;
	}

	public void setSoil(String soil) {
		this.soil = soil;
	}

	public String getSun() {
		return sun;
	}

	public void setSun(String sun) {
		this.sun = sun;
	}

	public String getMoisture() {
		return moisture;
	}

	public void setMoisture(String moisture) {
		this.moisture = moisture;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getBloomTime() {
		return bloomTime;
	}

	public void setBloomTime(int bloomTime) {
		this.bloomTime = bloomTime;
	}

	public static Collection sortPlants(ArrayList<Plant> plantCollection) {

		Collections.sort(plantCollection, new PlantCompare());


		System.out.println(plantCollection);
		return plantCollection;

	}

	public static ArrayList<Plant> conditionCheckedPlants(ArrayList<Plant> masterList, GardenConditions cond) {


		ArrayList<Plant> conditionedPlants = new ArrayList<Plant>();


		for(Plant plant : masterList) {

			if(plant.soil == cond.getSoil() && plant.sun == cond.getSun() && plant.moisture == cond.getMoisture()) {
				conditionedPlants.add(plant);
			}
		}

		//checking enum conditions....

		return conditionedPlants;
	}

}

class PlantCompare implements Comparator<Plant>{

	public int compare(Plant a1, Plant a2){

		int value = a1.lepsSupported - a2.lepsSupported;
		if(value==0){
			value = a1.scientificName.compareToIgnoreCase(a2.scientificName);
			return value;
		}

		return value *= -1;

	}
}
