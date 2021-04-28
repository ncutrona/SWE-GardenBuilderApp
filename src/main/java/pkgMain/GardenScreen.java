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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
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
            new BackgroundSize(Screen.getPrimary().getVisualBounds().getHeight(), Screen.getPrimary().getVisualBounds().getWidth(), true, true, true, true));
	
	TilePane infoTile;
	GridPane gardenTile;
	Pane gardenPane;
	BorderPane gardenBorder;
	ScrollPane plantScroll;
	Text leps, budget, sortedPlants, conditionsDisplay;
	Button inventory, optionsButton, finish;
	HashMap<String, Image> plantImageList = new HashMap<String, Image>();
	
	
	/**
	 * creates new GardenScreen object with conditions from params
	 * 
	 * @param condition GardenConditions to display
	 */
	public GardenScreen(GardenConditions condition) {
		createPanes();
		createText();
		createButton();
		createScreen(condition.getSun(), condition.getSoil(), condition.getMoisture(), condition.getBudget());
	}
	
	/**
	 * Creates new GardenScreen object with default conditions
	 */
	public GardenScreen() {
		createPanes();
		createText();
		createButton();
		createScreen("", "" , "" , 0);
	}
	
	/**
	 * updates the condition text and lep/budget count
	 * 
	 * @param condition GardenConditions to update
	 */
	public void updateCondition(GardenConditions condition) {
		setConditionText(condition.getSun(), condition.getSoil(), condition.getMoisture());
		updateLepAndBudget(0, condition.getBudget());
	}
	
	/**
	 * Creates the necassary panes for GardenScreen
	 */
	public void createPanes() {
		gardenTile = new GridPane();
		infoTile = new TilePane();
		gardenPane = new Pane();
		gardenBorder = new BorderPane();
		plantScroll = new ScrollPane();
	}
	
	/**
	 * Creates text boxes to display
	 */
	public void createText() {
		leps = new Text();
		budget = new Text();
		sortedPlants = new Text("Sorted Plants");
		conditionsDisplay = new Text();
	}
	
	/**
	 * Creates necassary buttons
	 */
	public void createButton() {
		inventory = new Button("See Full Inventory");
		inventory.setStyle("-fx-padding: 8 15 15 15; -fx-background-insets: "
				+ "0,0 0 5 0, 0 0 6 0, 0 0 7 0; -fx-background-radius: "
				+ "8; -fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #add8e6 0%, #add8e6 100%),"
				+ "#add8e6,#add8e6,radial-gradient(center 50% 50%, radius 100%, #add8e6, #add8e6);"
				+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );-fx-font-weight: bold; -fx-font-size: 1.1em;");
		optionsButton = new Button("Options");
		optionsButton.setStyle("-fx-padding: 8 15 15 15; -fx-background-insets: "
				+ "0,0 0 5 0, 0 0 6 0, 0 0 7 0; -fx-background-radius: "
				+ "8; -fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #add8e6 0%, #add8e6 100%),"
				+ "#add8e6,#add8e6,radial-gradient(center 50% 50%, radius 100%, #add8e6, #add8e6);"
				+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );-fx-font-weight: bold; -fx-font-size: 1.1em;");
		finish = new Button("Finish");
		finish.setStyle("-fx-padding: 8 15 15 15; -fx-background-insets: "
				+ "0,0 0 5 0, 0 0 6 0, 0 0 7 0; -fx-background-radius: "
				+ "8; -fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #add8e6 0%, #add8e6 100%),"
				+ "#add8e6,#add8e6,radial-gradient(center 50% 50%, radius 100%, #add8e6, #add8e6);"
				+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );-fx-font-weight: bold; -fx-font-size: 1.1em;");
		
	}
	
	/**
	 * Sets the condition text from params
	 * 
	 * @param sun String sun condition
	 * @param soil String soil condition
	 * @param moist String moisture condition
	 */
	public void setConditionText(String sun, String soil, String moist) {
		conditionsDisplay.setText(sun + " | " + moist  + " | " + soil);
	}
	
	
	/**
	 * Creates plantImageList from plantsMaster ArrayList
	 * 
	 * @param plantsMaster ArrayList-Plant- used to create plantImageList
	 */
	public void createPlantImageList(ArrayList<Plant> plantsMaster) {
//		plantImageList.put(one, milkweed);
//    	plantImageList.put(two, planttwo);
//    	plantImageList.put(three, plantthree);
		for(Plant plant : plantsMaster) {
			plant.setScientificName(plant.getScientificName().strip());
			Image image = new Image(getClass().getResourceAsStream("/img/" + plant.getScientificName().replace(" ", "_") +".png"));
			plantImageList.put(plant.getScientificName(), image);
			
		}
	}
	
	/**
	 * returns the plantImageList
	 * 
	 * @return HashMap-String, Image-, a list of plant images
	 */
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
		if(newBudget < 0) {
			budget.setFill(Color.RED);
		}
		
		else {
			budget.setFill(Color.BLACK);
		}
		budget.setText("Budget: $" + newBudget);	
	}
	
	
	/**
	 * returns the BorderPane for GardenScreen
	 * 
	 * @return BorderPane for GardenScreen
	 */
	public BorderPane getScreen() {
		return gardenBorder;
	}
	
	
	/**
	 * Returns an ImageView of a new plant from plantImages
	 * 
	 * @param p Plant to return ImageView of
	 * @return ImageView of new plant
	 */
	public ImageView newPlantIv(Plant p) {
		ImageView iv1;
		Image plantView = plantImageList.get(p.getScientificName());
		
		iv1 = new ImageView();
		iv1.setImage(plantView);
		iv1.setFitWidth(100);
		iv1.setFitHeight(100);
		iv1.setId(p.getScientificName());
		Tooltip t =  new Tooltip("Scientific Name: " +  p.getScientificName() + "\nPrice: $" + p.getPrice() + "\nLeps: " + p.getLepsSupported());
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
	 * Formatting for how GardenScreen is displayed.
	 * Sets styling, padding etc for Panes, Tiles.
	 * Sets Conditions text. options button.
	 * 
	 * @param sun String current sun condition
	 * @param soil String current soil condition
	 * @param moisture String current moisture condition
	 * @param budgetNeeded int budget needed
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
