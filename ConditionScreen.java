package pkgMain;

import java.awt.TextField;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

public class ConditionScreen {

	private Image background = new Image(getClass().getResourceAsStream("/img/gcs.png"));
	private BackgroundImage backgroundimage = new BackgroundImage(background, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT, 
               BackgroundSize.DEFAULT);
	
	private TextField budget, gardenName;
	private Button clear, submit;
	private Text sun, moisture, soil;
	private Slider sunSlider, moistSlider, soilSlider;
	private HBox input, sunBox, moistBox, soilBox;
	
	private BorderPane border;
	private FlowPane flow;
	
	public ConditionScreen() {
		setSunSlider();
		setMoistSlider();
		setSoilSlider();
	}
	
	public void setText() {
		sun = new Text("Set Garden Sun Conditions");
		moisture = new Text("Set Garden Moisture Conditions");
		soil = new Text("Set Garden Soil Conditions");
	}
	
	public void setScreen() {
		border = new BorderPane();
		flow = new FlowPane(Orientation.VERTICAL);
		flow.setBackground(new Background(backgroundimage));
		flow.getChildren().add(sun);
		flow.getChildren().add(sunBox);
		flow.getChildren().add(moisture);
		flow.getChildren().add(moistBox);
		flow.getChildren().add(soil);
		flow.getChildren().add(soilBox);
	}
	
	public void setSunSlider() {
		sunSlider = setSlider();
		sunSlider.setLabelFormatter(new StringConverter<Double>() {
			@Override
			public String toString(Double n) {
				if (n < 1.5) {
					return "Shade";
				}else if (n > 2.5) {
					return "Full";
				}
				return "Partial";
			}
			@Override
			public Double fromString(String s) {
				switch (s) {
				case "Shade":
					return 1d;
				case "Full":
					return 3d;	
				default:
					return 2d;
				}
			}
		});
		
	}
	
	public void setMoistSlider() {
		moistSlider = setSlider();
		moistSlider.setLabelFormatter(new StringConverter<Double>() {
			@Override
			public String toString(Double n) {
				if (n < 1.5) {
					return "Sand";
				}else if (n > 2.5) {
					return "Clay";
				}
				return "Loam";

			}
			@Override
			public Double fromString(String s) {
				switch (s) {
				case "Sand":
					return 1d;
				case "Clay":
					return 3d;
				default:
					return 2d;
				}
			}
		});
	}
	
	public void setSoilSlider() {
		soilSlider = setSlider();
		soilSlider.setLabelFormatter(new StringConverter<Double>() {
			@Override
			public String toString(Double n) {
				if (n < 1.5) {
					return "Dry";
				}
				if (n > 2.5) {
					return "Wet";
				}

				else {
					return "Moist";
				}
			}

			@Override
			public Double fromString(String s) {
				switch (s) {
				case "Dry":
					return 1d;
				case "Wet":
					return 3d;	
				default:
					return 2d;
				}
			}
		});
	}
	
	public HBox setInputHBox() {
		budget = new TextField();
		//budget.setPromptText("Enter your Budget $");
		
		
		HBox box = new HBox(5);
		box.setPadding(new Insets(5, 5, 5, 5));
		box.setStyle("-fx-background-color: pink;");
		//box.getChildren().addAll(budgetLabel, budget, gardenLabel, gardenName, submit, clear);
		
		return box;
	}
	
	public HBox setSliderHbox(String phrase, Slider slide) {
		Button setSoil = new Button(phrase);
		HBox box = new HBox(5);
		box.setPadding(new Insets(5, 5, 5, 5));
		box.setStyle("-fx-background-color: transparent;");
		box.getChildren().addAll(slide ,setSoil);
		
		return box;
	}
	
	
	public Slider setSlider() {
		Slider slider = new Slider(0,3,0);
		slider.setMin(1);
		slider.setMax(3);
		slider.setValue(1);
		slider.setMinorTickCount(0);
		slider.setMajorTickUnit(1);
		slider.setSnapToTicks(true);
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		slider.setMinWidth(380);
		return slider;
	}
}
