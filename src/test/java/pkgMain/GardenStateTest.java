package pkgMain;

import static org.junit.Assert.*;

import org.junit.Test;
import pkgMain.GardenState;

public class GardenStateTest {
	
	GardenState tester = new GardenState("Test", "4/8/21", 200, false, 500);
	Plant testPlant = new Plant(20, 2, "Test", "clay", "full", "moist", 10, 10, 2, 0, 0);
	
	public static void main(String args[]) {
		
	}
	
	@Test
	public void testUpdateLeps() {
		tester.updateLeps(tester, testPlant);
		fail("Not yet imp");
	}
	@Test
	public void testUpdateGarden() {
		tester.updateGarden(tester, testPlant);
		fail("Not yet imp");
	}
	@Test
	public void testUpdateBudget() {
		tester.updateBudget(tester, testPlant);
		fail("Not yet imp");
	}

	@Test
	public void testPlacePlant() {
		tester.placePlant(tester, testPlant);
		fail("Not yet imp");
	}
	@Test
	public void testHoverPlant() {
		tester.hoverPlant();
		fail("Not yet imp");
	}
	@Test
	public void testDeletePlant() {
		tester.deletePlant();
		fail("Not yet imp");
	}
}

