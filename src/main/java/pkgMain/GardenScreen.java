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

public class GardenScreen {

	String NodeId = "NULL";
	
	TilePane gardenTile = new TilePane();
	TilePane gardenTileTwo = new TilePane();
	FlowPane gardenFlow = new FlowPane();
	BorderPane gardenBorder = new BorderPane();
	Text leps = new Text();
	Text budget = new Text();
	Text sortedPlants = new Text();
	Text ConditionsDisplay = new Text();
	Image milkweed = new Image(getClass().getResourceAsStream("/img/commonMilkweed.png"));
	Image planttwo = new Image(getClass().getResourceAsStream("/img/planttwo.png"));
	Image plantthree = new Image(getClass().getResourceAsStream("/img/plantthree.png"));
	Image background = new Image(getClass().getResourceAsStream("/img/bkdirt.png"));
	Button inventory = new Button("See Full Inventory");
	Button optionsButton = new Button("Options");
	
	
	BackgroundImage backgroundimage = new BackgroundImage(background, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT, 
               BackgroundSize.DEFAULT);
	

	public HashMap<String, Image> createPlantImages(Plant one, Plant two, Plant three) {
		HashMap<String, Image> plantData = new HashMap<String, Image>();
    	
		
		plantData.put(one.getScientificName(), milkweed);
    	plantData.put(two.getScientificName(), planttwo);
    	plantData.put(three.getScientificName(), plantthree);
    	
    	return plantData;
	}
	
	
	public ImageView newPlant(String NodeID, HashMap<String, Image> plantImages, String name, int price, int lepsSupported) {
		ImageView iv1;
		Image plantView = plantImages.get(NodeID);
		
		
	
		iv1 = new ImageView();
		iv1.setImage(plantView);
		iv1.setPreserveRatio(true);
		iv1.setFitHeight(100);
		iv1.setId(NodeID);
		Tooltip t =  new Tooltip("Scientific Name: " + name + "\nPrice: $" + price + "\nLeps: " + lepsSupported/*"Scientific Name: " + plant.getScientificName() + "\nPrice: $" + plant.getPrice() + "\nLeps: " + plant.getLepsSupported()*/);
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
	
	public BorderPane createBorder(String ConditionSun, String ConditionSoil, String ConditionMoisture, HashMap<String, Image> plantImages, int lepsNeeded, int budgetNeeded) {
		gardenBorder.setStyle("-fx-background-color: white;");
		gardenFlow.setPadding(new Insets(10, 10, 10, 10));
		//flow.setStyle("-fx-background-color: Brown;");
		gardenFlow.setBackground(new Background(backgroundimage));
		
		
	
		gardenBorder.setCenter(gardenFlow); 
		gardenTile.setPadding(new Insets(10, 10, 10, 10));
		gardenTile.setStyle("-fx-background-color: yellow");
		
		sortedPlants.setText("Sorted Plants");
	
		gardenTile.getChildren().add(sortedPlants);
		//tile.getChildren().add(newPlant(demoPlantOne.getScientificName()));
		//tile.getChildren().add(newPlant(demoPlantTwo.getScientificName()));
		//tile.getChildren().add(newPlant(demoPlantThree.getScientificName()));
		//addSortedTile(gardenTile, plants, plantImages, plantsMap);
		
		
		gardenBorder.setLeft(gardenTile);
		gardenTileTwo.setPadding(new Insets(10, 10, 10, 10));
		gardenTileTwo.setStyle("-fx-background-color: pink;");	

		
		//Text leps = new Text();
		leps.setText("Leps Supported: " + lepsNeeded);
		gardenTileTwo.getChildren().add(leps);
		
		//Text budget = new Text();
		budget.setText("Budget: $" + budgetNeeded);
		gardenTileTwo.getChildren().add(budget);
		
		//Adding Conditions Text
		ConditionsDisplay.setText(ConditionSun + " | " + ConditionSoil + " | " + ConditionMoisture);
		gardenTileTwo.getChildren().add(ConditionsDisplay);
		
		
		
		//Adding the options button to the top tile pane
		optionsButton.setTooltip(new Tooltip("Tooltip for Button"));
		gardenTileTwo.getChildren().add(optionsButton);
		gardenTileTwo.getChildren().add(inventory);
		
		
		gardenBorder.setTop(gardenTileTwo);
		
		return gardenBorder;
	}
	
	public void updateGardenDisplay(int newLeps, int newBudget) {
	    	

			leps.setText("Leps Supported: " + newLeps);
			budget.setText("Budget: $" + newBudget);
			
	    	
	    	
	    }
	
	
	
}
