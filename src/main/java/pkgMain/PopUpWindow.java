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

	Button resume = new Button("Resume");
	Button save = new Button("Save");
	Button restart = new Button("Restart");
	
	Image background = new Image(getClass().getResourceAsStream("/img/vfjk56.png"));
	BackgroundImage backgroundimage = new BackgroundImage(background, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT, 
               BackgroundSize.DEFAULT);
	
	/**
	 * Sets up and returns the layout VBox
	 * 
	 * @return the layout VBox of the popup window
	 */
	public VBox display() {
		VBox layout = new VBox(10);
		layout.getChildren().add(resume);
		layout.getChildren().add(save);
		layout.getChildren().add(restart);
		layout.setAlignment(Pos.CENTER);
		
		layout.setBackground(new Background(backgroundimage));
		return layout;
	}
	
}
