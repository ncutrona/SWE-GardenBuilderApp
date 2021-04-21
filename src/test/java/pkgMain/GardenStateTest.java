package pkgMain;

import static org.junit.Assert.*;

import org.junit.Test;

public class GardenStateTest {

	
	GardenState tester = new GardenState("Test", "4/8/21", 200, false, 500);
	Plant testPlant = new Plant(20, 2, "Test", "clay", "full", "moist", 10, 10, 2, 0, 0);
	
	@Test
	public void testgetGardenName() {
		assertEquals("Test", tester.getGardenName());
		
	}
	
	@Test
	public void testsetGardenName() {
		tester.setGardenName("TestSet");
		assertEquals("TestSet", tester.getGardenName());
	}
	
	@Test
	public void testgetLastSaved() {
		assertEquals("4/8/21", tester.getLastSaved());
	}
	
	@Test
	public void testsetLastSaved() {
		tester.setLastSaved("5/1/20");
		assertEquals("5/1/20", tester.getLastSaved());
	}
	
	@Test
	public void testgetTotalLepsSupported() {
		assertEquals(tester.getTotalLepsSupported(), 200);
	}
	
	@Test
	public void testsetTotalLepsSupported() {
		tester.setTotalLepsSupported(2);
		assertEquals(2, tester.getTotalLepsSupported());
	}
	
	@Test
	public void testisFull() {
		assertEquals(false, tester.isFull());
	}
	
	@Test
	public void testsetFull() {
		tester.setFull(true);
		assertEquals(true, tester.isFull());
	}
	
	@Test
	public void getGardenBudget() {
		assertEquals(500, tester.getGardenBudget());
	}
	
	@Test
	public void setGardenBudget() {
		tester.setGardenBudget(5);
		assertEquals(5, tester.getGardenBudget());
	}

}
