package pkgMain;

import java.util.ArrayList;
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
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class View{
	
	private Stage stage, popupStage;
	private Scene screenScene;
	
	static final double HEIGHT = 1000;
	static final double WIDTH = 1000;
	
	LoadScreen loadScreen;
	ConditionScreen conditionScreen;
	GardenScreen gardenScreen;
	InvScreen invScreen;
	SaveScreen saveScreen;
	PentagonScreen pentagonScreen;
	PopUpWindow popup;
	LepMotivationScreen lepScreen;
	SummaryScreen sumScreen;
	
	Scene load, save, condition, garden, inv , pop, pentagon, leps, sum;
	
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
	public Scene pentagonScreenToScene() {
		screenScene = pentagon;
		return screenScene;
	}
	
	public Scene lepScreenToScene() {
		screenScene = leps;
		return screenScene;
	}
	public Scene sumScreenToScene() {
		screenScene = sum;
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
		load = new Scene(loadScreen.getScreen(), HEIGHT, WIDTH);
		saveScreen = new SaveScreen();
		save = new Scene(saveScreen.getScreen(),HEIGHT, WIDTH);
		conditionScreen = new ConditionScreen();
		condition = new Scene(conditionScreen.getScreen(), HEIGHT, WIDTH);
		gardenScreen = new GardenScreen();
		garden = new Scene(gardenScreen.getScreen(), HEIGHT, WIDTH);
		invScreen = new InvScreen();
		inv = new Scene(invScreen.getScreen(),HEIGHT, WIDTH);
		popup = new PopUpWindow();
		pop = new Scene(popup.getScreen());
		pentagonScreen = new PentagonScreen();
		pentagon = new Scene(pentagonScreen.getScreen(), HEIGHT, WIDTH);
		lepScreen = new LepMotivationScreen();
		leps = new Scene(lepScreen.getScreen(), HEIGHT, WIDTH);
		sumScreen = new SummaryScreen();
		sum = new Scene(sumScreen.getScreen(), HEIGHT, WIDTH);
	}
	
	public void clearInfo() {
		conditionScreen.budget.clear();
		conditionScreen.gardenName.clear();
		gardenScreen.gardenPane.getChildren().removeAll(gardenScreen.gardenPane.getChildren());
		conditionScreen.budget.setPromptText("Enter your Budget $");
		conditionScreen.gardenName.setPromptText("Enter Your Garden Name:");
		conditionScreen.soilSlider.setValue(1);
		conditionScreen.sunSlider.setValue(1);
		conditionScreen.moistSlider.setValue(1);
		
		pentagonScreen.pentaGardenPane.getChildren().removeAll(pentagonScreen.pentaGardenPane.getChildren());
		pentagonScreen.setHexagon();
		
	}
	
	public void setSummaryScreen(HashMap<String, Plant> plantDataList,String name, int budget, int lepSupported) {
		HashMap<String, Integer> frequency = sumScreen.findTotal(gardenScreen.addedPlants);
		sumScreen.createSummaryScreen(plantDataList, gardenScreen.returnPlantImageList(), frequency, name, budget, lepSupported);
	}
	
	public int[] plantDragDropping(DragEvent event, HashMap<String, Plant> plantList) {
		int [] numbers= new int[2];
		Dragboard db = event.getDragboard();
		if(db.hasString()) {
			String nodeId = db.getString();
			ImageView plant = new ImageView();
			plant.setImage(gardenScreen.plantImageList.get(nodeId));
			plant.setPreserveRatio(true);
			plant.setFitHeight(100);
			plant.setId(nodeId);
			
			
			if(plant != null) {
				Plant plantNeeded = plantList.get(nodeId);
				double x = event.getX()- (plant.getFitHeight()/2);
				double y = event.getY() - (plant.getFitHeight()/2);
				plant.setY(y);
				plant.setX(x);
				gardenScreen.gardenPane.getChildren().add(plant);
				numbers[0] = plantNeeded.getLepsSupported();
				numbers[1] = plantNeeded.getPrice();
				gardenScreenAddPlant(nodeId, x, y);
				return numbers;
			}	
		}
		return numbers;
	}
	public void gardenScreenAddPlant(String nodeID, double x, double y) {
		if(!gardenScreen.addedPlants.containsKey(nodeID)) {
			gardenScreen.addedPlants.put(nodeID, new ArrayList<Coordinates>());
		}
		gardenScreen.addedPlants.get(nodeID).add(new Coordinates(x,y));
	}

	public void loadPlantsToGarden() {
		for(String plantName: gardenScreen.addedPlants.keySet()) {
			for(Coordinates c: gardenScreen.addedPlants.get(plantName)) {
				Image plantImage = gardenScreen.plantImageList.get(plantName);
				ImageView plantView = new ImageView(plantImage);
				plantView.setPreserveRatio(true);
				plantView.setFitHeight(100);
				plantView.setId(plantName);
				plantView.setX(c.getX());
				plantView.setY(c.getY());
				gardenScreen.gardenPane.getChildren().add(plantView);
			}
			
		}
	}
	public void loadHexagonToGarden(ArrayList<Double> hexPoints) {
		gardenScreen.gardenPane.getChildren().addAll(pentagonScreen.setAndGetHexagon(hexPoints));
	}
	
	public void plantDragOver(DragEvent event) {
		if(event.getGestureSource() != gardenScreen.gardenPane && event.getDragboard().hasString()) {
			event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		}
	}
	
	public Plant deletePlant(EventTarget e, HashMap<String, Plant> plantList) {
		ImageView removePlant = (ImageView)e;
		String removedPlantName = removePlant.getId();
		Plant removedPlant = plantList.get(removedPlantName);
		return removedPlant;
	
	}
	public void addPlantToGarden(Collection<Plant> plantCollection) {
		int i = 1;
		for(Plant p : plantCollection) {
			gardenScreen.gardenTile.addRow(i, gardenScreen.newPlant(p.getScientificName(), p.getScientificName(),  p.getPrice(),  p.getLepsSupported()));
			i++;
		}
		
	}
	
	public boolean conditionHasText() {
		boolean hasText = (!conditionScreen.budget.getText().isEmpty()) && (!conditionScreen.gardenName.getText().isEmpty());
		return hasText;
	}
	
	public void setValidBudgetText() {
		conditionScreen.budget.clear();
		conditionScreen.budget.setPromptText("Enter a valid budget $");
	}
	public void setValidNameText() {
		conditionScreen.gardenName.clear();
		conditionScreen.gardenName.setPromptText("Enter an unused Name");
	}
	
	public int[] returnConditionSliderValue() {
		int [] sliderValue = new int[3];
		sliderValue[0] = (int)conditionScreen.moistSlider.getValue()-1;
		sliderValue[1] = (int)conditionScreen.sunSlider.getValue()-1;
		sliderValue[2] = (int)conditionScreen.soilSlider.getValue()-1;
		return sliderValue;
	}
}