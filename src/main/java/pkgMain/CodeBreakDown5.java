package pkgMain;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CodeBreakDown5 extends Application {

	Stage window;
	Scene scene1, scene2;
	
	
	//this class is used for switching screens
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		Label label1 = new Label("Welcome to the First Scene");
		Label label2 = new Label("Welcome to the Second Screen");
		
		Button button1 = new Button("Go to Scene 2");
		button1.setOnAction(e-> window.setScene(scene2));
		
		VBox layout1 = new VBox(20);
		layout1.getChildren().addAll(label1, button1);
		scene1 = new Scene(layout1, 600, 600);
		
		
		Button button2 = new Button("Go Back");
		button2.setOnAction(e-> window.setScene(scene1));
		
		VBox layout2 = new VBox(20);
		layout2.getChildren().addAll(label2, button2);
		scene2 = new Scene(layout2, 700, 700);
		
		
		window.setScene(scene1);
		window.setTitle("Window TITLE");
		window.show();
		
	}
	
	
}
