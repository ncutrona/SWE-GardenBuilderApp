package pkgMain;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;


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
            new BackgroundSize(Screen.getPrimary().getVisualBounds().getHeight(), Screen.getPrimary().getVisualBounds().getWidth(), true, true, true, true));
	
	
	/**
	 * Defaults constructor for SaveScreen object.
	 * Tries to load gardens from file.
	 * Creates the screen.
	 */
	public SaveScreen() {
		try {
			loadGardens();
		} catch (ClassNotFoundException | IOException e) {
			
		}
		createScreen();
	}
		
	/**
	 * Returns the BorderPane for SaveScreen
	 * 
	 * @return BorderPane for SaveScreen
	 */
	public BorderPane getScreen() {
		return this.border;
	}
	
	/**
	 * Loads gardens from file via Serializable interface
	 */
	public void loadGardens() throws IOException, ClassNotFoundException {
		savedGarden = new HashMap<String, SaveGarden>();
		fillBox = new VBox(15);
		FileInputStream fis = new FileInputStream("Save.txt");
		
		ObjectInputStream ois = new ObjectInputStream(fis);
		HashMap <String, SaveGarden> gardens = (HashMap<String, SaveGarden>) ois.readObject();
		boolean one = true;

		for(SaveGarden garden : gardens.values()) {
			savedGarden.put(garden.getName(), garden);
			Text gardenText = new Text(setText(garden));
			Button button = new Button("Load " + garden.getName());
			HBox box =  new HBox(button, gardenText);
			box.setMinWidth(Screen.getPrimary().getVisualBounds().getWidth());
			if(one) {
				box.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
			}
			gardenText.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 20));
			fillBox.getChildren().add(box);
			
			one = !one;
		}
		fis.close();
		ois.close();
	}
	
	/**
	 * Creates the screen for SaveScreen.
	 * Sets up ScrollPane, BorderPane.
	 */
	public void createScreen() {
		
		
		
		scroll.setContent(fillBox);
		//NEED TO FIGURE THIS OUT.
		scroll.setStyle("-fx-background: #b5dfff");
		//scroll.setBackground(new Background(backgroundimage));
		border.setCenter(scroll);
		border.setTop(prevButton);
		border.setStyle("-fx-background: #5cb1f2");
		//border.setBackground(new Background(backgroundimage));
		fillBox.setAlignment(Pos.CENTER);
	}
	
	/**
	 * returns the text to display based on garden
	 * 
	 * @param garden SaveGarden, garden save from file
	 * @return text to display
	 */
	public String setText(SaveGarden garden) {
		String text = "\tGarden Name: " + garden.getName() + "\t\tBudget: " + garden.getBudget() + "\t\tLep Supported: " + garden.getNumLepSupported();
		text = text + "\n\tSun Condition: " + garden.getSunCondition() + "\t\tSoil Condition: " + garden.getSoilCondition() + "\t\tMoisture Condition: " + garden.getMoistCondition();
		text = text + "\n\tLength: " + garden.getLength() + "\t\tWidth: " + garden.getWidth();
		return text;
	}
}
