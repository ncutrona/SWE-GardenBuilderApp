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

		//call screen handler so buttons and stuff actually do something
		popUpHandler();
		invScreenHandler();
		saveScreenHandler();
		gardenScreenHandler();
		loadScreenHandler();
		conditionScreenHandler();
		
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
	public void gardenScreenHandler() {
		
    	view.gardenScreen.gardenPane.setOnDragDropped(new EventHandler <DragEvent>(){
			public void handle(DragEvent event) {
				view.plantDragDropping(event);
				event.setDropCompleted(true);
				event.consume();
			}
		});
    	view.gardenScreen.gardenPane.setOnDragOver(new EventHandler <DragEvent>() {
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
    				ImageView removePlant = (ImageView)e.getTarget();
    		        removePlant.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
    		            @Override
    		            public void handle(ContextMenuEvent event) {
    		                view.gardenScreen.getDeleteMenu(removePlant).show(removePlant, event.getScreenX(), event.getScreenY());
    		            }
    		        });
    			}
    		}
    	});
    	
    	view.gardenScreen.inventory.setOnAction(e-> window.setScene(view.invScreenToScene()));
	}
	public void loadScreenHandler() {
		view.loadScreen.startButton.setOnAction(e-> window.setScene(view.conditionScreenToScene()));
		view.loadScreen.loadButton.setOnAction(e-> window.setScene(view.saveScreenToScene()));
	}
	public void conditionScreenHandler() {
		
		view.conditionScreen.next.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(view.conditionScreenHelper()) {
					view.setConditions();
					window.setScene(view.gardenScreenToScene());
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
	
	
}