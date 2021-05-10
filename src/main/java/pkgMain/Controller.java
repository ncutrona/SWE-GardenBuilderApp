package pkgMain;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
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
import javafx.geometry.Rectangle2D;
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
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.Modality;
import javafx.stage.Screen;
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
	
	public boolean alphabetFilter = false;
	public boolean lepsFilter = false;
	public String soilFilter = "";
	public String sunFilter = "";
	public String moistureFilter = "";
	
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
		
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();
		
		window = primaryStage;
		view = new View(); 
		model = new Model();
		readCsv();
		readLepsCsv();
		view.gardenScreen.createPlantImageList(model.plantsMaster);
		
		//call screen handler so buttons and stuff actually do something
		popUpHandler();
		// invScreenHandler();
		saveScreenHandler();
		gardenScreenHandler();
		loadScreenHandler();
		conditionScreenHandler();
		hexagonScreenHandler();
		lepsSupportedScreenHandler();
		seeLepsSupportedScreenHandler();
		
		window.setTitle("GARDEN BUILDER v 0.01 ~ BETA");
		window.setScene(view.getScreen());
		window.setMaximized(true);
		window.show();
    	
	}
	
	
	/**
	 * popUpHandler method, sets up actions and events for the pop up window.
	 * Handles saving of garden to file "Save.txt".
	 */
	public void popUpHandler() {
		// Setting an action for the options button
		
    	view.popup.restart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				model.gardenState.totalLepsSupported = 0;
				model.lepsInGardenArray.clear();
				model.addedPlants.clear();
				view.clearInfo();
				view.closePopUp();
				hexagonAnchorHandler();
				window.setScene(view.loadScreenToScene());
			}
    	});
    	
    	view.popup.save.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				SaveGarden save = new SaveGarden(model.gardenCondition.getLength(), model.gardenCondition.getWidth());
				save.setName(model.gardenState.getGardenName());
				save.setBudget(model.gardenState.getGardenBudget());
				save.setNumLepSupported(model.gardenState.getTotalLepsSupported());
				save.setConditions(model.gardenCondition.getSoil(),model.gardenCondition.getSun(), model.gardenCondition.getMoisture());
				save.setPlants(model.addedPlants);
				save.setHexPoints(view.hexagonScreen.hexagon.getPoints());
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

	
	/**
	 * saveScreenHandler method.
	 * handles prevButton event, calls load button handler
	 */
	public void saveScreenHandler() {
		view.saveScreen.prevButton.setOnAction(e-> window.setScene(view.loadScreenToScene()));
		
		saveScreenLoadButtonHandler();
	}
	
	
	/**
	 * saveScreenLoadButtonHandler method
	 * handles loading from file on button press.
	 */
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
					view.gardenScreen.updateScreen(model.gardenCondition, model.gardenState);
					view.gardenScreen.updateLepAndBudget(garden.getNumLepSupported(), garden.getBudget());
					view.loadHexagonToGarden(garden.getHexPoints());
					view.loadPlantsToGarden(model.plantDataList, model.addedPlants, model.gardenCondition.getLength(), model.gardenCondition.getWidth());
					window.setScene(view.gardenScreenToScene());
				}
				
			});
		}
	}
	
	/**
	 * saveScreenLoadModel method.
	 * Handles loading model from SaveGarden.
	 * Called from saveScreenLoadButtonHandler with SaveGarden garden from file.
	 * 
	 * @param garden SaveGarden garden, from file
	 */
	public void saveScreenLoadModel(SaveGarden garden) {
		model.gardenCondition = new GardenConditions(garden.getBudget(), garden.getSunCondition(), garden.getMoistCondition(), garden.getSoilCondition());
		model.gardenState.GardenName = garden.getName();
		model.gardenState.totalLepsSupported = garden.getNumLepSupported();
		//model.addedPlants = garden.getPlants().;
		for(String plant : garden.getPlants().keySet()) {
			for(Coordinates c : garden.getPlants().get(plant)) {
				gardenScreenAddPlant(plant, c.getX(), c.getY());
			}
		}
		model.gardenCondition.setDimensions(garden.getLength(), garden.getWidth());
	}
	
	
	/**
	 * hexagonScreenHandler method.
	 * Controls view of hexagonScreen.
	 * Calls anchor handler.
	 */
	public void hexagonScreenHandler() {
		
		view.hexagonScreen.set.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.gardenScreen.gardenPane.getChildren().add(view.fitHexToGarden(view.hexagonScreen.hexagon));
				window.setScene(view.gardenScreenToScene());
			}
		});
		hexagonAnchorHandler();
	}
	
	
	/**
	 * pentagonAnchorHandler method
	 * Controls the view and events of hexagonScreen via anchors
	 */
	public void hexagonAnchorHandler() {
		for(Anchor a: view.hexagonScreen.anchors) {
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
	
	
	/**
	 * gardenScreenHandler method.
	 * controls the view off garden screen, handles adding/dragging plants,
	 * gardenPane, options buttons, inventory, and finish button.
	 */
	public void gardenScreenHandler() {
	
		view.addPlantToGarden(Plant.getConditionCheckedPlants(model.plantsMaster, model.gardenCondition.getSun(), 
				model.gardenCondition.getSoil(), model.gardenCondition.getMoisture()));
		
    	view.gardenScreen.gardenPane.setOnDragDropped(new EventHandler <DragEvent>(){
			public void handle(DragEvent event) {
				double[] coords = view.plantDragDropping(event, model.addedPlants, getPlantList() , model.gardenCondition.getWidth());
				// plantDragDropping returns the coordinates that the image are being placed, if -1.0s then no images are being places
				if(coords[0] != -1.0 && coords[1] != -1.0) {
					Plant p = model.plantDataList.get(event.getDragboard().getString());
					gardenScreenAddPlant(p.getScientificName(), coords[0], coords[1]);
					setModelLepAndBudget(p.getLepsSupported(), p.getPrice());
					view.gardenScreen.updateLepAndBudget(model.gardenState.totalLepsSupported, model.gardenState.gardenBudget);
					view.lepSupportedScreen.createLepImageList(model.lepsInGardenArray); //HERE
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
    					view.invScreen.invTile.getChildren().clear();
    					view.invScreen.invTileControls.getChildren().clear();
    				}
    	    	});
    	    	view.invScreen.filterByLepsAlphabet.setOnAction(new EventHandler<ActionEvent>() {
    				@Override
    				public void handle(ActionEvent event) {
    					if (view.invScreen.filterByLepsAlphabet.getValue().equals("A to Z")) {
    						alphabetFilter = true;
    					}
    					else {
    						alphabetFilter = false;
    					}
    					
    					if (view.invScreen.filterByLepsAlphabet.getValue().equals("Leps supported")) {
    						lepsFilter = true;
    					}
    					else {
    						lepsFilter = false;
    					}
    				}
    	    	});
    	    	view.invScreen.filterBySoil.setOnAction(new EventHandler<ActionEvent>() {
    				@Override
    				public void handle(ActionEvent event) {
    					soilFilter = (String)view.invScreen.filterBySoil.getValue();
    					soilFilter = soilFilter.toLowerCase();

    				}
    	    	});
    	    	view.invScreen.filterBySun.setOnAction(new EventHandler<ActionEvent>() {
    				@Override
    				public void handle(ActionEvent event) {
    					sunFilter = (String)view.invScreen.filterBySun.getValue();
    					sunFilter = sunFilter.toLowerCase();
    					
    					if(sunFilter.contains("full")){
    						sunFilter = "full";
    					}
    					else if(sunFilter.contains("partial")){
    						sunFilter = "partial";
    					}
    				}
    	    	});
    	    	view.invScreen.filterByMoisture.setOnAction(new EventHandler<ActionEvent>() {
    				@Override
    				public void handle(ActionEvent event) {
    					moistureFilter = (String)view.invScreen.filterByMoisture.getValue();
    					moistureFilter = moistureFilter.toLowerCase();
    					if(moistureFilter.contains("medium")){
    						moistureFilter = "moist";
    					}
    				}
    	    	});
    	    	
    	    	view.invScreen.filterButton.setOnAction(new EventHandler<ActionEvent>() {
    				@Override
    				public void handle(ActionEvent event) {
    					view.invScreen.invTile.getChildren().clear();
    					view.invScreen.invTileControls.getChildren().clear();
    					if (alphabetFilter) {
    						Map<String, Plant> aToZ = new TreeMap<String, Plant>(Plant.sortInvByConditions(model.plantsMaster, soilFilter, sunFilter, moistureFilter));
    						view.displayInvAtoZ(aToZ);
    		    			window.setScene(view.invScreenToScene());
    					}
    					else if (lepsFilter) {
    						view.displayInvLepsSupported(Plant.sortInvByConditions(Plant.getLepSupportedPlants(model.plantsMaster), soilFilter, sunFilter, moistureFilter));
    		    			window.setScene(view.invScreenToScene());
    					}
    					else if(soilFilter != "Refine soil conditions:" || sunFilter != "Refine sun conditions:" || moistureFilter != "Refine moisture conditions:") {
    						view.displayInvLepsSupported(Plant.sortInvByConditions(model.plantsMaster, soilFilter, sunFilter, moistureFilter));
    		    			window.setScene(view.invScreenToScene());
    					}
    				}
    	    	});
 
        	}
        });
    	
    	view.gardenScreen.finish.setOnMouseClicked(new EventHandler<MouseEvent>() {	
    		@Override
    		public void handle(MouseEvent e) {
    			
    			view.setSummaryScreen(model.plantDataList, model.addedPlants,model.gardenState.getGardenName(),model.gardenState.getGardenBudget(), model.gardenState.getTotalLepsSupported());
    			window.setScene(view.sumScreenToScene());
    			view.popup.save.fire();
				
    		
    	    	view.sumScreen.returnHome.setOnAction(new EventHandler<ActionEvent>() {
    				@Override
    				public void handle(ActionEvent event) {
    					view.clearInfo();
    					model.gardenState.totalLepsSupported = 0;
    					model.addedPlants.clear();
    					model.lepsInGardenArray.clear();
    					view.sumScreen.clearSumScreen();
    					hexagonAnchorHandler();
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
    	
    	view.gardenScreen.lepsSupported.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.lepSupportedScreen.updateLepEncyclopedia(model.lepsInGardenArray);
				window.setScene(view.lepSupportedScreenToScene());
				
			}
    	});
 
	}
	
	/**
	 * deletePlantUpdateState method.
	 * updates total leps supported and garden budget in model and view.
	 *  
	 * @param removed Plant being removed
	 */
	public void deletePlantUpdateState(Plant removed) {
		model.gardenState.totalLepsSupported -= removed.getLepsSupported();
		model.gardenState.gardenBudget += removed.getPrice();
		view.gardenScreen.updateLepAndBudget(model.gardenState.totalLepsSupported, model.gardenState.gardenBudget);
	}
	
	
	/**
	 * getDeleteMenu method.
	 * Sets up the delete menu for deleting plants from model, view
	 * 
	 * @param plant ImageView of plant to be deleted
	 * @param plantObject Plant, passed to deletePlantUpdateState()
	 * @return ContextMenu for the delete menu
	 */
	public ContextMenu getDeleteMenu(ImageView plant, Plant plantObject) {
		ContextMenu contextMenu = new ContextMenu();
		MenuItem deletePlant = new MenuItem("Compost plant");
        deletePlant.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.gardenScreen.gardenPane.getChildren().remove(plant);
                deletePlantUpdateState(plantObject);
                //only deletes the lep if there is only one plant left that supports the plant 
                if(model.addedPlants.get(plant.getId()).size()== 1){
                	model.lepsInGardenArray.remove(model.lepsMap.get(plant.getId()));
                }
                System.out.println(model.addedPlants.get(plant.getId()).size());
                //model.addedPlants.get(plant.getId()).remove(new Coordinates(plant.getX(), plant.getY()));
                ArrayList<Coordinates> newCords = deletePlantFromList(model.addedPlants.get(plant.getId()), plant.getX(), plant.getY());
                model.addedPlants.put(plant.getId(), newCords); 
            }
        });
		contextMenu.getItems().add(deletePlant);
		return contextMenu;
	}
	
	/**
	 * deletePlantFromList method.
	 * Deletes plant from passed in arraylist coords matching the passed in x and y coords.
	 * 
	 * @param coords ArrayList of plant coordinates
	 * @param x x coord of plant to be deleted
	 * @param y y coord of plant to be deleted
	 * @return original passed in ArrayList-Coordinates- without the deleted plant
	 */
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
	
	
	/**
	 * handles back button for leps supported screen
	 */
	public void lepsSupportedScreenHandler() {
		view.lepScreen.back.setOnAction(e-> window.setScene(view.loadScreenToScene()));
		view.lepScreen.mtCubaLink.setOnAction(e -> {
		    if(Desktop.isDesktopSupported())
		    {
		        try {
		            Desktop.getDesktop().browse(new URI("https://mtcubacenter.org"));
		        } catch (IOException e1) {
		            e1.printStackTrace();
		        } catch (URISyntaxException e1) {
		            e1.printStackTrace();
		        }
		    }
		});
	}
	
	public void seeLepsSupportedScreenHandler() {
    	view.lepSupportedScreen.goBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
    			view.lepSupportedScreen.lepInfoTile.getChildren().clear();
    			window.setScene(view.gardenScreenToScene());
			}
    	});
	}
	
	/**
	 * handles start, load, and learn buttons for loadScreen
	 */
	public void loadScreenHandler() {
		view.loadScreen.startButton.setOnAction(e-> window.setScene(view.conditionScreenToScene()));
		view.loadScreen.loadButton.setOnAction(e-> window.setScene(view.saveScreenToScene()));
		view.loadScreen.learn.setOnAction(e-> window.setScene(view.lepScreenToScene()));
	}
	
	/**
	 * handles next button view conditionsScreen.
	 * Sets conditions for sliders in model.
	 * update conditions in view and show pentagon screen.
	 */
	public void conditionScreenHandler() {
		view.conditionScreen.next.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(conditionScreenHelper()) {
					String [] soilList = {"sand", "loam", "clay"};
					String [] sunList = {"shade","partial","full"};
					String [] moistList = {"dry", "moist", "wet"};
					
					int [] sliderValues = view.returnConditionSliderValue();
					
					model.gardenCondition.setMoistureConditions(moistList[sliderValues[0]]);
					model.gardenCondition.setSunConditions(sunList[sliderValues[1]]);
					model.gardenCondition.setSoilConditions(soilList[sliderValues[2]]);
					view.gardenScreen.updateScreen(model.gardenCondition, model.gardenState);
					window.setScene(view.hexagonScreenToScene());
				}
			}
    	});
		view.conditionScreen.previous.setOnAction(e-> window.setScene(view.loadScreenToScene()));
	}
	
	/**
	 * Increments totalLepsSupported and decrements gardenBudget based on price
	 * 
	 * @param lep int, increments totalLepsSupported
	 * @param price int price of plant, removed from gardenBudget
	 */
	public void setModelLepAndBudget(int lep, int price) {
		model.gardenState.totalLepsSupported += lep;
		model.gardenState.gardenBudget -= price;
	}
	
	/**
	 * returns the plantList from model
	 * 
	 * @return HashMap-String, Plant- model.plantDataList
	 */
	public HashMap<String, Plant> getPlantList() {
		return model.plantDataList;
	}
	
	/**
	 * helper method for conditionScreen.
	 * updates some model values based on view when needed.
	 * 
	 * @return return false when nothing happens, true when values are updated
	 */
	public boolean conditionScreenHelper() {
		if (view.conditionHasText()) {
			try {
				int intBudget = Integer.parseInt(view.conditionScreen.budget.getText());
				int length = Integer.parseInt(view.conditionScreen.length.getText());
				int width = Integer.parseInt(view.conditionScreen.width.getText());
				if(!view.saveScreen.savedGarden.containsKey(view.conditionScreen.gardenName.getText())) {
					model.gardenState.setGardenName(view.conditionScreen.gardenName.getText());
					model.gardenCondition.setBudget(intBudget);
					model.gardenState.gardenBudget = model.gardenCondition.getBudget();
					model.gardenCondition.setDimensions(length, width);
					return true;
				}
				view.setValidNameText();
			} catch(Exception except) {				
				view.setValidBudgetText();
			}
		}
		return false;
	}
	
	/**
	 * Code for reading plant info from csv in src/main/resources/Plant_Data.csv
	 */
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
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void readLepsCsv() throws IOException {
		File lepData = Paths.get("src/main/resources/leps_supported_USA.csv").toFile().getAbsoluteFile();
		BufferedReader br = new BufferedReader(new FileReader(lepData));
		String line = "";
		try {
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				String lepName = data[2];
				String plantName = data[4];
				model.lepsMap.put(plantName, lepName);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (Map.Entry mapElement : model.lepsMap.entrySet()) {
			String key = (String) mapElement.getKey();
			String lep = (String) mapElement.getValue();
			for (int i = 0; i < 100; i++) {
				if(model.plantsMaster.get(i).getScientificName().equals(key)) {
					model.plantWithLepSuppMap.put(model.plantsMaster.get(i).getScientificName(), lep);
				}
			}
		}
	}
	
	/**
	 * adds a plant with nodeID, pos x,y to model
	 * 
	 * @param nodeID String nodeID of plant to add
	 * @param x double x pos of plant
	 * @param y double y pos of plant
	 */
	public void gardenScreenAddPlant(String nodeID, double x, double y) {
		
		if(model.addedPlants.containsKey(nodeID)) {
			if(model.addedPlants.get(nodeID).size() > 0) {
				model.addedPlants.get(nodeID).add(new Coordinates(x,y));
				return;
			}
		}
		
		if(!model.addedPlants.containsKey(nodeID)) {
			model.addedPlants.put(nodeID, new ArrayList<Coordinates>());
		}
		
		if (model.lepsMap.containsKey(nodeID) && !model.lepsInGardenArray.contains(nodeID)) {
				model.lepsInGardenArray.add(model.lepsMap.get(nodeID));
				model.addedPlants.get(nodeID).add(new Coordinates(x,y));
				return;
		}
		
		if(!model.addedPlants.containsKey(nodeID)) {
				model.addedPlants.put(nodeID, new ArrayList<Coordinates>());
			}
			
		
		model.addedPlants.get(nodeID).add(new Coordinates(x,y));
		
	}
}