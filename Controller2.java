package pkgMain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;

public class Controller2 extends Application{
	
	private Model2 model;
	private View2 view;

	@Override
	public void start(Stage stage) {
		// TODO Auto-generated method stub
		view = new View2(stage);
		model = new Model2();
		
		stage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}
	
	
}