package pkgMain;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;


/**
 * PopUpWindow class.
 * Displays a popup window
 * 
 * @author Aidan Chao
 * @author Nicholas Cutrona
 * @author Caleb Davis
 * @author Joey Loporto
 * @author Tommy Cheung
 */
public class PopUpWindow {

	Button resume,save, restart;
	VBox layout;
	
	Image background = new Image(getClass().getResourceAsStream("/img/vfjk56.png"));
	BackgroundImage backgroundimage = new BackgroundImage(background, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT, 
               BackgroundSize.DEFAULT);
	
	/**
	 * Default constructor for PopUpWindow object.
	 */
	public PopUpWindow() {
		createButtons();
		setDisplay();
	}
	
	/**
	 * Returns the VBox for the layout of PopUpWindow
	 * 
	 * @return VBox layout of PopUpWindow
	 */
	public VBox getScreen() {
		return layout;
	}
	
	/**
	 * Creates the buttons resume save and restart.
	 */
	public void createButtons() {
		resume = new Button("Resume");
		save = new Button("Save");
		restart = new Button("Restart");
		styleButtons();
	}
	public void styleButtons() {
		resume.setStyle("-fx-padding: 8 15 15 15; -fx-background-insets: "
				+ "0,0 0 5 0, 0 0 6 0, 0 0 7 0; -fx-background-radius: "
				+ "8; -fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #ffb6c1 0%, #ffb6c1 100%),"
				+ "#ffb6c1,#ffb6c1,radial-gradient(center 50% 50%, radius 100%, #ffb6c1, #ffb6c1);"
				+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );-fx-font-weight: bold; -fx-font-size: 1.1em;");
		save.setStyle("-fx-padding: 8 15 15 15; -fx-background-insets: "
				+ "0,0 0 5 0, 0 0 6 0, 0 0 7 0; -fx-background-radius: "
				+ "8; -fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #ffb6c1 0%, #ffb6c1 100%),"
				+ "#ffb6c1,#ffb6c1,radial-gradient(center 50% 50%, radius 100%, #ffb6c1, #ffb6c1);"
				+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );-fx-font-weight: bold; -fx-font-size: 1.1em;");
		restart.setStyle("-fx-padding: 8 15 15 15; -fx-background-insets: "
				+ "0,0 0 5 0, 0 0 6 0, 0 0 7 0; -fx-background-radius: "
				+ "8; -fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #ffb6c1 0%, #ffb6c1 100%),"
				+ "#ffb6c1,#ffb6c1,radial-gradient(center 50% 50%, radius 100%, #ffb6c1, #ffb6c1);"
				+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );-fx-font-weight: bold; -fx-font-size: 1.1em;");
	}
	
	/**
	 * adds the buttons to the layout VBox, sets alignment and background.
	 */
	public void setDisplay() {
		layout = new VBox(10);
		layout.getChildren().add(resume);
		layout.getChildren().add(save);
		layout.getChildren().add(restart);
		layout.setAlignment(Pos.CENTER);
		
		layout.setBackground(new Background(backgroundimage));
	}
	
}
