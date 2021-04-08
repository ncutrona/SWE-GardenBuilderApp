package pkgMain;

import pkgMain.Conditions.moistureCondition;
import pkgMain.Conditions.soilCondition;
import pkgMain.Conditions.sunCondition;

public class GardenConditions extends Model {
	
	public int userBudget;
	
	static Conditions.sunCondition sunCond = sunCondition.FULL;
	static Conditions.soilCondition soilCond = soilCondition.CLAY;
	static Conditions.moistureCondition moistureCond = moistureCondition.DRY;
	
	public void setBudget(int userBudget) {	
		this.userBudget = userBudget;
	}
	
	public int getBudget() {
		return userBudget;
	}
	
	public void setConditions(String sun, String moisture, String soil) {
		if (sun.equals("full")) {
			sunCond = sunCondition.FULL;
		}
		else if (sun.equals("shade")) {
			sunCond = sunCondition.SHADE;
		}
		else {
			sunCond = sunCondition.PARTIAL;
		}
		
		if (soil.equals("clay")) {
			soilCond = soilCondition.CLAY;
		}
		else if (soil.equals("loam")) {
			soilCond = soilCondition.LOAM;
		}
		else {
			soilCond = soilCondition.SAND;
		}
		
		if (moisture.equals("wet")) {
			moistureCond = moistureCondition.WET;
		}
		else if (moisture.equals("moist")) {
			moistureCond = moistureCondition.MOIST;
		}
		else {
			moistureCond = moistureCondition.DRY;
		}
	}
	
	public String getSun() {
		return sunCond.getName();
	}
	
	public String getSoil() {
		return soilCond.getName();
	}
	
	public String getMoisture() {
		return moistureCond.getName();
	}
	
	public GardenConditions(int userBudget, String sun, String moisture, String soil) {
		this.userBudget = userBudget;
		setConditions(sun, moisture, soil);
	}
}
