package pkgMain;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CodeBreakDownScroll extends Application {
	
	public void start(Stage stage) {
		
		Image milkweed = new Image(getClass().getResourceAsStream("/img/commonMilkweed.png"));
		ImageView iv1;
		
        VBox root = new VBox();
        ScrollPane scroll = new ScrollPane();
        GridPane grid = new GridPane();
        
		root.setSpacing(10);
        root.setPadding(new Insets(10));

        scroll.setPannable(true);
		
		for (int i = 0; i < 20; i++) {
			HBox plant = new HBox();
			Text plantName = new Text("Milkweed");
			Text leps = new Text("5 leps supported");
	        plant.setSpacing(10);
	        plant.setPadding(new Insets(10));
			iv1 = new ImageView();
			iv1.setImage(milkweed);
			iv1.setPreserveRatio(true);
			iv1.setFitHeight(100);
			plant.getChildren().addAll(plantName, iv1, leps);
	        grid.addRow(i, plant);
		}
		scroll.setContent(grid);
		
	    Scene scene = new Scene(scroll, 800, 600);
	    stage.setScene(scene);
	    stage.show();
	}

}
