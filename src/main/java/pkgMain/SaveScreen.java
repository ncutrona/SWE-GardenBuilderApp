package pkgMain;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

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
	
	ArrayList<SaveGarden> saved = new ArrayList<SaveGarden>();
	
	//The image for the background
	Image background = new Image(getClass().getResourceAsStream("/img/loadbk.jpg"));
	BackgroundImage backgroundimage = new BackgroundImage(background, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT, 
               BackgroundSize.DEFAULT);
	
	
	public SaveScreen() {
		try {
			createScreen();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
		
	public BorderPane getScreen() {
		return this.border;
	}
	
	public void createScreen() throws IOException, ClassNotFoundException {
		fillBox = new VBox(15);
		FileInputStream fis = new FileInputStream("Save.txt");
		
		ObjectInputStream ois = new ObjectInputStream(fis);
		ArrayList<SaveGarden> gardens = (ArrayList<SaveGarden>) ois.readObject();

		for(SaveGarden garden: gardens) {
			saved.add(garden);
			Text gardenText = new Text(setText(garden));
			gardenText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
			fillBox.getChildren().add(gardenText);
		}
		
		fis.close();
		ois.close();
		scroll.setContent(fillBox);
		scroll.setStyle("-fx-background-color: transparent;");
		border.setCenter(scroll);
		border.setTop(prevButton);
		border.setBackground(new Background(backgroundimage));
		fillBox.setAlignment(Pos.CENTER);
		
	}
	
	public String setText(SaveGarden garden) {
		return "Garden Name: " + garden.getName() + " Budget: " + garden.getBudget() + " Lep Supported: " + garden.getNumLepSupported();
	}
	
	

}
