package pkgMain;

public class GardenState {

	public int gardenBudget;
	public String GardenName;
	public String lastSaved;
	public int totalLepsSupported;
	public boolean isFull = false;
	//PentagonShape gardenShape;
	
	public GardenState(String GardenName, String lastSaved, int totalLepsSupported, boolean isFull, int gardenBudget /*PentagonShape gardenShape*/) {
		this.GardenName = GardenName;
		this.lastSaved = lastSaved;
		this.totalLepsSupported = totalLepsSupported;
		this.isFull = isFull;
		this.gardenBudget = gardenBudget;
		//this.gardenShape = gardenShape;
	}

	public String getGardenName() {
		return GardenName;
	}

	public void setGardenName(String gardenName) {
		GardenName = gardenName;
	}

	public String getLastSaved() {
		return lastSaved;
	}

	public void setLastSaved(String lastSaved) {
		this.lastSaved = lastSaved;
	}

	public int getTotalLepsSupported() {
		return totalLepsSupported;
	}

	public void setTotalLepsSupported(int totalLepsSupported) {
		this.totalLepsSupported = totalLepsSupported;
	}

	public boolean isFull() {
		return isFull;
	}

	public void setFull(boolean isFull) {
		this.isFull = isFull;
	}
	
	public int getGardenBudget() {
		return gardenBudget;
	}

	public void setGardenBudget(int gardenBudget) {
		this.gardenBudget = gardenBudget;
	}

	/*public PentagonShape getGardenShape() {
		return gardenShape;
	}

	public void setGardenShape(PentagonShape gardenShape) {
		this.gardenShape = gardenShape;
	}*/
	
	public static int updateLeps(GardenState garden, Plant plant) {
		
		garden.totalLepsSupported += plant.lepsSupported;
		
		System.out.println("Total Leps Supported: " + garden.totalLepsSupported);
		return garden.totalLepsSupported;
	}
	
	
	public static int updateBudget(GardenState garden, Plant plant) {
		
		garden.gardenBudget -= plant.price;
		
		System.out.println("Remaining Budget: " + garden.gardenBudget);
		return garden.gardenBudget;
		
	}
	
	public static void updateGarden(GardenState garden, Plant plant) {
		
		updateBudget(garden, plant);
		updateLeps(garden, plant);
		//will add more methods later (post alpha).
		
	}
	
	public static void placePlant(GardenState garden, Plant plant) {
		
		updateGarden(garden, plant);
	}
	
	//METHODS TO ADD POST ALPHA:
	//hoverPlant(){}
	//deletePlant(){}
	
	public void hoverPlant() {
		
	}
	
	public void deletePlant() {
		
	}
}
