package pkgMain;

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

public class LepMotivationScreen {
	
	
	
	//LAYOUT 
	//Lep Image at the Top, 3 Panes of Information - What Are Leps, What do Leps Do?, how To supprt Leps?
	
	
	Image background = new Image(getClass().getResourceAsStream("/img/lepMotivation.png")); 
	ImageView top = new ImageView(background);
	
	
	BorderPane border;
	Button back;
	VBox infoOne, infoTwo, infoThree;
	HBox data;
	VBox buttonData;
	

	
	public LepMotivationScreen() {
		createInfo();
		createScreen();
	}
	
	public void createInfo() {
		back = new Button("Back");
		infoOne = new VBox(10);
		infoTwo = new VBox(10);
		infoThree = new VBox(10);
		data = new HBox(25);
		buttonData = new VBox(25);
		
		
		//What Are Leps!
		Text what = new Text("What Are Leps?");
		what.setFont(Font.font ("Verdana", FontWeight.BOLD, 20));
		what.setFill(Color.WHITE);
		what.setStroke(Color.PINK);
		
		Text infoWhat = new Text(" - Lepidoptera is an order of insects that includes butterflies and moths.\n\n\n- About 180,000 species of the Lepidoptera are described, in 126 families and 46 superfamilies,\n 10 percent of the total described species of living organisms.\n\n\n- It is one of the most widespread and widely recognizable insect orders in the world");

		//Adding to the VBOX
		infoOne.getChildren().addAll(what, infoWhat);
		
		Text why = new Text("What do Leps Do?");
		why.setFont(Font.font ("Verdana",FontWeight.BOLD, 20));
		why.setFill(Color.WHITE);
		why.setStroke(Color.PINK);
		
		
		Text infoWhy = new Text("- In most land environments the lepidopterans are ecologically important \n because they transform large amounts of plant matter into animal matter and in turn serve as food for \n many other groups of animals.");
		
		//Adding to the VBOX
		infoTwo.getChildren().addAll(why, infoWhy);
		
		
		Text how = new Text("How To Support Leps & Native Plants?");
		how.setFont(Font.font ("Verdana",FontWeight.BOLD, 20));
		how.setFill(Color.WHITE);
		how.setStroke(Color.PINK);
		
		Text infoHow = new Text(" - Adopt a more tolerant approach to debris and spent plant material in \n he garden in order to support lepidoptera. The likelihood of seeing certain species of lepidoptera can \n be limited due to stringent habitat requirements or ranged");
		
		
		//Adding to the VBOX
		infoThree.getChildren().addAll(how, infoHow);
		
		
		//Adding to the hbox
		data.getChildren().addAll(infoOne, infoTwo, infoThree);
		
		
		Text link = new Text("https://mtcubacenter.org");
		link.setFont(Font.font ("Verdana",FontWeight.BOLD, 20));
		link.setFill(Color.WHITE);
		link.setStroke(Color.PINK);
		
		buttonData.getChildren().addAll(data, link, back);
		
	}
	
	public void createScreen() {
		border = new BorderPane();
		border.setTop(top);
		border.setCenter(buttonData);
		border.setStyle("-fx-background-color: #93e1e6;");
		data.setAlignment(Pos.CENTER);
		buttonData.setAlignment(Pos.TOP_CENTER);

		
	}
	
	public BorderPane getScreen() {
		return border;
	}
	
	

}
