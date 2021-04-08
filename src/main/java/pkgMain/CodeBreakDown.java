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
	
	public static void main(String args[]) {
		GardenConditions conditions = new GardenConditions(500, "full", "dry", "clay");
		CodeBreakDown garden = new CodeBreakDown("Test Garden", "4/8/21", 0, false, conditions.getBudget());
		Plant demoPlantTwo = new Plant(6, 4, "Betula lenta", "clay", "full", "dry", 100, 100, 1,0,0);
		
		
		System.out.println("Testing Updating Lep Count");
		
		updateLeps(garden, demoPlantTwo);
		updateBudget(garden, demoPlantTwo);
		
		
		
		
	}
	
	public int gardenBudget;
	public String GardenName;
	public String lastSaved;
	public int totalLepsSupported;
	public boolean isFull = false;
	//PentagonShape gardenShape;
	
	public CodeBreakDown(String GardenName, String lastSaved, int totalLepsSupported, boolean isFull, int gardenBudget /*PentagonShape gardenShape*/) {
		this.GardenName = GardenName;
		this.lastSaved = lastSaved;
		this.totalLepsSupported = totalLepsSupported;
		this.isFull = isFull;
		this.gardenBudget = gardenBudget;
		//this.gardenShape = gardenShape;
	}

	public String getGardenName() {
		return GardenName;
	}

	public void setGardenName(String gardenName) {
		GardenName = gardenName;
	}

	public String getLastSaved() {
		return lastSaved;
	}

	public void setLastSaved(String lastSaved) {
		this.lastSaved = lastSaved;
	}

	public int getTotalLepsSupported() {
		return totalLepsSupported;
	}

	public void setTotalLepsSupported(int totalLepsSupported) {
		this.totalLepsSupported = totalLepsSupported;
	}

	public boolean isFull() {
		return isFull;
	}

	public void setFull(boolean isFull) {
		this.isFull = isFull;
	}
	
	public int getGardenBudget() {
		return gardenBudget;
	}

	public void setGardenBudget(int gardenBudget) {
		this.gardenBudget = gardenBudget;
	}

	/*public PentagonShape getGardenShape() {
		return gardenShape;
	}

	public void setGardenShape(PentagonShape gardenShape) {
		this.gardenShape = gardenShape;
	}*/
	
	public static int updateLeps(CodeBreakDown garden, Plant plant) {
		
		garden.totalLepsSupported += plant.lepsSupported;
		
		System.out.println("Total Leps Supported: " + garden.totalLepsSupported);
		return garden.totalLepsSupported;
	}
	
	
	public static int updateBudget(CodeBreakDown garden, Plant plant) {
		
		garden.gardenBudget -= plant.price;
		
		System.out.println("Remaining Budget: " + garden.gardenBudget);
		return garden.gardenBudget;
		
	}
	
	

}

