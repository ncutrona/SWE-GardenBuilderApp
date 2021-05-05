package pkgMain;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;

public class LepSupportedScreen {

	
	FlowPane lepInfo;
	ScrollPane scroll;
	Button goBack;
	
	Text test = new Text("TEST");
	
	
	public LepSupportedScreen() {
		createButton();
		createPanes();
		
	}
	
	public ScrollPane getScreen() {
		return scroll;
	}
	
	public void createButton() {
		goBack = new Button("Go Back");
		goBack.setStyle("-fx-padding: 8 15 15 15; -fx-background-insets: "
				+ "0,0 0 5 0, 0 0 6 0, 0 0 7 0; -fx-background-radius: "
				+ "8; -fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #add8e6 0%, #add8e6 100%),"
				+ "#add8e6,#add8e6,radial-gradient(center 50% 50%, radius 100%, #add8e6, #add8e6);"
				+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );-fx-font-weight: bold; -fx-font-size: 1.1em;");
	}
	
	
	public void createPanes() {
		lepInfo = new FlowPane();
		scroll = new ScrollPane();
		scroll.setPannable(true);
	}
	
	
	public void createScreen() {

		lepInfo.getChildren().add(test);
		scroll.setContent(lepInfo);
	}
	
	
}
