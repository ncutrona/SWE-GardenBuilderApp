package pkgMain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
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
            new BackgroundSize(Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight(), true, true, true, true));
	
	private Image lepSupport = new Image(getClass().getResourceAsStream("/img/lepSupport.png"));
	
	
	TilePane infoTile;
	GridPane gardenTile;
	Pane gardenPane;
	BorderPane gardenBorder;
	ScrollPane plantScroll;
	Text leps, budget, sortedPlants, conditionsDisplay, gardenName, dimension;
	Button inventory, optionsButton, finish, lepsSupported;
	HashMap<String, Image> plantImageList = new HashMap<String, Image>();
	
	
	/**
	 * creates new GardenScreen object with conditions from params
	 * 
	 * @param condition GardenConditions to display
	 */
	public GardenScreen(GardenConditions condition, GardenState state) {
		createPanes();
		createText();
		createButton();
		createScreen(condition.getSun(), condition.getSoil(), condition.getMoisture(), condition.getBudget(), state.getGardenName());
	}
	
	/**
	 * Creates new GardenScreen object with default conditions
	 */
	public GardenScreen() {
		createPanes();
		createText();
		createButton();
		createScreen("", "" , "" , 0, "");
	}
	
	/**
	 * updates the condition text and lep/budget count
	 * 
	 * @param condition GardenConditions to update
	 */
	public void updateScreen(GardenConditions condition, GardenState state) {
		setConditionText(condition.getSun(), condition.getSoil(), condition.getMoisture());
		updateLepAndBudget(0, condition.getBudget());
		dimension.setText("Dimension: " + condition.getLength()  + "ft X " + condition.getWidth() + "ft");
		gardenName.setText(state.getGardenName());
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
		leps.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		leps.setFill(Color.GREEN);
		leps.setStrokeWidth(0.2);
		leps.setStroke(Color.BLUE);
		
		budget = new Text();
		budget.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
		budget.setFill(Color.BLUE);
		
		sortedPlants = new Text("Sorted Plants");
		sortedPlants.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
		sortedPlants.setFill(Color.BLUE);
		
		
		conditionsDisplay = new Text();
		conditionsDisplay.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
		conditionsDisplay.setFill(Color.PURPLE);
		
		
		gardenName = new Text();
		gardenName.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
		gardenName.setFill(Color.PURPLE);
		
		dimension = new Text();
		dimension.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
		dimension.setFill(Color.BLUEVIOLET);
		
	}
	
	/**
	 * Creates necessary buttons
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
		lepsSupported = new Button ("Leps Supported");
		lepsSupported.setStyle("-fx-padding: 8 15 15 15; -fx-background-insets: "
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
		
		if(sun == "partial") {
			sun = "Sun: Partial Sun";
		}
		else if(sun == "full") {
			sun = "Sun: Full Sun";
		}
		else {
			sun = "Sun: Shade";
		}
		
		if(soil == "clay") {
			soil = "Soil: Clay";
		}
		else if(soil == "loam") {
			soil = "Soil: Loam";
		}
		else {
			soil = "Soil: Sand";
		}
		
		if(moist == "dry") {
			moist = "Moisture: Dry";
		}
		else if(moist == "wet") {
			moist = "Moisture: Wet";
		}
		else {
			moist = "Moisture: Medium";
		}
		
		
		conditionsDisplay.setText(sun + " | " + moist  + " | " + soil);
	}
	
	
	/**
	 * Creates plantImageList from plantsMaster ArrayList
	 * 
	 * @param plantsMaster ArrayList-Plant- used to create plantImageList
	 */
	public void createPlantImageList(ArrayList<Plant> plantsMaster) {;
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
			budget.setFill(Color.BLUE);
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
	public void createScreen(String sun, String soil, String moisture, int budgetNeeded, String name) {
		
		HBox infoTileBox = new HBox(20);
		
		VBox infoFinalBox = new VBox(10);
		infoFinalBox.getChildren().addAll(infoTileBox);
		infoFinalBox.setPadding(new Insets(5.0, 5.0, 5.0, 5.0));
		
		
		gardenBorder.setStyle("-fx-background-color: white;");
		gardenPane.setPadding(new Insets(10, 10, 10, 10));;
		gardenPane.setBackground(new Background(backgroundimage));
		
		gardenBorder.setCenter(gardenPane); 
		gardenTile.setPadding(new Insets(10, 10, 10, 10));
		gardenTile.setStyle("-fx-background-color: #e1fcc5");
		//#d8f2d9 #aee8b1 #ededcc
		gardenTile.getChildren().add(sortedPlants);
		
		plantScroll.setContent(gardenTile);
		gardenBorder.setLeft(plantScroll);
		
		
		//infoTile.setPadding(new Insets(10, 10, 10, 10));
		infoTile.setStyle("-fx-background-color: #ffdede");	

		gardenName.setText("Garden 1");
		
		dimension.setText("Dimension: " + 0  + "ft X " + 0 + "ft");
		leps.setText("Leps Supported: " + 0);
		budget.setText("Budget: $" + budgetNeeded);

		//Adding Conditions Text
		setConditionText(sun, soil, moisture);
	
		//Adding the options button to the top tile pane
		optionsButton.setTooltip(new Tooltip("Tooltip for Button"));
		infoTileBox.getChildren().addAll(gardenName, dimension, leps, budget, conditionsDisplay, optionsButton, inventory, lepsSupported, finish);
		infoTileBox.setAlignment(Pos.CENTER);
		infoFinalBox.setAlignment(Pos.CENTER);
		infoTile.getChildren().add(infoFinalBox);
		infoTile.setAlignment(Pos.CENTER);
		
		gardenBorder.setTop(infoTile);
	}
	
}
