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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SaveScreen {

	BorderPane loadingBorder = new BorderPane();
	Button loadingButton = new Button("Load Garden in Progress");
	Button prevButton = new Button("Go Back");
	Button LoadingButton2 = new Button("Load Garden in Progress");
	Text saved1 = new Text("Test Garden | Saved: 4/14/2021");
	Text saved2 = new Text("Test Garden Original | Saved 4/11/2021");
	
	//The image for the background
	Image background = new Image(getClass().getResourceAsStream("/img/loadbk.jpg"));
	BackgroundImage backgroundimage = new BackgroundImage(background, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT, 
               BackgroundSize.DEFAULT);
	
	public BorderPane createLoadingBorder() {
		
		VBox fillBox = new VBox(15);
		
		HBox buttonbox2 = new HBox(10);
		buttonbox2.getChildren().addAll(saved2, LoadingButton2);
		HBox buttonBox = new HBox(10);
		buttonBox.getChildren().addAll(saved1, loadingButton);
		
		buttonBox.setAlignment(Pos.CENTER);
		buttonbox2.setAlignment(Pos.CENTER);
		
		fillBox.getChildren().addAll(buttonBox, buttonbox2);
		
		loadingBorder.setCenter(fillBox);
		fillBox.setAlignment(Pos.CENTER);
		
		loadingBorder.setAlignment(fillBox, Pos.CENTER);
		loadingBorder.setTop(prevButton);
		loadingBorder.setBackground(new Background(backgroundimage));
		
		return loadingBorder;
		
	}
	
	

}
