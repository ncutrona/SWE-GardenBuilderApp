package pkgMain;

import static org.junit.Assert.*;

import org.junit.Test;

public class CoordinatesTest {

	Coordinates tester = new Coordinates(1, 2);
	Coordinates tester2 = new Coordinates();
	
	@Test
	public void testGetX() {
		assertEquals(1, tester.getX(), 0.01);
	}
	
	@Test
	public void testGetY() {
		assertEquals(2, tester.getY(), 0.01);
	}

}
