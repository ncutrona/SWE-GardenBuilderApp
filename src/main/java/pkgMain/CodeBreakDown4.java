package pkgMain;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class CodeBreakDown4 extends Application {
	
	public void start(Stage stage) {
		
		stage.setTitle("Garden Builder v. 0.01 (Alpha)");
		
		Slider slider = new Slider(1, 3, 2);
		slider.setMin(1);
		slider.setMax(3);
		slider.setValue(2);
		slider.setMajorTickUnit(3);
		slider.setMinorTickCount(2);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setBlockIncrement(1);
		slider.setSnapToTicks(true);
		
		Slider slider2 = new Slider(1, 3, 2);
		slider2.setMin(1);
		slider2.setMax(3);
		slider2.setValue(2);
		slider2.setMajorTickUnit(3);
		slider2.setMinorTickCount(2);
		slider2.setShowTickLabels(true);
		slider2.setShowTickMarks(true);
		slider2.setBlockIncrement(1);
		slider2.setSnapToTicks(true);
		
		TilePane tile = new TilePane(slider);
		tile.getChildren().add(slider2);

    	Scene scene = new Scene(tile, 800, 600);
        stage.setScene(scene);
        stage.show();
		
	}
}
