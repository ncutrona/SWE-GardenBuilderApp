package pkgMain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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

	View view;
	Stage window;
	Model model;
	
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
		view = new View();
		model = new Model();
		view.gardenScreen.createPlantImageList(model.demoPlantOne.getScientificName(), model.demoPlantTwo.getScientificName(), model.demoPlantThree.getScientificName());

		//call screen handler so buttons and stuff actually do something
		popUpHandler();
		invScreenHandler();
		saveScreenHandler();
		gardenScreenHandler();
		loadScreenHandler();
		conditionScreenHandler();
		pentagonScreenHandler();
		
		window.setTitle("GARDEN BUILDER v 0.01 ~ ALPHA");
		window.setScene(view.getScreen());
		window.show();
    	
	}
	
	public void popUpHandler() {
		// Setting an action for the options button
		
    	view.popup.restart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.clearInfo();
				view.closePopUp();
				window.setScene(view.loadScreenToScene());
			}
    	});
    	
    	view.popup.save.setOnAction(k -> view.closePopUp());
    	view.popup.resume.setOnAction(k -> view.closePopUp());
    	
	}
	public void invScreenHandler() {
		view.invScreen.PrevButtonInv.setOnAction(e-> window.setScene(view.gardenScreenToScene()));
	}
	public void saveScreenHandler() {
		view.saveScreen.prevButton.setOnAction(e-> window.setScene(view.loadScreenToScene()));
		
		//there should be other handlers here for loading a garden
	}
	public void pentagonScreenHandler() {
		view.pentagonScreen.set.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.gardenScreen.gardenPane.getChildren().add(view.pentagonScreen.hexagon);
				window.setScene(view.gardenScreenToScene());
				
			}
			
		});
	}
	public void gardenScreenHandler() {
		view.addPlantToGarden(model.plantCollection);
		
    	view.gardenScreen.gardenPane.setOnDragDropped(new EventHandler <DragEvent>(){
			public void handle(DragEvent event) {
				int [] lepAndBudget = view.plantDragDropping(event, getPlantList());
				if(lepAndBudget[0] > 0 && lepAndBudget[1] > 0) {
					setModelLepAndBudget(lepAndBudget[0], lepAndBudget[1]);
					view.gardenScreen.updateLepAndBudget(model.stateFinal.totalLepsSupported, model.stateFinal.gardenBudget);
				}
				
				event.setDropCompleted(true);
				event.consume();
			}
		});
    	view.gardenScreen.gardenPane.setOnDragOver(new EventHandler <DragEvent>() {
			@Override
    		public void handle(DragEvent event) {
				view.plantDragOver(event);
				event.consume();
			}
		});
    	view.gardenScreen.optionsButton.setOnAction(e -> view.popScreenToStage());
    	
    	// Delete plant handler
    	view.gardenScreen.gardenPane.setOnMouseClicked(new EventHandler<MouseEvent>() {	
    		@Override
    		public void handle(MouseEvent e) {
    			if (e.getButton() == MouseButton.SECONDARY) {
    				e.consume();
        			Plant removed = view.deletePlant(e.getTarget(), model.plantDataList);
        			ImageView removePlant = (ImageView) e.getTarget();
        			removePlant.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
        				@Override
        				public void handle(ContextMenuEvent event) {
        					getDeleteMenu(removePlant, removed).show(removePlant, event.getScreenX(), event.getScreenY());
        				}
        			});
    			}
    		}
    	});
    	
    	view.gardenScreen.inventory.setOnAction(e-> window.setScene(view.invScreenToScene()));
	}

	public void deletePlantUpdateState(Plant removed) {
		model.stateFinal.totalLepsSupported -= removed.lepsSupported;
		model.stateFinal.gardenBudget += removed.price;
		view.gardenScreen.updateLepAndBudget(model.stateFinal.totalLepsSupported, model.stateFinal.gardenBudget);
	}
	
	public ContextMenu getDeleteMenu(ImageView plant, Plant plantObject) {
		ContextMenu contextMenu = new ContextMenu();
		MenuItem deletePlant = new MenuItem("Delete plant");
        deletePlant.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.gardenScreen.gardenPane.getChildren().remove(plant);
                deletePlantUpdateState(plantObject);
                
            }
        });
		contextMenu.getItems().add(deletePlant);
		return contextMenu;
	}
	
	
	public void loadScreenHandler() {
		view.loadScreen.startButton.setOnAction(e-> window.setScene(view.conditionScreenToScene()));
		view.loadScreen.loadButton.setOnAction(e-> window.setScene(view.saveScreenToScene()));
	}
	public void conditionScreenHandler() {
		
		view.conditionScreen.next.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(conditionScreenHelper()) {
					String [] soilList = {"sand", "loam", "clay"};
					String [] sunList = {"shade","partial","full"};
					String [] moistList = {"dry", "moist", "wet"};
					
					int [] sliderValues = view.returnConditionSliderValue();
					//System.out.println(sliderValues);
					
					model.gardenFinal.setMoistureConditions(moistList[sliderValues[0]]);
					model.gardenFinal.setSunConditions(sunList[sliderValues[1]]);
					model.gardenFinal.setSoilConditions(soilList[sliderValues[2]]);
					view.gardenScreen.updateCondition(model.gardenFinal);
					window.setScene(view.pentagonScreenToScene());
				}
				
			}
    		
    	});
		
    	/*
    	//Setting an action for the Clear button
    	conditionScreen.clear.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent e) {
    			conditionScreen.budget.clear();
    			conditionScreen.gardenName.clear();
    		}
    	});
		*/
		view.conditionScreen.previous.setOnAction(e-> window.setScene(view.loadScreenToScene()));
	}
	
	public void setModelLepAndBudget(int lep, int price) {
		model.stateFinal.totalLepsSupported += lep;
		model.stateFinal.gardenBudget -= price;
	}
	public HashMap<String, Plant> getPlantList() {
		return model.plantDataList;
	}
	public boolean conditionScreenHelper() {
		if (view.conditionHasText()) {
			try {
				int intBudget = Integer.parseInt(view.conditionScreen.budget.getText());
				model.gardenFinal.setBudget(intBudget);
				model.stateFinal.gardenBudget = model.gardenFinal.getBudget();
				model.stateFinal.setGardenName(view.conditionScreen.gardenName.getText());
				return true;
			} catch(Exception except) {				
				view.setValidBudgetText();
			}
		}
		return false;
	}
}