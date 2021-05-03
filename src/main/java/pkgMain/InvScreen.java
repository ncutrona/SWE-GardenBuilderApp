package pkgMain;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Inventory Screen class.
 * Handles formatting of Inventory Screen.
 * Adds three demo plants
 * 
 * @author Aidan Chao
 * @author Nicholas Cutrona
 * @author Caleb Davis
 * @author Joey Loporto
 * @author Tommy Cheung
 */
public class InvScreen {

	BorderPane invBorder;
	TilePane invTile;
	TilePane invTileControls;
	
	VBox root;
	Button prevButtonInv;
	Image milkweed = new Image(getClass().getResourceAsStream("/img/commonMilkweed.png"));
	ImageView plantIV;
	ScrollPane scroll;
	Text imageLabel, plantNameLabel, lepsLabel, soilConditionsLabel, sunConditionsLabel, dimensionsLabel;
	ComboBox filterBy;
	
	/**
	 * Creates InvScreen object.
	 * Creates buttons, panes, textLabels.
	 */
	public InvScreen() {
		createButton();
		createPanes();
		createTextLabels();
	}
	
	/**
	 * Returns the screen's ScrollPane
	 * @return ScrollPane for InvScreen
	 */
	public ScrollPane getScreen() {
		return scroll;
	}
	
	/**
	 * Creates go back button for Inventory
	 */
	public void createButton() {
		prevButtonInv = new Button("Go Back");
		prevButtonInv.setStyle("-fx-padding: 8 15 15 15; -fx-background-insets: "
				+ "0,0 0 5 0, 0 0 6 0, 0 0 7 0; -fx-background-radius: "
				+ "8; -fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #add8e6 0%, #add8e6 100%),"
				+ "#add8e6,#add8e6,radial-gradient(center 50% 50%, radius 100%, #add8e6, #add8e6);"
				+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );-fx-font-weight: bold; -fx-font-size: 1.1em;");
	}
	
	/**
	 * Creates the gridpane and scrollpane for InvScreen.
	 */
	public void createPanes() {
		invBorder = new BorderPane();
		invTile = new TilePane();
		invTileControls = new TilePane();
		scroll = new ScrollPane();
		scroll.setPannable(true);
	}
	
	/**
	 * Creates all the text labels and sets up font formatting for InvScreen.
	 */
	public void createTextLabels() {
		imageLabel = new Text("Image:");
		imageLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		plantNameLabel = new Text("Plant name:");
		plantNameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		lepsLabel = new Text("Leps supported:");
		lepsLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		soilConditionsLabel = new Text("Soil:");
		soilConditionsLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		sunConditionsLabel = new Text("Sun:");
		sunConditionsLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		dimensionsLabel = new Text("Dimensions (ft):");
		dimensionsLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		String filters[] = {"Filter by: ", "A to Z", "Leps supported"};
		filterBy = new ComboBox(FXCollections.observableArrayList(filters));
		filterBy.setPromptText("Filter by:");
		
	}
	

	/**
	 * Sets up the InvScreen.
	 * Creates and formats the labels HBox.
	 * adds plants from plantData, sets conditions accordingly.
	 * 
	 * @param plantData HashMap-String, Plant- plantData to be shown on screen
	 * @param imageData HashMap-String, Image- images for plants
	 */
	public void createScreen(Map<String, Plant> plantData, HashMap<String, Image> imageData) {
        
		//invTile.setStyle("-fx-background-color: pink;");	
		//invTileControls.setStyle("-fx-background-color: pink;");
		invBorder.setStyle("-fx-background-color: pink;");
		//scroll.setStyle("-fx-background-color: pink;");
		
		HBox controls = new HBox();
        controls.setSpacing(40);
        controls.setPadding(new Insets(10));
        controls.getChildren().addAll(prevButtonInv, filterBy);
        invTileControls.getChildren().addAll(controls);
        
		for (Map.Entry mapElement : plantData.entrySet()) {
			VBox plantVB = new VBox();
			String key = (String)mapElement.getKey();
			
			Image plantImage = imageData.get(key);
			ImageView plantImageIv = new ImageView(plantImage);
			plantImageIv.setPreserveRatio(true);
			plantImageIv.setFitHeight(100);
			
			Plant plant = plantData.get(key);
			String lepsSupp = String.valueOf(plant.getLepsSupported());
			
			String soilCond = plant.getSoil();
			soilCond = returnSoilCondition(soilCond);
			
			String sunCond = plant.getSun();
			sunCond = returnSunCondition(sunCond);
			Text plantName = new Text(key);
			lepsSupp = lepsSupp + " leps supported";
			Text leps = new Text(lepsSupp);
			Text soilConditions = new Text(soilCond);
			Text sunConditions = new Text(sunCond);
			String height = String.valueOf(plant.getHeight()) + " ft";
			String width = String.valueOf(plant.getWidth()) + " ft";
			String heightWidth = height + " x " + width;
			Text dimensions = new Text(heightWidth);
	        plantVB.setSpacing(5);
	        plantVB.setPadding(new Insets(10));
			plantVB.getChildren().addAll(plantImageIv, plantName, leps, soilConditions, sunConditions, dimensions);
			invTile.getChildren().add(plantVB);
		}
		invBorder.setTop(invTileControls);
		invBorder.setCenter(invTile);
		scroll.setContent(invBorder);
	}
	
	public String returnSoilCondition(String soilCond) {
		if(soilCond.toLowerCase().contains("clay") && soilCond.toLowerCase().contains("sand") && soilCond.toLowerCase().contains("loam")) {
			soilCond = "Clay, loam, sand";
		}
		else if(soilCond.toLowerCase().contains("clay") && soilCond.toLowerCase().contains("sand")) {
			soilCond = "Clay, sand";
		}
		else if(soilCond.toLowerCase().contains("clay") && soilCond.toLowerCase().contains("loam")) {
			soilCond = "Clay, loam";
		}
		else if(soilCond.toLowerCase().contains("loam") && soilCond.toLowerCase().contains("sand")) {
			soilCond = "Loam, sand";
		}
		else if(soilCond.toLowerCase().contains("clay")) {
			soilCond = "Clay";
		}
		else if(soilCond.toLowerCase().contains("loam")) {
			soilCond = "Loam";
		}
		else {
			soilCond = "Sand";
		}
		return soilCond;
	}
	
	public String returnSunCondition(String sunCond) {
		if(sunCond.toLowerCase().contains("full") && sunCond.toLowerCase().contains("partial") && sunCond.toLowerCase().contains("shade")) {
			sunCond = "Full sun, partial sun, shade";
		}
		else if(sunCond.toLowerCase().contains("full") && sunCond.toLowerCase().contains("partial")) {
			sunCond = "Full sun, partial sun";
		}
		else if(sunCond.toLowerCase().contains("full") && sunCond.toLowerCase().contains("shade")) {
			sunCond = "Full sun, shade";
		}
		else if(sunCond.toLowerCase().contains("partial") && sunCond.toLowerCase().contains("shade")) {
			sunCond = "Partial sun, shade";
		}
		else if(sunCond.toLowerCase().contains("full")) {
			sunCond = "Full sun";
		}
		else if(sunCond.toLowerCase().contains("partial")) {
			sunCond = "Partial sun";
		}
		else {
			sunCond = "Shade";
		}
		return sunCond;
	}
	
	
	/**
	 * calls createScreen to setup the screen, returns the ScrollPane
	 * 
	 * @param plantData HashMap-String, Plant- plantData to be shown on screen
	 * @param imageData HashMap-String, Image- images for plants
	 * @return ScrollPane from InvScreen
	 */
	public ScrollPane createInventoryScreen(Map<String, Plant> plantData, HashMap<String, Image> imageData) {
		createScreen(plantData, imageData);
		return scroll;
	}
}
