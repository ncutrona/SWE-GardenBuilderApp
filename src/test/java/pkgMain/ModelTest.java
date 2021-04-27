package pkgMain;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import pkgMain.Model;

public class ModelTest {
	
	Model tester = new Model();
	ArrayList<Plant> plants = new ArrayList<Plant>();
	
	Plant plant1 = new Plant(500, 10, "A a", "loam", "full", "dry", 50, 50, 5, 0, 0);
	Plant plant2 = new Plant(500, 20, "B b", "loam", "full", "dry", 50, 50, 5, 0, 0);
	Plant plant3 = new Plant(500, 30, "Cornus b", "loam", "full", "dry", 50, 50, 5, 0, 0);
	Plant plant4 = new Plant(500, 30, "Cornus a", "loam", "full", "dry", 50, 50, 5, 0, 0);
	
	@Test
	public void sortPlantList() {
		
	}

}
