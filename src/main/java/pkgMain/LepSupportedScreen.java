package pkgMain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * LepSupportedScreen class.
 * Screen that displays the leps supported by garden
 *
 * @author Aidan Chao
 * @author Nicholas Cutrona
 * @author Caleb Davis
 * @author Joey Loporto
 * @author Tommy Cheung
 */
public class LepSupportedScreen {

	
	TilePane lepInfoTile, lepControlTile;
	ScrollPane scroll;
	BorderPane lepBorder;
	Button goBack;
	HashMap<String, Image> lepImagesMap = new HashMap<String, Image>();
	
	Text test = new Text("TEST");
	
	
	/**
	 * default constructor for LepSupportedScreen object,
	 * Calls helper methods to create buttons, panes, and screen.
	 */
	public LepSupportedScreen() {
		createButton();
		createPanes();
		createScreen();
		
	}
	
	/**
	 * returns ScrollPane scroll of LepSupportedScreen
	 * @return ScrollPane scroll
	 */
	public ScrollPane getScreen() {
		return scroll;
	}
	
	/**
	 * Creates and formats back button for LepSupportedScreen
	 */
	public void createButton() {
		goBack = new Button("Go Back");
		goBack.setStyle("-fx-padding: 8 15 15 15; -fx-background-insets: "
				+ "0,0 0 5 0, 0 0 6 0, 0 0 7 0; -fx-background-radius: "
				+ "8; -fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #add8e6 0%, #add8e6 100%),"
				+ "#add8e6,#add8e6,radial-gradient(center 50% 50%, radius 100%, #add8e6, #add8e6);"
				+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );-fx-font-weight: bold; -fx-font-size: 1.1em;");
	}
	
	
	/**
	 * Creates and formats TilePanes, BorderPane, and ScrollPane for LepSupportedScreen.
	 */
	public void createPanes() {
		lepInfoTile = new TilePane();
		lepControlTile = new TilePane();
		lepBorder = new BorderPane();
		scroll = new ScrollPane();
		scroll.setPannable(true);
	}
	
	
	/**
	 * Formats attributes, text, and positions for the LepSupportedScreen
	 */
	public void createScreen() {
		lepInfoTile.setStyle("-fx-background-color: beige;");
		lepControlTile.setStyle("-fx-background-color: beige;");
		Text lepsSupportedTitle = new Text("Featured Garden Leps Supported");
		lepsSupportedTitle.setFont(Font.font ("Verdana", FontWeight.BOLD, 50));
		lepsSupportedTitle.setFill(Color.GREEN);
		lepsSupportedTitle.setStroke(Color.PINK);
		HBox backAndTitle = new HBox();
		backAndTitle.setSpacing(200);
        backAndTitle.setPadding(new Insets(10));
        backAndTitle.getChildren().addAll(goBack, lepsSupportedTitle);
		lepControlTile.getChildren().add(backAndTitle);
		lepBorder.setCenter(lepInfoTile);
		lepBorder.setTop(lepControlTile);
        scroll.setFitToHeight(true);
        scroll.setFitToWidth(true);
		scroll.setContent(lepBorder);
	}
	
	/**
	 * Updates values in the Lep Encyclopedia from passed in ArrayList
	 * 
	 * @param lepsInGardenArray ArrayList-String- of leps in the garden
	 */
	public void updateLepEncyclopedia(ArrayList<String> lepsInGardenArray) {
		List<String> lepsList = lepsInGardenArray;
		Set<String> uniqueLepsList = new HashSet<String>(lepsList);
        for (String lep : uniqueLepsList) {
        	VBox lepVB = new VBox();
	        lepVB.setSpacing(5);
	        lepVB.setPadding(new Insets(10));
	        Text lepName = new Text(lep);
	        
			ImageView iv1;
			Image lepView = lepImagesMap.get(lep);
			
			iv1 = new ImageView();
			iv1.setImage(lepView);
			iv1.setFitWidth(250);
			iv1.setFitHeight(250);
	        
	        lepVB.getChildren().addAll(iv1, lepName);
	        lepInfoTile.getChildren().add(lepVB);
        }
	}
	
	/**
	 * Creates a list of lep images that are in the garden
	 * 
	 * @param lepsInGardenArray ArrayList-String- of leps in the garden
	 */
	public void createLepImageList(ArrayList<String> lepsInGardenArray) {
		for(String lep : lepsInGardenArray) {
			lep = lep.strip();

			Image image = new Image(getClass().getResourceAsStream("/img/" + lep.replace(" ", "_") +".png"));
			lepImagesMap.put(lep, image);
		}
	}

	/**
	 * returns images of leps that are compatible with the garden
	 * @return lepImagesMap HashMap-String, Image- of lep images
	 */
	public HashMap<String, Image> returnLepImageList() {
		return lepImagesMap;
	}
	
}
