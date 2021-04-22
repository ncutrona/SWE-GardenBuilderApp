package pkgMain;

import java.util.Collection;
import java.util.HashMap;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class View{
	
	private Stage stage, popupStage;
	private Scene screenScene;
	
	LoadScreen loadScreen;
	ConditionScreen conditionScreen;
	GardenScreen gardenScreen;
	InvScreen invScreen;
	SaveScreen saveScreen;
	PopUpWindow popup;
	
	Scene load, save, condition, garden, inv , pop;
	
	public View() {
		createScreenAndScene();
		
		loadScreenToScene();
	}
	public Scene getScreen() {
		return screenScene;
	}
	
	public Scene loadScreenToScene() {
		screenScene = load;
		return screenScene;
	}
	public Scene saveScreenToScene() {
		screenScene = save;
		return screenScene;
	}
	public Scene conditionScreenToScene() {
		screenScene = condition;
		return screenScene;
	}
	public Scene gardenScreenToScene() {
		screenScene = garden;
		return screenScene;
	}
	public Scene invScreenToScene() {
		screenScene = inv;
		return screenScene;
	}
	public void popScreenToStage() {
		popupStage = new Stage();  			
		popupStage.initModality(Modality.APPLICATION_MODAL);	
		popupStage.setTitle("Options");
		popupStage.setMinWidth(250);
		popupStage.setMinHeight(500);
		popupStage.setScene(pop);
		popupStage.showAndWait(); 
	}
	public void closePopUp() {
		popupStage.close();
	}
	
	
	public void createScreenAndScene() {
		loadScreen = new LoadScreen();
		load = new Scene(loadScreen.getScreen(), 1000, 600);
		saveScreen = new SaveScreen();
		save = new Scene(saveScreen.createLoadingBorder(),800, 600);
		conditionScreen = new ConditionScreen();
		condition = new Scene(conditionScreen.getScreen(), 800, 600);
		gardenScreen = new GardenScreen();
		garden = new Scene(gardenScreen.getScreen(), 1000, 600);
		invScreen = new InvScreen();
		inv = new Scene(invScreen.getScreen(),800, 600);
		popup = new PopUpWindow();
		pop = new Scene(popup.getScreen());
	}
	
	public void clearInfo() {
		conditionScreen.budget.clear();
		conditionScreen.gardenName.clear();
		gardenScreen.gardenPane.getChildren().removeAll(gardenScreen.gardenPane.getChildren());
		conditionScreen.budget.setPromptText("Enter your Budget $");
		conditionScreen.soilSlider.setValue(1);
		conditionScreen.sunSlider.setValue(1);
		conditionScreen.moistSlider.setValue(1);
	}
	
	public int[] plantDragDropping(DragEvent event, HashMap<String, Plant> plantList) {
		int [] numbers= new int[2];
		Dragboard db = event.getDragboard();
		if(db.hasString()) {
			String nodeId = db.getString();
			ImageView plant = new ImageView();
			plant.setImage(gardenScreen.plantImageList.get(nodeId));
			System.out.println(nodeId);
			plant.setPreserveRatio(true);
			plant.setFitHeight(100);
			plant.setId(nodeId);
			
			if(plant != null) {
				Plant plantNeeded = plantList.get(nodeId);
				plant.relocate(event.getX()- (plant.getFitHeight()/2), event.getY() - (plant.getFitHeight()/2));
				gardenScreen.gardenPane.getChildren().add(plant);
				numbers[0] = plantNeeded.getLepsSupported();
				numbers[1] = plantNeeded.getPrice();
				return numbers;
			}	
		}
		return numbers;
	}
	public void plantDragOver(DragEvent event) {
		if(event.getGestureSource() != gardenScreen.gardenPane && event.getDragboard().hasString()) {
			event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		}
	}
	
	public Plant deletePlant(EventTarget e, HashMap<String, Plant> plantList) {
		// gardenScreen.gardenPane.getChildren().remove(e);
		ImageView removePlant = (ImageView)e;
		String removedPlantName = removePlant.getId();
		Plant removedPlant = plantList.get(removedPlantName);
		return removedPlant;
	
	}
	public void addPlantToGarden(Collection<Plant> plantCollection) {
		for(Plant p : plantCollection) {
			gardenScreen.gardenTile.getChildren().add(gardenScreen.newPlant(p.getScientificName(), p.getScientificName(),  p.getPrice(),  p.getLepsSupported()));
		}
		
	}
	
	public boolean conditionHasText() {
		boolean hello = (!conditionScreen.budget.getText().isEmpty()) && (!conditionScreen.gardenName.getText().isEmpty());
		System.out.println(hello);
		return hello;
	}
	
	public void setValidBudgetText() {
		conditionScreen.budget.clear();
		conditionScreen.gardenName.clear();
		conditionScreen.budget.setPromptText("Enter a valid budget $");
	}
	
	public int[] returnConditionSliderValue() {
		int [] sliderValue = new int[3];
		sliderValue[0] = (int)conditionScreen.moistSlider.getValue()-1;
		sliderValue[1] = (int)conditionScreen.sunSlider.getValue()-1;
		sliderValue[2] = (int)conditionScreen.soilSlider.getValue()-1;
		return sliderValue;
	}
}