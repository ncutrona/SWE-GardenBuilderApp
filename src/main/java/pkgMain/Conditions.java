package pkgMain;

public class Conditions {
	
	public enum sunCondition {
		
		SHADE("shade"), 
		PARTIAL("partial"), 
		FULL("full");
		
		
		private String name;
		
		private sunCondition(String s) {
			name = s;
		}
		
		public String getName() {
			return name;
		}
	}
	
	public enum moistureCondition {
		
		DRY("dry"), 
		MOIST("moist"), 
		WET("wet");
		
		
		private String name;
		
		private moistureCondition(String s) {
			name = s;
		}
		
		public String getName() {
			return name;
		}


	}
	

	
	public enum soilCondition {
		
		CLAY("clay"), 
		LOAM("loam"), 
		SAND("sand");
		
		
		private String name;
		
		private soilCondition(String s) {
			name = s;
		}
		
		public String getName() {
			return name;
		}
		
		
		

	}
	
	
	
	

}
