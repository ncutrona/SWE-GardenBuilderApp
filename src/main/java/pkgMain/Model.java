package pkgMain;

import java.util.ArrayList;
import java.util.HashMap;

public class Model {

	
	String NodeId = "NULL";
	
	//Creating the garden conditions
	GardenConditions garden = new GardenConditions(500, "full", "dry", "clay");
	//Creating the State of the Garden
	GardenState state = new GardenState("", "Arpil", 0, false, garden.getBudget());
	//Creating Plants for the Garden
	Plant demoPlantOne = new Plant(6, 3, "Acer negudo", "clay", "full", "dry", 100, 100, 1, 0 ,0);
	Plant demoPlantTwo = new Plant(20, 5, "Cornus florida", "clay", "full", "dry", 100, 100, 1, 0 ,0);
	Plant demoPlantThree = new Plant(6, 7, "Coreposis verticillata", "clay", "full", "dry", 100, 100, 1, 0 ,0);
	
	//Conditions String
	public String Conditions = "Soil : " + garden.getSoil() + ",  Sun : " + garden.getSun() + ",  Moisture : " + garden.getMoisture();
	
	public Model() {
		
	}
	
	//Adding our Master Plant List
	public ArrayList<Plant> getPlants() {
		ArrayList<Plant> plantsMaster = new ArrayList<Plant>();
		plantsMaster.add(demoPlantOne);
		plantsMaster.add(demoPlantTwo);
		plantsMaster.add(demoPlantThree);
		return plantsMaster;
	}
	
	//Generating a HashMap of the Plant Data
	public HashMap<String, Plant> createPlantData() {
		HashMap<String, Plant> plantData = new HashMap<String, Plant>();
    	plantData.put(demoPlantOne.getScientificName(), demoPlantOne);
    	plantData.put(demoPlantTwo.getScientificName(), demoPlantTwo);
    	plantData.put(demoPlantThree.getScientificName(), demoPlantThree);
    	
    	return plantData;
	}
	
	
}
