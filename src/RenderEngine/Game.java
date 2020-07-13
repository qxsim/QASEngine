package RenderEngine;

import Level.Level;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * This is the main class for the game engine, which is based on JavaFX and written from scratch.
 * Run this file.
 * Running this will load up a test level.
 * @author Qasim Nawaz
 * @version 25/02/2019
 */
public class Game extends Application {
	
	public static final int WIDTH = 960; //Use WIDTH and SCALE to scale up the window.
	public static final int HEIGHT = 540; //16:9 aspect ratio.
	public static final int SCALE = 1; //Use WIDTH and SCALE to scale up the window.
	
	private Pane root = new Pane();
	private Level level;
	
	@Override
	public void start(Stage stage) throws Exception {
		Scene scene = new Scene(root);
		stage.setTitle("FightNight!");
		Canvas canvas = new Canvas(WIDTH * SCALE, HEIGHT * SCALE);
		GraphicsContext g = canvas.getGraphicsContext2D();
		stage.setScene(scene);
		stage.setResizable(false);
		level = new Level();

		root.getChildren().add(canvas);
		
		scene.setOnKeyPressed(event -> keyPressed(event));
		scene.setOnKeyReleased(event -> keyReleased(event));
		
		AnimationTimer timer = new AnimationTimer() {
           
			@Override
            public void handle(long now) {
                update();
                g.clearRect(0, 0, WIDTH, HEIGHT);
                render(g);
            }
        };
        
        timer.start();
		
        stage.show();
	}
	
	private void update() {
		level.update();
	}
	
	private void render(GraphicsContext g) {
		level.render(g);
	}
	
	public void keyPressed(KeyEvent e) {
		level.keyPressed(e.getCode().toString());
	}

	public void keyReleased(KeyEvent e) {
		level.keyReleased(e.getCode().toString());
	}
	
	public static void main(String[] args) {
        launch(args);
    }
}