package Drones;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Interface to display the arena containing all objects
 */
public class Interface extends Application {
    private ObjectCanvas mc;
    private AnimationTimer timer;								// timer used for animation
    private VBox rtPane;										// vertical box for putting info
    private Arena arena;
    /**
     * about pop up box that displays short messsage
     */
    private void showAbout() {
        Alert alert = new Alert(AlertType.INFORMATION);				// define what box is
        alert.setTitle("About");									// say is About
        alert.setHeaderText(null);
        alert.setContentText("Welcome the big drone game click to move the playerdrone and knock the balls into the goal without being blocked");			// give text
        alert.showAndWait();										// display the box until the user closes it
    }
    /**
     * set up the mouse event - when mouse pressed move the player object to that position
     * @param canvas
     */
    void MouseClick (Canvas canvas) {
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, 		// for MOUSE PRESSED event
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        arena.setPaddle(e.getX(), e.getY());
                        drawWorld();							// redraw world
                        drawStatus();
                    }
                });
    }
    /**
     * set up the menu containing the different functions
     * @return the menu bar
     */
    MenuBar setMenu() {
        MenuBar menuBar = new MenuBar();						// create main menu

        Menu mFile = new Menu("File");							// add File main menu
        MenuItem mExit = new MenuItem("Exit");					// whose sub menu has Exit
        mExit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {					// action on exit is
                timer.stop();									// stop timer
                System.exit(0);									// exit program
            }
        });
        //mFile.getItems().addAll(mExit);							// add exit to File menu

        MenuItem mSave = new MenuItem("Save");					// whose sub menu has Save
        mSave.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {					// action on exit is
            	save();
            }
        });

        MenuItem mLoad = new MenuItem("Load");					// whose sub menu has Save
        mLoad.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {					// action on exit is
                load();
            }
        });
        mFile.getItems().addAll(mExit, mSave, mLoad);							// add exit to File menu

        Menu mHelp = new Menu("Help");							// create Help menu
        MenuItem mAbout = new MenuItem("About");				// add About sub men item
        mAbout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showAbout();									// and its action to print about
            }
        });
        mHelp.getItems().addAll(mAbout);						// add About to Help main item

        menuBar.getMenus().addAll(mFile, mHelp);				// set main menu with File, Help
        return menuBar;											// return the menu
    }

    /**
     * set up the horizontal box for the bottom with relevant buttons
     * @return
     */
    private HBox setButtons() {
        Button btnStart = new Button("Start");			// button to start the simulation
        btnStart.setOnAction(click -> timer.start());

        Button btnStop = new Button("Stop");				// button to stop the simulation
        btnStop.setOnAction(click -> timer.stop());

        Button btnAddDrone = new Button("Add Drone");				// button to add another drone/ball
        btnAddDrone.setOnAction(click -> {arena.addDrone(); drawWorld();});

        // now add these buttons + labels to a HBox
        return new HBox(btnStart, btnStop, btnAddDrone);
    }

    /**
     * draw the score at position x,y
     * @param x
     * @param y
     * @param score
     */
    public void showScore (double x, double y, int score) {
        mc.drawText(x, y, Integer.toString(score));
    }
    /**
     * draw the world with ball in it
     */
    public void drawWorld () {
        mc.clearCanvas();						
        arena.drawArena(mc);
    }

    /**
     *  in pane on right describe the position of each object
     */
    public void drawStatus() {
        rtPane.getChildren().clear();					// clear rtpane
        ArrayList<String> allDs = arena.describeAll();
        for (String s : allDs) {
            Label l = new Label(s); 		// turn description into a label
            rtPane.getChildren().add(l);	// add label
        }
    }


    /**
     *  function to start the program and set up the position of the toolbars and canvas
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        primaryStage.setTitle("The Big Drone Game");
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10, 20, 10, 20));

        bp.setTop(setMenu());											// put at the top

        Group root = new Group();										// create group with canvas
        Canvas canvas = new Canvas( 400, 500 );
        root.getChildren().add( canvas );
        bp.setLeft(root);												// load canvas to left area

        mc = new ObjectCanvas(canvas.getGraphicsContext2D(), 400, 500);

        MouseClick(canvas);											// set up mouse events

        arena = new Arena(400, 500);								// set up arena
        drawWorld();

        timer = new AnimationTimer() {									// set up timer
            public void handle(long currentNanoTime) {					// and its action when on
                arena.checkDrones();									// check the angle of all balls
                arena.adjustDrones();								// move all balls
                drawWorld();										// redraw the world
                drawStatus();										// indicate where balls are
            }
        };

        rtPane = new VBox();											// set vertical Box on right of the window
        rtPane.setAlignment(Pos.TOP_LEFT);								// set alignment
        rtPane.setPadding(new Insets(5, 75, 75, 5));					// padding
        bp.setRight(rtPane);											// add rtPane to borderpane right

        bp.setBottom(setButtons());										// set bottom pane with buttons

        Scene scene = new Scene(bp, 700, 600);							// set overall scene
        bp.prefHeightProperty().bind(scene.heightProperty());
        bp.prefWidthProperty().bind(scene.widthProperty());

        primaryStage.setScene(scene);
        primaryStage.show();


    }
    /**
     *  function to load a simulation from the text file
     */
    public void load() {
    	String url = "simulation.txt";
    	try {
    		ObjectInputStream loader = new ObjectInputStream(new FileInputStream(url));
    		arena = (Arena) loader.readObject();
    		loader.close();
    		drawWorld();										// redraw the world
    	} catch(Exception e) {
    		System.out.println(e);
    	}
    }
    /**
     *  function to save the simulation as a string to a text file
     */
    public void save() {
    	String url = "simulation.txt";
    	try {
    		ObjectOutputStream saver = new ObjectOutputStream(new FileOutputStream(url));
    		saver.writeObject(arena);
    		saver.close();
    	} catch(Exception e) {
    		System.out.println(e);
    	}
    }

    /**
     * launches the GUI
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(args);			// launch the GUI

    }

}
