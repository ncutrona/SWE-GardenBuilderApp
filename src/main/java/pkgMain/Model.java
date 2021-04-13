package pkgMain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Model {

	
	GardenConditions gardenFinal = new GardenConditions(0, "", "", "");
	GardenState stateFinal = new GardenState("Test Garden", "Arpil", 0, false, gardenFinal.getBudget());
	
	
	Plant demoPlantOne = new Plant(6, 3, "Acer negudo", "clay", "full", "dry", 100, 100, 1, 0 ,0);
	Plant demoPlantTwo = new Plant(12, 5, "Cornus florida", "clay", "full", "dry", 100, 100, 1, 0 ,0);
	Plant demoPlantThree = new Plant(3, 7, "Betula nigra", "clay", "full", "dry", 100, 100, 1, 0 ,0);
	
	public String Conditions = "Soil : " + gardenFinal.getSoil() + ",  Sun : " + gardenFinal.getSun() + ",  Moisture : " + gardenFinal.getMoisture();
	
	
	
	public ArrayList<Plant> getPlants() {
		ArrayList<Plant> plantsMaster = new ArrayList<Plant>();
		plantsMaster.add(demoPlantOne);
		plantsMaster.add(demoPlantTwo);
		plantsMaster.add(demoPlantThree);
		return plantsMaster;
		
	}
	
	public HashMap<String, Plant> createPlantData() {
		HashMap<String, Plant> plantData = new HashMap<String, Plant>();
    	plantData.put(demoPlantOne.getScientificName(), demoPlantOne);
    	plantData.put(demoPlantTwo.getScientificName(), demoPlantTwo);
    	plantData.put(demoPlantThree.getScientificName(), demoPlantThree);
    	
    	return plantData;
	}
	
	Collection<Plant> plantCollection = Plant.sortPlants(getPlants());
		
	}
