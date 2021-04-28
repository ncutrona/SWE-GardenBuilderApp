package pkgMain;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import javafx.stage.Screen;


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
	
	Image background = new Image(getClass().getResourceAsStream("/img/mainMenu.jpg"));
	BackgroundImage backgroundimage = new BackgroundImage(background, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT, 
               new BackgroundSize(Screen.getPrimary().getVisualBounds().getHeight(), Screen.getPrimary().getVisualBounds().getWidth(), true, true, true, true));
	
	Image startButtonImg = new Image(getClass().getResourceAsStream("/img/Start_Garden.png"));
	ImageView startButtonIv = new ImageView(startButtonImg);
	
	Image loadButtonImg = new Image(getClass().getResourceAsStream("/img/Load_Garden.png"));
	ImageView loadButtonIv = new ImageView(loadButtonImg);
	
	Image lepsButtonImg = new Image(getClass().getResourceAsStream("/img/Learn_About_Leps.png"));
	ImageView lepsButtonIv = new ImageView(lepsButtonImg);
	
	BorderPane border;
	Button loadButton, startButton, learn;
	VBox buttonBox;

	/**
	 * Default constructor for LoadScreen object
	 */
	public LoadScreen() {
		createButtonBox();
		createScreen();
	}
	
	//The image for the background
	
	/**
	 * Creates load, start, and learn buttons.
	 * Add buttons to VBox.
	 */
	public void createButtonBox() {
		loadButton = new Button("", loadButtonIv);
		startButton = new Button("", startButtonIv);
		learn = new Button("", lepsButtonIv);
		buttonBox = new VBox(20);
		buttonBox.getChildren().addAll(startButton, loadButton, learn);
	}
	
	/**
	 * Sets up BorderPane for LoadScreen
	 */
	public void createScreen() {
		border = new BorderPane();
		border.setCenter(buttonBox);
		buttonBox.setAlignment(Pos.CENTER);
		border.setAlignment(buttonBox, Pos.CENTER);
		border.setBackground(new Background(backgroundimage));	
	}
	
	/**
	 * Returns the BorderPane for LoadScreen
	 * 
	 * @return BorderPane for LoadScreen
	 */
	public BorderPane getScreen() {
		return border;
	}
	
	
	
	
	
}
