package pkgMain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * GardenScreen class.
 * Handles formatting of Garden Screen.
 * Sources image file paths.
 * 
 * @author Aidan Chao
 * @author Nicholas Cutrona
 * @author Caleb Davis
 * @author Joey Loporto
 * @author Tommy Cheung
 */
public class GardenScreen {
	
	Image background = new Image(getClass().getResourceAsStream("/img/bkdirt.png"));
	BackgroundImage backgroundimage = new BackgroundImage(background, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT, 
               BackgroundSize.DEFAULT);
	
	Image milkweed = new Image(getClass().getResourceAsStream("/img/commonMilkweed.png"));
	Image planttwo = new Image(getClass().getResourceAsStream("/img/planttwo.png"));
	Image plantthree = new Image(getClass().getResourceAsStream("/img/plantthree.png"));

	String NodeId = "NULL";
	
	TilePane gardenTile, gardenTileTwo;
	FlowPane gardenFlow;
	BorderPane gardenBorder;
	Text leps, budget, sortedPlants, conditionsDisplay;
	Button inventory, optionsButton;
	HashMap<String, Image> plantImageList = new HashMap<String, Image>();
	
	public GardenScreen(GardenConditions condition) {
		createTiles();
		createText();
		createButton();
		createScreen(condition.getSun(), condition.getSoil(), condition.getMoisture(), condition.getBudget());
	}
	
	public GardenScreen() {
		createTiles();
		createText();
		createButton();
		createScreen("", "" , "" , 0);
	}
	
	public void updateCondition(GardenConditions condition) {
		setConditionText(condition.getSun(), condition.getSoil(), condition.getMoisture());
		updateLepAndBudget(0, condition.getBudget());
	}
	
	public void createTiles() {
		gardenTile = new TilePane();
		gardenTileTwo = new TilePane();
		gardenFlow = new FlowPane();
		gardenBorder = new BorderPane();
	}
	
	public void createText() {
		leps = new Text();
		budget = new Text();
		sortedPlants = new Text("Sorted Plants");
		conditionsDisplay = new Text();
	}
	
	public void createButton() {
		inventory = new Button("See Full Inventory");
		optionsButton = new Button("Options");
	}
	
	public void setConditionText(String sun, String soil, String moist) {
		conditionsDisplay.setText(sun + " | " + soil  + " | " + moist);
	}
	/**
	 * Creates a hashmap of three plant images
	 * 
	 * @param one first plant
	 * @param two second plant
	 * @param three third plant
	 * @return HashMap String, Image of created images
	 */
	
	public void createPlantImageList(String one, String two, String three) {
		plantImageList.put(one, milkweed);
    	plantImageList.put(two, planttwo);
    	plantImageList.put(three, plantthree);
	}
	
	public HashMap<String, Image> returnPlantImageList() {
		return plantImageList;
	}
	
	/**
	 * Updates what is displayed by GardenScreen
	 * 
	 * @param newLeps int increments number of leps
	 * @param newBudget int increments budget
	 */
	public void updateLepAndBudget(int newLeps, int newBudget) {
		leps.setText("Leps Supported: " + newLeps);
		budget.setText("Budget: $" + newBudget);
			
	}
	
	public BorderPane getScreen() {
		return gardenBorder;
	}
	
	
	/**
	 * Returns an ImageView of a new plant from plantImages
	 * 
	 * @param NodeID String NodeID of plantImages for the plant image
	 * @param plantImages ArrayList of plant images accessed by NodeID
	 * @param name the name of the plant
	 * @param price the price of the plant
	 * @param lepsSupported the number of leps supported
	 * @return ImageView of new plant
	 */
	public ImageView newPlant(String NodeID, String name, int price, int lepsSupported) {
		ImageView iv1;
		Image plantView = plantImageList.get(NodeID);
		
		iv1 = new ImageView();
		iv1.setImage(plantView);
		iv1.setPreserveRatio(true);
		iv1.setFitHeight(100);
		iv1.setId(NodeID);
		Tooltip t =  new Tooltip("Scientific Name: " + name + "\nPrice: $" + price + "\nLeps: " + lepsSupported);
		Tooltip.install(iv1, t);
		
		iv1.setOnDragDetected(new EventHandler<MouseEvent>(){
			
			public void handle(MouseEvent event) {				
				Dragboard db = iv1.startDragAndDrop(TransferMode.COPY);
				ClipboardContent content = new ClipboardContent();
				content.putString(iv1.getId());
				db.setContent(content);
				event.consume();
				System.out.println(iv1.getId());
			}
		});
    	
		
		return iv1;
					
	}
	
	
	/**
	 * Creates the BorderPane for GardenScreen
	 * 
	 * @param ConditionSun String current sun condition
	 * @param ConditionSoil String current soil condition
	 * @param ConditionMoisture String current moisture condition
	 * @param plantImages hashmap of plantImages
	 * @param lepsNeeded int number of leps needed
	 * @param budgetNeeded int budget needed
	 * @return BorderPane for GardenScreen
	 */
	public void createScreen(String sun, String soil, String moisture, int budgetNeeded) {
		gardenBorder.setStyle("-fx-background-color: white;");
		gardenFlow.setPadding(new Insets(10, 10, 10, 10));;
		gardenFlow.setBackground(new Background(backgroundimage));
		
		gardenBorder.setCenter(gardenFlow); 
		gardenTile.setPadding(new Insets(10, 10, 10, 10));
		gardenTile.setStyle("-fx-background-color: yellow");
		
		gardenTile.getChildren().add(sortedPlants);
		
		gardenBorder.setLeft(gardenTile);
		gardenTileTwo.setPadding(new Insets(10, 10, 10, 10));
		gardenTileTwo.setStyle("-fx-background-color: pink;");	

		leps.setText("Leps Supported: " + 0);
		gardenTileTwo.getChildren().add(leps);
		
		budget.setText("Budget: $" + budgetNeeded);
		gardenTileTwo.getChildren().add(budget);
		
		//Adding Conditions Text
		setConditionText(sun, soil, moisture);
		gardenTileTwo.getChildren().add(conditionsDisplay);
		
		//Adding the options button to the top tile pane
		optionsButton.setTooltip(new Tooltip("Tooltip for Button"));
		gardenTileTwo.getChildren().add(optionsButton);
		gardenTileTwo.getChildren().add(inventory);
		
		gardenBorder.setTop(gardenTileTwo);
	}
	
	
	
	
	
	
}
