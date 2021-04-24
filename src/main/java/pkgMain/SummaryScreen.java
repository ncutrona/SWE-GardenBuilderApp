package pkgMain;

import javafx.collections.ObservableList;
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
import javafx.scene.layout.VBox;
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
	Button close;
	
	public SummaryScreen() {
		createPanes();
		createButton();
	}
	
	public void createPanes() {
		summaryBorder = new BorderPane();
		dataPane = new ScrollPane();
	}
	
	public void createButton() {
		close = new Button("Close");
	}
	
	public BorderPane getScreen() {
		return summaryBorder;
	}
	
	
	public BorderPane createSummaryScreen(HashMap<String, Plant> plantData, HashMap<String, Image> imageData, HashMap<String, Integer> dataFrequency) {
		createScreen(plantData, imageData, dataFrequency);
		
		summaryBorder.setCenter(dataPane);
		
		return summaryBorder;
		
		
	}
	
	//Need to call this when handler is switched to this screen
	public void createScreen(HashMap<String, Plant> plantData, HashMap<String, Image> imageData, HashMap<String, Integer> dataFrequency) {
		
		VBox vbox = new VBox(15);
		
		for (Map.Entry mapElement : dataFrequency.entrySet()) {
			
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
			Text totalSpent = new Text("Total Spent: " + String.valueOf(plant.price * value));
			//Leps Supported
			Text lepsSupported = new Text("Leps Supported: " + String.valueOf(plant.lepsSupported * value));
			
			//Vbox adding to scroll
			HBox hbox = new HBox(10);
			hbox.getChildren().addAll(plantImageIv, name, quantity, totalSpent, lepsSupported);
			vbox.getChildren().add(hbox);
			
			
		}
		
		dataPane.setContent(vbox);
		
		
		
	}
	
	public HashMap<String, Integer> findTotal(ArrayList<String> names) {

		HashMap<String, Integer> data = new HashMap<String, Integer>();
		Set<String> distinct = new HashSet<>(names);
		
		for (String s: distinct) {
			data.put(s, Collections.frequency(names, s));
			System.out.println(Collections.frequency(names, s));
		}
		
		return data;
		
	}
	

}
