package RenderEngine;

import javafx.scene.image.Image;

/**
 * This class handles all the animation for the engine.
 * @author Qasim Nawaz
 * @version 25/02/2019
 */
public class Animation {
	
	private Image[] frameArray;
	
	private int currentFrame;
	
	private long startTime;
	private long delay;
	
	private boolean looped;
	
	public Animation() {
		looped = false;
	}
	
	public void importFrames(Image[] frameArr) {
		frameArray = frameArr;
		currentFrame = 0;
		startTime = System.nanoTime();
		looped = false;
	}
	
	public void setDelay(long val) { 
		delay = val;
	}
	
	public void setFrame(int i) { 
		currentFrame = i;
	}
	
	public void update() {
		
		long timeTaken = (System.nanoTime() - startTime) / 1000000;
		
		if (timeTaken > delay) {
			currentFrame++;
			startTime = System.nanoTime();
		}
		
		if (currentFrame == frameArray.length) {
			currentFrame = 0;
			looped = true;
		}
		
	}
	
	public int getFrame() { 
		return currentFrame; 
	}
	
	public Image getImage() { 
		return frameArray[currentFrame]; 
	}
	
	public boolean hasPlayedOnce() { 
		return looped; 
	}
}