package pkgMain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javafx.scene.image.Image;

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

	ArrayList<Plant> plantsMaster= new ArrayList<Plant>();;
	HashMap<String, Plant> plantDataList = new HashMap<String, Plant>();
	Collection<Plant> plantCollection;

//	// Code for reading in from a file
//	public void readCsv() throws IOException {
//		File plantData = Paths.get("src/main/resources/Plant_Data.csv").toFile().getAbsoluteFile();
//		BufferedReader br = new BufferedReader(new FileReader(plantData));
//		String line = "";
//		try {
//			while ((line = br.readLine()) != null) {
//				String[] data = line.split(",");
//				Plant plant = new Plant(Integer.parseInt(data[5]), Integer.parseInt(data[1]),
//						data[0], data[2], data[4], data[3], Integer.parseInt(data[7]), Integer.parseInt(data[8]), Integer.parseInt(data[6]), 0, 0);
//				plantsMaster.add(plant);
//				plantDataList.put(plant.getScientificName(), plant);
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//	}

	public Model() throws IOException {
		// setPlants();
		// createPlantData();
		//readCsv();
		sortPlantList(plantsMaster);
	}


	/**
	 * Returns new arrayList with 3 demo plants
	 * 
	 * @return ArrayList of Plants with 3 demo plants
	 */
	//	public void setPlants() {
	//		plantsMaster.add(demoPlantOne);
	//		plantsMaster.add(demoPlantTwo);
	//		plantsMaster.add(demoPlantThree);
	//	}


	/**
	 * Creates and returns a Hashmap with plant data for 3 demo plants.
	 * Stored by scientific name.
	 * 
	 * @return HashMap String, Plant  with 3 demo plants
	 */
	//	public void createPlantData() {
	//    	plantDataList.put(demoPlantOne.getScientificName(), demoPlantOne);
	//    	plantDataList.put(demoPlantTwo.getScientificName(), demoPlantTwo);
	//    	plantDataList.put(demoPlantThree.getScientificName(), demoPlantThree);
	//
	//	}

	public void sortPlantList(ArrayList<Plant> plants) {
		plantCollection = Plant.sortPlants(plants);
	}

}
