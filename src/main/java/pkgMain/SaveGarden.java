package pkgMain;

import java.io.Serializable;

public class SaveGarden implements Serializable{
	private int budget;
	private String Name;
	private int numLepSupported;
	
	public SaveGarden() {}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getNumLepSupported() {
		return numLepSupported;
	}

	public void setNumLepSupported(int numLepSupported) {
		this.numLepSupported = numLepSupported;
	}
	
	
}