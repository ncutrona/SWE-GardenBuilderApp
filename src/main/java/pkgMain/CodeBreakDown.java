package pkgMain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

//THIS FILE IS NOT MVC - WE ARE GETTING EACH FUNCTION OF OUR PROJECT WORKING TOGETHER BEFORE IMPLEMENTING MVC

public class CodeBreakDown {

	//Model Functionality - Sorting plants based on leps supported and 

	public static void main(String args[]) {
		
		
		
		//Building the Plant collection
		CodeBreakDown demoPlantOne = new CodeBreakDown(6, 1, "Acer negudo", "clay", "full", "dry", 100, 100, 1);
		CodeBreakDown demoPlantTwo = new CodeBreakDown(6, 4, "Betula lenta", "clay", "full", "dry", 100, 100, 1);
		CodeBreakDown demoPlantThree = new CodeBreakDown(6, 2, "Carya glabra", "clay", "full", "dry", 100, 100, 1);
		
		ArrayList plantCollection = new ArrayList<CodeBreakDown>();
		plantCollection.add(demoPlantOne);
		plantCollection.add(demoPlantTwo);
		plantCollection.add(demoPlantThree);
		
		System.out.println("Plant Sorting Testing - Results");
		
		sortPlants(plantCollection);
	
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
		
		public static Collection sortPlants(ArrayList plantCollection) {
			
			Collections.sort(plantCollection, new PlantCompare());
			
			
			System.out.println(plantCollection);
			return plantCollection;
			
		}
		
		public static Collection conditionCheckedPlants(ArrayList masterPlantList) {
			
			
			ArrayList conditionedPlants = new ArrayList<CodeBreakDown>();
			//checking enum conditions....
			
			return conditionedPlants;
		}
					
}




class PlantCompare implements Comparator<CodeBreakDown>{
	
	public int compare(CodeBreakDown a1, CodeBreakDown a2){
		
		int value = a1.lepsSupported - a2.lepsSupported;
		if(value==0){
			value = a1.scientificName.compareToIgnoreCase(a2.scientificName);
		}
		return value;
	}
}
