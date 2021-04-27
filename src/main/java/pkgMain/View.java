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
	
	static final double WIDTH = 1000;
	static final double HEIGHT = 700;
	
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
		load = new Scene(loadScreen.getScreen(), WIDTH, HEIGHT);
		saveScreen = new SaveScreen();
		save = new Scene(saveScreen.getScreen(),WIDTH, HEIGHT);
		conditionScreen = new ConditionScreen();
		condition = new Scene(conditionScreen.getScreen(),WIDTH, HEIGHT);
		gardenScreen = new GardenScreen();
		garden = new Scene(gardenScreen.getScreen(), WIDTH, HEIGHT);
		invScreen = new InvScreen();
		inv = new Scene(invScreen.getScreen(),WIDTH, HEIGHT);
		popup = new PopUpWindow();
		pop = new Scene(popup.getScreen());
		pentagonScreen = new PentagonScreen();
		pentagon = new Scene(pentagonScreen.getScreen(), WIDTH, HEIGHT);
		lepScreen = new LepMotivationScreen();
		leps = new Scene(lepScreen.getScreen(), WIDTH, HEIGHT);
		sumScreen = new SummaryScreen();
		sum = new Scene(sumScreen.getScreen(), WIDTH, HEIGHT);
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
	
	public void setSummaryScreen(HashMap<String, Plant> plantDataList,HashMap<String, ArrayList<Coordinates>> plants,String name, int budget, int lepSupported) {
		HashMap<String, Integer> frequency = sumScreen.findTotal(plants);
		sumScreen.createSummaryScreen(plantDataList, gardenScreen.returnPlantImageList(), frequency, name, budget, lepSupported);
	}
	
	public double[] plantDragDropping(DragEvent event, HashMap<String, Plant> plantList, int length, int width) {
		double [] xAndY = new double[2];
		Dragboard db = event.getDragboard();
		if(db.hasString()) {
			String nodeId = db.getString();
			ImageView plant = new ImageView();
			plant.setImage(gardenScreen.plantImageList.get(nodeId));
			double[] dimensions = convertToSize(length, width, plantList.get(nodeId).getHeight(), plantList.get(nodeId).getWidth());
			plant.setFitHeight(dimensions[0]);
			plant.setFitWidth(dimensions[1]);
			plant.setId(nodeId);
			
			if(plant != null) {
				Plant plantNeeded = plantList.get(nodeId);
				double x = event.getX()- (plant.getFitWidth()/2);
				double y = event.getY() - (plant.getFitHeight()/2);
				plant.setY(y);
				plant.setX(x);
				gardenScreen.gardenPane.getChildren().add(plant);
				xAndY[0] = plant.getX();
				xAndY[1] = plant.getY();
				return xAndY;
			}	
		}
		return xAndY;
	}
	
	public double[] convertToSize(int gardenLength, int gardenWidth, int plantLength, int plantWidth) {
		double [] dimension = new double[2];
		
		dimension[0] = plantLength*(HEIGHT / gardenLength);
		dimension[1] = plantWidth*(WIDTH / gardenWidth);
		
		return dimension;
	}

	public void loadPlantsToGarden(HashMap<String, Plant> plantList, HashMap<String, ArrayList<Coordinates>> plants, int length, int width) {
		for(String plantName: plants.keySet()) {
			Plant p = plantList.get(plantName);
			for(Coordinates c: plants.get(plantName)) {
				Image plantImage = gardenScreen.plantImageList.get(plantName);
				ImageView plantView = new ImageView(plantImage);
				double[] dimension = convertToSize(length, width, p.getHeight(), p.getWidth());
				plantView.setFitWidth(dimension[1]);
				plantView.setFitHeight(dimension[0]);
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
			gardenScreen.gardenTile.addRow(i, gardenScreen.newPlant(p));
			i++;
		}
		
	}
	
	public boolean conditionHasText() {
		boolean hasText = (!conditionScreen.budget.getText().isEmpty()) && (!conditionScreen.gardenName.getText().isEmpty());
		boolean hasCoords = (!conditionScreen.length.getText().isEmpty()) && (!conditionScreen.width.getText().isEmpty());
		return hasText && hasCoords;
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
		int [] sliderValue = new int[5];
		sliderValue[0] = (int)conditionScreen.moistSlider.getValue()-1;
		sliderValue[1] = (int)conditionScreen.sunSlider.getValue()-1;
		sliderValue[2] = (int)conditionScreen.soilSlider.getValue()-1;
		return sliderValue;
	}
}