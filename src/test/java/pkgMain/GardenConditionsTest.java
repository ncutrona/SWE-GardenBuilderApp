package pkgMain;

import static org.junit.Assert.*;

import org.junit.Test;

public class GardenConditionsTest {

GardenConditions tester = new GardenConditions(500, "full", "dry", "loam");
	
	@Test
	public void testSetBudget() {
		tester.setBudget(500);
		assertEquals(500, tester.getBudget());
	}
	
	@Test
	public void testgetBudget() {
		assertEquals(500, tester.getBudget());
	}
	
	@Test
	public void testSetDimensions() {
		tester.setDimensions(1, 1);
	}
	
	@Test
	public void testGetLength() {
		tester.setDimensions(1, 1);
		assertEquals(1, tester.getLength());
	}
	
	@Test
	public void testGetWidth() {
		tester.setDimensions(1, 1);
		assertEquals(1, tester.getWidth());
	}
	
	@Test
	public void testSetSunConditions() {
		tester.setSunConditions("full");
		assertEquals("full", tester.getSun());
		
		tester.setSunConditions("partial");
		assertEquals("partial", tester.getSun());
		
		tester.setSunConditions("shade");
		assertEquals("shade", tester.getSun());
	}
	
	@Test
	public void testSetSoilConditions() {
		tester.setSoilConditions("clay");
		assertEquals("clay", tester.getSoil());
		
		tester.setSoilConditions("loam");
		assertEquals("loam", tester.getSoil());
		
		tester.setSoilConditions("sand");
		assertEquals("sand", tester.getSoil());
	}
	
	@Test
	public void testSetMoistureConditions() {
		tester.setMoistureConditions("wet");
		assertEquals("wet", tester.getMoisture());
		
		tester.setMoistureConditions("dry");
		assertEquals("dry", tester.getMoisture());
		
		tester.setMoistureConditions("moist");
		assertEquals("moist", tester.getMoisture());
	}
	
	@Test
	public void testgetSun() {
		assertEquals("full", tester.getSun());
	}
	
	@Test
	public void testgetSoil() {
		assertEquals("loam", tester.getSoil());
	}
	
	@Test
	public void testgetMoisture() {
		assertEquals("dry", tester.getMoisture());
	}

}
