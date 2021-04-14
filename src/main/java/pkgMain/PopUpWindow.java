package pkgMain;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class PopUpWindow {

	Button resume = new Button("Resume");
	Button save = new Button("Save");
	Button restart = new Button("Restart");
	
	public VBox display() {
		VBox layout = new VBox(10);
		layout.getChildren().add(resume);
		layout.getChildren().add(save);
		layout.getChildren().add(restart);
		layout.setAlignment(Pos.CENTER);
		return layout;
	}
	
}
