package pkgMain;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import pkgMain.Plant;
public class PlantTest {
	
	Plant tester = new Plant(500, 10, "Test", "loam", "full", "dry", 50, 50, 5, 0, 0);
	Plant testerCCP1 = new Plant(500, 2, "Test", "loam", "full", "dry", 50, 50, 5, 0, 0);
	Plant testerCCP2 = new Plant(500, 2, "Test", "loam", "full", "dry", 50, 50, 5, 0, 0);
	Plant testerCCP3 = new Plant(500, 2, "Test", "loam", "full", "dry", 50, 50, 5, 0, 0);
	Plant testerCCP4 = new Plant(500, 2, "Test", "sand", "full", "dry", 50, 50, 5, 0, 0);
	Plant testerLCP1 = new Plant(500, 1, "Test", "loam", "full", "dry", 50, 50, 5, 0, 0);
	Plant testerLCP2 = new Plant(500, 2, "Test", "loam", "full", "dry", 50, 50, 5, 0, 0);
	Plant testerLCP3 = new Plant(500, 3, "Test", "loam", "full", "dry", 50, 50, 5, 0, 0);
	Plant testerLCP4 = new Plant(500, 4, "Test", "sand", "full", "dry", 50, 50, 5, 0, 0);
	Plant tester2 = new Plant(500, 20, "Test", "clay", "full", "wet", 50, 50, 5, 0, 0);
	Plant tester3 = new Plant(500, 30, "Test", "clay", "full", "wet", 50, 50, 5, 0, 0);
	Plant tester4 = new Plant(500, 30, "Test", "clay", "full", "wet", 50, 50, 5, 0, 0);
	Plant tester5 = new Plant(500, 30, "YTest", "clay", "full", "wet", 50, 50, 5, 0, 0);
	Plant tester6 = new Plant(500, 30, "ATest", "clay", "full", "wet", 50, 50, 5, 0, 0);
	ArrayList<Plant> testList = new ArrayList<Plant>();
	ArrayList<Plant> testListLCP = new ArrayList<Plant>();
	GardenConditions testCond = new GardenConditions(500, "full", "dry", "loam");
	PlantCompare testPC = new PlantCompare();
	ArrayList<Plant> testConditionSorting = new ArrayList<Plant>();
	Plant p1 = new Plant(500, 20, "P1", "clay", "full", "wet", 50, 50, 5, 0, 0);
	Plant p2 = new Plant(500, 30, "P2", "loam", "medium", "dry", 50, 50, 5, 0, 0);
	Plant p3 = new Plant(500, 30, "P3", "sand", "shade", "moist", 50, 50, 5, 0, 0);
	Plant p4 = new Plant(500, 30, "P4", "clay", "full", "wet", 50, 50, 5, 0, 0);
	Plant p5 = new Plant(500, 30, "P5", "clay", "full", "wet", 50, 50, 5, 0, 0);
	
	
	@Test
	public void testtoString() {
		assertEquals("Test", tester.toString());
	}
	
	@Test
	public void testgetPrice() {
		assertEquals(500, tester3.getPrice());
	}
	
	@Test
	public void testsetPrice() {
		tester.setPrice(5);
		assertEquals(5, tester.getPrice());
	}
	
	@Test
	public void testsetLepsSupported() {
		tester.setLepsSupported(400);
		assertEquals(400, tester.getLepsSupported());
	}
	
	@Test
	public void testgetLepsSupported() {
		assertEquals(10, tester.getLepsSupported());
	}
	
	@Test
	public void testgetScientificName() {
		assertEquals("Test", tester.getScientificName());
	}
	
	@Test
	public void testsetScientificName() {
		tester.setScientificName("Plant");
		assertEquals("Plant", tester.getScientificName());
	}
	
	@Test
	public void testgetSoil() {
		assertEquals("loam", tester.getSoil());
	}
	
	@Test
	public void testsetSoil() {
		tester.setSoil("clay");
		assertEquals("clay", tester.getSoil());
	}
	
	@Test
	public void testgetSun() {
		assertEquals("full", tester.getSun());
	}

	@Test	
	public void testsetSun() {
		tester.setSun("shade");
		assertEquals("shade", tester.getSun());
	}
	
	@Test
	public void testgetMoisture() {
		assertEquals("dry", tester.getMoisture());
	}
	
	@Test
	public void testsetMoisture() {
		tester.setMoisture("wet");
		assertEquals("wet", tester.getMoisture());
	}
	
	@Test
	public void testgetHeight() {
		assertEquals(50, tester.getHeight());
	}
	
	@Test
	public void testsetHeight() {
		tester.setHeight(60);
		assertEquals(60, tester.getHeight());
	}
	
	@Test
	public void testgetWidth() {
		assertEquals(50, tester.getWidth());
	}
	
	@Test
	public void testsetWidth() {
		tester.setWidth(60);
		assertEquals(60, tester.getWidth());
	}
	
	@Test
	public void testgetBloomTime() {
		assertEquals(5, tester.getBloomTime());
	}
	
	@Test
	public void testsetBloomTime() {
		tester.setBloomTime(5);
		assertEquals(5, tester.getBloomTime());
		
	}
	
	@Test
	public void testgetxCor() {
		assertEquals(0, tester.getxCor());
	}
	
	@Test
	public void testsetxCor() {
		tester.setxCor(2);
		assertEquals(2, tester.getxCor());
	}
	
	@Test
	public void testgetyCor() {
		assertEquals(0, tester.getyCor());
	}
	
	@Test
	public void testsetyCor() {
		tester.setyCor(2);
		assertEquals(2, tester.getyCor());
	}
	
	@Test
	public void testconditionCheckedPlants() {
		testList.add(testerCCP1);
		testList.add(testerCCP2);
		testList.add(testerCCP3);
		testList.add(testerCCP4);
		assertEquals(Plant.getConditionCheckedPlants(testList, "full", "loam", "dry").get(0), testerCCP1);
		assertEquals(Plant.getConditionCheckedPlants(testList, "full", "loam", "dry").get(1), testerCCP2);
		assertEquals(Plant.getConditionCheckedPlants(testList, "full", "loam", "dry").get(2), testerCCP3);
	}
	
	@Test
	public void testLepCheckedPlants() {
		testListLCP.add(testerLCP1);
		testListLCP.add(testerLCP2);
		testListLCP.add(testerLCP3);
		testListLCP.add(testerLCP4);
		assertEquals(testerLCP1, Plant.getLepSupportedPlants(testListLCP).get(3));
		assertEquals(testerLCP2, Plant.getLepSupportedPlants(testListLCP).get(2));
		assertEquals(testerLCP3, Plant.getLepSupportedPlants(testListLCP).get(1));
		assertEquals(testerLCP4, Plant.getLepSupportedPlants(testListLCP).get(0));
	}
	
	@Test
	public void testCompare() {
		assertEquals(10, testPC.compare(tester, tester2));
		assertEquals(20, testPC.compare(tester, tester3));
		assertEquals(0, testPC.compare(tester3, tester4));
		boolean val1 = testPC.compare(tester4, tester5) < 0;
		boolean val2 = testPC.compare(tester4, tester6) > 0;
		assertEquals(true, val1);
		assertEquals(true, val2);
	}
	
	@Test
	public void testGetSoilSortedPlants() {
		testConditionSorting.add(p1);
		testConditionSorting.add(p2);
		testConditionSorting.add(p3);
		testConditionSorting.add(p4);
		testConditionSorting.add(p5);
		assertEquals(p1, Plant.getSoilSortedPlants(testConditionSorting, "clay").get(p1.getScientificName()));
		assertEquals(p2, Plant.getSoilSortedPlants(testConditionSorting, "loam").get(p2.getScientificName()));
		assertEquals(p3, Plant.getSoilSortedPlants(testConditionSorting, "sand").get(p3.getScientificName()));
		assertEquals(p4, Plant.getSoilSortedPlants(testConditionSorting, "clay").get(p4.getScientificName()));
		assertEquals(p5, Plant.getSoilSortedPlants(testConditionSorting, "clay").get(p5.getScientificName()));
	}
	
	@Test
	public void testGetSunSortedPlants() {
		testConditionSorting.add(p1);
		testConditionSorting.add(p2);
		testConditionSorting.add(p3);
		testConditionSorting.add(p4);
		testConditionSorting.add(p5);
		assertEquals(p1, Plant.getSunSortedPlants(testConditionSorting, "full").get(p1.getScientificName()));
		assertEquals(p2, Plant.getSunSortedPlants(testConditionSorting, "medium").get(p2.getScientificName()));
		assertEquals(p3, Plant.getSunSortedPlants(testConditionSorting, "shade").get(p3.getScientificName()));
		assertEquals(p4, Plant.getSunSortedPlants(testConditionSorting, "full").get(p4.getScientificName()));
		assertEquals(p5, Plant.getSunSortedPlants(testConditionSorting, "full").get(p5.getScientificName()));
	}
	
	@Test
	public void testGetMoistureSortedPlants() {
		testConditionSorting.add(p1);
		testConditionSorting.add(p2);
		testConditionSorting.add(p3);
		testConditionSorting.add(p4);
		testConditionSorting.add(p5);
		assertEquals(p1, Plant.getMoistureSortedPlants(testConditionSorting, "wet").get(p1.getScientificName()));
		assertEquals(p2, Plant.getMoistureSortedPlants(testConditionSorting, "dry").get(p2.getScientificName()));
		assertEquals(p3, Plant.getMoistureSortedPlants(testConditionSorting, "moist").get(p3.getScientificName()));
		assertEquals(p4, Plant.getMoistureSortedPlants(testConditionSorting, "wet").get(p4.getScientificName()));
		assertEquals(p5, Plant.getMoistureSortedPlants(testConditionSorting, "wet").get(p5.getScientificName()));
	}
	
	@Test
	public void testSortInvByConditions() {
		testConditionSorting.add(p1);
		testConditionSorting.add(p2);
		testConditionSorting.add(p3);
		testConditionSorting.add(p4);
		testConditionSorting.add(p5);
		assertEquals(p1, Plant.sortInvByConditions(testConditionSorting, "clay", "full", "wet").get(p1.getScientificName()));
		assertEquals(p4, Plant.sortInvByConditions(testConditionSorting, "clay", "full", "wet").get(p4.getScientificName()));
		assertEquals(p5, Plant.sortInvByConditions(testConditionSorting, "clay", "full", "wet").get(p5.getScientificName()));
		assertEquals(p2, Plant.sortInvByConditions(testConditionSorting, "loam", "medium", "dry").get(p2.getScientificName()));
		assertEquals(p3, Plant.sortInvByConditions(testConditionSorting, "sand", "shade", "moist").get(p3.getScientificName()));
	}

}
