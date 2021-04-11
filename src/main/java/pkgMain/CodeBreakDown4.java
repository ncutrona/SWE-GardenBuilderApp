package pkgMain;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
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
		
		BorderPane border = new BorderPane(slider);

    	Scene scene = new Scene(border, 800, 600);
        stage.setScene(scene);
        stage.show();
		
	}
}
