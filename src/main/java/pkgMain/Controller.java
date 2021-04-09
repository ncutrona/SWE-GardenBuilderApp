package pkgMain;

import javafx.application.Application;
import javafx.stage.Stage;

public class Controller extends Application{
	Model model;
	View view;
	
	public static void main(String[] args) {
		/*
		 * Launch hands off execution to javafx to do various backend tasks
		 * such as setting up graphics and system specifics
		 */
		launch();
	}
	
	@Override
	public void start(Stage theStage){
		/*
		 * Once javafx is done setting everything up it hands exectuion back to us with
		 * the top level "stage" object, this is our main window object to
		 * do with as we see fit, in this case pass to view to take care of.
		 */
		view = new View(theStage,this);
		model = new Model();
	}
	
	void saveGardenHandler() {
		
	}
	
	void switchPageHandler() {
		
	}
	
	void popUpHandler() {
		
	}
	
	void conditionScreenHandler() {
		
	}
	
	void budgetTextHandler() {
		
	}
	
	void loadGardenHandler() {
		
	}
	
	void hoverHandler() {
		
	}
	
	void dragDropHandler() {
		
	}
	
}
