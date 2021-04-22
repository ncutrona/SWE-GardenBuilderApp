package pkgMain;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.beans.property.*;
import javafx.beans.value.*;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;




/** Drag the anchors around to change a polygon's points. */
public class CodeBreakDown extends Application {
	Button setShape = new Button("submit");
	StackPane border;
	public Polygon hexagon = new Polygon();
	public static void main(String[] args) throws Exception { launch(args); }

	// main application layout logic.
	@Override public void start(final Stage stage) throws Exception {
    	
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
		hexagon.setFill(Color.CORNSILK.deriveColor(0, 1.2, 1, 0.6));
		
		Group root = new Group();
		root.getChildren().add(hexagon);
		root.getChildren().addAll(createControlAnchorsFor(hexagon.getPoints()));
		root.getChildren().add(setShape);
		root.relocate(400, 400);
		
		
		setShape.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				border = new StackPane();
				border.getChildren().add(hexagon);
				Scene next = new Scene(border, 800, 800);
				stage.setScene(next);
				
			}
			
		});
		
		stage.setTitle("Hexagon Manipulation Sample");
		stage.setScene(
				new Scene(
						root,
						800, 800, Color.ALICEBLUE
						)
				);
		stage.show();
	}


	// @return a list of anchors which can be dragged around to modify points in the format [x1, y1, x2, y2...]
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

	// a draggable anchor displayed around a point.
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
		private void enableDrag() {
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
