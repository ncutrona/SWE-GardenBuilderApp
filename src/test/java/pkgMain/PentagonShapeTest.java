package pkgMain;

import static org.junit.Assert.*;

import org.junit.Test;
import pkgMain.PentagonShape;
public class PentagonShapeTest {
	
	PentagonShape tester = new PentagonShape(200, 200);
	Coordinates coords = new Coordinates(0, 0);
	
	public static void main (String args[]) {
		
	}
	
	@Test
	public void testgetTop() {
		tester.getTop();
		fail("Not yet implemented");
	}
	
	@Test
	public void testgetLeft() {
		tester.getLeft();
		fail("Not yet implemented");
	}
	
	@Test
	public void testgetLeftdown() {
		tester.getLeftdown();
		fail("Not yet implemented");
	}
	
	@Test
	public void testgetRight() {
		tester.getRight();
		fail("Not yet implemented");
	}
	
	@Test
	public void testgetRightdown() {
		tester.getRightdown();
		fail("Not yet implemented");
	}
	
	@Test
	public void testsetTop() {
		tester.setTop(coords);
		fail("Not yet implemented");
	}
	
	@Test
	public void testsetLeft() {
		tester.setLeft(coords);
		fail("Not yet implemented");
	}
	
	@Test
	public void testsetLeftdown() {
		tester.setLeftdown(coords);
		fail("Not yet implemented");
	}
	
	@Test
	public void testsetRight() {
		tester.setRight(coords);
		fail("Not yet implemented");
	}
	
	@Test
	public void testsetRightdown() {
		tester.setRightdown(coords);
		fail("Not yet implemented");
	}
	

}
