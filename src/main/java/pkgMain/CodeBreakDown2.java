package pkgMain;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class CodeBreakDown2 extends Application{
	
    @Override
    public void start(Stage stage) {
 
    	Image im1 = new Image("/img/commonMilkweed.png");
    	ImageView iv1 = new ImageView(im1);
    	iv1.setPreserveRatio(true);
    	iv1.setFitHeight(100);

		iv1.setOnMouseDragged(event -> drag(event));
			    	
    	Scene scene = new Scene(new StackPane(iv1), 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
	public void drag(MouseEvent event) {
		System.out.println("ic mouse");
		Node n = (Node)event.getSource();
		n.setTranslateX(n.getTranslateX() + event.getX());
		n.setTranslateY(n.getTranslateY() + event.getY());
	}
}


