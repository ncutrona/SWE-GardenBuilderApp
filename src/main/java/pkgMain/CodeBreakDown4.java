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
		
		Slider slider = new Slider();
		slider.setMin(0);
		slider.setMax(100);
		slider.setValue(40);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(50);
		slider.setMinorTickCount(5);
		slider.setBlockIncrement(10);
		
		
		BorderPane border = new BorderPane(slider);

    	Scene scene = new Scene(border, 800, 600);
        stage.setScene(scene);
        stage.show();
		
	}
}
