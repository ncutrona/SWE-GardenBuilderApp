package pkgMain;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.beans.property.*;
import javafx.beans.value.*;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.print.PrinterJob;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CodeBreakDown extends Application {
	Stage window;
	Scene pdf;
	
	@Override
	public void start(Stage primaryStage) {
		
		Button btn = new Button("Download PDF");
		window = primaryStage;
		BorderPane border = new BorderPane();
		Text t = new Text("TESTING PDF");
		//border.getChildren().add(t);
		border.setTop(btn);
		border.setCenter(t);

		
		btn.setOnAction(e-> {
			PrinterJob job = PrinterJob.createPrinterJob();
			if(job != null){
				System.out.println("Get here?");
				job.showPrintDialog(window); // Window must be your main Stage
				job.printPage(border);
				job.endJob();
				System.out.println("Get here?");
			}
		});
		 
		window.setTitle("PDF TEST");
		pdf = new Scene(border, 500, 500);
		window.setScene(pdf);
		window.show();
	}
	
	public static void main(String[] args) {
		launch();
	}
	
	
	
}


