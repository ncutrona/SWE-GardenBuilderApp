package pkgMain;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


/**
 * SaveScreen class.
 * Formats the Save Screen.
 * 
 * @author Aidan Chao
 * @author Nicholas Cutrona
 * @author Caleb Davis
 * @author Joey Loporto
 * @author Tommy Cheung
 */
public class SaveScreen {

	BorderPane border = new BorderPane();
	ScrollPane scroll = new ScrollPane();
	Button prevButton = new Button("Go Back");
	VBox fillBox;
	
	HashMap<String, SaveGarden> savedGarden;
	
	//The image for the background
	Image background = new Image(getClass().getResourceAsStream("/img/loadbk.jpg"));
	BackgroundImage backgroundimage = new BackgroundImage(background, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT, 
               BackgroundSize.DEFAULT);
	
	
	public SaveScreen() {
		try {
			loadGardens();
		} catch (ClassNotFoundException | IOException e) {
			
		}
		createScreen();
	}
		
	public BorderPane getScreen() {
		return this.border;
	}
	
	public void loadGardens() throws IOException, ClassNotFoundException {
		savedGarden = new HashMap<String, SaveGarden>();
		fillBox = new VBox(15);
		FileInputStream fis = new FileInputStream("Save.txt");
		
		ObjectInputStream ois = new ObjectInputStream(fis);
		HashMap <String, SaveGarden> gardens = (HashMap<String, SaveGarden>) ois.readObject();

		for(SaveGarden garden : gardens.values()) {
			savedGarden.put(garden.getName(), garden);
			Text gardenText = new Text(setText(garden));
			Button button = new Button("Load " + garden.getName());
			HBox box =  new HBox(button, gardenText);
			gardenText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
			fillBox.getChildren().add(box);
		}
		fis.close();
		ois.close();
	}
	
	public void createScreen() {
		scroll.setContent(fillBox);
		scroll.setStyle("-fx-background-color: transparent;");
		border.setCenter(scroll);
		border.setTop(prevButton);
		border.setBackground(new Background(backgroundimage));
		fillBox.setAlignment(Pos.CENTER);
	}
	
	public String setText(SaveGarden garden) {
		String text = "Garden Name: " + garden.getName() + " Budget: " + garden.getBudget() + " Lep Supported: " + garden.getNumLepSupported();
		text = text + "\nSun Condition: " + garden.getSunCondition() + " Soil Condition: " + garden.getSoilCondition() + " Moisture Condition: " + garden.getMoistCondition();
		return text;
	}
}
