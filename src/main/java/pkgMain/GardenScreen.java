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

	//Creating the Required Panes
	TilePane tile = new TilePane();
	TilePane tileTwo = new TilePane();
	FlowPane flow = new FlowPane();
	BorderPane border = new BorderPane();
	
	//Creating the Texts
	Text leps = new Text();
	Text budget = new Text();
	Text sortedPlants = new Text();
	Text ConditionsDisplay = new Text();
	
	//Creating the Images
	Image milkweed = new Image(getClass().getResourceAsStream("/img/commonMilkweed.png"));
	Image planttwo = new Image(getClass().getResourceAsStream("/img/planttwo.png"));
	Image plantthree = new Image(getClass().getResourceAsStream("/img/plantthree.png"));
	Image background = new Image(getClass().getResourceAsStream("/img/bkdirt.png"));
	
	//public String Conditions = "Soil : " + garden.getSoil() + ",  Sun : " + garden.getSun() + ",  Moisture : " + garden.getMoisture();
	
	
	//Setting The Background Image
	BackgroundImage backgroundimage = new BackgroundImage(background, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT, 
               BackgroundSize.DEFAULT);
	
	
	public GardenScreen() {
		
	}
	
	
	//Adding the Sorted Plants to the Tile Pane
	public void addSortedTile(TilePane tile, Collection<Plant> plants,HashMap<String, Image> plantImages, HashMap<String, Plant> plantsMap) {
		
		for(Plant p : plants) {
			tile.getChildren().add(newPlant(p.getScientificName(), plantImages, plantsMap));
		}
		
	}
	
	
	public HashMap<String, Image> createPlantImages(Plant one, Plant two, Plant Three) {
		HashMap<String, Image> plantData = new HashMap<String, Image>();
    	
		
		plantData.put(one.getScientificName(), milkweed);
    	plantData.put(one.getScientificName(), planttwo);
    	plantData.put(one.getScientificName(), plantthree);
    	
    	return plantData;
	}
	
	
	public ImageView newPlant(String NodeID, HashMap<String, Image> plantImages, HashMap<String, Plant> plants) {
		ImageView iv1;
		Image plantView = plantImages.get(NodeID);
		Plant plant = plants.get(NodeID);
		
	
		iv1 = new ImageView();
		iv1.setImage(plantView);
		iv1.setPreserveRatio(true);
		iv1.setFitHeight(100);
		iv1.setId(NodeID);
		Tooltip t =  new Tooltip("Scientific Name: " + plant.getScientificName() + "\nPrice: $" + plant.getPrice() + "\nLeps: " + plant.getLepsSupported());
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
	
	public BorderPane createBorder(String Conditions, Collection<Plant> plants, HashMap<String, Image> plantImages, HashMap<String, Plant> plantsMap, GardenState state) {
		border.setStyle("-fx-background-color: white;");
		flow.setPadding(new Insets(10, 10, 10, 10));
		//flow.setStyle("-fx-background-color: Brown;");
		flow.setBackground(new Background(backgroundimage));
		
		
	
		border.setCenter(flow); 
		tile.setPadding(new Insets(10, 10, 10, 10));
		tile.setStyle("-fx-background-color: yellow");
		
		sortedPlants.setText("Sorted Plants");
	
		tile.getChildren().add(sortedPlants);
		//tile.getChildren().add(newPlant(demoPlantOne.getScientificName()));
		//tile.getChildren().add(newPlant(demoPlantTwo.getScientificName()));
		//tile.getChildren().add(newPlant(demoPlantThree.getScientificName()));
		addSortedTile(tile, plants, plantImages, plantsMap);
		
		
		border.setLeft(tile);
		tileTwo.setPadding(new Insets(10, 10, 10, 10));
		tileTwo.setStyle("-fx-background-color: pink;");	

		
		//Text leps = new Text();
		leps.setText("Leps Supported: " + state.totalLepsSupported);
		tileTwo.getChildren().add(leps);
		
		//Text budget = new Text();
		budget.setText("Budget: $" + state.gardenBudget);
		tileTwo.getChildren().add(budget);
		
		//Adding Conditions Text
		ConditionsDisplay.setText(Conditions);
		tileTwo.getChildren().add(ConditionsDisplay);
		
		
		
		//Adding the options button to the top tile pane
		Button optionsButton = new Button("Options");
		optionsButton.setTooltip(new Tooltip("Tooltip for Button"));
		tileTwo.getChildren().add(optionsButton);
		
		
		border.setTop(tileTwo);
		
		return border;
	}
	
    public void updateGardenDisplay(String NodeId, HashMap<String, Plant> plants, GardenState state) {
    	
    	HashMap<String, Plant> plantData = plants;
    	
    	Plant plant = plantData.get(NodeId);
    	
    	ArrayList<Integer> updates = GardenState.placePlant(state, plant);
    	//System.out.println(updates);
    	
    	int newLeps = updates.get(0);
    	int newBudget = updates.get(1);
    	
		leps.setText("Leps Supported: " + newLeps);
		//tileTwo.getChildren().add(leps);
    	
		
		budget.setText("Budget: $" + newBudget);
		//tileTwo.getChildren().add(budget);
    	
    	
    }
	
	
	
	
	
	
}
