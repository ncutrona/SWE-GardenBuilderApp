package pkgMain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller class from MVC design pattern.
 * Extends from Application.
 *
 * @author Aidan Chao
 * @author Nicholas Cutrona
 * @author Caleb Davis
 * @author Joey Loporto
 * @author Tommy Cheung
 */
public class Controller extends Application{
	Model model;
	View2 view;
	LoadScreen loadScreen;
	ConditionScreen conditionScreen;
	GardenScreen gardenScreen;
	InvScreen invScreen;
	SaveScreen saveScreen;
	PopUpWindow popup;
	
	Scene load, save, condition, garden, inv;
	
	Stage window, popupStage;
	
	/**
	 * main method, launches application.
	 * Launch hands off execution to javafx to do various backend tasks
	 * such as setting up graphics and system specifics.
	 * 
	 * @param args console arguments
	 */
	public static void main(String[] args) {
		launch();
	}

	/**
	 * Overrides the start method from Application.
	 * Sets up view.gardenScreen, ConditionScreen, Load Screen,
	 * Save Screen, Inventory Screen, and Popup Scene.
	 * Sets actions for events and buttons.
	 * 
	 * @param primaryStage primary Stage object
	 */
	@Override
	public void start(Stage primaryStage){
		window = primaryStage;
		model = new Model();
		//view = new View2();
		
		loadScreen = new LoadScreen();
		load = new Scene(loadScreen.getScreen(), 1000, 600);
		saveScreen = new SaveScreen();
		save = new Scene(saveScreen.createLoadingBorder(),800, 600);
		conditionScreen = new ConditionScreen();
		condition = new Scene(conditionScreen.getScreen(), 1000, 600);
		gardenScreen = new GardenScreen();
		garden = new Scene(gardenScreen.getScreen(), 1000, 600);
		invScreen = new InvScreen();
		inv = new Scene(invScreen.createInvBorder(),800, 600);
		
		popUpHandler();
		invScreenHandler();
		saveScreenHandler();
		gardenScreenHandler();
		loadScreenHandler();
		conditionScreenHandler();
	
		window.setTitle("GARDEN BUILDER v 0.01 ~ ALPHA");
		window.setScene(load);
		window.show();
    	
    	
	}
	public void popUpHandler() {
		// Setting an action for the options button
    	gardenScreen.optionsButton.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent e) {
    	    	popup.restart.setOnAction(new EventHandler<ActionEvent>() {

    	    		@Override
    	    		public void handle(ActionEvent e) {
    	    			popupStage = new Stage();
    	    			popup = new PopUpWindow();
    	    			Scene scene = new Scene(popup.display());
    	    			
    	    			popupStage.initModality(Modality.APPLICATION_MODAL);	
    	    			popupStage.setTitle("Options");
    	    			popupStage.setMinWidth(250);
    	    			popupStage.setMinHeight(500);
    	    			popupStage.setScene(scene);
    	    			popupStage.showAndWait(); 
    	    		}
    	    	});
    			  			
    	    	popup.save.setOnAction(k -> popupStage.close());
    	    	popup.resume.setOnAction(k -> popupStage.close());
    		}
    	});
	}
	public void invScreenHandler() {
		invScreen.PrevButtonInv.setOnAction(e-> window.setScene(load));
	}
	public void saveScreenHandler() {
		saveScreen.prevButton.setOnAction(e-> window.setScene(load));
	}
	public void gardenScreenHandler() {
		
    	ArrayList<Plant> plantsMaster = model.getPlants();
    	Collection<Plant> plantCollection = Plant.sortPlants(plantsMaster);
    	
    	gardenScreen.createPlantImageList(model.demoPlantOne.getScientificName(), model.demoPlantTwo.getScientificName(), model.demoPlantThree.getScientificName());
    	
    	gardenScreen.gardenPane.setOnDragDropped(new EventHandler <DragEvent>(){
			public void handle(DragEvent event) {
	
				Dragboard db = event.getDragboard();
				if(db.hasString()) {
					String nodeId = db.getString();
					ImageView plant = new ImageView();
					plant.setImage(gardenScreen.plantImageList.get(nodeId));
					System.out.println(nodeId);
					plant.setPreserveRatio(true);
					plant.setFitHeight(100);
					plant.setId(nodeId);
					
					if(plant != null) {
						HashMap<String, Plant> plants = model.createPlantData();
						Plant plantNeeded = plants.get(nodeId);
						ImageView newPlantView = gardenScreen.newPlant(nodeId, plantNeeded.getScientificName(), plantNeeded.getPrice(), plantNeeded.getLepsSupported());
						newPlantView.relocate(event.getX()- (newPlantView.getFitHeight()/2), event.getY() - (newPlantView.getFitHeight()/2));
						gardenScreen.gardenPane.getChildren().add(newPlantView);
		
						model.stateFinal.totalLepsSupported += plantNeeded.lepsSupported;
						model.stateFinal.gardenBudget -= plantNeeded.price;
						gardenScreen.updateLepAndBudget(model.stateFinal.totalLepsSupported, model.stateFinal.gardenBudget);
						
					}
				}
				
				event.setDropCompleted(true);
				event.consume();
			}
		});
    	gardenScreen.gardenPane.setOnDragOver(new EventHandler <DragEvent>() {
			public void handle(DragEvent event) {
				
				if (event.getGestureSource() != gardenScreen.gardenPane && event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.COPY);
                }
				
				event.consume();
			}
		});
    	
    	gardenScreen.inventory.setOnAction(e-> window.setScene(inv));
		
    	
    	//Setting the GardenScene
		for(Plant p : plantCollection) {
			gardenScreen.gardenTile.getChildren().add(gardenScreen.newPlant(p.getScientificName(), p.getScientificName(),  p.getPrice(),  p.getLepsSupported()));
		}
	}
	public void loadScreenHandler() {
		loadScreen.startButton.setOnAction(e-> window.setScene(condition));
		loadScreen.loadButton.setOnAction(e-> window.setScene(save));
	}
	public void conditionScreenHandler() {
		
		conditionScreen.next.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String [] soilList = {"sand", "loam", "clay"};
				String [] sunList = {"shade","partial","full"};
				String [] moistList = {"dry", "moist", "wet"};
				
				if(conditionScreenHelper()) {
					model.gardenFinal.setMoistureConditions(moistList[(int)conditionScreen.moistSlider.getValue()-1]);
					model.gardenFinal.setSunConditions(sunList[(int)conditionScreen.sunSlider.getValue()-1]);
					model.gardenFinal.setSoilConditions(soilList[(int)conditionScreen.soilSlider.getValue()-1]);
					gardenScreen.updateCondition(model.gardenFinal);
					window.setScene(garden);
				}
			}
    		
    	});
		
    	/*
    	conditionScreen.submit.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent e) {
    			if (!conditionScreen.budget.getText().isEmpty() && !conditionScreen.gardenName.getText().isEmpty()) {
    				try {
    					int intBudget = Integer.parseInt(conditionScreen.budget.getText());
    					conditionScreen.budget.setPromptText("Your budget was set.");
    					conditionScreen.gardenName.setPromptText("Your Garden Name was set.");
    					model.gardenFinal.setBudget(intBudget);
    					model.stateFinal.gardenBudget = model.gardenFinal.getBudget();
    					model.stateFinal.setGardenName(conditionScreen.gardenName.getText());
    					
    				} catch(Exception except) {				
    					conditionScreen.budget.setPromptText("Enter a valid budget $");
    					conditionScreen.gardenName.setPromptText("Enter Your Garden Name: ");
    				}

    			} else {
    				conditionScreen.budget.setPromptText("Enter a budget $");
    				conditionScreen.gardenName.setPromptText("Enter a Garden Name: ");
    			}
    			
    			System.out.println(model.gardenFinal.getBudget());
    		}
    	});
		
    	//Setting an action for the Clear button
    	conditionScreen.clear.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent e) {
    			conditionScreen.budget.clear();
    			conditionScreen.gardenName.clear();
    		}
    	});
		*/
		conditionScreen.previous.setOnAction(e-> window.setScene(load));
	}
	
	public boolean conditionScreenHelper() {
		if (!conditionScreen.budget.getText().isEmpty() && !conditionScreen.gardenName.getText().isEmpty()) {
			try {
				int intBudget = Integer.parseInt(conditionScreen.budget.getText());
				conditionScreen.budget.setPromptText("Your budget was set.");
				conditionScreen.gardenName.setPromptText("Your Garden Name was set.");
				model.gardenFinal.setBudget(intBudget);
				model.stateFinal.gardenBudget = model.gardenFinal.getBudget();
				model.stateFinal.setGardenName(conditionScreen.gardenName.getText());
				return true;
			} catch(Exception except) {				
				conditionScreen.budget.clear();
				conditionScreen.gardenName.clear();
				
			}
		}
		return false;
	}
}