package pkgMain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;

public class Controller extends Application{
	Model model;
	
	Stage window;
	Scene gardenScene, LoadScreenScene, ConditionsScene;
	
	
	public static void main(String[] args) {
		/*
		 * Launch hands off execution to javafx to do various backend tasks
		 * such as setting up graphics and system specifics
		 */
		launch();
	}
	
	@Override
	public void start(Stage primaryStage){
		
		//Creating the Window
		window = primaryStage;
		//Creating a Model instance
		Model model = new Model();
		
		
		//GARDEN SCREEN CONTROLLER CODE **********************************************************************************************
		
		//Creating a GardenScreen Instance
		GardenScreen GardenScreenView = new GardenScreen();
		
		//Creating our Sorted Collection of Plants
    	ArrayList<Plant> plantsMaster = model.getPlants();
    	Collection<Plant> plantCollection = Plant.sortPlants(plantsMaster);
    	
    	//Creaeting our PlantImageData with the demoplants
    	HashMap<String, Image> plantImageData = new HashMap<String, Image>();
    	plantImageData = GardenScreenView.createPlantImages(model.demoPlantOne, model.demoPlantTwo, model.demoPlantThree);
    	
    	
    	
    	
    	
    	GardenScreenView.flow.setOnDragDropped(new EventHandler <DragEvent>(){
			public void handle(DragEvent event) {
				HashMap<String, Image> images = GardenScreenView.createPlantImages(model.demoPlantOne, model.demoPlantTwo, model.demoPlantThree);
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
						GardenScreenView.flow.getChildren().add(GardenScreenView.newPlant(nodeId, GardenScreenView.createPlantImages(model.demoPlantOne, model.demoPlantTwo, model.demoPlantThree), model.createPlantData()));
						GardenScreenView.updateGardenDisplay(nodeId, model.createPlantData(), model.state);
					}
				}
				
				event.setDropCompleted(true);
				event.consume();
			}
		});
    	
    	
    	GardenScreenView.flow.setOnDragOver(new EventHandler <DragEvent>() {
			public void handle(DragEvent event) {
				
				if (event.getGestureSource() != GardenScreenView.flow && event.getDragboard().hasString()) {
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.COPY);
                }
				
				event.consume();
			}
		});
    	
		
    	//Setting the GardenScene
    	Scene gardenScene = new Scene(GardenScreenView.createBorder(model.Conditions, plantCollection, GardenScreenView.createPlantImages(model.demoPlantOne, model.demoPlantTwo, model.demoPlantThree), model.createPlantData(), model.state), 800, 600);
 
    	
		//****************************************************************************************************************************
		
		window.setScene(gardenScene);
		window.setTitle("Window TITLE");
		window.show();
    	
    	
	}
	

	
}
