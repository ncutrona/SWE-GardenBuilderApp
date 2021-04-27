package pkgMain;

import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

/**
 * Anchor class, used to model the shape of the garden.
 * 
 * @author Aidan Chao
 * @author Nicholas Cutrona
 * @author Caleb Davis
 * @author Joey Loporto
 * @author Tommy Cheung
 */
public class Anchor extends Circle {
	private final DoubleProperty x, y;

	
	/**
	 * Creates new Anchor object.
	 * Sets color, coords from params.
	 * @param color Color color of anchor
	 * @param x center X position for anchor
	 * @param y center Y position for anchor
	 */
	public Anchor(Color color, DoubleProperty x, DoubleProperty y) {
		super(x.get(), y.get(), 10);
		setFill(color.deriveColor(1, 1, 1, 0.5));
		setStroke(color);
		setStrokeWidth(2);
		setStrokeType(StrokeType.OUTSIDE);

		this.x = x;
		this.y = y;

		x.bind(centerXProperty());
		y.bind(centerYProperty());
		//enableDrag();
	}
}