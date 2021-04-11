package pkgMain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CodeBreakDown extends Application{
	
	String NodeId = "NULL";
	
	GardenConditions garden = new GardenConditions(500, "full", "dry", "clay");
	GardenState state = new GardenState("Test Garden", "Arpil", 0, false, garden.getBudget());
	Plant demoPlantOne = new Plant(6, 3, "A negudo", "clay", "full", "dry", 100, 100, 1, 0 ,0);
	Plant demoPlantTwo = new Plant(12, 5, "B negudo", "clay", "full", "dry", 100, 100, 1, 0 ,0);
	Plant demoPlantThree = new Plant(3, 7, "C negudo", "clay", "full", "dry", 100, 100, 1, 0 ,0);
	int gardenBudget = garden.getBudget();
	TilePane tile = new TilePane();
	TilePane tileTwo = new TilePane();
	FlowPane flow = new FlowPane();
	BorderPane border = new BorderPane();
	Text leps = new Text();
	Text budget = new Text();
	Text sortedPlants = new Text();
	Image milkweed = new Image(getClass().getResourceAsStream("/img/commonMilkweed.png"));
	Image planttwo = new Image(getClass().getResourceAsStream("/img/planttwo.png"));
	Image plantthree = new Image(getClass().getResourceAsStream("/img/plantthree.png"));
	
	Image background = new Image(getClass().getResourceAsStream("/img/bkdirt.png"));
	BackgroundImage backgroundimage = new BackgroundImage(background, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT, 
               BackgroundSize.DEFAULT);
	
	ArrayList<Plant> plantsMaster = new ArrayList<Plant>();
	
	public void getPlants() {
		plantsMaster.add(demoPlantOne);
		plantsMaster.add(demoPlantTwo);
		plantsMaster.add(demoPlantThree);
		
		
	}
	
	//getSortedPlants();
	
	public void addSortedTile(TilePane tile, Collection<Plant> plants) {
		
		for(Plant p : plants) {
			tile.getChildren().add(newPlant(p.getScientificName()));
		}
		
	}
	
	//Added - Creates a hashmap of our plants to be called when we update the garden in order to select the plant to update
	public HashMap<String, Plant> createPlantData() {
		HashMap<String, Plant> plantData = new HashMap<String, Plant>();
    	plantData.put(demoPlantOne.getScientificName(), demoPlantOne);
    	plantData.put(demoPlantTwo.getScientificName(), demoPlantTwo);
    	plantData.put(demoPlantThree.getScientificName(), demoPlantThree);
    	
    	return plantData;
	}
	
	public HashMap<String, Image> createPlantImages() {
		HashMap<String, Image> plantData = new HashMap<String, Image>();
    	plantData.put(demoPlantOne.getScientificName(), milkweed);
    	plantData.put(demoPlantTwo.getScientificName(), planttwo);
    	plantData.put(demoPlantThree.getScientificName(), plantthree);
    	
    	return plantData;
	}
	
	
	
	
	public ImageView newPlant(String NodeID) {
		ImageView iv1;
		HashMap<String, Image> plantImages = createPlantImages();
		Image plantView = plantImages.get(NodeID);
		
		
		iv1 = new ImageView();
		iv1.setImage(plantView);
		iv1.setPreserveRatio(true);
		iv1.setFitHeight(100);
		iv1.setId(NodeID);
		
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
	
    @Override
    public void start(Stage stage) {
    	
    	getPlants();
    	Collection<Plant> plantCollection = Plant.sortPlants(plantsMaster);
    	
    	
    	
    	
    	stage.setTitle("Garden Builder v. 0.01 (Alpha)");
    	//Setting up the Images
    	//newPlant("Milkweed");
    
    	
    	//DRAG AND DROP FEATURE *****************************************************
    	
    	flow.setOnDragDropped(new EventHandler <DragEvent>(){
			public void handle(DragEvent event) {
				HashMap<String, Image> images = createPlantImages();
				Dragboard db = event.getDragboard();
				if(db.hasString()) {
					String nodeId = db.getString();
					ImageView plant = new ImageView();
					plant.setImage(images.get(nodeId));
					plant.setPreserveRatio(true);
					plant.setFitHeight(100);
					plant.setId(nodeId);
					//System.out.println(tile.lookup("#" + nodeId)); //TILE LOOKUP IS CAUSING ISSUES. OTHERWISE CODE IS GOOD
					
					
					if(plant != null) {
						//System.out.println("WE REACH HERE!");
						flow.getChildren().add(newPlant(nodeId));
						updateGardenDisplay(nodeId);
					}
				}
				
				event.setDropCompleted(true);
				event.consume();
			}
		});
    	
    	flow.setOnDragOver(new EventHandler <DragEvent>() {
			public void handle(DragEvent event) {
				
				if (event.getGestureSource() != flow && event.getDragboard().hasString()) {
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.COPY);
                }
				
				event.consume();
			}
		});
    	
    	
    	//***************************************************************************
    	//Creating the Layout of Main Garden Screen
		border.setStyle("-fx-background-color: white;");
		flow.setPadding(new Insets(10, 10, 10, 10));
		//flow.setStyle("-fx-background-color: Brown;");
		flow.setBackground(new Background(backgroundimage));
		
		
	
		border.setCenter(flow); 
		tile.setPadding(new Insets(10, 10, 10, 10));
		tile.setStyle("-fx-background-color: Pink;");
		
		sortedPlants.setText("Sorted Plants");
	
		tile.getChildren().add(sortedPlants);
		//tile.getChildren().add(newPlant(demoPlantOne.getScientificName()));
		//tile.getChildren().add(newPlant(demoPlantTwo.getScientificName()));
		//tile.getChildren().add(newPlant(demoPlantThree.getScientificName()));
		addSortedTile(tile, plantCollection);
		
		
		border.setLeft(tile);
		tileTwo.setPadding(new Insets(10, 10, 10, 10));
		tileTwo.setStyle("-fx-background-color: orange;");	

		
		//Text leps = new Text();
		leps.setText("Leps Supported: " + state.totalLepsSupported);
		tileTwo.getChildren().add(leps);
		
		//Text budget = new Text();
		budget.setText("Budget: $" + state.gardenBudget);
		tileTwo.getChildren().add(budget);
		
		border.setTop(tileTwo);
	
		
    	Scene scene = new Scene(border, 800, 600);
        stage.setScene(scene);
        stage.show();
    }
    
    public void updateGardenDisplay(String NodeId) {
    	
    	HashMap<String, Plant> plantData = createPlantData();
    	
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

    public static void main(String[] args) {
        launch();
    }
	/*public void drag(MouseEvent event) {
		System.out.println("ic mouse");
		Node n = (Node)event.getSource();
		n.setTranslateX(n.getTranslateX() + event.getX());
		n.setTranslateY(n.getTranslateY() + event.getY());
		//call place plant
		updateGardenDisplay();
	}*/
}


