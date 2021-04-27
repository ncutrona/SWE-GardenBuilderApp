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
	
	public SummaryScreen() {
		createPanes();
		createButton();
	}
	
	public void createPanes() {
		summaryBorder = new BorderPane();
		dataPane = new ScrollPane();
		Data = new StackPane();
	}
	
	public void createButton() {
		returnHome = new Button("Return Home");
		printInfo = new Button("Print Info");
	}
	
	public BorderPane getScreen() {
		return summaryBorder;
	}
	
	
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
	
	public BorderPane createSummaryScreen(HashMap<String, Plant> plantData, HashMap<String, Image> imageData, HashMap<String, Integer> dataFrequency, String gardenName, int remainingBudget, int finalLeps) {
		createScreen(plantData, imageData, dataFrequency);
		createTileData(gardenName, remainingBudget, finalLeps);
		
		summaryBorder.setCenter(dataPane);
		summaryBorder.setTop(Data);
		summaryBorder.setStyle("-fx-background-color: Pink;");
		
		return summaryBorder;
		
		
	}
	
	//Need to call this when handler is switched to this screen
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
	
	public HashMap<String, Integer> findTotal(HashMap<String, ArrayList<Coordinates>> names) {

		HashMap<String, Integer> data = new HashMap<String, Integer>();
		
		for (String s: names.keySet()) {
			data.put(s, names.get(s).size());
		}
		return data;
		
	}
	
	public void clearSumScreen() {
		dataPane.setContent(null);
		Data.getChildren().clear();

	}
	

}
