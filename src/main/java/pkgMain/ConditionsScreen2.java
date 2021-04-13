package pkgMain;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import javafx.stage.Stage;
import javafx.util.StringConverter;

public class ConditionsScreen2 {
	
	Image background = new Image(getClass().getResourceAsStream("/img/gcs.png"));
	BackgroundImage backgroundimage = new BackgroundImage(background, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT, 
               BackgroundSize.DEFAULT);
	
	Slider slider = new Slider(0, 3, 0);
	Slider slider2 = new Slider(0, 3, 0);
	Slider slider3 = new Slider(0, 3, 0);
	
	TextField budget = new TextField();
	Label budgetLabel = new Label("Budget $: ");
	TextField gardenName = new TextField();
	Label gardenLabel = new Label("Garden Name: ");
	
	Button submit = new Button("Submit");
	Button clear = new Button("Clear");
	
	Text sun = new Text("Set Garden Sun Conditions");
	Text moisture = new Text("Set Garden Moisture Conditions");
	Text soil = new Text("Set Garden Soil Conditions");
	
	Button setSoil = new Button("Set Soil");
	Button setMoisture = new Button("Set Moisture");
	Button setSun = new Button("Set Sun");
	
	Button Previous = new Button("Go Back");
	Button Continue = new Button("Continue");
	
	public BorderPane createBorder() {
		
		budget.setPromptText("Enter your Budget $");
		gardenName.setPromptText("Enter Your Garden Name: ");
		
		HBox box = new HBox(5);
		box.setPadding(new Insets(5, 5, 5, 5));
		box.setStyle("-fx-background-color: pink;");
		box.getChildren().addAll(budgetLabel, budget, gardenLabel, gardenName, submit, clear);
		
		
		//SLider Labels
		
		
		//SLIDERS
		//Slider slider = new Slider(0, 3, 0);
		slider.setMin(1);
		slider.setMax(3);
		slider.setValue(1);
		slider.setMinorTickCount(0);
		slider.setMajorTickUnit(1);
		slider.setSnapToTicks(true);
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);

		slider.setLabelFormatter(new StringConverter<Double>() {
			@Override
			public String toString(Double n) {
				if (n < 1.5) {
					return "Shade";
				}
				if (n > 2.5) {
					return "Full";
				}
				
				else {
					return "Partial";
				}
			}

			@Override
			public Double fromString(String s) {
				switch (s) {
				case "Shade":
					return 1d;
				case "Full":
					return 3d;	
				default:
					return 2d;
				}
			}
		});
		
		

		slider.setMinWidth(380);
		
		//Button setSun = new Button("Set Sun");
		HBox slider1Box = new HBox(5);
		slider1Box.setPadding(new Insets(5, 5, 5, 5));
		slider1Box.setStyle("-fx-background-color: transparent;");
		slider1Box.getChildren().addAll(slider, setSun);
		
		
		
		//Slider slider2 = new Slider(0, 3, 0);
		slider2.setMin(1);
		slider2.setMax(3);
		slider2.setValue(1);
		slider2.setMinorTickCount(0);
		slider2.setMajorTickUnit(1);
		slider2.setSnapToTicks(true);
		slider2.setShowTickMarks(true);
		slider2.setShowTickLabels(true);

		slider2.setLabelFormatter(new StringConverter<Double>() {
			@Override
			public String toString(Double n) {
				if (n < 1.5) {
					return "Sand";
				}
				if (n > 2.5) {
					return "Clay";
				}
				else {
					return "Loam";
				}
				
			}

			@Override
			public Double fromString(String s) {
				switch (s) {
				case "Sand":
					return 1d;
				case "Clay":
					return 3d;
					
				default:
					return 2d;
				}
			}
		});

		slider2.setMinWidth(380);
		
		//Button setSoil = new Button("Set Soil");
		HBox slider2Box = new HBox(5);
		slider2Box.setPadding(new Insets(5, 5, 5, 5));
		slider2Box.setStyle("-fx-background-color: transparent;");
		slider2Box.getChildren().addAll(slider2, setSoil);
		
		
		//Slider slider3 = new Slider(0, 3, 0);
		slider3.setMin(1);
		slider3.setMax(3);
		slider3.setValue(1);
		slider3.setMinorTickCount(0);
		slider3.setMajorTickUnit(1);
		slider3.setSnapToTicks(true);
		slider3.setShowTickMarks(true);
		slider3.setShowTickLabels(true);

		slider3.setLabelFormatter(new StringConverter<Double>() {
			@Override
			public String toString(Double n) {
				if (n < 1.5) {
					return "Dry";
				}
				if (n > 2.5) {
					return "Wet";
				}
				
				else {
					return "Moist";
				}
			}

			@Override
			public Double fromString(String s) {
				switch (s) {
				case "Dry":
					return 1d;
				case "Wet":
					return 3d;	
				default:
					return 2d;
				}
			}
		});

		
		slider3.setMinWidth(380);
		
		//Button setMoisture = new Button("Set Moisture");
		HBox slider3Box = new HBox(5);
		slider3Box.setPadding(new Insets(5, 5, 5, 5));
		slider3Box.setStyle("-fx-background-color: transparent;");
		slider3Box.getChildren().addAll(slider3, setMoisture, Continue, Previous);
		
		

		BorderPane conditionsBorder = new BorderPane();
		FlowPane conditionsFlow = new FlowPane(Orientation.VERTICAL);
		conditionsFlow.setBackground(new Background(backgroundimage));
		conditionsFlow.getChildren().add(sun);
		conditionsFlow.getChildren().add(slider1Box);
		conditionsFlow.getChildren().add(moisture);
		conditionsFlow.getChildren().add(slider2Box);
		conditionsFlow.getChildren().add(soil);
		conditionsFlow.getChildren().add(slider3Box);
		
		conditionsBorder.setTop(box);
		conditionsBorder.setCenter(conditionsFlow);
		BorderPane.setAlignment(conditionsFlow, Pos.CENTER);
		conditionsFlow.setAlignment(Pos.CENTER);
		conditionsFlow.setVgap(20);
		
		return conditionsBorder;
		
	}
	
}
