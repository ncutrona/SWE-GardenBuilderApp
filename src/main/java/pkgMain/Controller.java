package pkgMain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.print.PageLayout;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller class from MVC design pattern.
 * Extends from Application.
 *
 * @author Aidan Chao
 * @author Nicholas Cutrona
 * @author Caleb Davis
 * @author Joey Loporto
 * @author Tommy Cheung
 */
public class Controller extends Application{

	View view;
	Stage window;
	Model model;
	
	/**
	 * main method, launches application.
	 * Launch hands off execution to javafx to do various backend tasks
	 * such as setting up graphics and system specifics.
	 * 
	 * @param args console arguments
	 */
	public static void main(String[] args) {
		launch();
	}

	/**
	 * Overrides the start method from Application.
	 * Sets up view.gardenScreen, ConditionScreen, Load Screen,
	 * Save Screen, Inventory Screen, and Popup Scene.
	 * Sets actions for events and buttons.
	 * 
	 * @param primaryStage primary Stage object
	 */
	@Override
	public void start(Stage primaryStage) throws IOException{
		window = primaryStage;
		view = new View(); 
		model = new Model();
		readCsv();
		view.gardenScreen.createPlantImageList(model.plantsMaster);
		
		//call screen handler so buttons and stuff actually do something
		popUpHandler();
		// invScreenHandler();
		saveScreenHandler();
		gardenScreenHandler();
		loadScreenHandler();
		conditionScreenHandler();
		pentagonScreenHandler();
		lepsSupportedScreenHandler();
		
		window.setTitle("GARDEN BUILDER v 0.01 ~ ALPHA");
		window.setScene(view.getScreen());
		window.show();
    	
	}
	
	public void popUpHandler() {
		// Setting an action for the options button
		
    	view.popup.restart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.clearInfo();
				view.closePopUp();
				pentagonAnchorHandler();
				window.setScene(view.loadScreenToScene());
			}
    	});
    	
    	view.popup.save.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				SaveGarden save = new SaveGarden(model.gardenFinal.getLength(), model.gardenFinal.getWidth());
				save.setName(model.stateFinal.getGardenName());
				save.setBudget(model.stateFinal.getGardenBudget());
				save.setNumLepSupported(model.stateFinal.getTotalLepsSupported());
				save.setConditions(model.gardenFinal.getSoil(),model.gardenFinal.getSun(), model.gardenFinal.getMoisture());
				save.setPlants(model.addedPlants);
				save.setHexPoints(view.pentagonScreen.hexagon.getPoints());
				view.saveScreen.savedGarden.put(save.getName(), save);
				try {
					File f = new File("Save.txt");
					FileOutputStream fos = new FileOutputStream(f);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(view.saveScreen.savedGarden);
					fos.close();
					oos.close();
					//view.closePopUp(); THIS WILL BREAK IF UNCOMMENTED. IF YOU DNT CLICK ON OPTIONS, POPUP IS NuLL AND THROWS EXCEPTION TO BREAK RETURN HOME BUTTON.
					view.saveScreen.loadGardens();
					view.saveScreen.createScreen();
					saveScreenLoadButtonHandler();
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
    		
    	});
    	view.popup.resume.setOnAction(k -> view.closePopUp());
    	
	}

	public void saveScreenHandler() {
		view.saveScreen.prevButton.setOnAction(e-> window.setScene(view.loadScreenToScene()));
		
		saveScreenLoadButtonHandler();
	}
	public void saveScreenLoadButtonHandler() {
		for(Node n: view.saveScreen.fillBox.getChildren()) {
			HBox h = (HBox)n;
			Button b = (Button)h.getChildren().get(0);
			b.setOnMouseClicked(new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent event) {
					//starts at 5 because this gets rid of "Load "
					SaveGarden garden = view.saveScreen.savedGarden.get(b.getText().substring(5));
					saveScreenLoadModel(garden);
					view.gardenScreen.updateCondition(model.gardenFinal);
					view.gardenScreen.updateLepAndBudget(garden.getNumLepSupported(), garden.getBudget());
					view.loadHexagonToGarden(garden.getHexPoints());
					view.loadPlantsToGarden(model.plantDataList, model.addedPlants, model.gardenFinal.getLength(), model.gardenFinal.getWidth());
					window.setScene(view.gardenScreenToScene());
				}
				
			});
		}
	}
	public void saveScreenLoadModel(SaveGarden garden) {
		model.gardenFinal = new GardenConditions(garden.getBudget(), garden.getSunCondition(), garden.getMoistCondition(), garden.getSoilCondition());
		model.stateFinal.GardenName = garden.getName();
		model.stateFinal.totalLepsSupported = garden.getNumLepSupported();
		model.addedPlants = garden.getPlants();
		model.gardenFinal.setDimensions(garden.getLength(), garden.getWidth());
	}
	public void pentagonScreenHandler() {
		view.pentagonScreen.set.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
				view.gardenScreen.gardenPane.getChildren().add(view.pentagonScreen.hexagon);
				window.setScene(view.gardenScreenToScene());
				
			}
			
		});
		pentagonAnchorHandler();
	}
	public void pentagonAnchorHandler() {
		for(Anchor a: view.pentagonScreen.anchors) {
			final Coordinates dragDelta = new Coordinates();
			a.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override public void handle(MouseEvent mouseEvent) {
					// record a delta distance for the drag and drop operation.
					dragDelta.x = a.getCenterX() - mouseEvent.getX();
					dragDelta.y = a.getCenterY() - mouseEvent.getY();
					a.getScene().setCursor(Cursor.MOVE);
				}
			});
			a.setOnMouseReleased(new EventHandler<MouseEvent>() {
				@Override public void handle(MouseEvent mouseEvent) {
					a.getScene().setCursor(Cursor.HAND);
				}
			});
			a.setOnMouseDragged(new EventHandler<MouseEvent>() {
				@Override public void handle(MouseEvent mouseEvent) {
					double newX = mouseEvent.getX() + dragDelta.x;
					if (newX > 0 && newX < a.getScene().getWidth()) {
						a.setCenterX(newX);
					}
					double newY = mouseEvent.getY() + dragDelta.y;
					if (newY > 0 && newY < a.getScene().getHeight()) {
						a.setCenterY(newY);
					}
				}
			});
			a.setOnMouseEntered(new EventHandler<MouseEvent>() {
				@Override public void handle(MouseEvent mouseEvent) {
					if (!mouseEvent.isPrimaryButtonDown()) {
						a.getScene().setCursor(Cursor.HAND);
					}
				}
			});
			a.setOnMouseExited(new EventHandler<MouseEvent>() {
				@Override public void handle(MouseEvent mouseEvent) {
					if (!mouseEvent.isPrimaryButtonDown()) {
						a.getScene().setCursor(Cursor.DEFAULT);
					}
				}
			});
		}
	}
	public void gardenScreenHandler() {
		view.addPlantToGarden(Plant.conditionCheckedPlants(model.plantsMaster, model.gardenFinal.getSun(), 
				model.gardenFinal.getSoil(), model.gardenFinal.getMoisture()));
		
    	view.gardenScreen.gardenPane.setOnDragDropped(new EventHandler <DragEvent>(){
			public void handle(DragEvent event) {
				//System.out.println("Length: " + model.gardenFinal.getLength() + " Width: "  + model.gardenFinal.getWidth());
				double[] coords = view.plantDragDropping(event, getPlantList(), model.gardenFinal.getLength(), model.gardenFinal.getWidth());
				if(coords[0] != 0 && coords[1] != 0) {
					Plant p = model.plantDataList.get(event.getDragboard().getString());
					gardenScreenAddPlant(p.getScientificName(), coords[0], coords[1]);
					setModelLepAndBudget(p.getLepsSupported(), p.getPrice());
					view.gardenScreen.updateLepAndBudget(model.stateFinal.totalLepsSupported, model.stateFinal.gardenBudget);
				}
				
				event.setDropCompleted(true);
				event.consume();
			}
		});
    	view.gardenScreen.gardenPane.setOnDragOver(new EventHandler <DragEvent>() {
			@Override
    		public void handle(DragEvent event) {
				view.plantDragOver(event);
				event.consume();
			}
		});
    	view.gardenScreen.optionsButton.setOnAction(e -> view.popScreenToStage());
    	
    	// Delete plant handler
    	view.gardenScreen.gardenPane.setOnMouseClicked(new EventHandler<MouseEvent>() {	
    		@Override
    		public void handle(MouseEvent e) {
    			try {
    				ImageView eplant = (ImageView)e.getTarget();
    			}catch(Exception excep) {
    				return;
    			}
    			if (e.getButton() == MouseButton.SECONDARY) {
    				e.consume();
    				Plant removed = view.deletePlant(e.getTarget(), model.plantDataList);
    					ImageView removePlant = (ImageView) e.getTarget();
    					removePlant.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
    						@Override
    						public void handle(ContextMenuEvent event) {
    							getDeleteMenu(removePlant, removed).show(removePlant, event.getScreenX(), event.getScreenY());
    						}
    					});
    			}
    		}
    	});
    	
    	view.gardenScreen.inventory.setOnMouseClicked(new EventHandler<MouseEvent>() {	
			@SuppressWarnings("unchecked")
			@Override
    		public void handle(MouseEvent e) {
    			view.invScreen.createInventoryScreen(model.plantDataList, view.gardenScreen.returnPlantImageList());
    			window.setScene(view.invScreenToScene());
    			
    	    	view.invScreen.prevButtonInv.setOnAction(new EventHandler<ActionEvent>() {
    				@Override
    				public void handle(ActionEvent event) {
    					window.setScene(view.gardenScreenToScene());
    				}
    	    	});
    	    	view.invScreen.filterBy.setOnAction(new EventHandler<ActionEvent>() {
    				@Override
    				public void handle(ActionEvent event) {
    					if (view.invScreen.filterBy.getValue().equals("A to Z")) {
    						Map<String, Plant> aToZ = new TreeMap<String, Plant>(model.plantDataList);
    						view.invScreen.invGrid.getChildren().clear();
    		    			view.invScreen.createInventoryScreen(aToZ, view.gardenScreen.returnPlantImageList());
    		    			window.setScene(view.invScreenToScene());
    					}
    				}
    	    	});
 
        	}
        });
    	
    	view.gardenScreen.finish.setOnMouseClicked(new EventHandler<MouseEvent>() {	
    		@Override
    		public void handle(MouseEvent e) {
    			
    			view.setSummaryScreen(model.plantDataList, model.addedPlants,model.stateFinal.getGardenName(),model.stateFinal.getGardenBudget(), model.stateFinal.getTotalLepsSupported());
    			window.setScene(view.sumScreenToScene());
    			view.popup.save.fire();
				
    		
    	    	view.sumScreen.returnHome.setOnAction(new EventHandler<ActionEvent>() {
    				@Override
    				public void handle(ActionEvent event) {
    					view.clearInfo();
    					model.addedPlants.clear();
    					view.sumScreen.clearSumScreen();
    					pentagonAnchorHandler();
    					window.setScene(view.loadScreenToScene());
    					
    				}
    	    	});
    	    	view.sumScreen.printInfo.setOnAction(f-> {
    	    		Printer printer = Printer.getDefaultPrinter();
    	    	    PageLayout pageLayout = printer.getDefaultPageLayout();
    	    	    
    	    	    Node node = view.sumScreen.getScreen();

    	    	    // Printable area
    	    	    double pWidth = pageLayout.getPrintableWidth();
    	    	    double pHeight = pageLayout.getPrintableHeight();

    	    	    // Node's (Image) dimensions
    	    	    double nWidth = node.getBoundsInParent().getWidth();
    	    	    double nHeight = node.getBoundsInParent().getHeight();

    	    	    // How much space is left? Or is the image to big?
    	    	    double widthLeft = pWidth - nWidth;
    	    	    double heightLeft = pHeight - nHeight;

    	    	    // scale the image to fit the page in width, height or both
    	    	    double scale;

    	    	    if (widthLeft < heightLeft) {
    	    	    	scale = pWidth / nWidth;
    	    	    }
    	    	    else {
    	    	    	scale = pHeight / nHeight;
    	    	    }

    	    	    // preserve ratio (both values are the same)
    	    	    node.getTransforms().add(new Scale(scale, scale));
					PrinterJob job = PrinterJob.createPrinterJob();
					if(job != null){
						job.showPrintDialog(window);
						job.printPage(node);
						job.endJob();
						node.getTransforms().clear();
					}
    	    	});
        	}	
        });
    	
 
	}
	
	public void deletePlantUpdateState(Plant removed) {
		model.stateFinal.totalLepsSupported -= removed.getLepsSupported();
		model.stateFinal.gardenBudget += removed.getPrice();
		view.gardenScreen.updateLepAndBudget(model.stateFinal.totalLepsSupported, model.stateFinal.gardenBudget);
	}
	
	public ContextMenu getDeleteMenu(ImageView plant, Plant plantObject) {
		ContextMenu contextMenu = new ContextMenu();
		MenuItem deletePlant = new MenuItem("Delete plant");
        deletePlant.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.gardenScreen.gardenPane.getChildren().remove(plant);
                deletePlantUpdateState(plantObject);
                //First find coordinates to delete, then stores the new arraylist of coordinates to the plant id
                ArrayList<Coordinates> newCords = deletePlantFromList(model.addedPlants.get(plant.getId()), plant.getX(), plant.getY());
                model.addedPlants.put(plant.getId(), newCords); 
            }
        });
		contextMenu.getItems().add(deletePlant);
		return contextMenu;
	}
	
	public ArrayList<Coordinates> deletePlantFromList(ArrayList<Coordinates> coords, double x, double y) {
		ListIterator<Coordinates> cds = coords.listIterator();
		while(cds.hasNext()) {
			Coordinates c = cds.next();
			if(c.getX() == x && c.getY() == y) {
				cds.remove();
			}
		}
		return coords;
	}
	
	public void lepsSupportedScreenHandler() {
		view.lepScreen.back.setOnAction(e-> window.setScene(view.loadScreenToScene()));
	}
	
	
	public void loadScreenHandler() {
		view.loadScreen.startButton.setOnAction(e-> window.setScene(view.conditionScreenToScene()));
		view.loadScreen.loadButton.setOnAction(e-> window.setScene(view.saveScreenToScene()));
		view.loadScreen.learn.setOnAction(e-> window.setScene(view.lepScreenToScene()));
		
	}
	public void conditionScreenHandler() {
		
		view.conditionScreen.next.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(conditionScreenHelper()) {
					String [] soilList = {"sand", "loam", "clay"};
					String [] sunList = {"shade","partial","full"};
					String [] moistList = {"dry", "moist", "wet"};
					
					int [] sliderValues = view.returnConditionSliderValue();
					//System.out.println(sliderValues);
					
					model.gardenFinal.setMoistureConditions(moistList[sliderValues[0]]);
					model.gardenFinal.setSunConditions(sunList[sliderValues[1]]);
					model.gardenFinal.setSoilConditions(soilList[sliderValues[2]]);
					view.gardenScreen.updateCondition(model.gardenFinal);
					window.setScene(view.pentagonScreenToScene());
					
				}
				
			}
    		
    	});
		view.conditionScreen.previous.setOnAction(e-> window.setScene(view.loadScreenToScene()));
	}
	
	public void setModelLepAndBudget(int lep, int price) {
		model.stateFinal.totalLepsSupported += lep;
		model.stateFinal.gardenBudget -= price;
	}
	public HashMap<String, Plant> getPlantList() {
		return model.plantDataList;
	}
	public boolean conditionScreenHelper() {
		if (view.conditionHasText()) {
			try {
				int intBudget = Integer.parseInt(view.conditionScreen.budget.getText());
				int length = Integer.parseInt(view.conditionScreen.length.getText());
				int width = Integer.parseInt(view.conditionScreen.width.getText());
				if(!view.saveScreen.savedGarden.containsKey(view.conditionScreen.gardenName.getText())) {
					model.stateFinal.setGardenName(view.conditionScreen.gardenName.getText());
					model.gardenFinal.setBudget(intBudget);
					model.stateFinal.gardenBudget = model.gardenFinal.getBudget();
					model.gardenFinal.setDimensions(length, width);
					return true;
				}
				view.setValidNameText();
			} catch(Exception except) {				
				view.setValidBudgetText();
			}
		}
		return false;
	}
	
	// Code for reading in from a file
	public void readCsv() throws IOException {
		File plantData = Paths.get("src/main/resources/Plant_Data.csv").toFile().getAbsoluteFile();
		BufferedReader br = new BufferedReader(new FileReader(plantData));
		String line = "";
		try {
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				Plant plant = new Plant(Integer.parseInt(data[5]), Integer.parseInt(data[1]),
						data[0].strip(), data[2], data[4], data[3], Integer.parseInt(data[7]), Integer.parseInt(data[8]), Integer.parseInt(data[6]), 0, 0);
				model.plantsMaster.add(plant);
				model.plantDataList.put(plant.getScientificName(), plant);
				//System.out.println(plant.getScientificName());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void gardenScreenAddPlant(String nodeID, double x, double y) {
		if(!model.addedPlants.containsKey(nodeID)) {
			model.addedPlants.put(nodeID, new ArrayList<Coordinates>());
		}
		model.addedPlants.get(nodeID).add(new Coordinates(x,y));
	}
}