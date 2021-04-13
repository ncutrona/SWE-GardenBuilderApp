package pkgMain;
import pkgMain.Conditions.moistureCondition;
import pkgMain.Conditions.soilCondition;
import pkgMain.Conditions.sunCondition;


/**
 * GardenConditions class.
 * Contains information on garden budget and conditions.
 * 
 * @author Aidan Chao
 * @author Nicholas Cutrona
 * @author Caleb Davis
 * @author Joey Loporto
 * @author Tommy Cheung
 */
public class GardenConditions {
	public int userBudget;
	
	static Conditions.sunCondition sunCond = sunCondition.FULL;
	static Conditions.soilCondition soilCond = soilCondition.CLAY;
	static Conditions.moistureCondition moistureCond = moistureCondition.DRY;
	
	
	/**
	 * Constructor for GardenConditions object.
	 * Creates instance of GardenConditions with passed in budget and conditions.
	 * 
	 * @param userBudget int the user's budget
	 * @param sun sun condition from Conditions.sunCondition
	 * @param moisture moisture condition from Conditions.moistureCondition
	 * @param soil soil condition from Conditions.soilCondition
	 */
	public GardenConditions(int userBudget, String sun, String moisture, String soil) {
		setBudget(userBudget);
		setSunConditions(sun);
		setMoistureConditions(moisture);
		setSoilConditions(soil);
	}
	
	
	/**
	 * Sets the garden budget from passed in int
	 * 
	 * @param userBudget int the user's budget
	 */
	public void setBudget(int userBudget) {	
		this.userBudget = userBudget;
	}
	
	
	/**
	 * Returns the garden budget
	 * 
	 * @return int the user's budget
	 */
	public int getBudget() {
		return userBudget;
	}
	
	
	/**
	 * Sets the garden's sunCondition from a string
	 * 
	 * @param sun string representing condition from Conditions.sunCondition
	 */
	public void setSunConditions(String sun) {
		if (sun.equals("full")) {
			sunCond = sunCondition.FULL;
		}
		else if (sun.equals("shade")) {
			sunCond = sunCondition.SHADE;
		}
		else {
			sunCond = sunCondition.PARTIAL;
		}
	}
		
		
	/**
	 * Sets the garden's soilCondition from a string
	 * 
	 * @param soil string representing condition from Conditions.soilCondition
	 */
	public void setSoilConditions(String soil) {	
		if (soil.equals("clay")) {
			soilCond = soilCondition.CLAY;
		}
		else if (soil.equals("loam")) {
			soilCond = soilCondition.LOAM;
		}
		else {
			soilCond = soilCondition.SAND;
		}
		
	}
	
	
	/**
	 * Sets the garden's moistureCondition from a string
	 * 
	 * @param moisture string representing condition from Conditions.moistureCondition
	 */
	public void setMoistureConditions(String moisture) {
		
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
	
	
	/**
	 * Returns the current sunCondition as a string
	 * 
	 * @return string representing condition from Conditions.sunCondition
	 */
	public String getSun() {
		return sunCond.getName();
	}
	
	
	/**
	 * Returns the current soilCondition as a string
	 * 
	 * @return string representing condition from Conditions.soilCondition
	 */
	public String getSoil() {
		return soilCond.getName();
	}
	
	
	/**
	 * Returns the current moistureCondition as a string
	 * 
	 * @return string representing condition from Conditions.moistureCondition
	 */
	public String getMoisture() {
		return moistureCond.getName();
	}
	
}