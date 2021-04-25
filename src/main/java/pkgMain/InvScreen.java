package pkgMain;

import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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

	GridPane invGrid;
	VBox root;
	Button prevButtonInv;
	Image milkweed = new Image(getClass().getResourceAsStream("/img/commonMilkweed.png"));
	ImageView plantIV;
	ScrollPane scroll;
	Text imageLabel, plantNameLabel, lepsLabel, soilConditionsLabel, sunConditionsLabel, dimensionsLabel;
	
	public InvScreen() {
		createButton();
		createPanes();
		createTextLabels();
	}
	
	public ScrollPane getScreen() {
		return scroll;
	}
	
	public void createButton() {
		prevButtonInv = new Button("Go Back");
	}
	
	public void createPanes() {
		invGrid = new GridPane();
		scroll = new ScrollPane();
		scroll.setPannable(true);
	}
	
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
	}
	
	/**
	 * Sets up and returns the GridPane for Inventory Screen
	 *  
	 * @return GridPane for Inventory Screen
	 */
	public void createScreen(HashMap<String, Plant> plantData, HashMap<String, Image> imageData) {
        HBox labels = new HBox();
        int i = 1;
        labels.setSpacing(40);
        labels.setPadding(new Insets(10));
        labels.getChildren().addAll(imageLabel, plantNameLabel, lepsLabel, soilConditionsLabel, sunConditionsLabel, dimensionsLabel, prevButtonInv);
        invGrid.addRow(0, labels);
        
		for (Map.Entry mapElement : plantData.entrySet()) {
			HBox plantHB = new HBox();
			String key = (String)mapElement.getKey();
			
			Image plantImage = imageData.get(key);
			ImageView plantImageIv = new ImageView(plantImage);
			plantImageIv.setPreserveRatio(true);
			plantImageIv.setFitHeight(100);
			
			Plant plant = plantData.get(key);
			String lepsSupp = String.valueOf(plant.getLepsSupported());
			String soilCond = plant.getSoil();
			String sunCond = plant.getSun();
			
			Text plantName = new Text(key);
			Text leps = new Text(lepsSupp);
			Text soilConditions = new Text(soilCond);
			Text sunConditions = new Text(sunCond);
			Text dimensions = new Text("2x2");
	        plantHB.setSpacing(40);
	        plantHB.setPadding(new Insets(10));
			plantHB.getChildren().addAll(plantImageIv, plantName, leps, soilConditions, sunConditions, dimensions);
	        invGrid.addRow(i, plantHB);
	        i++;
		}
		scroll.setContent(invGrid);
		
	}
	
	public ScrollPane createInventoryScreen(HashMap<String, Plant> plantData, HashMap<String, Image> imageData) {
		createScreen(plantData, imageData);
		return scroll;
	}
}
