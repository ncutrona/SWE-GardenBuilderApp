package pkgMain;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Inventory Screen class.
 * Handles formatting of Inventory Screen.
 * Adds three demo plants
 * 
 * @author Aidan Chao
 * @author Nicholas Cutrona
 * @author Caleb Davis
 * @author Joey Loporto
 * @author Tommy Cheung
 */
public class InvScreen {

	BorderPane invBorder;
	VBox buttonBox;
	Button PrevButtonInv;
	
	Text plantOne = new Text("Acer negudo; $6.00; Leps Supported: 3; Conditions: Clay, Full Sun, Dry; Link: www.link.com");
	Text plantTwo = new Text("Cornus florida; $20.00; Leps Supported: 5; Conditions: Clay, Full Sun, Dry; Link: www.link.com");
	Text plantThree = new Text("Betula nigra; $6.00; Leps Supported: 7; Conditions: Clay, Full Sun, Dry; Link: www.link.com");
	
	Image background = new Image(getClass().getResourceAsStream("/img/invscreen.jpg"));
	BackgroundImage backgroundimage = new BackgroundImage(background, 
			BackgroundRepeat.NO_REPEAT, 
			BackgroundRepeat.NO_REPEAT, 
			BackgroundPosition.DEFAULT, 
			BackgroundSize.DEFAULT);

	
	
	public InvScreen() {
		setButton();
		createScreen();
	}
	
	public BorderPane getScreen() {
		return invBorder;
	}
	
	public void setButton() {
		PrevButtonInv = new Button("Go BACK");
	}
	
	//This will have to change - simply hard coding for alpha!!

	//The image for the background
	
	public void addPlants() {
		
	}
	
	/**
	 * Sets up and returns the BorderPane for Inventory Screen
	 *  
	 * @return BorderPane for Inventory Screen
	 */
	public void createScreen() {
		invBorder = new BorderPane();

		plantOne.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10)); 
		plantOne.setFill(Color.WHITE);   
		plantOne.setStrokeWidth(1); 
		
		plantTwo.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10)); 
		plantTwo.setFill(Color.WHITE);   
		plantTwo.setStrokeWidth(1); 
		
		plantThree.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10)); 
		plantThree.setFill(Color.WHITE);   
		plantThree.setStrokeWidth(1); 

		
		VBox buttonBox = new VBox(10);
		buttonBox.getChildren().addAll(PrevButtonInv, plantOne, plantTwo, plantThree);

		invBorder.setCenter(buttonBox);
		buttonBox.setAlignment(Pos.CENTER);

		invBorder.setAlignment(buttonBox, Pos.CENTER);
		//loadBorder.setCenter(loadButton);
		invBorder.setBackground(new Background(backgroundimage));			
		
	}
}
