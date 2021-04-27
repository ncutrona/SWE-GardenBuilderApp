package pkgMain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
	
	Image background = new Image(getClass().getResourceAsStream("/img/grassBknd.jpg"));
	BackgroundImage backgroundimage = new BackgroundImage(background, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT, 
               BackgroundSize.DEFAULT);
	
	TilePane infoTile;
	GridPane gardenTile;
	Pane gardenPane;
	BorderPane gardenBorder;
	ScrollPane plantScroll;
	Text leps, budget, sortedPlants, conditionsDisplay;
	Button inventory, optionsButton, finish;
	HashMap<String, Image> plantImageList = new HashMap<String, Image>();
	
	
	
	public GardenScreen(GardenConditions condition) {
		createPanes();
		createText();
		createButton();
		createScreen(condition.getSun(), condition.getSoil(), condition.getMoisture(), condition.getBudget());
	}
	
	public GardenScreen() {
		createPanes();
		createText();
		createButton();
		createScreen("", "" , "" , 0);
	}
	
	public void updateCondition(GardenConditions condition) {
		setConditionText(condition.getSun(), condition.getSoil(), condition.getMoisture());
		updateLepAndBudget(0, condition.getBudget());
	}
	
	public void createPanes() {
		gardenTile = new GridPane();
		infoTile = new TilePane();
		gardenPane = new Pane();
		gardenBorder = new BorderPane();
		plantScroll = new ScrollPane();
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
		finish = new Button("Finish");
		
	}
	
	public void setConditionText(String sun, String soil, String moist) {
		conditionsDisplay.setText(sun + " | " + moist  + " | " + soil);
	}
	/**
	 * Creates a hashmap of three plant images
	 * 
	 * @param one first plant
	 * @param two second plant
	 * @param three third plant
	 * @return HashMap String, Image of created images
	 */
	
	public void createPlantImageList(ArrayList<Plant> plantsMaster) {
//		plantImageList.put(one, milkweed);
//    	plantImageList.put(two, planttwo);
//    	plantImageList.put(three, plantthree);
		for(Plant plant : plantsMaster) {
<<<<<<< HEAD
			plant.scientificName = plant.scientificName.strip();
			System.out.println(plant.getScientificName());
			Image image = new Image(getClass().getResourceAsStream("/img/" + plant.scientificName.replace(" ", "_") +".png"));
			plantImageList.put(plant.scientificName, image);
=======
			Image image = new Image(getClass().getResourceAsStream("/img/" + plant.getScientificName().replace(" ", "_") +".png"));
			plantImageList.put(plant.getScientificName(), image);
>>>>>>> 9b2f9c3ccbf6dbb9e00d833a58fc807a088c3b7d
			
		}
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
	public ImageView newPlant(Plant p) {
		ImageView iv1;
		Image plantView = plantImageList.get(p.getScientificName());
		
		iv1 = new ImageView();
		iv1.setImage(plantView);
		iv1.setFitWidth(100);
		iv1.setFitHeight(100);
		iv1.setId(p.getScientificName());
		Tooltip t =  new Tooltip("Scientific Name: " +  p.getScientificName() + "\nPrice: $" + p.getScientificName() + "\nLeps: " + p.getLepsSupported());
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
		gardenPane.setPadding(new Insets(10, 10, 10, 10));;
		gardenPane.setBackground(new Background(backgroundimage));
		
		gardenBorder.setCenter(gardenPane); 
		gardenTile.setPadding(new Insets(10, 10, 10, 10));
		gardenTile.setStyle("-fx-background-color: yellow");
		
		gardenTile.getChildren().add(sortedPlants);
		
		plantScroll.setContent(gardenTile);
		gardenBorder.setLeft(plantScroll);
		
		
		infoTile.setPadding(new Insets(10, 10, 10, 10));
		infoTile.setStyle("-fx-background-color: pink;");	

		leps.setText("Leps Supported: " + 0);
		infoTile.getChildren().add(leps);
		
		budget.setText("Budget: $" + budgetNeeded);
		infoTile.getChildren().add(budget);
		
		//Adding Conditions Text
		setConditionText(sun, soil, moisture);
		infoTile.getChildren().add(conditionsDisplay);
		
		//Adding the options button to the top tile pane
		optionsButton.setTooltip(new Tooltip("Tooltip for Button"));
		infoTile.getChildren().add(optionsButton);
		infoTile.getChildren().add(inventory);
		infoTile.getChildren().add(finish);
		
		gardenBorder.setTop(infoTile);
	}
	
}
