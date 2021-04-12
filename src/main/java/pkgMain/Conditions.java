package pkgMain;

/**
 * Conditions enumerators for sun, moisture, and soil condition.
 * sunCondition: shade, partial, full.
 * moistureCondition: dry, moist, wet.
 * soilCondition: clay, loam, sand.
 * 
 * @author Aidan Chao
 * @author Nicholas Cutrona
 * @author Caleb Davis
 * @author Joey Loporto
 * @author Tommy Cheung
 */
public class Conditions {
	
	
	/**
	 * sunCondition enumerator.
	 * SHADE, PARTIAL, FULL.
	 */
	public enum sunCondition {
		
		SHADE("shade"), 
		PARTIAL("partial"), 
		FULL("full");
		
		
		private String name;
		
		
		/**
		 * Sets name of sun condition from passed in string.
		 * 
		 * @param s string name of condition
		 */
		private sunCondition(String s) {
			name = s;
		}
		
		
		/**
		 * Returns the sun condition as a string
		 * 
		 * @return name of condition as string
		 */
		public String getName() {
			return name;
		}
	}
	
	
	/**
	 * moistureCondition enumerator.
	 * DRY, MOIST, WET.
	 */
	public enum moistureCondition {
		
		DRY("dry"), 
		MOIST("moist"), 
		WET("wet");
		
		
		private String name;
		
		
		/**
		 * Sets name of moisture condition from passed in string.
		 * 
		 * @param s string name of condition
		 */
		private moistureCondition(String s) {
			name = s;
		}
		
		
		/**
		 * Returns the sun condition as a string
		 * 
		 * @return name of condition as string
		 */
		public String getName() {
			return name;
		}


	}
	

	/**
	 * soilCondition enumerator.
	 * CLAY, LOAM, SAND.
	 */
	public enum soilCondition {
		
		CLAY("clay"), 
		LOAM("loam"), 
		SAND("sand");
		
		
		private String name;
		
		
		/**
		 * Sets name of soil condition from passed in string.
		 * 
		 * @param s string name of condition
		 */
		private soilCondition(String s) {
			name = s;
		}
		
		
		/**
		 * Returns the sun condition as a string
		 * 
		 * @return name of condition as string
		 */
		public String getName() {
			return name;
		}
		
		
		

	}
	
	
	
	

}
