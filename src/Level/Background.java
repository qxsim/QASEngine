package Level;

import RenderEngine.Animation;
import RenderEngine.Game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This class renders the background images. 
 * It is a RenderedObject.
 * @author Qasim Nawaz
 * @version 25/02/2019
 */
public class Background extends RenderedObject {
	
	private static final int NUMFRAMES = 32;
	
	private double x;
	private double y;
	private double moveByX;
	private double moveByY;
	
	public Background() {
		
		Image[] bgArray = new Image[NUMFRAMES];
		for(int i = 0; i < NUMFRAMES; i++) {
			try {
				bgArray[i] = new Image("/images/Backgrounds/bg/" + i + ".gif/", 960, 540, false, true);
			}
			
			catch (Exception e) {
				e.printStackTrace();
			}	
		}
		
		animation = new Animation();
		animation.importFrames(bgArray);
		animation.setDelay(90);
	}
	
	public void setMovementAmount(double x, double y) {
		moveByX = x;
		moveByY = y;
	}
	
	public void update() {
		animation.update();
		x += moveByX;
		y += moveByY;
	}
	
	public void render(GraphicsContext g) {
		
		if (x < (Game.WIDTH*-1)) {
			x = 0.0d;
		}
		
		g.drawImage(animation.getImage(), x, y);
		
		if (x < 0) {
			g.drawImage(animation.getImage(), x + Game.WIDTH, y);
		}
		
		else {
			g.drawImage(animation.getImage(), x, y);
		}
		
	}
}