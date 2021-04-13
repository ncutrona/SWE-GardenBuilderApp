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
import javafx.scene.text.Text;

public class InvScreen {

	
	Plant demoPlantOne = new Plant(6, 3, "Acer negudo", "clay", "full", "dry", 100, 100, 1, 0 ,0);
	Plant demoPlantTwo = new Plant(20, 5, "Cornus florida", "clay", "full", "dry", 100, 100, 1, 0 ,0);
	Plant demoPlantThree = new Plant(6, 7, "Betula nigra", "clay", "full", "dry", 100, 100, 1, 0 ,0);

	BorderPane invBorder = new BorderPane();
	Button PrevButtonInv = new Button("Go BACK");
	Text plantOne = new Text("Scientific Name: Acer negudo; Price: $6.00; Leps Supported: 3; Conditions: Clay, Full Sun, Dry; Link: www.link.com");
	Text plantTwo = new Text("Scientific Name: Cornus florida; Price: $20.00; Leps Supported: 5; Conditions: Clay, Full Sun, Dry; Link: www.link.com");
	Text plantThree = new Text("Scientific Name: Betula nigra; Price: $6.00; Leps Supported: 7; Conditions: Clay, Full Sun, Dry; Link: www.link.com");
	
	
	//This will have to change - simply hard coding for alpha!!

	//The image for the background
	Image background = new Image(getClass().getResourceAsStream("/img/invscreen.jpg"));
	BackgroundImage backgroundimage = new BackgroundImage(background, 
			BackgroundRepeat.NO_REPEAT, 
			BackgroundRepeat.NO_REPEAT, 
			BackgroundPosition.DEFAULT, 
			BackgroundSize.DEFAULT);

	public BorderPane createInvBorder() {

		VBox buttonBox = new VBox(10);
		buttonBox.getChildren().addAll(PrevButtonInv, plantOne, plantTwo, plantThree);

		invBorder.setCenter(buttonBox);
		buttonBox.setAlignment(Pos.CENTER);

		invBorder.setAlignment(buttonBox, Pos.CENTER);
		//loadBorder.setCenter(loadButton);
		invBorder.setBackground(new Background(backgroundimage));

		return invBorder;
			
		
}
}
