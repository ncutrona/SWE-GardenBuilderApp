package pkgMain;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class LoadScreen {
	
	/*
	 * Idea is to have the main scene, which is the object which is refrenced by view
	 * Then the scene has a main node which is a stack pane,
	 * choosing a stack pane so that we can have the selection screen stack like in the
	 * storyboard. the stackpane will have a main content pane which will be as laid out in 
	 * the storyboard.
	 */
	
	public Scene theScene;
	StackPane rootPane;
	BorderPane mainContentPane;
	
	Controller controller;
	
	
	int sceneWidth;
	int sceneHeight;
	
	LoadScreen(int sceneWidth, int sceneHeight,Controller controller){
		// Setup locals
		this.sceneWidth = sceneWidth;
		this.sceneHeight = sceneHeight;
		this.controller = controller;
		// Setup panes
		this.rootPane = new StackPane();
		rootPane.setPrefSize(sceneWidth, sceneHeight);

		this.mainContentPane = new BorderPane();
		mainContentPane.setPrefSize(sceneWidth, sceneHeight);
		// Add main content as sub pane to our stack pane root
		rootPane.getChildren().add(mainContentPane);
		
		// Setup scene adding our stackpane os our root display object
		this.theScene = new Scene(rootPane,sceneWidth,sceneHeight);
		
		
		
	}
	
	
	
}
