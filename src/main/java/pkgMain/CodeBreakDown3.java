package pkgMain;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class CodeBreakDown3 extends Application{
	
	BorderPane border;
	StackPane stack;
	FlowPane flow;

	@Override
	public void start(Stage stage) {
		Button botton = new Button("A Button");
		botton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				flow = new FlowPane();
				flow.setStyle("-fx-background-color: Brown;");
				border.setCenter(flow);
			}
			
		});
		
		Button botton2 = new Button("Start Garden");
		
		
		border = new BorderPane();
		stack = new StackPane(botton2);
		border.setCenter(botton);
		border.setTop(stack);
		
		stage.setTitle("Garden Builder v0.01 Alpha");
		Scene scene = new Scene(border, 600, 300);
		stage.setScene(scene);
		stage.show();
		
	}
	
	
}