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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

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
	
	//Keeping Track of what plants are placed on garden.
	HashMap<String, ArrayList<Coordinates>> addedPlants = new HashMap<String, ArrayList<Coordinates>>();

	ArrayList<Plant> plantsMaster= new ArrayList<Plant>();;
	HashMap<String, Plant> plantDataList = new HashMap<String, Plant>();
	Map<String, Plant> lepsHash = new LinkedHashMap<String, Plant>();
	Collection<Plant> plantCollection;
	Collection<Plant> lepsInvScreen; 


	/**
	 * Default constructor for Model
	 */
	public Model() {

	}
	
	/**
	 * Sorts the leps from plants into the lepsHash hashmap
	 * 
	 * @param plants ArrayList<Plant> of plants to add to lepsHash
	 */
	public void sortHashLeps(ArrayList<Plant> plants) {
		for(Plant p: plants) {
			lepsHash.put(p.getScientificName(), p);
			System.out.println(p.getScientificName());
		}
	}

}
