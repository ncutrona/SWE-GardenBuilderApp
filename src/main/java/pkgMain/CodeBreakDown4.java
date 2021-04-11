package pkgMain;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import javafx.scene.layout.TilePane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import javafx.stage.Stage;

// CODE BREAKDOWN FOR THE CONDITIONS PAGE

public class CodeBreakDown4 extends Application {
	
	public void start(Stage stage) {
		
		stage.setTitle("Garden Builder v. 0.01 (Alpha)");
		
		TextField budget = new TextField();
		budget.setPromptText("Enter your Budget $");
		Label budgetLabel = new Label("Budget $: ");
		
		Button submit = new Button("Submit");
		Button clear = new Button("Clear");
		
		HBox box = new HBox(5);
		box.setPadding(new Insets(5, 5, 5, 5));
		box.getChildren().addAll(budgetLabel, budget, submit, clear);
		
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

		BorderPane border = new BorderPane(slider);
		border.setTop(box);
		
		submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			    public void handle(ActionEvent e) {
			        if ((budget.getText() != null && !budget.getText().isEmpty())) {
			        	try {
			        		int intBudget = Integer.parseInt(budget.getText());
			        		budget.setText("Your budget was set.");
			        	} catch(Exception except) {
			        		budget.setText("");
			        		budget.setPromptText("Enter a valid budget $");
			        	}
			            
			        } else {
			            budget.setPromptText("Enter a budget $");
			        }
			     }
			 });


    	Scene scene = new Scene(tile, 800, 600);
        stage.setScene(scene);
        stage.show();
   	
	}
	
}
