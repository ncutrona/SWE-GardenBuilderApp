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

/*
 * Idea is to have the main scene, which is the object which is refrenced by view
 * Then the scene has a main node which is a stack pane,
 * choosing a stack pane so that we can have the selection screen stack like in the
 * storyboard. the stackpane will have a main content pane which will be as laid out in 
 * the storyboard.
 */
public class LoadScreen {
	
	BorderPane border;
	StackPane stack;
	private FlowPane flow;
	private Button loadButton;
	private Button startButton;
	
	//The image for the background
	Image background = new Image(getClass().getResourceAsStream("/img/Intro_Background.jpg"));
	BackgroundImage backgroundimage = new BackgroundImage(background, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT, 
               BackgroundSize.DEFAULT);
	
	public BorderPane getScreen() {
		return border;
	}
	
	public LoadScreen() {
		startGardenButton();
		loadGardenButton();
		
		border = new BorderPane();
		border.setTop(startButton);
		border.setAlignment(startButton, Pos.TOP_CENTER);
		border.setCenter(loadButton);
		border.setBackground(new Background(backgroundimage));
		
	}
	public void startGardenButton() {
		startButton = new Button("Start Garden");
		startButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				//something goes here
			}
			
		});
	}
	public void loadGardenButton() {
		loadButton = new Button("Load Garden");
		loadButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				newFlow();
				border.setCenter(flow);
			}
			
		});
	}
	public void newFlow() {
		flow = new FlowPane();
		flow.setStyle("-fx-background-color: Brown;");
	}
	
	
}
