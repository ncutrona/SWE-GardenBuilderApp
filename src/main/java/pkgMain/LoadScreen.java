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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 * LoadScreen class.
 * Handles formatting of Load Screen.
 * Sets background and buttons.
 * 
 * @author Aidan Chao
 * @author Nicholas Cutrona
 * @author Caleb Davis
 * @author Joey Loporto
 * @author Tommy Cheung
 */
public class LoadScreen {
	
	Image background = new Image(getClass().getResourceAsStream("/img/intro.jpg"));
	BackgroundImage backgroundimage = new BackgroundImage(background, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT, 
               BackgroundSize.DEFAULT);
	
	BorderPane border;
	Button loadButton, startButton;
	VBox buttonBox;

	public LoadScreen() {
		createButtonBox();
		createScreen();
	}
	
	//The image for the background
	
	public void createButtonBox() {
		loadButton = new Button("Load Garden");
		startButton = new Button("Start Garden");
		buttonBox = new VBox(10);
		buttonBox.getChildren().addAll(startButton, loadButton);
	}
	
	/**
	 * Sets up and returns the BorderPane for LoadScreen
	 * 
	 * @return BorderPane for LoadScreen
	 */
	public void createScreen() {
		border = new BorderPane();
		border.setCenter(buttonBox);
		buttonBox.setAlignment(Pos.CENTER);
		border.setAlignment(buttonBox, Pos.CENTER);
		border.setBackground(new Background(backgroundimage));	
	}
	public BorderPane getScreen() {
		return border;
	}
	
	
	
	
	
}
