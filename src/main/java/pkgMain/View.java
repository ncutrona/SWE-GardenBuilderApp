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
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * View class from MVC design pattern
 *
 * @author Aidan Chao
 * @author Nicholas Cutrona
 * @author Caleb Davis
 * @author Joey Loporto
 * @author Tommy Cheung
 */
public class View{
	
	private Stage stage, popupStage;
	private Scene screenScene;
	
	static final double WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
	static final double HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();
	
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
	
	/**
	 * default constructor for View object.
	 * calls methods createScreenAndScene and loadScreenToScene.
	 */
	public View() {
		createScreenAndScene();
		
		loadScreenToScene();
	}
	
	/**
	 * Returns the Scene from View
	 * 
	 * @return Scene screenScene from View
	 */
	public Scene getScreen() {
		return screenScene;
	}
	
	/**
	 * sets screenScene to load and returns screenScene.
	 * 
	 * @return Scene screenScene from View, load screen
	 */
	public Scene loadScreenToScene() {
		screenScene = load;
		return screenScene;
	}
	
	/**
	 * sets screenScene to save and returns screenScene
	 * 
	 * @return Scene screenScene from View, save screen
	 */
	public Scene saveScreenToScene() {
		screenScene = save;
		return screenScene;
	}
	
	/**
	 * sets screenScene to condition and returns screenScene
	 * 
	 * @return Scene screenScene from View, condition screen
	 */
	public Scene conditionScreenToScene() {
		screenScene = condition;
		return screenScene;
	}
	
	/**
	 * sets screenScene to garden and returns screenScene
	 * 
	 * @return Scene screenScene from View, garden screen
	 */
	public Scene gardenScreenToScene() {
		screenScene = garden;
		return screenScene;
	}
	
	/**
	 * sets screenScene to inv and returns screenScene
	 * 
	 * @return Scene screenScene from View, inv screen
	 */
	public Scene invScreenToScene() {
		screenScene = inv;
		return screenScene;
	}
	
	/**
	 * sets screenScene to pentagon and returns screenScene
	 * 
	 * @return Scene screenScene from View, pentagon screen
	 */
	public Scene pentagonScreenToScene() {
		screenScene = pentagon;
		return screenScene;
	}
	
	/**
	 * sets screenScene to leps and returns screenScene
	 * 
	 * @return Scene screenScene from View, leps screen
	 */
	public Scene lepScreenToScene() {
		screenScene = leps;
		return screenScene;
	}
	
	/**
	 * sets screenScene to sum and returns screenScene
	 * 
	 * @return Scene screenScene from View, sum screen
	 */
	public Scene sumScreenToScene() {
		screenScene = sum;
		return screenScene;
	}
	
	/**
	 * Creates and sets up the pupupStage, then sets scene to pop and calls showAndWait.
	 */
	public void popScreenToStage() {
		popupStage = new Stage();  			
		popupStage.initModality(Modality.APPLICATION_MODAL);	
		popupStage.setTitle("Options");
		popupStage.setMinWidth(250);
		popupStage.setMinHeight(500);
		popupStage.setScene(pop);
		popupStage.showAndWait(); 
	}
	
	/**
	 * Closes the popupStage
	 */
	public void closePopUp() {
		popupStage.close();
	}
	
	/**
	 * Creates and sets up all of the screens and scenes objects.
	 */
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
	
	/**
	 * Clears the info from all of the screens.
	 */
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
	
	/**
	 * Sets up the summary screen.
	 * 
	 * @param plantDataList HashMap-String, Plant- list of plant data
	 * @param plants HashMap-String, ArrayList-Coordinates-- list of plants and their coordinates on the garden
	 * @param name String name of the garden
	 * @param budget int budget for the garden
	 * @param lepSupported int total leps supported by garden
	 */
	public void setSummaryScreen(HashMap<String, Plant> plantDataList,HashMap<String, ArrayList<Coordinates>> plants,String name, int budget, int lepSupported) {
		HashMap<String, Integer> frequency = sumScreen.findTotal(plants);
		sumScreen.createSummaryScreen(plantDataList, gardenScreen.returnPlantImageList(), frequency, name, budget, lepSupported);
	}
	
	/**
	 * Handles dragging and dropping plants in the garden
	 * 
	 * @param event DragEvent mouse dragging event
	 * @param plantList HashMap-String, Plant- list of available plants
	 * @param length int length of garden
	 * @param width int width of garden
	 * @return double[] moved plant location
	 */
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
	
	/**
	 * Scales size of plant based on garden size and plant size.
	 * 
	 * @param gardenLength int length of garden
	 * @param gardenWidth int width of garden
	 * @param plantLength int length of plant
	 * @param plantWidth int width of plant
	 * @return double[] new dimensions of plant
	 */
	public double[] convertToSize(int gardenLength, int gardenWidth, int plantLength, int plantWidth) {
		double [] dimension = new double[2];
		double min = 45; //hardcode screenSize/500
		
		dimension[0] = plantLength*(HEIGHT / gardenLength);
		dimension[1] = plantWidth*(WIDTH / gardenWidth);
		
		dimension[0] = Math.max(dimension[0], min);
		dimension[1] = Math.max(dimension[1], min);
		
		return dimension;
	}

	/**
	 * Loads plants to the garden from passed in ArrayList
	 * 
	 * @param plantList HashMap-String, Plant- list of available plants
	 * @param plants HashMap-String, ArrayList-Coordinates-- list of plants in the garden and their coordinates
	 * @param length length of the garden
	 * @param width width of the garden
	 */
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
	
	/**
	 * Loads the hexagon shape to the garden view
	 * 
	 * @param hexPoints ArrayList-Double- points to draw the hexagon
	 */
	public void loadHexagonToGarden(ArrayList<Double> hexPoints) {
		gardenScreen.gardenPane.getChildren().addAll(pentagonScreen.setAndGetHexagon(hexPoints));
	}
	
	/**
	 * Event triggered when dragging over plant.
	 * Calls correct transfer mode.
	 * 
	 * @param event event of plant dragging.
	 */
	public void plantDragOver(DragEvent event) {
		if(event.getGestureSource() != gardenScreen.gardenPane && event.getDragboard().hasString()) {
			event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		}
	}
	
	/**
	 * Deletes a plant from Garden view
	 * 
	 * @param e EventTarget plant to delete
	 * @param plantList HashMap-String, Plant- list of available plants
	 * @return Plant removed plant
	 */
	public Plant deletePlant(EventTarget e, HashMap<String, Plant> plantList) {
		ImageView removePlant = (ImageView)e;
		String removedPlantName = removePlant.getId();
		Plant removedPlant = plantList.get(removedPlantName);
		return removedPlant;
	
	}
	
	/**
	 * Adds plants to the garden view from Collection
	 * 
	 * @param plantCollection Collection-Plant- of plants to add
	 */
	public void addPlantToGarden(Collection<Plant> plantCollection) {
		int i = 1;
		for(Plant p : plantCollection) {
			gardenScreen.gardenTile.addRow(i, gardenScreen.newPlantIv(p));
			i++;
		}
		
	}
	
	/**
	 * Checks to see if text or coords are empty in the conditionScreen.
	 * 
	 * @return true if text and coords are not empty in conditionScreen
	 */
	public boolean conditionHasText() {
		boolean hasText = (!conditionScreen.budget.getText().isEmpty()) && (!conditionScreen.gardenName.getText().isEmpty());
		boolean hasCoords = (!conditionScreen.length.getText().isEmpty()) && (!conditionScreen.width.getText().isEmpty());
		return hasText && hasCoords;
	}
	
	/**
	 * Sets the text to be shown on conditionScreen for budget box
	 */
	public void setValidBudgetText() {
		conditionScreen.budget.clear();
		conditionScreen.budget.setPromptText("Enter a valid budget $");
	}
	
	/**
	 * Sets the text to be shown on conditionScreen for name box
	 */
	public void setValidNameText() {
		conditionScreen.gardenName.clear();
		conditionScreen.gardenName.setPromptText("Enter an unused Name");
	}
	
	/**
	 * Gets the values from the conditions sliders on conditionScreen
	 * 
	 * @return int[] slider values for conditions from conditionScreen
	 */
	public int[] returnConditionSliderValue() {
		int [] sliderValue = new int[5];
		sliderValue[0] = (int)conditionScreen.moistSlider.getValue()-1;
		sliderValue[1] = (int)conditionScreen.sunSlider.getValue()-1;
		sliderValue[2] = (int)conditionScreen.soilSlider.getValue()-1;
		return sliderValue;
	}
}