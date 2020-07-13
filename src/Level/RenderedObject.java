package Level;

import RenderEngine.Animation;
import javafx.scene.canvas.GraphicsContext;

/**
 * This is an abstract class that is used for any object that will be displayed by the engine.
 * @author Qasim Nawaz
 * @version 25/02/2019
 */
public abstract class RenderedObject {
    
    protected Animation animation;

    public abstract void update();  
    public abstract void render(GraphicsContext g);
}