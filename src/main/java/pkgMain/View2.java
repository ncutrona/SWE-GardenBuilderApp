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
import javafx.stage.Stage;

public class View2{
	
	private Stage stage;
	private Scene scene;
	
	LoadScreen loadScreen;
	ConditionScreen conditionScreen;
	GardenScreen gardenScreen;
	InventoryScreen invScreen;
	
	public View2(Stage stage) {
		this.stage = stage;
		loadScreen = new LoadScreen();
		
		scene = new Scene(loadScreen.getScreen(), 600, 500);
		loadScreenHandler();
		this.stage.setScene(scene);
	}
	
	public void conditionScreenHandler() {
		
	}
	public void loadScreenHandler() {
		loadScreen.loadButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				loadScreen.newFlow();
				loadScreen.border.setCenter(loadScreen.flow);
			}
			
		});
		loadScreen.startButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				conditionScreen = new ConditionScreen();
				stage.setScene(new Scene(conditionScreen.getScreen(), 600, 500));
			}
		});
		
	}
	
}