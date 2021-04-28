package pkgMain;

import java.util.ArrayList;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;

public class PentagonScreen {
	
	private Image background = new Image(getClass().getResourceAsStream("/img/bkdirt.png"));
	private BackgroundImage backgroundimage = new BackgroundImage(background, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT, 
            BackgroundSize.DEFAULT);
	
	Group pentaGardenPane;
	BorderPane pentaGardenBorder;
	Button set;
	
	Polygon hexagon;
	ObservableList<Anchor> anchors;
	
	public PentagonScreen() {
		createPanes();
		createButton();
		setHexagon();
		createScreen();
	}

	public void setHexagon() {
		hexagon = new Polygon();
		hexagon.getPoints().addAll(new Double[]{        
				200.0, 50.0, 
				400.0, 50.0, 
				450.0, 150.0,          
				400.0, 250.0, 
				200.0, 250.0,                   
				150.0, 150.0, 
	 	});
		
		hexagon.setStroke(Color.GREEN);
		hexagon.setStrokeWidth(1);
		hexagon.setStrokeLineCap(StrokeLineCap.ROUND);
		Image img = new Image(getClass().getResourceAsStream("/img/bkdirt.png"));
		hexagon.setFill(new ImagePattern(img));
		
		anchors = createControlAnchorsFor(hexagon.getPoints());
		
		pentaGardenPane.setStyle("-fx-background-color: pink;");
		pentaGardenPane.getChildren().add(hexagon);
		pentaGardenPane.getChildren().addAll(anchors);
		pentaGardenPane.getChildren().add(set);
	}
	public Polygon setAndGetHexagon(ArrayList<Double> hexPoints) {
		hexagon = new Polygon();
		hexagon.getPoints().addAll(hexPoints);
		
		hexagon.setStroke(Color.GREEN);
		hexagon.setStrokeWidth(1);
		hexagon.setStrokeLineCap(StrokeLineCap.ROUND);
		Image img = new Image(getClass().getResourceAsStream("/img/bkdirt.png"));
		hexagon.setFill(new ImagePattern(img));
		
		return hexagon;
	}
	
	public void createPanes() {
		pentaGardenPane = new Group();
		pentaGardenBorder = new BorderPane();
	}
	
	public void createScreen() {
		pentaGardenBorder.setStyle("-fx-background-color: pink;");
		pentaGardenBorder.getChildren().add(pentaGardenPane);
		
	}
	
	
	//NEED TO PUT THIS SOMEWHERE ELSE
	public void createButton() {
		set = new Button("Set Garden Shape");
		set.setStyle("-fx-padding: 8 15 15 15; -fx-background-insets: "
				+ "0,0 0 5 0, 0 0 6 0, 0 0 7 0; -fx-background-radius: "
				+ "8; -fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #add8e6 0%, #add8e6 100%),"
				+ "#add8e6,#add8e6,radial-gradient(center 50% 50%, radius 100%, #add8e6, #add8e6);"
				+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );-fx-font-weight: bold; -fx-font-size: 1.1em;");
	}
	
	public BorderPane getScreen() {
		return pentaGardenBorder;
	}
	
	private ObservableList<Anchor> createControlAnchorsFor(final ObservableList<Double> points) {
		ObservableList<Anchor> anchors = FXCollections.observableArrayList();

		for (int i = 0; i < points.size(); i+=2) {
			final int idx = i;

			DoubleProperty xProperty = new SimpleDoubleProperty(points.get(i));
			DoubleProperty yProperty = new SimpleDoubleProperty(points.get(i + 1));

			xProperty.addListener(new ChangeListener<Number>() {
				@Override public void changed(ObservableValue<? extends Number> ov, Number oldX, Number x) {
					points.set(idx, (double) x);
				}
			});

			yProperty.addListener(new ChangeListener<Number>() {
				@Override public void changed(ObservableValue<? extends Number> ov, Number oldY, Number y) {
					points.set(idx + 1, (double) y);
				}
			});

			anchors.add(new Anchor(Color.GOLD, xProperty, yProperty));
		}

		return anchors;
	}
}

