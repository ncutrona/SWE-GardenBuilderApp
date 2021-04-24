package pkgMain;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class SaveGarden implements Serializable{
	private int budget;
	private String Name;
	private int numLepSupported;
	private String sunCondition, moistCondition, soilCondition;
	
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

	public String getSunCondition() {
		return sunCondition;
	}
	public String getMoistCondition() {
		return moistCondition;
	}
	public String getSoilCondition() {
		return soilCondition;
	}
	
	public void setConditions(String soil, String sun, String moist) {
		this.soilCondition = soil;
		this.sunCondition = sun;
		this.moistCondition = moist;
	}
	
}