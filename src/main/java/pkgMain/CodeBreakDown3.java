package pkgMain;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class CodeBreakDown3 extends Application{
	
	BorderPane border;
	StackPane stack;
	FlowPane flow;
	Button loadBotton;
	Button startBotton;
	
	Image background = new Image(getClass().getResourceAsStream("/img/Intro_Background.jpg"));
	
	BackgroundImage backgroundimage = new BackgroundImage(background, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT, 
               BackgroundSize.DEFAULT);

	public void newFlow() {
		flow = new FlowPane();
		flow.setStyle("-fx-background-color: Brown;");
	}
	@Override
	public void start(Stage stage) {
		startGardenButton();
		loadGardenButton();
		
		border = new BorderPane();
		border.setTop(startBotton);
		border.setAlignment(startBotton, Pos.TOP_CENTER);
		border.setCenter(loadBotton);
		border.setBackground(new Background(backgroundimage));
		
		stage.setTitle("Garden Builder v0.01 Alpha");
		Scene scene = new Scene(border, 600, 300);
		stage.setScene(scene);
		stage.show();
		
	}
	public void startGardenButton() {
		startBotton = new Button("Start Garden");
		startBotton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				//something goes here
			}
			
		});
	}
	
	public void loadGardenButton() {
		loadBotton = new Button("Load Garden");
		loadBotton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				newFlow();
				border.setCenter(flow);
			}
			
		});
	}
	
	
}