package pkgMain;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class PentagonScreen {
	
	Image background = new Image(getClass().getResourceAsStream("/img/bkdirt.png"));
	BackgroundImage backgroundimage = new BackgroundImage(background, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT, 
            BackgroundSize.DEFAULT);
	
	TilePane pentaGardenTile, pentaInfoTile;
	Group pentaGardenPane;
	BorderPane pentaGardenBorder;
	Button set;
	
	public PentagonScreen() {
		createPanes();
		createButton();
		createScreen();
	}
	
	public void createPanes() {
		pentaGardenTile = new TilePane();
		pentaInfoTile = new TilePane();
		pentaGardenPane = new Group();
		pentaGardenBorder = new BorderPane();
	}
	
	public void createScreen() {
		pentaGardenBorder.setStyle("-fx-background-color: white;");
		// pentaGardenPane.setPadding(new Insets(10, 10, 10, 10));;
		// pentaGardenPane.setBackground(new Background(backgroundimage));
		
		pentaGardenBorder.setCenter(pentaGardenPane); 
		pentaGardenTile.setPadding(new Insets(10, 10, 10, 10));
		pentaGardenTile.setStyle("-fx-background-color: yellow");
		
		pentaGardenBorder.setLeft(pentaGardenTile);
		pentaInfoTile.setPadding(new Insets(10, 10, 10, 10));
		pentaInfoTile.setStyle("-fx-background-color: pink;");	

		pentaGardenBorder.setTop(pentaInfoTile);
		
		pentaInfoTile.getChildren().add(set);
	}
	
	public void createButton() {
		set = new Button("Set Garden Shape");
	}
	
	public BorderPane getScreen() {
		return pentaGardenBorder;
	}

}
