package pkgMain;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import pkgMain.Plant;
public class PlantTest {
	
	Plant tester = new Plant(500, 2, "Test", "loam", "full", "dry", 50, 50, 5, 0, 0);
	Plant tester2 = new Plant(500, 2, "Test", "clay", "full", "wet", 50, 50, 5, 0, 0);
	Plant tester3 = new Plant(500, 3, "Test", "clay", "full", "wet", 50, 50, 5, 0, 0);
	ArrayList<Plant> testList = new ArrayList<Plant>();
	GardenConditions testCond = new GardenConditions(500, "full", "dry", "loam");
	PlantCompare testPC = new PlantCompare();
	
	public static void main(String args[]) {

	}
	
	@Test
	public void testtoString() {
		tester.toString();
		fail("Not yet implemented");
	}
	
	@Test
	public void testgetPrice() {
		tester.getPrice();
		fail("Not yet implemented");
	}
	
	@Test
	public void testsetPrice() {
		tester.setPrice(5);
		fail("Not yet implemented");
	}
	
	@Test
	public void testsetLepsSupported() {
		tester.setLepsSupported(2);
		fail("Not yet implemented");
	}
	
	@Test
	public void testgetLepsSupported() {
		tester.getLepsSupported();
		fail("Not yet implemented");
	}
	
	@Test
	public void testgetScientificName() {
		tester.getScientificName();
		fail("Not yet implemented");
	}
	
	@Test
	public void testsetScientificName() {
		tester.setScientificName("Plant");
		fail("Not yet implemented");
	}
	
	@Test
	public void testgetSoil() {
		tester.getSoil();
		fail("Not yet implemented");
	}
	
	@Test
	public void testsetSoil() {
		tester.setSoil("clay");
		fail("Not yet implemented");
	}
	
	@Test
	public void testgetSun() {
		tester.getSun();
		fail("Not yet implemented");
	}

	@Test	
	public void testsetSun() {
		tester.setSun("shade");
		fail("Not yet implemented");
	}
	
	@Test
	public void testgetMoisture() {
		tester.getMoisture();
		fail("Not yet implemented");
	}
	
	@Test
	public void testsetMoisture() {
		tester.setMoisture("wet");
		fail("Not yet implemented");
	}
	
	@Test
	public void testgetHeight() {
		tester.getHeight();
		fail("Not yet implemented");
	}
	
	@Test
	public void testsetHeight() {
		tester.setHeight(50);
		fail("Not yet implemented");
	}
	
	@Test
	public void testgetWidth() {
		tester.getWidth();
		fail("Not yet implemented");
	}
	
	@Test
	public void testsetWidth() {
		tester.setWidth(50);
		fail("Not yet implemented");
	}
	
	@Test
	public void testgetBloomTime() {
		tester.getBloomTime();
		fail("Not yet implemented");
	}
	
	@Test
	public void testsetBloomTime() {
		tester.setBloomTime(5);
		fail("Not yet implemented");
	}
	
	@Test
	public void testgetxCor() {
		tester.getxCor();
		fail("Not yet implemented");
	}
	
	@Test
	public void testsetxCor() {
		tester.setxCor(2);
		fail("Not yet implemented");
	}
	
	@Test
	public void testgetyCor() {
		tester.getyCor();
		fail("Not yet implemented");
	}
	
	@Test
	public void testsetyCor() {
		tester.setyCor(2);
		fail("Not yet implemented");
	}
	
	@Test
	public void testConditionCheckedPlants() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testSortPlants() {
		Plant.sortPlants(testList);
		fail("Not yet implemented");
	}
	
	@Test
	public void testconditionCheckedPlants() {
		testList.add(tester);
		Plant.conditionCheckedPlants(testList, "full", "loam", "wet");
		fail("Not yet implemented");
	}
	
	@Test
	public void testCompare() {
		testPC.compare(tester, tester2);
		testPC.compare(tester2, tester3);
		fail("Not yet implemented");
	}

}
