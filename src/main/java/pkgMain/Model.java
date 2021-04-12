package pkgMain;

import java.util.ArrayList;
import java.util.HashMap;

public class Model {

	
	GardenConditions gardenFinal = new GardenConditions(500, "full", "dry", "clay");
	GardenState stateFinal = new GardenState("Test Garden", "Arpil", 0, false, gardenFinal.getBudget());
	
}
