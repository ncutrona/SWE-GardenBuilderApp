package pkgMain;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;

public class CodeBreakDownPopUp extends Application {
	
	Stage window;
	Scene scene1;

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage){
		window = stage;
		Button optionsButton = new Button("Options");
		optionsButton.setTooltip(new Tooltip("test"));
		BorderPane border = new BorderPane(optionsButton);
		scene1 = new Scene(border, 700, 700);
		window.setScene(scene1);
		window.show();
		
//		Popup popup = new Popup();
//		Label label = new Label("Popup");
//		label.setStyle(" -fx-background-color: black;");
//		popup.getContent().add(label);
		
		optionsButton.setOnAction(e -> PopUpWindowTest.display());
		
	}
}
