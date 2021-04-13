package pkgMain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;

public class Model2{
	
	private GardenConditions conditions;
	private GardenState state;
	
	public Model2(){
		conditions = new GardenConditions(500, "full", "dry", "clay");
		state = new GardenState("", "Arpil", 0, false, conditions.getBudget());
	}
		
	public Model2(GardenConditions conditions, GardenState state) {
		this.conditions = conditions;
		this.state = state;
	}

	public GardenConditions getConditions() {
		return conditions;
	}

	public void setConditions(GardenConditions conditions) {
		this.conditions = conditions;
	}

	public GardenState getState() {
		return state;
	}

	public void setState(GardenState state) {
		this.state = state;
	}
	
	
}