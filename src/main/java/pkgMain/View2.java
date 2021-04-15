package pkgMain;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
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
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class View2{
	
	private Stage stage , popUpStage;
	private Scene scene;
	
	LoadScreen loadScreen;
	ConditionScreen conditionScreen;
	GardenScreen gardenScreen;
	InvScreen invScreen;
	SaveScreen saveScreen;
	PopUpWindow popup;
	
	public View2() {
		//this.stage = stage;
		
		//startLoadScreen();
		
		//stage.setScene(scene);
	}
	
	
	public Scene startLoadScreen() {
		loadScreen = new LoadScreen();
		scene = new Scene(loadScreen.getScreen(), 1000, 600);
		return scene;
	}
	public Scene startSaveScreen() {
		saveScreen = new SaveScreen();
		scene = new Scene(saveScreen.createLoadingBorder(),800, 600);
		return scene;
	}
	public Scene startConditionScreen() {
		conditionScreen = new ConditionScreen();
		scene = new Scene(conditionScreen.getScreen(), 1000, 600);
		return scene;
	}
	public Scene startGardenScreen(GardenConditions c) {
		gardenScreen = new GardenScreen(c);
		scene = new Scene(gardenScreen.getScreen(), 1000, 600);
		return scene;
	}
	public void startPopUp() {
		Stage popupStage = new Stage();
		popup = new PopUpWindow();
		Scene scene = new Scene(popup.display());
		
		popupStage.initModality(Modality.APPLICATION_MODAL);	
		popupStage.setTitle("Options");
		popupStage.setMinWidth(250);
		popupStage.setMinHeight(500);
		popupStage.setScene(scene);
		popupStage.showAndWait(); 
	}
	
	public void closePopUpStage() {
		popUpStage.close();
	}
	
	public Scene startInventoryScreen() {
		invScreen = new InvScreen();
		scene = new Scene(invScreen.createInvBorder(),800, 600);
		return scene;
	}
	
	
}