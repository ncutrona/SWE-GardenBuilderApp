package pkgMain;

import java.util.ArrayList;

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
		//this.gardenBudget = gardenBudget;
		setGardenBudget(gardenBudget);

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
	
	
	public void hoverPlant() {
		
	}
	
	public void deletePlant() {
		
	}
}
