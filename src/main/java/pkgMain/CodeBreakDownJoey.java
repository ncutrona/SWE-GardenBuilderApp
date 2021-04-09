package pkgMain;

import java.io.File;

import javax.swing.JFileChooser;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
/*
 * 
 */
public class CodeBreakDownJoey extends Application{

	
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage theStage){
		
		// Event handlers ie button presses
	    EventHandler<ActionEvent> LoadButtonPressed = new EventHandler<ActionEvent>() {
	        public void handle(ActionEvent e)
	        {
	            JFileChooser FC = new JFileChooser();
	            int returnVal = FC.showOpenDialog(null);
	            if(returnVal == JFileChooser.APPROVE_OPTION) {
	            	File file = FC.getSelectedFile();
	            	System.out.println("Chosen file: " + file.getAbsolutePath());
	            }
	        }
	    };
	    
	    EventHandler<ActionEvent> SaveButtonPressed = new EventHandler<ActionEvent>() {
	        public void handle(ActionEvent e)
	        {
	            JFileChooser FC = new JFileChooser();
	            int returnVal = FC.showSaveDialog(null);
	            if(returnVal == JFileChooser.APPROVE_OPTION) {
	            	File file = FC.getSelectedFile();
	            	System.out.println("Chosen file: " + file.getAbsolutePath());
	            }
	        }
	    };
	    
	    
		// Setup scene and pane
		Scene theScene;
		FlowPane mainPane = new FlowPane();
		
		// Create button and setup action handlers and textfield
		Button LoadFile = new Button("Load file");
		LoadFile.setOnAction(LoadButtonPressed);
		Button SaveFile = new Button("Save File");
		SaveFile.setOnAction(SaveButtonPressed);
		
		String defaultText = "{test:100,}";
		TextArea textEdit = new TextArea(defaultText);
		textEdit.setPrefSize(400, 300);
		
		// Add stuff to pane
		mainPane.getChildren().addAll(LoadFile,SaveFile,textEdit);
		
		// Setup and display scene
		theScene = new Scene(mainPane,500,500);
		theStage.setScene(theScene);
		theStage.show();
		
	}
	
	
}
