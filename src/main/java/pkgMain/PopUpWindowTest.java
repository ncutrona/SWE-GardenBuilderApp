package pkgMain;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopUpWindowTest {

	public static void display() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Options");
		window.setMinWidth(250);
		window.setMinHeight(500);
		
		Button resume = new Button("Resume");
		resume.setOnAction(e -> window.close());
		Button save = new Button("Save");
		save.setOnAction(e -> window.close());
		Button restart = new Button("Restart");
		restart.setOnAction(e -> window.close());
		
		VBox layout = new VBox(10);
		layout.getChildren().add(resume);
		layout.getChildren().add(save);
		layout.getChildren().add(restart);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
	}
	
}
