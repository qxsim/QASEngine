package Level;

import java.util.ArrayList;
import RenderEngine.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This class renders the player sprite images and handles all player behaviours.
 * This includes inputs and move-sets.
 * It is a RenderedObject.
 * @author Qasim Nawaz
 * @version 25/02/2019
 */
public class Player extends RenderedObject { //Just a test sprite
	
	private ArrayList<Image[]> sprites;
	private final int[] numFrames = {10, 10, 6}; // Number of frames in each action
	
	private static final int IDLE = 0; //Numbers allocated to each action. NOTE: MUST correspond with number in folder structure.
	private static final int WALKING = 1;
	private static final int PUNCHING = 2;
	
	private int currentAction;
	
	private double x;
	private double y;
	private double moveByX;
	private double moveByY;
	int flip;
	boolean left, right, punching;
	
	public Player(double startPosX, double startPosY, int Orientation) {
		 
		if (Orientation == 1) {
			flip = 1;
		}
		
		 x = startPosX;
		 y = startPosY;
		
		
		sprites = new ArrayList<Image[]>();
		for (int i = 0; i < numFrames.length; i++) {
			Image[] action = new Image[numFrames[i]];
			
			for(int j = 0; j < numFrames[i]; j++) {
				try {
					action[j] = new Image("/images/Player/"+i+"/"+j+".gif", 150, 150, false, true);
					//Sprites layout must be in folder format[ (integer corresponding to action) > sprite images numbered from 0 -> num of frames ]
				}
				
				catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			sprites.add(action);
		
		}
		
		animation = new Animation();
		currentAction = IDLE;
		animation.importFrames(sprites.get(IDLE));
		animation.setDelay(60);
	}
	
	public void update() {
		
		if (left || right) {
			if (currentAction != WALKING) {
				currentAction = WALKING;
				animation.importFrames(sprites.get(WALKING));
				animation.setDelay(60);
			}
		}
		
		else if (punching) {
			if (currentAction != PUNCHING) {
				currentAction = PUNCHING;
				animation.importFrames(sprites.get(PUNCHING));
				animation.setDelay(60);
			}
		}
		
		else if (currentAction != IDLE) {
			currentAction = IDLE;
			animation.importFrames(sprites.get(IDLE));
			animation.setDelay(60);
		}
		
		animation.update();
		
		x += moveByX;
		y += moveByY;
	}
	
	public void render(GraphicsContext g) {
		if (flip == 1) {
			//Flipped image
		    g.drawImage(animation.getImage(), 0, 0, animation.getImage().getWidth(), animation.getImage().getHeight(), x + 200.0d, y, -animation.getImage().getWidth(), animation.getImage().getHeight());
		}
		
		else {
			g.drawImage(animation.getImage(), x, y);
		}
	}
	
	public Rectangle2D getBoundary() {
        return new Rectangle2D(x + 37.5d, y + 37.5d, 52.0d, 52.0d);
    }
	
	public boolean intersects(Player player) {
		return player.getBoundary().intersects(this.getBoundary());
	}
	
	public void setPunch(Boolean b) {
		if (b) {
			punching = true;
		}
		
		else {
			punching = false;
		}
	}

	public void setRight(Boolean b) {
		if (b) {
			right = true;
			flip = 0;
			moveByX = 2.0d;
		}
		
		else {
			right = false;
			moveByX = 0.0d;
		}
	}
	
	public void setLeft(Boolean b) {
		if (b) {
			left = true;
			flip = 1;
			moveByX = -2.0d;
		}
		
		else {
			left = false;
			moveByX = 0.0d;
		}
	}
	
	public void setJump(Boolean b) {
		if (b) {
			moveByY = 0.0d;
		}
		
		else {
			moveByY = 0.0d;
		}
	}
	
	public void setDuck(Boolean b) {
		moveByY = 0.0d;
	}
}