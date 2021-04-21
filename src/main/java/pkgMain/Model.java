package pkgMain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Model class from MVC design pattern
 *
 * @author Aidan Chao
 * @author Nicholas Cutrona
 * @author Caleb Davis
 * @author Joey Loporto
 * @author Tommy Cheung
 */
public class Model {

	
	GardenConditions gardenFinal = new GardenConditions(500, "", "", "");
	GardenState stateFinal = new GardenState("Test Garden", "Arpil", 0, false, gardenFinal.getBudget());
	
	
	Plant demoPlantOne = new Plant(6, 3, "Acer negudo", "clay", "full", "dry", 100, 100, 1, 0 ,0);
	Plant demoPlantTwo = new Plant(20, 5, "Cornus florida", "clay", "full", "dry", 100, 100, 1, 0 ,0);
	Plant demoPlantThree = new Plant(6, 7, "Betula nigra", "clay", "full", "dry", 100, 100, 1, 0 ,0);
	
	ArrayList<Plant> plantsMaster= new ArrayList<Plant>();;
	HashMap<String, Plant> plantDataList = new HashMap<String, Plant>();
	Collection<Plant> plantCollection;
	
	public Model() {
		setPlants();
		createPlantData();
		sortPlantList(plantsMaster);
	}
	
	
	/**
	 * Returns new arrayList with 3 demo plants
	 * 
	 * @return ArrayList of Plants with 3 demo plants
	 */
	public void setPlants() {
		plantsMaster.add(demoPlantOne);
		plantsMaster.add(demoPlantTwo);
		plantsMaster.add(demoPlantThree);
	}
	
	
	/**
	 * Creates and returns a Hashmap with plant data for 3 demo plants.
	 * Stored by scientific name.
	 * 
	 * @return HashMap String, Plant  with 3 demo plants
	 */
	public void createPlantData() {
    	plantDataList.put(demoPlantOne.getScientificName(), demoPlantOne);
    	plantDataList.put(demoPlantTwo.getScientificName(), demoPlantTwo);
    	plantDataList.put(demoPlantThree.getScientificName(), demoPlantThree);

	}
	
	public void sortPlantList(ArrayList<Plant> plants) {
		plantCollection = Plant.sortPlants(plants);
	}
		
	}
