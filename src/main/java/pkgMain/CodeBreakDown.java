package pkgMain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import pkgMain.Conditions.moistureCondition;
import pkgMain.Conditions.soilCondition;
import pkgMain.Conditions.sunCondition;

//THIS FILE IS NOT MVC - WE ARE GETTING EACH FUNCTION OF OUR PROJECT WORKING TOGETHER BEFORE IMPLEMENTING MVC

public class CodeBreakDown {

	//Model Functionality - Sorting plants based on leps supported and 

	public static void main(String args[]) {
		
		
		
		//Building the Plant collection
		CodeBreakDown demoPlantOne = new CodeBreakDown(6, 1, "Acer negudo", "clay", "full", "dry", 100, 100, 1);
		CodeBreakDown demoPlantTwo = new CodeBreakDown(6, 4, "Betula lenta", "clay", "full", "dry", 100, 100, 1);
		CodeBreakDown demoPlantThree = new CodeBreakDown(6, 2, "Carya glabra", "loam", "full", "dry", 100, 100, 1);
		CodeBreakDown demoPlantFour = new CodeBreakDown(6, 4, "TEST negudo", "clay", "full", "dry", 100, 100, 1);
		
		ArrayList<CodeBreakDown> masterPlantCollection = new ArrayList<CodeBreakDown>();
		masterPlantCollection.add(demoPlantOne);
		masterPlantCollection.add(demoPlantTwo);
		masterPlantCollection.add(demoPlantThree);
		masterPlantCollection.add(demoPlantFour);
		
		System.out.println("Plant Sorting Testing - Results");
		
		ArrayList<CodeBreakDown> conditionedPlantCheck = conditionCheckedPlants(masterPlantCollection);
		
		
		sortPlants(conditionedPlantCheck);
	
	}
	
	
	public String toString() {
		return scientificName;
	}
	
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
	
	static Conditions.sunCondition sunCond = sunCondition.FULL;
	static Conditions.soilCondition soilCond = soilCondition.CLAY;
	static Conditions.moistureCondition moistureCond = moistureCondition.DRY;
	
	
	
	
	public CodeBreakDown(int price, int lepsSupported, String scientificName, String soil, String sun, String moisture, int height, int width, int bloomTime) {
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
		
	public static Collection sortPlants(ArrayList<CodeBreakDown> plantCollection) {
			
		Collections.sort(plantCollection, new PlantCompare());
			
			
		System.out.println(plantCollection);
		return plantCollection;
			
	}
		
	
	
	public static ArrayList<CodeBreakDown> conditionCheckedPlants(ArrayList<CodeBreakDown> masterList) {
			
			
		ArrayList<CodeBreakDown> conditionedPlants = new ArrayList<CodeBreakDown>();
			
			
		for(CodeBreakDown plant : masterList) {
				
			if(plant.soil == soilCond.getName() && plant.sun == sunCond.getName() && plant.moisture == moistureCond.getName()) {
				conditionedPlants.add(plant);
			}
		}
			
			//checking enum conditions....
			
		return conditionedPlants;
	}
					
}




class PlantCompare implements Comparator<CodeBreakDown>{
	
	public int compare(CodeBreakDown a1, CodeBreakDown a2){
		
		int value = a1.lepsSupported - a2.lepsSupported;
		if(value==0){
			value = a1.scientificName.compareToIgnoreCase(a2.scientificName);
			return value;
		}
		
		return value *= -1;
		
		}
	}

