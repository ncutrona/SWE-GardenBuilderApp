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

// CODE BREAKDOWN FOR THE CONDITIONS PAGE

public class CodeBreakDown4 extends Application {
	
	GardenState garden = new GardenState("garden",  "today", 0, false, 0 /*PentagonShape gardenShape*/);
	GardenConditions conditions = new GardenConditions(0, "", "", "");
	
	Image background = new Image(getClass().getResourceAsStream("/img/gcs.png"));
	BackgroundImage backgroundimage = new BackgroundImage(background, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT, 
               BackgroundSize.DEFAULT);
	
	
	public void setSliderSun(Slider slider) {
		
		if(!slider.isValueChanging() && slider.getValue() == 3d) {
			conditions.setSunConditions("full");
			
		}
		
		else if(!slider.isValueChanging() && slider.getValue() == 2d) {
			conditions.setSunConditions("partial");
			
		}
		
		
		else{
			conditions.setSunConditions("shade");
		}
		
	}
		
		public void setSliderMoisture(Slider slider) {
			
			if(!slider.isValueChanging() && slider.getValue() == 3d) {
				conditions.setMoistureConditions("wet");
				
			}
			
			else if(!slider.isValueChanging() && slider.getValue() == 2d) {
				conditions.setMoistureConditions("moist");
				
			}
			
			
			else{
				conditions.setMoistureConditions("dry");
			}
		
	}
		
		public void setSliderSoil(Slider slider) {
			
			if(!slider.isValueChanging() && slider.getValue() == 3d) {
				conditions.setSoilConditions("clay");
				
			}
			
			else if(!slider.isValueChanging() && slider.getValue() == 2d) {
				conditions.setSoilConditions("loam");
				
			}
			
			else{
				conditions.setSoilConditions("sand");
			}
		}
			
	
	public void start(Stage stage) {
		
		stage.setTitle("Garden Builder v. 0.01 (Alpha)");
		
		TextField budget = new TextField();
		budget.setPromptText("Enter your Budget $");
		Label budgetLabel = new Label("Budget $: ");
		TextField gardenName = new TextField();
		gardenName.setPromptText("Enter Your Garden Name: ");
		Label gardenLabel = new Label("Garden Name: ");
		
		
		Button submit = new Button("Submit");
		Button clear = new Button("Clear");
		
		HBox box = new HBox(5);
		box.setPadding(new Insets(5, 5, 5, 5));
		box.setStyle("-fx-background-color: pink;");
		box.getChildren().addAll(budgetLabel, budget, gardenLabel, gardenName, submit, clear);
		
		
		//SLider Labels
		Text sun = new Text("Set Garden Sun Conditions");
		Text moisture = new Text("Set Garden Moisture Conditions");
		Text soil = new Text("Set Garden Soil Conditions");
		
		
		//SLIDERS
		Slider slider = new Slider(0, 3, 0);
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
		
		Button setSun = new Button("Set Sun");
		HBox slider1Box = new HBox(5);
		slider1Box.setPadding(new Insets(5, 5, 5, 5));
		slider1Box.setStyle("-fx-background-color: transparent;");
		slider1Box.getChildren().addAll(slider, setSun);
		
		
		
		Slider slider2 = new Slider(0, 3, 0);
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
					conditions.setSoilConditions("sand");
					return "Sand";
				}
				if (n > 2.5) {
					conditions.setSoilConditions("clay");
					return "Clay";
				}
				else {
					conditions.setSoilConditions("loam");
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
		
		Button setSoil = new Button("Set Soil");
		HBox slider2Box = new HBox(5);
		slider2Box.setPadding(new Insets(5, 5, 5, 5));
		slider2Box.setStyle("-fx-background-color: transparent;");
		slider2Box.getChildren().addAll(slider2, setSoil);
		
		
		Slider slider3 = new Slider(0, 3, 0);
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
					conditions.setMoistureConditions("dry");
					return "Dry";
				}
				if (n > 2.5) {
					conditions.setMoistureConditions("wet");
					return "Wet";
				}
				
				else {
					conditions.setMoistureConditions("moist");
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
		
		Button setMoisture = new Button("Set Moisture");
		HBox slider3Box = new HBox(5);
		slider3Box.setPadding(new Insets(5, 5, 5, 5));
		slider3Box.setStyle("-fx-background-color: transparent;");
		slider3Box.getChildren().addAll(slider3, setMoisture);
		
		
		

		BorderPane border = new BorderPane();
		FlowPane flow = new FlowPane(Orientation.VERTICAL);
		flow.setBackground(new Background(backgroundimage));
		flow.getChildren().add(sun);
		flow.getChildren().add(slider1Box);
		flow.getChildren().add(moisture);
		flow.getChildren().add(slider2Box);
		flow.getChildren().add(soil);
		flow.getChildren().add(slider3Box);
		
		border.setTop(box);
		border.setCenter(flow);
		BorderPane.setAlignment(flow, Pos.CENTER);
		flow.setAlignment(Pos.CENTER);
		flow.setVgap(20);
		
		setSun.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			    public void handle(ActionEvent e) {
					setSliderSun(slider);
					System.out.println(conditions.getSun());
			     }
			 });
		
		setMoisture.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			    public void handle(ActionEvent e) {
					setSliderMoisture(slider3);
					System.out.println(conditions.getMoisture());
			     }
			 });
		
		setSoil.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			    public void handle(ActionEvent e) {
					setSliderSoil(slider2);
					System.out.println(conditions.getSoil());
			     }
			 });


		submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			    public void handle(ActionEvent e) {
			        if ((budget.getText() != null && !budget.getText().isEmpty() && gardenName.getText() != null && !gardenName.getText().isEmpty())) {
			        	try {
			        		int intBudget = Integer.parseInt(budget.getText());
			        		budget.setText("Your budget was set.");
			        		gardenName.setText("Your Garden Name was set.");
			        		
			        		//Garden State Budget is now updated based off of the user input.
			        		garden.setGardenBudget(intBudget);
			        		garden.setGardenName(gardenName.getText());
			        	} catch(Exception except) {
			        		budget.setText("");
			        		budget.setPromptText("Enter a valid budget $");
			        		gardenName.setText("");
			        		gardenName.setPromptText("Enter Your Garden Name: ");
			        	}
			            
			        } else {
			            budget.setPromptText("Enter a budget $");
			            gardenName.setPromptText("Enter a Garden Name: ");
			        }
			     }
			 });
		
		//Setting an action for the Clear button
		clear.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		    public void handle(ActionEvent e) {
				budget.clear();
				budgetLabel.setText(null);
				gardenName.clear();
				gardenLabel.setText(null);
		    }
		});


		
    	Scene scene = new Scene(border, 800, 600);
        stage.setScene(scene);
        stage.show();
   	
	}
	
}