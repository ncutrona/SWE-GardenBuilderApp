package pkgMain;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class LoadScreen {
	
	BorderPane loadBorder = new BorderPane();
	StackPane stack;
	Button loadButton = new Button("Load Garden");
	Button startButton = new Button("Start Garden");;
	
	//The image for the background
	Image background = new Image(getClass().getResourceAsStream("/img/Intro_Background.jpg"));
	BackgroundImage backgroundimage = new BackgroundImage(background, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT, 
               BackgroundSize.DEFAULT);
	
	public BorderPane createLoadBorder() {
		
		VBox buttonBox = new VBox(10);
		buttonBox.getChildren().addAll(startButton, loadButton);
		
		loadBorder.setCenter(buttonBox);
		buttonBox.setAlignment(Pos.TOP_CENTER);
		
		loadBorder.setAlignment(buttonBox, Pos.TOP_CENTER);
		//loadBorder.setCenter(loadButton);
		loadBorder.setBackground(new Background(backgroundimage));
		
		return loadBorder;
		
	}
	
	
	public BorderPane getScreen() {
		return loadBorder;
	}
	
	
	
}
