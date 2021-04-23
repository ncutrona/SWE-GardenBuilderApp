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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class CodeBreakDownScroll extends Application {
	
	public void start(Stage stage) {
		
		Image milkweed = new Image(getClass().getResourceAsStream("/img/commonMilkweed.png"));
		ImageView iv1;
		
        VBox root = new VBox();
        ScrollPane scroll = new ScrollPane();
        GridPane grid = new GridPane();
        
		root.setSpacing(20);
        root.setPadding(new Insets(10));

        scroll.setPannable(true);
        
        Text imageLabel = new Text("Image:");
        imageLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        Text plantNameLabel = new Text("Plant name:");
        plantNameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        Text lepsLabel = new Text("Leps supported:");
        lepsLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        Text conditionsLabel = new Text("Conditions:");
        conditionsLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        Text dimensionsLabel = new Text("Dimensions (ft):");
        dimensionsLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
	
        HBox labels = new HBox();
        labels.setSpacing(40);
        labels.setPadding(new Insets(10));
        labels.getChildren().addAll(imageLabel, plantNameLabel, lepsLabel, conditionsLabel, dimensionsLabel);
        grid.addRow(0, labels);
        
		for (int i = 1; i < 20; i++) {
			HBox plant = new HBox();
			Text plantName = new Text("Aslepias syriaca");
			Text leps = new Text("5");
			Text conditions = new Text("Clay, Sand");
			Text dimensions = new Text("2x2");
	        plant.setSpacing(40);
	        plant.setPadding(new Insets(10));
			iv1 = new ImageView();
			iv1.setImage(milkweed);
			iv1.setPreserveRatio(true);
			iv1.setFitHeight(100);
			plant.getChildren().addAll(iv1, plantName, leps, conditions, dimensions);
	        grid.addRow(i, plant);
		}
		scroll.setContent(grid);
		
	    Scene scene = new Scene(scroll, 800, 600);
	    stage.setScene(scene);
	    stage.show();
	}

}
