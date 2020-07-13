package Level;

import javafx.scene.canvas.GraphicsContext;

/**
 * This is a test level to test the rendering capabilities of the engine. 
 * It is a basic arena fight level.
 * @author Qasim Nawaz
 * @version 25/02/2019
 */
public class Level {
    
    private Background bg;
    private Player player;
    private Player player2;
    
    public Level() { //Just a test level
        init();
    }
    
    private void init() {
        bg = new Background();
        bg.setMovementAmount(0, 0);
        player = new Player(125.0d, 355.0d, 0);
        player2 = new Player(635.0d, 355.0d, 1);
    }

    public void update() {
        bg.update();
        player.update();
        player2.update();
        
        if (player.intersects(player2)) {
            System.out.println("COLLISION!");
        }
    }

    public void render(GraphicsContext g) {
        bg.render(g);
        player.render(g);
        player2.render(g);
    }
    
    public void keyPressed(String k) {
        if (k == "A") {
            player.setLeft(true);
        }
        
        if( k == "D") {
            player.setRight(true);
        }
        
        if (k == "Q") {
            player.setPunch(true);
        }
        
        if (k == "P") {
            player2.setPunch(true);
        }
        
        if (k == "RIGHT") {
            player2.setRight(true);
        }
        
        if (k == "LEFT") {
            player2.setLeft(true);
        }
        
        
    }
    
    public void keyReleased(String k) {
        if (k == "A") {
            player.setLeft(false);
        }
        
        if( k == "D") {
            player.setRight(false);
        }
        
        if (k == "Q") {
            player.setPunch(false);
        }
        
        if (k == "P") {
            player2.setPunch(false);
        }
        
        if (k == "RIGHT") {
            player2.setRight(false);
        }
        
        if (k == "LEFT") {
            player2.setLeft(false);
        }
    }
}