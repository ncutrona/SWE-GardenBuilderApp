package pkgMain;

import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * View class from MVC design pattern
 *
 * @author Aidan Chao
 * @author Nicholas Cutrona
 * @author Caleb Davis
 * @author Joey Loporto
 * @author Tommy Cheung
 */
public class View {
	/*Stage theStage;
	Scene currentScene;
	
	LoadScreen loadScreen;
	GardenScreen gardenScreen;
	MainScreen mainScreen;
	
	Controller controller;
	
	final static int windowHeight = 720;;
	final static int windowWidth = 1280;
	final static String windowLabel = "Garden Builder 0.0.1";
	*/
	
	/**
	 * Constructor for View object.
	 * Setup stage and controller from passed in objects.
	 * Sets window options, scene.
	 * Shows stage window.
	 * 
	 * @param passedStage stage to be shown
	 * @param controller controller object passed to loadScreen
	 */
	/*View(Stage passedStage,Controller controller){
		// Get stage from controller and the controller itself to communicate
		this.theStage = passedStage;
		this.controller = controller;
		
		// set window options
		theStage.setTitle(windowLabel);
		theStage.setHeight(windowHeight);
		theStage.setWidth(windowWidth);
		
		// Setup screen objects need to be updated as implemented
		this.loadScreen = new LoadScreen(windowWidth,windowHeight,controller);
		this.gardenScreen = new GardenScreen();
		this.mainScreen = new MainScreen();
		
		// Set scene to loading scene
		setSceneToLoading();
		
		// Tell window to show
		theStage.show();
	}
	*/
	
	
	/**
	 * Helper method that sets currentScene from loadScreen, then passes this to Stage setScene
	 */
	/*public void setSceneToLoading() {
		// In each set scene method update stuff like
		// window height/width if needed, can handle
		// stuff like window resizing later
		this.currentScene = loadScreen.theScene;
		theStage.setScene(currentScene);
	}
	
*/
}
