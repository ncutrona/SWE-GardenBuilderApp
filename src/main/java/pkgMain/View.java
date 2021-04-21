package pkgMain;

import java.util.HashMap;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class View{
	
	private Stage stage, popupStage;
	private Scene screenScene;
	
	Model model;
	
	LoadScreen loadScreen;
	ConditionScreen conditionScreen;
	GardenScreen gardenScreen;
	InvScreen invScreen;
	SaveScreen saveScreen;
	PopUpWindow popup;
	
	Scene load, save, condition, garden, inv , pop;
	
	public View() {
		model = new Model();
		createScreenAndScene();
		gardenScreen.createPlantImageList(model.demoPlantOne.getScientificName(), model.demoPlantTwo.getScientificName(), model.demoPlantThree.getScientificName());
		addPlantToGarden();
		loadScreenToScene();
	}
	public Scene getScreen() {
		return screenScene;
	}
	
	public Scene loadScreenToScene() {
		screenScene = load;
		return screenScene;
	}
	public Scene saveScreenToScene() {
		screenScene = save;
		return screenScene;
	}
	public Scene conditionScreenToScene() {
		screenScene = condition;
		return screenScene;
	}
	public Scene gardenScreenToScene() {
		screenScene = garden;
		return screenScene;
	}
	public Scene invScreenToScene() {
		screenScene = inv;
		return screenScene;
	}
	public void popScreenToStage() {
		popupStage = new Stage();  			
		popupStage.initModality(Modality.APPLICATION_MODAL);	
		popupStage.setTitle("Options");
		popupStage.setMinWidth(250);
		popupStage.setMinHeight(500);
		popupStage.setScene(pop);
		popupStage.showAndWait(); 
	}
	public void closePopUp() {
		popupStage.close();
	}
	
	
	public void createScreenAndScene() {
		loadScreen = new LoadScreen();
		load = new Scene(loadScreen.getScreen(), 1000, 600);
		saveScreen = new SaveScreen();
		save = new Scene(saveScreen.createLoadingBorder(),800, 600);
		conditionScreen = new ConditionScreen();
		condition = new Scene(conditionScreen.getScreen(), 800, 600);
		gardenScreen = new GardenScreen();
		garden = new Scene(gardenScreen.getScreen(), 1000, 600);
		invScreen = new InvScreen();
		inv = new Scene(invScreen.getScreen(),800, 600);
		popup = new PopUpWindow();
		pop = new Scene(popup.getScreen());
	}
	
	public void clearInfo() {
		conditionScreen.budget.clear();
		conditionScreen.gardenName.clear();
		gardenScreen.gardenPane.getChildren().removeAll(gardenScreen.gardenPane.getChildren());
		conditionScreen.budget.setPromptText("Enter your Budget $");
		conditionScreen.soilSlider.setValue(1);
		conditionScreen.sunSlider.setValue(1);
		conditionScreen.moistSlider.setValue(1);
	}
	
	public void plantDragDropping(DragEvent event) {
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
				Plant plantNeeded = model.plantDataList.get(nodeId);
				plant.relocate(event.getX()- (plant.getFitHeight()/2), event.getY() - (plant.getFitHeight()/2));
				gardenScreen.gardenPane.getChildren().add(plant);

				model.stateFinal.totalLepsSupported += plantNeeded.lepsSupported;
				model.stateFinal.gardenBudget -= plantNeeded.price;
				gardenScreen.updateLepAndBudget(model.stateFinal.totalLepsSupported, model.stateFinal.gardenBudget);
			}
			
		}	
	}
	public void plantDragOver(DragEvent event) {
		if(event.getGestureSource() != gardenScreen.gardenPane && event.getDragboard().hasString()) {
			event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		}
	}
	
	public void deletePlant(EventTarget e) {
		gardenScreen.gardenPane.getChildren().remove(e);
		ImageView removePlant = (ImageView)e;
		String removedPlantName = removePlant.getId();
		Plant removedPlant = model.plantDataList.get(removedPlantName);
		model.stateFinal.totalLepsSupported -= removedPlant.lepsSupported;
		model.stateFinal.gardenBudget += removedPlant.price;
		gardenScreen.updateLepAndBudget(model.stateFinal.totalLepsSupported, model.stateFinal.gardenBudget);
	
	}
	public void addPlantToGarden() {
		System.out.println(model.plantCollection);
		for(Plant p : model.plantCollection) {
			gardenScreen.gardenTile.getChildren().add(gardenScreen.newPlant(p.getScientificName(), p.getScientificName(),  p.getPrice(),  p.getLepsSupported()));
		}
		
	}
	
	public boolean conditionScreenHelper() {
		if (!conditionScreen.budget.getText().isEmpty() && !conditionScreen.gardenName.getText().isEmpty()) {
			try {
				int intBudget = Integer.parseInt(conditionScreen.budget.getText());
				model.gardenFinal.setBudget(intBudget);
				model.stateFinal.gardenBudget = model.gardenFinal.getBudget();
				model.stateFinal.setGardenName(conditionScreen.gardenName.getText());
				return true;
			} catch(Exception except) {				
				conditionScreen.budget.clear();
				conditionScreen.gardenName.clear();
				conditionScreen.budget.setPromptText("Enter a valid budget $");	
			}
		}
		return false;
	}
	
	public void setConditions() {
		String [] soilList = {"sand", "loam", "clay"};
		String [] sunList = {"shade","partial","full"};
		String [] moistList = {"dry", "moist", "wet"};
		
		
		model.gardenFinal.setMoistureConditions(moistList[(int)conditionScreen.moistSlider.getValue()-1]);
		model.gardenFinal.setSunConditions(sunList[(int)conditionScreen.sunSlider.getValue()-1]);
		model.gardenFinal.setSoilConditions(soilList[(int)conditionScreen.soilSlider.getValue()-1]);
		//since there is already an instance of a gardenScreen, we do no create another one, we update the one we have
		gardenScreen.updateCondition(model.gardenFinal);
	}
}