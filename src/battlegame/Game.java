
package battlegame;

import java.io.IOException;

import battlegame.graphics.Assets;
import battlegame.input.KeyboardController;
import battlegame.world.World;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game extends Application{
	
	public static int screenWidth = 920;
	public static int screenHeight = 600;
		
	 private final long[] frameTimes = new long[100];
	 private int frameTimeIndex = 0 ;
	 private boolean arrayFilled = false ;
	
	private Canvas canvas;
	private boolean upTyped = true;
	private Stage stage;
	private Scene scene;
	private Group group;
	private GraphicsContext g;
	private final Duration fpsTime = Duration.millis(1000/60);
	private static World currentWorld;
	private static KeyboardController keyController;
	
	public Game() {
		keyController = new KeyboardController();
	}
	
	public void begin(String args[]) {
		launch(args);
	}
	
	public void initialise() throws IOException {
		canvas = new Canvas(screenWidth, screenHeight);
		group = new Group(canvas);
		scene = new Scene(group);
		g = canvas.getGraphicsContext2D();
		Assets.init();
		currentWorld = new World("/testlvl.txt");
		currentWorld.init();


	}

	@SuppressWarnings("deprecation")
	@Override
	public void start(Stage primaryStage)throws Exception {
		initialise();

		@SuppressWarnings({ "unchecked", "rawtypes" })
		final KeyFrame oneFrame = new KeyFrame(fpsTime,
		   new EventHandler() {
			@Override
			public void handle(Event event) {
			      render();
			      tick();
			 				
			      long oldFrameTime = frameTimes[frameTimeIndex] ;
	                frameTimes[frameTimeIndex] = System.nanoTime() ;
	                frameTimeIndex = (frameTimeIndex + 1) % frameTimes.length ;
	                if (frameTimeIndex == 0) {
	                    arrayFilled = true ;
	                }
	                if (arrayFilled) {
	                    long elapsedNanos = System.nanoTime() - oldFrameTime ;
	                    long elapsedNanosPerFrame = elapsedNanos / frameTimes.length ;
	                    double frameRate = 1_000_000_000.0 / elapsedNanosPerFrame ;
	                }
			}

		});
		 
		TimelineBuilder.create()
		   .cycleCount(Animation.INDEFINITE)
		   .keyFrames(oneFrame)
		   .build()
		   .play();
		stage = primaryStage;
		stage.getIcons().add(new Image(Game.class.getResourceAsStream("/GameIcon.png")));
		stage.setScene(scene);
		stage.setResizable(true);
		stage.setOnCloseRequest(event -> {
		
		});
		stage.show();
		scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
		      if(upTyped && (key.getCode() == KeyCode.W || key.getCode() == KeyCode.UP || key.getCode() == KeyCode.SPACE)) {
		          keyController.up = true;
		          upTyped = false;
		      }
		      if(key.getCode() == KeyCode.A || key.getCode() == KeyCode.LEFT) {
		    	  keyController.left = true;
		      }
		      if(key.getCode() == KeyCode.S || key.getCode() == KeyCode.DOWN) {
		    	  keyController.down = true;
		      }
		      if(key.getCode() == KeyCode.D || key.getCode() == KeyCode.RIGHT) {
		    	  keyController.right = true;
		      }
		      if(key.getCode() == KeyCode.Z || key.getCode() == KeyCode.O || key.getCode() == KeyCode.ENTER) {
		    	  keyController.select = true;
		      }
		      if(key.getCode() == KeyCode.P || key.getCode() == KeyCode.X) {
		    	  keyController.action = true;
		      }
		      
		});
		
		scene.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
		      if(key.getCode() == KeyCode.W || key.getCode() == KeyCode.UP || key.getCode() == KeyCode.SPACE) {
		          keyController.up = false;
		          upTyped = true;
		      }
		      if(key.getCode() == KeyCode.A || key.getCode() == KeyCode.LEFT) {
		    	  keyController.left = false;
		      }
		      if(key.getCode() == KeyCode.S || key.getCode() == KeyCode.DOWN) {
		    	  keyController.down = false;
		      }
		      if(key.getCode() == KeyCode.D || key.getCode() == KeyCode.RIGHT) {
		    	  keyController.right = false;
		      }
		      if(key.getCode() == KeyCode.Z || key.getCode() == KeyCode.O || key.getCode() == KeyCode.ENTER) {
		    	  keyController.select = false;
		      }
		      if(key.getCode() == KeyCode.P || key.getCode() == KeyCode.X) {
		    	  keyController.action = false;
		      }		});
		

	}
	
	
	public void tick() {
		currentWorld.tick();
		keyController.tick();
	}
	
	public void render() {
		g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		currentWorld.render(g);
	}
	
	public static void main(String[] args){
		launch();
	}
	
	public static KeyboardController getKeyController(){
		return keyController;
	}

	public static World getCurrentWorld() {
		return currentWorld;
	}


	
	}



