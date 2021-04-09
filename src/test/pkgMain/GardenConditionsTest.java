package pkgMain;

import static org.junit.Assert.*;
import org.junit.Test;

public class GardenConditionsTest {
	
	GardenConditions tester = new GardenConditions(500, "full", "dry", "loam");
	
	public static void main(String args[]) {
		
	}
	
	@Test
	public void testSetBudget() {
		tester.setBudget(500);
		fail("Not yet implemented");
	}
	
	@Test
	public void testgetBudget() {
		tester.getBudget();
		fail("Not yet implemented");
	}
	
	@Test
	public void testSetConditions() {
		tester.setConditions("full", "dry", "loam");
		tester.setConditions("partial", "wet", "clay");
		tester.setConditions("shade", "moist", "sand");
		fail("Not yet imp");
	}
	
	@Test
	public void testgetSun() {
		tester.getSun();
		fail("Not yet implemented");
	}
	
	@Test
	public void testgetSoil() {
		tester.getSoil();
		fail("Not yet implemented");
	}
	
	@Test
	public void testgetMoisture() {
		tester.getMoisture();
		fail("Not yet implemented");
	}

}
