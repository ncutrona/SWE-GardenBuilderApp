package pkgMain;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;

/**
 * LepMotivationScreen class.
 * Handles formatting of the Lep Motivation Screen.
 * Displays information about the motivation around leps.
 * 
 * @author Aidan Chao
 * @author Nicholas Cutrona
 * @author Caleb Davis
 * @author Joey Loporto
 * @author Tommy Cheung
 */
public class LepMotivationScreen {
	
	
	
	//LAYOUT 
	//Lep Image at the Top, 3 Panes of Information - What Are Leps, What do Leps Do?, how To supprt Leps?
	
	
	Image background = new Image(getClass().getResourceAsStream("/img/lepMotivation.png")); 
	ImageView top = new ImageView(background);
	
	Image lep = new Image(getClass().getResourceAsStream("/img/lepPlace.png"));
	ImageView lepIv = new ImageView(lep);
	
	BorderPane border;
	Button back;
	VBox infoOne, infoTwo, infoThree;
	HBox data;
	VBox buttonData;
	HBox lepBox;
	Hyperlink mtCubaLink;
	
	

	/**
	 * default constructor for LepMotivationScreen.
	 * first creates info to display, then formats info to display.
	 */
	public LepMotivationScreen() {
		createInfo();
		createScreen();
	}
	
	/**
	 * Method to create the info to display on LepMotivationScreen.
	 * Also sets buttons, font formatting.
	 */
	public void createInfo() {
		back = new Button("Back");
		back.setStyle("-fx-padding: 8 15 15 15; -fx-background-insets: "
				+ "0,0 0 5 0, 0 0 6 0, 0 0 7 0; -fx-background-radius: "
				+ "8; -fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #ffb6c1 0%, #ffb6c1 100%),"
				+ "#ffb6c1,#ffb6c1,radial-gradient(center 50% 50%, radius 100%, #ffb6c1, #ffb6c1);"
				+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );-fx-font-weight: bold; -fx-font-size: 1.1em;");
		
		infoOne = new VBox(10);
		infoTwo = new VBox(10);
		infoThree = new VBox(10);
		data = new HBox(25);
		buttonData = new VBox(25);
		
		top.setFitWidth(Screen.getPrimary().getVisualBounds().getWidth());
		
		
		//What Are Leps!
		Text what = new Text("What Are Leps?");
		what.setFont(Font.font ("Verdana", FontWeight.BOLD, 25));
		what.setFill(Color.WHITE);
		what.setStroke(Color.PINK);
		
		Text infoWhat = new Text(" - Lepidoptera is an order of insects that includes\n  butterflies and moths.\n\n\n- About 180,000 species of the Lepidoptera are described, \n  in 126 families and 46 superfamilies, 10 percent of the \n  total described species of living organisms.\n\n\n- It is one of the most widespread and widely \n  recognizable insect orders in the world.");
		infoWhat.setFill(Color.BEIGE);
		infoWhat.setFont(Font.font ("Verdana", FontWeight.BOLD, 17));
		infoWhat.setStrokeWidth(0.75);
		//infoWhat.setStroke(Color.BLACK);
		
		//Adding to the VBOX
		infoOne.getChildren().addAll(what, infoWhat);
		
		Text why = new Text("What do Leps Do?");
		why.setFont(Font.font ("Verdana",FontWeight.BOLD, 25));
		why.setFill(Color.WHITE);
		why.setStroke(Color.PINK);
		
		
		Text infoWhy = new Text("- In most land environments the lepidopterans are\n  ecologically important because they transform large\n  amounts of plant matter into animal matter and\n  in turn serve as food for many other\n  groups of animals.");
		infoWhy.setFill(Color.BEIGE);
		infoWhy.setFont(Font.font ("Verdana", FontWeight.BOLD, 17));
		infoWhy.setStrokeWidth(0.75);
		//infoWhy.setStroke(Color.BLACK);
		
		//Adding to the VBOX
		infoTwo.getChildren().addAll(why, infoWhy);
		
		
		Text how = new Text("How To Support Leps & Native Plants?");
		how.setFont(Font.font ("Verdana",FontWeight.BOLD, 25));
		how.setFill(Color.WHITE);
		how.setStroke(Color.PINK);
		
		Text infoHow = new Text(" - Adopt a more tolerant approach to debris and spent plant\n  material in the garden in order to support lepidoptera.\n  The likelihood of seeing certain species of lepidoptera\n  can be limited due to stringent habitat\n  requirements or ranged.");
		infoHow.setFill(Color.BEIGE);
		infoHow.setFont(Font.font ("Verdana", FontWeight.BOLD, 17));
		infoHow.setStrokeWidth(0.75);
		//infoHow.setStroke(Color.BLACK);
		
		//Adding to the VBOX
		infoThree.getChildren().addAll(how, infoHow);
		
		
		//Adding to the hbox
		data.getChildren().addAll(infoOne, infoTwo, infoThree);
		data.setAlignment(Pos.CENTER);
		
		
		mtCubaLink = new Hyperlink();
		mtCubaLink.setText("https://mtcubacenter.org");
		mtCubaLink.resize(20, 20);
		mtCubaLink.setTextFill(Color.GREEN);
		
		Font courierNewFontBold36 = Font.font("Courier New", FontWeight.BOLD, 36);
		mtCubaLink.setFont(courierNewFontBold36);
		
		mtCubaLink.setOnAction(e -> {
		    if(Desktop.isDesktopSupported())
		    {
		        try {
		            Desktop.getDesktop().browse(new URI("https://mtcubacenter.org"));
		        } catch (IOException e1) {
		            e1.printStackTrace();
		        } catch (URISyntaxException e1) {
		            e1.printStackTrace();
		        }
		    }
		});
		
		
		buttonData.getChildren().addAll(data, mtCubaLink, back);
		buttonData.setAlignment(Pos.TOP_CENTER);
		
		lepBox = new HBox();
		lepBox.getChildren().add(lepIv);
		lepBox.setAlignment(Pos.CENTER);
		
		
		
	}
	
	/**
	 * setups up BorderPane and formats for LepMotivationScreen
	 */
	public void createScreen() {
		border = new BorderPane();
		border.setTop(top);
		border.setCenter(buttonData);
		border.setBottom(lepBox);
		border.setStyle("-fx-background-color: #93e1e6;");
	
	}
	
	/**
	 * returns the BorderPane for LepMotivationScreen
	 * 
	 * @return BorderPane for LepMotivationScreen
	 */
	public BorderPane getScreen() {
		return border;
	}
	
	

}
