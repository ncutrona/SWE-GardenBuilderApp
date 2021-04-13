package pkgMain;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
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

	private Image background = new Image(getClass().getResourceAsStream("/img/bkdirt.png"));
	private BackgroundImage backgroundimage = new BackgroundImage(background, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.DEFAULT, 
               BackgroundSize.DEFAULT);
	
	private Label budgetLabel, gardenLabel;
	private TextField budget, gardenName;
	private Button clear, submit;
	private Text sun, moisture, soil;
	private Slider sunSlider, moistSlider, soilSlider;
	private HBox input, sunBox, moistBox, soilBox;
	
	private BorderPane border;
	private FlowPane flow;
	
	/*public ConditionScreen() {
		border = new BorderPane();
		flow = new FlowPane(Orientation.VERTICAL);
		flow.setBackground(new Background(backgroundimage));
		border.setCenter(flow);
		border.setAlignment(flow, Pos.CENTER);
		flow.setAlignment(Pos.CENTER);
		flow.setVgap(20);
	}*/
	
	public ConditionScreen() {
		setSunSlider();
		setMoistSlider();
		setSoilSlider();
		setText();
		setInputHBox();
		setScreen();
		
	}
	
	public BorderPane getScreen() {
		return this.border;
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
		
		
		border.setTop(input);
		border.setCenter(flow);
		BorderPane.setAlignment(flow, Pos.CENTER);
		flow.setAlignment(Pos.CENTER);
		flow.setVgap(20);
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
		
		sunBox = setSliderHbox(sunSlider);
	}
	
	public void setSoilSlider() {
		soilSlider = setSlider();
		soilSlider.setLabelFormatter(new StringConverter<Double>() {
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
		soilBox = setSliderHbox(soilSlider);
	}
	
	public void setMoistSlider() {
		moistSlider = setSlider();
		moistSlider.setLabelFormatter(new StringConverter<Double>() {
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
		
		moistBox = setSliderHbox(moistSlider);
	}
	
	public void setInputHBox() {
		budgetLabel = new Label("Budget $: ");
		gardenLabel = new Label("Garden Name: ");
		
		submit = new Button("Submit");
		clear = new Button("Clear");
		
		budget = new TextField();
		budget.setPromptText("Enter your Budget $");
		gardenName = new TextField();
		gardenName.setPromptText("Enter Your Garden Name: ");
		
		input =  new HBox(5);
		input.setPadding(new Insets(5, 5, 5, 5));
		input.setStyle("-fx-background-color: pink;");
		input.getChildren().addAll(budgetLabel, budget, gardenLabel, gardenName, submit, clear);
		
	}
	
	public HBox setSliderHbox(Slider slide) {
		HBox box = new HBox(5);
		box.setPadding(new Insets(5, 5, 5, 5));
		box.setStyle("-fx-background-color: transparent;");
		box.getChildren().addAll(slide);
		
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
