package pkgMain;

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
	
	Image background = new Image(getClass().getResourceAsStream("/img/bkdirt.png"));
	BackgroundImage backgroundimage = new BackgroundImage(background, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT, 
            BackgroundSize.DEFAULT);
	
	TilePane pentaGardenTile, pentaInfoTile;
	Group pentaGardenPane;
	BorderPane pentaGardenBorder;
	Button set;
	
	public Polygon hexagon;
	
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
		
		hexagon.setStroke(Color.FORESTGREEN);
		hexagon.setStrokeWidth(4);
		hexagon.setStrokeLineCap(StrokeLineCap.ROUND);
		Image img = new Image(getClass().getResourceAsStream("/img/bkdirt.png"));
		hexagon.setFill(new ImagePattern(img));
		
		pentaGardenPane.getChildren().add(hexagon);
		pentaGardenPane.getChildren().addAll(createControlAnchorsFor(hexagon.getPoints()));
		pentaGardenPane.getChildren().add(set);
	}
	
	public void createPanes() {
		pentaGardenTile = new TilePane();
		pentaInfoTile = new TilePane();
		pentaGardenPane = new Group();
		pentaGardenBorder = new BorderPane();
	}
	
	public void createScreen() {
		pentaGardenBorder.setStyle("-fx-background-color: white;");
		// pentaGardenPane.setPadding(new Insets(10, 10, 10, 10));;
		// pentaGardenPane.setBackground(new Background(backgroundimage));
		
		//pentaGardenBorder.setCenter(pentaGardenPane); 
		pentaGardenBorder.getChildren().add(pentaGardenPane);
		//pentaGardenTile.setPadding(new Insets(10, 10, 10, 10));
		//pentaGardenTile.setStyle("-fx-background-color: yellow");
		
		//pentaGardenBorder.setLeft(pentaGardenTile);
		//pentaInfoTile.setPadding(new Insets(10, 10, 10, 10));
		//pentaInfoTile.setStyle("-fx-background-color: pink;");

		//pentaGardenBorder.setTop(pentaInfoTile);
		
		//pentaInfoTile.getChildren().add(set);
		
		
	}
	
	
	//NEED TO PUT THIS SOMEWHERE ELSE
	public void createButton() {
		set = new Button("Set Garden Shape");
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
	
	class Anchor extends Circle {
		private final DoubleProperty x, y;

		Anchor(Color color, DoubleProperty x, DoubleProperty y) {
			super(x.get(), y.get(), 10);
			setFill(color.deriveColor(1, 1, 1, 0.5));
			setStroke(color);
			setStrokeWidth(2);
			setStrokeType(StrokeType.OUTSIDE);

			this.x = x;
			this.y = y;

			x.bind(centerXProperty());
			y.bind(centerYProperty());
			enableDrag();
		}

		// make a node movable by dragging it around with the mouse.
		public void enableDrag() {
			final Delta dragDelta = new Delta();
			setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override public void handle(MouseEvent mouseEvent) {
					// record a delta distance for the drag and drop operation.
					dragDelta.x = getCenterX() - mouseEvent.getX();
					dragDelta.y = getCenterY() - mouseEvent.getY();
					getScene().setCursor(Cursor.MOVE);
				}
			});
			setOnMouseReleased(new EventHandler<MouseEvent>() {
				@Override public void handle(MouseEvent mouseEvent) {
					getScene().setCursor(Cursor.HAND);
				}
			});
			setOnMouseDragged(new EventHandler<MouseEvent>() {
				@Override public void handle(MouseEvent mouseEvent) {
					double newX = mouseEvent.getX() + dragDelta.x;
					if (newX > 0 && newX < getScene().getWidth()) {
						setCenterX(newX);
					}
					double newY = mouseEvent.getY() + dragDelta.y;
					if (newY > 0 && newY < getScene().getHeight()) {
						setCenterY(newY);
					}
				}
			});
			setOnMouseEntered(new EventHandler<MouseEvent>() {
				@Override public void handle(MouseEvent mouseEvent) {
					if (!mouseEvent.isPrimaryButtonDown()) {
						getScene().setCursor(Cursor.HAND);
					}
				}
			});
			setOnMouseExited(new EventHandler<MouseEvent>() {
				@Override public void handle(MouseEvent mouseEvent) {
					if (!mouseEvent.isPrimaryButtonDown()) {
						getScene().setCursor(Cursor.DEFAULT);
					}
				}
			});
		}

		// records relative x and y co-ordinates.
		private class Delta { double x, y; }
	}

}
