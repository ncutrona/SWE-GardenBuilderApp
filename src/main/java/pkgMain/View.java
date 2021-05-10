package pkgMain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javafx.application.Application;
import javafx.collections.ObservableList;
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
import javafx.scene.transform.Scale;
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
	HexagonScreen hexagonScreen;
	PopUpWindow popup;
	LepMotivationScreen lepScreen;
	SummaryScreen sumScreen;
	LepSupportedScreen lepSupportedScreen;
	
	Scene load, save, condition, garden, inv , pop, pentagon, leps, sum, lepSupport;
	
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
	public Scene hexagonScreenToScene() {
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
	 * sets screenScene to sum Screen and returns screenScene
	 * 
	 * @return Scene screenScene from View, sum screen
	 */
	public Scene sumScreenToScene() {
		screenScene = sum;
		return screenScene;
	}
	
	/**
	 * sets screenScene to LepSupported Screen and returns screenScene
	 * 
	 * @return Scene screenScene from View, lepSupported screen
	 */
	public Scene lepSupportedScreenToScene() {
		screenScene = lepSupport;
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
		hexagonScreen = new HexagonScreen();
		pentagon = new Scene(hexagonScreen.getScreen(), WIDTH, HEIGHT);
		lepScreen = new LepMotivationScreen();
		leps = new Scene(lepScreen.getScreen(), WIDTH, HEIGHT);
		sumScreen = new SummaryScreen();
		sum = new Scene(sumScreen.getScreen(), WIDTH, HEIGHT);
		lepSupportedScreen = new LepSupportedScreen();
		lepSupport = new Scene(lepSupportedScreen.getScreen(), WIDTH, HEIGHT);
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
		conditionScreen.length.clear();
		conditionScreen.width.clear();
		
		lepSupportedScreen.lepInfoTile.getChildren().removeAll(lepSupportedScreen.lepInfoTile.getChildren());
		
		hexagonScreen.pentaGardenPane.getChildren().removeAll(hexagonScreen.pentaGardenPane.getChildren());
		hexagonScreen.setHexagon();
		
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
	public double[] plantDragDropping(DragEvent event, HashMap<String, ArrayList<Coordinates>> plantsInGarden, HashMap<String, Plant> plantList, int width) {
		double [] xAndY = new double[2];
		Dragboard db = event.getDragboard();
		if(db.hasString()) {
			String nodeId = db.getString();
			ImageView plant = new ImageView();
			plant.setImage(gardenScreen.plantImageList.get(nodeId));
			double dimension = convertToSize(width, plantList.get(nodeId).getWidth());
			plant.setFitHeight(dimension);
			plant.setFitWidth(dimension);
			plant.setId(nodeId);
			
			if(plant != null) {
				double x = event.getX()- (plant.getFitWidth()/2);
				double y = event.getY() - (plant.getFitHeight()/2);
				if(isEmptySpace(plantsInGarden, plantList,nodeId, width, event.getX(), event.getY())) {
					plant.setY(y);
					plant.setX(x);
					gardenScreen.gardenPane.getChildren().add(plant);
					xAndY[0] = plant.getX();
					xAndY[1] = plant.getY();
					return xAndY;
				}
			}	
		}
		xAndY[0]= -1.0;
		xAndY[1] = -1.0;
		return xAndY;
	}
	
	/**
	 * Checks to see if there is empty space in garden to place the plant when the user drags it into the garden.
	 * 
	 * @param plants HashMap-String, ArrayList-Coordinates-- plants in the garden and their location
	 * @param plantList HashMap-String, Plant- of available plants
	 * @param plant String plant being dragged
	 * @param gardenWidth int width of the garden
	 * @param x int x position of dragged plant
	 * @param y int y position of dragged plant
	 * @return boolean true if there is space for the plant, false if not
	 */
	public boolean isEmptySpace(HashMap<String, ArrayList<Coordinates>> plants, HashMap<String, Plant> plantList, String plant, int gardenWidth,double x, double y) {
		Plant draggedPlant = plantList.get(plant);
		double width = convertToSize(gardenWidth, draggedPlant.getWidth())/2.0;
		for(String plantName: plants.keySet()) {
			Plant checkingPlant = plantList.get(plantName);
			double tempWidth = convertToSize(gardenWidth, checkingPlant.getWidth())/2.0;
			for(Coordinates c : plants.get(plantName)) {
				double newX = c.getX() + tempWidth;
				double newY = c.getY() + tempWidth;
				//if the plant is being dropped on the left side of the plant being checking
				//if it doesn't goes to continue them it means that it's touching/overlapping
				if (x <  newX) {
					//System.out.println("Right is touching left: " + (x + width > newX - tempWidth));
					if(x + width > newX - tempWidth) {
						//if the bottom if the plant isn't touching the top of the checkedPlant and vice versa
						if((y + width < newY - tempWidth) || (y - width > newY + tempWidth)) {
							continue;
						}
					}else {
						continue;
					}
				}else if (x == newX){
					if((y + width < newY - tempWidth) || (y - width > newY + tempWidth)) {
						continue;
					}
				}else if(x > newX){
					if(x - width < newX + tempWidth) {
						if((y + width < newY - tempWidth) || (y - width > newY + tempWidth)) {
							continue;
						}
					}else {
						continue;
					}
				}
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Scales size of plant based on garden size and plant size.
	 * 
	 * @param gardenWidth int width of garden
	 * @param plantWidth int width of plant
	 * @return double new dimension of plant
	 */
	public double convertToSize(int gardenWidth, int plantWidth) {
		double dimension;
		double min = 45; //hardcode screenSize/500
		
		dimension = plantWidth*(WIDTH / gardenWidth);
		
		dimension = Math.max(dimension, min);
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
				double dimension = convertToSize(width,p.getWidth());
				plantView.setFitWidth(dimension);
				plantView.setFitHeight(dimension);
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
		gardenScreen.gardenPane.getChildren().addAll(fitHexToGarden(hexagonScreen.setAndGetHexagon(hexPoints)));
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
	
	/**
	 * Scales the hex Polygon properly based on the garden's dimensions
	 * 
	 * @param hex Polygon to be scaled
	 * @return scaled Polygon
	 */
	public Polygon fitHexToGarden(Polygon hex) {
		//int Hmax = 750;
		//int Wmax = 1400;
		//double scaleH = Hmax / HEIGHT;
		//double scaleW = Wmax / WIDTH;
		
		double scaleH = (HEIGHT * (10.0/11.0)) / HEIGHT;
		double scaleW = (WIDTH * (13.5/15.0)) / WIDTH;
		
		System.out.println("ScaleH: " + scaleH);
		System.out.println("ScaleW: " + scaleW);
		hex.getTransforms().add(new Scale(scaleW, scaleH));
		return hex;
	}
	
	/**
	 * refreshes inventory screen to display plants alphabetically by scientific name
	 * 
	 * @param aToZ Map-String, Plant- sorted list of available plants
	 */
	public void displayInvAtoZ(Map<String, Plant> aToZ) {
		invScreen.invTile.getChildren().clear();
		invScreen.invTileControls.getChildren().clear();
		invScreen.createInventoryScreen(aToZ, gardenScreen.returnPlantImageList());
	}
	
	
	/**
	 * refreshes inventory screen to display plants sorted by number of lepsSupported
	 * 
	 * @param lepsSupportedSortMap Map-String, Plant- sorted list of available plants by lepsSupported
	 */
	public void displayInvLepsSupported(Map<String, Plant> lepsSupportedSortMap) {
		invScreen.invTile.getChildren().clear();
		invScreen.invTileControls.getChildren().clear();
		invScreen.createInventoryScreen(lepsSupportedSortMap, gardenScreen.returnPlantImageList());
	}
	
	/**
	 * refreshes inventory screen to display plants sorted by soil condition
	 * 
	 * @param aToZ Map-String, Plant- sorted list of available plants by soil condition
	 */
	public void displayInvSoil(Map<String, Plant> soilSortMap) {
		invScreen.invTile.getChildren().clear();
		invScreen.invTileControls.getChildren().clear();
		invScreen.createInventoryScreen(soilSortMap, gardenScreen.returnPlantImageList());
	}
	
	/**
	 * refreshes inventory screen to display plants sorted by sun condition
	 * 
	 * @param aToZ Map-String, Plant- sorted list of available plants by sun condition
	 */
	public void displayInvSun(Map<String, Plant> sunSortMap) {
		invScreen.invTile.getChildren().clear();
		invScreen.invTileControls.getChildren().clear();
		invScreen.createInventoryScreen(sunSortMap, gardenScreen.returnPlantImageList());
	}
}