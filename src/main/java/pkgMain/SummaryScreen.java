package pkgMain;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.*;

/**
 * SummaryScreen class.
 * Formats the Summary Screen.
 * Displays a summary of information for the garden.
 * 
 * @author Aidan Chao
 * @author Nicholas Cutrona
 * @author Caleb Davis
 * @author Joey Loporto
 * @author Tommy Cheung
 */
public class SummaryScreen {
	
	private Image background = new Image(getClass().getResourceAsStream("/img/bkdirt.png"));
	private BackgroundImage backgroundimage = new BackgroundImage(background, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT, 
            BackgroundSize.DEFAULT);
	
	ScrollPane dataPane;
	BorderPane summaryBorder;
	StackPane Data;
	Button returnHome, printInfo;
	
	Text name,budget, leps;
	
	/**
	 * Default constructor for SummaryScreen object.
	 * Creates Panes and Buttons.
	 */
	public SummaryScreen() {
		createPanes();
		createButton();
	}
	
	/**
	 * Creates the BorderPane, ScrollPane, and StackPane for SummaryScreen
	 */
	public void createPanes() {
		summaryBorder = new BorderPane();
		dataPane = new ScrollPane();
		Data = new StackPane();
	}
	
	/**
	 * Creates the return home and print info button for SummaryScreen
	 */
	public void createButton() {
		returnHome = new Button("Return Home");
		printInfo = new Button("Print Info");
	}
	
	/**
	 * Returns the BorderPane for SummaryScreen
	 * 
	 * @return BorderPane for SummaryScreen
	 */
	public BorderPane getScreen() {
		return summaryBorder;
	}
	
	
	/**
	 * Creates the tiledate from passed in args.
	 * Sets font, adds text to HBox.
	 * 
	 * @param gardenName String name of garden
	 * @param remainingBudget int budget remaining after plants are purchased
	 * @param finalLeps int final total lep count for the garden
	 */
	public void createTileData(String gardenName, int remainingBudget, int finalLeps) {
		
		name = new Text("Name: " + gardenName + " ");
		budget = new Text("Remaining Budget: $" + remainingBudget + " ");
		leps = new Text("Total Garden Leps Supported: " + finalLeps);
		
		name.setFont(Font.font ("Verdana", 15));
		name.setFill(Color.WHITE);
		budget.setFont(Font.font ("Verdana", 15));
		budget.setFill(Color.WHITE);
		leps.setFont(Font.font ("Verdana", 15));
		leps.setFill(Color.WHITE);
		
		
		HBox hbox = new HBox(20);
		hbox.getChildren().addAll(name, budget, leps, returnHome, printInfo);
		Data.getChildren().add(hbox);
		hbox.setAlignment(Pos.CENTER);
		
		Data.setStyle("-fx-background-color: blue;");
		
	}
	
	/**
	 * Returns the filled and formatted BorderPane for SummaryScreen
	 * 
	 * @param plantData HashMap<String, Plant> data for plants
	 * @param imageData HashMap<String, Image> images for plants
	 * @param dataFrequency HashMap<String, Integer> frequency of plants in garden
	 * @param gardenName String name of the garden
	 * @param remainingBudget int remaining budget
	 * @param finalLeps int final lep count
	 * @return BorderPane for SummaryScreen
	 */
	public BorderPane createSummaryScreen(HashMap<String, Plant> plantData, HashMap<String, Image> imageData, HashMap<String, Integer> dataFrequency, String gardenName, int remainingBudget, int finalLeps) {
		createScreen(plantData, imageData, dataFrequency);
		createTileData(gardenName, remainingBudget, finalLeps);
		
		summaryBorder.setCenter(dataPane);
		summaryBorder.setTop(Data);
		summaryBorder.setStyle("-fx-background-color: Pink;");
		
		return summaryBorder;
		
		
	}
	
	//Need to call this when handler is switched to this screen
	/**
	 * Creates the SummaryScreen and fills with information to display based on garden data passed in.
	 * 
	 * @param plantData HashMap<String, Plant> data of plants
	 * @param imageData HashMap<String, Image> images of plants
	 * @param dataFrequency HashMap<String, Integer> frequency of plants in garden
	 */
	public void createScreen(HashMap<String, Plant> plantData, HashMap<String, Image> imageData, HashMap<String, Integer> dataFrequency) {
		
		VBox vbox = new VBox(15);
		
		Map<String, Integer> sortedMap = new TreeMap<String, Integer>(dataFrequency);
		
		for (Map.Entry mapElement : sortedMap.entrySet()) {
			
			String key = (String)mapElement.getKey();
			int value = (int)mapElement.getValue();
			String valueText = String.valueOf(value);
			
			Image plantImage = imageData.get(key);
			ImageView plantImageIv = new ImageView(plantImage);
			plantImageIv.setPreserveRatio(true);
			plantImageIv.setFitHeight(100);
			
			Plant plant = plantData.get(key);
			
			Text name = new Text(key);
			Text quantity = new Text("Quantity: " + valueText);
			
			//Total Spent
			Text totalSpent = new Text("Total Spent: $" + String.valueOf(plant.getPrice() * value));
			//Leps Supported
			Text lepsSupported = new Text("Leps Supported: " + String.valueOf(plant.getLepsSupported() * value));
			
			//Vbox adding to scroll
			HBox hbox = new HBox(10);
			hbox.getChildren().addAll(plantImageIv, name, quantity, totalSpent, lepsSupported);
			vbox.getChildren().add(hbox);

		}
		
		dataPane.setContent(vbox);
		dataPane.setStyle("-fx-background: Pink;");
		
		
		
	}
	
	/**
	 * Finds the number of each plant in the garden from the list of coordinates for plants.
	 * 
	 * @param names ArrayList<Coordinates>> of plants in garden
	 * @return HashMap<String, Integer> HashMap of plants in the garden and their count
	 */
	public HashMap<String, Integer> findTotal(HashMap<String, ArrayList<Coordinates>> names) {

		HashMap<String, Integer> data = new HashMap<String, Integer>();
		
		for (String s: names.keySet()) {
			data.put(s, names.get(s).size());
		}
		return data;
		
	}
	
	/**
	 * Clears the data in the SummaryScreen
	 */
	public void clearSumScreen() {
		dataPane.setContent(null);
		Data.getChildren().clear();

	}
	

}
