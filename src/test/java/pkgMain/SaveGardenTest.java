package pkgMain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.junit.Test;

public class SaveGardenTest {

	SaveGarden tester = new SaveGarden(1,1);
	Coordinates testCoor = new Coordinates(10, 10);
	String plantString = "Plant";
	ArrayList<Coordinates> coords = new ArrayList<Coordinates>();
	ArrayList<Double> hexPoints = new ArrayList<Double>();
	ObservableList<Double> oList = FXCollections.observableArrayList();
	HashMap<String, ArrayList<Coordinates>> hMap = new HashMap<String, ArrayList<Coordinates>>();
	
	@Test
	public void testGetBudget() {
		tester.setBudget(2);
		assertEquals(tester.getBudget(), 2);
	}
	
	@Test
	public void testsetBudget() {
		tester.setBudget(3);
		assertEquals(tester.getBudget(), 3);
	}
	
	@Test
	public void testGetName() {
		tester.setName("Test");
		assertEquals(tester.getName(), "Test");
	}
	
	@Test
	public void testSetHexPoints() {
		oList.add(1.0);
		tester.setHexPoints(oList);
		assertEquals(oList, tester.getHexPoints());
	}
	
	@Test
	public void testGetHexPoints() {
		tester.setHexPoints(oList);
		assertEquals(oList, tester.getHexPoints());
	}
	
	@Test
	public void testSetName() {
		tester.setName("Garden");
		assertEquals(tester.getName(), "Garden");
	}
	
	@Test
	public void testSetPlants() {
		tester.setPlants(hMap);
		assertEquals(hMap, tester.getPlants());
	}
	
	@Test
	public void testGetPlants() {
		tester.setPlants(hMap);
		assertEquals(hMap, tester.getPlants());
	}
	
	@Test
	public void testGetNumLepSupported() {
		tester.setNumLepSupported(1);
		assertEquals(1, tester.getNumLepSupported());
	}
	
	@Test
	public void testSetNumLepSupported() {
		tester.setNumLepSupported(5);
		assertEquals(5, tester.getNumLepSupported());
	}
	
	@Test
	public void testGetSunCondition() {
		tester.setConditions("clay", "full", "moist");
		assertEquals("full", tester.getSunCondition());
	}
	
	@Test
	public void testGetMoistCondition() {
		tester.setConditions("clay", "full", "moist");
		assertEquals("moist", tester.getMoistCondition());
	}
	
	@Test
	public void testGetSoilCondition() {
		tester.setConditions("clay", "full", "moist");
		assertEquals("clay", tester.getSoilCondition());
	}
	
	@Test
	public void testSetConditions() {
		tester.setConditions("clay", "full", "moist");
		assertEquals("full", tester.getSunCondition());
		assertEquals("moist", tester.getMoistCondition());
		assertEquals("clay", tester.getSoilCondition());
	}
	
	@Test
	public void testGetLength() {
		assertEquals(1, tester.getLength());
	}
	
	@Test
	public void testGetWidth() {
		assertEquals(1, tester.getWidth());
	}

}
