package pkgMain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import pkgMain.Model;

public class ModelTest {
	
	Model tester = new Model();
	ArrayList<Plant> plants = new ArrayList<Plant>();
	ArrayList<Plant> plantsLeps = new ArrayList<Plant>();
	Map<String, Plant> lepsHash = new LinkedHashMap<String, Plant>();
	
	Plant plant1 = new Plant(500, 10, "A a", "loam", "full", "dry", 50, 50, 5, 0, 0);
	Plant plant2 = new Plant(500, 20, "B b", "loam", "full", "dry", 50, 50, 5, 0, 0);
	Plant plant3 = new Plant(500, 30, "Cornus b", "loam", "full", "dry", 50, 50, 5, 0, 0);
	Plant plant4 = new Plant(500, 30, "Cornus a", "loam", "full", "dry", 50, 50, 5, 0, 0);
	
	@Test
	public void sortPlantList() {
		plants.add(plant1);
		plants.add(plant2);
		plants.add(plant3);
		plants.add(plant4);
		tester.sortHashLeps(plants);
	}

}
